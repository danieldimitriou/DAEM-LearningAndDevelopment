import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../services/user.service";
import {User} from "../../../models/user.model";

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrls: ['./view-employee.component.css']
})
export class ViewEmployeeComponent implements OnInit{

  userId: number;
  user:User;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,) {
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
        this.userId = params['id'];
      });
    console.log(this.userId);

    this.userService.getUserById(this.userId).subscribe(
      next =>{
        this.user = next["data"];
        console.log(this.user);
      }
    )
    }

    goBack(){
      this.router.navigate(['/admin-home'], { relativeTo: this.route });
  }



}
