import { Component, Input, OnInit } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { EventDetailsPage } from '../../pages/event-details/event-details';
import { EventService } from '../../services/event.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'event-card',
  templateUrl: 'event-card.html'
})
export class EventCardComponent implements OnInit {
  @Input('event') event: any;
  @Input('detailed') detailed: boolean;

  constructor(private navCtrl: NavController, private UserService: UserService, private EventService: EventService) {

  }

  ngOnInit(): void {
    console.log(this.event);
    this.clearNulls(); 
  }

  goToDetails() {
    this.navCtrl.push(EventDetailsPage, {event: this.event});
  }

  attend() {
    console.log('attend()');
    if (this.isAttending()) {
      this.EventService.unattend(this.event.id).subscribe(() => {
        console.log('called service');
      }, () => {});
    } else {
      this.EventService.attend(this.event.id).subscribe(() => {
        console.log('called service');
      }, () => {});
    }
  }

  like() {
    console.log('like()');
    if (this.isInterested()) {
      this.EventService.unlike(this.event.id).subscribe(() => {
        console.log('called service');
      }, () => {});
    } else {
      this.EventService.like(this.event.id).subscribe(() => {
        console.log('called service');
      }, () => {});
    }
  }

  isAttending(): boolean {
    if (this.event.attendees) {
      const email = this.UserService.getUser().email;
      for (let user of this.event.attendees) {
        if (user && user.email == email) {
          return true;
        }
      }
    }
    return false;
  }

  isInterested(): boolean {
    if (!this.event.interested) {
      return false;
    }
    const email = this.UserService.getUser().email;
    for (let user of this.event.interested) {
      if (user && user.email == email) {
        return true;
      }
    }
    return false;
  }

  clearNulls() {
    if (this.event.organizers) {
      console.log(JSON.stringify(this.event.organizers));
      for(let i = 0; i < this.event.organizers.length; i++) {
        if (this.event.organizers[i] == null) {
          delete this.event.organizers[i];
        }
      }
    }
    if (this.event.attendees) {
      console.log(JSON.stringify(this.event.attendees));
      for(let i = 0; i < this.event.attendees.length; i++) {
        if (this.event.attendees[i] == null) {
          delete this.event.attendees[i];
        }
      }
    }
  }
}
