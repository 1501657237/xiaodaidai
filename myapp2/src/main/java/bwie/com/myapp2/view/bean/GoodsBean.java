package bwie.com.myapp2.view.bean;

import java.util.List;

/**
 * Created by ASUS on 2018/3/27.
 */

public class GoodsBean {

    /**
     * count : 10
     * error : false
     * results : [{"desc":" 调试UI利器（简直跨时代！！！）","ganhuo_id":"56cc6d1d421aa95caa707552","publishedAt":"2015-10-26T03:52:58.757000","readability":"","type":"Android","url":"https://github.com/xfumihiro/ViewInspector","who":"Dear宅学长"},{"desc":"Android性能调优利器StrictMode","ganhuo_id":"56cc6d26421aa95caa707ee9","publishedAt":"2015-11-25T03:57:07.096000","readability":"","type":"Android","url":"http://www.kuqin.com/shuoit/20150928/348295.html","who":"LHF"},{"desc":" Android利用ViewPager仿微信主界面","ganhuo_id":"56cc6d26421aa95caa708076","publishedAt":"2015-12-25T04:01:12.052000","readability":"","type":"Android","url":"https://github.com/fengsehng/WeixinTest","who":"刘朋"},{"desc":"RecyclerView：利用打造悬浮效果","ganhuo_id":"594109e5421aa92c769a8c84","publishedAt":"2017-06-15T13:55:57.947000","readability":"","type":"Android","url":"http://www.jianshu.com/p/b335b620af39","who":null},{"desc":"设计师利器，安卓程序员好好学","ganhuo_id":"56cc6d1d421aa95caa7077da","publishedAt":"2015-08-24T04:06:07.597000","readability":"","type":"Android","url":"http://zuimeia.com/app/3252/?category=15&platform=2","who":"lxxself"},{"desc":"利用Camera和Matrix实现有趣的卡片效果","ganhuo_id":"57a8501e421aa90b35e1f3f8","publishedAt":"2016-08-09T11:30:16.672000","readability":"","type":"Android","url":"http://blog.csdn.net/zhangke3016/article/details/52143339","who":"张珂"},{"desc":"歌词风格的 TextView，利用 Gradient 渐变实现。","ganhuo_id":"593f2083421aa92c769a8c69","publishedAt":"2017-06-14T11:34:54.556000","readability":"","type":"Android","url":"https://github.com/livesun/GradientTextView","who":"代码家"},{"desc":"利用 Databinding 来实现自定义字体功能，这个可以有。","ganhuo_id":"5913cfeb421aa90c7d49ad84","publishedAt":"2017-05-11T12:03:09.581000","readability":"","type":"Android","url":"https://github.com/EngrAhsanAli/AACustomFont","who":"Allen"},{"desc":"利用 Renderscript 实现的一个简单的模糊效果封装组件","ganhuo_id":"57ba3b1f421aa950d35eb33f","publishedAt":"2016-08-22T11:29:37.164000","readability":"","type":"Android","url":"https://github.com/wl9739/BlurredView","who":"代码家"},{"desc":"利用 KeyStore 存储密码，加密 SharedPreference 的数据，保证安全性。","ganhuo_id":"57c75e58421aa9125fa3edc9","publishedAt":"2016-09-01T11:31:19.288000","readability":"","type":"Android","url":"https://github.com/iamMehedi/Secured-Preference-Store","who":"代码家"}]
     */

    private int count;
    private boolean error;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * desc :  调试UI利器（简直跨时代！！！）
         * ganhuo_id : 56cc6d1d421aa95caa707552
         * publishedAt : 2015-10-26T03:52:58.757000
         * readability :
         * type : Android
         * url : https://github.com/xfumihiro/ViewInspector
         * who : Dear宅学长
         */

        private String desc;
        private String ganhuo_id;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGanhuo_id() {
            return ganhuo_id;
        }

        public void setGanhuo_id(String ganhuo_id) {
            this.ganhuo_id = ganhuo_id;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getReadability() {
            return readability;
        }

        public void setReadability(String readability) {
            this.readability = readability;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
