# collectionX
simple but compatible and powerful collections implementation for java 8 

Features:
1. fluent API with lambda support, no need to call stream() and collect() again and again 
2. well compatible with native collection implementation in java.util
3. performance optimized and welll tested

Example Usage: 
*  ListX 
```java
    ListX.newList("1", "2", "3", "4")
      .map(it -> Integer.parseInt(it)) // ListX.newList(1,2,3,4)
      .filter(it -> it > 2)            // ListX.newList(3,4)
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
