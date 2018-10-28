import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AuthService {
  authorized: boolean = false;
  serverUrl: string = 'someurl/user';

  constructor(private UserService: UserService, private http: HttpClient) {}

  authorize(email: string, password: string) {
    // TODO: Login here
    // Send: User email, user password
    // Recieve: user object/failure
    const login = {
        email: email,
        password: password
    };
    // return this.http.post(`${this.serverUrl}/login`, login);
    this.authorized = true;
  }

  isAuthorized(): boolean {
      return !!this.authorized;
  }

  logout() {
      this.authorized = false;
      this.UserService.logout();
      window.location.reload();
  }
}