import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the InputPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
  mode: string;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.mode = 'login';
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

  register() {
    // TODO
  }

  setRegisterMode() {
    this.mode = 'register';
  }

  registerMode() {
    return this.mode === 'register';
  }

  login() {
    // TODO
  }

  setLoginMode() {
    this.mode = 'login';
  }

  loginMode() {
    return this.mode === 'login';
  }
}
