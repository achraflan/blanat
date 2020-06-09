import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealCategoryUpdateComponent } from 'app/entities/deal-category/deal-category-update.component';
import { DealCategoryService } from 'app/entities/deal-category/deal-category.service';
import { DealCategory } from 'app/shared/model/deal-category.model';

describe('Component Tests', () => {
  describe('DealCategory Management Update Component', () => {
    let comp: DealCategoryUpdateComponent;
    let fixture: ComponentFixture<DealCategoryUpdateComponent>;
    let service: DealCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealCategoryUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DealCategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealCategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealCategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DealCategory(123);
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
        const entity = new DealCategory();
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
