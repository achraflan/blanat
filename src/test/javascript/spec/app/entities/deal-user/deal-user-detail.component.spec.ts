import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealUserDetailComponent } from 'app/entities/deal-user/deal-user-detail.component';
import { DealUser } from 'app/shared/model/deal-user.model';

describe('Component Tests', () => {
  describe('DealUser Management Detail Component', () => {
    let comp: DealUserDetailComponent;
    let fixture: ComponentFixture<DealUserDetailComponent>;
    const route = ({ data: of({ dealUser: new DealUser(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealUserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DealUserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DealUserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dealUser on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dealUser).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
