public class FirstInFirstOut extends PageReplacer {

    public FirstInFirstOut(String ref, int frames) {
        super(ref, frames);
    }

    @Override
    protected void replacePage(int num) {
        int replaceIndex = 0;
        int age = Integer.MAX_VALUE;
        for(int i=0; i<this.pages.length; i++) { //get page that was used last
            if (this.pages[i] == null) {
                replaceIndex = i;
                break;
            }
            if (this.pages[i].created < age) { //replace highest age
                replaceIndex = i;
                age = this.pages[i].created;
            }
        }
        this.pages[replaceIndex] = new PageFrame(num, currIndex, currIndex); //replace page in frame
        this.pageFaults++;
    }
}