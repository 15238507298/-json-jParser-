# jParser<json解析器>
手写json解析器&lt;jParser>
jParser支持获取对象与元素,如果非正常json格式数据,或索引和key值不存在，结果将返回为空<null>
 * 1.获取对象：jParser.get(json数据,"第一个索引或者key值","第二个索引或者key值"...)
 * 2.获取元素：jParser.get(json数据,"第一个索引或者key值>第二个索引或者key值>..>..")
 
