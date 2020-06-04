import { TestBed, inject } from '@angular/core/testing';

import { AlphaNumericService } from './alphaNumeric.service';

describe('AlphaNumericService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AlphaNumericService]
    });
  });

  it('should be created', inject([AlphaNumericService], (service: AlphaNumericService) => {
    expect(service).toBeTruthy();
  }));
});
