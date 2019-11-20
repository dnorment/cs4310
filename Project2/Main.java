import java.util.Random;

public class Main {

    public static void main(String[] args) {

        final int[] NumberOfPageFrame = new int[]{3,4,5,6};
        final int NUM_REF_STRINGS = 50;
        final int REF_STRING_LEN = 30;
        Random rng = new Random();

        //create all random reference strings
        String[] references = new String[NUM_REF_STRINGS];
        for (int i=0; i<references.length; i++) { //add strings until ref string array is full
            String ref = "";
            for (int j=0; j<REF_STRING_LEN; j++) { //add characters until string is of length
                int c = rng.nextInt(8);
                ref += c;
            }
            references[i] = ref;
        }
        //for each number of pages frames, run each algorithm on each reference string
        for (int frames : NumberOfPageFrame) {
            float totalofaults = 0; float totalffaults = 0; float totallfaults = 0; //store number of faults for each algorithm for getting average
            for (String refString : references) {
                System.out.println(refString);
                PageReplacer optml = new Optimal(refString, frames, false); //optimal algorithm
                int ofaults = optml.run();
                totalofaults += ofaults;

                PageReplacer fifo = new FirstInFirstOut(refString, frames, false); //FIFO algorithm
                int ffaults = fifo.run();
                totalffaults += ffaults;

                PageReplacer lru = new LeastRecentlyUsed(refString, frames, false); //LRU algorithm
                int lfaults = lru.run();
                totallfaults += lfaults;

                //print number of page faults for each algorithm
                System.out.printf("%d frames: OPTIMAL %d, FIFO %d, LRU %d%n", frames, ofaults, ffaults, lfaults);
            }   
            //print average of page faults for each algorithm per numpages
            System.out.printf("%d frames: AVERAGE: OPTIMAL %f, FIFO %f, LRU %f%n", frames, totalofaults/NUM_REF_STRINGS, totalffaults/NUM_REF_STRINGS, totallfaults/NUM_REF_STRINGS);
        }
    }
}