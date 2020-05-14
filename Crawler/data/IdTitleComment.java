12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/model/IdTitleComment.java
package ru.bgcrm.model;

public class IdTitleComment extends IdTitle {
    private String comment;

    public IdTitleComment() {
    }

    public IdTitleComment(int id, String title, String comment) {
        super(id, title);
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
