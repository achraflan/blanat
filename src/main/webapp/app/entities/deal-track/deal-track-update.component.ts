import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDealTrack, DealTrack } from 'app/shared/model/deal-track.model';
import { DealTrackService } from './deal-track.service';
import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from 'app/entities/deal/deal.service';

@Component({
  selector: 'jhi-deal-track-update',
  templateUrl: './deal-track-update.component.html',
})
export class DealTrackUpdateComponent implements OnInit {
  isSaving = false;
  deals: IDeal[] = [];

  editForm = this.fb.group({
    id: [],
    ipLocalisation: [null, [Validators.required]],
    localisation: [],
    isAuthentified: [null, [Validators.required]],
    dealId: [null, Validators.required],
  });

  constructor(
    protected dealTrackService: DealTrackService,
    protected dealService: DealService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealTrack }) => {
      this.updateForm(dealTrack);

      this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));
    });
  }

  updateForm(dealTrack: IDealTrack): void {
    this.editForm.patchValue({
      id: dealTrack.id,
      ipLocalisation: dealTrack.ipLocalisation,
      localisation: dealTrack.localisation,
      isAuthentified: dealTrack.isAuthentified,
      dealId: dealTrack.dealId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dealTrack = this.createFromForm();
    if (dealTrack.id !== undefined) {
      this.subscribeToSaveResponse(this.dealTrackService.update(dealTrack));
    } else {
      this.subscribeToSaveResponse(this.dealTrackService.create(dealTrack));
    }
  }

  private createFromForm(): IDealTrack {
    return {
      ...new DealTrack(),
      id: this.editForm.get(['id'])!.value,
      ipLocalisation: this.editForm.get(['ipLocalisation'])!.value,
      localisation: this.editForm.get(['localisation'])!.value,
      isAuthentified: this.editForm.get(['isAuthentified'])!.value,
      dealId: this.editForm.get(['dealId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDealTrack>>): void {
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
