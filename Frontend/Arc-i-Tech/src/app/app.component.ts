import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ResetPasswordComponent } from './reset-pass/reset-password.component';
import { ResetEmailUrlComponent } from './reset-email-url/reset-email-url.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ResetPasswordComponent,ResetEmailUrlComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'application';
}

