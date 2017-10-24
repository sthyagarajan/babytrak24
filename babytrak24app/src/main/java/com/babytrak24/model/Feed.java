package com.babytrak24.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Feed {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    private String name;
    private Integer number;

    @JsonIgnore
    @ManyToOne
    private Baby baby;


    /**
     * @return the baby
     */
    public Baby getBaby() {
        return baby;
    }

    /**
     * @param baby the course to set
     */
    public void setBaby(Baby baby) {
        this.baby = baby;
    }
}

