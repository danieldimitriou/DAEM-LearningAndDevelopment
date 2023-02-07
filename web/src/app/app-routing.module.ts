import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {SignupComponent} from "./components/signup/signup.component";
import {LoginComponent} from "./components/login/login.component";
import {AddExperienceComponent} from "./components/add-experience/add-experience.component";
import {AddCertificationsComponent} from "./components/add-certifications/add-certifications.component";
import {AdminHomeComponent} from "./components/admin-home/admin-home.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {AuthGuard} from "./helpers";

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'home',canActivate:[AuthGuard],component:HomeComponent},
  {path:'signup',component:SignupComponent},
  {path:'login',component:LoginComponent},
  {path:'addExperience',canActivate:[AuthGuard], component:AddExperienceComponent},
  {path:'addCertifications',canActivate:[AuthGuard], component:AddCertificationsComponent},
  {path:'admin-home',canActivate:[AuthGuard],component:AdminHomeComponent},
  {path: '**', component:NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
