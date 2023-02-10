import {Component, Input, OnInit} from '@angular/core';
import {Course} from "../../../models/course.model";
import {UserService} from "../../../services/user.service";
import {AuthenticationService} from "../../../services/authentication.service";
import {User} from "../../../models/user.model";
import { ChangeDetectorRef } from '@angular/core';
@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit{

  //takes values from the parent component, home.component.ts courseList
  pendingCoursesList: Course[] = [];
  completedCoursesList: Course[] = [];
  user: User;

  @Input() userId: number;

  constructor(private userService: UserService, private authService: AuthenticationService,private cdr: ChangeDetectorRef) {

  }

  markPendingCourseAsComplete(courseId: number, userId: number){
    this.userService. completePendingCourse(userId, courseId).subscribe(
        next => {
          // console.log(next);
          const completedCourse = this.pendingCoursesList.find(course => course.id === courseId);
          this.pendingCoursesList = this.pendingCoursesList.filter(course => course.id !== courseId);
          this.completedCoursesList.push(completedCourse);
          // Trigger Angular's change detection
          this.cdr.detectChanges();
        }
      )
  }

  getUserWithCourses(userId: number){
    this.userService.getUserWithCourses(userId).subscribe(
      next => {
        // console.log(next);
        this.user = {
          firstName: next["data"]["firstName"],
          lastName: next["data"]["lastName"]
        }
        for (let pendingCourse of next["data"]["pendingCourses"]) {
          // console.log(pendingCourse);
          this.pendingCoursesList.push(pendingCourse);
        }
        for (let completedCourse of next["data"]["completedCourses"]) {
          // console.log(pendingCourse);
          this.completedCoursesList.push(completedCourse);
        }
        // console.log(this.pendingCoursesList);
        // console.log(this.completedCoursesList);
      })
  }

  ngOnInit(){
    if(this.userId === undefined){
      this.userId = this.authService.currentUserValue.id;
      this.getUserWithCourses(this.userId);
    }else{
      console.log(this.userId);
      this.getUserWithCourses(this.userId);
    }

    // this.userService.completePendingCourse(this.authService.currentUserValue.id, this.)
  }
}
