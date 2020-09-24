import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRegistry, Registry } from 'app/shared/model/registry.model';
import { RegistryService } from './registry.service';
import { RegistryComponent } from './registry.component';
import { RegistryDetailComponent } from './registry-detail.component';
import { RegistryUpdateComponent } from './registry-update.component';

@Injectable({ providedIn: 'root' })
export class RegistryResolve implements Resolve<IRegistry> {
  constructor(private service: RegistryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRegistry> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((registry: HttpResponse<Registry>) => {
          if (registry.body) {
            return of(registry.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Registry());
  }
}

export const registryRoute: Routes = [
  {
    path: '',
    component: RegistryComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.registry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RegistryDetailComponent,
    resolve: {
      registry: RegistryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.registry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RegistryUpdateComponent,
    resolve: {
      registry: RegistryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.registry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RegistryUpdateComponent,
    resolve: {
      registry: RegistryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.registry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
