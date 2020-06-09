import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDealReport } from 'app/shared/model/deal-report.model';

@Component({
  selector: 'jhi-deal-report-detail',
  templateUrl: './deal-report-detail.component.html',
})
export class DealReportDetailComponent implements OnInit {
  dealReport: IDealReport | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealReport }) => (this.dealReport = dealReport));
  }

  previousState(): void {
    window.history.back();
  }
}
