import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {User} from "../models/user.model";
// import {BaseService} from "./base.service";
let baseUrl = 'http://localhost:8080/users';
// let testUrl = 'https://2e05c955-9985-44f9-8f0f-1f6db988d513.mock.pstmn.io/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<User> {
    return this.http.get<User[]>(baseUrl)
      .pipe(
      map(res => new User(res))
    );
  }


  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl +"/register", data);
  }

  login(data:any): Observable<any>{
    return this.http.post(baseUrl + "/login",data)
  }





}
