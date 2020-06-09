import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'deal',
        loadChildren: () => import('./deal/deal.module').then(m => m.BlanatDealModule),
      },
      {
        path: 'deal-category',
        loadChildren: () => import('./deal-category/deal-category.module').then(m => m.BlanatDealCategoryModule),
      },
      {
        path: 'deal-history',
        loadChildren: () => import('./deal-history/deal-history.module').then(m => m.BlanatDealHistoryModule),
      },
      {
        path: 'deal-report',
        loadChildren: () => import('./deal-report/deal-report.module').then(m => m.BlanatDealReportModule),
      },
      {
        path: 'deal-comment',
        loadChildren: () => import('./deal-comment/deal-comment.module').then(m => m.BlanatDealCommentModule),
      },
      {
        path: 'comment-history',
        loadChildren: () => import('./comment-history/comment-history.module').then(m => m.BlanatCommentHistoryModule),
      },
      {
        path: 'deal-track',
        loadChildren: () => import('./deal-track/deal-track.module').then(m => m.BlanatDealTrackModule),
      },
      {
        path: 'deal-user',
        loadChildren: () => import('./deal-user/deal-user.module').then(m => m.BlanatDealUserModule),
      },
      {
        path: 'deal-location',
        loadChildren: () => import('./deal-location/deal-location.module').then(m => m.BlanatDealLocationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BlanatEntityModule {}
