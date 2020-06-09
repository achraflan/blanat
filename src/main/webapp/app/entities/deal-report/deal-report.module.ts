import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { DealReportComponent } from './deal-report.component';
import { DealReportDetailComponent } from './deal-report-detail.component';
import { DealReportUpdateComponent } from './deal-report-update.component';
import { DealReportDeleteDialogComponent } from './deal-report-delete-dialog.component';
import { dealReportRoute } from './deal-report.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(dealReportRoute)],
  declarations: [DealReportComponent, DealReportDetailComponent, DealReportUpdateComponent, DealReportDeleteDialogComponent],
  entryComponents: [DealReportDeleteDialogComponent],
})
export class BlanatDealReportModule {}
