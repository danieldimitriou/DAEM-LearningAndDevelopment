import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {Course} from "../../models/course.model";
import {TypeOfInstitution} from "../../models/type-of-institution.model";
import {Authority} from "../../models/authority.model";
import {Certification} from "../../models/certification.model";
import {AreaOfStudy} from "../../models/area-of-study.model";
import {TypeOfCourse} from "../../models/type-of-course.model";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit{

  addCourseForm: FormGroup;
  submitted = false;
  returnUrl: string;
  error : string;
  counter = 1;

  areasOfStudy: FormGroup[];


  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: UserService) {

  }
  ngOnInit(){
      this.addCourseForm = this.formBuilder.group({
      courseName: ['', Validators.required],
      typeOfCourseName: ['', Validators.required],
      typeOfCourseDescription: ['', Validators.required],
      areasOfStudy: this.formBuilder.array([
        this.formBuilder.group({
          name: ['', Validators.required],
          description: ['', Validators.required]
        })
      ]),
      certificationName:['', Validators.required],
      certificationAuthorityName:['',Validators.required],
      awardingBodyDescription:['',Validators.required]
    });



    // get return url from route parameters or default to '/'
    // if a user enters /admin-home but needs login, they get redirected
    // to /admin-home after authentication, or if they didn't give any link,
    // they get redirected to home
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get f() { return this.addCourseForm.controls; }

  addAreaOfStudy() {
    const control = <FormArray>this.addCourseForm.controls['areasOfStudy'];
    control.push(
      this.formBuilder.group({
        'name': [''],
        'description': ['']
      })
    );
    this.counter++;
  }

  removeAreaOfStudy(){
    const control = <FormArray>this.addCourseForm.controls['areasOfStudy'];
    this.counter--;
    control.removeAt(this.counter);
  }


  onSubmit(){
    let awardingBody:TypeOfInstitution = {
      description:this.f['awardingBodyDescription'].value
    }

    let certificationAuthority:Authority = {
      name:this.f['certificationAuthorityName'].value,
      awardingBody:awardingBody
    }

    let certification: Certification = {
      name:this.f['certificationName'].value,
      certificationAuthority:certificationAuthority
    }

    //for each areaOfStudy the user inputs, add them to a list of areasOfStudy
    let areasOfStudy: AreaOfStudy[] =[];
    for(let areaOfStudy of this.f['areasOfStudy'].value){
        areasOfStudy.push(areaOfStudy);
    }
    console.log(areasOfStudy)

    let typeOfCourse: TypeOfCourse = {
      name: this.f['typeOfCourseName'].value,
      description:this.f['typeOfCourseDescription'].value
    }

    let course :Course = {
      name:this.f['courseName'].value,
      type: typeOfCourse,
      areasOfStudy:areasOfStudy,
      certification:certification,
    }

    console.log(JSON.stringify({ data: course}, null, 4));
    this.userService.createCourse(course,this.authenticationService.currentUserValue.id).subscribe(
      next =>{
        if(next.status === 201){
          this.submitted = true;
          setTimeout(() => {
            this.router.navigate(['/'])
          },1500)
        }
      },error=>{
        this.error = error;
        this.submitted = false;
      }
    );
    this.addCourseForm.reset();
  }



}
