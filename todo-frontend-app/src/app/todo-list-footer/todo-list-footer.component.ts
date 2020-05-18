import { Component, OnInit, Input } from '@angular/core';
import {Todo} from '../_models/todo';

@Component({
  selector: 'app-todo-list-footer',
  templateUrl: './todo-list-footer.component.html',
  styleUrls: ['./todo-list-footer.component.scss']
})
export class TodoListFooterComponent implements OnInit {
  @Input()
  todos: Todo[];

  constructor() { }

  ngOnInit(): void {
  }

}
