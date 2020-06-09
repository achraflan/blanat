import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DealCategoryComponentsPage, DealCategoryDeleteDialog, DealCategoryUpdatePage } from './deal-category.page-object';

const expect = chai.expect;

describe('DealCategory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealCategoryComponentsPage: DealCategoryComponentsPage;
  let dealCategoryUpdatePage: DealCategoryUpdatePage;
  let dealCategoryDeleteDialog: DealCategoryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DealCategories', async () => {
    await navBarPage.goToEntity('deal-category');
    dealCategoryComponentsPage = new DealCategoryComponentsPage();
    await browser.wait(ec.visibilityOf(dealCategoryComponentsPage.title), 5000);
    expect(await dealCategoryComponentsPage.getTitle()).to.eq('blanatApp.dealCategory.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(dealCategoryComponentsPage.entities), ec.visibilityOf(dealCategoryComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DealCategory page', async () => {
    await dealCategoryComponentsPage.clickOnCreateButton();
    dealCategoryUpdatePage = new DealCategoryUpdatePage();
    expect(await dealCategoryUpdatePage.getPageTitle()).to.eq('blanatApp.dealCategory.home.createOrEditLabel');
    await dealCategoryUpdatePage.cancel();
  });

  it('should create and save DealCategories', async () => {
    const nbButtonsBeforeCreate = await dealCategoryComponentsPage.countDeleteButtons();

    await dealCategoryComponentsPage.clickOnCreateButton();

    await promise.all([
      dealCategoryUpdatePage.setNameInput('name'),
      dealCategoryUpdatePage.setDescriptionInput('description'),
      dealCategoryUpdatePage.categorySelectLastOption(),
    ]);

    expect(await dealCategoryUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await dealCategoryUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    const selectedIsParent = dealCategoryUpdatePage.getIsParentInput();
    if (await selectedIsParent.isSelected()) {
      await dealCategoryUpdatePage.getIsParentInput().click();
      expect(await dealCategoryUpdatePage.getIsParentInput().isSelected(), 'Expected isParent not to be selected').to.be.false;
    } else {
      await dealCategoryUpdatePage.getIsParentInput().click();
      expect(await dealCategoryUpdatePage.getIsParentInput().isSelected(), 'Expected isParent to be selected').to.be.true;
    }

    await dealCategoryUpdatePage.save();
    expect(await dealCategoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await dealCategoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DealCategory', async () => {
    const nbButtonsBeforeDelete = await dealCategoryComponentsPage.countDeleteButtons();
    await dealCategoryComponentsPage.clickOnLastDeleteButton();

    dealCategoryDeleteDialog = new DealCategoryDeleteDialog();
    expect(await dealCategoryDeleteDialog.getDialogTitle()).to.eq('blanatApp.dealCategory.delete.question');
    await dealCategoryDeleteDialog.clickOnConfirmButton();

    expect(await dealCategoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
