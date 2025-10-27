import { TestBed } from '@angular/core/testing';

import { RoomserviceService } from './roomservice.service';

describe('RoomserviceService', () => {
  let service: RoomserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoomserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
