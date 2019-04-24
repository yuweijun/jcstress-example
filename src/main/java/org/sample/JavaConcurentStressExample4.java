package org.sample;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult2;

@JCStressTest
@Outcome(id = {"0, 2", "1, 0"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"0, 0"}, expect = Expect.FORBIDDEN, desc = "Abnormal outcome")
@Outcome(id = {"1, 2"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@State
public class JavaConcurentStressExample4 {

    int a = 0;

    volatile int b = 0;

    @Actor
    public void method1(IntResult2 r) {
        b = 1; // 写读
        r.r2 = a; // volatile 第二写位置禁止重排序
    }

    @Actor
    public void method2(IntResult2 r) {
        a = 2; // 写读
        r.r1 = b;
    }
}
