import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MyEventsPage } from './my-events';

@NgModule({
  declarations: [
    MyEventsPage,
  ],
  imports: [
    IonicPageModule.forChild(MyEventsPage),
  ],
})
export class MyEventsPageModule {}
