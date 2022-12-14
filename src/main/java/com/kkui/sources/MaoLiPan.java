package com.kkui.sources;



import com.kkui.common.JsoupUtil;
import com.kkui.messageModel.MsgSimple;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.UnsupportedEncodingException;
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
    //单例
    private MaoLiPan(){
    }
    private static final MaoLiPan maoLiPan = new MaoLiPan();
    public static MaoLiPan getMaoLiPan(){
        return maoLiPan;
    }

    //目标地址
    static final String link = "https://www.alipansou.com";
    //自定义响应类
    private MsgSimple msgSimple;
    private final List<MsgSimple> aliyuLink = new ArrayList<>();
    private final List<String> maoLiSou = new ArrayList<>();

    public void find(String k){
        aliyuLink.clear();
        maoLiSou.clear();

        Document document = null;
        Document document1 = null;
        // 获取文档对象
        try {
            document = JsoupUtil.getDoc(link + "/search?k=" +  URLEncoder.encode(k,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assert document != null;
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

            document1 = JsoupUtil.getDoc(link + maoLink);


            //获取名字
            Elements h3 = document1.getElementsByTag("h3");

            //获取目标script
            Element script = document1.getElementsByTag("script").get(10);
            String str = script.data();
            Pattern pattern = Pattern.compile("https://www.aliyundrive.com.*\"?(?=\\,)");
            Matcher m = pattern.matcher(str);
            if (m.find()){
                msgSimple = new MsgSimple();
                msgSimple.setName(h3.text());
                msgSimple.setLink(m.group().replace("\\","").replace("\"",""));
                aliyuLink.add(msgSimple);
            }
        }

    }



    //通过自定义响应类返回
    public List<MsgSimple> getAliyuLink(){
        return aliyuLink;
    }

}
