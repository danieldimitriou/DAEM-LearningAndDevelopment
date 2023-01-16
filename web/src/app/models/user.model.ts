import {Position} from "./position.model";
import {Department} from "./department.model";
import {Certification} from "./certification.model";
import {Course} from "./course.model";

export class User {
  firstName?: string;
  lastName?: string;
  email?: string;
  password?: string;
  position?: Position;
  manager?:User;
  department?:Department;
  certifications?:Certification[];
  pendingCourses?:Course[];
  completedCourses?:Course[];

  constructor(obj?: any) {
    Object.assign(this, obj);
  }


}
