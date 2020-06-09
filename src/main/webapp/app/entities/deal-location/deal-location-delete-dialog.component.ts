import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDealLocation } from 'app/shared/model/deal-location.model';
import { DealLocationService } from './deal-location.service';

@Component({
  templateUrl: './deal-location-delete-dialog.component.html',
})
export class DealLocationDeleteDialogComponent {
  dealLocation?: IDealLocation;

  constructor(
    protected dealLocationService: DealLocationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dealLocationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dealLocationListModification');
      this.activeModal.close();
    });
  }
}
