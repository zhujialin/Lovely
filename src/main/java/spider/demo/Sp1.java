package spider.demo;

/**
 * Created by zhaobofan on 16/9/14.
 */

import org.apache.commons.logging.LogFactory;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Sp1 {
   // private static final Logger log = LoggerFactory.getLogger(Sp1.class);

    public static void main(String[] args) {
        /*java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.FINEST);
        java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.FINEST);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");

*/
        String url = "http://www.dianping.com/search/category/1/10/g114";
        crawl(url);
    }

    public static void crawl(String url) {
        String selector = "div.shop-wrap>div.content>div#shop-all-list>ul>li>div.pic>a";
        Elements linksElements = getElements(url, selector);
        //以上代码的意思是 找id为“page”的div里面   id为“content”的div里面   id为“main”的div里面   class为“left”的div里面   id为“recommend”的div里面ul里面li里面a标签
        for (Element ele : linksElements) {
            String href = "http://www.dianping.com" + ele.attr("href");
            shopPage(href);
            Element img = ele.child(0);
            String shopName = img.attr("title");
            System.out.println(href + "," + shopName);
        }

    }

    public static void shopPage(String url) {
        String selector = "div.J-info-all>p.J-desc";
        Elements commentElements = getElements(url, selector);
        //以上代码的意思是 找id为“page”的div里面   id为“content”的div里面   id为“main”的div里面   class为“left”的div里面   id为“recommend”的div里面ul里面li里面a标签
        for (Element ele : commentElements) {
            String comment=ele.html().replaceAll("</br>","\n");
            System.out.println(comment);
        }

    }

    public static Elements getElements(String url, String selector) {
        String html = getHtmlByUrl(url);
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
    public static String getHtmlByUrl(String url) {
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
                    html = EntityUtils.toString(entity);//获得html源代码
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
