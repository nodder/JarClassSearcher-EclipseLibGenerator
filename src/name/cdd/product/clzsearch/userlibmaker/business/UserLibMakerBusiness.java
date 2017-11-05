package name.cdd.product.clzsearch.userlibmaker.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import name.cdd.product.clzsearch.business.search4classes.ImportedClassSearchInJavaFile;
import name.cdd.product.clzsearch.business.search4java.JavaFileSearch;
import name.cdd.product.clzsearch.userlibmaker.business.ignorepolicy.IgnorePolicy;
import name.cdd.product.clzsearch.userlibmaker.business.manager.ClassAndJavaListManager;
import name.cdd.product.clzsearch.userlibmaker.business.manager.JarAndClassListManager;
import name.cdd.product.clzsearch.userlibmaker.business.postprocess.PostProcess;
import name.cdd.product.clzsearch.userlibmaker.business.postprocess.PostProcessFacotry;
import name.cdd.product.clzsearch.userlibmaker.business.search.IJarsSearcher;
import name.cdd.product.clzsearch.userlibmaker.business.selectpolicy.SelectionPolicy;
import name.cdd.product.clzsearch.userlibmaker.business.xml.PostProcessXml;
import name.cdd.product.clzsearch.userlibmaker.business.xml.UserConfigXml;

import org.apache.log4j.Logger;

public class UserLibMakerBusiness
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private SelectionPolicy selectionPolicy;
    private String[] javaSrcPaths;
    private String jarFilePath;
    private IgnorePolicy ignorePolicy;
    private IJarsSearcher jarSearcher;
    
    
    public UserLibMakerBusiness(String[] javaSrcPaths, String jarFilePath)
    {
        this.javaSrcPaths = javaSrcPaths;
        this.jarFilePath = jarFilePath;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy)
    {
        this.selectionPolicy = selectionPolicy;
    }
    
    public void setIgnorePolicy(IgnorePolicy ignorePolicy)
    {
        this.ignorePolicy = ignorePolicy;
    }
    
    public void setJarsSearcher(IJarsSearcher jarSearcher)
    {
        this.jarSearcher = jarSearcher;
    }

    public void generateLibrary(File outputFile, String eclipseLibraryName) throws IOException
    {
        Collection<File> archivedJarlist = getArchivedJarList();

        Collection<File> processesArchivedJarList = doPostProcess(archivedJarlist);

        doExport(processesArchivedJarList, outputFile, eclipseLibraryName);

        logger.info("\nAll Completed!");
    }

    private Collection<File> doPostProcess(Collection<File> archivedJarlist)
    {
        logger.info("\n======================== Doing post-processing...");
        String[][] processes = PostProcessXml.getInstance().getAllPostProcess();
        for(String[] process : processes)
        {
            String action = process[0];
            String jarFilePattern = process[1];
            
            PostProcess pp = PostProcessFacotry.getPostProcess(action, UserConfigXml.getInstance().getJarDirectory());
            archivedJarlist = pp.processFileCollection(archivedJarlist, jarFilePattern);
        }
        
        logger.info("\n  -----------------Archived Jar List After process -----------------:\n");
        printResult(archivedJarlist);
        return archivedJarlist;
    }

    private void doExport(Collection<File> archivedJarlist, File outputFile, String eclipseLibraryName) throws FileNotFoundException
    {
        logger.info("\n======================== Exporting to " + outputFile.getAbsolutePath() + "... ");
        (new EclipseLibFileExporter()).export(archivedJarlist, eclipseLibraryName, outputFile);
    }

    private Collection<File> getArchivedJarList() throws IOException
    {
        JarAndClassListManager jarAndClassListManager = new JarAndClassListManager();
        ClassAndJavaListManager classAndJavaListManager = new ClassAndJavaListManager();
        
        logger.info("======================== Analysising java files...");
        
        for(int i = 0; i < javaSrcPaths.length; i++)
        {
            String OneJavaSrcPath = javaSrcPaths[i];
            logger.info("\n(Jar_Path:" + (i+1) + "/" + javaSrcPaths.length + ") Searching java files in " + OneJavaSrcPath + "...");
            ArrayList<File> javaFiles = new JavaFileSearch().searchJavaFiles(new File(OneJavaSrcPath));
            logger.info(" " + javaFiles.size() + " found.\n");
            
            for(int j = 0; j < javaFiles.size(); j++)
            {
                File javaFile = javaFiles.get(j);
                ArrayList<String> importedClasses = new ImportedClassSearchInJavaFile().searchImportedClasses(javaFile);
                classAndJavaListManager.add(javaFile, importedClasses);
                logger.info("(Java_File:" + (j+1) + "/" + javaFiles.size() + " in Jar_Path:" + (i+1)  + "/" + javaSrcPaths.length + ") " 
                    + "analysising imported classes in java file:" + javaFile.getName());
            }
        }
        
        Collection<String> classNameList = classAndJavaListManager.getManagedClassNameList();
        final long totalClassCount = classNameList.size(); 
        long currentIndex = 0;
        logger.info("\n    ---- Analysis completed, total class count:" + totalClassCount + "\n");
        
        logger.info("======================== Searching jar files... \n");
        Iterator<String> it4ClassName = classNameList.iterator();
        while(it4ClassName.hasNext())
        {
            String className = it4ClassName.next();
            
            if(ignorePolicy.isIgnored(className))
            {
                logger.info("(" + ++currentIndex + "/" + totalClassCount + ") Ignored class :" + className);
            }
            else
            {
                logger.info("(" + ++currentIndex + "/" + totalClassCount + ") Searchng jar files for " + className + "...");
                
                ArrayList<File> relatedJarFiles = this.jarSearcher.search(className, new File(jarFilePath));
                printRelatedJarFiles(relatedJarFiles);
                
                File selectedJar = selectionPolicy.selectOneFromJars(relatedJarFiles);
                printSelectedJar(selectedJar);
                
                jarAndClassListManager.add(className, relatedJarFiles, selectedJar);
            }
        }
        
        Collection<File> archivedJarlist = jarAndClassListManager.getArchivesJarList();
        
        logger.info("  -----------------Archived Jar List -----------------:\n");
        printResult(archivedJarlist);
        return archivedJarlist;
    }

    private void printSelectedJar(File selectedJar)
    {
        if(null != selectedJar)
        {
            logger.info(" Actual selected jar:" + selectedJar.getAbsolutePath());
            logger.info("");
        }
    }

    private void printRelatedJarFiles(ArrayList<File> relatedJarFiles)
    {
        if(relatedJarFiles.isEmpty())
        { 
            logger.warn("    ----NO related jar file!");
        }
        else
        {
            for(File relatedJarFile : relatedJarFiles)
            {
                logger.info("    ----related jar file:" + relatedJarFile.getAbsolutePath());
            }
        }
        
        logger.info("");
    }

    private void printResult(Collection<File> archivedJars)
    {
        Iterator<File> it = archivedJars.iterator();
        
        while(it.hasNext())
        {
            logger.info(it.next().getAbsolutePath());
        }
    }
    
}
