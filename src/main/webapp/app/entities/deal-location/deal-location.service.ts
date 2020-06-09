import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDealLocation } from 'app/shared/model/deal-location.model';

type EntityResponseType = HttpResponse<IDealLocation>;
type EntityArrayResponseType = HttpResponse<IDealLocation[]>;

@Injectable({ providedIn: 'root' })
export class DealLocationService {
  public resourceUrl = SERVER_API_URL + 'api/deal-locations';

  constructor(protected http: HttpClient) {}

  create(dealLocation: IDealLocation): Observable<EntityResponseType> {
    return this.http.post<IDealLocation>(this.resourceUrl, dealLocation, { observe: 'response' });
  }

  update(dealLocation: IDealLocation): Observable<EntityResponseType> {
    return this.http.put<IDealLocation>(this.resourceUrl, dealLocation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDealLocation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDealLocation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
