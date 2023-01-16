import { Component } from '@angular/core';
import {User} from "../../models/user.model";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  user:User ={
    email: '',
    password:''
  }

  submitted = false;

  constructor(private userService: UserService) {
  }


}
