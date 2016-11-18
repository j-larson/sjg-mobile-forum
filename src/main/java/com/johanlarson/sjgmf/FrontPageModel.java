package com.johanlarson.sjgmf;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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

    private void load() {
        File input = new File("c:\\johan\\code\\web-dev\\pages\\front-page.html");
        ForumGroup curGroup = null;
        try {
            Document doc = Jsoup.parse(input, null, "http://example.com/");
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
                    if (links.size() <1 ) {
                        continue;
                    }
                    Element link = links.first();
                    String name = link.child(0).text();  // child(0) because the link contains a <strong> element
                    String sid = link.attr("href").substring("forumdisplay.php?f=".length());
                    int id = Integer.parseInt(sid);
                    curGroup.forums.add(new Forum(name, id));
                }
                  
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        FrontPageModel model = new FrontPageModel();
        model.load();
        
        // Render
        for (ForumGroup g : model.groups) {
            System.out.println("GROUP: " + g.name);
            for (Forum f : g.forums) {
                System.out.println("  FORUM: " + f.name + " " + f.id);
            }
        }
    }

}
