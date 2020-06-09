import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDealHistory, DealHistory } from 'app/shared/model/deal-history.model';
import { DealHistoryService } from './deal-history.service';
import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from 'app/entities/deal/deal.service';

@Component({
  selector: 'jhi-deal-history-update',
  templateUrl: './deal-history-update.component.html',
})
export class DealHistoryUpdateComponent implements OnInit {
  isSaving = false;
  deals: IDeal[] = [];
  dateModificationDp: any;

  editForm = this.fb.group({
    id: [],
    attributName: [null, [Validators.required]],
    attributLastValue: [null, [Validators.required, Validators.maxLength(10000)]],
    dateModification: [null, [Validators.required]],
    dealId: [null, Validators.required],
  });

  constructor(
    protected dealHistoryService: DealHistoryService,
    protected dealService: DealService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealHistory }) => {
      this.updateForm(dealHistory);

      this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));
    });
  }

  updateForm(dealHistory: IDealHistory): void {
    this.editForm.patchValue({
      id: dealHistory.id,
      attributName: dealHistory.attributName,
      attributLastValue: dealHistory.attributLastValue,
      dateModification: dealHistory.dateModification,
      dealId: dealHistory.dealId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dealHistory = this.createFromForm();
    if (dealHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.dealHistoryService.update(dealHistory));
    } else {
      this.subscribeToSaveResponse(this.dealHistoryService.create(dealHistory));
    }
  }

  private createFromForm(): IDealHistory {
    return {
      ...new DealHistory(),
      id: this.editForm.get(['id'])!.value,
      attributName: this.editForm.get(['attributName'])!.value,
      attributLastValue: this.editForm.get(['attributLastValue'])!.value,
      dateModification: this.editForm.get(['dateModification'])!.value,
      dealId: this.editForm.get(['dealId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDealHistory>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IDeal): any {
    return item.id;
  }
}
