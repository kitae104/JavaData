package web.crawling.sites;

import java.io.IOException;
import java.util.Objects;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InflearnInfoCrawling {
	
    private static final int FIRST_PAGE_INDEX = 1;
    private static final int LAST_PAGE_INDEX = 32;
    private static final String PLATFORM = "Inflearn";
    private static int TOTAL = 0;

	public static void main(String[] args) {
		
		
		
		try {
			
			for (int i = FIRST_PAGE_INDEX; i <= LAST_PAGE_INDEX; i++) {
				
				final String inflearnUrl = "https://www.inflearn.com/courses/it-programming?order=seq&page=" + i;
				Connection conn = Jsoup.connect(inflearnUrl);
				Document document = conn.get();
				
				// 클래스 속성을 이용하여 이미지 URL 요소 추출 
				Elements imageUrlElements = document.getElementsByClass("swiper-lazy");
				Elements titleElements = document.select("div.card-content > div.course_title");
                Elements priceElements = document.getElementsByClass("price");
                Elements instructorElements = document.getElementsByClass("instructor");
                Elements linkElements = document.select("a.course_card_front");
                Elements descriptionElements = document.select("p.course_description");
                Elements skillElements = document.select("div.course_skills > span");
                String[] imageUrls = new String[imageUrlElements.size()];
                
                int setIndex = 0;
                int getIndex = 0;

                for (Element e : imageUrlElements) {
                    imageUrls[setIndex++] = e.attr("abs:src");
                }
                System.out.println(titleElements.size());
                
                for (int j = 0; j < titleElements.size(); j++) {
                    final String title = titleElements.get(j).text();
                    final String price = priceElements.get(j).text();
                    final String realPrice = getRealPrice(price);
                    final String salePrice = getSalePrice(price);
                    final int realIntPrice = toInt(removeNotNumeric(realPrice));
                    final int saleIntPrice = toInt(removeNotNumeric(salePrice));
                    final String currency = String.valueOf(price.charAt(0));
                    final String instructor = instructorElements.get(j).text();
                    final String url = linkElements.get(j).attr("abs:href");
                    final String description = descriptionElements.get(j).text();
                    final String skills = removeWhiteSpace(skillElements.get(j).text());

                    System.out.println("==============================================================");
                    System.out.println("번호 : " + TOTAL++);

                    if(j < imageUrls.length ) {
                    	System.out.println("썸네일: " + imageUrls[j]);
                    }
                    
                    System.out.println("강의 제목: " + title);
                    System.out.println("가격: " + realIntPrice);
                    System.out.println("할인 가격: " + saleIntPrice);
                    System.out.println("원화: " + currency);
                    System.out.println("강의자: " + instructor);
                    System.out.println("강의 링크: " + url);
                    System.out.println("강의 설명: " + description);
                    System.out.println("기술 스택: " + skills);
                    System.out.println("==============================================================");
                    
                    /* 강의 링크 내부 */
                    Connection innerConn = Jsoup.connect(url);
                    Document innerDocument = innerConn.get();

                    /* 평점 */
                    Element ratingElement = innerDocument.selectFirst("div.dashboard-star__num");
                    final float rating = Objects.isNull(ratingElement)
                            ? toFloat("0")
                            : toFloat(ratingElement.text());
                    System.out.println("평점: " + rating);

                    /* 수강자 수 */
                    Element listenerElement = innerDocument.selectFirst("div.cd-header__info-cover");
                    final String listener = Objects.isNull(listenerElement)
                            ? innerDocument.selectFirst("span > strong").text()
                            : innerDocument.select("div.cd-header__info-cover > span > strong").get(1).text();
                    System.out.println("수강자 수: " + removeNotNumeric(listener));
                    final int viewCount = Integer.parseInt(removeNotNumeric(listener));

                    /* 강의 세션 개수 */
                    final String course = innerDocument.selectFirst("span.cd-curriculum__sub-title").text();
                    System.out.println("강의 세션 개수: " + getSessionCount(course));
                    final int sessionCount = Integer.parseInt(getSessionCount(course));
                    System.out.println();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private static String getSessionCount(final String course) {
		return removeNotNumeric(course.substring(0, course.indexOf("개")));
	}

	private static String removeWhiteSpace(String str) {
		 return str.replaceAll("\\s", "");
	}

	/**
	 * 숫자를 문자로 변환
	 * @param str
	 * @return
	 */
	private static int toInt(final String str) {	
		if(str.equals("")) {
			return 0;
		}			
		return Integer.parseInt(str);
	}
	
	private static float toFloat(final String str) {		
		return Float.parseFloat(str);
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
