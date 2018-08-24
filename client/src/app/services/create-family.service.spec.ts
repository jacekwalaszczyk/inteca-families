import { TestBed, inject } from '@angular/core/testing';

import { CreateFamilyService } from './create-family.service';

describe('CreateFamilyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CreateFamilyService]
    });
  });

  it('should be created', inject([CreateFamilyService], (service: CreateFamilyService) => {
    expect(service).toBeTruthy();
  }));
});
