package com.kkui.common;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.util.Random;
import java.util.Timer;



/**
 * @Author kkui
 * @Date 2022/8/20
 * @Description jsoup封装
 */
public final class JsoupUtil {

    public static Document getDoc(String linkParm){
        Random random = new Random();
        Timer timer = new Timer();

        Document doc = null;

            try {
                Thread.sleep(3 * 1000);
                Connection con = Jsoup.connect(linkParm).userAgent(
                                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36")
                        .cookie("Cookie"," _ga=GA1.1.1870846927.1660703195; _bid=cfce75ab8ad5abdce40cc9df3e5d5a75; __gads=ID=9e23f469abb9a5ac-22f496a5a3d5002d:T=1660703196:RT=1660703196:S=ALNI_MbmJ0A6oJeGgGwRmFJZo50OCxig_A; ")
                        .timeout(50000); // 设置连接超时时间

                Connection.Response response = con.execute();

                if (response.statusCode() == 200) {
                    doc = con.get();
                } else {
                    System.out.println(response.statusCode());
                }
            } catch (
                    IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        return doc;
    }

}
