package CyberHomeWork3;
import java.io.IOException;
import java.util.Random;
public class Simple_Enc
{//"C:\\Users\\Aviel\\eclipse-workspace\\Cyber\\testdata"
	public static int KEY = 12345678;
	public static void main(String[] a) throws IOException, InterruptedException { 
		long startTime = System.currentTimeMillis();
		enc.encFile("C:\\Users\\Aviel\\eclipse-workspace\\Cyber\\data", KEY);
		//dec.decFile("C:\\Users\\Aviel\\eclipse-workspace\\Cyber\\", KEY);
		//dec.bonusDec("C:\\Users\\Aviel\\eclipse-workspace\\Cyber\\data");
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Program Finished Within "+totalTime+"[ms]");
	}
	public static String enc(String msg, int key){
		String ans = "";
		Random rand = new Random(key);
		for(int i=0;i<msg.length();i=i+1) {
			char c = msg.charAt(i);
			int s = c;
			int rd = rand.nextInt()%(256*256);
			int s2 = s^rd;
			char c2 = (char)(s2);
			ans +=c2;
		}
		return ans;
	}
}