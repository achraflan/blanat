import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDealReport } from 'app/shared/model/deal-report.model';

type EntityResponseType = HttpResponse<IDealReport>;
type EntityArrayResponseType = HttpResponse<IDealReport[]>;

@Injectable({ providedIn: 'root' })
export class DealReportService {
  public resourceUrl = SERVER_API_URL + 'api/deal-reports';

  constructor(protected http: HttpClient) {}

  create(dealReport: IDealReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dealReport);
    return this.http
      .post<IDealReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dealReport: IDealReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dealReport);
    return this.http
      .put<IDealReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDealReport>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDealReport[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dealReport: IDealReport): IDealReport {
    const copy: IDealReport = Object.assign({}, dealReport, {
      dateReport: dealReport.dateReport && dealReport.dateReport.isValid() ? dealReport.dateReport.format(DATE_FORMAT) : undefined,
      dateClose: dealReport.dateClose && dealReport.dateClose.isValid() ? dealReport.dateClose.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateReport = res.body.dateReport ? moment(res.body.dateReport) : undefined;
      res.body.dateClose = res.body.dateClose ? moment(res.body.dateClose) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((dealReport: IDealReport) => {
        dealReport.dateReport = dealReport.dateReport ? moment(dealReport.dateReport) : undefined;
        dealReport.dateClose = dealReport.dateClose ? moment(dealReport.dateClose) : undefined;
      });
    }
    return res;
  }
}
