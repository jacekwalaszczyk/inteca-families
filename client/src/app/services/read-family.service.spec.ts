import { TestBed, inject } from '@angular/core/testing';

import { ReadFamilyService } from './read-family.service';

describe('ReadFamilyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReadFamilyService]
    });
  });

  it('should be created', inject([ReadFamilyService], (service: ReadFamilyService) => {
    expect(service).toBeTruthy();
  }));
});
