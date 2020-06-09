import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDealComment } from 'app/shared/model/deal-comment.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DealCommentService } from './deal-comment.service';
import { DealCommentDeleteDialogComponent } from './deal-comment-delete-dialog.component';

@Component({
  selector: 'jhi-deal-comment',
  templateUrl: './deal-comment.component.html',
})
export class DealCommentComponent implements OnInit, OnDestroy {
  dealComments: IDealComment[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected dealCommentService: DealCommentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.dealComments = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.dealCommentService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IDealComment[]>) => this.paginateDealComments(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.dealComments = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDealComments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDealComment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDealComments(): void {
    this.eventSubscriber = this.eventManager.subscribe('dealCommentListModification', () => this.reset());
  }

  delete(dealComment: IDealComment): void {
    const modalRef = this.modalService.open(DealCommentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dealComment = dealComment;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDealComments(data: IDealComment[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.dealComments.push(data[i]);
      }
    }
  }
}
