import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDealUser } from 'app/shared/model/deal-user.model';
import { DealUserService } from './deal-user.service';

@Component({
  templateUrl: './deal-user-delete-dialog.component.html',
})
export class DealUserDeleteDialogComponent {
  dealUser?: IDealUser;

  constructor(protected dealUserService: DealUserService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dealUserService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dealUserListModification');
      this.activeModal.close();
    });
  }
}
