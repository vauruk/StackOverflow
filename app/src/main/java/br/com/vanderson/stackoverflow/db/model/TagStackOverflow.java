package br.com.vanderson.stackoverflow.db.model;

import br.com.vanderson.stackoverflow.app.annotation.Id;
import br.com.vanderson.stackoverflow.app.annotation.Table;

/**
 * Created by vauruk on 19/03/16.
 */
@Table(name = "tbl_tags_stack_overflow")
public class TagStackOverflow extends EntidadeApp {


    private String name;
    private String tag;

    public TagStackOverflow() {
    }

    public TagStackOverflow(int id, String name, String tag) {
        super.setId( id );
        this.name = name;
        this.tag = tag;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }



}
