import {Course} from "./course.model";

export class AreaOfStudy {

  name : string;

  description : string;
  courses : Course[];

  constructor(obj? : any) {
    Object.assign(this,obj);
  }
}
