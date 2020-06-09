import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { CommentHistoryComponent } from './comment-history.component';
import { CommentHistoryDetailComponent } from './comment-history-detail.component';
import { CommentHistoryUpdateComponent } from './comment-history-update.component';
import { CommentHistoryDeleteDialogComponent } from './comment-history-delete-dialog.component';
import { commentHistoryRoute } from './comment-history.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(commentHistoryRoute)],
  declarations: [
    CommentHistoryComponent,
    CommentHistoryDetailComponent,
    CommentHistoryUpdateComponent,
    CommentHistoryDeleteDialogComponent,
  ],
  entryComponents: [CommentHistoryDeleteDialogComponent],
})
export class BlanatCommentHistoryModule {}
