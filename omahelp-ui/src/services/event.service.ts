import { Injectable } from '@angular/core';

@Injectable()
export class EventService {

  constructor() {}

  listEvents() {
    // TODO
    // Send: Nothing
    // Recieve: List of event objects
  }

  listMyEvents() {
    // TODO
    // Send: User id
    // Recieve: List of event objects
  }

  create(event: any) {
    // TODO
    // Send: Event Object
    // Recieve: Event Object/failure
  }

  edit(event: any) {
    // TODO
    // Send: Event Object
    // Recieve: Event Object/failure
  }

  like() {
    // TODO
    // Send: User id, event id
    // Recieve: success/failure
  }

  unlike() {
    // TODO
    // Send: User id, event id
    // Recieve: success/failure
  }

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