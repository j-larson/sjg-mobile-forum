package com.johanlarson.sjgmf;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ThreadsListModel {
	
	public class Thread {
		public String id;
		public String title;
		public int numReplies;
		public String threadStartAuthor;
		public String lastPostTime;
		
		private Thread(String i, String t, int n, String tsa, String lpt) {
			id = i;
			title = t;
			numReplies = n;
			threadStartAuthor = tsa;
			lastPostTime = lpt;
		}
	}
	
	public List<Thread> threads;
	public String name;
	public int curPage;
	public int totalPages;
	
	public ThreadsListModel() {
		threads = new LinkedList<>();
	}
	
    public void loadFromPage(String url) { 
        try {
            Document doc = Jsoup.connect(url).get();
            loadFromDocument(doc);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse HTML document from url " + url, e);
        }
    }
	
    private void loadFromDocument(Document doc) {
    	// Forum name.
    	Elements ne = doc.select("td.navbar strong");
    	name = ne.text();
    	
    	// Pagination information.
    	curPage = 1;
    	totalPages = 1;
    	Elements paginators = doc.select("div.pagenav tbody tr td.vbmenu_control");
    	for (Element e : paginators) {
    		String paginatorText = e.text();
    		if (paginatorText.startsWith("Page ")) {
    			String[] words = paginatorText.split(" ");
    			curPage = Integer.parseInt(words[1]);
    			totalPages = Integer.parseInt(words[3]);
    			break;
    		}
    	}

    	// Threads and thread details
		Elements els = doc.select("td.alt1 div a");
		for (Element el : els) {
			if (el.attr("id") != null && el.attr("id").startsWith("thread_title_")) {				
				String name = el.text();
				Map<String, String> params = ModelHelpers.getParametersFromUrl(el.attr("href"));
				String id = params.get("t");
				String threadStartAuthor = el.parent().nextElementSibling().child(0).text();
				Element row = el.parent().parent().parent();
				Element column = row.child(3);
				int numReplies = 0;
			    String numRepliesString = column.text();
			    String numRepliesNoCommas = numRepliesString.replaceAll(",", "");
			    try {
					numReplies = Integer.parseInt(numRepliesNoCommas);
			    } catch (NumberFormatException e) {
			    	numReplies = 0;
			    }
			    String lastPostTime = "-";  // missing
			    Elements timeEls = row.child(2).select("div.smallfont");
				if (timeEls.size() > 0) {
					String time = timeEls.get(0).text();
					int startGarbage = time.indexOf(" by");
					lastPostTime = time.substring(0, startGarbage);
				}
		
				Thread thread = new Thread(id, name, numReplies, threadStartAuthor, lastPostTime);
				threads.add(thread);
			}
		}
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//File input = new File("C:\\johan\\code\\web-dev\\pages\\forum.html");
		//Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Document doc = Jsoup.connect("http://forums.sjgames.com/forumdisplay.php?f=2").get();
		
		ThreadsListModel model = new ThreadsListModel();
		model.loadFromDocument(doc);
		
	}

}
