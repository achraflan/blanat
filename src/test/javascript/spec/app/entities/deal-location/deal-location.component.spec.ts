import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BlanatTestModule } from '../../../test.module';
import { DealLocationComponent } from 'app/entities/deal-location/deal-location.component';
import { DealLocationService } from 'app/entities/deal-location/deal-location.service';
import { DealLocation } from 'app/shared/model/deal-location.model';

describe('Component Tests', () => {
  describe('DealLocation Management Component', () => {
    let comp: DealLocationComponent;
    let fixture: ComponentFixture<DealLocationComponent>;
    let service: DealLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealLocationComponent],
      })
        .overrideTemplate(DealLocationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealLocationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealLocationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DealLocation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dealLocations && comp.dealLocations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
