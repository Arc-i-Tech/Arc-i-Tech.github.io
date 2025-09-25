import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
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

import { OtpVerificationService } from '../../Services/otp-verfication-service.service';

@Component({
  selector: 'app-otp-verification',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './otp-verfication.component.html',
  styleUrl: './otp-verfication.component.css'
})
export class OtpVerificationComponent {
  currentStep: 1 | 2 | 3 = 1;

  emailForm: FormGroup;
  otpForm: FormGroup;
  passwordForm: FormGroup;

  message = '';
  messageType: 'success' | 'error' | 'info' | '' = '';
  maskedEmail: string | null = null;

  isSendingOtp = false;
  isVerifyingOtp = false;
  isResetting = false;

  private verifiedUsername: string | null = null;
  private verifiedOtp: string | null = null;

  constructor(private fb: FormBuilder, private otpService: OtpVerificationService) {
    this.emailForm = this.fb.group({
      username: ['', [Validators.required, Validators.email]]
    });

    this.otpForm = this.fb.group({
      otpStr: ['', [Validators.required, Validators.pattern(/^[0-9]{6}$/)]]
    });

    this.passwordForm = this.fb.group(
      {
        newPassword: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', Validators.required]
      },
      { validators: this.passwordMatchValidator }
    );
  }

  get username(): AbstractControl | null {
    return this.emailForm.get('username');
  }

  get otpStr(): AbstractControl | null {
    return this.otpForm.get('otpStr');
  }

  get newPassword(): AbstractControl | null {
    return this.passwordForm.get('newPassword');
  }

  get confirmPassword(): AbstractControl | null {
    return this.passwordForm.get('confirmPassword');
  }

  get activeUsername(): string | null {
    return this.verifiedUsername ?? (this.emailForm.value.username || null);
  }

  passwordMatchValidator: ValidatorFn = (group: AbstractControl): ValidationErrors | null => {
    const pass = group.get('newPassword')?.value;
    const confirm = group.get('confirmPassword')?.value;
    if (!pass || !confirm) {
      return null;
    }
    return pass === confirm ? null : { passwordMismatch: true };
  };

  goToStep(step: 1 | 2 | 3): void {
    this.currentStep = step;
  }

  backToEmail(): void {
    this.setMessage('', '');
    this.otpForm.reset();
    this.passwordForm.reset();
    this.verifiedOtp = null;
    this.goToStep(1);
  }

  backToOtp(): void {
    if (!this.verifiedUsername) {
      this.backToEmail();
      return;
    }
    this.setMessage('', '');
    this.passwordForm.reset();
    this.goToStep(2);
  }

  sendOtp(): void {
    if (this.emailForm.invalid) {
      this.emailForm.markAllAsTouched();
      this.setMessage('Please enter a valid email address.', 'error');
      return;
    }

    const usernameValue = this.emailForm.value.username;
    this.isSendingOtp = true;
    this.setMessage('', '');

    this.otpService
      .sendOtp(usernameValue)
      .pipe(finalize(() => (this.isSendingOtp = false)))
      .subscribe({
        next: (res) => {
          this.verifiedUsername = usernameValue;
          this.maskedEmail = res?.Email ?? null;
          this.otpForm.reset();
          this.passwordForm.reset();
          this.verifiedOtp = null;
          const successMessage = res?.success ?? 'OTP sent successfully to registered email.';
          const emailInfo = this.maskedEmail ? ` OTP sent to ${this.maskedEmail}.` : '';
          this.setMessage(successMessage + emailInfo, 'info');
          this.goToStep(2);
        },
        error: (err) => {
          const errorMessage = err?.error?.error || 'Failed to send OTP. Please try again.';
          this.setMessage(errorMessage, 'error');
        }
      });
  }

  resendOtp(): void {
    if (this.verifiedUsername) {
      this.emailForm.patchValue({ username: this.verifiedUsername });
    }
    this.sendOtp();
  }

  verifyOtp(): void {
    if (!this.verifiedUsername) {
      this.setMessage('Please start by entering your email.', 'error');
      this.goToStep(1);
      return;
    }

    if (this.otpForm.invalid) {
      this.otpForm.markAllAsTouched();
      this.setMessage('Please enter the 6-digit OTP sent to your email.', 'error');
      return;
    }

    const otpValue = this.otpForm.value.otpStr;
    this.isVerifyingOtp = true;
    this.setMessage('', '');

    this.otpService
      .verifyOtp(this.verifiedUsername, otpValue)
      .pipe(finalize(() => (this.isVerifyingOtp = false)))
      .subscribe({
        next: () => {
          this.verifiedOtp = otpValue;
          this.passwordForm.reset();
          this.setMessage('OTP verified successfully! Please set your new password.', 'success');
          this.goToStep(3);
        },
        error: (err) => {
          const errorMessage = err?.error?.error || 'Invalid or expired OTP. Please try again.';
          this.setMessage(errorMessage, 'error');
        }
      });
  }

  resetPassword(): void {
    if (!this.verifiedUsername || !this.verifiedOtp) {
      this.setMessage('OTP verification is required before resetting your password.', 'error');
      this.goToStep(1);
      return;
    }

    if (this.passwordForm.invalid) {
      this.passwordForm.markAllAsTouched();
      this.setMessage('Please correct the highlighted password fields.', 'error');
      return;
    }

    const newPasswordValue = this.passwordForm.value.newPassword;
    this.isResetting = true;
    this.setMessage('', '');

    this.otpService
      .resetPassword(this.verifiedUsername, this.verifiedOtp, newPasswordValue)
      .pipe(finalize(() => (this.isResetting = false)))
      .subscribe({
        next: () => {
          this.setMessage('Password reset successful! You can now log in with your new password.', 'success');
          this.resetFlow();
        },
        error: (err) => {
          const errorMessage = err?.error?.error || 'Failed to reset password. Please try again.';
          this.setMessage(errorMessage, 'error');
        }
      });
  }

  private resetFlow(): void {
    this.emailForm.reset();
    this.otpForm.reset();
    this.passwordForm.reset();
    this.maskedEmail = null;
    this.isSendingOtp = false;
    this.isVerifyingOtp = false;
    this.isResetting = false;
    this.verifiedUsername = null;
    this.verifiedOtp = null;
    this.goToStep(1);
  }

  private setMessage(message: string, type: 'success' | 'error' | 'info' | ''): void {
    this.message = message;
    this.messageType = type;
  }
}
