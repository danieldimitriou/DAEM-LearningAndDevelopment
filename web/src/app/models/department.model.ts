import {User} from "./user.model";

export class Department {

  name:string;
  headOfDepartment: User;
  members:User[];

  constructor(obj?: any) {
    Object.assign(this, obj);
  }
}
