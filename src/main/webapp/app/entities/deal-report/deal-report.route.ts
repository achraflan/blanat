import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDealReport, DealReport } from 'app/shared/model/deal-report.model';
import { DealReportService } from './deal-report.service';
import { DealReportComponent } from './deal-report.component';
import { DealReportDetailComponent } from './deal-report-detail.component';
import { DealReportUpdateComponent } from './deal-report-update.component';

@Injectable({ providedIn: 'root' })
export class DealReportResolve implements Resolve<IDealReport> {
  constructor(private service: DealReportService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDealReport> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dealReport: HttpResponse<DealReport>) => {
          if (dealReport.body) {
            return of(dealReport.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DealReport());
  }
}

export const dealReportRoute: Routes = [
  {
    path: '',
    component: DealReportComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'blanatApp.dealReport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DealReportDetailComponent,
    resolve: {
      dealReport: DealReportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealReport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DealReportUpdateComponent,
    resolve: {
      dealReport: DealReportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealReport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DealReportUpdateComponent,
    resolve: {
      dealReport: DealReportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealReport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
