import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDealReport } from 'app/shared/model/deal-report.model';
import { DealReportService } from './deal-report.service';

@Component({
  templateUrl: './deal-report-delete-dialog.component.html',
})
export class DealReportDeleteDialogComponent {
  dealReport?: IDealReport;

  constructor(
    protected dealReportService: DealReportService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dealReportService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dealReportListModification');
      this.activeModal.close();
    });
  }
}
