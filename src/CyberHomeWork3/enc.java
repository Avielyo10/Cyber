package CyberHomeWork3;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class enc {
	/**
	 * @param allFiles from the organized DS you can encrypt a whole folder or just a file
	 * @param key to encrypt with
	 * @throws IOException
	 */
	public static void writeEnc(List<ArrayList<String>> allFiles, int key) throws IOException {
		for (int i = 0; i < allFiles.size(); i++) {
			PrintWriter writer = new PrintWriter("enc_"+i+"_file.txt");
			for (int k = 0; k < allFiles.get(i).size() ; k++) {
				writer.println(Simple_Enc.enc(allFiles.get(i).get(k),key));
			}
			System.out.println("File Number "+(i+1)+" Successfully Encrypted");
			writer.close();
		}
	}
	/**
	 * @param path of folder with files to encrypt or just a file
	 * @param key to encrypt with
	 * @throws IOException
	 */
	public static void encFile (String path, int key) throws IOException {
		writeEnc(DS.createDS(path), key);
	}
}
