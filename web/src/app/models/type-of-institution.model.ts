import {Authority} from "./authority.model";

export class TypeOfInstitution {

  description : string;

  authorities?: Authority[];

  constructor(obj? : any) {
    Object.assign(this,obj);
  }
}
