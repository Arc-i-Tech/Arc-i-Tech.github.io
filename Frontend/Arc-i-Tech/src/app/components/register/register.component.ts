
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RegistrationService } from '../service/registration.service';
import { User } from './User';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  user: User = new User('', '', '', '', '', '', '', '');
  message: string = '';

  constructor(private userService: RegistrationService) {}

  onSubmit(): void {
    if (this.isFormValid()) {
      this.userService.userRegister(this.user).subscribe({
        next: (response: any) => {
          this.message = response.message || 'Registered Successfully!';
          this.resetForm();
        },
        error: (error) => {
          this.message = 'Something went wrong. Please try again.';
          console.error('Error:', error);
        }
      });
    } else {
      this.message = 'Please fill all required fields.';
    }
  }

private isFormValid(): boolean {
  return !!this.user.fname.trim() &&
         !!this.user.username.trim() &&
         !!this.user.email.trim() &&
         !!this.user.password.trim() &&
         !!this.user.address.trim() &&
         !!this.user.mob.trim() &&
         !!this.user.city.trim() &&
         !!this.user.pcode.trim();
}


  private resetForm(): void {
    this.user = new User('', '', '', '', '', '', '', '');
  }
}

