package com.johanlarson.sjgmf;

import java.util.HashMap;
import java.util.Map;

public class ModelHelpers {
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
}
