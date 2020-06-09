import { element, by, ElementFinder } from 'protractor';

export class DealReportComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-deal-report div table .btn-danger'));
  title = element.all(by.css('jhi-deal-report div h2#page-heading span')).first();
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

export class DealReportUpdatePage {
  pageTitle = element(by.id('jhi-deal-report-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  reasonInput = element(by.id('field_reason'));
  dateReportInput = element(by.id('field_dateReport'));
  dateCloseInput = element(by.id('field_dateClose'));
  isValidInput = element(by.id('field_isValid'));

  assignedToSelect = element(by.id('field_assignedTo'));
  dealSelect = element(by.id('field_deal'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setReasonInput(reason: string): Promise<void> {
    await this.reasonInput.sendKeys(reason);
  }

  async getReasonInput(): Promise<string> {
    return await this.reasonInput.getAttribute('value');
  }

  async setDateReportInput(dateReport: string): Promise<void> {
    await this.dateReportInput.sendKeys(dateReport);
  }

  async getDateReportInput(): Promise<string> {
    return await this.dateReportInput.getAttribute('value');
  }

  async setDateCloseInput(dateClose: string): Promise<void> {
    await this.dateCloseInput.sendKeys(dateClose);
  }

  async getDateCloseInput(): Promise<string> {
    return await this.dateCloseInput.getAttribute('value');
  }

  getIsValidInput(): ElementFinder {
    return this.isValidInput;
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

export class DealReportDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-dealReport-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-dealReport'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
