import { Component } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {MatChipInputEvent} from '@angular/material';

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
  event: any = {};

  constructor(public navCtrl: NavController, public navParams: NavParams) { 
    this.event.id = this.navParams.data.id;
    console.log(this.event.id);
    this.setViewMode();
    this.mockEvent();
  }

  private mockEvent() {
    this.event = {
      id: this.event.id,
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
        { firstName: 'Bob', lastName: 'Dole', email: 'bdole@bedull.com' }
      ],
      organizers: []
    };
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

  attend() {
    // TODO
  }

  like() {
    // TODO
  }

  submit() {
    // TODO
    if (this.event.id) {
      // update event
    } else {
      // create event
    }
  }

  editMode() {
    return this.mode === 'edit';
  }

  setEditMode() {
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

    if (this.event.tags.indexOf(value.trim()) >= 0) {
      input.value = '';
      return;
    }

    // Add our tag
    if ((value || '').trim()) {
      this.event.tags.push(value.trim());
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  removeTag(tag: string): void {
    const index = this.event.tags.indexOf(tag);

    if (index >= 0) {
      this.event.tags.splice(index, 1);
    }
  }
}
