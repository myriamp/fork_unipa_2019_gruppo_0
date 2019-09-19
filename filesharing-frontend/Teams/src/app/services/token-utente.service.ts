import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {TokenUtente} from "../models/TokenUtente";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TokenUtenteService {

  private basePath: string = environment.apiBaseUrl+ "/tokenutente";

  constructor(private httpClient: HttpClient) { }

  public save(): Observable<TokenUtente>{
    return this.httpClient.get<TokenUtente>(this.basePath);
  }
}
