import { Routes } from '@angular/router';
import { TestUserComponent } from './dashboard/test-user/test-user.component';
import { ServicesComponent } from './dashboard/services/services.component';
import { UserFeedbackComponent } from './dashboard/user-feedback/user-feedback.component';

export const routes: Routes = [
  {
    path: ' ',redirectTo: '/test-user',pathMatch: 'full'
  },
  { path : 'test-user', component:TestUserComponent},
  {
    path: 'user-service',component:ServicesComponent
  },
  {
    path: 'user-feedback',component:UserFeedbackComponent
  }

];
