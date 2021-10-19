import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-seleccion-modulos',
  templateUrl: './seleccion-modulos.component.html'
})
export class SeleccionModulosComponent implements OnInit {

  constructor(public router: Router) {
  }

  ngOnInit() {
  }

  goToRadicarTramiteAcuerdo() {
    this.router.navigate(['radicar_tramite_acuerdo']);
  }

  goToConsultarTramites() {
    this.router.navigate(['consultar_tramites']);
  }

}
