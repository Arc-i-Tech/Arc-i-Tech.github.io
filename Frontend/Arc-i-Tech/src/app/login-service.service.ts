// src/app/login-service.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {}

  loginUser(data: { username: string; password: string }): Observable<any> {
    return this.http.post('http://localhost:8888/api/auth/login', data);
  }
}
