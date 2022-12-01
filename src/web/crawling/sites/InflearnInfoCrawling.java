package web.crawling.sites;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InflearnInfoCrawling {

	public static void main(String[] args) {
		final String inflearnUrl = "https://www.inflearn.com/courses/it-programming";
		Connection conn = Jsoup.connect(inflearnUrl);
		try {
			Document document = conn.get();
			
			// 클래스 속성을 이용하여 이미지 URL 요소 추출 
			Elements imageUrlElements = document.getElementsByClass("swiper-lazy");
			
			for (Element element : imageUrlElements) {
                System.out.println(element);
//                System.out.println(element.attr("abs:src"));
            }
			
			Elements titleElements = document.select("div.card-content > div.course_title");
			for (Element element : titleElements) {
				System.out.println("강의 제목 : " + element.text());
			}
			
			Elements priceElements = document.getElementsByClass("price");
			
			for (int i = 0; i < priceElements.size(); i++) {
				final String price = priceElements.get(i).text();
				System.out.println("가격: " + price);
			}
			
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
