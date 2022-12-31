package data.dataset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * 테스트 데이터 셋 생성하기
 * @author 김기태
 *
 */
public class GeneratingTestData {
	
	private static final int ROWS = 8, COLS = 5;
    private static final Random RANDOM = new Random();

	public static void main(String[] args) {
		File outputFile = new File("datas/output/output.csv");
		try {
			PrintWriter writer = new PrintWriter(outputFile);
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS-1; j++) {
					writer.printf("%.6f,", RANDOM.nextDouble());	// ,로 구분 
				}
				writer.printf("%.6f%n", RANDOM.nextDouble());		// \n으로 한 줄 내림 
			}
			writer.close();
			System.out.println("파일 생성 완료!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
