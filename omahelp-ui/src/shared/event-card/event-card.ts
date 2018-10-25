import { Component, Input } from '@angular/core';

@Component({
  selector: 'event-card',
  templateUrl: 'event-card.html'
})
export class EventCardComponent {
    @Input('event') event: any;
    @Input('detailed') detailed: boolean;

    constructor() {
      console.log(this.event);
    }
}
