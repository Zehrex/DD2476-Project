11
https://raw.githubusercontent.com/chris-albert/zio4j/master/src/test/java/io/lbert/IOTest.java
package io.lbert;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.Assert.*;

public class IOTest {

  private final Function<Integer, Integer> incrementFunction = i -> i + 1;

  private final Function<Integer, IO<Integer>> incrementIOFunction = i -> IO.succeed(incrementFunction.apply(i));

  @Test
  public void succeed() {
    final IO<Integer> io = IO.succeed(10);
    final Integer out = IORuntime.unsafeRun(io);
    assertEquals((Integer) 10, out);
  }

  @Test(expected = MyError.class)
  public void fail() {
    final IO<Integer> io = IO.fail(new MyError());
    IORuntime.unsafeRun(io);
  }

  @Test
  public void effect() {
    final IO<Integer> io = IO.effect(() -> 10);
    final Integer out = IORuntime.unsafeRun(io);
    assertEquals((Integer) 10, out);
  }

  @Test
  public void mapHappy() {
    final IO<Integer> io = IO.succeed(10);
    final IO<Integer> mapped = io.map(i -> i + 10);
    final Integer out = IORuntime.unsafeRun(mapped);
    assertEquals((Integer) 20, out);
  }

  @Test(expected = MyError.class)
  public void mapSadShouldntCallMapFunction() {
    final IO<Integer> io = IO.fail(new MyError());

    AtomicInteger count = new AtomicInteger(0);
    final IO<Integer> mapped = io.map(i -> {
      count.incrementAndGet();
      return i + 10;
    });
    IORuntime.unsafeRun(mapped);
    assertEquals(0, count.get());
  }

  @Test
  public void flatMapHappy() {
    final IO<Integer> io = IO.succeed(10);
    final IO<Integer> mapped = io.flatMap(i -> IO.succeed(i + 10));
    final Integer out = IORuntime.unsafeRun(mapped);
    assertEquals((Integer) 20, out);
  }

  @Test(expected = MyOtherError.class)
  public void mapError() {
    final IO<Integer> io = IO.fail(new MyError());

    final IO<Integer> mapped = io.mapError(i ->
      new MyOtherError()
    );
    IORuntime.unsafeRun(mapped);
  }

  @Test
  public void eitherInRightPath() {
    final IO<Integer> io = IO.succeed(10);
    final IO<Either<Throwable, Integer>> either = io.either();
    final Either<Throwable, Integer> out = IORuntime.unsafeRun(either);
    assertEquals(out, Either.right(10));
  }

  @Test
  public void eitherInLeftPath() {
    final MyError myError = new MyError();
    final IO<Integer> io = IO.fail(myError);
    final IO<Either<Throwable, Integer>> either = io.either();
    final Either<Throwable, Integer> out = IORuntime.unsafeRun(either);
    assertEquals(out, Either.left(myError));
  }

  @Test
  public void zip() {
    final IO<Integer> io1 = IO.succeed(10);
    final IO<Integer> io2 = IO.succeed(11);
    final IO<Tuple<Integer, Integer>> zipped = io1.zip(io2);
    final Tuple<Integer, Integer> out = IORuntime.unsafeRun(zipped);
    assertEquals(Tuple.of(10, 11), out);
  }

  @Test
  public void zipRight() {
    final IO<Integer> io1 = IO.succeed(10);
    final IO<Integer> io2 = IO.succeed(11);
    final IO<Integer> zippedRight = io1.zipRight(io2);
    assertEquals((Integer) 11, IORuntime.unsafeRun(zippedRight));
  }

  @Test
  public void zipLeft() {
    final IO<Integer> io1 = IO.succeed(10);
    final IO<Integer> io2 = IO.succeed(11);
    final IO<Integer> zippedRight = io1.zipLeft(io2);
    assertEquals((Integer) 10, IORuntime.unsafeRun(zippedRight));
  }

  @Test
  public void foreach() {
    final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3, 4);
    final IO<ImmutableList<Integer>> eached = IO.foreach(list, incrementIOFunction);
    final ImmutableList<Integer> out = IORuntime.unsafeRun(eached);
    assertEquals(ImmutableList.of(2,3,4,5), out);
  }

  @Test
  public void foreachPar() {
    final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3, 4);
    final IO<ImmutableList<Integer>> eached = IO.foreachPar(list, incrementIOFunction);
    final ImmutableList<Integer> out = IORuntime.unsafeRun(eached);
    assertEquals(ImmutableList.of(2,3,4,5), out);
  }

  @Test
  public void fromOptionSome() {
    final var some = Option.some(10);
    final var io = IO.fromOption(some, new MyError());
    assertEquals((Integer) 10, IORuntime.unsafeRun(io));
  }

  @Test
  public void fromOptionNone() {
    final var myError = new MyError();
    final var none = Option.none();
    final var io = IO.fromOption(none, myError);
    assertEquals(Either.left(myError), IORuntime.unsafeRun(io.either()));
  }

  @Test
  public void fromEitherRight() {
    final Either<Throwable, Integer> right = Either.right(10);
    final var io = IO.fromEither(right);
    assertEquals((Integer) 10, IORuntime.unsafeRun(io));
  }

  @Test
  public void fromEitherLeft() {
    final var myError = new MyError();
    final Either<Throwable, Integer> left = Either.left(myError);
    final var io = IO.fromEither(left);
    assertEquals(Either.left(myError), IORuntime.unsafeRun(io.either()));
  }

  @Test
  public void absolveRight() {
    final IO<Either<Throwable, Integer>> right = IO.succeed(Either.right(10));
    final var io = IO.absolve(right);
    assertEquals((Integer) 10, IORuntime.unsafeRun(io));
  }

  @Test
  public void absolveLeft() {
    final var myError = new MyError();
    final IO<Either<Throwable, Integer>> left = IO.succeed(Either.left(myError));
    final var io = IO.absolve(left);
    assertEquals(Either.left(myError), IORuntime.unsafeRun(io.either()));
  }

  @Test
  public void catchAll() {
    final var myError = new MyError();
    final var io = IO.fail(myError);
    final var fixed = io.catchAll(t -> IO.succeed(10));
    assertEquals((Integer) 10, IORuntime.unsafeRun(fixed));
  }

  @Test
  public void catchAllWithNoErrorShouldntCallFunc() {
    final var io = IO.succeed(10);
    final var count = new AtomicInteger(0);
    final var fixed = io.catchAll(t -> {
      count.incrementAndGet();
      return IO.succeed(10);
    });
    assertEquals((Integer) 10, IORuntime.unsafeRun(fixed));
    assertEquals(0, count.get());
  }

  @Test
  public void orElse() {
    final var myError = new MyError();
    final var io = IO.fail(myError);
    final var fixed = io.orElse(() -> IO.succeed(10));
    assertEquals((Integer) 10, IORuntime.unsafeRun(fixed));
  }

  @Test
  public void orElseWithSuccess() {
    final var io = IO.succeed(10);
    final var fixed = io.orElse(() -> IO.succeed(100));
    assertEquals((Integer) 10, IORuntime.unsafeRun(fixed));
  }

  @Test
  public void foldWithFailure() {
    final var myError = new MyError();
    final var io = IO.fail(myError);
    final var fixed = io.fold(t -> 100, a -> 200);
    assertEquals((Integer) 100, IORuntime.unsafeRun(fixed));
  }

  @Test
  public void foldWithSuccess() {
    final var io = IO.succeed(10);
    final var fixed = io.fold(t -> 100, a -> 200);
    assertEquals((Integer) 200, IORuntime.unsafeRun(fixed));
  }

  @Test
  public void foldMWithFailure() {
    final var myError = new MyError();
    final var io = IO.fail(myError);
    final var fixed = io.foldM(t -> IO.succeed(100), a -> IO.succeed(200));
    assertEquals((Integer) 100, IORuntime.unsafeRun(fixed));
  }

  @Test
  public void foldMWithSuccess() {
    final var io = IO.succeed(10);
    final var fixed = io.foldM(t -> IO.succeed(100), a -> IO.succeed(200));
    assertEquals((Integer) 200, IORuntime.unsafeRun(fixed));
  }

  public static class MyError extends Throwable {}
  public static class MyOtherError extends Throwable {}
}
