import { Moment } from 'moment';
import { IDealHistory } from 'app/shared/model/deal-history.model';
import { IDealTrack } from 'app/shared/model/deal-track.model';
import { IDealReport } from 'app/shared/model/deal-report.model';
import { IDealComment } from 'app/shared/model/deal-comment.model';
import { IDealCategory } from 'app/shared/model/deal-category.model';
import { IDealUser } from 'app/shared/model/deal-user.model';
import { TypeDeal } from 'app/shared/model/enumerations/type-deal.model';
import { TypeCoupon } from 'app/shared/model/enumerations/type-coupon.model';
import { StatutDeal } from 'app/shared/model/enumerations/statut-deal.model';

export interface IDeal {
  id?: number;
  title?: string;
  description?: string;
  type?: TypeDeal;
  url?: string;
  image?: string;
  price?: number;
  priceNormal?: number;
  priceShipping?: number;
  coupon?: string;
  couponType?: TypeCoupon;
  couponValue?: number;
  couponPercentage?: number;
  dateStart?: Moment;
  dateEnd?: Moment;
  datePublication?: Moment;
  views?: number;
  like?: number;
  dislike?: number;
  local?: boolean;
  statut?: StatutDeal;
  isDeleted?: boolean;
  isBlocked?: boolean;
  dealLocationId?: number;
  dealHistories?: IDealHistory[];
  dealTracks?: IDealTrack[];
  dealReports?: IDealReport[];
  dealComments?: IDealComment[];
  assignedToId?: number;
  dealCategories?: IDealCategory[];
  dealUsers?: IDealUser[];
}

export class Deal implements IDeal {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public type?: TypeDeal,
    public url?: string,
    public image?: string,
    public price?: number,
    public priceNormal?: number,
    public priceShipping?: number,
    public coupon?: string,
    public couponType?: TypeCoupon,
    public couponValue?: number,
    public couponPercentage?: number,
    public dateStart?: Moment,
    public dateEnd?: Moment,
    public datePublication?: Moment,
    public views?: number,
    public like?: number,
    public dislike?: number,
    public local?: boolean,
    public statut?: StatutDeal,
    public isDeleted?: boolean,
    public isBlocked?: boolean,
    public dealLocationId?: number,
    public dealHistories?: IDealHistory[],
    public dealTracks?: IDealTrack[],
    public dealReports?: IDealReport[],
    public dealComments?: IDealComment[],
    public assignedToId?: number,
    public dealCategories?: IDealCategory[],
    public dealUsers?: IDealUser[]
  ) {
    this.local = this.local || false;
    this.isDeleted = this.isDeleted || false;
    this.isBlocked = this.isBlocked || false;
  }
}
