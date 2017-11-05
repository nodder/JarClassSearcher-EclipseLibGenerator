package name.cdd.product.clzsearch.userlibmaker.business.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.jdom.Element;

public class PostProcessXml
{
    private static PostProcessXml instance = new PostProcessXml();
    private XmlIO xmlIo;
    
    private PostProcessXml()
    {
        xmlIo = new XmlIO(new File("conf/" +  ActiveDir.getActiveDir(), "Post-Processing.xml"));
//        xmlIo = new XmlIO(new File("D:/Tool/Eclip/seLibraryGenerator/conf/Post-Processing.xml"));
    }
    
    public static PostProcessXml getInstance()
    {
        return instance; 
    }
    
    public String[][] getAllPostProcess()
    {
        ArrayList<Element> elements = XmlIO.getAllElements(xmlIo.getRootElement(), "post-processes", "process");
        
        String[][] processes = new String[elements.size()][2
                                                           ];
        for(int i = 0; i < elements.size(); i++)
        {
            Element e = elements.get(i);
            
            processes[i][0] = e.getAttributeValue("action");
            processes[i][1] = e.getAttributeValue("jar-file");
        }
        
        return processes;
    }

    public static void main(String[] args)
    {
        String[][] ps = PostProcessXml.getInstance().getAllPostProcess();
        
        System.out.println(Arrays.deepToString(ps));
    }
}
