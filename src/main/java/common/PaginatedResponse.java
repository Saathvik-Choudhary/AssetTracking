package common;

/**
 * Response for retrieving a page of records.
 */
public abstract class PaginatedResponse extends Response {
    private static final long serialVersionUID = 1L;

    /**
     * The page number for pagination.
     */
    private int pageNumber;

    /**
     * The size of each page for pagination.
     */
    private int pageSize;

    /**
     * The total number of items across all pages.
     */
    private long total;

    /**
     * Gets the page number.
     *
     * @return The page number.
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Gets the page size.
     *
     * @return The page size.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Gets the total number of items.
     *
     * @return The total number of items.
     */
    public long getTotal() {
        return total;
    }

    /**
     * Sets the page number.
     *
     * @param pageNumber The page number to set.
     */
    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize The page size to set.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Sets the total number of items.
     *
     * @param total The total number of items to set.
     */
    public void setTotal(final long total) {
        this.total = total;
    }
}

