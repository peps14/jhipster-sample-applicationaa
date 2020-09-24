import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICacheConfiguration } from 'app/shared/model/cache-configuration.model';

type EntityResponseType = HttpResponse<ICacheConfiguration>;
type EntityArrayResponseType = HttpResponse<ICacheConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class CacheConfigurationService {
  public resourceUrl = SERVER_API_URL + 'api/cache-configurations';

  constructor(protected http: HttpClient) {}

  create(cacheConfiguration: ICacheConfiguration): Observable<EntityResponseType> {
    return this.http.post<ICacheConfiguration>(this.resourceUrl, cacheConfiguration, { observe: 'response' });
  }

  update(cacheConfiguration: ICacheConfiguration): Observable<EntityResponseType> {
    return this.http.put<ICacheConfiguration>(this.resourceUrl, cacheConfiguration, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICacheConfiguration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICacheConfiguration[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
