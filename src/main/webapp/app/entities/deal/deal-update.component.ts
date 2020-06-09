import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IDeal, Deal } from 'app/shared/model/deal.model';
import { DealService } from './deal.service';
import { IDealLocation } from 'app/shared/model/deal-location.model';
import { DealLocationService } from 'app/entities/deal-location/deal-location.service';
import { IDealUser } from 'app/shared/model/deal-user.model';
import { DealUserService } from 'app/entities/deal-user/deal-user.service';
import { IDealCategory } from 'app/shared/model/deal-category.model';
import { DealCategoryService } from 'app/entities/deal-category/deal-category.service';

type SelectableEntity = IDealLocation | IDealUser | IDealCategory;

@Component({
  selector: 'jhi-deal-update',
  templateUrl: './deal-update.component.html',
})
export class DealUpdateComponent implements OnInit {
  isSaving = false;
  deallocations: IDealLocation[] = [];
  dealusers: IDealUser[] = [];
  dealcategories: IDealCategory[] = [];
  dateStartDp: any;
  dateEndDp: any;
  datePublicationDp: any;

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.required]],
    description: [null, [Validators.required, Validators.maxLength(10000)]],
    type: [null, [Validators.required]],
    url: [],
    image: [],
    price: [],
    priceNormal: [],
    priceShipping: [],
    coupon: [],
    couponType: [],
    couponValue: [],
    couponPercentage: [null, [Validators.min(1), Validators.max(100)]],
    dateStart: [],
    dateEnd: [],
    datePublication: [null, [Validators.required]],
    views: [],
    like: [],
    dislike: [],
    local: [],
    statut: [null, [Validators.required]],
    isDeleted: [],
    isBlocked: [],
    dealLocationId: [],
    assignedToId: [null, Validators.required],
    dealCategories: [],
  });

  constructor(
    protected dealService: DealService,
    protected dealLocationService: DealLocationService,
    protected dealUserService: DealUserService,
    protected dealCategoryService: DealCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deal }) => {
      this.updateForm(deal);

      this.dealLocationService
        .query({ filter: 'deal-is-null' })
        .pipe(
          map((res: HttpResponse<IDealLocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IDealLocation[]) => {
          if (!deal.dealLocationId) {
            this.deallocations = resBody;
          } else {
            this.dealLocationService
              .find(deal.dealLocationId)
              .pipe(
                map((subRes: HttpResponse<IDealLocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IDealLocation[]) => (this.deallocations = concatRes));
          }
        });

      this.dealUserService.query().subscribe((res: HttpResponse<IDealUser[]>) => (this.dealusers = res.body || []));

      this.dealCategoryService.query().subscribe((res: HttpResponse<IDealCategory[]>) => (this.dealcategories = res.body || []));
    });
  }

  updateForm(deal: IDeal): void {
    this.editForm.patchValue({
      id: deal.id,
      title: deal.title,
      description: deal.description,
      type: deal.type,
      url: deal.url,
      image: deal.image,
      price: deal.price,
      priceNormal: deal.priceNormal,
      priceShipping: deal.priceShipping,
      coupon: deal.coupon,
      couponType: deal.couponType,
      couponValue: deal.couponValue,
      couponPercentage: deal.couponPercentage,
      dateStart: deal.dateStart,
      dateEnd: deal.dateEnd,
      datePublication: deal.datePublication,
      views: deal.views,
      like: deal.like,
      dislike: deal.dislike,
      local: deal.local,
      statut: deal.statut,
      isDeleted: deal.isDeleted,
      isBlocked: deal.isBlocked,
      dealLocationId: deal.dealLocationId,
      assignedToId: deal.assignedToId,
      dealCategories: deal.dealCategories,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deal = this.createFromForm();
    if (deal.id !== undefined) {
      this.subscribeToSaveResponse(this.dealService.update(deal));
    } else {
      this.subscribeToSaveResponse(this.dealService.create(deal));
    }
  }

  private createFromForm(): IDeal {
    return {
      ...new Deal(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      description: this.editForm.get(['description'])!.value,
      type: this.editForm.get(['type'])!.value,
      url: this.editForm.get(['url'])!.value,
      image: this.editForm.get(['image'])!.value,
      price: this.editForm.get(['price'])!.value,
      priceNormal: this.editForm.get(['priceNormal'])!.value,
      priceShipping: this.editForm.get(['priceShipping'])!.value,
      coupon: this.editForm.get(['coupon'])!.value,
      couponType: this.editForm.get(['couponType'])!.value,
      couponValue: this.editForm.get(['couponValue'])!.value,
      couponPercentage: this.editForm.get(['couponPercentage'])!.value,
      dateStart: this.editForm.get(['dateStart'])!.value,
      dateEnd: this.editForm.get(['dateEnd'])!.value,
      datePublication: this.editForm.get(['datePublication'])!.value,
      views: this.editForm.get(['views'])!.value,
      like: this.editForm.get(['like'])!.value,
      dislike: this.editForm.get(['dislike'])!.value,
      local: this.editForm.get(['local'])!.value,
      statut: this.editForm.get(['statut'])!.value,
      isDeleted: this.editForm.get(['isDeleted'])!.value,
      isBlocked: this.editForm.get(['isBlocked'])!.value,
      dealLocationId: this.editForm.get(['dealLocationId'])!.value,
      assignedToId: this.editForm.get(['assignedToId'])!.value,
      dealCategories: this.editForm.get(['dealCategories'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeal>>): void {
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

  getSelected(selectedVals: IDealCategory[], option: IDealCategory): IDealCategory {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
