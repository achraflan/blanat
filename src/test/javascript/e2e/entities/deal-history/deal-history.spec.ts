import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  DealHistoryComponentsPage,
  /* DealHistoryDeleteDialog, */
  DealHistoryUpdatePage,
} from './deal-history.page-object';

const expect = chai.expect;

describe('DealHistory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealHistoryComponentsPage: DealHistoryComponentsPage;
  let dealHistoryUpdatePage: DealHistoryUpdatePage;
  /* let dealHistoryDeleteDialog: DealHistoryDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DealHistories', async () => {
    await navBarPage.goToEntity('deal-history');
    dealHistoryComponentsPage = new DealHistoryComponentsPage();
    await browser.wait(ec.visibilityOf(dealHistoryComponentsPage.title), 5000);
    expect(await dealHistoryComponentsPage.getTitle()).to.eq('blanatApp.dealHistory.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(dealHistoryComponentsPage.entities), ec.visibilityOf(dealHistoryComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DealHistory page', async () => {
    await dealHistoryComponentsPage.clickOnCreateButton();
    dealHistoryUpdatePage = new DealHistoryUpdatePage();
    expect(await dealHistoryUpdatePage.getPageTitle()).to.eq('blanatApp.dealHistory.home.createOrEditLabel');
    await dealHistoryUpdatePage.cancel();
  });

  /* it('should create and save DealHistories', async () => {
        const nbButtonsBeforeCreate = await dealHistoryComponentsPage.countDeleteButtons();

        await dealHistoryComponentsPage.clickOnCreateButton();

        await promise.all([
            dealHistoryUpdatePage.setAttributNameInput('attributName'),
            dealHistoryUpdatePage.setAttributLastValueInput('attributLastValue'),
            dealHistoryUpdatePage.setDateModificationInput('2000-12-31'),
            dealHistoryUpdatePage.dealSelectLastOption(),
        ]);

        expect(await dealHistoryUpdatePage.getAttributNameInput()).to.eq('attributName', 'Expected AttributName value to be equals to attributName');
        expect(await dealHistoryUpdatePage.getAttributLastValueInput()).to.eq('attributLastValue', 'Expected AttributLastValue value to be equals to attributLastValue');
        expect(await dealHistoryUpdatePage.getDateModificationInput()).to.eq('2000-12-31', 'Expected dateModification value to be equals to 2000-12-31');

        await dealHistoryUpdatePage.save();
        expect(await dealHistoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await dealHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last DealHistory', async () => {
        const nbButtonsBeforeDelete = await dealHistoryComponentsPage.countDeleteButtons();
        await dealHistoryComponentsPage.clickOnLastDeleteButton();

        dealHistoryDeleteDialog = new DealHistoryDeleteDialog();
        expect(await dealHistoryDeleteDialog.getDialogTitle())
            .to.eq('blanatApp.dealHistory.delete.question');
        await dealHistoryDeleteDialog.clickOnConfirmButton();

        expect(await dealHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
