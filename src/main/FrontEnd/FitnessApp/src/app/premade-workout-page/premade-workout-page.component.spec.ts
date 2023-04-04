import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PremadeWorkoutPageComponent } from './premade-workout-page.component';

describe('PremadeWorkoutPageComponent', () => {
  let component: PremadeWorkoutPageComponent;
  let fixture: ComponentFixture<PremadeWorkoutPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PremadeWorkoutPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PremadeWorkoutPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
