import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuxilioPracticaComponent } from './auxilio-practica.component';

describe('AuxilioPracticaComponent', () => {
  let component: AuxilioPracticaComponent;
  let fixture: ComponentFixture<AuxilioPracticaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuxilioPracticaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuxilioPracticaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
