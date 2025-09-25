import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { AuthService, ResetPasswordRequest } from '../Services/auth.service';

@Component({
  selector: 'app-reset-username',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reset-username-url.component.html',
  styleUrls: ['./reset-username.component.css']
})
export class ResetUsernameComponent {
  resetForm: FormGroup;
  message = '';
  messageType: 'success' | 'error' | 'info' | '' = '';

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.resetForm = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      otpStr: ['', [Validators.required, Validators.pattern(/^[0-9]{6}$/)]],
      newPassword: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
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

    this.authService.resetPassword(req).subscribe({
      next: () => {
        this.setMessage('Password reset successful!', 'success');
        this.resetForm.reset();
      },
      error: (err) => {
        const errorMessage = err?.error?.error || 'Failed to reset password.';
        this.setMessage(errorMessage, 'error');
      }
    });
  }

  private setMessage(message: string, type: 'success' | 'error' | 'info' | ''): void {
    this.message = message;
    this.messageType = type;
  }
}
