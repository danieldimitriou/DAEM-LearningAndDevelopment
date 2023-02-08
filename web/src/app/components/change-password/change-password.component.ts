import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-reset-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit{

  changePasswordForm: FormGroup;

  passwordChangeSuccess: boolean;

  error:string;


  constructor(private authService: AuthenticationService,
              private userService: UserService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router ) {

  }

  ngOnInit() {
    this.changePasswordForm =   this.formBuilder.group({
      currentPassword:['',Validators.required],
      newPassword:['',Validators.required],
      newPasswordConfirmed:['',Validators.required]
    })
  }

  get f() { return this.changePasswordForm.controls; }

  onSubmit(){
    this.error='';
    // let passwordChange = {
    //   "currentPassword":this.f['currentPassword'].value,
    //   "newPassword":this.f['newPassword'].value,
    //   "newPasswordConfirmed":this.f['newPasswordConfirmed'].value,
    // }
    // console.log(passwordChange);
    this.userService.changePassword
    (this.f['currentPassword'].value,
      this.f['newPassword'].value,
      this.f['newPasswordConfirmed'].value,
      this.authService.currentUserValue.id).subscribe(
        next =>{
            if(next.status === 200){
              this.passwordChangeSuccess = true;
              // this.router.navigate(['/']);
            }
        },error =>{
          this.error=error;
          console.error(error);
      }
    );
  }
}
