import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DealLocationComponentsPage, DealLocationDeleteDialog, DealLocationUpdatePage } from './deal-location.page-object';

const expect = chai.expect;

describe('DealLocation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealLocationComponentsPage: DealLocationComponentsPage;
  let dealLocationUpdatePage: DealLocationUpdatePage;
  let dealLocationDeleteDialog: DealLocationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DealLocations', async () => {
    await navBarPage.goToEntity('deal-location');
    dealLocationComponentsPage = new DealLocationComponentsPage();
    await browser.wait(ec.visibilityOf(dealLocationComponentsPage.title), 5000);
    expect(await dealLocationComponentsPage.getTitle()).to.eq('blanatApp.dealLocation.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(dealLocationComponentsPage.entities), ec.visibilityOf(dealLocationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DealLocation page', async () => {
    await dealLocationComponentsPage.clickOnCreateButton();
    dealLocationUpdatePage = new DealLocationUpdatePage();
    expect(await dealLocationUpdatePage.getPageTitle()).to.eq('blanatApp.dealLocation.home.createOrEditLabel');
    await dealLocationUpdatePage.cancel();
  });

  it('should create and save DealLocations', async () => {
    const nbButtonsBeforeCreate = await dealLocationComponentsPage.countDeleteButtons();

    await dealLocationComponentsPage.clickOnCreateButton();

    await promise.all([dealLocationUpdatePage.setCityInput('city'), dealLocationUpdatePage.setCountryInput('country')]);

    expect(await dealLocationUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await dealLocationUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');

    await dealLocationUpdatePage.save();
    expect(await dealLocationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await dealLocationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DealLocation', async () => {
    const nbButtonsBeforeDelete = await dealLocationComponentsPage.countDeleteButtons();
    await dealLocationComponentsPage.clickOnLastDeleteButton();

    dealLocationDeleteDialog = new DealLocationDeleteDialog();
    expect(await dealLocationDeleteDialog.getDialogTitle()).to.eq('blanatApp.dealLocation.delete.question');
    await dealLocationDeleteDialog.clickOnConfirmButton();

    expect(await dealLocationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
