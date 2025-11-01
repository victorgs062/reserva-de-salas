import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservaslistComponent } from './reservaslist.component';

describe('ReservaslistComponent', () => {
  let component: ReservaslistComponent;
  let fixture: ComponentFixture<ReservaslistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReservaslistComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservaslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
