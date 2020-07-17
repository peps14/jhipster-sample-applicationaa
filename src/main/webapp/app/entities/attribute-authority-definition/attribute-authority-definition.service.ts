import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';

type EntityResponseType = HttpResponse<IAttributeAuthorityDefinition>;
type EntityArrayResponseType = HttpResponse<IAttributeAuthorityDefinition[]>;

@Injectable({ providedIn: 'root' })
export class AttributeAuthorityDefinitionService {
  public resourceUrl = SERVER_API_URL + 'api/attribute-authority-definitions';

  constructor(protected http: HttpClient) {}

  create(attributeAuthorityDefinition: IAttributeAuthorityDefinition): Observable<EntityResponseType> {
    return this.http.post<IAttributeAuthorityDefinition>(this.resourceUrl, attributeAuthorityDefinition, { observe: 'response' });
  }

  update(attributeAuthorityDefinition: IAttributeAuthorityDefinition): Observable<EntityResponseType> {
    return this.http.put<IAttributeAuthorityDefinition>(this.resourceUrl, attributeAuthorityDefinition, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAttributeAuthorityDefinition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAttributeAuthorityDefinition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
