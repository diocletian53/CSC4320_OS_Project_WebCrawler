package mtWebCrawler;


import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Starship implements Runnable {
    private static final int MAX_DEPTH = 3;  // Maximum exploration depth
    private Thread warpEngine;  // Represents the starship's warp engine (thread)
    private String initialLink;  // Initial link to explore
    private ArrayList<String> exploredLinks = new ArrayList<>();  // Tracks explored links
    private int shipID;  // ID of the starship

    public Starship(String link, int id) {
        System.out.print("Starship Created");
        initialLink = link;
        shipID = id;
        
        warpEngine = new Thread(this);
        warpEngine.start();  // Begin the exploration
    }
    
    @Override
    public void run() {
        explore(1, initialLink);
    }

    private void explore(int level, String url) {
        if (level <= MAX_DEPTH) {
            Document doc = initiateRequest(url);
            
            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String nextLink = link.absUrl("href");
                    if (!exploredLinks.contains(nextLink)) {
                        explore(level + 1, nextLink);
                    }
                }
            }
        }
    }

    private Document initiateRequest(String url) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            
            if (con.response().statusCode() == 200) {
                System.out.println("\n**Starship ID: " + shipID + " received webpage at " + url);
                
                String title = doc.title();
                System.out.println(title);
                exploredLinks.add(url);
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public Thread getWarpEngine() {
        return warpEngine;
    }
}
