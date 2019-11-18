public class Optimal extends PageReplacer {
    
    public Optimal(String ref, int frames) {
        super(ref, frames);
    }

    @Override
    protected void replacePage(int num) {
        int replaceIndex = 0;
        int dist = 0;
        for(int i=0; i<this.pages.length; i++) { //get page that will be used last
            if (this.pages[i] == null) {
                replaceIndex = i;
                break;
            }
            if (this.nextUse(this.pages[i].num) > dist) {
                replaceIndex = i;
                dist = this.nextUse(this.pages[i].num);
            }
        }
        this.pages[replaceIndex] = new PageFrame(num, currIndex, currIndex); //replace page in frame
        this.pageFaults++;
    }
}