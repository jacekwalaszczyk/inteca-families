import { TestBed, inject } from '@angular/core/testing';

import { ReadChildService } from './read-child.service';

describe('ReadChildService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReadChildService]
    });
  });

  it('should be created', inject([ReadChildService], (service: ReadChildService) => {
    expect(service).toBeTruthy();
  }));
});
