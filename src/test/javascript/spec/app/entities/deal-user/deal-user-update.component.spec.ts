import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BlanatTestModule } from '../../../test.module';
import { DealUserUpdateComponent } from 'app/entities/deal-user/deal-user-update.component';
import { DealUserService } from 'app/entities/deal-user/deal-user.service';
import { DealUser } from 'app/shared/model/deal-user.model';

describe('Component Tests', () => {
  describe('DealUser Management Update Component', () => {
    let comp: DealUserUpdateComponent;
    let fixture: ComponentFixture<DealUserUpdateComponent>;
    let service: DealUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BlanatTestModule],
        declarations: [DealUserUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DealUserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DealUserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DealUserService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DealUser(123);
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
        const entity = new DealUser();
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
