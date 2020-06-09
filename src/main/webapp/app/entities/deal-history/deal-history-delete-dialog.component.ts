import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDealHistory } from 'app/shared/model/deal-history.model';
import { DealHistoryService } from './deal-history.service';

@Component({
  templateUrl: './deal-history-delete-dialog.component.html',
})
export class DealHistoryDeleteDialogComponent {
  dealHistory?: IDealHistory;

  constructor(
    protected dealHistoryService: DealHistoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dealHistoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dealHistoryListModification');
      this.activeModal.close();
    });
  }
}
