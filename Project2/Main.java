public class Main {

    public static void main(String[] args) {

        final int[] NumberOfPageFrame = new int[]{3,4,5,6};

        for (int frames : NumberOfPageFrame) {
            PageReplacer fifo = new FirstInFirstOut(frames);
            PageReplacer lru = new LeastRecentlyUsed(frames);
            PageReplacer optml = new Optimal(frames);
        }
        
    }
}