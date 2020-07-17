import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRegistry } from 'app/shared/model/registry.model';

type EntityResponseType = HttpResponse<IRegistry>;
type EntityArrayResponseType = HttpResponse<IRegistry[]>;

@Injectable({ providedIn: 'root' })
export class RegistryService {
  public resourceUrl = SERVER_API_URL + 'api/registries';

  constructor(protected http: HttpClient) {}

  create(registry: IRegistry): Observable<EntityResponseType> {
    return this.http.post<IRegistry>(this.resourceUrl, registry, { observe: 'response' });
  }

  update(registry: IRegistry): Observable<EntityResponseType> {
    return this.http.put<IRegistry>(this.resourceUrl, registry, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRegistry>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRegistry[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
