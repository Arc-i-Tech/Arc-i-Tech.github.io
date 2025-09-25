import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from '@angular/forms';
import { finalize } from 'rxjs/operators';

import { AuthService, ResetPasswordRequest } from '../Services/auth.service';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {
  resetForm: FormGroup;
  message = '';
  messageType: 'success' | 'error' | 'info' | '' = '';
  otpSent = false;
  maskedEmail: string | null = null;
  isSendingOtp = false;
  isSubmitting = false;

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.resetForm = this.fb.group(
      {
        username: ['', [Validators.required, Validators.email]],
        otpStr: ['', [Validators.required, Validators.pattern(/^[0-9]{6}$/)]],
        newPassword: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', Validators.required]
      },
      { validators: this.passwordMatchValidator }
    );
  }

  passwordMatchValidator: ValidatorFn = (group: AbstractControl): ValidationErrors | null => {
    const pass = group.get('newPassword')?.value;
    const confirm = group.get('confirmPassword')?.value;
    if (!pass || !confirm) {
      return null;
    }
    return pass === confirm ? null : { passwordMismatch: true };
  };

  get username(): AbstractControl | null {
    return this.resetForm.get('username');
  }

  get otpStr(): AbstractControl | null {
    return this.resetForm.get('otpStr');
  }

  get newPassword(): AbstractControl | null {
    return this.resetForm.get('newPassword');
  }

  get confirmPassword(): AbstractControl | null {
    return this.resetForm.get('confirmPassword');
  }

  sendOtp(): void {
    if (!this.username || this.username.invalid) {
      this.username?.markAsTouched();
      this.setMessage('Please enter your registered email address.', 'error');
      return;
    }

    const usernameValue = this.username.value as string;
    this.isSendingOtp = true;
    this.setMessage('', '');

    this.authService
      .sendOtp(usernameValue)
      .pipe(finalize(() => (this.isSendingOtp = false)))
      .subscribe({
        next: (res) => {
          this.otpSent = true;
          this.maskedEmail = res?.Email ?? null;
          this.resetForm.get('otpStr')?.reset('');
          this.resetForm.get('newPassword')?.reset('');
          this.resetForm.get('confirmPassword')?.reset('');
          const successMessage = res?.success ?? 'OTP sent successfully to registered email.';
          const emailInfo = this.maskedEmail ? ` OTP sent to ${this.maskedEmail}.` : '';
          this.setMessage(successMessage + emailInfo, 'info');
        },
        error: (err) => {
          const errorMessage = err?.error?.error || 'Failed to send OTP. User may not exist.';
          this.setMessage(errorMessage, 'error');
        }
      });
  }

  onSubmit(): void {
    if (!this.otpSent) {
      this.setMessage('Please request an OTP before resetting the password.', 'error');
      return;
    }

    if (this.resetForm.invalid) {
      this.resetForm.markAllAsTouched();
      this.setMessage('Please fill all fields correctly.', 'error');
      return;
    }

    const req: ResetPasswordRequest = {
      username: this.resetForm.value.username,
      otpStr: this.resetForm.value.otpStr,
      newPassword: this.resetForm.value.newPassword
    };

    this.isSubmitting = true;
    this.setMessage('', '');

    this.authService
      .resetPassword(req)
      .pipe(finalize(() => (this.isSubmitting = false)))
      .subscribe({
        next: () => {
          this.setMessage('Password reset successful!', 'success');
          this.resetForm.reset();
          this.otpSent = false;
          this.maskedEmail = null;
        },
        error: (err) => {
          const errorMessage = err?.error?.error || 'Failed to reset password. Invalid OTP or expired.';
          this.setMessage(errorMessage, 'error');
        }
      });
  }

  private setMessage(message: string, type: 'success' | 'error' | 'info' | ''): void {
    this.message = message;
    this.messageType = type;
  }
}
