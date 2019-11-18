public class Main {

    public static void main(String[] args) {

        final int[] NumberOfPageFrame = new int[]{3,4,5,6};

        String refString = "70120304230321201701";

        //for (int frames : NumberOfPageFrame) {
            PageReplacer optml = new Optimal(refString, 3);
            int faults = optml.run();
            System.out.println(faults);

            PageReplacer fifo = new FirstInFirstOut(refString, 3);
            faults = fifo.run();
            System.out.println(faults);

            PageReplacer lru = new LeastRecentlyUsed(refString, 3);
            faults = lru.run();
            System.out.println(faults);
        //}   
    }
}