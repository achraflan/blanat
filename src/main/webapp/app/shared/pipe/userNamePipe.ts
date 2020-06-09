import { Pipe, PipeTransform } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { DealUserService } from '../../entities/deal-user/deal-user.service';
import { DealUser } from 'app/shared/model/deal-user.model';

@Pipe({
  name: 'userPipe',
})
export class UserNamePipe implements PipeTransform {
  constructor(protected dealUserService: DealUserService) {}
  transform(value: any, args?: any): any {
    if (value) {
      return this.dealUserService.find(value).subscribe((dealUser: HttpResponse<DealUser>) => {
        if (args === 'name') return 'name';
        return dealUser.body?.userId;
      });
    }
  }
}
