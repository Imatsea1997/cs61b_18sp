import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad1 = new ArrayDequeSolution<>();
        boolean isSame = true;
        String operationsUntilError = "";
        while (isSame) {
            int numberToAction = StdRandom.uniform(0, 4);
            int insertNum = StdRandom.uniform(0, 1000);
            //System.out.println(insertNum);
            Integer expectedRemove;
            Integer actualRemove;
            if (numberToAction == 0) {
                sad1.addFirst(insertNum);
                ad1.addFirst(insertNum);
                operationsUntilError += "addFirst(" + insertNum + ")\n";
            } else if(numberToAction == 1) {
                sad1.addLast(insertNum);
                ad1.addLast(insertNum);
                operationsUntilError += "addLast(" + insertNum + ")\n";
            } else if(numberToAction == 2) {
                actualRemove = sad1.removeFirst();
                expectedRemove = ad1.removeFirst();
                operationsUntilError += "removeFirst()\n";
                assertEquals(operationsUntilError, expectedRemove, actualRemove);
            } else {
                actualRemove = sad1.removeLast();
                expectedRemove = ad1.removeLast();
                operationsUntilError += "removeLast()\n";
                assertEquals(operationsUntilError, expectedRemove, actualRemove);
            }

        }
    }
}
