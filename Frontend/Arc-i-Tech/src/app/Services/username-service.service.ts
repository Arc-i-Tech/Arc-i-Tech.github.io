import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

// Define a proper model (you can adjust fields as per backend)
export interface UserNameRequest {
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class UsernameServiceService {

  private userNameLink = "http://localhost:8000/api/auth/send-otp";

  constructor(private http: HttpClient) {}

  // ✅ Strongly typed parameter
  userName(username: UserNameRequest): Observable<any> {
    console.log(username);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<any>(this.userNameLink, username, { headers }).pipe(
      catchError((error) => {
        console.error('Error sending email:', error);
        return throwError(() => error);
      })
    );
  }
}
