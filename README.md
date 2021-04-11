# Project with benchmarks about some common operations

e.g. search element in array, strings concatenation, collection methods comparison etc

## To visualize output could be used the following combination:
```java
new OptionsBuilder()
 .resultFormat(ResultFormatType.JSON)
 .result("output/benchmark.json")
``` 
And use 1 of two approaches:
- site which parse .json - https://jmh.morethan.io/
- gradle plugin - https://github.com/jzillmann/gradle-jmh-report

