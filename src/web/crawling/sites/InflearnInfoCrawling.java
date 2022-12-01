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

			// 강의 제목
			Elements titleElements = document.select("div.card-content > div.course_title");
			for (Element element : titleElements) {
				System.out.println("강의 제목 : " + element.text());
			}
			
			// 가격
			Elements priceElements = document.getElementsByClass("price");
			
			for (int i = 0; i < priceElements.size(); i++) {
				final String price = priceElements.get(i).text();
				System.out.println("price : " + price);
				
				final String realPrice = getRealPrice(price);
				final String salePrice = getSalePrice(price);
				
				final int realIntPrice = toInt(removeNotNumeric(realPrice));
				final int saleIntPrice = toInt(removeNotNumeric(salePrice));
				
				System.out.println("가격 : " + realIntPrice);
				System.out.println("할인 가격 : " + saleIntPrice);
			}
			
			// 강의 링크 
			Elements linkElements = document.select("a.course_card_front");
			for (int j = 0; j < linkElements.size(); j++) {
                final String url = linkElements.get(j).attr("abs:href");
                System.out.println("url : " + url);
            }
			
			// 평점 
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	/**
	 * 숫자를 문자로 변환
	 * @param str
	 * @return
	 */
	private static int toInt(final String str) {		
		return Integer.parseInt(str);
	}

	private static String removeNotNumeric(final String str) {	
		return str.replaceAll("\\W", "");
	}

	private static String getRealPrice(final String price) {
		final String[] pricesArray = price.split(" ");
        return pricesArray[0];
	}

	private static String getSalePrice(final String price) {
		final String[] pricesArray = price.split(" ");
        return (pricesArray.length == 1) ? price : pricesArray[1];
	}

}
