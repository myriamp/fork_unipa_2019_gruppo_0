import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {TokenUtente} from "../models/TokenUtente";
import {Observable} from "rxjs";
import {Verifica} from "../models/Verifica";

@Injectable({
  providedIn: 'root'
})
export class TokenUtenteService {

  private basePath: string = environment.apiBaseUrl+ "/tokenutente";

  constructor(private httpClient: HttpClient) { }

  public save(): Observable<TokenUtente>{
    return this.httpClient.get<TokenUtente>(this.basePath);
  }

  public checkStatus(): Observable<Verifica>{
    return this.httpClient.get<Verifica>(this.basePath+'/checkStatus');
  }

  public notificheOff(){
    return this.httpClient.delete(this.basePath);
  }
}
