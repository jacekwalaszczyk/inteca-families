import { TestBed, inject } from '@angular/core/testing';

import { AddFatherToFamilyService } from './add-father-to-family.service';

describe('AddFatherToFamilyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddFatherToFamilyService]
    });
  });

  it('should be created', inject([AddFatherToFamilyService], (service: AddFatherToFamilyService) => {
    expect(service).toBeTruthy();
  }));
});
