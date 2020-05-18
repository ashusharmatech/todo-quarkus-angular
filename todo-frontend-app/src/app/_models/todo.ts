export class Todo {

  id: number;
  title: string;
  completed = false;

  constructor(values: any = {}) {
    Object.assign(this, values);
  }

}

