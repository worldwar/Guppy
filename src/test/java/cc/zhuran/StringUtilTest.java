package cc.zhuran;

import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkElementIndex;
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

    @Test
    public void shouldRemoveWhiteSpace(){
        String original = "We are   the   champions         , my friends!";
        String expected = "We are the champions , my friends!";
        String scrubbed = CharMatcher.WHITESPACE.collapseFrom(original, ' ');
        assertThat(scrubbed, is(expected));
    }

    @Test
    public void testRetainFrom(){
        String lettersAndNumbers = "foo989yxbar234";
        String expected = "989234";
        String retained = CharMatcher.JAVA_DIGIT.
                retainFrom(lettersAndNumbers);
        assertThat(expected,is(retained));
    }

    @Test
    public void checkIndex(){
        List<Integer> ints = Arrays.asList(1, 2);
        checkElementIndex(3, 2, "out of index");
    }

    @Test
    public void testFirstNonNull(){
        List<Integer> ints = new ArrayList<>();
        List<Integer> value = Objects.firstNonNull(ints, ints);
        java.util.Objects o;
    }

}
