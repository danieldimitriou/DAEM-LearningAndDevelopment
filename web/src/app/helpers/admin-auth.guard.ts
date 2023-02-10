import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {AuthenticationService} from "../services/authentication.service";
import {UserService} from "../services/user.service";
import {Role} from "../models/role.model";
import {first} from "rxjs";
import {map} from "rxjs/operators";


@Injectable({ providedIn: 'root' })
export class AdminAuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;

    if (!currentUser) {
      // not logged in so redirect to login page with the return url
      this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
      return false;
    }

    return this.userService.getUserById(this.authenticationService.currentUserValue.id)
      .pipe(
        map(next => {
          if (next["data"]["role"] === Role.Admin) {
            return true;
          } else {
            this.router.navigate(['/home']);
            return false;
          }
        }),
        first()
      );
  }
}
