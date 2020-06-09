import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealHistoryUpdateComponent } from 'app/entities/deal-history/deal-history-update.component';
import { DealHistoryService } from 'app/entities/deal-history/deal-history.service';
import { DealHistory } from 'app/shared/model/deal-history.model';

describe('Component Tests', () => {
  describe('DealHistory Management Update Component', () => {
    let comp: DealHistoryUpdateComponent;
    let fixture: ComponentFixture<DealHistoryUpdateComponent>;
    let service: DealHistoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealHistoryUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DealHistoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealHistoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealHistoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DealHistory(123);
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
        const entity = new DealHistory();
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
