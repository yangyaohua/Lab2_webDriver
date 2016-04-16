package driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadFile {

	public static List<HashMap<String,String>> getCsvInfo(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		try{
			BufferedReader reader = new BufferedReader(new FileReader("d:\\info.csv"));
			String line=null;
			while((line=reader.readLine()) != null){
				String item[] = line.split(",");
				String key=item[0];
				String value = item[1];
				HashMap<String,String> map = new HashMap<String,String>();
				map.put(key, value);
				list.add(map);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}
