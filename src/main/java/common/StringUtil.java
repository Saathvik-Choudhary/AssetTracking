package common;

/**
 * Utility class for string operation.
 */
public final class StringUtil {

    private StringUtil() {

    }

    /**
     * Checks if a string is blank (empty or contains only whitespace).
     *
     * @param text The string to check.
     * @return true if the string is blank, otherwise false.
     */
    public static boolean isBlank(final String text) {
        return (text == null) || text.trim().equals("");
    }

    /**
     * Checks if a string is not blank (not empty and not containing only whitespace).
     *
     * @param text The string to check.
     * @return true if the string is not blank, otherwise false.
     */
    public static boolean isNotBlank(final String text) {
        return !isBlank(text);
    }

    /**
     * Trims leading and trailing whitespace from the given string.
     *
     * @param text The string to trim.
     * @return The trimmed string, or null if the input string is null.
     */
    public static String trim(final String text) {
        return text != null ? text.trim() : null;
    }

}
