import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class EventService {
    serverUrl: string = 'someurl/event';

    constructor(private http: HttpClient) {}

    listEvents() {
        // TODO
        // Send: Nothing
        // Recieve: List of event objects
        return this.http.get(`${this.serverUrl}/list`);
    }

    listMyEvents() {
        // TODO
        // Send: User id
        // Recieve: List of event objects
        return this.http.get(`${this.serverUrl}/list`);
    }

    create(event: any) {
        // TODO
        // Send: Event Object
        // Recieve: Event Object/failure
        return this.http.post(`${this.serverUrl}/register`, event);
    }

    edit(event: any) {
        // TODO
        // Send: Event Object
        // Recieve: Event Object/failure
        return this.http.post(`${this.serverUrl}/edit`, event);
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