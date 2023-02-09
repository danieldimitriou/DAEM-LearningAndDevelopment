import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {User} from "../models/user.model";
import {environment} from "../../environments/environment";
import {Course} from "../models/course.model";
import {Position} from "../models/position.model";
import {Certification} from "../models/certification.model";

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

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${environment.apiUrl}/users/${userId}`);
  }

  getUserWithCourses(userId: number){
    return this.http.get<User>(`${environment.apiUrl}/users/${userId}/courses`);
  }

  createCourse(course: Course, userId: number){
    return this.http.post(`${environment.apiUrl}/users/${userId}/addPendingCourse`,course,{observe:'response'});
  }

  changePassword(currentPassword: string, newPassword: string, newPasswordConfirmed: string, userId: number) {
    return this.http.put(`${environment.apiUrl}/users/${userId}/change-password`,
      {"currentPassword": currentPassword, "newPassword": newPassword, "newPasswordConfirmed": newPasswordConfirmed},
      {observe: 'response'});
  }

  addCertificationToUser(certification: Certification, userId: number) {
    return this.http.put(`${environment.apiUrl}/users/${userId}/addCertification`, certification, {observe: 'response'});
  }

  addDepartmentToUser(departmentId: number, userId: number) {
    return this.http.put(`${environment.apiUrl}/users/${userId}/setDepartment/${departmentId}`, null, {observe: 'response'});
  }

  addPositionToUser(position: Position, userId: number) {
    return this.http.put(`${environment.apiUrl}/users/${userId}/setPosition`, position, {observe: 'response'})
  }

  addManagerToUser() {
  }

  completePendingCourse(userId: number, pendingCourseId: number,) {
    return this.http.post(`${environment.apiUrl}/users/${userId}/completePendingCourse/${pendingCourseId}`, {});
  }
}
