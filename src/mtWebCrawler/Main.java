package mtWebCrawler;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//@ 16 minutes
		ArrayList<WebCrawler> bots = new ArrayList<>();
		bots.add(new WebCrawler ("https://abcnews.go.com", 1));
		bots.add(new WebCrawler ("https://www.npr.org", 2));
		bots.add(new WebCrawler("https://gamefaqs.gamespot.com/", 3));
		
		for(WebCrawler w : bots) {
			try {
				w.getThread().join();
				
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
