import {Component, OnInit} from '@angular/core';
import {Course} from "../../../../models/course.model";
import {CourseService} from "../../../../services/course.service";

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit{

  coursesList: Course[] = [];

  constructor(private courseService: CourseService) {
  }

  ngOnInit(){
    this.courseService.getAll().subscribe(
      (data) => {
        // @ts-ignore
        for(let x:Course of data["data"]){
          this.coursesList.push(x);
        }
      }
    );
    console.log(this.coursesList);
  }

}
