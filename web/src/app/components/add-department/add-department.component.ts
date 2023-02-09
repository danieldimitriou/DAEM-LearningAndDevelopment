import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {DepartmentService} from "../../services/department.service";
import {Department} from "../../models/department.model";

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})
export class AddDepartmentComponent implements OnInit{

  addDepartmentForm:FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  counter = 1;
  departments:Department[] =[];




  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: UserService,
              private departmentService: DepartmentService) {
  }
  ngOnInit(){
    this.departmentService.getAllDepartments().subscribe(
      next =>{
        // @ts-ignore
        for(let department :Department of next["data"]){
          this.departments.push(department);
        }
      }
    )

  }
}
