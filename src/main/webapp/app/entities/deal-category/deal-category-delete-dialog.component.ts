import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDealCategory } from 'app/shared/model/deal-category.model';
import { DealCategoryService } from './deal-category.service';

@Component({
  templateUrl: './deal-category-delete-dialog.component.html',
})
export class DealCategoryDeleteDialogComponent {
  dealCategory?: IDealCategory;

  constructor(
    protected dealCategoryService: DealCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dealCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dealCategoryListModification');
      this.activeModal.close();
    });
  }
}
