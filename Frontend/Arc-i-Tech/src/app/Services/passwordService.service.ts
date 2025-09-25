import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { UserNameRequest } from './username-service.service';

  @Injectable({
    providedIn: 'root'
  })
  export class PasswordService {
    private passwordUrl = 'https://localhost:8000/passwordUrl';      //change url as per backend API.

  constructor(private http: HttpClient) {}
  
  
  
    userPassword(user: UserNameRequest): Observable<any> {
      return this.http.post<any>(this.passwordUrl, user).pipe(
        catchError((error) => {
          console.error('Error updating password:', error);
          return throwError(() => error);
        })
      );
  } 
}
  