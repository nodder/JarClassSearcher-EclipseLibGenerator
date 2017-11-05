【软件名称】

Eclipse User-Libraries Maker
--------------------------------

【功能简述】

给定Java代码路径和Jar包根路径，生成Eclipse环境所需的user-libraries文件。
尤其针对U31网管优化，能快速生成最精简的user-libraries。

--------------------------------

【使用方法】
第一次使用时需要指定JDK1.6及以上版本的JAVA_HOME。
可以设定操作系统的JAVA_HOME环境变量，也可以只在本程序的start.bat中指定JAVA_HOME。


1、运行startGui.bat，打开界面
2、指定JAVA代码路径、jar包根路径等条件，点击Start Analysis按钮
3、 等待操作完成后，点击Export按钮以导出userlibraries文件。

本程序提供了3种Generating Template，根据实际需要选择合适的template：
uep4x_whole_src: 生成整个uep4x网管所需最小user libraries，此时JAVA代码路径必须为SVN库根目录，jar包根路径必须为网管根目录；
uep4x:可以指定任何次级的SVN库（例如commonsh/src），生成userlibraries文件。相比custom，此template针对uep4x的分析处理进行了优化；
custom：用户自定义，无特殊优化。

【获取4X代码依赖的Library方法】 【推荐】
1、更新SVN库
2、安装最新网管每日构造：安装时选择最大规模，勾选所有组件
3、运行startGui.bat，打开界面
4、Jar File Root Directory输入步骤二中的网管安装目录
5、Source File Directory输入SVN库根目录，即commonsh所在代码的父目录
6、Generating Template选择uep_4x_whole_src
7、点击Start Analysis，等待约11分钟即可完成
8、点击Export to Library，导出userlibrary文件。

--------------------------------

【使用限制】

出现下面几种情况，本程序生成的user-libraries文件可能会遗漏部分jar包
	a)间接引用class
	b)类内部使用new全包名的方式(例如new org.jdom.IllegalDataException())引用另一个类
	c)Java文件开头声明部分的import行编写不规范
	
此时有两种处理方法，推荐第二种：
	a)手工添加jar文件到user-libraries
	b)修改程序配置文件，即将遗漏的jar包添加到post-processing.xml。
--------------------------------

  
【版本更新记录】

2013.1.5		V1.0 Beta






