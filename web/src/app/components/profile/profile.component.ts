import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  user: User;

  constructor(private authService:AuthenticationService,
              private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUserById(this.authService.currentUserValue.id).subscribe(
      next =>{
        console.log(next["data"]);
          this.user=next["data"];
        console.log(this.user);
      }
    )
  }

}
