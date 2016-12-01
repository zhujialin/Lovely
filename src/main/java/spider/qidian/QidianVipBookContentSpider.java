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

/**
 * Created by zhaobofan on 16/9/18.
 */
public class QidianVipBookContentSpider {
    public static final String ChapterListUrl = "http://vipreader.qidian.com/BookReader/vip,2597043,44548147.aspx";


    public static void main(String args[]) {
        start();

    }

    public static void start() {
        String html = getHtmlByUrl("http://vipreader.qidian.com/BookReader/vip,2597043,44523290.aspx", "UTF-8");
        System.out.print(html);
    }

    public static void getContent(String url) {
        Elements chaptercontents = getElements(url, "#chaptercontent>script");
        Element scriptElement = chaptercontents.get(1);
        String finalUrl = scriptElement.attr("src");
        String content = getHtmlByUrl(finalUrl, "GB2312");
        content = content.substring(16, content.indexOf("<a href=http://www.qidian.com>起点中文网"));
        content = content.replaceAll("<p>", "\n");
        System.out.println(content);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public static Elements getElements(String url, String selector) {
        String html = getHtmlByUrl(url, "UTF-8");
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
    public static String getHtmlByUrl(String url, String charSet) {
        String html = null;
        HttpClient httpClient = new DefaultHttpClient();//创建httpClient对象
        HttpGet httpGet = new HttpGet(url);//以get方式请求该URL

        httpGet.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.addHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6");
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        httpGet.addHeader("Connection","Keep-Alive");
        httpGet.addHeader("Cookie", "beacon_visit_count=4; _csrfToken=sk0vIxHzrAMgxwvB0iBO116sXq2EYUeYWGdewa5m; ns=2; ad_gid=1474177288570322; pgv_pvi=343500800; pgv_si=s2593880064; qg_ad_guid=cebbf6263149b8f83989a76796e69d42; qdgg=2658%3B%3B; stat_gid=8216977475; stat_sessid=18400042236; rcr=2597043; isCloseQDCode=1; qdgd=1; __utma=1.314593295.1474177290.1474177290.1474180129.2; __utmc=1; __utmz=1.1474180129.2.2.utmcsr=se.qidian.com|utmccn=(referral)|utmcmd=referral|utmcct=/; b_t_s=s; bc=2597043; dltk=0%7C18049910658; ll=2016-06-06 18:16:57; rt=2013-07-02 11:59:17; uos=00000000011112000; uc=0; mdltk=id=2cd281aae0c4ddcd82efe6e936c608ba&nk=%e7%99%bd%e5%a6%82%e7%99%bd%e7%b3%96%e4%b8%b6&ut=3&si=90753d9cd0bb6b90494ec6d1ac458581&pd=dz00203435403.pt&hi=2644678&ai=d64353edfca01f3d1529e0a19eb82d2d&an=; zan=0; cmfuToken=N((9ukRBbNdFB9IxC37HTR5t2Upg4xudCG4qPeUWLRh2GsAVAEQS8Ub60phO9C0Cm-4PFnAWuMDnq1fvVLho9-bqaWB9Et3KCt-YbzU2HgJw567soO817Vi8hr3651-DQE9hmmOtdSgsgKfe-Th8UwMP5gV9bI7r_zoSvUkdNQik9V0mT12HUST1aTrFFihCrl6ag1qQaMPiAoo4Q0WfM_VKf0iFT_z0tpP0YN7cAdlkSrXoYPMtKmZkexK4r7Fw7xrPkzgu1q6F2FQgoVvCmy7d5UXq6hF4eGB9cotVcEBWOKyeUa-lcYaRA2; qduid=162938511; stat_id24=0,1,1,noimg; stt=162938511=20160918162502; vrk=307c313937302d30312d30312030383a30303a30307c7c343730383736; lrbc=2597043%7C44523290%7C1; isBindWeiXin=0");
        try {
            HttpResponse responce = httpClient.execute(httpGet);//得到responce对象
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
