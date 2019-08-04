package com.example.mytestapp.ui.news.home.domain;

import java.util.List;

public class NewsResq {

    /**
     * date : 20190803
     * stories : [{"images":["https://pic1.zhimg.com/v2-4308b6562af3ac1ce30705ba44ed9968.jpg"],"type":0,"id":9713805,"ga_prefix":"080309","title":"单眼皮有必要弄成双眼皮吗？"},{"images":["https://pic4.zhimg.com/v2-08c52bc90abbf9a388c87adc4f151d83.jpg"],"type":0,"id":9713755,"ga_prefix":"080307","title":"基因编辑，点亮了艾滋病的治愈曙光"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-bd52e7280c360baf7a729f5ece751a8c.jpg","type":0,"id":9714016,"ga_prefix":"080111","title":"为什么 8 月 1 日美联储降息 25 个基点，会产生哪些影响？"},{"image":"https://pic3.zhimg.com/v2-fa72d1feb48a04a06dece39e2288bab2.jpg","type":0,"id":9713839,"ga_prefix":"073007","title":"《哪吒》十一年大史记：逆天改命的国产动画"},{"image":"https://pic2.zhimg.com/v2-701c6c6a39b0d37224609678f32c0e6d.jpg","type":0,"id":9713626,"ga_prefix":"072307","title":"周杰伦 PK 蔡徐坤：两代明星的底层逻辑差异"},{"image":"https://pic2.zhimg.com/v2-61a7bd90cbe602c90b06e4883d607d61.jpg","type":0,"id":9713485,"ga_prefix":"071814","title":"原来「贾君鹏，你妈妈喊你回家吃饭」，已经过去十年了"},{"image":"https://pic4.zhimg.com/v2-e93a312f674d01e57bca0e0abe897547.jpg","type":0,"id":9712644,"ga_prefix":"061914","title":"自己人的广告 · 终极问题的终极答案"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-4308b6562af3ac1ce30705ba44ed9968.jpg"]
         * type : 0
         * id : 9713805
         * ga_prefix : 080309
         * title : 单眼皮有必要弄成双眼皮吗？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-bd52e7280c360baf7a729f5ece751a8c.jpg
         * type : 0
         * id : 9714016
         * ga_prefix : 080111
         * title : 为什么 8 月 1 日美联储降息 25 个基点，会产生哪些影响？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
