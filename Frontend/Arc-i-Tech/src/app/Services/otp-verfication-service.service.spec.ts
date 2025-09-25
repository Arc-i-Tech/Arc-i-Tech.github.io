import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OtpVerificationService {
  private baseUrl = 'http://localhost:8000/api/auth';

  constructor(private http: HttpClient) {}

  // Send OTP
  sendOtp(username: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}/send-otp`, { username }, { headers }).pipe(
      catchError(error => {
        console.error('Error sending OTP:', error);
        return throwError(() => error);
      })
    );
  }

  // Verify OTP
  verifyOtp(username: string, otpStr: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}/verify-otp`, { username, otpStr }, { headers }).pipe(
      catchError(error => {
        console.error('Error verifying OTP:', error);
        return throwError(() => error);
      })
    );
  }

  // Reset Password
  resetPassword(username: string, otpStr: string, newPassword: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}/reset-password`, { username, otpStr, newPassword }, { headers }).pipe(
      catchError(error => {
        console.error('Error resetting password:', error);
        return throwError(() => error);
      })
    );
  }
}
