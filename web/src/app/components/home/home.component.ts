import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user.model";
import {UserService} from "../../services/user.service";
import {Observable} from "rxjs";
import {CourseService} from "../../services/course.service";
import {Course} from "../../models/course.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
   styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  //array of type User to hold the users from userService.getAll
  userList: User[] = [];
  //local variable to save the user we get from getUserByID
  user:User;
  //array of type Course to hold the courses from courseService.getAll
  coursesList: Course[] = [];

  constructor(private userService: UserService,private courseService: CourseService){
  }



  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      // @ts-ignore
      for(let x:User of data["data"]){
        this.userList.push(x);
      }
      // console.log(this.userList)
    });


    //get user by id
    this.userService.getUserById(18).subscribe(data =>{
      this.user = data["data"];
      console.log(this.user);
    });
  }


}
