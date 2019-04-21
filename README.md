# collectionX
simple but compatible and powerful collections implementation for java 8 

Features:
1. fluent API with lambda support, no need to call stream() and collect() again and again 
2. well compatible with native collection implementation in java.util
3. performance optimized and welll tested

Example Usage: 
1. ListX.newList("1", "2", "3", "4")
      .map(it -> Integer.parseInt(it)) // ListX.newList(1,2,3,4)
      .filter(it -> it > 2)            // ListX.newList(3,4)
      .mkString(",", "{", "}")         // "{3,4}"
2. SetX.newSet(100, 200, 300).intersect(SetX.newSet(100, 400)) //  SetX.newSet(100)
