import { element, by, ElementFinder } from 'protractor';

export class DealTrackComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-deal-track div table .btn-danger'));
  title = element.all(by.css('jhi-deal-track div h2#page-heading span')).first();
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

export class DealTrackUpdatePage {
  pageTitle = element(by.id('jhi-deal-track-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  ipLocalisationInput = element(by.id('field_ipLocalisation'));
  localisationInput = element(by.id('field_localisation'));
  isAuthentifiedInput = element(by.id('field_isAuthentified'));

  dealSelect = element(by.id('field_deal'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIpLocalisationInput(ipLocalisation: string): Promise<void> {
    await this.ipLocalisationInput.sendKeys(ipLocalisation);
  }

  async getIpLocalisationInput(): Promise<string> {
    return await this.ipLocalisationInput.getAttribute('value');
  }

  async setLocalisationInput(localisation: string): Promise<void> {
    await this.localisationInput.sendKeys(localisation);
  }

  async getLocalisationInput(): Promise<string> {
    return await this.localisationInput.getAttribute('value');
  }

  getIsAuthentifiedInput(): ElementFinder {
    return this.isAuthentifiedInput;
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

export class DealTrackDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-dealTrack-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-dealTrack'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
