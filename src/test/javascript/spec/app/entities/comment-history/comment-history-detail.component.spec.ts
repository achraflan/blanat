import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { CommentHistoryDetailComponent } from 'app/entities/comment-history/comment-history-detail.component';
import { CommentHistory } from 'app/shared/model/comment-history.model';

describe('Component Tests', () => {
  describe('CommentHistory Management Detail Component', () => {
    let comp: CommentHistoryDetailComponent;
    let fixture: ComponentFixture<CommentHistoryDetailComponent>;
    const route = ({ data: of({ commentHistory: new CommentHistory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [CommentHistoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CommentHistoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommentHistoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load commentHistory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commentHistory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
