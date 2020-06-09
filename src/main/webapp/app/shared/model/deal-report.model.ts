import { Moment } from 'moment';

export interface IDealReport {
  id?: number;
  reason?: string;
  dateReport?: Moment;
  dateClose?: Moment;
  isValid?: boolean;
  assignedToId?: number;
  dealId?: number;
}

export class DealReport implements IDealReport {
  constructor(
    public id?: number,
    public reason?: string,
    public dateReport?: Moment,
    public dateClose?: Moment,
    public isValid?: boolean,
    public assignedToId?: number,
    public dealId?: number
  ) {
    this.isValid = this.isValid || false;
  }
}
