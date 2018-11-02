import { Component } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { MatChipInputEvent } from '@angular/material';
import { EventService } from '../../services/event.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
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
  event: any = {};
  edit: any = {};

  constructor(public navCtrl: NavController, public navParams: NavParams, private EventService: EventService, private UserService: UserService) {
    this.event = this.navParams.data.event;
    console.log(this.event);
    if (!this.event) {
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
      this.EventService.edit(this.edit).subscribe(() => {
         this.event = this.edit;
         this.setViewMode();
      });
    } else {
      // create event
      this.edit.organizers.push(this.UserService.getUser());
      this.EventService.create(this.edit).subscribe(() => {
        this.event = this.edit;
        this.setViewMode();
      });
    }
    this.setViewMode();
  }

  canEdit() {
    if (this.UserService.getUser().admin) {
      return true;
    }
    const id = this.UserService.getUser().userId;
    for (let org of this.event.organizers) {
      if (org.userId === id) {
        return true;
      }
    }
    return false;
  }

  editMode() {
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
        { firstName: 'Bob', lastName: 'Dole', email: 'bdole@bedull.com' },
        { firstName: 'Watsis', lastName: 'Face', email: 'sample@email.com' }
      ],
      organizers: [
        { firstName: 'Guy', lastName: 'Encharj', email: 'theman@email.com' }
      ]
    };
  }
}
