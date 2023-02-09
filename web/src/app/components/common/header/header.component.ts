import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../services/authentication.service";
import {UserService} from "../../../services/user.service";
import {Role} from "../../../models/role.model";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  //acts as a flag to check whether the user is logged in, and change the buttons accordingly
  isLoggedIn: boolean;
  isAdmin:boolean;
  userId:number;
  constructor(private authService: AuthenticationService,private userService:UserService) {
  }
  ngOnInit() {
    if(this.authService.currentUserValue){
      this.isLoggedIn = true;
      this.userId = this.authService.currentUserValue.id;
      this.userService.getUserById(this.authService.currentUserValue.id).subscribe(
        next =>{
          if(next && next["data"] && next["data"]["role"] === Role.Admin){
            console.log(next);
            this.isAdmin = true;
          }
        }
      )
    }

  }

  logout(){
    this.isAdmin = false;
    this.isLoggedIn = false;
    this.authService.logout();
  }
}
