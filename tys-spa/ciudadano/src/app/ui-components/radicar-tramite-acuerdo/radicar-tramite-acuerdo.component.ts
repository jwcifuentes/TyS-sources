import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-radicar-tramite-acuerdo',
  templateUrl: './radicar-tramite-acuerdo.component.html'
})
export class RadicarTramiteAcuerdoComponent implements OnInit {

  accept = true;

  constructor(public formBuilder: FormBuilder, public router: Router) {
  }

  ngOnInit() {
  }

  goToRadicarTramite() {
    this.router.navigate(['radicar_tramites_servicios']);
  }

}
