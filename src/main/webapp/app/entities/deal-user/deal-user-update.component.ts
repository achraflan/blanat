import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDealUser, DealUser } from 'app/shared/model/deal-user.model';
import { DealUserService } from './deal-user.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IDeal } from 'app/shared/model/deal.model';
import { DealService } from 'app/entities/deal/deal.service';

type SelectableEntity = IUser | IDeal;

@Component({
  selector: 'jhi-deal-user-update',
  templateUrl: './deal-user-update.component.html',
})
export class DealUserUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  deals: IDeal[] = [];
  birthDayDp: any;

  editForm = this.fb.group({
    id: [],
    gender: [null, [Validators.required]],
    phone: [null, [Validators.pattern('^(?:0|\\(?\\+212\\)?\\s?|00212\\s?)[1-79](?:[\\.\\-\\s]?\\d\\d)')]],
    address: [],
    city: [],
    birthDay: [],
    comment: [],
    notification: [],
    reporting: [],
    emailNotification: [],
    message: [],
    userId: [null, Validators.required],
    dealSaveds: [],
  });

  constructor(
    protected dealUserService: DealUserService,
    protected userService: UserService,
    protected dealService: DealService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dealUser }) => {
      this.updateForm(dealUser);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.dealService.query().subscribe((res: HttpResponse<IDeal[]>) => (this.deals = res.body || []));
    });
  }

  updateForm(dealUser: IDealUser): void {
    this.editForm.patchValue({
      id: dealUser.id,
      gender: dealUser.gender,
      phone: dealUser.phone,
      address: dealUser.address,
      city: dealUser.city,
      birthDay: dealUser.birthDay,
      comment: dealUser.comment,
      notification: dealUser.notification,
      reporting: dealUser.reporting,
      emailNotification: dealUser.emailNotification,
      message: dealUser.message,
      userId: dealUser.userId,
      dealSaveds: dealUser.dealSaveds,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dealUser = this.createFromForm();
    if (dealUser.id !== undefined) {
      this.subscribeToSaveResponse(this.dealUserService.update(dealUser));
    } else {
      this.subscribeToSaveResponse(this.dealUserService.create(dealUser));
    }
  }

  private createFromForm(): IDealUser {
    return {
      ...new DealUser(),
      id: this.editForm.get(['id'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      address: this.editForm.get(['address'])!.value,
      city: this.editForm.get(['city'])!.value,
      birthDay: this.editForm.get(['birthDay'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      notification: this.editForm.get(['notification'])!.value,
      reporting: this.editForm.get(['reporting'])!.value,
      emailNotification: this.editForm.get(['emailNotification'])!.value,
      message: this.editForm.get(['message'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      dealSaveds: this.editForm.get(['dealSaveds'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDealUser>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IDeal[], option: IDeal): IDeal {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
