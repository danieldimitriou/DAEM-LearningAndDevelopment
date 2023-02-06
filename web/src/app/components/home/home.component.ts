import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Course} from "../../models/course.model";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
   styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  //array of type User to hold the users from userService.getAll
  courseList: Course[] = [];
  //array of type Course to hold the courses from courseService.getAll
  coursesList: Course[] = [];

  constructor(private userService: UserService, private authService: AuthenticationService){
  }



  ngOnInit() {
    // console.log(this.authService.currentUserValue)
    // this.userService.getUserWithCourses(this.authService.currentUserValue["id"]).subscribe(
    //   next =>{
    //     console.log("----------")
    //     console.log(next);
    //   }
    // )
  }


}
