import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDealLocation } from 'app/shared/model/deal-location.model';

@Component({
  selector: 'jhi-deal-location-detail',
  templateUrl: './deal-location-detail.component.html',
})
export class DealLocationDetailComponent implements OnInit {
  dealLocation: IDealLocation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealLocation }) => (this.dealLocation = dealLocation));
  }

  previousState(): void {
    window.history.back();
  }
}
