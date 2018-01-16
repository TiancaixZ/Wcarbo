package com.example.chenguozhen.wcarbo.utils;

/**
 * Created by chenguozhen on 2018/1/11.
 */

public class StringUtil {

    /**
     * 截取source中的来源
     *
     * @param html
     * @return
     */
    public static String getWeiboSource(String html) {
        if (html == null) return null;
        int s0 = html.indexOf(">");
        if (s0 == -1) return null;
        int s1 = html.indexOf("</a>", s0);
        if (s1 == -1) return null;
        try {
            return html.substring(s0 + 1, s1);
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

}
