import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
})
export class HeaderComponent {
  DashboardName = 'Arc-i-Tech';
  notificationCount = 3;
  loggedInUser: any = { email: 'arc-i-tech.org' };
  notifications = [
    { message: 'New user registered' },
    { message: 'New course added' },
    { message: 'System update scheduled' }
  ];

  logout(): void {
    console.log('Static logout triggered');
  }
}
