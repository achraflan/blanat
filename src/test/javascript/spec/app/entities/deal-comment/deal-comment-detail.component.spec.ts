import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealCommentDetailComponent } from 'app/entities/deal-comment/deal-comment-detail.component';
import { DealComment } from 'app/shared/model/deal-comment.model';

describe('Component Tests', () => {
  describe('DealComment Management Detail Component', () => {
    let comp: DealCommentDetailComponent;
    let fixture: ComponentFixture<DealCommentDetailComponent>;
    const route = ({ data: of({ dealComment: new DealComment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealCommentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DealCommentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DealCommentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dealComment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dealComment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
