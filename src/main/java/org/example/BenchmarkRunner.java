package org.example;

import org.example.ExampleData;
import org.example.ZigZagSerializator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 2)
@Warmup(iterations = 3 ,time = 300, timeUnit = TimeUnit.MILLISECONDS)
public class BenchmarkRunner {

    @Param({"10","100", "1000"})
    private static int N;
    private static ExampleData myObject = new ExampleData();
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public static void init() throws Exception {

        for (int i=0;i<N;i++) {
            byte[] serializedData = ZigZagSerializator.serialize(myObject);
            ZigZagDeserializator.deserialize(myObject, serializedData);
        }
    }

    @Setup
    public void setup() {
        for (int i=0;i<N;i++) {
            List<Integer> numbers = new ArrayList<>();
            numbers.add(1);
            numbers.add(2);
            numbers.add(3);
            myObject.setNumberList(numbers);
            myObject.setIntField(256);
            myObject.setStringField("abcdef");
        }
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(BenchmarkRunner.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
