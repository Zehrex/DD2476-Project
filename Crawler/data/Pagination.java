2
https://raw.githubusercontent.com/Heemooo/Bored/master/src/main/java/com/bored/model/Pagination.java
package com.bored.model;

import com.bored.core.Page;
import lombok.Data;

import java.util.List;

@Data
public class Pagination {

    private List<Page> data;

    private Boolean hasPrev;

    private String prev;

    private Boolean hasNext;

    private String next;

    private Integer pageCount;

    private Integer current;

    private String templatePath;

    private String uri;

}
