import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlanatSharedModule } from 'app/shared/shared.module';
import { DealCategoryComponent } from './deal-category.component';
import { DealCategoryDetailComponent } from './deal-category-detail.component';
import { DealCategoryUpdateComponent } from './deal-category-update.component';
import { DealCategoryDeleteDialogComponent } from './deal-category-delete-dialog.component';
import { dealCategoryRoute } from './deal-category.route';

@NgModule({
  imports: [BlanatSharedModule, RouterModule.forChild(dealCategoryRoute)],
  declarations: [DealCategoryComponent, DealCategoryDetailComponent, DealCategoryUpdateComponent, DealCategoryDeleteDialogComponent],
  entryComponents: [DealCategoryDeleteDialogComponent],
})
export class BlanatDealCategoryModule {}
