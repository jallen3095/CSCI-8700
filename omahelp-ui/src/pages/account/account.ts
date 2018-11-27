import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { UserService } from '../../services/user.service';

/**
 * Generated class for the AccountPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-account',
  templateUrl: 'account.html',
})
export class AccountPage {
  mode: string;
  user: any = {};
  edit: any = {};

  constructor(public navCtrl: NavController, public navParams: NavParams, private UserService: UserService) {
    this.setViewMode();
    this.user = this.UserService.getUser();
  }

  submit() {
    // TODO
    this.UserService.editAccount(this.edit).subscribe(() => {}, () => {});
    this.user = this.edit;
  }

  mockUser() {
    this.user = {
      firstName: "Person",
      lastName: "McGuy",
      email: "contact@here.com"
    }
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AccountPage');
  }

  viewMode() {
    return this.mode === 'view';
  }

  setViewMode() {
    this.edit = {};
    this.mode = 'view';
  }

  editMode() {
    return this.mode === 'edit';
  }

  setEditMode() {
    this.edit = JSON.parse(JSON.stringify(this.user));
    this.mode = 'edit';
  }

}
