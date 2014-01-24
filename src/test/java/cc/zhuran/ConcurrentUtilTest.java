package cc.zhuran;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConcurrentUtilTest {
    @Test
    public void shouldDetachList(){
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(9, 1, 6, 2, 4, 5, 3, 10, 7, 8));
        Map<String, List<Integer>> map = ConcurrentUtil.detach(numbers);
        assertThat(isOddList(map.get("odd")), is(true));
        assertThat(isEvenList(map.get("even")), is(true));
    }

    private Boolean isSorted(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; ++i){
            if (list.get(i) > list.get(i + 1))
            {
                return false;
            }
        }
        return true;
    }

    private Boolean isOddList(List<Integer> numbers){
        for(Integer number : numbers){
            System.out.print(number + ",");
        }
        System.out.println();
        return Iterables.all(numbers, ConcurrentUtil.odd);
    }

    private Boolean isEvenList(List<Integer> numbers){
        System.out.println(Joiner.on(",").join(numbers));
        return Iterables.all(numbers, ConcurrentUtil.even);
    }
}
