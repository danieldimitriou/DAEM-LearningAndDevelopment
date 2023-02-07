import {Component, Input, OnInit} from '@angular/core';
import {Course} from "../../../models/course.model";

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit{

  //takes values from the parent component, home.component.ts courseList
  @Input() coursesList: Course[] =[];

  constructor() {
  }

  ngOnInit(){
  }

  ngOnDestroy(){
  }


}
