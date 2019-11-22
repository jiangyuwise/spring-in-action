package com.codve.buffer;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author admin
 * @date 2019/11/22 17:51
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader r) throws IOException;
}
