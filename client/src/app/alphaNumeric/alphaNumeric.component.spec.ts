import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlphaNumericListComponent } from './alphaNumeric.component';

describe('AlphaNumericListComponent', () => {
  let component: AlphaNumericListComponent;
  let fixture: ComponentFixture<AlphaNumericListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlphaNumericListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlphaNumericListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
