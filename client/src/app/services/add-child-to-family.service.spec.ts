import { TestBed, inject } from '@angular/core/testing';

import { AddChildToFamilyService } from './add-child-to-family.service';

describe('AddChildToFamilyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddChildToFamilyService]
    });
  });

  it('should be created', inject([AddChildToFamilyService], (service: AddChildToFamilyService) => {
    expect(service).toBeTruthy();
  }));
});
