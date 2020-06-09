import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDealCategory } from 'app/shared/model/deal-category.model';

@Component({
  selector: 'jhi-deal-category-detail',
  templateUrl: './deal-category-detail.component.html',
})
export class DealCategoryDetailComponent implements OnInit {
  dealCategory: IDealCategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealCategory }) => (this.dealCategory = dealCategory));
  }

  previousState(): void {
    window.history.back();
  }
}
