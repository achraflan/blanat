import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { DealHistoryComponent } from './deal-history.component';
import { DealHistoryDetailComponent } from './deal-history-detail.component';
import { DealHistoryUpdateComponent } from './deal-history-update.component';
import { DealHistoryDeleteDialogComponent } from './deal-history-delete-dialog.component';
import { dealHistoryRoute } from './deal-history.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(dealHistoryRoute)],
  declarations: [DealHistoryComponent, DealHistoryDetailComponent, DealHistoryUpdateComponent, DealHistoryDeleteDialogComponent],
  entryComponents: [DealHistoryDeleteDialogComponent],
})
export class BlanatDealHistoryModule {}
