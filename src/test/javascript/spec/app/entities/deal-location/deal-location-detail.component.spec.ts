import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealLocationDetailComponent } from 'app/entities/deal-location/deal-location-detail.component';
import { DealLocation } from 'app/shared/model/deal-location.model';

describe('Component Tests', () => {
  describe('DealLocation Management Detail Component', () => {
    let comp: DealLocationDetailComponent;
    let fixture: ComponentFixture<DealLocationDetailComponent>;
    const route = ({ data: of({ dealLocation: new DealLocation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealLocationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DealLocationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DealLocationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dealLocation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dealLocation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
