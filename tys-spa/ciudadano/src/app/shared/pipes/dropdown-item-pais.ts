import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'dropdownItemPais'})
export class DropdownItemPaisPipe implements PipeTransform {
  transform(value, args?) {
    // ES6 array destructuring
    console.log(value);
    if (value) {
      return value.map(item => {
        return {label: item.name, value: item};
      });
    }
  }
}

@Pipe({name: 'dropdownItemEstado'})
export class DropdownItemEstadoPipe implements PipeTransform {
  transform(value, args?) {
    // ES6 array destructuring
    if (value) {
      return value.map(item => {
        console.log(item);
        return {label: item.name, value: item};
      });
    }
  }
}
