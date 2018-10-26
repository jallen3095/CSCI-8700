import { Component, Input, OnInit } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { EventDetailsPage } from '../../pages/event-details/event-details';

@Component({
  selector: 'event-card',
  templateUrl: 'event-card.html'
})
export class EventCardComponent implements OnInit {
    @Input('event') event: any;
    @Input('detailed') detailed: boolean;

    constructor(private navCtrl: NavController) {
      
    }

    ngOnInit(): void {
      console.log(this.event);
    }

    goToDetails() {
      this.navCtrl.push(EventDetailsPage, {id: 123456});
    }

    attend() {
      // TODO
    }
  
    like() {
      // TODO
    }
}
