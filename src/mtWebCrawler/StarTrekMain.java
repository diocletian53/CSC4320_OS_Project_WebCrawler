package mtWebCrawler;


import java.util.ArrayList;

public class StarTrekMain {

    public static void main(String[] args) {
        // Create an ArrayList of starships (WebCrawlers)
        ArrayList<Starship> starships = new ArrayList<>();
        starships.add(new Starship("https://abcnews.go.com", 1));
        starships.add(new Starship("https://www.npr.org", 2));
        starships.add(new Starship("https://gamefaqs.gamespot.com/", 3));
        
        // Command each starship to explore their assigned link
        for (Starship s : starships) {
            try {
                s.getWarpEngine().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
