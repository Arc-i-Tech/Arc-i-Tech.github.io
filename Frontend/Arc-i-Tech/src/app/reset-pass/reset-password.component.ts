import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { User } from '../user';
import { PasswordService } from '../password.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reset-pass',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {
  resetForm: FormGroup;
  message: string = '';

  constructor(private fb: FormBuilder, private userService: PasswordService) {
    this.resetForm = this.fb.group({
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator: ValidatorFn = (group: AbstractControl): ValidationErrors | null => {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordMismatch: true };
  }

  onSubmit(): void {
    if (this.resetForm.valid) {
      const passwordData = new User(
        this.resetForm.value.password,
        this.resetForm.value.confirmPassword
      );

      this.userService.userPassword(passwordData).subscribe({
        next: (res) => {
          this.message = 'Password Updated Successfully!';
          console.log(res);
        },
        error: (err) => {
          this.message = 'Something went wrong';
          console.error(err);
        }
      });
    } else {
      this.message = 'Please fill the form correctly.';
    }
  }
}
