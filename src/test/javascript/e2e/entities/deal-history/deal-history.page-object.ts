import { element, by, ElementFinder } from 'protractor';

export class DealHistoryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-deal-history div table .btn-danger'));
  title = element.all(by.css('jhi-deal-history div h2#page-heading span')).first();
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

export class DealHistoryUpdatePage {
  pageTitle = element(by.id('jhi-deal-history-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  attributNameInput = element(by.id('field_attributName'));
  attributLastValueInput = element(by.id('field_attributLastValue'));
  dateModificationInput = element(by.id('field_dateModification'));

  dealSelect = element(by.id('field_deal'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAttributNameInput(attributName: string): Promise<void> {
    await this.attributNameInput.sendKeys(attributName);
  }

  async getAttributNameInput(): Promise<string> {
    return await this.attributNameInput.getAttribute('value');
  }

  async setAttributLastValueInput(attributLastValue: string): Promise<void> {
    await this.attributLastValueInput.sendKeys(attributLastValue);
  }

  async getAttributLastValueInput(): Promise<string> {
    return await this.attributLastValueInput.getAttribute('value');
  }

  async setDateModificationInput(dateModification: string): Promise<void> {
    await this.dateModificationInput.sendKeys(dateModification);
  }

  async getDateModificationInput(): Promise<string> {
    return await this.dateModificationInput.getAttribute('value');
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

export class DealHistoryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-dealHistory-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-dealHistory'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
