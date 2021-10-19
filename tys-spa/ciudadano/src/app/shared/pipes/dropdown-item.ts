import { Pipe, PipeTransform } from '@angular/core';


@Pipe({ name: 'dropdownItem' })
export class DropdownItemPipe implements PipeTransform {
  transform(value, args?) {
    // ES6 array destructuring
    if (value) {
      return value.map(item => {
        return { label: item.nombre, value: item };
      });
    }
  }
}

@Pipe({ name: 'dropdownItemFullName' })
export class DropdownItemPipeFullName implements PipeTransform {
  transform(value, args?) {
    // ES6 array destructuring
    return value.map(item => {
      return {
        label: item.nombre + ' ' + (item.valApellido1 ? item.valApellido1 : '') + ' ' + (item.valApellido2 ? item.valApellido2 : ''),
        value: item
      };
    });
  }
}

@Pipe({ name: 'dropdownYesNo' })
export class DropdownYesNoPipe implements PipeTransform {
  transform(value, args?) {
    if (value) {
      return value.map(item => {
        if (item.nombre === 'Seleccione') {
          return { label: item.nombre, value: null};
        }
        return { label: item.nombre, value: item };
      });
    }

  }
}
@Pipe({ name: 'dropdownItemId' })
export class DropdownItemIdPipe implements PipeTransform {
  transform(value, args?) {
    // ES6 array destructuring
    if (value) {
      return value.map(item => {
        return { label: item.nombre, value: item.id };
      });
    }
  }
}

@Pipe({ name: 'dropdownItemCausalDespido' })
export class DropdownItemCausalDespidoPipe implements PipeTransform {
  transform(value, args?) {
    // ES6 array destructuring
    if (value) {
      return value.map(item => {
        return { label: item.descripcionCausalDespido, value: item };
      });
    }
  }
}

@Pipe({ name: 'dropdownItemActividadEconomica' })
export class DropdownItemActividadEconomica implements PipeTransform {
  transform(value, args?) {
    // ES6 array destructuring
    if (value) {
      return value.map(item => {
        return { label: `${item.seccion ? item.seccion : ''} ${item.descripcion}`.trim(), value: item };
      });
    }
  }
}
