import { Component } from '@angular/core';
import { userauth } from '../../Users/Classes/userauth';
import { AuthServiceService } from '../../Users/services/auth-service.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule]
})
export class LoginComponent {

  constructor(private AuthService:AuthServiceService) {}
  userauths:userauth=new userauth('','');
   msg:string = '';
  onSubmit() {
   this.AuthService.userAuthService(this.userauths).subscribe({
    next:(Response)=>{
      this.msg="login success";
      console.log(Response);
    },
    error:(error)=>{
        this.msg = "invalid username or password"; 
        console.log(error);
    }
   });
  
  }
}
