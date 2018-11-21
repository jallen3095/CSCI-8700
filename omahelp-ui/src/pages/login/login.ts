import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ToastController } from 'ionic-angular';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
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
  loginData: { email: string, password: string } = {email: null, password: null};
  registrationData: any = {};

  constructor(public navCtrl: NavController, public navParams: NavParams, private AuthService: AuthService, private UserService: UserService, private toastController: ToastController) {
    this.setLoginMode();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

  register() {
    // TODO
    if (this.checkPasswords()) {
      delete this.registrationData.passwordConfirmation;
      this.UserService.register(this.registrationData).subscribe(() => {
        this.AuthService.authorize({email: this.registrationData.email, password: this.registrationData.password}, this.navCtrl);
      },() => {
        this.AuthService.authorize({email: this.registrationData.email, password: this.registrationData.password}, this.navCtrl);
      });
    }
  }

  checkPasswords() {
    if (this.registrationData.password != this.registrationData.passwordConfirmation) {
      const toast = this.toastController.create({
        message: 'Passwords must match!',
        duration: 3000
      });
      toast.present();
      return false;
    } else if (this.registrationData.password.length < 6) {
      const toast = this.toastController.create({
        message: 'Passwords must be at least 6 characters!',
        duration: 3000
      });
      toast.present();
      return false;
    }
    return true;
  }

  setRegisterMode() {
    this.mode = 'register';
  }

  registerMode() {
    return this.mode === 'register';
  }

  login() {
    // TODO
    console.log(this.loginData);
    this.AuthService.authorize(this.loginData, this.navCtrl);
  }

  setLoginMode() {
    this.mode = 'login';
  }

  loginMode() {
    return this.mode === 'login';
  }
}
