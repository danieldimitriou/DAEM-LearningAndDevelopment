import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {Certification} from "../../models/certification.model";
import {TypeOfInstitution} from "../../models/type-of-institution.model";
import {Authority} from "../../models/authority.model";

@Component({
  selector: 'app-add-certifications',
  templateUrl: './add-certifications.component.html',
  styleUrls: ['./add-certifications.component.css']
})
export class AddCertificationsComponent implements OnInit{
  addCertificationForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: UserService) {

  }

  ngOnInit(): void {
    this.addCertificationForm = this.formBuilder.group({
      certificationName:['', Validators.required],
      certificationAuthorityName:['',Validators.required],
      awardingBodyDescription:['',Validators.required]
    })
  }

  get f() { return this.addCertificationForm.controls; }

  onSubmit() {
    this.submitted = false;
    this.error = '';
    if (this.addCertificationForm.valid) {
      let awardingBody: TypeOfInstitution = {
        description: this.f['awardingBodyDescription'].value
      }

      let certificationAuthority: Authority = {
        name: this.f['certificationAuthorityName'].value,
        awardingBody: awardingBody
      }

      let certification: Certification = {
        name: this.f['certificationName'].value,
        certificationAuthority: certificationAuthority
      }
      this.userService.addCertificationToUser(certification, this.authenticationService.currentUserValue.id).subscribe(
        next => {
          if (next.status === 201) {
            this.submitted = true;
            setTimeout(() => {
              this.router.navigate(['/profile']);
            }, 500);
            this.addCertificationForm.reset();
          }
          console.log(next);
        }, error => {
          this.error = error;
          this.submitted = false;
        }
      );

    } else {
      this.submitted = false;
      this.error = "Invalid form fields.";
    }
  }
}
