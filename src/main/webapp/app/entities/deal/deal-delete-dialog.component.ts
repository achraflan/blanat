import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from './deal.service';

@Component({
  templateUrl: './deal-delete-dialog.component.html',
})
export class DealDeleteDialogComponent {
  deal?: IDeal;

  constructor(protected dealService: DealService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dealService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dealListModification');
      this.activeModal.close();
    });
  }
}
