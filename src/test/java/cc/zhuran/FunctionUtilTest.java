package cc.zhuran;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FunctionUtilTest {

    @Test
    public void testMapFunction(){
        assertThat(FunctionUtil.numberToEnglish(5), is("five"));
        assertThat(FunctionUtil.numberToEnglish(6), is("six"));
    }

    @Test
    public void testComposeFunction(){
        assertThat(FunctionUtil.numberToChar(5), is("F"));
        assertThat(FunctionUtil.numberToChar(6), is("S"));
    }
}
