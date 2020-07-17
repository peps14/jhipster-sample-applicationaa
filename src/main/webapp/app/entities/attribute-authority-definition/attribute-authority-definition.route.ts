import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAttributeAuthorityDefinition, AttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';
import { AttributeAuthorityDefinitionService } from './attribute-authority-definition.service';
import { AttributeAuthorityDefinitionComponent } from './attribute-authority-definition.component';
import { AttributeAuthorityDefinitionDetailComponent } from './attribute-authority-definition-detail.component';
import { AttributeAuthorityDefinitionUpdateComponent } from './attribute-authority-definition-update.component';

@Injectable({ providedIn: 'root' })
export class AttributeAuthorityDefinitionResolve implements Resolve<IAttributeAuthorityDefinition> {
  constructor(private service: AttributeAuthorityDefinitionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAttributeAuthorityDefinition> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((attributeAuthorityDefinition: HttpResponse<AttributeAuthorityDefinition>) => {
          if (attributeAuthorityDefinition.body) {
            return of(attributeAuthorityDefinition.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AttributeAuthorityDefinition());
  }
}

export const attributeAuthorityDefinitionRoute: Routes = [
  {
    path: '',
    component: AttributeAuthorityDefinitionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.attributeAuthorityDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AttributeAuthorityDefinitionDetailComponent,
    resolve: {
      attributeAuthorityDefinition: AttributeAuthorityDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.attributeAuthorityDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AttributeAuthorityDefinitionUpdateComponent,
    resolve: {
      attributeAuthorityDefinition: AttributeAuthorityDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.attributeAuthorityDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AttributeAuthorityDefinitionUpdateComponent,
    resolve: {
      attributeAuthorityDefinition: AttributeAuthorityDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.attributeAuthorityDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
