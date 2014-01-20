package cc.zhuran;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringUtilTest {

    @Test
    public void shouldRemoveBlankFromTitle(){
        assertThat(StringUtil.urlFor("That's my first blog").contains(" "), is(false));
    }

    @Ignore
    @Test
    public void shouldLowercaseTitle(){
        assertThat(StringUtil.urlFor("AbCdE"), is("abcde"));
    }

    @Test
    public void shouldPrependDate(){
        assertThat(StringUtil.urlWithDateFor("abcdef", "2014-01-14"), is("2014-01-14-abcdef"));
    }

    @Test
    public void shouldReplaceQuotaWithDash(){
        assertThat(StringUtil.urlFor("you`re right!"), is("you-re-right!"));
    }
}
