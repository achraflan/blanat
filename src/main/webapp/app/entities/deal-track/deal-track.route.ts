import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDealTrack, DealTrack } from 'app/shared/model/deal-track.model';
import { DealTrackService } from './deal-track.service';
import { DealTrackComponent } from './deal-track.component';
import { DealTrackDetailComponent } from './deal-track-detail.component';
import { DealTrackUpdateComponent } from './deal-track-update.component';

@Injectable({ providedIn: 'root' })
export class DealTrackResolve implements Resolve<IDealTrack> {
  constructor(private service: DealTrackService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDealTrack> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dealTrack: HttpResponse<DealTrack>) => {
          if (dealTrack.body) {
            return of(dealTrack.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DealTrack());
  }
}

export const dealTrackRoute: Routes = [
  {
    path: '',
    component: DealTrackComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'blanatApp.dealTrack.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DealTrackDetailComponent,
    resolve: {
      dealTrack: DealTrackResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealTrack.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DealTrackUpdateComponent,
    resolve: {
      dealTrack: DealTrackResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealTrack.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DealTrackUpdateComponent,
    resolve: {
      dealTrack: DealTrackResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealTrack.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
