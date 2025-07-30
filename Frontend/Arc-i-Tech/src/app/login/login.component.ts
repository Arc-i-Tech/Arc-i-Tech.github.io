import { Component } from '@angular/core';
import { UserService } from '../login-service.service';
import { Router, RouterOutlet } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private router: Router, private userService: UserService) {}

  onSubmit(): void {
    const loginPayload = {
      username: this.username,
      password: this.password
    };

    this.userService.loginUser(loginPayload).subscribe({
      next: (res: any) => {
        localStorage.setItem('jwtToken', res.token);
        localStorage.setItem('userEmail', res.email);
        localStorage.setItem('userRole', res.role);

        setTimeout(() => {
          if (res.role === 'ADMIN') {
            this.router.navigate(['/dashboard']);
          } else if (res.role === 'USER') {
            this.router.navigate(['/user']);
          } else {
            this.router.navigate(['/home']);
          }
        }, 1000);
      },
      error: (err) => {
        alert('Login failed: ' + (err?.error?.message || err.message));
      }
    });
  }
}
