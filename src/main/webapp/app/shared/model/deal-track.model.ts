export interface IDealTrack {
  id?: number;
  ipLocalisation?: string;
  localisation?: string;
  isAuthentified?: boolean;
  dealId?: number;
}

export class DealTrack implements IDealTrack {
  constructor(
    public id?: number,
    public ipLocalisation?: string,
    public localisation?: string,
    public isAuthentified?: boolean,
    public dealId?: number
  ) {
    this.isAuthentified = this.isAuthentified || false;
  }
}
