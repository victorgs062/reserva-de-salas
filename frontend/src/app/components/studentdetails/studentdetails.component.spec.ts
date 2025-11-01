import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentdetailsComponent } from './studentdetails.component';

describe('StudentdetailsComponent', () => {
  let component: StudentdetailsComponent;
  let fixture: ComponentFixture<StudentdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentdetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
