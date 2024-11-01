import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginError: string | null = null;

  constructor() {}

  onSubmit(form: NgForm) {
    const username = form.value.username;
    const password = form.value.password;

    // Simulate a login check (you can customize this)
    if (username === 'admin' && password === 'password') {
      // Successful login simulation
      alert('Login successful! Redirecting to dashboard...');
      // Here you could navigate to the dashboard
    } else {
      // Failed login simulation
      this.loginError = 'Invalid username or password.';
    }
  }
}
