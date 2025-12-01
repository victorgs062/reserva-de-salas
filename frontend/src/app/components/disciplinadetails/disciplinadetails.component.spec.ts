import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisciplinadetailsComponent } from './disciplinadetails.component';

describe('DisciplinadetailsComponent', () => {
  let component: DisciplinadetailsComponent;
  let fixture: ComponentFixture<DisciplinadetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisciplinadetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisciplinadetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
