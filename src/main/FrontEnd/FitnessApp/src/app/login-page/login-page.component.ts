import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  public loginForm: FormGroup;

  constructor(private authenticatonService: AuthenticationService){}

  ngOnInit(): void {
      this.loginForm = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
      });
  }

  public onSubmit(){
    this.authenticatonService.login(
      this.loginForm.get('username')!.value,
      this.loginForm.get('password')!.value,
    );
  }

}
