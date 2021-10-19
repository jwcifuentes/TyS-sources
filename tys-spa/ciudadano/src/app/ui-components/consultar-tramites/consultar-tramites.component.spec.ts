import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarTramitesComponent } from './consultar-tramites.component';

describe('ConsultarTramitesComponent', () => {
  let component: ConsultarTramitesComponent;
  let fixture: ComponentFixture<ConsultarTramitesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultarTramitesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarTramitesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
