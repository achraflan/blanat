import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommentHistory, CommentHistory } from 'app/shared/model/comment-history.model';
import { CommentHistoryService } from './comment-history.service';
import { CommentHistoryComponent } from './comment-history.component';
import { CommentHistoryDetailComponent } from './comment-history-detail.component';
import { CommentHistoryUpdateComponent } from './comment-history-update.component';

@Injectable({ providedIn: 'root' })
export class CommentHistoryResolve implements Resolve<ICommentHistory> {
  constructor(private service: CommentHistoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommentHistory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((commentHistory: HttpResponse<CommentHistory>) => {
          if (commentHistory.body) {
            return of(commentHistory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CommentHistory());
  }
}

export const commentHistoryRoute: Routes = [
  {
    path: '',
    component: CommentHistoryComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.commentHistory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommentHistoryDetailComponent,
    resolve: {
      commentHistory: CommentHistoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.commentHistory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommentHistoryUpdateComponent,
    resolve: {
      commentHistory: CommentHistoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.commentHistory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommentHistoryUpdateComponent,
    resolve: {
      commentHistory: CommentHistoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'blanatApp.commentHistory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
