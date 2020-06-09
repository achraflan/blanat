import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDealLocation } from 'app/shared/model/deal-location.model';
import { DealLocationService } from './deal-location.service';
import { DealLocationDeleteDialogComponent } from './deal-location-delete-dialog.component';

@Component({
  selector: 'jhi-deal-location',
  templateUrl: './deal-location.component.html',
})
export class DealLocationComponent implements OnInit, OnDestroy {
  dealLocations?: IDealLocation[];
  eventSubscriber?: Subscription;

  constructor(
    protected dealLocationService: DealLocationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.dealLocationService.query().subscribe((res: HttpResponse<IDealLocation[]>) => (this.dealLocations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDealLocations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDealLocation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDealLocations(): void {
    this.eventSubscriber = this.eventManager.subscribe('dealLocationListModification', () => this.loadAll());
  }

  delete(dealLocation: IDealLocation): void {
    const modalRef = this.modalService.open(DealLocationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dealLocation = dealLocation;
  }
}
