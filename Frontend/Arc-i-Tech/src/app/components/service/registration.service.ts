import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../register/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private url = 'http://localhost:8000/addUser'; 
  constructor(private http: HttpClient) {}

  userRegister(user: User): Observable<any> {

    console.warn("User service "+user.fname+"\t" +user.username+ "\t" +user.address+"\t" +user.mob+ "\t" +user.city+ "\t" +user.pcode);
    return this.http.post<any>(this.url, user);
  }
}
