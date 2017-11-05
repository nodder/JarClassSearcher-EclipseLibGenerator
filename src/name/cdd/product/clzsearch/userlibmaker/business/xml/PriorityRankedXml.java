package name.cdd.product.clzsearch.userlibmaker.business.xml;

import java.io.File;
import java.util.ArrayList;

import org.jdom.Element;

public abstract class PriorityRankedXml
{
    private XmlIO xmlIo;
    
    protected PriorityRankedXml(File file)
    {
        xmlIo = new XmlIO(file);
    }
    
    public String[] getFilePatternList()
    {
        ArrayList<Element> elements = XmlIO.getAllElements(xmlIo.getRootElement(), "filelist-pattern", "file-pattern");
        
        String[] fileListPattern = new String[elements.size()];
        for(int i = 0; i < elements.size(); i++)
        {
            Element e = elements.get(i);
            
            fileListPattern[i] = e.getAttributeValue("file");
        }
        
        return fileListPattern;
    }
}
