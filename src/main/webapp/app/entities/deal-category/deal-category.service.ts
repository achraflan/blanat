import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDealCategory } from 'app/shared/model/deal-category.model';

type EntityResponseType = HttpResponse<IDealCategory>;
type EntityArrayResponseType = HttpResponse<IDealCategory[]>;

@Injectable({ providedIn: 'root' })
export class DealCategoryService {
  public resourceUrl = SERVER_API_URL + 'api/deal-categories';

  constructor(protected http: HttpClient) {}

  create(dealCategory: IDealCategory): Observable<EntityResponseType> {
    return this.http.post<IDealCategory>(this.resourceUrl, dealCategory, { observe: 'response' });
  }

  update(dealCategory: IDealCategory): Observable<EntityResponseType> {
    return this.http.put<IDealCategory>(this.resourceUrl, dealCategory, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDealCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDealCategory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
