import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDeal } from 'app/shared/model/deal.model';

type EntityResponseType = HttpResponse<IDeal>;
type EntityArrayResponseType = HttpResponse<IDeal[]>;

@Injectable({ providedIn: 'root' })
export class DealService {
  public resourceUrl = SERVER_API_URL + 'api/deals';

  constructor(protected http: HttpClient) {}

  create(deal: IDeal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deal);
    return this.http
      .post<IDeal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(deal: IDeal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deal);
    return this.http
      .put<IDeal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDeal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDeal[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(deal: IDeal): IDeal {
    const copy: IDeal = Object.assign({}, deal, {
      dateStart: deal.dateStart && deal.dateStart.isValid() ? deal.dateStart.format(DATE_FORMAT) : undefined,
      dateEnd: deal.dateEnd && deal.dateEnd.isValid() ? deal.dateEnd.format(DATE_FORMAT) : undefined,
      datePublication: deal.datePublication && deal.datePublication.isValid() ? deal.datePublication.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateStart = res.body.dateStart ? moment(res.body.dateStart) : undefined;
      res.body.dateEnd = res.body.dateEnd ? moment(res.body.dateEnd) : undefined;
      res.body.datePublication = res.body.datePublication ? moment(res.body.datePublication) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((deal: IDeal) => {
        deal.dateStart = deal.dateStart ? moment(deal.dateStart) : undefined;
        deal.dateEnd = deal.dateEnd ? moment(deal.dateEnd) : undefined;
        deal.datePublication = deal.datePublication ? moment(deal.datePublication) : undefined;
      });
    }
    return res;
  }
}
