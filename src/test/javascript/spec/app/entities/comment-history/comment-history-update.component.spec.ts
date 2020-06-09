import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { CommentHistoryUpdateComponent } from 'app/entities/comment-history/comment-history-update.component';
import { CommentHistoryService } from 'app/entities/comment-history/comment-history.service';
import { CommentHistory } from 'app/shared/model/comment-history.model';

describe('Component Tests', () => {
  describe('CommentHistory Management Update Component', () => {
    let comp: CommentHistoryUpdateComponent;
    let fixture: ComponentFixture<CommentHistoryUpdateComponent>;
    let service: CommentHistoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [CommentHistoryUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CommentHistoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommentHistoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommentHistoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommentHistory(123);
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
        const entity = new CommentHistory();
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
