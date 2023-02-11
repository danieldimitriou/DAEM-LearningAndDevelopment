import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-manager',
  templateUrl: './add-manager.component.html',
  styleUrls: ['./add-manager.component.css']
})
export class AddManagerComponent implements OnInit{
  updateManagerForm:FormGroup;
  submitted: boolean;
  error:string;

  constructor(private authService: AuthenticationService,
              private userService: UserService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router ) {
  }

  ngOnInit(){
    this.updateManagerForm = this.formBuilder.group({
      managerEmail:['',Validators.email]
    })
  }

  get f() {
    return this.updateManagerForm.controls;
  }
  onSubmit(){
    this.error = '';
    this.submitted = false;

    this.userService.addManagerToUser(this.f['managerEmail'].value,this.authService.currentUserValue.id).subscribe(
      next =>{
        if(next.status === 201){
            this.submitted = true;
          setTimeout(() => {
            this.router.navigate(['/profile'])
          }, 500);
        }else{
          this.submitted=false;
          this.error = 'error';
        }
      },error =>{
        this.error = error;
      }
    )

  }
}
