import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ResetPassComponent } from './reset-pass/reset-pass.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ResetPassComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'application';
}

