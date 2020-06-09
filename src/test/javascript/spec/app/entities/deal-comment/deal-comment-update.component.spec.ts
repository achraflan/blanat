import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealCommentUpdateComponent } from 'app/entities/deal-comment/deal-comment-update.component';
import { DealCommentService } from 'app/entities/deal-comment/deal-comment.service';
import { DealComment } from 'app/shared/model/deal-comment.model';

describe('Component Tests', () => {
  describe('DealComment Management Update Component', () => {
    let comp: DealCommentUpdateComponent;
    let fixture: ComponentFixture<DealCommentUpdateComponent>;
    let service: DealCommentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealCommentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DealCommentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealCommentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealCommentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DealComment(123);
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
        const entity = new DealComment();
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
