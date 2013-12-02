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
@Table(name="testcolumnfamily")
public class MyPojo {
    @Id
//    @Column(name="id")
    private int id;

//    @Column(name="lp1")
    private long longProp1;

//    @me.prettyprint.hom.annotations.Column(name="name")
    @Column(name = "name", length = 100)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLongProp1() {
        return longProp1;
    }

    public void setLongProp1(long longProp1) {
        this.longProp1 = longProp1;
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
//        if (longProp1 != myPojo.longProp1) return false;
        if (name != null ? !name.equals(myPojo.name) : myPojo.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (int) (longProp1 ^ (longProp1 >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}