import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  DealComponentsPage,
  /* DealDeleteDialog, */
  DealUpdatePage,
} from './deal.page-object';

const expect = chai.expect;

describe('Deal e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealComponentsPage: DealComponentsPage;
  let dealUpdatePage: DealUpdatePage;
  /* let dealDeleteDialog: DealDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Deals', async () => {
    await navBarPage.goToEntity('deal');
    dealComponentsPage = new DealComponentsPage();
    await browser.wait(ec.visibilityOf(dealComponentsPage.title), 5000);
    expect(await dealComponentsPage.getTitle()).to.eq('blanatApp.deal.home.title');
    await browser.wait(ec.or(ec.visibilityOf(dealComponentsPage.entities), ec.visibilityOf(dealComponentsPage.noResult)), 1000);
  });

  it('should load create Deal page', async () => {
    await dealComponentsPage.clickOnCreateButton();
    dealUpdatePage = new DealUpdatePage();
    expect(await dealUpdatePage.getPageTitle()).to.eq('blanatApp.deal.home.createOrEditLabel');
    await dealUpdatePage.cancel();
  });

  /* it('should create and save Deals', async () => {
        const nbButtonsBeforeCreate = await dealComponentsPage.countDeleteButtons();

        await dealComponentsPage.clickOnCreateButton();

        await promise.all([
            dealUpdatePage.setTitleInput('title'),
            dealUpdatePage.setDescriptionInput('description'),
            dealUpdatePage.typeSelectLastOption(),
            dealUpdatePage.setUrlInput('url'),
            dealUpdatePage.setImageInput('image'),
            dealUpdatePage.setPriceInput('5'),
            dealUpdatePage.setPriceNormalInput('5'),
            dealUpdatePage.setPriceShippingInput('5'),
            dealUpdatePage.setCouponInput('coupon'),
            dealUpdatePage.couponTypeSelectLastOption(),
            dealUpdatePage.setCouponValueInput('5'),
            dealUpdatePage.setCouponPercentageInput('5'),
            dealUpdatePage.setDateStartInput('2000-12-31'),
            dealUpdatePage.setDateEndInput('2000-12-31'),
            dealUpdatePage.setDatePublicationInput('2000-12-31'),
            dealUpdatePage.setViewsInput('5'),
            dealUpdatePage.setLikeInput('5'),
            dealUpdatePage.setDislikeInput('5'),
            dealUpdatePage.statutSelectLastOption(),
            dealUpdatePage.dealLocationSelectLastOption(),
            dealUpdatePage.assignedToSelectLastOption(),
            // dealUpdatePage.dealCategorySelectLastOption(),
        ]);

        expect(await dealUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
        expect(await dealUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
        expect(await dealUpdatePage.getUrlInput()).to.eq('url', 'Expected Url value to be equals to url');
        expect(await dealUpdatePage.getImageInput()).to.eq('image', 'Expected Image value to be equals to image');
        expect(await dealUpdatePage.getPriceInput()).to.eq('5', 'Expected price value to be equals to 5');
        expect(await dealUpdatePage.getPriceNormalInput()).to.eq('5', 'Expected priceNormal value to be equals to 5');
        expect(await dealUpdatePage.getPriceShippingInput()).to.eq('5', 'Expected priceShipping value to be equals to 5');
        expect(await dealUpdatePage.getCouponInput()).to.eq('coupon', 'Expected Coupon value to be equals to coupon');
        expect(await dealUpdatePage.getCouponValueInput()).to.eq('5', 'Expected couponValue value to be equals to 5');
        expect(await dealUpdatePage.getCouponPercentageInput()).to.eq('5', 'Expected couponPercentage value to be equals to 5');
        expect(await dealUpdatePage.getDateStartInput()).to.eq('2000-12-31', 'Expected dateStart value to be equals to 2000-12-31');
        expect(await dealUpdatePage.getDateEndInput()).to.eq('2000-12-31', 'Expected dateEnd value to be equals to 2000-12-31');
        expect(await dealUpdatePage.getDatePublicationInput()).to.eq('2000-12-31', 'Expected datePublication value to be equals to 2000-12-31');
        expect(await dealUpdatePage.getViewsInput()).to.eq('5', 'Expected views value to be equals to 5');
        expect(await dealUpdatePage.getLikeInput()).to.eq('5', 'Expected like value to be equals to 5');
        expect(await dealUpdatePage.getDislikeInput()).to.eq('5', 'Expected dislike value to be equals to 5');
        const selectedLocal = dealUpdatePage.getLocalInput();
        if (await selectedLocal.isSelected()) {
            await dealUpdatePage.getLocalInput().click();
            expect(await dealUpdatePage.getLocalInput().isSelected(), 'Expected local not to be selected').to.be.false;
        } else {
            await dealUpdatePage.getLocalInput().click();
            expect(await dealUpdatePage.getLocalInput().isSelected(), 'Expected local to be selected').to.be.true;
        }
        const selectedIsDeleted = dealUpdatePage.getIsDeletedInput();
        if (await selectedIsDeleted.isSelected()) {
            await dealUpdatePage.getIsDeletedInput().click();
            expect(await dealUpdatePage.getIsDeletedInput().isSelected(), 'Expected isDeleted not to be selected').to.be.false;
        } else {
            await dealUpdatePage.getIsDeletedInput().click();
            expect(await dealUpdatePage.getIsDeletedInput().isSelected(), 'Expected isDeleted to be selected').to.be.true;
        }
        const selectedIsBlocked = dealUpdatePage.getIsBlockedInput();
        if (await selectedIsBlocked.isSelected()) {
            await dealUpdatePage.getIsBlockedInput().click();
            expect(await dealUpdatePage.getIsBlockedInput().isSelected(), 'Expected isBlocked not to be selected').to.be.false;
        } else {
            await dealUpdatePage.getIsBlockedInput().click();
            expect(await dealUpdatePage.getIsBlockedInput().isSelected(), 'Expected isBlocked to be selected').to.be.true;
        }

        await dealUpdatePage.save();
        expect(await dealUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await dealComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last Deal', async () => {
        const nbButtonsBeforeDelete = await dealComponentsPage.countDeleteButtons();
        await dealComponentsPage.clickOnLastDeleteButton();

        dealDeleteDialog = new DealDeleteDialog();
        expect(await dealDeleteDialog.getDialogTitle())
            .to.eq('blanatApp.deal.delete.question');
        await dealDeleteDialog.clickOnConfirmButton();

        expect(await dealComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
