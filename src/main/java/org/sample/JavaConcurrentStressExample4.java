package org.sample;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult2;

@JCStressTest
@Outcome(id = {"0, 2", "1, 0"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"0, 0"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Normal outcome")
@Outcome(id = {"1, 2"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Normal outcome")
@State
public class JavaConcurrentStressExample4 {

    int a = 0;

    volatile int b = 0;

    @Actor
    public void method1(IntResult2 r) {
        b = 1; // 第一个操作是 volatile 写操作，写读仍然会重排序
        r.r2 = a;
    }

    @Actor
    public void method2(IntResult2 r) {
        a = 2; // 写读
        r.r1 = b; // 第二个操作是 volatile 读操作，写读仍然会重排序
    }
}
