import { Injectable } from '@angular/core';
import {BaseService} from "./base.service";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {User} from "../models/user.model";
import {Course} from "../models/course.model";

let baseUrl = 'http://localhost:8080/courses';
// let baseUrl = 'http://localhost:4200/assets/courses.json'
@Injectable({
  providedIn: 'root'
})
export class CourseService {


  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Course> {
    return this.http.get<Course[]>(baseUrl)
      .pipe(
      map(res => new Course(res))
    );
  }

  get(id: any): Observable<Course> {
    return this.http.get<Course>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }



}
