import { Routes } from '@angular/router';
import { MainPanelComponent } from './dashboard/main-panel/main-panel.component';
import { UserDashboard } from './User/user-dashboard';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
       {path:'dashboard', component:MainPanelComponent},
       { path: 'user', component: UserDashboard },
         { path: '', component: LoginComponent }

];
