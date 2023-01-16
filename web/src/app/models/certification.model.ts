import {Authority} from "./authority.model";
import {User} from "./user.model";
import {Course} from "./course.model";

export class Certification {

  name:string;
  certificationAuthority:Authority;
  expirationDate:Date;
  holders:User[];
  course:Course;
  // constructor(name:string, certificationAuthority:Authority,expirationDate:Date, holders:User[],course:Course) {
  //   this.name = name;
  //   this.certificationAuthority = certificationAuthority;
  //   this.expirationDate = expirationDate;
  //   this.holders = holders;
  //   this.course = course;
  // }

  constructor(obj?: any) {
    Object.assign(this, obj);
  }
}
