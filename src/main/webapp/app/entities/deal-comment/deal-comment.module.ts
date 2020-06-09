import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { DealCommentComponent } from './deal-comment.component';
import { DealCommentDetailComponent } from './deal-comment-detail.component';
import { DealCommentUpdateComponent } from './deal-comment-update.component';
import { DealCommentDeleteDialogComponent } from './deal-comment-delete-dialog.component';
import { dealCommentRoute } from './deal-comment.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(dealCommentRoute)],
  declarations: [DealCommentComponent, DealCommentDetailComponent, DealCommentUpdateComponent, DealCommentDeleteDialogComponent],
  entryComponents: [DealCommentDeleteDialogComponent],
})
export class BlanatDealCommentModule {}
