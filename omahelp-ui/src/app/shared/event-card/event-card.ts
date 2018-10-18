import { Component, Input } from '@angular/core';

@Component({
  selector: 'event-card',
  templateUrl: 'event-card.html'
})
export class EventCardComponent {
    @Input('event') event: any;

    constructor() {
      console.log(this.event);
     }
}
