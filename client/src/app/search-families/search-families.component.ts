import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Child } from '../models/child';
import { Father } from '../models/father';
import { Paths } from '../models/urls';
import { SearchChildService }  from '../services/search-child.service';
import { ReadFatherService } from '../services/read-father.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-families',
  templateUrl: './search-families.component.html',
  styleUrls: ['./search-families.component.css']
})

export class SearchFamiliesComponent implements OnInit {
  
  childData: Child = { firstName:'', secondName:'', birthDate:null, pesel:'', sex:'' } as Child;
  ids: number[] = [];
  fathers: Father[] = [];

  constructor(
    private route: ActivatedRoute,
    private searchChildService: SearchChildService,
    private readFatherService: ReadFatherService,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit() {
  }

  getFathersFromIds(): Promise<Father[]> {
    return Promise.all(
      this.ids.map(
        (id: number) => this.readFatherService.ReadFather(id).toPromise()
      )
    );
  }

  searchChildren(): void {
    let sBirthDate: string = '';

    this.ids = [];
    this.fathers = [];

    if (this.childData == null) return;
    if (this.childData.birthDate != null) sBirthDate = this.childData.birthDate.toString();
    
    this.searchChildService.searchChild(this.childData.firstName, this.childData.secondName, sBirthDate, this.childData.pesel, this.childData.sex)
        .subscribe(ids => {
          this.ids = ids;
          this.getFathersFromIds()
            .then(fathers => this.fathers = fathers)
            .catch(err => console.log('Error SearchFamiliesComponent -> searchChildren ', err))
        });
  }

  goToFatherDetails(id: number){
    let url: string = '/' + Paths.familydetailId;
    this.router.navigateByUrl(url.replace(':id',`${id}`));
  }
 }