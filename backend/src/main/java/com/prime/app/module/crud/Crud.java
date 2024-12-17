package com.prime.app.module.crud;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Cacheable
public class Crud {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column
    private String entryName;

    @Column
    private String humidity;

    @Column
    private String temp;

    @Column
    private String alarm;


    void copyData(CrudDto dto){
        this.entryName=dto.getEntryName();
        this.humidity=dto.getHumidity();
        this.temp=dto.getTemp();
        this.alarm=dto.getAlarm();
    }

}

