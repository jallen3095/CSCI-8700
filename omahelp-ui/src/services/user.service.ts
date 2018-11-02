import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class UserService {
    user: any;
    serverUrl: string = 'https://omahelp.herokuapp.com/user';

    constructor(private http: HttpClient) {
        this.user = JSON.parse(sessionStorage.getItem('user'));
        console.log('user on reload: ' + this.user);
    }

    register(userData: any) {
        // TODO
        // Send: User Object
        // Recieve: User Object/failure
        return this.http.post(`${this.serverUrl}/register`, userData);
    }

    editAccount(userData: any) {
        // TODO
        // Send: User Object
        // Recieve: User Object/failure
        return this.http.post(`${this.serverUrl}/edit`, userData);
    }

    getUser() {
        return this.user;
    }

    login(user: any) {
        this.user = user;
        sessionStorage.setItem('user', JSON.stringify(this.user));
    }

    logout() {
        this.user = null;
        sessionStorage.removeItem('user');
    }
}