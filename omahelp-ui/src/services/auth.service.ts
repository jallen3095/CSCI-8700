import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { HttpClient } from '@angular/common/http';
import { NavController, ToastController } from 'ionic-angular';
import { HomePage } from '../pages/home/home';


@Injectable()
export class AuthService {
  authorized: boolean = false;
  serverUrl: string = 'https://omahelp.herokuapp.com/user';

  constructor(private UserService: UserService, private http: HttpClient, private toastCtrl: ToastController) {
    this.authorized = sessionStorage.getItem('authorized') === 'true';
    console.log('Auth after reload: ' + sessionStorage.getItem('authorized'));
  }

  authorize(loginData:  { email: string, password: string }, nav: NavController) {
    // TODO: Login here
    // Send: User email, user password
    // Recieve: user object/failure
    this.http.get(`${this.serverUrl}/login?email=${loginData.email}&password=${loginData.password}`).subscribe((resp: any) => {
      console.log(resp);
      if (resp.userId) {
        this.UserService.login(resp);
        this.authorized = true;
        sessionStorage.setItem('authorized', 'true');
        console.log('Auth after login: ' + sessionStorage.getItem('authorized'));
        nav.setRoot(HomePage)
      }
    }, () => {
      // TODO: Fail appropriately
      // this.authorized = true;
      //   nav.setRoot(HomePage)
      //   sessionStorage.setItem('authorized', 'true');
      //   console.log('Auth after login: ' + sessionStorage.getItem('authorized'));
      const toast = this.toastCtrl.create({
        message: 'Incorrect username or password!',
        duration: 3000
      })
    });
  }

  isAuthorized(): boolean {
    return !!this.authorized;
  }

  logout() {
    sessionStorage.removeItem('authorized');
    this.authorized = false;
    this.UserService.logout();
    window.location.reload();
  }
}