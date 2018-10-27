import { Injectable } from '@angular/core';

@Injectable()
export class UserService {
  user: any;

  constructor() {}

  register(userData: any) {
    // TODO
    // Send: User Object
    // Recieve: User Object/failure
  }

  editAccount(userData: any) {
    // TODO
    // Send: User Object
    // Recieve: User Object/failure
  }

  login(user: any) {
      this.user = user;
  }

  logout() {
      this.user = null;
  }
}