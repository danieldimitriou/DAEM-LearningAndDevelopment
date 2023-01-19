export class CourseWithDates {

  startDate : Date;
  endDate : Date;

  constructor(obj? : any) {
    Object.assign(this,obj);
  }
}
