import {Component, OnDestroy} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnDestroy {

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

  createUser(){
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

  ngOnDestroy(): void {
  }

}
