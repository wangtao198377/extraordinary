
//  https://github.com/google/guava/wiki
package com.xitao.guawa;

import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Supplier;
import static com.google.common.base.Preconditions.*;

public class guava {
    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");

    public void testOptional() throws Throwable {

        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent()); // returns true
        System.out.println(possible.get()); // returns 5

        Optional<Integer> absent = Optional.ofNullable(null);

        System.out.println(absent.isPresent());
        System.out.println(absent.orElse(6));
        System.out.println(absent.orElseGet(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 6;
            }
        }));

        System.out.println(possible.orElseGet(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 6;
            }
        }));

        System.out.println(possible.orElseGet(()->{return 6;}));
        System.out.println(absent.hashCode());
        System.out.println(absent.toString());

        absent.ifPresent((x)->{
            System.out.println(x);
        });
        possible.ifPresent(x->{
            System.out.println(x);
        });

        System.out.println(possible.equals(Optional.of(5)));

        System.out.println(absent.orElseThrow(()->{return new Exception("11");}));
    }

    @Test
    public void testPreConditions() {
        int i=5,j=-6;
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkArgument(i > j, "Expected i < j, but %s >= %s", i, j);

        checkState(i<6);
//        checkNotNull(null);
        checkElementIndex(5,6);
        checkPositionIndex(5,6);
        checkPositionIndexes(1,5,3);
    }

    @Test
    public void testOrdering() {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };


        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });

    }

    @Test
    public void testObjects(){

        System.out.println(Objects.toString(null));
        System.out.println(Objects.hashCode(null));
        System.out.println(Objects.equals(null,null));
        System.out.println(Objects.compare("ddd", "dwd", new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }));
        System.out.println(Objects.hash(2,3,4));
        //Objects.deepEquals()
        System.out.println(Objects.isNull(null));
        System.out.println(Objects.nonNull(null));
        Objects.requireNonNull("33");
        Objects.requireNonNull("ee","aaa");

        Objects.requireNonNull(null,()->{return "bbb";});
    }

    public void testHtrowables()  throws Exception{
        try {
            testObjects();
        } catch (Exception e) {
        } catch (Throwable t) {
            Throwables.throwIfInstanceOf(t, IOException.class);
            Throwables.throwIfInstanceOf(t, SQLException.class);
            throw Throwables.propagate(t);
        }
    }

    @Test
    public void testCollections(){
        COLOR_NAMES.asList();

        class Foo {
            final ImmutableSet<Bar> bars;
            Foo(Set<Bar> bars) {
                this.bars = ImmutableSet.copyOf(bars); // defensive copy!
            }
        }
    }



}


class Foo {
    @Nullable String sortedBy;
    int notSortedBy;
}

class Bar {

}