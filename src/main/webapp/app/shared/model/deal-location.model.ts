export interface IDealLocation {
  id?: number;
  city?: string;
  country?: string;
}

export class DealLocation implements IDealLocation {
  constructor(public id?: number, public city?: string, public country?: string) {}
}
