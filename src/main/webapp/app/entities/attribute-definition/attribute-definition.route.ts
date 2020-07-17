import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAttributeDefinition, AttributeDefinition } from 'app/shared/model/attribute-definition.model';
import { AttributeDefinitionService } from './attribute-definition.service';
import { AttributeDefinitionComponent } from './attribute-definition.component';
import { AttributeDefinitionDetailComponent } from './attribute-definition-detail.component';
import { AttributeDefinitionUpdateComponent } from './attribute-definition-update.component';

@Injectable({ providedIn: 'root' })
export class AttributeDefinitionResolve implements Resolve<IAttributeDefinition> {
  constructor(private service: AttributeDefinitionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAttributeDefinition> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((attributeDefinition: HttpResponse<AttributeDefinition>) => {
          if (attributeDefinition.body) {
            return of(attributeDefinition.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AttributeDefinition());
  }
}

export const attributeDefinitionRoute: Routes = [
  {
    path: '',
    component: AttributeDefinitionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.attributeDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AttributeDefinitionDetailComponent,
    resolve: {
      attributeDefinition: AttributeDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.attributeDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AttributeDefinitionUpdateComponent,
    resolve: {
      attributeDefinition: AttributeDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.attributeDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AttributeDefinitionUpdateComponent,
    resolve: {
      attributeDefinition: AttributeDefinitionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.attributeDefinition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
