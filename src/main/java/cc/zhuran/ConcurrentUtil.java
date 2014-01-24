package cc.zhuran;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.util.concurrent.Monitor;

import java.util.*;

public class ConcurrentUtil {
    public static Predicate<Integer> even = new Predicate<Integer>() {
        @Override
        public boolean apply(Integer number) {
            return number % 2 == 0;
        }
    };

    public static Predicate<Integer> odd = Predicates.not(even);

    public static List<Integer> sort(List<Integer> numbers) {
        return null;
    }

    public static Map<String, List<Integer>> detach(List<Integer> numbers){
        Pool pool = new Pool();


        Fetcher evenFetcher = new Fetcher(pool, even);
        Fetcher oddFetcher = new Fetcher(pool, odd);

        Feeder feeder = new Feeder(numbers, pool);

        evenFetcher.start();
        oddFetcher.start();
        feeder.start();

        try {
            evenFetcher.join();
            oddFetcher.join();
            feeder.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map map = new HashMap<String, List<Integer>>();
        map.put("odd", oddFetcher.numbers());
        map.put("even", evenFetcher.numbers());
        return map;
    }
}

class Pool{
    private List<Integer> numbers;
    private Monitor monitor = new Monitor();
    private Monitor.Guard guard = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return numbers.size() > 0;
        }
    };
    public Pool(){
        this.numbers = new ArrayList<Integer>();
    }

    public Pool(List<Integer> numbers){
        this.numbers = numbers;
    }

    public void feed(int number){
        monitor.enter();
        numbers.add(number);
        monitor.leave();
    }

    public int fetch(){
        int number;
        try {
            monitor.enterWhen(guard);
            number = numbers.remove(0);
        } catch (InterruptedException e) {
            number = 0;
        } finally {
            monitor.leave();
        }
        return number;
    }
}

class Feeder extends Thread{
    private List<Integer> dataSource;
    private final Pool pool;

    public Feeder(List<Integer> dataSource, Pool pool){
        this.dataSource = dataSource;
        this.pool = pool;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10; ++i){
            pool.feed(dataSource.get(i));
            try {
                Thread.sleep(new Random().nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Fetcher extends Thread{
    private final Pool pool;
    private Predicate predicate;
    private List<Integer> numbers = new ArrayList<Integer>();

    public Fetcher(Pool pool, Predicate predicate){
        this.pool = pool;
        this.predicate = predicate;
    }

    @Override
    public void run(){
        while (numbers.size() < 5){
            int number = pool.fetch();
            if (predicate.apply(number)){
                numbers.add(number);
            }
            else {
                pool.feed(number);
            }
            try {
                Thread.sleep(new Random().nextInt(300));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Integer> numbers(){
        return numbers;
    }
}