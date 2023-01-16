import { Component } from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  //check if there is a different way to implement this
  user:User ={
    firstName:'',
    lastName:'',
    email:'',
    password : ''
  };
  submitted = false;

  constructor(private userService: UserService) {
  }

  saveUser(){
    const data : User = {
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      email: this.user.email,
      password:this.user.password
    };

    this.userService.create(data).subscribe(
      {
        next: (result) => {
      console.log(result);
      this.submitted = true;
    },
      error: (e) => console.error(e)
  });
  }

}
