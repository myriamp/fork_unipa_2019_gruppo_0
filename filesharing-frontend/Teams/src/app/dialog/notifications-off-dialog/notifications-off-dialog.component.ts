import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {TokenUtenteDTO} from "../../models/models";

@Component({
    selector: 'app-bucket-dialog',
    templateUrl: './notifications-off-dialog.component.html',
    styleUrls: ['./notifications-off-dialog.component.scss']
})
export class NotificationsOffDialogComponent implements OnInit {

    public tokenUtente: TokenUtenteDTO = new class implements TokenUtenteDTO {
        email: string;
        token: string;
        idTelegram: string;
    };

    constructor( public dialogRef: MatDialogRef<NotificationsOffDialogComponent>,
                 @Inject(MAT_DIALOG_DATA) public data: any) {

    }

    ngOnInit() {
    }
}