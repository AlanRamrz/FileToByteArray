import java.io.File;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class Main {

	public static void main(String[] args) {
		convertFileToByteArray();
	}
	
	private static void convertFileToByteArray() {
		try {
			File file = new File("/Users/alanramirez/Documents/Otros/perfil.jpg");
			byte[] fileBytes = FileUtils.readFileToByteArray(file);
			
			HrMailService.sendMail(fileBytes, "Test.jpg");
			
			String coded = Base64.getEncoder().encodeToString(fileBytes);
			System.out.println(coded);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
