import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ConstanteWcService} from 'app/services/bussiness/constante-wc.service';
import {TIME_RANGE} from 'environments/environment';
import {isString} from 'util';

declare type HourOption = { value: any, label: string };

@Component({
  selector: 'app-registro-horario',
  templateUrl: './registro-horario.component.html'
})
export class RegistroHorarioComponent implements OnInit {
  @Input() diasLaborales: any [] = [
    'Lunes', 'Martes', 'Miércoles',
    'Jueves', 'Viernes', 'Sábado', 'Domingo'
  ];
  registroHorario: any = {};
  totalRegistroHorario = null;

  hoursDayOptions: Array<HourOption>;
  hoursLateOptions: Array<HourOption>;

  calendarValidators: Array<any> = [];

  @Output() onValid: EventEmitter<any> = new EventEmitter();
  @Output() onInvalid: EventEmitter<any> = new EventEmitter();
  @Output()
  total: EventEmitter<number> = new EventEmitter<number>();

  constructor(public constanteWcService: ConstanteWcService) {}

  ngOnInit() {

    this.registroHorario = this.diasLaborales
      .map(dayToHorarioItem);

    this.hoursDayOptions = this.getTimeListOptions(TIME_RANGE.MIN_DAY_TIME, TIME_RANGE.MAX_DAY_TIME);
    this.hoursLateOptions = this.getTimeListOptions(TIME_RANGE.MIN_LATE_TIME, TIME_RANGE.MAX_LATE_TIME);

    this.setupDefaultRegistroHorario();
  }

  setupDefaultRegistroHorario() {
    this.calcFullTotal();
  }

  getTimeListOptions(start, end) {
    const timeArr = [];
    for (let i = start; i <= end; i++) {

      for (let k = 0; k < 60; k += 15) {
        timeArr.push({
          label: (i > 12 ? i - 12 : i) + ':' + this.fillEmpty(k),
          value: this.castToDecimal(i + ':' + this.fillEmpty(k)),
        });

        if (i === end && k === TIME_RANGE.MINUTE_END) {
          return timeArr;
        }
      }
    }

    return timeArr;
  }

  fillEmpty(num) {
    let toFill = 2 - String(num).length;

    if (toFill > 0) {
      let result = String(num);
      while (toFill > 0) {
        result = 0 + result;
        toFill--;
      }

      return result;
    }

    return String(num);
  }

  getRowTotal(row, index) {
    const mi = row.mi;
    const mh = row.mh;
    const ti = row.ti;
    const th = row.th;

    if (!this.isValidTimeRange(mi, mh, ti, th)) {
      if (this.calendarValidators.length === 0) {
        this.onInvalid.emit();
      }
      this.calendarValidators[index] = true;
      return 0;
    }

    this.calendarValidators.splice(index, 1);

    if (this.calendarValidators.length === 0) {
      this.onValid.emit();
    }

    const mt = mh - mi;
    const tt = th - ti;

    return mt + tt;
  }

  isValidTimeRange(mStart, mEnd, tStart, tEnd) {

    const isZeroRange = (start, end) => start - end == 0;

    const isMorningRangeValid   = isZeroRange(mStart, mEnd) || TIME_RANGE.MIN_DAY_TIME <= mStart && mStart < mEnd;
    const isAfternoonRangeValid = isZeroRange(tStart, tEnd) || tStart < tEnd && tEnd <= TIME_RANGE.MAX_LATE_TIME;

    return isMorningRangeValid || isAfternoonRangeValid;
  }

  calcFullTotal() {
    let acc = 0;
    this.registroHorario.forEach((value, index) => {
      const total = Math.max(0, this.getRowTotal(value, index));
      this.registroHorario[index].total = this.castToTime(total);
      acc += total;
    });

    this.totalRegistroHorario = this.castToTime(acc);
    this.total.next(acc);
  }

  castToDecimal(time): number {
    const [hours, minutes] = time.split(':');
    return parseInt(hours, 0) + parseFloat((minutes / 60).toFixed(4));
  }

  castToTime(decimal) {
    const hours = Math.floor(decimal);
    const minutes = Math.round((decimal % 1) * 60);
    return hours + ':' + this.fillEmpty(minutes);
  }

  castTimeToAMPM(timeString) {
    const p = timeString.split(':');
    return (p[0] > 12 ? p[0] - 12 : p[0]) + ':' + p[1];
  }

  getRegistroHorario() {
    const arr = [];
    this.registroHorario.forEach((value, index) => {
      const row = {};
      for (const key in value) {
        const raw = value[key];
        if (isString(raw)) {
          row[key] = raw;
        } else {
          row[key] = this.castTimeToAMPM(this.castToTime(value[key]));
        }
      }
      arr.push(row);
    });

    return arr;
  }


  finalHourOptions(options: HourOption[], minValue):  HourOption[] {

    if (minValue == TIME_RANGE.MIN_LATE_TIME || minValue == TIME_RANGE.MIN_DAY_TIME)
      return options;

    const defaultLabel = options[0].label.replace(/[0-9]/g, '-')
    const defaultOption = {value: 0, label: defaultLabel};

    const validOptions  = options.filter(option => option.value > minValue)

    return [defaultOption].concat(validOptions);
  }
}

const dayToHorarioItem = dia => {
  return {
    dia,
    mi: TIME_RANGE.MIN_DAY_TIME,
    mh: 0,
    ti: TIME_RANGE.MIN_LATE_TIME,
    th: TIME_RANGE.MIN_LATE_TIME,
    total: 0
  };
};
