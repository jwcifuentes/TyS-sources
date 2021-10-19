import { EllipsisPipe } from './ellipsis';
import { DropdownItemIdPipe, DropdownItemPipe, DropdownItemPipeFullName, DropdownYesNoPipe, DropdownItemCausalDespidoPipe, DropdownItemActividadEconomica } from './dropdown-item';
import { DropdownSingleItemPipe } from './dropdown-single-item';
import { ConstantCodePipe } from './constant-code-pipe.pipe';
import { CountryPhonePipe } from './countryPhone-input.pipe';
import { MobilePhonePipe } from './mobile-input.pipe';
import { DropdownItemEstadoPipe, DropdownItemPaisPipe } from './dropdown-item-pais';

export const PIPES = [
  EllipsisPipe,
  DropdownItemPipe,
  DropdownItemIdPipe,
  DropdownItemPaisPipe,
  DropdownItemEstadoPipe,
  DropdownSingleItemPipe,
  DropdownItemPipeFullName,
  ConstantCodePipe,
  CountryPhonePipe,
  MobilePhonePipe,
  DropdownYesNoPipe,
  DropdownItemCausalDespidoPipe,
  DropdownItemActividadEconomica
];

export const PIPES_AS_PROVIDERS = [
  CountryPhonePipe,
  MobilePhonePipe
];
//
// @NgModule({
//   declarations: PIPES,
//   exports: PIPES
// })
// export class PipesModule {
// }
