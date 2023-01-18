import {TypeOfInstitution} from "./type-of-institution.model";
import {Certification} from "./certification.model";

export class Authority {

  name : string;

  awardingBody : TypeOfInstitution;
  certifications : Certification[];

  constructor(obj? : any) {
    Object.assign(this,obj);

  }

}
