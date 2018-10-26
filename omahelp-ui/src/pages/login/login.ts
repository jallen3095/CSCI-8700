import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AuthService } from '../../services/auth.service';
import { HomePage } from '../home/home';

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
  loginData: { username: string, password: string } = {username: null, password: null};

  constructor(public navCtrl: NavController, public navParams: NavParams, private AuthService: AuthService) {
    this.setLoginMode();
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
    // TODO: this will be a subscribe.
    console.log(this.loginData);
    this.AuthService.authorize(this.loginData);
    if (this.AuthService.isAuthorized()) {
      this.navCtrl.setRoot(HomePage);
    }
  }

  setLoginMode() {
    this.mode = 'login';
  }

  loginMode() {
    return this.mode === 'login';
  }
}
