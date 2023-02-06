import {Position} from "./position.model";
import {Department} from "./department.model";
import {Certification} from "./certification.model";
import {Course} from "./course.model";

export class User {
  firstName?: string;
  lastName?: string;
  email: string;
  password: string;
  position?: Position;
  manager?:User;
  department?:Department;
  certifications?:Certification[];
  pendingCourses?:Course[];
  completedCourses?:Course[];
  authData?: string;

  // constructor(firstName: string, lastName: string, email:string, password:string) {
  //   this.firstName=firstName;
  //   this.lastName=lastName;
  //   this.email=email;
  //   this.password=password;
  // }

  constructor(obj?:any) {
    Object.assign(this,obj);
  }


}
