import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealHistoryDetailComponent } from 'app/entities/deal-history/deal-history-detail.component';
import { DealHistory } from 'app/shared/model/deal-history.model';

describe('Component Tests', () => {
  describe('DealHistory Management Detail Component', () => {
    let comp: DealHistoryDetailComponent;
    let fixture: ComponentFixture<DealHistoryDetailComponent>;
    const route = ({ data: of({ dealHistory: new DealHistory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealHistoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DealHistoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DealHistoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dealHistory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dealHistory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
