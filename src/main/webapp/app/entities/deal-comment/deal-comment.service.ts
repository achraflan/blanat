import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDealComment } from 'app/shared/model/deal-comment.model';

type EntityResponseType = HttpResponse<IDealComment>;
type EntityArrayResponseType = HttpResponse<IDealComment[]>;

@Injectable({ providedIn: 'root' })
export class DealCommentService {
  public resourceUrl = SERVER_API_URL + 'api/deal-comments';

  constructor(protected http: HttpClient) {}

  create(dealComment: IDealComment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dealComment);
    return this.http
      .post<IDealComment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dealComment: IDealComment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dealComment);
    return this.http
      .put<IDealComment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDealComment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDealComment[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dealComment: IDealComment): IDealComment {
    const copy: IDealComment = Object.assign({}, dealComment, {
      dateComment: dealComment.dateComment && dealComment.dateComment.isValid() ? dealComment.dateComment.format(DATE_FORMAT) : undefined,
      dateLastModification:
        dealComment.dateLastModification && dealComment.dateLastModification.isValid()
          ? dealComment.dateLastModification.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateComment = res.body.dateComment ? moment(res.body.dateComment) : undefined;
      res.body.dateLastModification = res.body.dateLastModification ? moment(res.body.dateLastModification) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((dealComment: IDealComment) => {
        dealComment.dateComment = dealComment.dateComment ? moment(dealComment.dateComment) : undefined;
        dealComment.dateLastModification = dealComment.dateLastModification ? moment(dealComment.dateLastModification) : undefined;
      });
    }
    return res;
  }
}
