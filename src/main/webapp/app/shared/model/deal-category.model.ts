import { IDeal } from 'app/shared/model/deal.model';

export interface IDealCategory {
  id?: number;
  name?: string;
  description?: string;
  isParent?: boolean;
  categoryName?: string;
  categoryId?: number;
  deals?: IDeal[];
}

export class DealCategory implements IDealCategory {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public isParent?: boolean,
    public categoryName?: string,
    public categoryId?: number,
    public deals?: IDeal[]
  ) {
    this.isParent = this.isParent || false;
  }
}
