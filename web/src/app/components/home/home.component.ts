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
  courseList: Course[] = [];

  user: User;

  constructor(private userService: UserService, private authService: AuthenticationService){
  }



  ngOnInit() {
    this.userService.getUserWithCourses(this.authService.currentUserValue["id"]).subscribe(
      next=>{
        this.user = {
          firstName:next["data"]["firstName"],
          lastName:next["data"]["lastName"]
        }
        for(let pendingCourse of next["data"]["pendingCourses"]){
          // console.log(pendingCourse);
          this.courseList.push(pendingCourse);
        }
      }
    )}
}
