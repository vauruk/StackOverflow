package br.com.vanderson.stackoverflow.db.model;

import java.io.Serializable;

import br.com.vanderson.stackoverflow.app.annotation.Id;

/**
 * Created by vauruk on 19/03/16.
 */
public class EntityApp {
    @Id
    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
