import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDealUser } from 'app/shared/model/deal-user.model';

type EntityResponseType = HttpResponse<IDealUser>;
type EntityArrayResponseType = HttpResponse<IDealUser[]>;

@Injectable({ providedIn: 'root' })
export class DealUserService {
  public resourceUrl = SERVER_API_URL + 'api/deal-users';

  constructor(protected http: HttpClient) {}

  create(dealUser: IDealUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dealUser);
    return this.http
      .post<IDealUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dealUser: IDealUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dealUser);
    return this.http
      .put<IDealUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDealUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDealUser[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dealUser: IDealUser): IDealUser {
    const copy: IDealUser = Object.assign({}, dealUser, {
      birthDay: dealUser.birthDay && dealUser.birthDay.isValid() ? dealUser.birthDay.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.birthDay = res.body.birthDay ? moment(res.body.birthDay) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((dealUser: IDealUser) => {
        dealUser.birthDay = dealUser.birthDay ? moment(dealUser.birthDay) : undefined;
      });
    }
    return res;
  }
}
