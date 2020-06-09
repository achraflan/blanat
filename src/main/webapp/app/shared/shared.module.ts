import { NgModule } from '@angular/core';
import { BlanatSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { DescriptionPipe } from './pipe/descriptionPipe';
import { UserNamePipe } from './pipe/userNamePipe';

@NgModule({
  imports: [BlanatSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    DescriptionPipe,
    UserNamePipe,
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    BlanatSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    DescriptionPipe,
    UserNamePipe,
  ],
})
export class BlanatSharedModule {}
