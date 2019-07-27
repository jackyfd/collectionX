# collectionX

简单确强大的java8集合实现, 适合应用于业务系统开发.
特点:
1. fluent API和lambda支持，不需要反复使用stream()和collect()  
2. 兼容java.util的原生实现, 意味着可以直接替换java.util集合类
3. 经过详细测试并性能优化

##使用方法:

```xml
<dependency>
  <groupId>com.github.jacky-cai</groupId>
  <artifactId>collectionx</artifactId>
  <version>0.0.2</version>
</dependency>
```

Simple but compatible and powerful collections implementation for java 8, suited for business application development.
Features include:
1. fluent API with lambda support, no need to call stream() and collect() again and again 
2. well compatible with native collection implementation in java.util
3. performance optimized and well tested

Example Usage: 
*  ListX 
```java
    ListX.newList("1", "2", "3", "4")
      .map(it -> Integer.parseInt(it)) // [1,2,3,4]
      .filter(it -> it > 2)            // [3,4]
      .mkString(",", "{", "}")         // "{3,4}" 
      
    ListX.newList("1", "20", "300", "400")
        .groupBy(it -> it.length()) // {1=[1], 3=[400], 2=[20]}
        
    ListX.newList("1", "20", "300", "400")
            .groupByWithMutiValue(it -> it.length()) // {1=[1], 3=[300, 400], 2=[20]}        
        
```

*  SetX 
```java
SetX.newSet(100, 200, 300)
    .intersect(SetX.newSet(100, 400)) //  [100]
    
SetX.newSet(100, 200, 300)
        .union(SetX.newSet(100, 400)) //  [100, 200, 300, 400]
```

*  MapX
```java
MapX.of("1", 100, "2", 200, "3", 300)
    .valueSet()     // [100, 200, 300]
    .intSum()       // 600
```
