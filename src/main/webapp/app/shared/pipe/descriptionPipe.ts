import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'description',
})
export class DescriptionPipe implements PipeTransform {
  transform(value: any, args?: any): any {
    if (args === 'all' || value.length < 420) {
      return value;
    } else {
      return value.slice(1, 420) + ' ...';
    }
  }
}
