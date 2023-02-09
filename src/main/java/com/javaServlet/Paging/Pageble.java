package com.javaServlet.Paging;

import com.javaServlet.Sort.Sorter;

public interface Pageble {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
