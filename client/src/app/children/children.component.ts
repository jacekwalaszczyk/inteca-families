import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

import { Father } from '../models/father';
import { Child } from '../models/child';
import { Paths } from '../models/urls';

import { ReadFatherService } from '../services/read-father.service';
import { ReadChildService } from '../services/read-child.service';

@Component({
  selector: 'app-children',
  templateUrl: './children.component.html',
  styleUrls: ['./children.component.css']
})

export class ChildrenComponent implements OnInit {

  @Input() father: Father;
  children: Child[];
  childDetailPath: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private readFatherService: ReadFatherService,
    private readChildService: ReadChildService,
    private location: Location
  ) {}

  ngOnInit() {
    this.ReadFather();
    this.ReadChildren();
  }

  ReadFather(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.readFatherService.ReadFather(id)
      .subscribe(father => {
        this.father = father;
        // Set the path prefix for a router link to the child's details
        this.childDetailPath = '/' + Paths.familydetailIdChild; 
        this.childDetailPath = this.childDetailPath.replace(':id', father.id.toString());
        this.childDetailPath = this.childDetailPath.replace(':pesel', '');
      });
  }

  ReadChildren(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.readChildService.ReadChildren(id)
      .subscribe(children => this.children = children);
  }

  detailClicked(pesel: string): void {
    this.router.navigateByUrl(this.childDetailPath + pesel);
  }
}
