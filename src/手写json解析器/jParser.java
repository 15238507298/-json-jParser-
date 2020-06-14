package 手写json解析器;
/**
 * jParser支持获取对象与元素,如果非正常json格式数据,或索引和key值不存在，结果将返回为空<null>
 * 1.获取对象：jParser.get(json数据,"第一个索引或者key值","第二个索引或者key值"...)
 * 2.获取元素：jParser.get(json数据,"第一个索引或者key值>第二个索引或者key值>..>..")
 * @author lius
 *
 */
public class jParser extends jsonHandler {
	/**
	 * <p>获取json的集合对象</p>
	 * @param json
	 * @param type
	 * @return
	 * 例如:json解析.get(data,"translateResult","0","0")
	 */
	public static Object get (String json,String... index) {
		return multiPartHandler(json, index, 0); 
	}
	/**
	 * <p>获取json的集合元素</p>
	 * @param json  data source
	 * @param index	index or key  
	 * @param type	result type
	 * @return
	 * 例如:json解析.get(data,"translateResult>0>0>tgt").toString()
	 */
	public static Object get (String json,String index) {
		String[] indexs = index.split(">");
		return multiPartHandler(json, indexs, 1);//0.获取集合对象 1.获取元素
	}
}
