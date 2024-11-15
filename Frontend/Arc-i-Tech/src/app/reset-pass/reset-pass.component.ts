
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../user';
import { PasswordService } from '../password.service';

@Component({
  selector: 'app-reset-pass',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './reset-pass.component.html',
  styleUrls: ['./reset-pass.component.css'],
})
export class ResetPassComponent {
  user: User = new User('', '', '');
  message: string = '';
  isPasswordMatch: boolean = true;

  constructor(private userService: PasswordService) {}

  validatePasswordMatch() {
    this.isPasswordMatch = this.user.newPassword === this.user.confirmPass;
  }

  onSubmit(): void {
    this.validatePasswordMatch(); 

    if (this.isPasswordMatch) {
      this.userService.userPassword(this.user).subscribe({
        next: (response) => {
          this.message = 'Password Updated Successfully!';
          console.log(response);
        },
        error: (error) => {
          this.message = 'Something went wrong';
          console.error(error);
        },
      });
    } else {
      this.message = 'New Password and Confirm Password must be the same.';
    }
  }
}
