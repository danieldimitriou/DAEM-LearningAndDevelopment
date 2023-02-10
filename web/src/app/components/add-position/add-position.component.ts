import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Department} from "../../models/department.model";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {DepartmentService} from "../../services/department.service";
import {Position} from "../../models/position.model";

@Component({
  selector: 'app-add-position',
  templateUrl: './add-position.component.html',
  styleUrls: ['./add-position.component.css']
})
export class AddPositionComponent {
  addPositionForm: FormGroup
  loading = false;
  submitted = false;
  positionSubmitted: boolean = false;
  departmentSubmitted: boolean = false;
  returnUrl: string;
  error = '';
  counter = 1;
  departments: Department[] = [];

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: UserService,
              private departmentService: DepartmentService) {
  }

  ngOnInit() {
    this.addPositionForm = this.formBuilder.group({
      positionName: ['', Validators.required],
      positionLevel: ['', Validators.required],
      department: ['', Validators.required]
    })
    this.departmentService.getAllDepartments().subscribe(
      next => {
        // @ts-ignore
        for (let department: Department of next["data"]) {
          this.departments.push(department);
          console.log(department)
        }
      }
    )
  }

  get f() {
    return this.addPositionForm.controls;
  }

  onSubmit() {
    this.error = '';
    this.submitted = false;
    this.positionSubmitted = false;
    this.departmentSubmitted = false;
    if (this.addPositionForm.valid) {
      let position: Position = {
        name: this.f['positionName'].value,
        level: this.f['positionLevel'].value,
      }
      let departmentId = this.f['department'].value;


      this.userService.addPositionToUser(position, this.authenticationService.currentUserValue.id).subscribe(
        next => {
          if (next.status === 201) {
            this.positionSubmitted = true;
            console.log(next);
            this.userService.addDepartmentToUser(departmentId, this.authenticationService.currentUserValue.id).subscribe(
              next => {
                if (next.status === 201) {
                  this.departmentSubmitted = true;
                  this.submitted = true;
                  setTimeout(() => {
                    this.router.navigate(['/profile'])
                  }, 500);
                }
              }, error => {
                this.error = error;
              }
            )
          }
        }, error => {
          this.error = error;
        }
      );

    }

  }
}
