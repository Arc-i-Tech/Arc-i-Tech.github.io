import { Routes } from '@angular/router';
import { MainPanelComponent } from './dashboard/main-panel/main-panel.component';
import { User } from './User/user';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
       { path:'login',component:LoginComponent},
       {path:'dashboard', component:MainPanelComponent},
       { path: 'user', component: User },

];
