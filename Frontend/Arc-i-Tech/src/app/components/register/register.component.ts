
import { Component } from '@angular/core';
import { User } from './User';
import { RegistrationService } from '../service/registration.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
   user: User = new User ('','','',0,'',0)
   message: string='';
   
   constructor(private userService:RegistrationService){}
  onSubmit(): void {
        
    this.userService.userRegister(this.user).subscribe({
      next: (response) => {
        this.message = 'Registered Successfully!';
        console.log(response); 
      },
      error: (error) => {
        this.message = "Something went wrong";
        console.error(this.message); 
      }
    });
  }
  
}