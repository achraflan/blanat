import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommentHistory } from 'app/shared/model/comment-history.model';

type EntityResponseType = HttpResponse<ICommentHistory>;
type EntityArrayResponseType = HttpResponse<ICommentHistory[]>;

@Injectable({ providedIn: 'root' })
export class CommentHistoryService {
  public resourceUrl = SERVER_API_URL + 'api/comment-histories';

  constructor(protected http: HttpClient) {}

  create(commentHistory: ICommentHistory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commentHistory);
    return this.http
      .post<ICommentHistory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(commentHistory: ICommentHistory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commentHistory);
    return this.http
      .put<ICommentHistory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICommentHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICommentHistory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(commentHistory: ICommentHistory): ICommentHistory {
    const copy: ICommentHistory = Object.assign({}, commentHistory, {
      dateModification:
        commentHistory.dateModification && commentHistory.dateModification.isValid()
          ? commentHistory.dateModification.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateModification = res.body.dateModification ? moment(res.body.dateModification) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((commentHistory: ICommentHistory) => {
        commentHistory.dateModification = commentHistory.dateModification ? moment(commentHistory.dateModification) : undefined;
      });
    }
    return res;
  }
}
