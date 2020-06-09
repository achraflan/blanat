import { element, by, ElementFinder } from 'protractor';

export class DealCommentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-deal-comment div table .btn-danger'));
  title = element.all(by.css('jhi-deal-comment div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class DealCommentUpdatePage {
  pageTitle = element(by.id('jhi-deal-comment-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  commentInput = element(by.id('field_comment'));
  dateCommentInput = element(by.id('field_dateComment'));
  isActiveInput = element(by.id('field_isActive'));
  isDeletedInput = element(by.id('field_isDeleted'));
  likeInput = element(by.id('field_like'));
  dislikeInput = element(by.id('field_dislike'));
  dateLastModificationInput = element(by.id('field_dateLastModification'));

  assignedToSelect = element(by.id('field_assignedTo'));
  parentSelect = element(by.id('field_parent'));
  dealSelect = element(by.id('field_deal'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCommentInput(comment: string): Promise<void> {
    await this.commentInput.sendKeys(comment);
  }

  async getCommentInput(): Promise<string> {
    return await this.commentInput.getAttribute('value');
  }

  async setDateCommentInput(dateComment: string): Promise<void> {
    await this.dateCommentInput.sendKeys(dateComment);
  }

  async getDateCommentInput(): Promise<string> {
    return await this.dateCommentInput.getAttribute('value');
  }

  getIsActiveInput(): ElementFinder {
    return this.isActiveInput;
  }

  getIsDeletedInput(): ElementFinder {
    return this.isDeletedInput;
  }

  async setLikeInput(like: string): Promise<void> {
    await this.likeInput.sendKeys(like);
  }

  async getLikeInput(): Promise<string> {
    return await this.likeInput.getAttribute('value');
  }

  async setDislikeInput(dislike: string): Promise<void> {
    await this.dislikeInput.sendKeys(dislike);
  }

  async getDislikeInput(): Promise<string> {
    return await this.dislikeInput.getAttribute('value');
  }

  async setDateLastModificationInput(dateLastModification: string): Promise<void> {
    await this.dateLastModificationInput.sendKeys(dateLastModification);
  }

  async getDateLastModificationInput(): Promise<string> {
    return await this.dateLastModificationInput.getAttribute('value');
  }

  async assignedToSelectLastOption(): Promise<void> {
    await this.assignedToSelect.all(by.tagName('option')).last().click();
  }

  async assignedToSelectOption(option: string): Promise<void> {
    await this.assignedToSelect.sendKeys(option);
  }

  getAssignedToSelect(): ElementFinder {
    return this.assignedToSelect;
  }

  async getAssignedToSelectedOption(): Promise<string> {
    return await this.assignedToSelect.element(by.css('option:checked')).getText();
  }

  async parentSelectLastOption(): Promise<void> {
    await this.parentSelect.all(by.tagName('option')).last().click();
  }

  async parentSelectOption(option: string): Promise<void> {
    await this.parentSelect.sendKeys(option);
  }

  getParentSelect(): ElementFinder {
    return this.parentSelect;
  }

  async getParentSelectedOption(): Promise<string> {
    return await this.parentSelect.element(by.css('option:checked')).getText();
  }

  async dealSelectLastOption(): Promise<void> {
    await this.dealSelect.all(by.tagName('option')).last().click();
  }

  async dealSelectOption(option: string): Promise<void> {
    await this.dealSelect.sendKeys(option);
  }

  getDealSelect(): ElementFinder {
    return this.dealSelect;
  }

  async getDealSelectedOption(): Promise<string> {
    return await this.dealSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class DealCommentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-dealComment-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-dealComment'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
