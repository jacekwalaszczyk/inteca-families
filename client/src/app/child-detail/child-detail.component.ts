import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

import { Child } from '../models/child';
import { ReadChildService }  from '../services/read-child.service';
import { Paths } from '../models/urls';

@Component({
  selector: 'app-child-detail',
  templateUrl: './child-detail.component.html',
  styleUrls: ['./child-detail.component.css']
})
export class ChildDetailComponent implements OnInit {
  @Input() child: Child;
         sexName: string;
 
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private readChildService: ReadChildService,
    private location: Location
  ) {}

  ngOnInit() {
    this.ReadChild();
  }

  getSexName(a_sex: string): string {
    if (a_sex == 'F') { return 'female'}
    else { return 'male' }
  }

  ReadChild(): void {
    if (+this.route.snapshot.paramMap.get('pesel') == 0) {return;}

    const pesel = this.route.snapshot.paramMap.get('pesel');
    if (pesel.length != 11) return;

    this.readChildService.ReadChild(pesel)
        .subscribe(child => {this.child = child; this.sexName = this.getSexName(child.sex)});
  }

  hideDetails(): void {
    this.router.navigateByUrl('/' + Paths.familydetailId.replace(':id', `${this.child.id}`));
  }

 }
