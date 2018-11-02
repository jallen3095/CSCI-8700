import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from './user.service';

@Injectable()
export class EventService {
    serverUrl: string = 'https://omahelp.herokuapp.com/event';

    constructor(private http: HttpClient, private UserService: UserService) {}

    listEvents() {
        // Send: Nothing
        // Recieve: List of event objects
        return this.http.get(`${this.serverUrl}/list`);
    }

    listMyEvents() {
        // Send: User id
        // Recieve: List of event objects
        const userId = this.UserService.getUser().userId;
        return this.http.get(`${this.serverUrl}/list/${userId}`);
    }

    create(event: any) {
        // Send: Event Object
        // Recieve: Event Object/failure
        return this.http.post(`${this.serverUrl}/register`, event);
    }

    edit(event: any) {
        // Send: Event Object
        // Recieve: Event Object/failure
        return this.http.put(`${this.serverUrl}/edit`, event);
    }

    // Don't think we can do these without adding a list of interested users to the event object
    // like() {
    //     // TODO
    //     // Send: User id, event id
    //     // Recieve: success/failure
    // }

    // unlike() {
    //     // TODO
    //     // Send: User id, event id
    //     // Recieve: success/failure
    // }

    attend() {
        // TODO
        // Send: User id, event id
        // Recieve: success/failure
    }

    unattend() {
        // TODO
        // Send: User id, event id
        // Recieve: success/failure
    }
}