import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TelainicioComponent } from './telainicio.component';

describe('TelainicioComponent', () => {
  let component: TelainicioComponent;
  let fixture: ComponentFixture<TelainicioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TelainicioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TelainicioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
