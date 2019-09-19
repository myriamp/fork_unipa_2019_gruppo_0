import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {TokenUtenteDTO} from "../../models/models";
import {TokenUtente} from "../../models/TokenUtente";

@Component({
    selector: 'app-bucket-dialog',
    templateUrl: './notifications-dialog.component.html',
    styleUrls: ['./notifications-dialog.component.scss']
})
export class NotificationsDialogComponent implements OnInit {

    constructor( public dialogRef: MatDialogRef<NotificationsDialogComponent>,
                 @Inject(MAT_DIALOG_DATA) public data: TokenUtente) {

    }

    ngOnInit() {
    }
}