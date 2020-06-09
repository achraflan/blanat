import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDealUser, DealUser } from 'app/shared/model/deal-user.model';
import { DealUserService } from './deal-user.service';
import { DealUserComponent } from './deal-user.component';
import { DealUserDetailComponent } from './deal-user-detail.component';
import { DealUserUpdateComponent } from './deal-user-update.component';

@Injectable({ providedIn: 'root' })
export class DealUserResolve implements Resolve<IDealUser> {
  constructor(private service: DealUserService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDealUser> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dealUser: HttpResponse<DealUser>) => {
          if (dealUser.body) {
            return of(dealUser.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DealUser());
  }
}

export const dealUserRoute: Routes = [
  {
    path: '',
    component: DealUserComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'blanatApp.dealUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DealUserDetailComponent,
    resolve: {
      dealUser: DealUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DealUserUpdateComponent,
    resolve: {
      dealUser: DealUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DealUserUpdateComponent,
    resolve: {
      dealUser: DealUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
