import {Component, OnInit} from '@angular/core';
import {Course} from "../../../models/course.model";
import {TypeOfCourse} from "../../../models/type-of-course.model";
import {Subscription} from "rxjs";
import {AuthenticationService} from "../../../services/authentication.service";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit{

  subscriptions: Subscription[] = [];
  coursesList: Course[] =[];
  courseType : TypeOfCourse[] =[];

  constructor(private userService:UserService, private authService: AuthenticationService) {
  }

  ngOnInit(){
    this.userService.getUserWithCourses(this.authService.currentUserValue["id"]).subscribe(
      next =>{
        console.log(next["data"]["pendingCourses"]);
        // @ts-ignore
        for(let x : Course of next["data"]["pendingCourses"]){
            this.coursesList.push(x);
        }
        console.log(this.coursesList);

      }
    )


    // getAll().subscribe(
    //   (data) => {
    //     // @ts-ignore
    //     for(let x:Course of data["data"]){
    //       // console.log(x);
    //       this.coursesList.push(x);
    //       this.courseType.push(x.type);
    //     }
    //   }
    // )
    // console.log(this.coursesList);
    // console.log(this.courseType);

    // console.log(this.coursesList);
  }

  ngOnDestroy(){
    this.subscriptions.forEach((subscription) =>subscription.unsubscribe());
    console.log(this.subscriptions);
  }

  // checkCourseType(course : Course){
  //   if()
  // }
}
