package name.cdd.product.clzsearch.business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

import name.cdd.product.clzsearch.business.search4classes.ClassSearch;
import name.cdd.product.clzsearch.business.search4jar.JarSearch;
import name.cdd.product.clzsearch.business.search4java.JavaFileSearch;
import name.cdd.product.clzsearch.userlibmaker.business.search.JarSearcher;

import org.springframework.util.AntPathMatcher;


public class Start
{
    private static void print(ArrayList<String> classes)
    {
        for(String className : classes)
        {
            System.out.println(className);
        }
    }
    
    private static void printFiles(ArrayList<File> files)
    {
        for(File file : files)
        {
            System.out.println(file.getAbsolutePath());
        }
    }
    
    private static void searchClass() throws IOException
    {
        JarFile jarFile = new JarFile("f:/test/test.jar");
        
        ArrayList<String> classes = new ClassSearch().searchClasses(jarFile);
        print(classes);
    }
    
    public static void main(String[] args) throws IOException
    {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("?:\\*", "c:\\test"));
        System.out.println(matcher.match("?:\\*", "cd:\\test"));
        System.out.println(matcher.match("?:\\*", ""));
//        searchClass();
//        searchJar();
//        search();
//        searchJavaFile();
    }

    private static void searchJavaFile() throws IOException
    {
        JavaFileSearch jFileSearch = new JavaFileSearch();
        ArrayList<File> jFiles = jFileSearch.searchJavaFiles(new File("T:\\SVN_3x\\ZXNM01V4_DOC\\summarize\\个人总结\\DSL开发人员\\陈多多\\8.MyJava\\java_src\\prj_class_searcher\\src\\"));
        
        printFiles(jFiles);
    }

    private static void search() throws IOException
    {
        JarSearcher search = new JarSearcher();
//        ArrayList<File> matchedFiles = search.search("com.zte.ums.an.a10.common.*", new File("E:\\V4X"));
        ArrayList<File> matchedFiles = search.search("com.zte.ums.an.a10.common.*", new File("E:\\V4X\\ums-server\\procs\\ppus\\an.ppu\\an-a10.pmu\\an-a10-v5trace.par\\lib\\ums-an-a10-v5trace-svr.jar"));
        printFiles(matchedFiles);
    }

    private static void searchJar() throws IOException
    {
        JarSearch jarSearch = new JarSearch();
        ArrayList<File> jarFiles = jarSearch.searchJars(new File("f:/test"));
        
        printFiles(jarFiles);
    }
}
