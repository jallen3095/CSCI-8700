import { Injectable } from '@angular/core';

@Injectable()
export class UserService {
  user: any;

  constructor() {}

  register(userData: any) {
    // TODO
  }

  editAccount(userData: any) {
    // TODO
  }

  login(user: any) {
      this.user = user;
  }

  logout() {
      this.user = null;
  }
}