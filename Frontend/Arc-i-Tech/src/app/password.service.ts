import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Email } from './Email';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {
  private emailLink = 'https://localhost:8000/email';  //change url as per backend API.
  private passwordUrl = 'https://localhost:8000/passwordUrl';      //change url as per backend API.

  constructor(private http: HttpClient) {}

  userEmail(email: Email): Observable<any> {
    return this.http.post<any>(this.emailLink, email).pipe(
      catchError((error) => {
        console.error('Error sending email:', error);
        return throwError(() => error);
      })
    );
  }

  userPassword(user: User): Observable<any> {
    return this.http.post<any>(this.passwordUrl, user).pipe(
      catchError((error) => {
        console.error('Error updating password:', error);
        return throwError(() => error);
      })
    );
  }
}
