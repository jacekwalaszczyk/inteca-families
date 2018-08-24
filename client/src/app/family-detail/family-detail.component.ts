import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Family } from '../models/family';
import { Child } from '../models/child';
import { FamilyContent } from '../models/family-content';
import { ReadFamilyService }  from '../services/read-family.service';
import { ReadChildService }  from '../services/read-child.service';

@Component({
  selector: 'app-family-detail',
  templateUrl: './family-detail.component.html',
  styleUrls: ['./family-detail.component.css']
})
export class FamilyDetailComponent implements OnInit {
  @Input() familyContent: FamilyContent;
  child: Child;

  constructor(
    private route: ActivatedRoute,
    private readFamilyService: ReadFamilyService,
    private readChildService: ReadChildService,
    private location: Location
  ) {}

  ngOnInit() {
    this.getFamilyContent();
    this.getChild();
  }

  getFamily(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.readFamilyService.getFamily(id)
      .subscribe(family => this.familyContent.family = family);
  }

  getChild(): void {
    if (+this.route.snapshot.paramMap.get('pesel') == 0) {return;}

    const pesel = this.route.snapshot.paramMap.get('pesel');
    if (pesel.length != 11) return;

    this.readChildService.ReadChild(pesel)
      .subscribe(child => this.child = child);
  }

  getFamilyContent(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.readFamilyService.ReadFamily(id)
      .subscribe(familyContent => this.familyContent = familyContent);
  }

  goBack(): void {
    this.location.back();
  }

}
