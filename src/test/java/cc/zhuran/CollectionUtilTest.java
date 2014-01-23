package cc.zhuran;

import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CollectionUtilTest {

    @Test
    public void testCollectionFilter(){
        String words = "we are the champions, my friends";
        Iterable<String> results =  FluentIterable.from(Splitter.onPattern(" |,").split(words)).filter(new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.length() >= 3;
            }
        });

        assertThat(Iterables.contains(results, "we"), is(false));
        assertThat(Iterables.contains(results, "are"), is(true));
        assertThat(Iterables.contains(results, "the"), is(true));
        assertThat(Iterables.contains(results, "champions"), is(true));
        assertThat(Iterables.contains(results, "my"), is(false));
        assertThat(Iterables.contains(results, "friends"), is(true));
    }

    @Test
    public void testRange(){
        assertThat(Range.open(1, 3).contains(1), is(false));
        assertThat(Range.open(1, 3).contains(2), is(true));
        assertThat(Range.open(1, 3).contains(3), is(false));

        assertThat(Range.openClosed(1, 3).contains(1), is(false));
        assertThat(Range.openClosed(1, 3).contains(2), is(true));
        assertThat(Range.openClosed(1, 3).contains(3), is(true));

        assertThat(Range.closedOpen(1, 3).contains(1), is(true));
        assertThat(Range.closedOpen(1, 3).contains(2), is(true));
        assertThat(Range.closedOpen(1, 3).contains(3), is(false));

        assertThat(Range.closed(1, 3).contains(1), is(true));
        assertThat(Range.closed(1, 3).contains(2), is(true));
        assertThat(Range.closed(1, 3).contains(3), is(true));
    }

    @Test
    public void testOrdering(){
        List<Integer> numbers = Arrays.asList(2, 5, 4, 1, 3);
        Ordering o = new Ordering() {
            @Override
            public int compare(Object left, Object right) {
                return Ints.compare((Integer)left, (Integer)right);
            }
        };
        Collections.sort(numbers, o);
        assertThat(numbers.get(0), is(1));
        assertThat(numbers.get(1), is(2));
        assertThat(numbers.get(2), is(3));
        assertThat(numbers.get(3), is(4));
        assertThat(numbers.get(4), is(5));

    }
}
