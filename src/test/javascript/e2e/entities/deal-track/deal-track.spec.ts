import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  DealTrackComponentsPage,
  /* DealTrackDeleteDialog, */
  DealTrackUpdatePage,
} from './deal-track.page-object';

const expect = chai.expect;

describe('DealTrack e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealTrackComponentsPage: DealTrackComponentsPage;
  let dealTrackUpdatePage: DealTrackUpdatePage;
  /* let dealTrackDeleteDialog: DealTrackDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DealTracks', async () => {
    await navBarPage.goToEntity('deal-track');
    dealTrackComponentsPage = new DealTrackComponentsPage();
    await browser.wait(ec.visibilityOf(dealTrackComponentsPage.title), 5000);
    expect(await dealTrackComponentsPage.getTitle()).to.eq('blanatApp.dealTrack.home.title');
    await browser.wait(ec.or(ec.visibilityOf(dealTrackComponentsPage.entities), ec.visibilityOf(dealTrackComponentsPage.noResult)), 1000);
  });

  it('should load create DealTrack page', async () => {
    await dealTrackComponentsPage.clickOnCreateButton();
    dealTrackUpdatePage = new DealTrackUpdatePage();
    expect(await dealTrackUpdatePage.getPageTitle()).to.eq('blanatApp.dealTrack.home.createOrEditLabel');
    await dealTrackUpdatePage.cancel();
  });

  /* it('should create and save DealTracks', async () => {
        const nbButtonsBeforeCreate = await dealTrackComponentsPage.countDeleteButtons();

        await dealTrackComponentsPage.clickOnCreateButton();

        await promise.all([
            dealTrackUpdatePage.setIpLocalisationInput('ipLocalisation'),
            dealTrackUpdatePage.setLocalisationInput('localisation'),
            dealTrackUpdatePage.dealSelectLastOption(),
        ]);

        expect(await dealTrackUpdatePage.getIpLocalisationInput()).to.eq('ipLocalisation', 'Expected IpLocalisation value to be equals to ipLocalisation');
        expect(await dealTrackUpdatePage.getLocalisationInput()).to.eq('localisation', 'Expected Localisation value to be equals to localisation');
        const selectedIsAuthentified = dealTrackUpdatePage.getIsAuthentifiedInput();
        if (await selectedIsAuthentified.isSelected()) {
            await dealTrackUpdatePage.getIsAuthentifiedInput().click();
            expect(await dealTrackUpdatePage.getIsAuthentifiedInput().isSelected(), 'Expected isAuthentified not to be selected').to.be.false;
        } else {
            await dealTrackUpdatePage.getIsAuthentifiedInput().click();
            expect(await dealTrackUpdatePage.getIsAuthentifiedInput().isSelected(), 'Expected isAuthentified to be selected').to.be.true;
        }

        await dealTrackUpdatePage.save();
        expect(await dealTrackUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await dealTrackComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last DealTrack', async () => {
        const nbButtonsBeforeDelete = await dealTrackComponentsPage.countDeleteButtons();
        await dealTrackComponentsPage.clickOnLastDeleteButton();

        dealTrackDeleteDialog = new DealTrackDeleteDialog();
        expect(await dealTrackDeleteDialog.getDialogTitle())
            .to.eq('blanatApp.dealTrack.delete.question');
        await dealTrackDeleteDialog.clickOnConfirmButton();

        expect(await dealTrackComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
