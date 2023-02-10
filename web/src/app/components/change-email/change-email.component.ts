import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-change-email',
  templateUrl: './change-email.component.html',
  styleUrls: ['./change-email.component.css']
})
export class ChangeEmailComponent implements OnInit{
  updateEmailForm:FormGroup;
  submitted: boolean;
  error:string;

  constructor(private authService: AuthenticationService,
              private userService: UserService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router ) {
  }
  ngOnInit() {
    this.updateEmailForm =   this.formBuilder.group({
      newEmail:['',Validators.email],
      newEmailConfirmed:['',Validators.email],
    },)
  }

  get f() { return this.updateEmailForm.controls; }

  onSubmit(){
    this.error='';
    this.submitted = false;
    let email = this.f['newEmail'].value;
    this.userService.updateEmail(email,this.authService.currentUserValue.id).subscribe(
      next =>{
        if(next.status === 201){
          this.submitted = true;
          setTimeout(() => {
            this.router.navigate(['/profile'])
          }, 500);
          console.log("success");

        }
      },error =>{
        this.error = error;
      }
    );
  }
}
