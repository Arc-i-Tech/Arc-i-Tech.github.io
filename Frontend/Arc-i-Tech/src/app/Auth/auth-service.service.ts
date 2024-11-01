import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  login(user: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/loginPage`, user);
  }
}
