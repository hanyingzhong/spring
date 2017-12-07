package com.xstream.xml;

import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.io.xml.DomDriver;  
/** 
 * XStream工具类 
 * @author sun.kai 
 * 2016年8月13日 
 */  
public class XStreamUtil {  
  
    private static XStream xStream;  
      
    //JVM加载类时会执行这些静态的代码块，如果static代码块有多个，JVM将按照它们在类中出现的先后顺序依次执行它们，每个代码块只会被执行一次。  
    static{  
        xStream = new XStream(new DomDriver());  
        /* 
         * 使用xStream.alias(String name, Class Type)为任何一个自定义类创建到类到元素的别名 
         * 如果不使用别名，则生成的标签名为类全名 
         */  
        xStream.alias("person", Person.class);  
        xStream.alias("address", Address.class);  
        //将某一个类的属性，作为xml头信息的属性，而不是子节点  
        //xStream.useAttributeFor(Address.class, "country");  
        //对属性取别名  
        //xStream.aliasField("省", Address.class,"province");  
    }  
      
    //xml转java对象  
    public static Object xmlToBean(String xml){  
        return xStream.fromXML(xml);  
    }  
      
    //java对象转xml  
    public static String beanToXml(Object obj){  
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(obj);  
    }  
}  