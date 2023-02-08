import {User} from "./user.model";

export class Position {
  name : string;

  level : string;
  users? : User[];

  constructor(obj?:any) {
    Object.assign(this,obj);
  }
}
