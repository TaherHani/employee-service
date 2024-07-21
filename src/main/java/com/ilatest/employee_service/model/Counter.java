package com.ilatest.employee_service.model;

public class Counter {
    public Counter(Long counter) {
        this.counter = counter;
    }
    public Counter() {
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    private Long counter;
}
