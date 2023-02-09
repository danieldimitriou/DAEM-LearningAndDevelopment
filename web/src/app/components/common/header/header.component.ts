import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../services/authentication.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  //acts as a flag to check whether the user is logged in, and change the buttons accordingly
  isLoggedIn: boolean;
  constructor(private authService: AuthenticationService) {
  }
  ngOnInit() {
    if(this.authService.currentUserValue){
      this.isLoggedIn = true;
    }
  }

  logout(){
    this.isLoggedIn = false;
    this.authService.logout();
  }
}
