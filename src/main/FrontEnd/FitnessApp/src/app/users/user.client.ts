import { environment } from "src/environments/environment";
import { Observable } from 'rxjs';
import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserClient implements OnInit{
  constructor(private http: HttpClient){}

  private username = localStorage.getItem("username");
  userJson: any;
  userArray: Array<Observable<any>>

  ngOnInit(): void{
    // this.setJsonData();
  }

  // async getUserInfo(){
  //   const url = environment.apiUrl + 'user/' + this.username;
  //   const response = await fetch(url, {method: "GET", headers : {'Content-Type': 'application/json '}})
  //   return await response.json();
  // }

  getUsersInfo(): Observable<any>{
    return this.http.get(environment.apiUrl + 'users', {responseType: "json"});
  }

  getUserInfo(): any{
    this.userJson = this.http.get(environment.apiUrl + 'user/' + this.username, {responseType: "text"});
    this.userArray.push(this.userJson);
    return this.userArray;

  }

  // async setJsonData(){
  //   this.jsonData = await this.getUserInfo();
  //   console.log(this.jsonData);
  // }
}

