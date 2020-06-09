import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDealTrack } from 'app/shared/model/deal-track.model';

type EntityResponseType = HttpResponse<IDealTrack>;
type EntityArrayResponseType = HttpResponse<IDealTrack[]>;

@Injectable({ providedIn: 'root' })
export class DealTrackService {
  public resourceUrl = SERVER_API_URL + 'api/deal-tracks';

  constructor(protected http: HttpClient) {}

  create(dealTrack: IDealTrack): Observable<EntityResponseType> {
    return this.http.post<IDealTrack>(this.resourceUrl, dealTrack, { observe: 'response' });
  }

  update(dealTrack: IDealTrack): Observable<EntityResponseType> {
    return this.http.put<IDealTrack>(this.resourceUrl, dealTrack, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDealTrack>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDealTrack[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
