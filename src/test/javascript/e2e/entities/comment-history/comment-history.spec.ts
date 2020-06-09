import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  CommentHistoryComponentsPage,
  /* CommentHistoryDeleteDialog, */
  CommentHistoryUpdatePage,
} from './comment-history.page-object';

const expect = chai.expect;

describe('CommentHistory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let commentHistoryComponentsPage: CommentHistoryComponentsPage;
  let commentHistoryUpdatePage: CommentHistoryUpdatePage;
  /* let commentHistoryDeleteDialog: CommentHistoryDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CommentHistories', async () => {
    await navBarPage.goToEntity('comment-history');
    commentHistoryComponentsPage = new CommentHistoryComponentsPage();
    await browser.wait(ec.visibilityOf(commentHistoryComponentsPage.title), 5000);
    expect(await commentHistoryComponentsPage.getTitle()).to.eq('blanatApp.commentHistory.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(commentHistoryComponentsPage.entities), ec.visibilityOf(commentHistoryComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CommentHistory page', async () => {
    await commentHistoryComponentsPage.clickOnCreateButton();
    commentHistoryUpdatePage = new CommentHistoryUpdatePage();
    expect(await commentHistoryUpdatePage.getPageTitle()).to.eq('blanatApp.commentHistory.home.createOrEditLabel');
    await commentHistoryUpdatePage.cancel();
  });

  /* it('should create and save CommentHistories', async () => {
        const nbButtonsBeforeCreate = await commentHistoryComponentsPage.countDeleteButtons();

        await commentHistoryComponentsPage.clickOnCreateButton();

        await promise.all([
            commentHistoryUpdatePage.setCommentInput('comment'),
            commentHistoryUpdatePage.setDateModificationInput('2000-12-31'),
            commentHistoryUpdatePage.dealCommentSelectLastOption(),
        ]);

        expect(await commentHistoryUpdatePage.getCommentInput()).to.eq('comment', 'Expected Comment value to be equals to comment');
        expect(await commentHistoryUpdatePage.getDateModificationInput()).to.eq('2000-12-31', 'Expected dateModification value to be equals to 2000-12-31');

        await commentHistoryUpdatePage.save();
        expect(await commentHistoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await commentHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last CommentHistory', async () => {
        const nbButtonsBeforeDelete = await commentHistoryComponentsPage.countDeleteButtons();
        await commentHistoryComponentsPage.clickOnLastDeleteButton();

        commentHistoryDeleteDialog = new CommentHistoryDeleteDialog();
        expect(await commentHistoryDeleteDialog.getDialogTitle())
            .to.eq('blanatApp.commentHistory.delete.question');
        await commentHistoryDeleteDialog.clickOnConfirmButton();

        expect(await commentHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
