// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { userauth } from '../Classes/userauth';
import { observableToBeFn } from 'rxjs/internal/testing/TestScheduler';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  private apiUrl = 'https://localhost:8000/login'; // Demo API endpoint

  constructor(private http: HttpClient) {}

  userAuthService(userAuth:userauth):Observable<any>
    {
      return this.http.post<any>(this.apiUrl,userAuth);

  }
}
