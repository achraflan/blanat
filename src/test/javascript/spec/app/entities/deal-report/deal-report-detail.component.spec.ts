import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealReportDetailComponent } from 'app/entities/deal-report/deal-report-detail.component';
import { DealReport } from 'app/shared/model/deal-report.model';

describe('Component Tests', () => {
  describe('DealReport Management Detail Component', () => {
    let comp: DealReportDetailComponent;
    let fixture: ComponentFixture<DealReportDetailComponent>;
    const route = ({ data: of({ dealReport: new DealReport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealReportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DealReportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DealReportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dealReport on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dealReport).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
