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
import {ChangePasswordComponent} from "./components/change-password/change-password.component";
import {ViewEmployeeComponent} from "./components/admin-home/view-employee/view-employee.component";
import {AdminAuthGuard} from "./helpers/admin-auth.guard";
import {ChangeEmailComponent} from "./components/change-email/change-email.component";
import {CertificationsListComponent} from "./components/home/certifications-list/certifications-list.component";

const routes: Routes = [
  {path:'',canActivate:[AuthGuard],component:HomeComponent},
  {path:'home',canActivate:[AuthGuard],component:HomeComponent},
  {path:'profile',canActivate:[AuthGuard],component:ProfileComponent},
  {path: 'users/addManager',canActivate:[AuthGuard],component:AddManagerComponent},
  {path: 'users/addPosition',canActivate:[AuthGuard],component:AddPositionComponent},
  {path: 'home/certifications',canActivate:[AuthGuard],component:CertificationsListComponent},
  {path: 'users/changePassword',canActivate:[AuthGuard],component:ChangePasswordComponent},
  {path:'signup',component:SignupComponent},
  {path:'login',component:LoginComponent},
  {path:'home/addCourse',canActivate:[AuthGuard], component:AddCourseComponent},
  {path:'users/addCertification',canActivate:[AuthGuard], component:AddCertificationsComponent},
  {path:'users/changeEmail',canActivate:[AuthGuard],component:ChangeEmailComponent},
  {path:'admin-home',canActivate:[AdminAuthGuard],component:AdminHomeComponent,children:[]},
  {path:'admin-home/viewEmployee/:id',canActivate:[AdminAuthGuard],component:ViewEmployeeComponent},
  {path: '**', component:NotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
