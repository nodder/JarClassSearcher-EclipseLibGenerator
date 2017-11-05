package name.cdd.product.clzsearch.userlibmaker;

import java.io.File;
import java.io.IOException;

import name.cdd.product.clzsearch.userlibmaker.business.PathTransfer;
import name.cdd.product.clzsearch.userlibmaker.business.UserLibMakerBusiness;
import name.cdd.product.clzsearch.userlibmaker.business.filterpolicy.FilterByConfigFile;
import name.cdd.product.clzsearch.userlibmaker.business.filterpolicy.FilterPolicy;
import name.cdd.product.clzsearch.userlibmaker.business.ignorepolicy.IngoredByConfigFile;
import name.cdd.product.clzsearch.userlibmaker.business.search.QuickerJarSearcher;
import name.cdd.product.clzsearch.userlibmaker.business.selectpolicy.AlwaysSelectFirstOne;
import name.cdd.product.clzsearch.userlibmaker.business.selectpolicy.MixSelectionPolicy;
import name.cdd.product.clzsearch.userlibmaker.business.xml.UnifiedPriorityRankedXml;
import name.cdd.product.clzsearch.userlibmaker.business.xml.UserConfigXml;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Start
{
    static
    {
        String propFile = new File("conf/log4j.properties").getAbsolutePath();
        PropertyConfigurator.configure(propFile);
    }
    
    private static Logger logger = Logger.getLogger(Start.class.getName());
    private static TimeMarker timeMarker = new TimeMarker();
    
    private static String[] javaSrcPaths;
    private static String jarFilePath;
    private static File generatedLibraryFile;
    private static String eclipseLibraryName;
    
    private static MixSelectionPolicy getSelectionPolicy()
    {
        MixSelectionPolicy selectionPolicy = new MixSelectionPolicy(true);
        
        FilterPolicy[] filterPolicy = new FilterPolicy[] {
            new FilterByConfigFile(UnifiedPriorityRankedXml.getInstance())
            };
        
        selectionPolicy.setFilterPolilcy(filterPolicy);
        selectionPolicy.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());
        
        return selectionPolicy;
    }
    
    private static File[] getPrioritySearchPath()
    {
        return new PathTransfer().filePatternListToFiles(UnifiedPriorityRankedXml.getInstance().getFilePatternList());
    }
    
    private static UserLibMakerBusiness getLibraryMaker(final String[] javaSrcPaths, final String jarFilePath)
    {
        UserLibMakerBusiness libMaker = new UserLibMakerBusiness(javaSrcPaths, jarFilePath);
        libMaker.setSelectionPolicy(getSelectionPolicy());
        libMaker.setIgnorePolicy(new IngoredByConfigFile());
        libMaker.setJarsSearcher(new QuickerJarSearcher(getPrioritySearchPath()));
//        libMaker.setJarsSearcher(new JarSearcher());
        return libMaker;
    }
    
    private static void printStartTime()
    {
        logger.info("Start Time : " + timeMarker.markStart());
    }
    
    
    private static void printEndTime()
    {
        logger.info("End Time : " + timeMarker.markend());
        logger.info("Time Cost : " + timeMarker.getTimeCost() + "s");
    }

    private static void initParameters()
    {
//        final String[] javaSrcPaths = new String[] {
//           "E:\\SVN_4X\\ZXNM01V4\\zxnm01\\map\\src\\com\\zte\\ums\\an\\map\\common\\util",
//           "E:\\SVN_4x\\ZXNM01V4\\zxnm01\\uni\\framework\\src\\com\\zte\\ums\\an\\uni\\framework\\atomicimpl",
//           "E:\\SVN_4x\\ZXNM01V4\\zxnm01\\common_sh\\testsrc\\com\\zte\\ums\\an\\commonsh\\ancmdtree"};
//       final String javaSrcPath = "E:\\SVN_4X\\ZXNM01V4\\zxnm01\\";
        
//        javaSrcPaths = new String[] {"E:\\SVN_4X\\ZXNM01V4\\zxnm01\\map\\src\\com\\zte\\ums\\an\\map\\common\\util"};
        
        
//        javaSrcPaths = new String[] {"E:/SVN_4X/ZXNM01V4/zxnm01/map/src/com/zte/ums/an/map/common/util"};
//        jarFilePath = "E:\\V4X";
//        
//        generatedLibraryFile = new File("output/uep4x.libraries");
//        eclipseLibraryName = "UEP 4X Library";
        
        javaSrcPaths = UserConfigXml.getInstance().getSourceFolders();
        jarFilePath = UserConfigXml.getInstance().getJarDirectory();
        
        generatedLibraryFile = new File(UserConfigXml.getInstance().getOutputLibraryFileName());
        eclipseLibraryName = UserConfigXml.getInstance().getOutputLibraryName();
    }
    public static void main(String[] args) throws IOException
    {
        initParameters();
        printStartTime();
        
        UserLibMakerBusiness libMaker = getLibraryMaker(javaSrcPaths, jarFilePath);
        libMaker.generateLibrary(generatedLibraryFile, eclipseLibraryName);
        
        printEndTime();
    }
}
