import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDealHistory } from 'app/shared/model/deal-history.model';

type EntityResponseType = HttpResponse<IDealHistory>;
type EntityArrayResponseType = HttpResponse<IDealHistory[]>;

@Injectable({ providedIn: 'root' })
export class DealHistoryService {
  public resourceUrl = SERVER_API_URL + 'api/deal-histories';

  constructor(protected http: HttpClient) {}

  create(dealHistory: IDealHistory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dealHistory);
    return this.http
      .post<IDealHistory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dealHistory: IDealHistory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dealHistory);
    return this.http
      .put<IDealHistory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDealHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDealHistory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dealHistory: IDealHistory): IDealHistory {
    const copy: IDealHistory = Object.assign({}, dealHistory, {
      dateModification:
        dealHistory.dateModification && dealHistory.dateModification.isValid()
          ? dealHistory.dateModification.format(DATE_FORMAT)
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
      res.body.forEach((dealHistory: IDealHistory) => {
        dealHistory.dateModification = dealHistory.dateModification ? moment(dealHistory.dateModification) : undefined;
      });
    }
    return res;
  }
}
