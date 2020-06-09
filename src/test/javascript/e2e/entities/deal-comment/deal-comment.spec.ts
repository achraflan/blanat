import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  DealCommentComponentsPage,
  /* DealCommentDeleteDialog, */
  DealCommentUpdatePage,
} from './deal-comment.page-object';

const expect = chai.expect;

describe('DealComment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealCommentComponentsPage: DealCommentComponentsPage;
  let dealCommentUpdatePage: DealCommentUpdatePage;
  /* let dealCommentDeleteDialog: DealCommentDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DealComments', async () => {
    await navBarPage.goToEntity('deal-comment');
    dealCommentComponentsPage = new DealCommentComponentsPage();
    await browser.wait(ec.visibilityOf(dealCommentComponentsPage.title), 5000);
    expect(await dealCommentComponentsPage.getTitle()).to.eq('blanatApp.dealComment.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(dealCommentComponentsPage.entities), ec.visibilityOf(dealCommentComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DealComment page', async () => {
    await dealCommentComponentsPage.clickOnCreateButton();
    dealCommentUpdatePage = new DealCommentUpdatePage();
    expect(await dealCommentUpdatePage.getPageTitle()).to.eq('blanatApp.dealComment.home.createOrEditLabel');
    await dealCommentUpdatePage.cancel();
  });

  /* it('should create and save DealComments', async () => {
        const nbButtonsBeforeCreate = await dealCommentComponentsPage.countDeleteButtons();

        await dealCommentComponentsPage.clickOnCreateButton();

        await promise.all([
            dealCommentUpdatePage.setCommentInput('comment'),
            dealCommentUpdatePage.setDateCommentInput('2000-12-31'),
            dealCommentUpdatePage.setLikeInput('5'),
            dealCommentUpdatePage.setDislikeInput('5'),
            dealCommentUpdatePage.setDateLastModificationInput('2000-12-31'),
            dealCommentUpdatePage.assignedToSelectLastOption(),
            dealCommentUpdatePage.parentSelectLastOption(),
            dealCommentUpdatePage.dealSelectLastOption(),
        ]);

        expect(await dealCommentUpdatePage.getCommentInput()).to.eq('comment', 'Expected Comment value to be equals to comment');
        expect(await dealCommentUpdatePage.getDateCommentInput()).to.eq('2000-12-31', 'Expected dateComment value to be equals to 2000-12-31');
        const selectedIsActive = dealCommentUpdatePage.getIsActiveInput();
        if (await selectedIsActive.isSelected()) {
            await dealCommentUpdatePage.getIsActiveInput().click();
            expect(await dealCommentUpdatePage.getIsActiveInput().isSelected(), 'Expected isActive not to be selected').to.be.false;
        } else {
            await dealCommentUpdatePage.getIsActiveInput().click();
            expect(await dealCommentUpdatePage.getIsActiveInput().isSelected(), 'Expected isActive to be selected').to.be.true;
        }
        const selectedIsDeleted = dealCommentUpdatePage.getIsDeletedInput();
        if (await selectedIsDeleted.isSelected()) {
            await dealCommentUpdatePage.getIsDeletedInput().click();
            expect(await dealCommentUpdatePage.getIsDeletedInput().isSelected(), 'Expected isDeleted not to be selected').to.be.false;
        } else {
            await dealCommentUpdatePage.getIsDeletedInput().click();
            expect(await dealCommentUpdatePage.getIsDeletedInput().isSelected(), 'Expected isDeleted to be selected').to.be.true;
        }
        expect(await dealCommentUpdatePage.getLikeInput()).to.eq('5', 'Expected like value to be equals to 5');
        expect(await dealCommentUpdatePage.getDislikeInput()).to.eq('5', 'Expected dislike value to be equals to 5');
        expect(await dealCommentUpdatePage.getDateLastModificationInput()).to.eq('2000-12-31', 'Expected dateLastModification value to be equals to 2000-12-31');

        await dealCommentUpdatePage.save();
        expect(await dealCommentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await dealCommentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last DealComment', async () => {
        const nbButtonsBeforeDelete = await dealCommentComponentsPage.countDeleteButtons();
        await dealCommentComponentsPage.clickOnLastDeleteButton();

        dealCommentDeleteDialog = new DealCommentDeleteDialog();
        expect(await dealCommentDeleteDialog.getDialogTitle())
            .to.eq('blanatApp.dealComment.delete.question');
        await dealCommentDeleteDialog.clickOnConfirmButton();

        expect(await dealCommentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
