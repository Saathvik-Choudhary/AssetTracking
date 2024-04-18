package common;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public abstract class PaginatedRequest extends Request{
    private static final long serialVersionUID = 1L;
    /**
     * The page number for pagination.
     */
    @Min(message = "Page cannot be less than 1. ", value = 1)
    private int pageNumber;

    /**
     * The size of each page for pagination.
     */
    @Max(message = "Page size cannot be more than 6", value = 6)
    @Min(message = "Page size cannot be less than 1", value = 1)
    private int pageSize;

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
     * Sets the page number, ensuring it is non-negative.
     *
     * @param pageNumber The page number to set.
     */
    public void setPageNumber(final int pageNumber) {
        this.pageNumber = Math.max(1, pageNumber);
    }

    /**
     * Sets the page size, ensuring it is between 1 and 20.
     *
     * @param pageSize The page size to set.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = Math.min(6, Math.max(1, pageSize));
    }
}


