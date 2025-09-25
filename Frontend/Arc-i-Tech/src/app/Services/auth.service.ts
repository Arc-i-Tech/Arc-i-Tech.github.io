import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

export interface ResetPasswordRequest {
  username: string;
  otpStr: string;
  newPassword: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8000/api/auth';

  constructor(private http: HttpClient) {}

  // Send OTP
  sendOtp(username: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.apiUrl}/send-otp`, { username }, { headers }).pipe(
      catchError((error) => {
        console.error('Error sending OTP:', error);
        return throwError(() => error);
      })
    );
  }

  // Reset password (also validates OTP inside backend)
  resetPassword(req: ResetPasswordRequest): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.apiUrl}/reset-password`, req, { headers }).pipe(
      catchError((error) => {
        console.error('Error resetting password:', error);
        return throwError(() => error);
      })
    );
  }
}
