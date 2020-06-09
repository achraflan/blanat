import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDealUser } from 'app/shared/model/deal-user.model';

@Component({
  selector: 'jhi-deal-user-detail',
  templateUrl: './deal-user-detail.component.html',
})
export class DealUserDetailComponent implements OnInit {
  dealUser: IDealUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealUser }) => (this.dealUser = dealUser));
  }

  previousState(): void {
    window.history.back();
  }
}
