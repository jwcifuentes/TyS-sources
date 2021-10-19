import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JustaCausaDescripcionComponent } from './justa-causa-descripcion.component';

describe('JustaCausaDescripcionComponent', () => {
  let component: JustaCausaDescripcionComponent;
  let fixture: ComponentFixture<JustaCausaDescripcionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JustaCausaDescripcionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JustaCausaDescripcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
