import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { DealUserComponent } from './deal-user.component';
import { DealUserDetailComponent } from './deal-user-detail.component';
import { DealUserUpdateComponent } from './deal-user-update.component';
import { DealUserDeleteDialogComponent } from './deal-user-delete-dialog.component';
import { dealUserRoute } from './deal-user.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(dealUserRoute)],
  declarations: [DealUserComponent, DealUserDetailComponent, DealUserUpdateComponent, DealUserDeleteDialogComponent],
  entryComponents: [DealUserDeleteDialogComponent],
})
export class BlanatDealUserModule {}
