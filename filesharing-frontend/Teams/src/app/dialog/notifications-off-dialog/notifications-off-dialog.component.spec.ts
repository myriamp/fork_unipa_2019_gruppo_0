import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationsOffDialogComponent } from './notifications-off-dialog.component';

describe('NotificationsOffDialogComponent', () => {
  let component: NotificationsOffDialogComponent;
  let fixture: ComponentFixture<NotificationsOffDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotificationsOffDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotificationsOffDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});