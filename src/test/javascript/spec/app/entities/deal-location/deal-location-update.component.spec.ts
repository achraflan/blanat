import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealLocationUpdateComponent } from 'app/entities/deal-location/deal-location-update.component';
import { DealLocationService } from 'app/entities/deal-location/deal-location.service';
import { DealLocation } from 'app/shared/model/deal-location.model';

describe('Component Tests', () => {
  describe('DealLocation Management Update Component', () => {
    let comp: DealLocationUpdateComponent;
    let fixture: ComponentFixture<DealLocationUpdateComponent>;
    let service: DealLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealLocationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DealLocationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealLocationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealLocationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DealLocation(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DealLocation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
