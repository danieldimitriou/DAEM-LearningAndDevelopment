import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Course} from "../../models/course.model";
import {AuthenticationService} from "../../services/authentication.service";
import {User} from "../../models/user.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
   styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  //array of type Course to hold the courses from user pending courses
  //courseList also acts as the source from where the courses-list.component.ts
  // coursesList arraygets the courses.
  pendingCoursesList: Course[] = [];
  completedCoursesList: Course[] = []

  user: User;

  constructor(private userService: UserService, private authService: AuthenticationService){
  }



  ngOnInit() {
    this.userService.getUserWithCourses(this.authService.currentUserValue["id"]).subscribe(
      next=>{
        console.log(next);
        this.user = {
          firstName:next["data"]["firstName"],
          lastName:next["data"]["lastName"]
        }
        for(let pendingCourse of next["data"]["pendingCourses"]){
          // console.log(pendingCourse);
          this.pendingCoursesList.push(pendingCourse);
        }
        for(let completedCourse of next["data"]["completedCourses"]){
          // console.log(pendingCourse);
          this.completedCoursesList.push(completedCourse);
        }

        console.log(this.pendingCoursesList);
        console.log(this.completedCoursesList);

      }
    )
  // this.userService.completePendingCourse(this.authService.currentUserValue.id, this.)
  }

}
