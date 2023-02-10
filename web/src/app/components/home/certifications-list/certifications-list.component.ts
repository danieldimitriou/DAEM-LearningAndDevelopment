import {Component} from '@angular/core';
import {User} from "../../../models/user.model";
import {UserService} from "../../../services/user.service";
import {AuthenticationService} from "../../../services/authentication.service";
import {Certification} from "../../../models/certification.model";

@Component({
  selector: 'app-certifications-list',
  templateUrl: './certifications-list.component.html',
  styleUrls: ['./certifications-list.component.css']
})
export class CertificationsListComponent {
  certifications: Certification[] = [];
  user: User;

  constructor(private userService: UserService, private authService: AuthenticationService) {
  }

  ngOnInit(){
    this.userService.getUserWithCertifications(this.authService.currentUserValue.id).subscribe(
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
