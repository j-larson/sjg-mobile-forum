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
    	
    	// Threads and thread details
		Elements els = doc.select("td.alt1 div a");
		for (Element el : els) {
			if (el.attr("id") != null && el.attr("id").startsWith("thread_title_")) {				
				String name = el.text();
				Map<String, String> params = ModelHelpers.getParametersFromUrl(el.attr("href"));
				String id = params.get("t");
				String threadStartAuthor = el.parent().nextElementSibling().child(0).text();
				Element row = el.parent().parent().parent();
				String numRepliesString = row.child(3).child(0).text();
				String numRepliesNoCommas = numRepliesString.replaceAll(",", "");
				int numReplies = Integer.parseInt(numRepliesNoCommas);
				
				String time = row.child(2).child(0).text();
				int startGarbage = time.indexOf(" by");
				String lastPostTime = time.substring(0, startGarbage);
		
				Thread thread = new Thread(id, name, numReplies, threadStartAuthor, lastPostTime);
				threads.add(thread);
			}
		}
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File input = new File("C:\\johan\\code\\web-dev\\pages\\forum.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		
		ThreadsListModel model = new ThreadsListModel();
		model.loadFromDocument(doc);
	}

}
