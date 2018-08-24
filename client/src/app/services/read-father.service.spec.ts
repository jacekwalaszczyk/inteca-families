import { TestBed, inject } from '@angular/core/testing';

import { ReadFatherService } from './read-father.service';

describe('ReadFatherService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReadFatherService]
    });
  });

  it('should be created', inject([ReadFatherService], (service: ReadFatherService) => {
    expect(service).toBeTruthy();
  }));
});
