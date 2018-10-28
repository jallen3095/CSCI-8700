import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class UserService {
    user: any;
    serverUrl: string = 'someurl/user';

    constructor(private http: HttpClient) {}

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

    login(user: any) {
        this.user = user;
    }

    logout() {
        this.user = null;
    }
}