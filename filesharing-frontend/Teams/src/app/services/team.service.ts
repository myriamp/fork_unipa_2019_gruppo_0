import { Injectable } from '@angular/core';
import {Team} from "../models/Team";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {TeamDTO} from "../models/models";
import {Observable, Subscription} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  private baseUrl: string = environment.apiBaseUrl+"/team";

  private cache: TeamDTO[];

  constructor(private httpClient:HttpClient) {
    this.myTeams().subscribe(data=>{
      this.cache = data;
    })
  }

  public myTeams() : Observable<TeamDTO[]> {
    return this.httpClient.get<TeamDTO[]>(this.baseUrl);
  }

  public getTeam(uuid: string) : Observable<TeamDTO>{
    return this.httpClient.get<TeamDTO>(this.baseUrl+'/' +uuid);
  }

  public save(team: TeamDTO){
    return this.httpClient.put(this.baseUrl,team);
  }

  public delete(uuid: string, recursive: boolean = false){
    return this.httpClient.delete(this.baseUrl+'/'+uuid+'/'+recursive);
  }

  setCache(teams: TeamDTO[]){
    this.cache = teams;
  }

  getTeamByUUID(uuid: string){
    var team = null;
    if(this.cache) {
      this.cache.forEach((t: TeamDTO) => {
        if (t.uuid == uuid) {
          team = t;
        }
      });
    }
    return team;
  }
}
