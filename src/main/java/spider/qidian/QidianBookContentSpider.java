package spider.qidian;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaobofan on 16/9/18.
 */
public class QidianBookContentSpider {
    public static final String ChapterListUrl = "http://read.qidian.com/BookReader/6xbxCkvMZqw1.aspx";


    public static void main(String args[]) {
        start();

    }

    public static void start() {
        Elements pageEles = getElements(ChapterListUrl, "div#bigcontbox>div.like_box>div#content>div.box_cont");
        Element element = pageEles.get(2);
        Elements aEles = element.select("div>ul>li>a");
        for (Element aEle : aEles) {
            getContent(aEle.attr("href"));
        }

    }

    public static void getContent(String url) {
        Elements chaptercontents = getElements(url, "#chaptercontent>script");
        Element scriptElement = chaptercontents.get(1);
        String finalUrl=scriptElement.attr("src");
        String content=getHtmlByUrl(finalUrl,"GB2312");
        content=content.substring(16,content.indexOf("<a href=http://www.qidian.com>起点中文网"));
        content=content.replaceAll("<p>", "\n");
        System.out.println(content);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public static Elements getElements(String url, String selector) {
        String html = getHtmlByUrl(url,"UTF-8");
        if (html != null && !"".equals(html)) {
            Document doc = Jsoup.parse(html);
            Elements linksElements = doc.select(selector);
            return linksElements;
        }
        return null;
    }

    /**
     * 根据URL获得所有的html信息
     *
     * @param url
     * @return
     */
    public static String getHtmlByUrl(String url,String charSet) {
        String html = null;
        HttpClient httpClient = new DefaultHttpClient();//创建httpClient对象
        HttpGet httpget = new HttpGet(url);//以get方式请求该URL
        try {
            HttpResponse responce = httpClient.execute(httpget);//得到responce对象
            int resStatu = responce.getStatusLine().getStatusCode();//返回码
            if (resStatu == HttpStatus.SC_OK) {//200正常  其他就不对
                //获得相应实体
                HttpEntity entity = responce.getEntity();
                if (entity != null) {
                    html = EntityUtils.toString(entity, charSet);//获得html源代码
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return html;
    }
}
