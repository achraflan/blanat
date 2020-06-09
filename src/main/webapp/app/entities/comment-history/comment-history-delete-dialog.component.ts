import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommentHistory } from 'app/shared/model/comment-history.model';
import { CommentHistoryService } from './comment-history.service';

@Component({
  templateUrl: './comment-history-delete-dialog.component.html',
})
export class CommentHistoryDeleteDialogComponent {
  commentHistory?: ICommentHistory;

  constructor(
    protected commentHistoryService: CommentHistoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commentHistoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commentHistoryListModification');
      this.activeModal.close();
    });
  }
}
