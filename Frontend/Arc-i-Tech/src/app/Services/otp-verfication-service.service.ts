import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OtpVerificationService {
  private API_BASE = 'http://localhost:8000/api/auth';

  constructor(private http: HttpClient) {}

  sendOtp(username: string): Observable<any> {
    return this.http.post(`${this.API_BASE}/send-otp`, { username });
  }

  verifyOtp(username: string, otpStr: string): Observable<any> {
    return this.http.post(`${this.API_BASE}/verify-otp`, { username, otpStr });
  }

  resetPassword(username: string, otpStr: string, newPassword: string): Observable<any> {
    return this.http.post(`${this.API_BASE}/reset-password`, {
      username,
      otpStr,
      newPassword
    });
  }
}
