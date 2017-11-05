package name.cdd.product.clzsearch.userlibmaker.business.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class QuickerJarSearcher implements IJarsSearcher
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private IJarsSearcher searcher;
    
    private File[] prioritySearchPathsOrFiles;
    private File[] lastMatchedFiles = new File[0];
    
    public QuickerJarSearcher(File... prioritySearchPathsOrFiles)
    {
        this.prioritySearchPathsOrFiles = prioritySearchPathsOrFiles;
        
        this.searcher = new JarSearcher();
    }
    
    @Override
    //每次先搜索lastHitFile，再prioritySearchPathOrFile，最后所有fromDir
    //使用场景特殊，必须保证前者是后者的子目录或者子目录中的文件
    public ArrayList<File> search(String targetClassName, File fromDirOrFile) throws IOException
    {
        File[][] filesSearchOrder = assembleSearchOrder(fromDirOrFile);
        
        return searchByOrder(targetClassName, filesSearchOrder);
    }
    
    private File[][] assembleSearchOrder(File fromDirOrFile)
    {
        return new File[][] {lastMatchedFiles, prioritySearchPathsOrFiles, new File[] {fromDirOrFile}};
    }

    private ArrayList<File> searchByOrder(String targetClassName, File[][] filesSearchOrder) throws IOException
    {
        for(File[] givenPathsOrFiles : filesSearchOrder)
        {
            ArrayList<File> result = searchUntilFirstHit(targetClassName, givenPathsOrFiles);
            if(isFileSearched(result))
            {
                return result;
            }
        }
        
        return new ArrayList<File>();
    }

    private boolean isFileSearched(ArrayList<File> result)
    {
        return result.size() > 0;
    }

    private ArrayList<File> searchUntilFirstHit(String targetClassName, File[] givenPathsOrFiles) throws IOException
    {
        for(File pathOrFile : givenPathsOrFiles)
        {
            ArrayList<File> result = searcher.search(targetClassName, pathOrFile);
            if(isFileSearched(result))
            {
                updateLastHitFile(result);
                return result;
            }
        }

        return new ArrayList<File>();
    }

    private void updateLastHitFile(ArrayList<File> pathOrFiles)
    {
        if(!arrFileEqualsFileList(lastMatchedFiles, pathOrFiles))
        {
            lastMatchedFiles = pathOrFiles.toArray(new File[0]);
            logger.info("  --update last matched file to " + Arrays.toString(lastMatchedFiles));
        }
    }
    
    private boolean arrFileEqualsFileList(File[] arrFile, ArrayList<File> fileList)
    {
        if(arrFile.length != fileList.size())
        {
            return false;
        }
        
        for(int i = 0; i < arrFile.length; i++)
        {
            if(!arrFile[i].equals((fileList).get(i)))
            {
                return false;
            }
        }
        
        return true;
    }
}
