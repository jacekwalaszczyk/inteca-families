import { Component, OnInit } from '@angular/core';

import { Family } from '../models/family';
import { Father } from '../models/father';
import { Paths } from '../models/urls';

import { ReadFamilyService } from '../services/read-family.service';
import { ReadFatherService } from '../services/read-father.service';

@Component({
  selector: 'app-families',
  templateUrl: './families.component.html',
  styleUrls: ['./families.component.css']
})
export class FamiliesComponent implements OnInit {

  families: Family[];
  fathers: Father[];
  familyDetailPath: string;


  constructor(private readFamilyService: ReadFamilyService, private readFatherService: ReadFatherService) { }

  ngOnInit() {
    this.ReadFathers();
    this.getFamilies();    
  }

  getFamilies(): void {
    this.readFamilyService.getFamilies()
        .subscribe(families => this.families = families);
  }

  ReadFathers(): void {
    this.readFatherService.ReadFathers()
        .subscribe(fathers => {
          this.fathers = fathers;
          this.familyDetailPath = '/' + Paths.familydetailId.replace(':id', '');
        });
  }

}
