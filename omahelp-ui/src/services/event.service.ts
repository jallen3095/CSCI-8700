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
        return this.http.post(`${this.serverUrl}/create`, event);
    }

    edit(event: any) {
        // Send: Event Object
        // Recieve: Event Object/failure
        return this.http.put(`${this.serverUrl}/edit`, event);
    }

    delete(event: any) {
        // Send: Event Object
        // Recieve: Event Object/failure
        return this.http.delete(`${this.serverUrl}/delete?eventId=${event.id}`);
    }

    like(eventId) {
        // Send: User id, event id
        // Recieve: success/failure
        const userId = this.UserService.getUser().userId;
        return this.http.post(`${this.serverUrl}/like?userId=${userId}&eventId=${eventId}`, {});
    }

    unlike(eventId) {
        // Send: User id, event id
        // Recieve: success/failure
        const userId = this.UserService.getUser().userId;
        return this.http.delete(`${this.serverUrl}/like?userId=${userId}&eventId=${eventId}`);
    }

    attend(eventId) {
        // Send: User id, event id
        // Recieve: success/failure
        const userId = this.UserService.getUser().userId;
        return this.http.post(`${this.serverUrl}/attend?userId=${userId}&eventId=${eventId}`, {});
    }

    unattend(eventId) {
        // Send: User id, event id
        // Recieve: success/failure
        const userId = this.UserService.getUser().userId;
        return this.http.delete(`${this.serverUrl}/attend?userId=${userId}&eventId=${eventId}`);
    }
}