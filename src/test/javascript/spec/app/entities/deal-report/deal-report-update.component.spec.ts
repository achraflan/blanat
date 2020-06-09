import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealReportUpdateComponent } from 'app/entities/deal-report/deal-report-update.component';
import { DealReportService } from 'app/entities/deal-report/deal-report.service';
import { DealReport } from 'app/shared/model/deal-report.model';

describe('Component Tests', () => {
  describe('DealReport Management Update Component', () => {
    let comp: DealReportUpdateComponent;
    let fixture: ComponentFixture<DealReportUpdateComponent>;
    let service: DealReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealReportUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DealReportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealReportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealReportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DealReport(123);
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
        const entity = new DealReport();
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
