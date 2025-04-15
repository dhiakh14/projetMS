import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KeycloaksecurityComponent } from './keycloaksecurity.component';

describe('KeycloaksecurityComponent', () => {
  let component: KeycloaksecurityComponent;
  let fixture: ComponentFixture<KeycloaksecurityComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KeycloaksecurityComponent]
    });
    fixture = TestBed.createComponent(KeycloaksecurityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
