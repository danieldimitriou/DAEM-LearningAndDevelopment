import {Authority} from "./authority.model";
import {User} from "./user.model";
import {Course} from "./course.model";

export class Certification {

  name:string;
  certificationAuthority:Authority;
  expirationDate?:Date;
  holders?:User[];
  course?:Course;


  constructor(obj?: any) {
    Object.assign(this, obj);
  }
}
