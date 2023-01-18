import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Course} from "../models/course.model";

let baseUrl = 'http://localhost:8080/courses';
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
