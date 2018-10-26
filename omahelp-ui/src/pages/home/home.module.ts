import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { HomePage } from './home';
import { EventCardComponent } from './../../shared/event-card/event-card';

@NgModule({
  declarations: [
    HomePage,
    EventCardComponent
  ],
  imports: [
    IonicPageModule.forChild(HomePage),
  ],
})
export class HomePageModule {}
