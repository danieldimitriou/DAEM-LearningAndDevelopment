import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {SignupComponent} from "./components/signup/signup.component";
import {LoginComponent} from "./components/login/login.component";
import {AddCourseComponent} from "./components/add-course/add-course.component";
import {AddCertificationsComponent} from "./components/add-certifications/add-certifications.component";
import {AdminHomeComponent} from "./components/admin-home/admin-home.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {AuthGuard} from "./helpers";
import {ProfileComponent} from "./components/profile/profile.component";
import {AddManagerComponent} from "./components/add-manager/add-manager.component";
import {AddPositionComponent} from "./components/add-position/add-position.component";
import {ResetPasswordComponent} from "./components/reset-password/reset-password.component";
import {AddDepartmentComponent} from "./add-department/add-department.component";

const routes: Routes = [
  {path:'',canActivate:[AuthGuard],component:HomeComponent},
  {path:'home',canActivate:[AuthGuard],component:HomeComponent},
  {path:'profile',canActivate:[AuthGuard],component:ProfileComponent},
  {path: 'users/addManager',canActivate:[AuthGuard],component:AddManagerComponent},
  {path: 'users/addPosition',canActivate:[AuthGuard],component:AddPositionComponent},
  {path: 'users/addDepartment',canActivate:[AuthGuard],component:AddDepartmentComponent},
  {path: 'users/resetPassword',canActivate:[AuthGuard],component:ResetPasswordComponent},
  {path:'signup',component:SignupComponent},
  {path:'login',component:LoginComponent},
  {path:'home/addCourse',canActivate:[AuthGuard], component:AddCourseComponent},
  {path:'addCertifications',canActivate:[AuthGuard], component:AddCertificationsComponent},
  {path:'admin-home',canActivate:[AuthGuard],component:AdminHomeComponent},
  {path: '**', component:NotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
