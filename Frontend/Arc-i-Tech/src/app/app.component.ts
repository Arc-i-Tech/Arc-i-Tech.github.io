import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { OtpVerificationComponent } from './otp-verification/otp-verfication/otp-verfication.component';
import { ResetPasswordComponent } from './reset-pass/reset-password.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ResetPasswordComponent, OtpVerificationComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'application';
}

