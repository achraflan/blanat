import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommentHistory, CommentHistory } from 'app/shared/model/comment-history.model';
import { CommentHistoryService } from './comment-history.service';
import { IDealComment } from 'app/shared/model/deal-comment.model';
import { DealCommentService } from 'app/entities/deal-comment/deal-comment.service';

@Component({
  selector: 'jhi-comment-history-update',
  templateUrl: './comment-history-update.component.html',
})
export class CommentHistoryUpdateComponent implements OnInit {
  isSaving = false;
  dealcomments: IDealComment[] = [];
  dateModificationDp: any;

  editForm = this.fb.group({
    id: [],
    comment: [null, [Validators.required, Validators.maxLength(1000)]],
    dateModification: [],
    dealCommentId: [null, Validators.required],
  });

  constructor(
    protected commentHistoryService: CommentHistoryService,
    protected dealCommentService: DealCommentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commentHistory }) => {
      this.updateForm(commentHistory);

      this.dealCommentService.query().subscribe((res: HttpResponse<IDealComment[]>) => (this.dealcomments = res.body || []));
    });
  }

  updateForm(commentHistory: ICommentHistory): void {
    this.editForm.patchValue({
      id: commentHistory.id,
      comment: commentHistory.comment,
      dateModification: commentHistory.dateModification,
      dealCommentId: commentHistory.dealCommentId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commentHistory = this.createFromForm();
    if (commentHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.commentHistoryService.update(commentHistory));
    } else {
      this.subscribeToSaveResponse(this.commentHistoryService.create(commentHistory));
    }
  }

  private createFromForm(): ICommentHistory {
    return {
      ...new CommentHistory(),
      id: this.editForm.get(['id'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      dateModification: this.editForm.get(['dateModification'])!.value,
      dealCommentId: this.editForm.get(['dealCommentId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommentHistory>>): void {
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

  trackById(index: number, item: IDealComment): any {
    return item.id;
  }
}
