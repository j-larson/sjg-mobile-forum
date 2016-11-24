package com.johanlarson.sjgmf;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PaginatorInfo {
	public int curPage;
	public int totalPages;

	PaginatorInfo() {
		curPage = 1;
		totalPages = 1;
	}
	
	void loadFromDocument(Document doc) {
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
	}
}
