import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomWorkoutPageComponent } from './custom-workout-page.component';

describe('CustomWorkoutPageComponent', () => {
  let component: CustomWorkoutPageComponent;
  let fixture: ComponentFixture<CustomWorkoutPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomWorkoutPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomWorkoutPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
