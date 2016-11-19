package com.johanlarson.sjgmf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FrontPageModel {
    
    public class ForumGroup {
        public String name;
        public List<Forum> forums;
        
        private ForumGroup(String n) {
            name = n;
            forums = new LinkedList<Forum>();
        }
    }
    
    public class Forum {
        public int id;
        public String name;
        
        private Forum(String n, int i) {
            id = i;
            name = n;
        }
    }
    
    public List<ForumGroup> groups;
    
    public FrontPageModel() {
        groups = new LinkedList<>();
    }
    
    public void loadFromFile(String fname) {
        File input = new File(fname);
        try {
            Document doc = Jsoup.parse(input, null, "http://forums.sjgames.com");
            loadFromDocument(doc);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse HTML document from file " + fname, e);
        }
    }
    
    public void loadFromPage(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            loadFromDocument(doc);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse HTML document from url " + url, e);
        }
    }
    
    // Quick and dirty.
    static Map<String, String> getParametersFromUrl(String url) {
        Map<String, String> m = new HashMap<>();
        int whereQuestionMark = url.indexOf("?");
        String parametersSection = url.substring(whereQuestionMark+1);
        String[] parameterPairs = parametersSection.split("&");
        for (String parameterPair : parameterPairs) {
            String[] sections = parameterPair.split("=");
            if (sections.length >= 2) {
                m.put(sections[0], sections[1]);
            }
        }
        return m;
    }

    private void loadFromDocument(Document doc) {
        ForumGroup curGroup = null;
        Elements el = doc.select("td.tcat,td.alt1Active");
        for (Element td : el) {
            if (td.attr("class").equals("tcat")) {
                Elements links = td.select("a");
                if (links.size() < 2) {
                    continue;
                }
                curGroup = new ForumGroup(links.get(1).text());
                groups.add(curGroup);
            } else if (td.attr("class").equals("alt1Active")) {
                Elements links = td.select("a");
                if (links.size() < 1) {
                    continue;
                }
                Element link = links.first();
                String name = link.text(); 
                Map<String, String> parms = getParametersFromUrl(link.attr("href"));
                String sid = parms.get("f");
                int id = Integer.parseInt(sid);
                curGroup.forums.add(new Forum(name, id));
            }
        }
    }

    public static void main(String[] args) {
        FrontPageModel model = new FrontPageModel();
        //model.loadFromFile("c:\\johan\\code\\web-dev\\pages\\front-page.html");
        model.loadFromPage("http://forums.sjgames.com");
        
        // Render
        for (ForumGroup g : model.groups) {
            System.out.println("GROUP: " + g.name);
            for (Forum f : g.forums) {
                System.out.println("  FORUM: " + f.name + " " + f.id);
            }
        }
    }

}
