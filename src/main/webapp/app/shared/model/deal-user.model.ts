import { Moment } from 'moment';
import { IDeal } from 'app/shared/model/deal.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IDealUser {
  id?: number;
  gender?: Gender;
  phone?: string;
  address?: string;
  city?: string;
  birthDay?: Moment;
  comment?: boolean;
  notification?: boolean;
  reporting?: boolean;
  emailNotification?: boolean;
  message?: boolean;
  userId?: number;
  dealSaveds?: IDeal[];
}

export class DealUser implements IDealUser {
  constructor(
    public id?: number,
    public gender?: Gender,
    public phone?: string,
    public address?: string,
    public city?: string,
    public birthDay?: Moment,
    public comment?: boolean,
    public notification?: boolean,
    public reporting?: boolean,
    public emailNotification?: boolean,
    public message?: boolean,
    public userId?: number,
    public dealSaveds?: IDeal[]
  ) {
    this.comment = this.comment || false;
    this.notification = this.notification || false;
    this.reporting = this.reporting || false;
    this.emailNotification = this.emailNotification || false;
    this.message = this.message || false;
  }
}
