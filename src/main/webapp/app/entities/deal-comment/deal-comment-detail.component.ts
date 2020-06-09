import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDealComment } from 'app/shared/model/deal-comment.model';

@Component({
  selector: 'jhi-deal-comment-detail',
  templateUrl: './deal-comment-detail.component.html',
})
export class DealCommentDetailComponent implements OnInit {
  dealComment: IDealComment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealComment }) => (this.dealComment = dealComment));
  }

  previousState(): void {
    window.history.back();
  }
}
