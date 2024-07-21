package com.ilatest.employee_service.dto;

public class ResEmployeeDto {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public ResEmployeeDto(long id) {
        this.id = id;
    }
}
