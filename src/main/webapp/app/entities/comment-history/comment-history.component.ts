import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICommentHistory } from 'app/shared/model/comment-history.model';
import { CommentHistoryService } from './comment-history.service';
import { CommentHistoryDeleteDialogComponent } from './comment-history-delete-dialog.component';

@Component({
  selector: 'jhi-comment-history',
  templateUrl: './comment-history.component.html',
})
export class CommentHistoryComponent implements OnInit, OnDestroy {
  commentHistories?: ICommentHistory[];
  eventSubscriber?: Subscription;

  constructor(
    protected commentHistoryService: CommentHistoryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.commentHistoryService.query().subscribe((res: HttpResponse<ICommentHistory[]>) => (this.commentHistories = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCommentHistories();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICommentHistory): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCommentHistories(): void {
    this.eventSubscriber = this.eventManager.subscribe('commentHistoryListModification', () => this.loadAll());
  }

  delete(commentHistory: ICommentHistory): void {
    const modalRef = this.modalService.open(CommentHistoryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.commentHistory = commentHistory;
  }
}
