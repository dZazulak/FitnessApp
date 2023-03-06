import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit{
  public registerForm: FormGroup;

  constructor(private authenticatorService: AuthenticationService){}

  ngOnInit(): void {
      this.registerForm = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
        firstName: new FormControl('', Validators.required),
        lastName: new FormControl('', Validators.required),
      });
  }

  public onSubmit(){
    this.authenticatorService.register(
      this.registerForm.get('username')!.value,
      this.registerForm.get('password')!.value,
      this.registerForm.get('firstName')!.value,
      this.registerForm.get('lastName')!.value
    );
  }

}
