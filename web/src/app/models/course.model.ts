import {TypeOfCourse} from "./type-of-course.model";
import {AreaOfStudy} from "./area-of-study.model";
import {Certification} from "./certification.model";
import {User} from "./user.model";

export class Course {

  name: string;

  type: TypeOfCourse;

  areasOfStudy: AreaOfStudy[];

  certification: Certification;

  usersPending?: User[];

  usersCompleted?: User[];

  constructor(obj?: any) {
      Object.assign(this, obj);
  }

}
