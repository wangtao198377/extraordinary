package com.xitao.mybatis;

import java.sql.Date;

public class Test1DO {

    private Long id;
    private String name;
    private String description;
    private Date gmtCreate;
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test1DO test1DO = (Test1DO) o;

        if (id != null ? !id.equals(test1DO.id) : test1DO.id != null) return false;
        if (name != null ? !name.equals(test1DO.name) : test1DO.name != null) return false;
        if (description != null ? !description.equals(test1DO.description) : test1DO.description != null) return false;
        if (gmtCreate != null ? !gmtCreate.equals(test1DO.gmtCreate) : test1DO.gmtCreate != null) return false;
        return gmtModified != null ? gmtModified.equals(test1DO.gmtModified) : test1DO.gmtModified == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Test1DO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }




}
