import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDealCategory, DealCategory } from 'app/shared/model/deal-category.model';
import { DealCategoryService } from './deal-category.service';

@Component({
  selector: 'jhi-deal-category-update',
  templateUrl: './deal-category-update.component.html',
})
export class DealCategoryUpdateComponent implements OnInit {
  isSaving = false;
  dealcategories: IDealCategory[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [null, [Validators.maxLength(1000)]],
    isParent: [],
    categoryId: [],
  });

  constructor(protected dealCategoryService: DealCategoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealCategory }) => {
      this.updateForm(dealCategory);

      this.dealCategoryService.query().subscribe((res: HttpResponse<IDealCategory[]>) => (this.dealcategories = res.body || []));
    });
  }

  updateForm(dealCategory: IDealCategory): void {
    this.editForm.patchValue({
      id: dealCategory.id,
      name: dealCategory.name,
      description: dealCategory.description,
      isParent: dealCategory.isParent,
      categoryId: dealCategory.categoryId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dealCategory = this.createFromForm();
    if (dealCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.dealCategoryService.update(dealCategory));
    } else {
      this.subscribeToSaveResponse(this.dealCategoryService.create(dealCategory));
    }
  }

  private createFromForm(): IDealCategory {
    return {
      ...new DealCategory(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      isParent: this.editForm.get(['isParent'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDealCategory>>): void {
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

  trackById(index: number, item: IDealCategory): any {
    return item.id;
  }
}
