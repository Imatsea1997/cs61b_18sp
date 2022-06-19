import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayListDequeTest {
   @Test
   public void testOf() {
      ArrayDeque<Integer> actual = new ArrayDeque<>();
      actual.of(1, 3, 2, 4, 5);
      ArrayDeque<Integer> expected = new ArrayDeque<>();
      expected.addLast(1);
      expected.addLast(3);
      expected.addLast(2);
      expected.addLast(4);
      expected.addLast(5);

      assertArrayEquals(expected.items, actual.items);
   }

   @Test
   /** test basic condition(<=8 elements) add */
   public void testAdd() {
      ArrayDeque<Integer> input1 = new ArrayDeque<>();
      input1.of(1, 3, 2, 4, 5, 8, 7, 6);
      input1.printDeque();

      System.out.println();

      ArrayDeque<Integer> input2 = new ArrayDeque<>();
      input2.addFirst(1);
      input2.addLast(3);
      input2.addLast(4);
      input2.addFirst(6);
      input2.addLast(2);
      input2.addFirst(5);
      input2.addLast(7);
      input2.addFirst(8);
      input2.printDeque();
   }

   @Test
   /** test basic condition(<=8 elements) remove */
   public void testRemove() {
      ArrayDeque<Integer> input1 = new ArrayDeque<>();
      input1.of(1, 3, 2, 4, 5, 8, 7, 6);
      int N = input1.size();
      System.out.println("original Array: ");
      input1.printDeque();
      System.out.println();
      System.out.println("after remove all: ");
      System.out.println();
      for (int i = 0; i < N; i++) {
         input1.removeFirst();
      }
      input1.printDeque();

      ArrayDeque<Integer> input2 = new ArrayDeque<>();
      input1.of(1, 3, 2, 4, 5, 8, 7, 6);
      System.out.println("original Array: ");
      input1.printDeque();
      System.out.println();

      input1.removeFirst();
      System.out.println("after removeFirst: ");
      input1.printDeque();
      System.out.println();

      input1.removeLast();
      System.out.println("after removeLast: ");
      input1.printDeque();
      System.out.println();

      input1.removeFirst();
      System.out.println("after removeFirst: ");
      input1.printDeque();
      System.out.println();

      input1.removeFirst();
      System.out.println("after removeFirst: ");
      input1.printDeque();
      System.out.println();

      input1.removeLast();
      System.out.println("after removeLast: ");
      input1.printDeque();
      System.out.println();
   }

   @Test
   /** test get:
    * 1. normal get
    * 2. null
    * 3. invalid index
    * */
   public void testGet() {
      ArrayDeque<Integer> input1 = new ArrayDeque<>();
      input1.of(1, 3, 2, 4, 5, 8, 7, 6);
      System.out.println("original Array: ");
      input1.printDeque();
      assertEquals(4, (int)input1.get(3));
      assertEquals(null, input1.get(-1));
      assertEquals(null, input1.get(10));

      ArrayDeque<Integer> input2 = new ArrayDeque<>();
      assertEquals(null, input2.get(4));
   }

   @Test
   /** test resize:
    * 1. expand
    * 2. shrink
    * */
   public void testResize() {
      ArrayDeque<Integer> input1 = new ArrayDeque<>();
      input1.of(1, 3, 2, 4, 5, 8, 7, 6);
      System.out.println("original Array: ");
      input1.printDeque();
      System.out.println();

      input1.addFirst(9);
      input1.addLast(10);
      input1.addLast(11);
      input1.addLast(12);
      input1.addLast(13);
      input1.addLast(14);
      input1.addFirst(15);
      input1.addFirst(16);
      input1.addFirst(17);

      System.out.println("expand Array: ");
      input1.printDeque();
      System.out.println();

      ArrayDeque<Integer> input2 = new ArrayDeque<>();
      input2.of(1, 3, 2, 4, 5, 8, 7, 6, 9, 11, 10, 14, 13, 12, 16, 15, 18, 17);
      System.out.println("original Array: ");
      input2.printDeque();
      System.out.println();

      input2.removeLast();
      input2.removeLast();
      input2.removeFirst();
      input2.removeFirst();
      input2.removeFirst();
      input2.removeFirst();
      input2.removeLast();

      System.out.println("shrink Array: ");
      input2.printDeque();
      System.out.println();
   }

}
