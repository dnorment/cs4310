import java.util.Random;

public class Main {

    public static void main(String[] args) {

        final int[] NumberOfPageFrame = new int[]{3,4,5,6};
        final int NUM_REF_STRINGS = 50;
        final int REF_STRING_LEN = 30;
        Random rng = new Random();

        String[] references = new String[NUM_REF_STRINGS];
        for (int i=0; i<references.length; i++) {
            String ref = "";
            for (int j=0; j<REF_STRING_LEN; j++) {
                int c = rng.nextInt(8);
                ref += c;
            }
            references[i] = ref;
        }
        for (int frames : NumberOfPageFrame) {
            float totalofaults = 0; float totalffaults = 0; float totallfaults = 0;
            for (String refString : references) {
                System.out.println(refString);
                PageReplacer optml = new Optimal(refString, frames, false);
                int ofaults = optml.run();
                totalofaults += ofaults;

                PageReplacer fifo = new FirstInFirstOut(refString, frames, false);
                int ffaults = fifo.run();
                totalffaults += ffaults;

                PageReplacer lru = new LeastRecentlyUsed(refString, frames, false);
                int lfaults = lru.run();
                totallfaults += lfaults;

                System.out.printf("%d frames: OPTIMAL %d, FIFO %d, LRU %d%n", frames, ofaults, ffaults, lfaults);
            }   
            System.out.printf("%d frames: AVERAGE: OPTIMAL %f, FIFO %f, LRU %f%n", frames, totalofaults/NUM_REF_STRINGS, totalffaults/NUM_REF_STRINGS, totallfaults/NUM_REF_STRINGS);
        }
    }
}