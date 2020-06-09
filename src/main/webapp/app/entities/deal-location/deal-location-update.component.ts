import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDealLocation, DealLocation } from 'app/shared/model/deal-location.model';
import { DealLocationService } from './deal-location.service';

@Component({
  selector: 'jhi-deal-location-update',
  templateUrl: './deal-location-update.component.html',
})
export class DealLocationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    city: [null, [Validators.required]],
    country: [],
  });

  constructor(protected dealLocationService: DealLocationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealLocation }) => {
      this.updateForm(dealLocation);
    });
  }

  updateForm(dealLocation: IDealLocation): void {
    this.editForm.patchValue({
      id: dealLocation.id,
      city: dealLocation.city,
      country: dealLocation.country,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dealLocation = this.createFromForm();
    if (dealLocation.id !== undefined) {
      this.subscribeToSaveResponse(this.dealLocationService.update(dealLocation));
    } else {
      this.subscribeToSaveResponse(this.dealLocationService.create(dealLocation));
    }
  }

  private createFromForm(): IDealLocation {
    return {
      ...new DealLocation(),
      id: this.editForm.get(['id'])!.value,
      city: this.editForm.get(['city'])!.value,
      country: this.editForm.get(['country'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDealLocation>>): void {
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
}
