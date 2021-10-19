import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatosBasicosPersonaComponent } from './datos-basicos-persona.component';

describe('DatosBasicosPersonaComponent', () => {
  let component: DatosBasicosPersonaComponent;
  let fixture: ComponentFixture<DatosBasicosPersonaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatosBasicosPersonaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatosBasicosPersonaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
