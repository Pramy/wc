package com.pramyness.demo;

import static org.junit.Assert.assertTrue;

import com.pramyness.demo.handler.base.Handler;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Objects;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    private BufferedReader bufferedReader;

    protected final String TEST_PATH="src/test/java/com/pramyness/demo/";

    protected final String MAIN_PATH="src/main/java/com/pramyness/demo/";

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true); //
    }//

    //????
    protected void init(String path) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(path));
    }

    protected void handle(Handler handler) throws IOException {
        String s;
        Assert.assertNotNull(bufferedReader);
        while ((s = bufferedReader.readLine())!=null) {
            handler.handle(s);
        }
    }

}
