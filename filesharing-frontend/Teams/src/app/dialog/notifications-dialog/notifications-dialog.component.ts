import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {TokenUtenteDTO} from "../../models/models";
import {TokenUtente} from "../../models/TokenUtente";
import {TokenUtenteService} from "../../services/token-utente.service";
import {Verifica} from "../../models/Verifica";

@Component({
    selector: 'app-bucket-dialog',
    templateUrl: './notifications-dialog.component.html',
    styleUrls: ['./notifications-dialog.component.scss']
})
export class NotificationsDialogComponent implements OnInit {

    statoAttivazione: boolean = false;

    interval;

    constructor( public dialogRef: MatDialogRef<NotificationsDialogComponent>,
                 @Inject(MAT_DIALOG_DATA) public data: TokenUtente,
                 private tokenUtenteService: TokenUtenteService) {

    }

    ngOnInit() {
        this.verifica();
        this.interval = setInterval(()=>{this.verifica()}, 5*1000);
    }

    private verifica(){
        this.tokenUtenteService.checkStatus().subscribe((verifica: Verifica)=>{
            this.statoAttivazione = verifica.status;
            if(this.statoAttivazione){
                clearInterval(this.interval);
            }
        });
    }
}