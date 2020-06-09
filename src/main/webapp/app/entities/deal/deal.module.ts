import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { DealComponent } from './deal.component';
import { DealDetailComponent } from './deal-detail.component';
import { DealUpdateComponent } from './deal-update.component';
import { DealDeleteDialogComponent } from './deal-delete-dialog.component';
import { dealRoute } from './deal.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(dealRoute)],
  declarations: [DealComponent, DealDetailComponent, DealUpdateComponent, DealDeleteDialogComponent],
  entryComponents: [DealDeleteDialogComponent],
})
export class BlanatDealModule {}
