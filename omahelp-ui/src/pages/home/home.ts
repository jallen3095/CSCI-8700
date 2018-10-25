import { Component } from '@angular/core';
import { NavController, IonicPage, NavParams } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  events: any[] = [];
  eventsToShow: any[] = [];
  filterText: string;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.mockData();
    this.eventsToShow = this.events;
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
