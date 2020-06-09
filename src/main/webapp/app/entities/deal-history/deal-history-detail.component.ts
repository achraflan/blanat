import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDealHistory } from 'app/shared/model/deal-history.model';

@Component({
  selector: 'jhi-deal-history-detail',
  templateUrl: './deal-history-detail.component.html',
})
export class DealHistoryDetailComponent implements OnInit {
  dealHistory: IDealHistory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealHistory }) => (this.dealHistory = dealHistory));
  }

  previousState(): void {
    window.history.back();
  }
}
