import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {User} from "../models/user.model";
import {environment} from "../../environments/environment";
import {Course} from "../models/course.model";
import {Position} from "../models/position.model";
import {Department} from "../models/department.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<User> {
    return this.http.get<User[]>(`${environment.apiUrl}/users`)
      .pipe(
      map(res => new User(res))
    );
  }


  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${environment.apiUrl}/users/${id}`);
  }

  getUserWithCourses(id: number){
    return this.http.get<User>(`${environment.apiUrl}/users/${id}/courses`);
  }



  getUserByEmail(email: string){
    return this.http.get(`${environment.apiUrl}/users`,{params:{email:email}})

  }

  createCourse(course: Course, id: number){
    return this.http.post(`${environment.apiUrl}/users/${id}/addPendingCourse`,course).subscribe(
      next =>{
        console.log(next);
      },
      error => {
        console.log(error);
      }
    );
  }

  changePassword(currentPassword: string, newPassword:string, newPasswordConfirmed:string, id:number){
    return this.http.put(`${environment.apiUrl}/users/${id}/change-password`,
      {"currentPassword":currentPassword,"newPassword":newPassword,"newPasswordConfirmed":newPasswordConfirmed},
      {observe:'response'});
  }

  addCertificationToUser(){

  }

  addDepartmentToUser(department: Department, id: number){
    this.http.put(`${environment.apiUrl}/users/${id}/addDepartment`,department,{observe:'response'});

  }
  addPositionToUser(position: Position, id: number){
    this.http.put(`${environment.apiUrl}/users/${id}/addPosition`,position,{observe:'response'})

  }

  addManagerToUser(){

  }




}
