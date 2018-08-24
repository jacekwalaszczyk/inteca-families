import { Component, OnInit } from '@angular/core';

import { Family } from '../models/family';
import { Father } from '../models/father';
import { Child } from '../models/child';
import { FamilyContent } from "../models/family-content";
import { Paths }         from "../models/urls";

import { CreateFamilyService } from '../services/create-family.service';
import { AddFatherToFamilyService } from '../services/add-father-to-family.service';
import { AddChildToFamilyService } from '../services/add-child-to-family.service';

import { mergeMap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-family',
  templateUrl: './new-family.component.html',
  styleUrls: ['./new-family.component.css']
})
export class NewFamilyComponent implements OnInit {

  constructor(
    private createFamilyService: CreateFamilyService,
    private addFatherToFamilyService: AddFatherToFamilyService,
    private addChildToFamilyService: AddChildToFamilyService,
    private router: Router
  ) { }

  familyContent: FamilyContent = new FamilyContent();
  newChildData: Child = new Child();
  fatherFormError: String = '';
  childrenFormError: String = '';
  saveFamilyError: String = '';

  ngOnInit() {
    this.familyContent.family = new Family();
    this.familyContent.father = null;
    this.familyContent.children = [];
  }

  properBirthDate(birthDate: Date): boolean {
    const today: number = Date.now();
    const birthDateUTC: number = birthDate.getTime();

    return ((birthDateUTC < today) && (birthDateUTC + 100 * 365 * 24 * 3600 * 1000 >= today)); 
  }

  addFather(firstName: string, secondName: string, birthDateRaw: string, pesel: string): boolean {
    this.fatherFormError = '';
    firstName = firstName.trim();
    secondName = secondName.trim();
    pesel = pesel.trim();

    if (!firstName || !secondName || pesel.length != 11) {
      this.fatherFormError = 'Please enter the proper father`s data';
      return false;
    }

    const birthDate: Date = new Date(birthDateRaw);

    if (!this.properBirthDate(birthDate)) {
      this.fatherFormError = 'Please enter the proper father`s birth date';
      return false;
    }

    let newFather: Father = {
      birthDate,
      firstName,
      secondName,
      pesel
    }
 
    this.familyContent.father = newFather;
    this.familyContent.children = [];
    
    return true;
  }

  addChild(): boolean {
    let { firstName, secondName, birthDate, pesel, sex } = this.newChildData;
    birthDate = new Date(birthDate);

    this.childrenFormError = '';

    if (!firstName || !secondName || !pesel || pesel.length != 11 || !sex) {
      this.childrenFormError = 'Please enter the proper data of a child';
      return false;
    }

    if (!this.properBirthDate(birthDate)) {
      this.childrenFormError = 'Please enter the proper birth date of a child';
      return false;
    }
 
    this.familyContent.children.push(this.newChildData);
    this.newChildData = new Child();
    
    return true;
  }


  storeFamily() {
    let url: string = '/' + Paths.familydetailId;

    if (this.familyContent.father == null)  {return ;}
    this.createFamilyService.CreateFamily(this.familyContent).then((familyContent: FamilyContent) => {
      familyContent
        ? this.router.navigateByUrl(url.replace(':id',`${familyContent.family.id}`))
        : (this.saveFamilyError = 'There was an error while processing your request');
    })
  }
  
}