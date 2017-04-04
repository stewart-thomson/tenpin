package com.vodafone.tenpin.util;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Helper class to handle dealing with input and output streams
 * <p>
 * Created by stewart thomson.
 */
public class StreamHelper {

    private final Scanner input;
    private final PrintStream out;

    public StreamHelper(InputStream is, PrintStream out) {
        this.input = new Scanner(is);
        this.out = out;
    }

    /**
     * Parse a single line of user entered text.  Splits the text based on whitespace and puts the values
     * into a String array.
     *
     * @return Array of strings containing the values or null if there is no input
     */
    public String[] parse() {
        String line = input.nextLine();
        line = line.trim();
        if (StringUtils.isNotEmpty(line)) {
            return StringUtils.split(line, " ");
        }
        return null;
    }

    /**
     * Prints the results of the game to the supplied PrintStream
     *
     * @param value The value to print to the stream
     */
    public void outputResult(Integer value) {
        if (value != null) {
            out.println(value);
        } else {
            out.println();
        }
    }
}
