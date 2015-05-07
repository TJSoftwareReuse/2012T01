import java.util.Iterator;
import java.util.Map;

import com.eva.me.cm.ConfigUtil;


public class Test {
	public static void main(String [] args) {
		/*
		 * first load, if not call load, when you set property, will automatic generate file 'config.properties'
		 */
//		ConfigUtil.getInstance().loadConfigFile();
		
		/*
		 * set properties
		 */
		ConfigUtil.getInstance().setProperty("key0", "value00");
		ConfigUtil.getInstance().setProperty("key1", "value1");
		ConfigUtil.getInstance().setProperty("key2", "value2");
		ConfigUtil.getInstance().setProperty("key0", "value3");//will overwrite value of key 'key0'
		
		/*
		 * show all properties
		 */
		Map<String, String> maps= ConfigUtil.getInstance().showConfigFileContent();
		Iterator iterator = maps.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			System.out.println("key = "+key+" value = "+value);
		}
		
		ConfigUtil.getInstance().changeConfigFilePath("config2.properties");//have another file but will not be able to open by the editor// can be open
//		ConfigUtil.getInstance().loadConfigFile();
		ConfigUtil.getInstance().setProperty("key0", "value00");
		ConfigUtil.getInstance().setProperty("key1", "value11");
		ConfigUtil.getInstance().setProperty("key2", "value22");
		ConfigUtil.getInstance().setProperty("key0", "value33");//will overwrite value of key 'key0'
		
	}
}
