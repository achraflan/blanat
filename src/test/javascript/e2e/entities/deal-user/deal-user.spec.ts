import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  DealUserComponentsPage,
  /* DealUserDeleteDialog, */
  DealUserUpdatePage,
} from './deal-user.page-object';

const expect = chai.expect;

describe('DealUser e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealUserComponentsPage: DealUserComponentsPage;
  let dealUserUpdatePage: DealUserUpdatePage;
  /* let dealUserDeleteDialog: DealUserDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DealUsers', async () => {
    await navBarPage.goToEntity('deal-user');
    dealUserComponentsPage = new DealUserComponentsPage();
    await browser.wait(ec.visibilityOf(dealUserComponentsPage.title), 5000);
    expect(await dealUserComponentsPage.getTitle()).to.eq('blanatApp.dealUser.home.title');
    await browser.wait(ec.or(ec.visibilityOf(dealUserComponentsPage.entities), ec.visibilityOf(dealUserComponentsPage.noResult)), 1000);
  });

  it('should load create DealUser page', async () => {
    await dealUserComponentsPage.clickOnCreateButton();
    dealUserUpdatePage = new DealUserUpdatePage();
    expect(await dealUserUpdatePage.getPageTitle()).to.eq('blanatApp.dealUser.home.createOrEditLabel');
    await dealUserUpdatePage.cancel();
  });

  /* it('should create and save DealUsers', async () => {
        const nbButtonsBeforeCreate = await dealUserComponentsPage.countDeleteButtons();

        await dealUserComponentsPage.clickOnCreateButton();

        await promise.all([
            dealUserUpdatePage.genderSelectLastOption(),
            dealUserUpdatePage.setPhoneInput('+212340'),
            dealUserUpdatePage.setAddressInput('address'),
            dealUserUpdatePage.setCityInput('city'),
            dealUserUpdatePage.setBirthDayInput('2000-12-31'),
            dealUserUpdatePage.userSelectLastOption(),
            // dealUserUpdatePage.dealSavedSelectLastOption(),
        ]);

        expect(await dealUserUpdatePage.getPhoneInput()).to.eq('+212340', 'Expected Phone value to be equals to +212340');
        expect(await dealUserUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');
        expect(await dealUserUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
        expect(await dealUserUpdatePage.getBirthDayInput()).to.eq('2000-12-31', 'Expected birthDay value to be equals to 2000-12-31');
        const selectedComment = dealUserUpdatePage.getCommentInput();
        if (await selectedComment.isSelected()) {
            await dealUserUpdatePage.getCommentInput().click();
            expect(await dealUserUpdatePage.getCommentInput().isSelected(), 'Expected comment not to be selected').to.be.false;
        } else {
            await dealUserUpdatePage.getCommentInput().click();
            expect(await dealUserUpdatePage.getCommentInput().isSelected(), 'Expected comment to be selected').to.be.true;
        }
        const selectedNotification = dealUserUpdatePage.getNotificationInput();
        if (await selectedNotification.isSelected()) {
            await dealUserUpdatePage.getNotificationInput().click();
            expect(await dealUserUpdatePage.getNotificationInput().isSelected(), 'Expected notification not to be selected').to.be.false;
        } else {
            await dealUserUpdatePage.getNotificationInput().click();
            expect(await dealUserUpdatePage.getNotificationInput().isSelected(), 'Expected notification to be selected').to.be.true;
        }
        const selectedReporting = dealUserUpdatePage.getReportingInput();
        if (await selectedReporting.isSelected()) {
            await dealUserUpdatePage.getReportingInput().click();
            expect(await dealUserUpdatePage.getReportingInput().isSelected(), 'Expected reporting not to be selected').to.be.false;
        } else {
            await dealUserUpdatePage.getReportingInput().click();
            expect(await dealUserUpdatePage.getReportingInput().isSelected(), 'Expected reporting to be selected').to.be.true;
        }
        const selectedEmailNotification = dealUserUpdatePage.getEmailNotificationInput();
        if (await selectedEmailNotification.isSelected()) {
            await dealUserUpdatePage.getEmailNotificationInput().click();
            expect(await dealUserUpdatePage.getEmailNotificationInput().isSelected(), 'Expected emailNotification not to be selected').to.be.false;
        } else {
            await dealUserUpdatePage.getEmailNotificationInput().click();
            expect(await dealUserUpdatePage.getEmailNotificationInput().isSelected(), 'Expected emailNotification to be selected').to.be.true;
        }
        const selectedMessage = dealUserUpdatePage.getMessageInput();
        if (await selectedMessage.isSelected()) {
            await dealUserUpdatePage.getMessageInput().click();
            expect(await dealUserUpdatePage.getMessageInput().isSelected(), 'Expected message not to be selected').to.be.false;
        } else {
            await dealUserUpdatePage.getMessageInput().click();
            expect(await dealUserUpdatePage.getMessageInput().isSelected(), 'Expected message to be selected').to.be.true;
        }

        await dealUserUpdatePage.save();
        expect(await dealUserUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await dealUserComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last DealUser', async () => {
        const nbButtonsBeforeDelete = await dealUserComponentsPage.countDeleteButtons();
        await dealUserComponentsPage.clickOnLastDeleteButton();

        dealUserDeleteDialog = new DealUserDeleteDialog();
        expect(await dealUserDeleteDialog.getDialogTitle())
            .to.eq('blanatApp.dealUser.delete.question');
        await dealUserDeleteDialog.clickOnConfirmButton();

        expect(await dealUserComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
