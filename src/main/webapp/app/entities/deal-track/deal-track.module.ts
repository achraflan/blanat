import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { DealTrackComponent } from './deal-track.component';
import { DealTrackDetailComponent } from './deal-track-detail.component';
import { DealTrackUpdateComponent } from './deal-track-update.component';
import { DealTrackDeleteDialogComponent } from './deal-track-delete-dialog.component';
import { dealTrackRoute } from './deal-track.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(dealTrackRoute)],
  declarations: [DealTrackComponent, DealTrackDetailComponent, DealTrackUpdateComponent, DealTrackDeleteDialogComponent],
  entryComponents: [DealTrackDeleteDialogComponent],
})
export class BlanatDealTrackModule {}
