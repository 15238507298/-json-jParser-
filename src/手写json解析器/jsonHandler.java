package 手写json解析器;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * <p>json数据处理类</p>
 * @author lius
 */
public class jsonHandler {
	/**
	 * 处理字符串json数据
	 * @param arrayStr
	 * @return
	 */
	private static String[] handlerJsonData(String arrayStr) {
		int turnOff = 0;
		//解密
		String[] strArray = arrayStr.replaceAll("@!g!@", ",").
				replaceAll("@!m!@", ":").split("");
		for (int t=0;t<strArray.length;t++) {
			
//			System.out.println(strArray[t]);
			if(turnOff==0 ||turnOff==1) {
				//加密
				if(strArray[t].equals(","))strArray[t] ="@!-!@";	
				if(strArray[t].equals("{") || strArray[t].equals("[")) 
				{strArray[t]=turnOff==0?"":strArray[t];turnOff++;};
				if(strArray[t].equals("}") || strArray[t].equals("]")) {
					--turnOff;if(turnOff ==0) strArray[t]="";	
				};
				strArray[t]=strArray[t].equals("\"")?"":strArray[t];
			}else {
				//加密
				if(strArray[t].equals(","))strArray[t] ="@!g!@";
				if(strArray[t].equals(":"))strArray[t] ="@!m!@";
				if(strArray[t].equals("{") || strArray[t].equals("["))turnOff++;
				if(strArray[t].equals("}") || strArray[t].equals("]")) {
					--turnOff;if(turnOff ==0) strArray[t]="";};
			}			
		}
		strArray = Stream.of(strArray).collect(Collectors.joining()).split("@!-!@");	
		return strArray;
	}
	/**
	 * 处理json map数据
	 * @param arrayStr
	 * @return
	 */
	private static Map<String,Object> parserMap(String arrayStr) {
		String[] strArray = handlerJsonData(arrayStr);
		Map<String, Object> value = Stream.of(strArray).map(str->{
			String[] kv = str.split(":");
			//解密
			kv[1]=kv[1].replaceAll("@!g!@", ",").replaceAll("@!m!@", ":");
			return kv;
		}).collect(Collectors.toMap((kv)->kv[0], (kv)->kv[1]));
		return value;
	}
	/**
	 * 处理json 集合list数据
	 * @param arrayStr
	 * @return
	 */
	private static List<String> parserArrays(String arrayStr) {
		String[] strArray = handlerJsonData(arrayStr);
		List<String> value = Stream.of(strArray)
				//解密
				.map(str->str.replaceAll("@!g!@", ",").replaceAll("@!m!@", ":"))
				.collect(Collectors.toList());
		return value;
	}
	
	
	
	/**
	 * <p>获取集合对象或元素</p>
	 * @param json
	 * @param index
	 * @param type
	 * @return
	 */
	protected static Object multiPartHandler(String json,String[] index,int type) {
		Object obj= null;
		int types = 0;
		for (String indexItem : index) {
			//判断json的结果类型
			if(json.charAt(0)=='{') {
				obj = type==0?parserMap(json)
						:parserMap(json).get(indexItem).toString();
			}else if(json.charAt(0)=='[') {
				obj = type==0?parserArrays(json)
						:parserArrays(json).get(Integer.parseInt(indexItem));
			}else {
				obj= null; 
			}
			json = obj.toString();
		}
		return obj;
	}
}
