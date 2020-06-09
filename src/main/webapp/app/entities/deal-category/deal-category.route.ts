import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDealCategory, DealCategory } from 'app/shared/model/deal-category.model';
import { DealCategoryService } from './deal-category.service';
import { DealCategoryComponent } from './deal-category.component';
import { DealCategoryDetailComponent } from './deal-category-detail.component';
import { DealCategoryUpdateComponent } from './deal-category-update.component';

@Injectable({ providedIn: 'root' })
export class DealCategoryResolve implements Resolve<IDealCategory> {
  constructor(private service: DealCategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDealCategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dealCategory: HttpResponse<DealCategory>) => {
          if (dealCategory.body) {
            return of(dealCategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DealCategory());
  }
}

export const dealCategoryRoute: Routes = [
  {
    path: '',
    component: DealCategoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'blanatApp.dealCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DealCategoryDetailComponent,
    resolve: {
      dealCategory: DealCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DealCategoryUpdateComponent,
    resolve: {
      dealCategory: DealCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DealCategoryUpdateComponent,
    resolve: {
      dealCategory: DealCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
