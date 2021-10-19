import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CondicionesVinculacionComponent } from './condiciones-vinculacion.component';

describe('CondicionesVinculacionComponent', () => {
  let component: CondicionesVinculacionComponent;
  let fixture: ComponentFixture<CondicionesVinculacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CondicionesVinculacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CondicionesVinculacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
