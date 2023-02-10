import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';

import {User} from "../models/user.model";
import {environment} from "../../environments/environment";
import {Role} from "../models/role.model";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  readonly currentUserSubject: BehaviorSubject<User>;
  private currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
    // console.log(this.currentUserSubject)
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  public get isUserAdmin(){
    return this.currentUserValue.role;
  }


  login(email: string, password: string): Observable<User> {
    return this.http.post<HttpResponse<any>>(`${environment.apiUrl}/users/login`, {
      email,
      password
    }, { observe: 'response' })
      .pipe(
        map(res => {
          if (res.status == 200) {
            let user: User = {
              id: res.body["data"]["id"],
              authData: window.btoa(email + ':' + password),
              role: res.body["data"]["role"]
            }
            localStorage.setItem('currentUser', JSON.stringify(user));
            this.currentUserSubject.next(user);
            return user;
          } else {
            throw new Error('Login failed');
          }
        }),
        catchError(e => {
          console.log(e);
          return throwError(e);
        })
      );
  }


  register(data: User){
    return this.http.post(`${environment.apiUrl}/users/register`, data,{observe:'response'})
      .pipe(
        map(res => {
          console.log(res.status)
          if (res.status == 200) {
            let role:Role;
            if(res.body["data"]["role"] === Role.Admin){
              role = Role.Admin
            }else{
              role = Role.User
            }
            let user: User = {
              id: res.body["data"]["id"],
              authData: window.btoa(data.email + ':' + data.password),
              role: role
            }
            localStorage.setItem('currentUser', JSON.stringify(user));
            this.currentUserSubject.next(user);
            return user;
          } else {
            throw new Error('Signup failed');
          }
        }),
        catchError(e => {
          console.log(e);
          return throwError(e);
        })
      );
  }
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
