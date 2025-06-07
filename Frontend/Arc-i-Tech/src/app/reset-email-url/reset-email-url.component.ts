import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PasswordService } from '../password.service';
import { Email } from '../Email';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reset-email-url',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reset-email-url.component.html',
  styleUrls: ['./reset-email-url.component.css']
})
export class ResetEmailUrlComponent {
  resetForm: FormGroup;
  message: string = '';
  messageType: 'success' | 'error' | '' = '';

  constructor(private fb: FormBuilder, private userService: PasswordService) {
    this.resetForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSubmit(): void {
    if (this.resetForm.invalid) {
      this.message = 'Please enter a valid email address.';
      this.messageType = 'error';
      return;
    }

    const emailObj = new Email(this.resetForm.value.email);

    this.userService.userEmail(emailObj).subscribe({
      next: () => {
        this.message = `✅ Password reset link sent `;
        this.messageType = 'success';
        this.resetForm.reset();
      },
      error: () => {
        this.message = `❌ Failed to send reset link `;
        this.messageType = 'error';
      }
    });
  }
}
