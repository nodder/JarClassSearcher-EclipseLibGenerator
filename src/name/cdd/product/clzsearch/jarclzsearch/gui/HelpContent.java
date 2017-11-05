package name.cdd.product.clzsearch.jarclzsearch.gui;

public class HelpContent
{
    private static final String content = 
    	  "功能说明："
        + "\n从指定路径包含的一系列jar包里面，查找指定java Class被包含的具体位置。"
        + "\n--------------------------------"
        + "\n使用方法："
        + "\n1、Jar File Path字段输入需要查找的jar文件所在路径，例如:  D:\\ems\\ums-server"
        + "\n2、Class Name字段输入需要查找的java类，类名包括包路径，支持通配符"
        + "\n例如： com.mysql.jdbc.Driver"
        + "\n或者：*.mysql.jdbc.Driver"
        + "\n或者：com.mysql.jdbc.*"
        + "\n3、点击Search按钮即可。"
        + " \n--------------------------------"
        + "\nAuthor:             Chen Duoduo"
        + "\nLast update Date:   2013.01.04"
        + "\nEmail:              nodder001@gmail.com"
        + "\n";

    public static String getContent()
    {
        return content;
    }

}
