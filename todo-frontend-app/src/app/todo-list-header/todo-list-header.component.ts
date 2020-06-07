import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {Todo} from '../_models/todo';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AccountService, AlertService} from '../_services';

@Component({
  selector: 'app-todo-list-header',
  templateUrl: './todo-list-header.component.html',
  styleUrls: ['./todo-list-header.component.scss']
})
export class TodoListHeaderComponent implements OnInit {
  form: FormGroup;

  newTodo: Todo = new Todo();
  dueDate: Date = new Date();

  @Output()
  add: EventEmitter<Todo> = new EventEmitter();


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private alertService: AlertService
  ) { }


  ngOnInit(): void {
    this.newTodo.dueDate = new Date();
    this.form = this.formBuilder.group({
      todoText: ['', Validators.required],
      dueDateText: ['', Validators.required]
    });
  }

  addTodo() {
    this.add.emit(this.newTodo);
    this.newTodo = new Todo();
  }


}
