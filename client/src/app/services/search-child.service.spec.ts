import { TestBed, inject } from '@angular/core/testing';

import { SearchChildService } from './search-child.service';

describe('SearchChildService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SearchChildService]
    });
  });

  it('should be created', inject([SearchChildService], (service: SearchChildService) => {
    expect(service).toBeTruthy();
  }));
});
