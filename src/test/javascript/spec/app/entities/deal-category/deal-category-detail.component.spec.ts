import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealCategoryDetailComponent } from 'app/entities/deal-category/deal-category-detail.component';
import { DealCategory } from 'app/shared/model/deal-category.model';

describe('Component Tests', () => {
  describe('DealCategory Management Detail Component', () => {
    let comp: DealCategoryDetailComponent;
    let fixture: ComponentFixture<DealCategoryDetailComponent>;
    const route = ({ data: of({ dealCategory: new DealCategory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealCategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DealCategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DealCategoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dealCategory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dealCategory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
