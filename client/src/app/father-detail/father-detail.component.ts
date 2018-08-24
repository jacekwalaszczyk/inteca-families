import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Father } from '../models/father';
import { ReadFatherService }  from '../services/read-father.service';

@Component({
  selector: 'app-father-detail',
  templateUrl: './father-detail.component.html',
  styleUrls: ['./father-detail.component.css']
})
export class FatherDetailComponent implements OnInit {
  @Input() father: Father;
 
  constructor(
    private route: ActivatedRoute,
    private readFatherService: ReadFatherService,
    private location: Location
  ) {}

  ngOnInit() {
    this.ReadFather();
  }

  ReadFather(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.readFatherService.ReadFather(id)
      .subscribe(father => this.father = father);
  }

 }
