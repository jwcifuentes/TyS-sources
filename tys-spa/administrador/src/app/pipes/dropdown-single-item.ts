import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'dropdownItem'})
export class DropdownItemPipe implements PipeTransform {
  transform(value, args?) {
    // ES6 array destructuring
    if (value) {
      return value.map(item => {
        return {label: item.nombre, value: item};
      });
    }
  }
}

@Pipe({name: 'dropdownSingleItem'})
export class DropdownSingleItemPipe implements PipeTransform {
  transform(value, args?) {
    if (value) {
      return value.map(item => {
        return {label: item.nombre, value: item};
      });
    }

  }
}
