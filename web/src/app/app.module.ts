import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {SignupComponent} from './components/signup/signup.component';
import {AdminHomeComponent} from './components/admin-home/admin-home.component';
import {HeaderComponent} from './components/common/header/header.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {CoursesListComponent} from './components/home/courses-list/courses-list.component';
import {AddCourseComponent} from './components/add-course/add-course.component';
import {AddCertificationsComponent} from './components/add-certifications/add-certifications.component';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {BasicAuthInterceptor, ErrorInterceptor} from "./helpers";
import {ProfileComponent} from './components/profile/profile.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import { AddPositionComponent } from './components/add-position/add-position.component';
import { AddManagerComponent } from './components/add-manager/add-manager.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { ViewEmployeeComponent } from './components/admin-home/view-employee/view-employee.component';
import { ChangeEmailComponent } from './components/change-email/change-email.component';
import { CertificationsListComponent } from './components/home/certifications-list/certifications-list.component';
import {MatButtonModule} from '@angular/material/button';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    AdminHomeComponent,
    HeaderComponent,
    CoursesListComponent,
    AddCourseComponent,
    AddCertificationsComponent,
    NotFoundComponent,
    ProfileComponent,
    AddPositionComponent,
    AddManagerComponent,
    ChangePasswordComponent,
    ViewEmployeeComponent,
    ChangeEmailComponent,
    CertificationsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatButtonModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
