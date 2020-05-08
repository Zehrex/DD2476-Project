74
https://raw.githubusercontent.com/harshalbenake/hbworkspace1-100/master/pulltorefresh%20and%20dragndrop%20to%20gridview/pageddragdropgrid/src/ca/laplanete/mobile/pageddragdropgrid/OnPageChangedListener.java
package ca.laplanete.mobile.pageddragdropgrid;

public interface OnPageChangedListener {

    /**
     * called when the grid is scrolled to another page
     * @param sender grid
     * @param newPageNumber 0 based
     */
    public void onPageChanged(PagedDragDropGrid sender, int newPageNumber);
}
