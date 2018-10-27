import { Injectable } from '@angular/core';
import { UserService } from './user.service';

@Injectable()
export class AuthService {
  authorized: boolean = false;

  constructor(private UserService: UserService) {}

  authorize(userData: any) {
      // TODO: Login here
      this.authorized = true;
  }

  isAuthorized(): boolean {
      return !!this.authorized;
  }

  logout() {
      this.authorized = false;
      this.UserService.logout();
      console.log("reloading");
      window.location.reload();
  }
}