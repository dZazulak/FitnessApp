import { Component } from '@angular/core';

@Component({
  selector: 'app-custom-workout-page',
  templateUrl: './custom-workout-page.component.html',
  styleUrls: ['./custom-workout-page.component.css']
})
export class CustomWorkoutPageComponent {

  columnDefs = [{ field: "Name" }, { field: "Description" }];

  rowData = [
    { Name: "Toyota", Description: "Celica"},
    { Name: "Ford", Description: "Mondeo"},
    { Name: "Porsche", Description: "Boxter"}
  ];
}
