��������ơ�

Eclipse User-Libraries Maker
--------------------------------

�����ܼ�����

����Java����·����Jar����·��������Eclipse���������user-libraries�ļ���
�������U31�����Ż����ܿ�����������user-libraries��

--------------------------------

��ʹ�÷�����
��һ��ʹ��ʱ��Ҫָ��JDK1.6�����ϰ汾��JAVA_HOME��
�����趨����ϵͳ��JAVA_HOME����������Ҳ����ֻ�ڱ������start.bat��ָ��JAVA_HOME��


1������startGui.bat���򿪽���
2��ָ��JAVA����·����jar����·�������������Start Analysis��ť
3�� �ȴ�������ɺ󣬵��Export��ť�Ե���userlibraries�ļ���

�������ṩ��3��Generating Template������ʵ����Ҫѡ����ʵ�template��
uep4x_whole_src: ��������uep4x����������Сuser libraries����ʱJAVA����·������ΪSVN���Ŀ¼��jar����·������Ϊ���ܸ�Ŀ¼��
uep4x:����ָ���κδμ���SVN�⣨����commonsh/src��������userlibraries�ļ������custom����template���uep4x�ķ�������������Ż���
custom���û��Զ��壬�������Ż���

����ȡ4X����������Library������ ���Ƽ���
1������SVN��
2����װ��������ÿ�չ��죺��װʱѡ������ģ����ѡ�������
3������startGui.bat���򿪽���
4��Jar File Root Directory���벽����е����ܰ�װĿ¼
5��Source File Directory����SVN���Ŀ¼����commonsh���ڴ���ĸ�Ŀ¼
6��Generating Templateѡ��uep_4x_whole_src
7�����Start Analysis���ȴ�Լ11���Ӽ������
8�����Export to Library������userlibrary�ļ���

--------------------------------

��ʹ�����ơ�

�������漸����������������ɵ�user-libraries�ļ����ܻ���©����jar��
	a)�������class
	b)���ڲ�ʹ��newȫ�����ķ�ʽ(����new org.jdom.IllegalDataException())������һ����
	c)Java�ļ���ͷ�������ֵ�import�б�д���淶
	
��ʱ�����ִ��������Ƽ��ڶ��֣�
	a)�ֹ����jar�ļ���user-libraries
	b)�޸ĳ��������ļ���������©��jar����ӵ�post-processing.xml��
--------------------------------

  
���汾���¼�¼��

2013.1.5		V1.0 Beta






