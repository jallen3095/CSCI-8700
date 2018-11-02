import { Component } from '@angular/core';
import { NavController, NavParams, IonicPage } from 'ionic-angular';
import { EventDetailsPage } from '../event-details/event-details';
import { EventService } from '../../services/event.service';
import { UserService } from '../../services/user.service';

@IonicPage()
@Component({
  selector: 'page-my-events',
  templateUrl: 'my-events.html'
})
export class MyEventsPage {
  events: any[] = [];
  eventsToShow: any[] = [];
  filterText: string;

  constructor(private navCtrl: NavController, private navParams: NavParams, private EventService: EventService, private UserService: UserService) {
    // this.mockData();
    this.loadEvents();
    // console.log(this.eventsToShow, this.events);
  }

  loadEvents() {
    this.EventService.listMyEvents().subscribe((resp: any) => {
      this.events = resp;
      this.eventsToShow = this.events;
    });
  }

  goToCreateEvent() {
    this.navCtrl.push(EventDetailsPage);
  }

  filterEvents() {
    this.eventsToShow = this.filter(this.events, this.filterText);
  }

  mockData(): void {
    const event = {
      name: 'Sample Title',
      date: new Date(),
      description: 'Descriptionwordswordswords',
      address: 'Sample Street, SA SAMPLE',
      area: 'Dundee',
      tags: [
        'fun',
        'exciting',
        'rewarding'
      ],
      attendees: [
        {firstName: 'Bob', lastName: 'Dole', email: 'bdole@bedull.com'}
      ],
      organizers: []
    }

    for (let i = 0; i < 50; i++) {
      this.events.push(JSON.parse(JSON.stringify(event)));
    }
    this.events[25].name = "Example Event";
    this.events[25].description = "searchin";
    this.events[25].tags[1] = "wat";
  }

  filter(array: any[], string: string): any[] {
    let returnArr = [];
    if (string === null || string === '') {
      return array;
    }
    for (let event of array) {
      if (event.name.toUpperCase().indexOf(string.toUpperCase()) >= 0
        || event.description.toUpperCase().indexOf(string.toUpperCase()) >= 0) {
        returnArr.push(event);
        continue;
      }
      for(let tag of event.tags) {
        if (tag.toUpperCase().indexOf(string.toUpperCase()) >= 0) {
          returnArr.push(event);
          continue;
        }
      }
    }
    return returnArr;
  }
}
