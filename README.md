## JCStress

```bash
mvn archetype:generate -DinteractiveMode=false -DarchetypeGroupId=org.openjdk.jcstress -DarchetypeArtifactId=jcstress-java-test-archetype -DarchetypeVersion=0.1.1 -DgroupId=org.sample -DartifactId=test -Dversion=1.0

cd test

echo 'package org.sample;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult2;
@JCStressTest
@Outcome(id = {"0, 0", "0, 2", "1, 0"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"1, 2"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Abnormal outcome")
@State
public class ConcurrencyTest {
  int a=0;
  int b=0; // 改成 volatile 试试？
  @Actor
  public void method1(IntResult2 r) {
    r.r2 = a;
    b = 1;
  }
  @Actor
  public void method2(IntResult2 r) {
    r.r1 = b;
    a = 2;
  }
}' > src/main/java/org/sample/ConcurrencyTest.java

mvn package
java -jar target/jcstress.jar
```

## 测试结果目录

可以查看`results`目录，下面会生成 HTML 报告。

## References

1. [jcstress](https://wiki.openjdk.java.net/display/CodeTools/jcstress)
2. [https://time.geekbang.org/column/article/13484](https://time.geekbang.org/column/article/13484)

