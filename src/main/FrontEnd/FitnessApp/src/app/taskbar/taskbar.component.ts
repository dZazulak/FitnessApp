import { Component } from '@angular/core';
import { HomePageComponent } from '../home-page/home-page.component';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-taskbar',
  templateUrl: './taskbar.component.html',
  styleUrls: ['./taskbar.component.css']
})
export class TaskbarComponent {

  constructor(private authenticationService: AuthenticationService ){}

  logout(): void{
    this.authenticationService.logout();
  }

}
