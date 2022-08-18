package com.kkui.sources;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author kkui
 * @Date 2022/8/17
 * @Description 猫狐盘搜索
 */
public class MaoLiPan  {
    private MaoLiPan(){

    }
    private static final MaoLiPan maoLiPan = new MaoLiPan();

    //目标地址
    static final String link = "https://www.alipansou.com";

    //定义两个list 一个存名字 一个存链接
    private final List<String> name = new ArrayList<>();
    private final List<String> aliyuLink = new ArrayList<>();
    private final List<String> maoLiSou = new ArrayList<>();

    public void find(String k) throws IOException {


        Document document = Jsoup.connect(link + "/search?k=" +  URLEncoder.encode(k,"utf-8")).get();
        Element elements = document.getElementById("app");
        assert elements != null;
        Element div = elements.getElementsByTag("div").first();
        assert div != null;
        Elements item = div.getElementsByTag("van-row");

        for (Element element : item){
            String someLink = element.select("a").attr("href");
            if (someLink.matches("/s/.*")){
                //共10项

                maoLiSou.add(someLink);
            }
        }

        //通过猫狸链接获取阿里云链接
        for (String maoLink : maoLiSou){
            Document document2 = Jsoup.connect(link + maoLink).get();
            //获取名字
            Elements h3 = document2.getElementsByTag("h3");
            name.add(h3.text());

            //获取目标script
            Element script = document2.getElementsByTag("script").get(10);
            String str = script.data();
            Pattern pattern = Pattern.compile("https://www.aliyundrive.com.*\"?(?=\\,)");
            Matcher m = pattern.matcher(str);
            if (m.find()){
                aliyuLink.add(m.group().replace("\\","").replace("\"",""));
//                System.out.println(m.group().replace("\\","").replace("\"",""));
            }
        }

    }

    public static MaoLiPan getMaoLiPan(){
        return maoLiPan;
    }

    public List<String> getName(){
        return name;
    }

    public List<String> getAliyuLink(){
        return aliyuLink;
    }

}
