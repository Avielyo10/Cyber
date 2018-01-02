package CyberHomeWork3;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class dec {
	/**
	 * @param allFiles from the organized DS you can decrypt a whole folder or just a file 
	 * @param key to decrypt with
	 * @throws IOException
	 */
	public static void writeDec(List<ArrayList<String>> allFiles, int key) throws IOException {
		for (int i = 0; i < allFiles.size(); i++) {
			PrintWriter writer = new PrintWriter("dec_"+i+"_file.txt");
			for (int k = 0; k < allFiles.get(i).size() ; k++) {
				writer.println(Simple_Enc.enc(allFiles.get(i).get(k),key));
			}
			System.out.println("File Number "+(i+1)+" Successfully Decrypted");
			writer.close();
		}
	}
	/**
	 * @param path of folder with files to decrypt or just a file
	 * @param key to decrypt with
	 * @throws IOException
	 */
	public static void decFile (String path, int key) throws IOException {
		writeDec(DS.createDS(path), key);
	}
	/**using brute force with 2 threads, one has the first half of keys
	 * and the second has the next half, working together to cut off the time
	 * returns a text file with all the keys.
	 * @param path to folder to decrypt with Meir's song
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void bonusDec(String path) throws IOException, InterruptedException {
		PrintWriter writer = new PrintWriter("Keys.txt");
		List<ArrayList<String>>allData=DS.orgData(DS.firstSenWithName(path));
		Thread t1 =new Thread(()->{
			for (int i = 0; i < allData.size(); i++) {
				int j=0;//keys from 0-50000000
				boolean decrypt=false;
				String[] s=allData.get(i).toArray(new String[allData.size()]);
				String finalS=numsStrToWord(s);//get the first sentence to his dec form 
				while (!decrypt&&j<50000000) {
					String toDec = Simple_Enc.enc(finalS,j);
					char[] ascii=toDec.toCharArray();
					if(withinRange(ascii)){
						decrypt=true;
						writer.println(allData.get(i).get(0)+"- key: "+j);
					}
					j++;
				}
			}
		});
		t1.start();
		Thread t2 =new Thread(()->{
			for (int i = 0; i < allData.size(); i++) {
				int j=50000000;//keys from 50000000-100000000
				boolean decrypt=false;
				String[] s=allData.get(i).toArray(new String[allData.size()]);
				String finalS=numsStrToWord(s);
				while (!decrypt&&j<100000000) {
					String toDec = Simple_Enc.enc(finalS,j);
					char[] ascii=toDec.toCharArray();
					if(withinRange(ascii)){
						decrypt=true;
						writer.println(allData.get(i).get(0)+"- key: "+j);
					}
					j++;
				}
			}
		});
		t2.start();
		t1.join();
		t2.join();
		writer.close();
	}
	/**check if the dec sentence is really a sentence 
	 * @param array of the dec sentence by some key
	 * @return true/false
	 */
	public static boolean withinRange(char[] ascii) {
		int check=0;
		boolean flag=false;
		for (int i = 0; i < ascii.length; i++) {
			if(((int)ascii[i]>1487 && (int)ascii[i]<1515)||((int)ascii[i]==32)) {
				check+=1;
			}
			else {
				return false;
			}
		}
		if(check==ascii.length) {
			flag=true;
		}
		return flag;
	}
	/**take the numbers at the file and make an ascii sentence from it
	 * @param array of numbers to ascii code
	 * @return the real sentence to dec
	 */
	public static String numsStrToWord(String[] s) {
		String ans="";
		for (int i = 1; i < s.length; i++) {
			if(s[i]!=null) {
				int num = Integer.valueOf(s[i]);
				char c = (char)num;
				ans += String.valueOf(c);	
			}
		}
		return ans;
	}
}
