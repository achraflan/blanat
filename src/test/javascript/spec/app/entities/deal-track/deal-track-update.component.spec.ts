import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealTrackUpdateComponent } from 'app/entities/deal-track/deal-track-update.component';
import { DealTrackService } from 'app/entities/deal-track/deal-track.service';
import { DealTrack } from 'app/shared/model/deal-track.model';

describe('Component Tests', () => {
  describe('DealTrack Management Update Component', () => {
    let comp: DealTrackUpdateComponent;
    let fixture: ComponentFixture<DealTrackUpdateComponent>;
    let service: DealTrackService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealTrackUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DealTrackUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealTrackUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealTrackService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DealTrack(123);
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
        const entity = new DealTrack();
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
