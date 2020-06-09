import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommentHistory } from 'app/shared/model/comment-history.model';

@Component({
  selector: 'jhi-comment-history-detail',
  templateUrl: './comment-history-detail.component.html',
})
export class CommentHistoryDetailComponent implements OnInit {
  commentHistory: ICommentHistory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commentHistory }) => (this.commentHistory = commentHistory));
  }

  previousState(): void {
    window.history.back();
  }
}
