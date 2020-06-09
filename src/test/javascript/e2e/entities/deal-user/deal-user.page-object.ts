import { element, by, ElementFinder } from 'protractor';

export class DealUserComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-deal-user div table .btn-danger'));
  title = element.all(by.css('jhi-deal-user div h2#page-heading span')).first();
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

export class DealUserUpdatePage {
  pageTitle = element(by.id('jhi-deal-user-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  genderSelect = element(by.id('field_gender'));
  phoneInput = element(by.id('field_phone'));
  addressInput = element(by.id('field_address'));
  cityInput = element(by.id('field_city'));
  birthDayInput = element(by.id('field_birthDay'));
  commentInput = element(by.id('field_comment'));
  notificationInput = element(by.id('field_notification'));
  reportingInput = element(by.id('field_reporting'));
  emailNotificationInput = element(by.id('field_emailNotification'));
  messageInput = element(by.id('field_message'));

  userSelect = element(by.id('field_user'));
  dealSavedSelect = element(by.id('field_dealSaved'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setGenderSelect(gender: string): Promise<void> {
    await this.genderSelect.sendKeys(gender);
  }

  async getGenderSelect(): Promise<string> {
    return await this.genderSelect.element(by.css('option:checked')).getText();
  }

  async genderSelectLastOption(): Promise<void> {
    await this.genderSelect.all(by.tagName('option')).last().click();
  }

  async setPhoneInput(phone: string): Promise<void> {
    await this.phoneInput.sendKeys(phone);
  }

  async getPhoneInput(): Promise<string> {
    return await this.phoneInput.getAttribute('value');
  }

  async setAddressInput(address: string): Promise<void> {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput(): Promise<string> {
    return await this.addressInput.getAttribute('value');
  }

  async setCityInput(city: string): Promise<void> {
    await this.cityInput.sendKeys(city);
  }

  async getCityInput(): Promise<string> {
    return await this.cityInput.getAttribute('value');
  }

  async setBirthDayInput(birthDay: string): Promise<void> {
    await this.birthDayInput.sendKeys(birthDay);
  }

  async getBirthDayInput(): Promise<string> {
    return await this.birthDayInput.getAttribute('value');
  }

  getCommentInput(): ElementFinder {
    return this.commentInput;
  }

  getNotificationInput(): ElementFinder {
    return this.notificationInput;
  }

  getReportingInput(): ElementFinder {
    return this.reportingInput;
  }

  getEmailNotificationInput(): ElementFinder {
    return this.emailNotificationInput;
  }

  getMessageInput(): ElementFinder {
    return this.messageInput;
  }

  async userSelectLastOption(): Promise<void> {
    await this.userSelect.all(by.tagName('option')).last().click();
  }

  async userSelectOption(option: string): Promise<void> {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect(): ElementFinder {
    return this.userSelect;
  }

  async getUserSelectedOption(): Promise<string> {
    return await this.userSelect.element(by.css('option:checked')).getText();
  }

  async dealSavedSelectLastOption(): Promise<void> {
    await this.dealSavedSelect.all(by.tagName('option')).last().click();
  }

  async dealSavedSelectOption(option: string): Promise<void> {
    await this.dealSavedSelect.sendKeys(option);
  }

  getDealSavedSelect(): ElementFinder {
    return this.dealSavedSelect;
  }

  async getDealSavedSelectedOption(): Promise<string> {
    return await this.dealSavedSelect.element(by.css('option:checked')).getText();
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

export class DealUserDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-dealUser-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-dealUser'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
