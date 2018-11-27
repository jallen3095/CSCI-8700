import { Component } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { MatChipInputEvent } from '@angular/material';
import { EventService } from '../../services/event.service';
import { UserService } from '../../services/user.service';

/**
 * Generated class for the EventDetails page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-event-details',
  templateUrl: 'event-details.html',
})
export class EventDetailsPage {
  private mode: string;
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  event: any;
  edit: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, private EventService: EventService, private UserService: UserService) {
    this.event = this.navParams.data.event;
    console.log(this.event);
    if (!this.event) {
      this.event = {name: '', date: '', description: '', location: '', area: '', attendees: [], organizers: [], tags: []};
      this.edit = JSON.parse(JSON.stringify(this.event));
      this.setCreateMode();
    } else {
      // this.mockEvent();
      this.setViewMode();
    }
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad EventDetails');
  }

  viewMode() {
    return this.mode === 'view';
  }

  setViewMode() {
    this.mode = 'view';
  }

  submit() {
    // TODO
    if (this.event.id) {
      // update event
      console.log(JSON.stringify(this.edit));
      this.EventService.edit(this.edit).subscribe(() => {
         this.event = JSON.parse(JSON.stringify(this.edit));
         console.log(this.edit, this.event);
         this.setViewMode();
      });
    } else {
      // create event
      this.edit.organizers.push(this.UserService.getUser());
      this.event = JSON.parse(JSON.stringify(this.edit));
      this.EventService.create(this.edit).subscribe(() => {
        this.event = JSON.parse(JSON.stringify(this.edit));
        this.setViewMode();
      });
    }
    this.setViewMode();
  }

  delete() {
    this.EventService.delete(this.event).subscribe(() => { });
    window.location.reload();
  }

  canEdit() {
    if (this.UserService.getUser().admin) {
      return true;
    }
    if (!this.event.organizers) {
      return false;
    }
    const email = this.UserService.getUser().email;
    for (let org of this.event.organizers) {
      if (org.email === email) {
        return true;
      }
    }
    return false;
  }

  editMode() {
    this.edit = JSON.parse(JSON.stringify(this.event));
    return this.mode === 'edit';
  }

  setEditMode() {
    this.edit = JSON.parse(JSON.stringify(this.event));
    this.mode = 'edit';
  }

  createMode() {
    return this.mode === 'create';
  }

  setCreateMode() {
    this.mode = 'create';
  }

  addTag(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;
    this.edit.tags ? {} : this.edit.tags = [];

    if (this.edit.tags.indexOf(value.trim()) >= 0) {
      input.value = '';
      return;
    }

    // Add our tag
    if ((value || '').trim()) {
      this.edit.tags.push(value.trim());
    }
    this.event = JSON.parse(JSON.stringify(this.edit));

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  removeTag(tag: string): void {
    const index = this.edit.tags.indexOf(tag);

    if (index >= 0) {
      this.edit.tags.splice(index, 1);
    }
    this.event = JSON.parse(JSON.stringify(this.edit));
  }

  setName($event) {
    this.edit.name = $event;
    this.event = JSON.parse(JSON.stringify(this.edit));
  }

  setDescription($event) {
    this.edit.description = $event;
    this.event = JSON.parse(JSON.stringify(this.edit));
  }

  setDate($event) {
    this.edit.date = $event;
    this.event = JSON.parse(JSON.stringify(this.edit));
  }

  setLocation($event) {
    this.edit.location = $event;
    this.event = JSON.parse(JSON.stringify(this.edit));
  }

  setArea($event) {
    this.edit.area = $event;
    this.event = JSON.parse(JSON.stringify(this.edit));
  }
  private mockEvent() {
    this.event = {
      id: this.event.id,
      name: 'Sample Title',
      date: new Date(),
      description: 'Descriptionwordswordswords',
      location: 'Sample Street, SA SAMPLE',
      area: 'Dundee',
      tags: [
        'fun',
        'exciting',
        'rewarding'
      ],
      attendees: [
        { firstName: 'Bob', lastName: 'Dole', email: 'bdole@bedull.com' },
        { firstName: 'Watsis', lastName: 'Face', email: 'sample@email.com' }
      ],
      organizers: [
        { firstName: 'Guy', lastName: 'Encharj', email: 'theman@email.com' }
      ]
    };
  }
}
