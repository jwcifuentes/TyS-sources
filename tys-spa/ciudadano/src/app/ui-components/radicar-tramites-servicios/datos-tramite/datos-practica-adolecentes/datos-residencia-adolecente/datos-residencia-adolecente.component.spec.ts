import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatosResidenciaAdolecenteComponent } from './datos-residencia-adolecente.component';

describe('DatosResidenciaAdolecenteComponent', () => {
  let component: DatosResidenciaAdolecenteComponent;
  let fixture: ComponentFixture<DatosResidenciaAdolecenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatosResidenciaAdolecenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatosResidenciaAdolecenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
