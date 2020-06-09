import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDealComment, DealComment } from 'app/shared/model/deal-comment.model';
import { DealCommentService } from './deal-comment.service';
import { IDealUser } from 'app/shared/model/deal-user.model';
import { DealUserService } from 'app/entities/deal-user/deal-user.service';
import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from 'app/entities/deal/deal.service';

type SelectableEntity = IDealUser | IDealComment | IDeal;

@Component({
  selector: 'jhi-deal-comment-update',
  templateUrl: './deal-comment-update.component.html',
})
export class DealCommentUpdateComponent implements OnInit {
  isSaving = false;
  dealusers: IDealUser[] = [];
  dealcomments: IDealComment[] = [];
  deals: IDeal[] = [];
  dateCommentDp: any;
  dateLastModificationDp: any;

  editForm = this.fb.group({
    id: [],
    comment: [null, [Validators.required, Validators.maxLength(1000)]],
    dateComment: [],
    isActive: [],
    isDeleted: [],
    like: [],
    dislike: [],
    dateLastModification: [],
    assignedToId: [null, Validators.required],
    parentId: [],
    dealId: [null, Validators.required],
  });

  constructor(
    protected dealCommentService: DealCommentService,
    protected dealUserService: DealUserService,
    protected dealService: DealService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealComment }) => {
      this.updateForm(dealComment);

      this.dealUserService.query().subscribe((res: HttpResponse<IDealUser[]>) => (this.dealusers = res.body || []));

      this.dealCommentService.query().subscribe((res: HttpResponse<IDealComment[]>) => (this.dealcomments = res.body || []));

      this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));
    });
  }

  updateForm(dealComment: IDealComment): void {
    this.editForm.patchValue({
      id: dealComment.id,
      comment: dealComment.comment,
      dateComment: dealComment.dateComment,
      isActive: dealComment.isActive,
      isDeleted: dealComment.isDeleted,
      like: dealComment.like,
      dislike: dealComment.dislike,
      dateLastModification: dealComment.dateLastModification,
      assignedToId: dealComment.assignedToId,
      parentId: dealComment.parentId,
      dealId: dealComment.dealId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dealComment = this.createFromForm();
    if (dealComment.id !== undefined) {
      this.subscribeToSaveResponse(this.dealCommentService.update(dealComment));
    } else {
      this.subscribeToSaveResponse(this.dealCommentService.create(dealComment));
    }
  }

  private createFromForm(): IDealComment {
    return {
      ...new DealComment(),
      id: this.editForm.get(['id'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      dateComment: this.editForm.get(['dateComment'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      isDeleted: this.editForm.get(['isDeleted'])!.value,
      like: this.editForm.get(['like'])!.value,
      dislike: this.editForm.get(['dislike'])!.value,
      dateLastModification: this.editForm.get(['dateLastModification'])!.value,
      assignedToId: this.editForm.get(['assignedToId'])!.value,
      parentId: this.editForm.get(['parentId'])!.value,
      dealId: this.editForm.get(['dealId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDealComment>>): void {
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
