import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealTrackDetailComponent } from 'app/entities/deal-track/deal-track-detail.component';
import { DealTrack } from 'app/shared/model/deal-track.model';

describe('Component Tests', () => {
  describe('DealTrack Management Detail Component', () => {
    let comp: DealTrackDetailComponent;
    let fixture: ComponentFixture<DealTrackDetailComponent>;
    const route = ({ data: of({ dealTrack: new DealTrack(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealTrackDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DealTrackDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DealTrackDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dealTrack on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dealTrack).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
