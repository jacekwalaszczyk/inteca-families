import { Component, OnInit } from '@angular/core';

import { Family } from '../models/family';
import { ReadFamilyService } from '../services/read-family.service';
import { Father } from '../models/father';
import { ReadFatherService } from '../services/read-father.service';
import { Paths } from '../models/urls';
 
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  families: Family[] = [];
  fathers: Father[] = [];
  newFamilyPath = Paths.newfamily;
  searchFamiliesPath = Paths.searchfamilies;
  familiesPath = Paths.families;
 
  constructor(private readFatherService: ReadFatherService, private readFamilyService: ReadFamilyService) { }
 
  ngOnInit() {
    this.getFamilies();
    this.ReadFathers();
  }
 
  getFamilies(): void {
    this.readFamilyService.getFamilies()
      .subscribe(families => this.families = families.slice(0, 2));
  }

  ReadFathers(): void {
    this.readFatherService.ReadFathers()
      .subscribe(fathers => this.fathers = fathers.slice(0, 2));
  }
}
