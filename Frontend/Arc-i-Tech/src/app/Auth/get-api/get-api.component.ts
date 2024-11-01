// import { HttpClient } from '@angular/common/http';
// import { Component, inject } from '@angular/core';
// import { FormsModule } from '@angular/forms';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-get-api',
//   standalone: true,
//   imports: [FormsModule],
//   templateUrl: './get-api.component.html',
//   styleUrl: './get-api.component.css'
// })
// export class GetApiComponent {

//   userList :any[]=[];
//   userObj: any = {
//     username: '',
//     password: ''
//   };
//   constructor(private http: HttpClient,router:Router) {}


//   onLoginPage() {
//     alert('hello');
    
    
//      this.http.post("http://localhost:8080/loginPage",this.userObj).subscribe((res:any)=>{
//       if(res.result){
//         alert("login success");
//       }else{
//          alert(res.message)
//          console.log(this.userObj)
//       }
//      })
//   }
// }

import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthServiceService } from '../../../Users/services/auth-service.service';

@Component({
  selector: 'app-get-api',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './get-api.component.html',
  styleUrls: ['./get-api.component.css'],
})
export class GetApiComponent {
  userObj = {
    username: '',
    password: '',
  };

  constructor(private authService: AuthServiceService) {}

  onLoginPage() {
    this.authService.login(this.userObj.username, this.userObj.password).subscribe({
      next: (res: any) => {
        if (res.result) {
          alert('Login successful');
        } else {
          alert(res.message);
        }
      },
      error: (error) => {
        console.error('Login error:', error);
        alert('An error occurred during login.');
      },
      complete: () => {
        console.log('Login request completed');
      },
    });
  }
  
}
