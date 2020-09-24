import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAttributeDefinition } from 'app/shared/model/attribute-definition.model';

type EntityResponseType = HttpResponse<IAttributeDefinition>;
type EntityArrayResponseType = HttpResponse<IAttributeDefinition[]>;

@Injectable({ providedIn: 'root' })
export class AttributeDefinitionService {
  public resourceUrl = SERVER_API_URL + 'api/attribute-definitions';

  constructor(protected http: HttpClient) {}

  create(attributeDefinition: IAttributeDefinition): Observable<EntityResponseType> {
    return this.http.post<IAttributeDefinition>(this.resourceUrl, attributeDefinition, { observe: 'response' });
  }

  update(attributeDefinition: IAttributeDefinition): Observable<EntityResponseType> {
    return this.http.put<IAttributeDefinition>(this.resourceUrl, attributeDefinition, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAttributeDefinition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAttributeDefinition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
