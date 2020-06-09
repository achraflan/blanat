import { Moment } from 'moment';

export interface ICommentHistory {
  id?: number;
  comment?: string;
  dateModification?: Moment;
  dealCommentId?: number;
}

export class CommentHistory implements ICommentHistory {
  constructor(public id?: number, public comment?: string, public dateModification?: Moment, public dealCommentId?: number) {}
}
