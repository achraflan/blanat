import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDeal, Deal } from 'app/shared/model/deal.model';
import { DealService } from './deal.service';
import { DealComponent } from './deal.component';
import { DealDetailComponent } from './deal-detail.component';
import { DealUpdateComponent } from './deal-update.component';

@Injectable({ providedIn: 'root' })
export class DealResolve implements Resolve<IDeal> {
  constructor(private service: DealService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDeal> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((deal: HttpResponse<Deal>) => {
          if (deal.body) {
            return of(deal.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Deal());
  }
}

export const dealRoute: Routes = [
  {
    path: '',
    component: DealComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.deal.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DealDetailComponent,
    resolve: {
      deal: DealResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.deal.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DealUpdateComponent,
    resolve: {
      deal: DealResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.deal.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DealUpdateComponent,
    resolve: {
      deal: DealResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.deal.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
