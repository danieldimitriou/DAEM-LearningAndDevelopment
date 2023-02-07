import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../models/user.model";
import {AuthenticationService} from "../../services/authentication.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnDestroy, OnInit {

  //check if there is a different way to implement this
  user:User ={
    firstName:'',
    lastName:'',
    email:'',
    password : ''
  };

  signupForm: FormGroup;
  loading=false;
  submitted = false;
  returnUrl: string;
  error= '';

  constructor(private authService: AuthenticationService,
              private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) {
    if(this.authService.currentUserValue){
      this.router.navigate(['/']);
    }
  }
  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      firstName: ['',Validators.required],
      lastName: ['',Validators.required],
      email: ['', Validators.required],
      password:['', Validators.required]
    })
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

  }

  // convenience getter for easy access to form fields
  get f() { return this.signupForm.controls; }


  onSubmit(){
    let data : User = {
      firstName: this.f['firstName'].value,
      lastName: this.f['lastName'].value,
      email: this.f['email'].value,
      password:this.f['password'].value
    };

    this.authService.register(data).subscribe(
      {
        next: (result) => {
          console.log(result);
          this.submitted = true;
          this.router.navigate([this.returnUrl])
        },
        error: (e) => console.error(e)
      });
  }

  ngOnDestroy(): void {
  }



}
