package com.johanlarson.sjgmf;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PostsListModel {
	public class Post {
		public String num;
		public String author;
		public String time;
		public String bodyHtml;
		
		Post(String n, String a, String t, String b) {
			num = n;
			author = a;
			time = t;
			bodyHtml = b;
		}
	}
	
	public String threadName;
	public String threadId;
	public String forumName;
	public String forumId;
	
	public PaginatorInfo paginatorInfo;
	
	public List<Post> posts;
	
	public PostsListModel() {
		paginatorInfo = new PaginatorInfo();
		posts = new LinkedList<Post>();
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
    	Elements els = doc.select("td.alt1 table td.navbar strong");
    	Element el = els.get(0);
    	threadName = el.text();
    	String href = el.previousElementSibling().attr("href");
    	threadId = ModelHelpers.getParametersFromUrl(href).get("t");
    	
    	els = doc.select("td.alt1 table span.navbar:last-child a");
    	el = els.first();
    	forumName = el.text();
    	forumId = ModelHelpers.getParametersFromUrl(el.attr("href")).get("f");
    	
    	paginatorInfo.loadFromDocument(doc);
    	
    	Elements postEls = doc.select("div.page table.tborder tbody tr td.thead a strong");
    	for (Element postEl : postEls) {
    		String number = postEl.text();
    		String time = postEl.parent().parent().previousElementSibling().text().trim();
    		Element tbody = postEl.parent().parent().parent().parent();
    		String author = tbody.select("a.bigusername").first().text();
    		
    		String bodyHtml = "";
    		for (Element e: tbody.select("td.alt1 div")) {
    			if (e.attr("id") != null && e.attr("id").startsWith("post_message_")) {
    				// Remove links to original reply. These links contain images that cause problems.
    				Elements links = e.select("table tbody td.alt2 a");
    				for (Element link : links) {
    					if (link.attr("href").startsWith("showthread.php")) {
    						link.remove();
    					}
    				}
    				bodyHtml = e.html();
    				break;
    			}
    		}
    		Post p = new Post(number, author, time, bodyHtml);
    		posts.add(p);
    	}
    }

	public static void main(String[] args) throws IOException {
		File input = new File("C:\\johan\\code\\web-dev\\pages\\thread2.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		
		PostsListModel model = new PostsListModel();
		model.loadFromDocument(doc);
		System.out.println("forumName: " + model.forumName);
		System.out.println("forumId: " + model.forumId);
		System.out.println("threadName: " + model.threadName);
		System.out.println("threadId: " + model.threadId);
		System.out.println("curPage: " + model.paginatorInfo.curPage);
		System.out.println("totalPages: " + model.paginatorInfo.totalPages);
		
		for (Post p : model.posts) {
			System.out.println("POST");
			System.out.println("   number: " + p.num);
			System.out.println("   author: " + p.author);
			System.out.println("   time: " + p.time);
			System.out.println(p.bodyHtml);
			
		}
	}

}
