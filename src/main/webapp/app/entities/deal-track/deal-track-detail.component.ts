import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDealTrack } from 'app/shared/model/deal-track.model';

@Component({
  selector: 'jhi-deal-track-detail',
  templateUrl: './deal-track-detail.component.html',
})
export class DealTrackDetailComponent implements OnInit {
  dealTrack: IDealTrack | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealTrack }) => (this.dealTrack = dealTrack));
  }

  previousState(): void {
    window.history.back();
  }
}
