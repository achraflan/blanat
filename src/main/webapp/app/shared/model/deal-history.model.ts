import { Moment } from 'moment';

export interface IDealHistory {
  id?: number;
  attributName?: string;
  attributLastValue?: string;
  dateModification?: Moment;
  dealId?: number;
}

export class DealHistory implements IDealHistory {
  constructor(
    public id?: number,
    public attributName?: string,
    public attributLastValue?: string,
    public dateModification?: Moment,
    public dealId?: number
  ) {}
}
