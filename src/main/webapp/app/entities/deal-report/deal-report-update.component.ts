import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDealReport, DealReport } from 'app/shared/model/deal-report.model';
import { DealReportService } from './deal-report.service';
import { IDealUser } from 'app/shared/model/deal-user.model';
import { DealUserService } from 'app/entities/deal-user/deal-user.service';
import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from 'app/entities/deal/deal.service';

type SelectableEntity = IDealUser | IDeal;

@Component({
  selector: 'jhi-deal-report-update',
  templateUrl: './deal-report-update.component.html',
})
export class DealReportUpdateComponent implements OnInit {
  isSaving = false;
  dealusers: IDealUser[] = [];
  deals: IDeal[] = [];
  dateReportDp: any;
  dateCloseDp: any;

  editForm = this.fb.group({
    id: [],
    reason: [null, [Validators.required, Validators.maxLength(1000)]],
    dateReport: [null, [Validators.required]],
    dateClose: [],
    isValid: [],
    assignedToId: [null, Validators.required],
    dealId: [null, Validators.required],
  });

  constructor(
    protected dealReportService: DealReportService,
    protected dealUserService: DealUserService,
    protected dealService: DealService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealReport }) => {
      this.updateForm(dealReport);

      this.dealUserService.query().subscribe((res: HttpResponse<IDealUser[]>) => (this.dealusers = res.body || []));

      this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));
    });
  }

  updateForm(dealReport: IDealReport): void {
    this.editForm.patchValue({
      id: dealReport.id,
      reason: dealReport.reason,
      dateReport: dealReport.dateReport,
      dateClose: dealReport.dateClose,
      isValid: dealReport.isValid,
      assignedToId: dealReport.assignedToId,
      dealId: dealReport.dealId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dealReport = this.createFromForm();
    if (dealReport.id !== undefined) {
      this.subscribeToSaveResponse(this.dealReportService.update(dealReport));
    } else {
      this.subscribeToSaveResponse(this.dealReportService.create(dealReport));
    }
  }

  private createFromForm(): IDealReport {
    return {
      ...new DealReport(),
      id: this.editForm.get(['id'])!.value,
      reason: this.editForm.get(['reason'])!.value,
      dateReport: this.editForm.get(['dateReport'])!.value,
      dateClose: this.editForm.get(['dateClose'])!.value,
      isValid: this.editForm.get(['isValid'])!.value,
      assignedToId: this.editForm.get(['assignedToId'])!.value,
      dealId: this.editForm.get(['dealId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDealReport>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
