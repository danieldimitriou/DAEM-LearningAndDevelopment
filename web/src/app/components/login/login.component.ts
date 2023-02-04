import {Component, OnDestroy} from '@angular/core';
import {User} from "../../models/user.model";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnDestroy{


  user:User ={
    email: '',
    password:''
  }

  loginSuccessful = false;

  constructor(private userService: UserService) {
  }

  login(){
      const user: User = {
        email: this.user.email,
        password: this.user.password
      }
      this.userService.login(user).subscribe(
        response => {
          console.log(response["data"]);
          if(response["data"]){
            this.loginSuccessful = true;
          }
        }
      );

    this.loginSuccessful=false;
  }

  ngOnDestroy(): void {
  }}
