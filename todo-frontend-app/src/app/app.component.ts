import {Component, OnInit} from '@angular/core';
import {TodoDataService} from './_services/todo-data.service';
import { User , Todo} from './_models';
import {AccountService} from './_services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [TodoDataService]
})
export class AppComponent implements OnInit {
  title = 'todo-frontend-app';
  user: User;
  todos: Todo[] = [];

  constructor(private todoDataService: TodoDataService, private accountService: AccountService) {
    this.accountService.user.subscribe(x => this.user = x);
  }


  ngOnInit(): void {
    this.todoDataService
      .getAllTodos()
      .subscribe(
        (todos) => {
          this.todos = todos;
        }
      );
  }

  onAddTodo(todo) {
    this.todoDataService
      .addTodo(todo)
      .subscribe(
        (newTodo) => {
          this.todos = this.todos.concat(newTodo);
        }
      );
  }

  onToggleTodoComplete(todo) {
    this.todoDataService
      .toggleTodoComplete(todo)
      .subscribe(
        (updatedTodo) => {
          todo = updatedTodo;
        }
      );
  }

  onRemoveTodo(todo) {
    this.todoDataService
      .deleteTodoById(todo.id)
      .subscribe(
        (_) => {
          this.todos = this.todos.filter((t) => t.id !== todo.id);
        }
      );
  }


  logout() {
    this.accountService.logout();
  }

}