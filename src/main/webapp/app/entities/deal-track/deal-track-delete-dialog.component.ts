import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDealTrack } from 'app/shared/model/deal-track.model';
import { DealTrackService } from './deal-track.service';

@Component({
  templateUrl: './deal-track-delete-dialog.component.html',
})
export class DealTrackDeleteDialogComponent {
  dealTrack?: IDealTrack;

  constructor(protected dealTrackService: DealTrackService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dealTrackService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dealTrackListModification');
      this.activeModal.close();
    });
  }
}
