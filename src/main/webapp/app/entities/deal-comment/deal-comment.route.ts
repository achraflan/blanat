import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDealComment, DealComment } from 'app/shared/model/deal-comment.model';
import { DealCommentService } from './deal-comment.service';
import { DealCommentComponent } from './deal-comment.component';
import { DealCommentDetailComponent } from './deal-comment-detail.component';
import { DealCommentUpdateComponent } from './deal-comment-update.component';

@Injectable({ providedIn: 'root' })
export class DealCommentResolve implements Resolve<IDealComment> {
  constructor(private service: DealCommentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDealComment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dealComment: HttpResponse<DealComment>) => {
          if (dealComment.body) {
            return of(dealComment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DealComment());
  }
}

export const dealCommentRoute: Routes = [
  {
    path: '',
    component: DealCommentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealComment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DealCommentDetailComponent,
    resolve: {
      dealComment: DealCommentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealComment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DealCommentUpdateComponent,
    resolve: {
      dealComment: DealCommentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealComment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DealCommentUpdateComponent,
    resolve: {
      dealComment: DealCommentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.dealComment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
