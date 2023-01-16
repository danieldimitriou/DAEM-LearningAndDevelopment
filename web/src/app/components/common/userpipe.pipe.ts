import { Pipe, PipeTransform } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../models/user.model";

@Pipe({
  name: 'userpipe'
})
export class UserpipePipe  implements PipeTransform {
  transform(value: any, args: any[] = null): any {
    return Object.keys(value).map(key => value[key]);
  }
}


