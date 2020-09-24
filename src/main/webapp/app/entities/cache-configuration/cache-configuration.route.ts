import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICacheConfiguration, CacheConfiguration } from 'app/shared/model/cache-configuration.model';
import { CacheConfigurationService } from './cache-configuration.service';
import { CacheConfigurationComponent } from './cache-configuration.component';
import { CacheConfigurationDetailComponent } from './cache-configuration-detail.component';
import { CacheConfigurationUpdateComponent } from './cache-configuration-update.component';

@Injectable({ providedIn: 'root' })
export class CacheConfigurationResolve implements Resolve<ICacheConfiguration> {
  constructor(private service: CacheConfigurationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICacheConfiguration> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cacheConfiguration: HttpResponse<CacheConfiguration>) => {
          if (cacheConfiguration.body) {
            return of(cacheConfiguration.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CacheConfiguration());
  }
}

export const cacheConfigurationRoute: Routes = [
  {
    path: '',
    component: CacheConfigurationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.cacheConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CacheConfigurationDetailComponent,
    resolve: {
      cacheConfiguration: CacheConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.cacheConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CacheConfigurationUpdateComponent,
    resolve: {
      cacheConfiguration: CacheConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.cacheConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CacheConfigurationUpdateComponent,
    resolve: {
      cacheConfiguration: CacheConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationaaApp.cacheConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
