package com.johanlarson.sjgmf;

import java.io.InputStream;

import org.junit.Test;

import com.johanlarson.sjgmf.FrontPageModel.Forum;
import com.johanlarson.sjgmf.FrontPageModel.ForumGroup;

import static org.junit.Assert.assertEquals;

public class FrontPageModelTest {
    @Test
    public void testFontPageModel() {
        FrontPageModel model = new FrontPageModel();
        
        InputStream is = this.getClass().getResourceAsStream("front-page.html");
        model.loadFromInputStream(is);
        
        assertEquals(7, model.groups.size());
        ForumGroup firstGroup = model.groups.get(0);
        assertEquals("Illuminati Headquarters", firstGroup.name);
        ForumGroup lastGroup = model.groups.get(model.groups.size() - 1);
        assertEquals("The Gnomes of Zurich", lastGroup.name);

        assertEquals(4, lastGroup.forums.size());
        Forum firstForum = lastGroup.forums.get(0);
        assertEquals("The Industry", firstForum.name);
        assertEquals(37, firstForum.id);
        Forum lastForum = lastGroup.forums.get(lastGroup.forums.size() - 1);
        assertEquals("Gamer Finder", lastForum.name);
        assertEquals(41, lastForum.id);            
    }
}
