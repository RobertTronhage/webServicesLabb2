/**
 * The IO interface defines methods for handling input and output operations.
 *
 * @Author: Robert Tronhage
 */
package se.tronhage.webserviceslabb2.io;

public interface IO {

    boolean yesNo(String prompt);

    String getString();

    String getAnyString();

    void addString(String s);

    void clear();

    void exit();

    int getValidIntegerInput(int minValue, int maxValue);

    long getValidLongInput(long minValue, long maxValue);
}
