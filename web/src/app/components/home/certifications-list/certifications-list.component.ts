import {Component} from '@angular/core';
import {User} from "../../../models/user.model";
import {UserService} from "../../../services/user.service";
import {AuthenticationService} from "../../../services/authentication.service";
import {Certification} from "../../../models/certification.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-certifications-list',
  templateUrl: './certifications-list.component.html',
  styleUrls: ['./certifications-list.component.css']
})
export class CertificationsListComponent {
  userId: number;
  certifications: Certification[] = [];
  user: User;
  isAdminView: boolean;

  constructor(private userService: UserService, private authService: AuthenticationService, private route:ActivatedRoute) {
  }

  ngOnInit(){
    this.route.params.subscribe(
      next =>{
        if(+next['id']){
          this.userId = +next['id'];
        }
      }
    );
    console.log(this.userId);

    if(this.userId === undefined){
      this.userId = this.authService.currentUserValue.id;
      console.log(this.userId);
      this.isAdminView = false;
    }else{
      console.log(this.userId);
      this.isAdminView = true;
    }

    this.userService.getUserWithCertifications(this.userId).subscribe(
      next => {
        // console.log(next);
        this.user = {
          firstName: next["data"]["firstName"],
          lastName: next["data"]["lastName"]
        }
        for (let certification of next["data"]["certifications"]) {
          // console.log(pendingCourse);
          this.certifications.push(certification);
        }
      })
  }
}
