import {Component, OnInit} from '@angular/core';
import {Course} from "../../../models/course.model";
import {CourseService} from "../../../services/course.service";
import {TypeOfCourse} from "../../../models/type-of-course.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit{

  subscriptions: Subscription[] = [];
  coursesList: Course[] =[];
  courseType : TypeOfCourse[] =[];

  constructor(private courseService: CourseService) {
  }

  ngOnInit(){
    this.courseService.getAll().subscribe(
      (data) => {
        // @ts-ignore
        for(let x:Course of data["data"]){
          console.log(x);
          this.coursesList.push(x);
          this.courseType.push(x.type);
        }
      }
    )
    console.log(this.coursesList);
    console.log(this.courseType);

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
