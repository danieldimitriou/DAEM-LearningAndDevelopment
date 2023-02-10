import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit{

  users: User[] = [];


  constructor(private authService: AuthenticationService,
              private userService: UserService,
              private cdr: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.cdr.detectChanges();
    this.userService.getAll().subscribe(
      next =>{
        // console.log(next["data"])
        // @ts-ignore
        for(let user:User of next["data"]){

          this.users.push(user);
        }
      }
    )
    console.log(this.users);
  }

}
