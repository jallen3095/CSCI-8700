import { EventCardComponent } from './../../shared/event-card/event-card';
import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { EventDetailsPage } from './event-details';
import { MatFormFieldModule, MatChipsModule, MatIconModule, MatCardModule, MatExpansionModule } from '@angular/material';

@NgModule({
  declarations: [
    EventDetailsPage,
    EventCardComponent,
  ],
  imports: [
    IonicPageModule.forChild(EventDetailsPage),
    MatFormFieldModule,
    MatChipsModule,
    MatIconModule,
    MatCardModule,
    MatExpansionModule
  ],
})
export class EventDetailsPageModule {}
