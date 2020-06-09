import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BlanatTestModule } from '../../../test.module';
import { CommentHistoryComponent } from 'app/entities/comment-history/comment-history.component';
import { CommentHistoryService } from 'app/entities/comment-history/comment-history.service';
import { CommentHistory } from 'app/shared/model/comment-history.model';

describe('Component Tests', () => {
  describe('CommentHistory Management Component', () => {
    let comp: CommentHistoryComponent;
    let fixture: ComponentFixture<CommentHistoryComponent>;
    let service: CommentHistoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [CommentHistoryComponent],
      })
        .overrideTemplate(CommentHistoryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommentHistoryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommentHistoryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CommentHistory(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.commentHistories && comp.commentHistories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
