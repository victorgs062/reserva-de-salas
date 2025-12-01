import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisciplinalistComponent } from './disciplinalist.component';

describe('DisciplinalistComponent', () => {
  let component: DisciplinalistComponent;
  let fixture: ComponentFixture<DisciplinalistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisciplinalistComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisciplinalistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
