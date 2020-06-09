import { element, by, ElementFinder } from 'protractor';

export class DealComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-deal div table .btn-danger'));
  title = element.all(by.css('jhi-deal div h2#page-heading span')).first();
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

export class DealUpdatePage {
  pageTitle = element(by.id('jhi-deal-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  titleInput = element(by.id('field_title'));
  descriptionInput = element(by.id('field_description'));
  typeSelect = element(by.id('field_type'));
  urlInput = element(by.id('field_url'));
  imageInput = element(by.id('field_image'));
  priceInput = element(by.id('field_price'));
  priceNormalInput = element(by.id('field_priceNormal'));
  priceShippingInput = element(by.id('field_priceShipping'));
  couponInput = element(by.id('field_coupon'));
  couponTypeSelect = element(by.id('field_couponType'));
  couponValueInput = element(by.id('field_couponValue'));
  couponPercentageInput = element(by.id('field_couponPercentage'));
  dateStartInput = element(by.id('field_dateStart'));
  dateEndInput = element(by.id('field_dateEnd'));
  datePublicationInput = element(by.id('field_datePublication'));
  viewsInput = element(by.id('field_views'));
  likeInput = element(by.id('field_like'));
  dislikeInput = element(by.id('field_dislike'));
  localInput = element(by.id('field_local'));
  statutSelect = element(by.id('field_statut'));
  isDeletedInput = element(by.id('field_isDeleted'));
  isBlockedInput = element(by.id('field_isBlocked'));

  dealLocationSelect = element(by.id('field_dealLocation'));
  assignedToSelect = element(by.id('field_assignedTo'));
  dealCategorySelect = element(by.id('field_dealCategory'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTitleInput(title: string): Promise<void> {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput(): Promise<string> {
    return await this.titleInput.getAttribute('value');
  }

  async setDescriptionInput(description: string): Promise<void> {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput(): Promise<string> {
    return await this.descriptionInput.getAttribute('value');
  }

  async setTypeSelect(type: string): Promise<void> {
    await this.typeSelect.sendKeys(type);
  }

  async getTypeSelect(): Promise<string> {
    return await this.typeSelect.element(by.css('option:checked')).getText();
  }

  async typeSelectLastOption(): Promise<void> {
    await this.typeSelect.all(by.tagName('option')).last().click();
  }

  async setUrlInput(url: string): Promise<void> {
    await this.urlInput.sendKeys(url);
  }

  async getUrlInput(): Promise<string> {
    return await this.urlInput.getAttribute('value');
  }

  async setImageInput(image: string): Promise<void> {
    await this.imageInput.sendKeys(image);
  }

  async getImageInput(): Promise<string> {
    return await this.imageInput.getAttribute('value');
  }

  async setPriceInput(price: string): Promise<void> {
    await this.priceInput.sendKeys(price);
  }

  async getPriceInput(): Promise<string> {
    return await this.priceInput.getAttribute('value');
  }

  async setPriceNormalInput(priceNormal: string): Promise<void> {
    await this.priceNormalInput.sendKeys(priceNormal);
  }

  async getPriceNormalInput(): Promise<string> {
    return await this.priceNormalInput.getAttribute('value');
  }

  async setPriceShippingInput(priceShipping: string): Promise<void> {
    await this.priceShippingInput.sendKeys(priceShipping);
  }

  async getPriceShippingInput(): Promise<string> {
    return await this.priceShippingInput.getAttribute('value');
  }

  async setCouponInput(coupon: string): Promise<void> {
    await this.couponInput.sendKeys(coupon);
  }

  async getCouponInput(): Promise<string> {
    return await this.couponInput.getAttribute('value');
  }

  async setCouponTypeSelect(couponType: string): Promise<void> {
    await this.couponTypeSelect.sendKeys(couponType);
  }

  async getCouponTypeSelect(): Promise<string> {
    return await this.couponTypeSelect.element(by.css('option:checked')).getText();
  }

  async couponTypeSelectLastOption(): Promise<void> {
    await this.couponTypeSelect.all(by.tagName('option')).last().click();
  }

  async setCouponValueInput(couponValue: string): Promise<void> {
    await this.couponValueInput.sendKeys(couponValue);
  }

  async getCouponValueInput(): Promise<string> {
    return await this.couponValueInput.getAttribute('value');
  }

  async setCouponPercentageInput(couponPercentage: string): Promise<void> {
    await this.couponPercentageInput.sendKeys(couponPercentage);
  }

  async getCouponPercentageInput(): Promise<string> {
    return await this.couponPercentageInput.getAttribute('value');
  }

  async setDateStartInput(dateStart: string): Promise<void> {
    await this.dateStartInput.sendKeys(dateStart);
  }

  async getDateStartInput(): Promise<string> {
    return await this.dateStartInput.getAttribute('value');
  }

  async setDateEndInput(dateEnd: string): Promise<void> {
    await this.dateEndInput.sendKeys(dateEnd);
  }

  async getDateEndInput(): Promise<string> {
    return await this.dateEndInput.getAttribute('value');
  }

  async setDatePublicationInput(datePublication: string): Promise<void> {
    await this.datePublicationInput.sendKeys(datePublication);
  }

  async getDatePublicationInput(): Promise<string> {
    return await this.datePublicationInput.getAttribute('value');
  }

  async setViewsInput(views: string): Promise<void> {
    await this.viewsInput.sendKeys(views);
  }

  async getViewsInput(): Promise<string> {
    return await this.viewsInput.getAttribute('value');
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

  getLocalInput(): ElementFinder {
    return this.localInput;
  }

  async setStatutSelect(statut: string): Promise<void> {
    await this.statutSelect.sendKeys(statut);
  }

  async getStatutSelect(): Promise<string> {
    return await this.statutSelect.element(by.css('option:checked')).getText();
  }

  async statutSelectLastOption(): Promise<void> {
    await this.statutSelect.all(by.tagName('option')).last().click();
  }

  getIsDeletedInput(): ElementFinder {
    return this.isDeletedInput;
  }

  getIsBlockedInput(): ElementFinder {
    return this.isBlockedInput;
  }

  async dealLocationSelectLastOption(): Promise<void> {
    await this.dealLocationSelect.all(by.tagName('option')).last().click();
  }

  async dealLocationSelectOption(option: string): Promise<void> {
    await this.dealLocationSelect.sendKeys(option);
  }

  getDealLocationSelect(): ElementFinder {
    return this.dealLocationSelect;
  }

  async getDealLocationSelectedOption(): Promise<string> {
    return await this.dealLocationSelect.element(by.css('option:checked')).getText();
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

  async dealCategorySelectLastOption(): Promise<void> {
    await this.dealCategorySelect.all(by.tagName('option')).last().click();
  }

  async dealCategorySelectOption(option: string): Promise<void> {
    await this.dealCategorySelect.sendKeys(option);
  }

  getDealCategorySelect(): ElementFinder {
    return this.dealCategorySelect;
  }

  async getDealCategorySelectedOption(): Promise<string> {
    return await this.dealCategorySelect.element(by.css('option:checked')).getText();
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

export class DealDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-deal-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-deal'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
