package com.example.yi;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.utils.Chronology;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        Chronology.getInstance().initGanZhi(2017,9,17);
//        Chronology.getInstance().initGanZhi(2017,7,27);
        System.out.println(Chronology.getInstance().getGanZhi());
    }
}