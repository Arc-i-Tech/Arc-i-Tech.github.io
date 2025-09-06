import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loginUrl = 'http://localhost:8000/api/auth/login';

  constructor(private http: HttpClient) {}

  login(payload: { username: string; password: string }): Observable<User> {
    return this.http.post<User>(this.loginUrl, payload);
  }
}
