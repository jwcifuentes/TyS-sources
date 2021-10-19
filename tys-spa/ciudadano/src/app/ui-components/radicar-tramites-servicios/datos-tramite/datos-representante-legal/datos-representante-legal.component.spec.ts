import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatosRepresentanteLegalComponent } from './datos-representante-legal.component';

describe('DatosRepresentanteLegalComponent', () => {
  let component: DatosRepresentanteLegalComponent;
  let fixture: ComponentFixture<DatosRepresentanteLegalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatosRepresentanteLegalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatosRepresentanteLegalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
