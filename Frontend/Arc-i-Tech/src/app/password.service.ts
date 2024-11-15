import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { catchError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {
  private url = 'https://localhost:8000/addUser';

  constructor(private http: HttpClient) {}


    userPassword(user:User): Observable<any> {
      return this.http.post<any>(this.url, user).pipe(
        catchError((error) => {
          console.error('Error in API call:', error);
          return this.http.post<any>(this.url, user);
          console.log(user);        
        })
      );
    }
    
  }
