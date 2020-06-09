import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { DealLocationComponent } from './deal-location.component';
import { DealLocationDetailComponent } from './deal-location-detail.component';
import { DealLocationUpdateComponent } from './deal-location-update.component';
import { DealLocationDeleteDialogComponent } from './deal-location-delete-dialog.component';
import { dealLocationRoute } from './deal-location.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(dealLocationRoute)],
  declarations: [DealLocationComponent, DealLocationDetailComponent, DealLocationUpdateComponent, DealLocationDeleteDialogComponent],
  entryComponents: [DealLocationDeleteDialogComponent],
})
export class BlanatDealLocationModule {}
