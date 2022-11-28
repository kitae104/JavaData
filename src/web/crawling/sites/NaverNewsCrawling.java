package web.crawling.sites;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NaverNewsCrawling {
	
	static String BASE_URL_F = "https://search.naver.com/search.naver?sm=tab_hty.top&where=news&query=%ED%81%AC%EB%A1%A4%EB%A7%81&oquery=rpa&tqi=hGgQSdp0J1sssPWuqSNssssssil-156270";
	static String BASE_URL_B ="&refresh_start=0"; 
	
	// 페이지를 변경하기 위한 변수
	static int BASE_URL_PAGE = 1;
	
	// 완성된 URL 주소 
	static String COMPLETE_URL = BASE_URL_F + BASE_URL_PAGE + BASE_URL_B;
	
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect(COMPLETE_URL).get();
			System.out.println(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
