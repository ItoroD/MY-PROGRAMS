/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.models;

/**
 *
 * @author OElA
 */
class Person {

    String firstName;
    String surname;
    String id;
    String idType;
    String address;

    public Person(String fn, String sn, String id, String idt) {
        firstName = fn;
        surname = sn;
        this.id = id;
        idType = idt;
    }
}
