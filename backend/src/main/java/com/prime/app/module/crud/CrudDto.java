package com.prime.app.module.crud;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrudDto {

    private String id;

    private String entryName;

    private String humidity;

    private String temp;


    private String alarm;
}
