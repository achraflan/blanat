import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDealComment } from 'app/shared/model/deal-comment.model';
import { DealCommentService } from './deal-comment.service';

@Component({
  templateUrl: './deal-comment-delete-dialog.component.html',
})
export class DealCommentDeleteDialogComponent {
  dealComment?: IDealComment;

  constructor(
    protected dealCommentService: DealCommentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dealCommentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dealCommentListModification');
      this.activeModal.close();
    });
  }
}
