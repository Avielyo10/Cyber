package CyberHomeWork3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DS{
	/**take the first sentence from each file 
	 * @param path to folder to decrypt
	 * @return DS 
	 * @throws IOException
	 */
	public static ArrayList<String> firstSenWithName(String path) throws IOException {
		ArrayList<String> line = new ArrayList<String>();
		@SuppressWarnings("resource")
		File folder = new File(path);//take folder
		File[] listOfFiles = folder.listFiles();{//create array of files in this folder
			for (int i = 0; i < listOfFiles.length; i++) {//Iterate the files
				File file = listOfFiles[i];//insert to the array
				if (file.isFile() && file.getName().endsWith(".txt")) {//take the csv file 
					FileReader in = new FileReader(listOfFiles[i]);
					BufferedReader br = new BufferedReader(in);
					line.add(file.getName()+" "+br.readLine());
					in.close();
				}
			}
		}
		return line;
	}
	/**
	 * @param line of data with all the first sentence
	 * @return organized data to work with 
	 */
	public static List<ArrayList<String>> orgData(ArrayList<String> line){
		List<ArrayList<String>> allData=new ArrayList<ArrayList<String>>();
		for (int i = 0; i < line.size(); i++) {
			ArrayList<String> newLine= new ArrayList<String>();
			String str[]=line.get(i).split(" ");
			for (int j = 0; j < str.length; j++) {
				newLine.add(str[j]);	
			}
			allData.add(newLine);
		}
		return allData;
	}
	/**
	 * @param path of folder with files or a file to encrypt/decrypt with one key
	 * @return DS that makes it easy to do
	 * @throws IOException
	 */
	public static List<ArrayList<String>> createDS(String path) throws IOException {
		List<ArrayList<String>> allFiles = new ArrayList<ArrayList<String>>();
		@SuppressWarnings("resource")
		File folderOrFile = new File(path);//take folder
		if (folderOrFile.isFile() && folderOrFile.getName().endsWith(".txt")) {//take the text file 
			ArrayList<String> _file = new ArrayList<String>();
			FileReader in = new FileReader(folderOrFile);
			BufferedReader br = new BufferedReader(in);
			String s;
			while((s=br.readLine())!=null) {
				_file.add(s);
			}
			allFiles.add(_file);
			in.close();
		}else {
			File[] listOfFiles = folderOrFile.listFiles();{//create array of files in this folder
				for (int i = 0; i < listOfFiles.length; i++) {//Iterate the files
					File file = listOfFiles[i];//insert to the array
					if (file.isFile() && file.getName().endsWith(".txt")) {//take the text file 
						ArrayList<String> _file = new ArrayList<String>();
						FileReader in = new FileReader(listOfFiles[i]);
						BufferedReader br = new BufferedReader(in);
						String s;
						while((s=br.readLine())!=null) {
							_file.add(s);
						}
						allFiles.add(_file);
						in.close();
					}
				}
			}	
		}
		return allFiles;
	}
}