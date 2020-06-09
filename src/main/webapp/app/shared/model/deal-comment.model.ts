import { Moment } from 'moment';
import { ICommentHistory } from 'app/shared/model/comment-history.model';

export interface IDealComment {
  id?: number;
  comment?: string;
  dateComment?: Moment;
  isActive?: boolean;
  isDeleted?: boolean;
  like?: number;
  dislike?: number;
  dateLastModification?: Moment;
  dealHistories?: ICommentHistory[];
  assignedToId?: number;
  parentId?: number;
  dealId?: number;
}

export class DealComment implements IDealComment {
  constructor(
    public id?: number,
    public comment?: string,
    public dateComment?: Moment,
    public isActive?: boolean,
    public isDeleted?: boolean,
    public like?: number,
    public dislike?: number,
    public dateLastModification?: Moment,
    public dealHistories?: ICommentHistory[],
    public assignedToId?: number,
    public parentId?: number,
    public dealId?: number
  ) {
    this.isActive = this.isActive || false;
    this.isDeleted = this.isDeleted || false;
  }
}
