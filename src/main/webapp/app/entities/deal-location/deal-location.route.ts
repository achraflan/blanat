import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDealLocation, DealLocation } from 'app/shared/model/deal-location.model';
import { DealLocationService } from './deal-location.service';
import { DealLocationComponent } from './deal-location.component';
import { DealLocationDetailComponent } from './deal-location-detail.component';
import { DealLocationUpdateComponent } from './deal-location-update.component';

@Injectable({ providedIn: 'root' })
export class DealLocationResolve implements Resolve<IDealLocation> {
  constructor(private service: DealLocationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDealLocation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dealLocation: HttpResponse<DealLocation>) => {
          if (dealLocation.body) {
            return of(dealLocation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DealLocation());
  }
}

export const dealLocationRoute: Routes = [
  {
    path: '',
    component: DealLocationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DealLocationDetailComponent,
    resolve: {
      dealLocation: DealLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DealLocationUpdateComponent,
    resolve: {
      dealLocation: DealLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DealLocationUpdateComponent,
    resolve: {
      dealLocation: DealLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
