import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../services/user.service";
import {User} from "../../../models/user.model";
import {Course} from "../../../models/course.model";
import {AuthenticationService} from "../../../services/authentication.service";
import {Certification} from "../../../models/certification.model";

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrls: ['./view-employee.component.css']
})
export class ViewEmployeeComponent implements OnInit {

  userId: number;
  user:User;
  certifications: Certification[] = [];

  pendingCoursesList: Course[]= [];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.userId = params['id'];
    });
    console.log(this.userId);

    this.userService.getUserById(this.userId).subscribe(
      next => {
        this.user = next["data"];
        this.userService.getUserWithCourses(this.userId).subscribe(
          next => {
            console.log(next);
            this.pendingCoursesList = next["data"]["pendingCourses"];
            console.log(this.pendingCoursesList);

          }
        )
        // console.log(this.user);
      }
    )

    this.userService.getUserWithCertifications(this.authService.currentUserValue.id).subscribe(
      next => {
        // console.log(next);
        for (let certification of next["data"]["certifications"]) {
          // console.log(pendingCourse);
          this.certifications.push(certification);
        }

      })
  }

  goBack() {
    this.router.navigate(['/admin-home'], {relativeTo: this.route});
  }


}
