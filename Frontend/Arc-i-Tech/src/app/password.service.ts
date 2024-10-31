import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './components/register/User';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {
  private url = 'https://localhost:8000/addUser';

  constructor(private http: HttpClient) {}

  userRegister(user: User): Observable<any> {
    return this.http.post<any>(this.url, user);
  }
}