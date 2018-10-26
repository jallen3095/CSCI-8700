import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {
  authorized: boolean = false;

  constructor() {}

  authorize(userData: any) {
      // TODO: Login here
      this.authorized = true;
  }

  isAuthorized(): boolean {
      return !!this.authorized;
  }
}