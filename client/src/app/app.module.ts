import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- for NgModel 

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { FamiliesComponent } from './families/families.component';
import { FamilyDetailComponent } from './family-detail/family-detail.component';
import { FatherDetailComponent } from './father-detail/father-detail.component';

import { RouterModule } from '@angular/router'
import { AppRoutingModule } from './app-routing.module'
import { HttpClientModule } from '@angular/common/http';

import { MessagesComponent } from './messages/messages.component';
import { ChildrenComponent } from './children/children.component';
import { ChildDetailComponent } from './child-detail/child-detail.component';
import { NewFamilyComponent } from './new-family/new-family.component';
import { SearchFamiliesComponent } from './search-families/search-families.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    FamiliesComponent,
    FamilyDetailComponent,
    FatherDetailComponent,
    MessagesComponent,
    ChildrenComponent,
    ChildDetailComponent,
    NewFamilyComponent,
    SearchFamiliesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
