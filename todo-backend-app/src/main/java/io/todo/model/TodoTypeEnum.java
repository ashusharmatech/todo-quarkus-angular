package io.todo.model;

public enum TodoTypeEnum {

    PERSONAL(1,"Personal"),
    WORK(1,"Work"),
    SHOPPING(1,"Shopping"),
    OTHER(4,"Other");


    public int number;
    public String name;


    TodoTypeEnum(int number, String name) {
        this.name=name;
        this.number=number;
    }


}
