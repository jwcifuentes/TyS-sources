import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatosFormacionComponent } from './datos-formacion.component';

describe('DatosFormacionComponent', () => {
  let component: DatosFormacionComponent;
  let fixture: ComponentFixture<DatosFormacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatosFormacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatosFormacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
