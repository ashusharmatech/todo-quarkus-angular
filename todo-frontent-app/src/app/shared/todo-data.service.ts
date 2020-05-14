import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {Todo} from './todo';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {


  constructor(private api: ApiService) {
  }

  // Simulate POST /todos
  addTodo(todo: Todo): Observable<Todo> {
    return this.api.createTodo(todo);
  }

  // Simulate DELETE /todos/:id
  deleteTodoById(todoId: number): Observable<Todo> {
    return this.api.deleteTodoById(todoId);
  }

  // Simulate PUT /todos/:id
  updateTodo(id , todo: Todo): Observable<Todo> {
    return this.api.updateTodo(todo);
  }

  // Simulate GET /todos
  getAllTodos(): Observable<Todo[]> {
    return this.api.getAllTodos();
  }

  // Simulate GET /todos/:id
  getTodoById(todoId: number): Observable<Todo> {
    return this.api.getTodoById(todoId);
  }

  // Toggle completed
  toggleTodoComplete(todo: Todo) {
    todo.completed = !todo.completed;
    return this.api.updateTodo(todo);
  }
}
