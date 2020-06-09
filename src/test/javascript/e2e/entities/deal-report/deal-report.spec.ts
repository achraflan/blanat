import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  DealReportComponentsPage,
  /* DealReportDeleteDialog, */
  DealReportUpdatePage,
} from './deal-report.page-object';

const expect = chai.expect;

describe('DealReport e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealReportComponentsPage: DealReportComponentsPage;
  let dealReportUpdatePage: DealReportUpdatePage;
  /* let dealReportDeleteDialog: DealReportDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DealReports', async () => {
    await navBarPage.goToEntity('deal-report');
    dealReportComponentsPage = new DealReportComponentsPage();
    await browser.wait(ec.visibilityOf(dealReportComponentsPage.title), 5000);
    expect(await dealReportComponentsPage.getTitle()).to.eq('blanatApp.dealReport.home.title');
    await browser.wait(ec.or(ec.visibilityOf(dealReportComponentsPage.entities), ec.visibilityOf(dealReportComponentsPage.noResult)), 1000);
  });

  it('should load create DealReport page', async () => {
    await dealReportComponentsPage.clickOnCreateButton();
    dealReportUpdatePage = new DealReportUpdatePage();
    expect(await dealReportUpdatePage.getPageTitle()).to.eq('blanatApp.dealReport.home.createOrEditLabel');
    await dealReportUpdatePage.cancel();
  });

  /* it('should create and save DealReports', async () => {
        const nbButtonsBeforeCreate = await dealReportComponentsPage.countDeleteButtons();

        await dealReportComponentsPage.clickOnCreateButton();

        await promise.all([
            dealReportUpdatePage.setReasonInput('reason'),
            dealReportUpdatePage.setDateReportInput('2000-12-31'),
            dealReportUpdatePage.setDateCloseInput('2000-12-31'),
            dealReportUpdatePage.assignedToSelectLastOption(),
            dealReportUpdatePage.dealSelectLastOption(),
        ]);

        expect(await dealReportUpdatePage.getReasonInput()).to.eq('reason', 'Expected Reason value to be equals to reason');
        expect(await dealReportUpdatePage.getDateReportInput()).to.eq('2000-12-31', 'Expected dateReport value to be equals to 2000-12-31');
        expect(await dealReportUpdatePage.getDateCloseInput()).to.eq('2000-12-31', 'Expected dateClose value to be equals to 2000-12-31');
        const selectedIsValid = dealReportUpdatePage.getIsValidInput();
        if (await selectedIsValid.isSelected()) {
            await dealReportUpdatePage.getIsValidInput().click();
            expect(await dealReportUpdatePage.getIsValidInput().isSelected(), 'Expected isValid not to be selected').to.be.false;
        } else {
            await dealReportUpdatePage.getIsValidInput().click();
            expect(await dealReportUpdatePage.getIsValidInput().isSelected(), 'Expected isValid to be selected').to.be.true;
        }

        await dealReportUpdatePage.save();
        expect(await dealReportUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await dealReportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last DealReport', async () => {
        const nbButtonsBeforeDelete = await dealReportComponentsPage.countDeleteButtons();
        await dealReportComponentsPage.clickOnLastDeleteButton();

        dealReportDeleteDialog = new DealReportDeleteDialog();
        expect(await dealReportDeleteDialog.getDialogTitle())
            .to.eq('blanatApp.dealReport.delete.question');
        await dealReportDeleteDialog.clickOnConfirmButton();

        expect(await dealReportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
