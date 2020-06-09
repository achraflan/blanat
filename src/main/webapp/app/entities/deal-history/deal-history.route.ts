import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDealHistory, DealHistory } from 'app/shared/model/deal-history.model';
import { DealHistoryService } from './deal-history.service';
import { DealHistoryComponent } from './deal-history.component';
import { DealHistoryDetailComponent } from './deal-history-detail.component';
import { DealHistoryUpdateComponent } from './deal-history-update.component';

@Injectable({ providedIn: 'root' })
export class DealHistoryResolve implements Resolve<IDealHistory> {
  constructor(private service: DealHistoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDealHistory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dealHistory: HttpResponse<DealHistory>) => {
          if (dealHistory.body) {
            return of(dealHistory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DealHistory());
  }
}

export const dealHistoryRoute: Routes = [
  {
    path: '',
    component: DealHistoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'blanatApp.dealHistory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DealHistoryDetailComponent,
    resolve: {
      dealHistory: DealHistoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealHistory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DealHistoryUpdateComponent,
    resolve: {
      dealHistory: DealHistoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealHistory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DealHistoryUpdateComponent,
    resolve: {
      dealHistory: DealHistoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealHistory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
