package com.github.antiportal.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Daneel Yaitskov
 */
@Entity
@Table(name="ticks")
public class MyPojo {
//    @Id
    @Column(name="idx")
    private int id;

    @Column(name = "lbl")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPojo myPojo = (MyPojo) o;

        if (id != myPojo.id) return false;
        if (name != null ? !name.equals(myPojo.name) : myPojo.name != null) return false;

        return true;
    }


}