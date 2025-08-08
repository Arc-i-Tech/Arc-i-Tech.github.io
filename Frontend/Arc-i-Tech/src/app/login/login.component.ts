import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../login-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private userService: UserService, private router: Router) {}

  login() {
    const payload = { username: this.username, password: this.password };

    this.userService.login(payload).subscribe({
      next: (response: any) => {
        console.log('Login Success', response);
        localStorage.setItem('user', JSON.stringify(response));

        const role = response.role?.toLowerCase();
        if (role === 'admin') {
          this.router.navigate(['/dashboard']);
        } else if (role === 'user') {
          this.router.navigate(['/user']);
        } else {
                            this.router.navigate(['/dashboard']); //  without role base open the dahshboard directly
          this.errorMessage = 'Unauthorized role!';
        }
      },
      error: (err) => {
        console.error('Login failed', err);

        this.errorMessage = 'Invalid credentials';
      }
    });
  }
}
