import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AccountPage } from './account';
import { MatFormFieldModule } from '@angular/material';

@NgModule({
  declarations: [
   AccountPage,
  ],
  imports: [
    IonicPageModule.forChild(AccountPage),
    MatFormFieldModule
  ],
})
export class AccountPageModule {}
