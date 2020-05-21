import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'dateRemaining'
})
export class DateRemainingPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (value) {
      const seconds = Math.floor((+new Date(value.substring(0, value.length - 5)) - +new Date()) / 1000);
      if (seconds < 29) // less than 30 seconds ago will show as 'Just now'
      {
        return 'Just now';
      }
      const intervals = {
        year: 31536000,
        month: 2592000,
        week: 604800,
        day: 86400,
        hour: 3600,
        minute: 60,
        second: 1
      };
      let counter;
      for(const i in intervals) {
        counter = Math.floor(seconds / intervals[i]);
        if (counter > 0) {
          if (counter === 1) {
            return 'due in ' + counter + ' ' + i + ''; // singular (1 day ago)
          } else {
            return 'due in ' + counter + ' ' + i + ''; // plural (2 days ago)
          }
        }
      }
    }
    return value;
  }

}
