package name.cdd.product.clzsearch.userlibmaker.business.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * <p>�ļ�����: XmlIO</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>�������: 2011��11��23��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author jingxueshi_10118495
 */
public class XmlIO
{
    //****** �����: �������� **********************************************************************/

    private File xmlFileName = null;
    private Document cloneDocument = null;
    private Element configRoot = null;

    //****** �����: �������� **********************************************************************/

    public XmlIO(File xmlFile)
    {
        this.xmlFileName = xmlFile;
        try
        {
            init();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void init() throws Exception
    {
        SAXBuilder builder = new SAXBuilder(false);
        Document originalDocument = builder.build(xmlFileName);
        cloneDocument = (Document)originalDocument.clone();
        configRoot = cloneDocument.getRootElement();
    }

    public Element getRootElement()
    {
        return configRoot;
    }

    /**
     * ��������Ķ༶��������ȡָ���������Ԫ�ض���
     * ��:getElement(String[]{agent,database})����database������Element
     * @param segName ������
     * @return ����Ԫ��,ע��:��Ԫ�ؿ��ܻ���child��children
     */
    public Element getElement(String[] segNames)
    {
        Element segElement = configRoot;
        int size = segNames.length;
        for(int i = 0; i < size; i++)
        {
            segElement = segElement.getChild(segNames[i]);
            if(segElement == null)
            {
                return null;
            }
        }
        return segElement;
    }

    /**
     * ���ݶ�������������ȡ����ֵ
     * ��:getAttribute({"database","host"})
     * @param allNames ����+������
     * @return ע��:allName[size]Ϊ������,֮ǰ����Element��
     */
    public String getAttribute(String[] allNames)
    {
        String[] segNames = new String[allNames.length - 1];
        System.arraycopy(allNames, 0, segNames, 0, segNames.length);
        Element e = getElement(segNames);
        return (e == null) ? null : e.getAttributeValue(allNames[segNames.length]);
    }

    /**
     * ���ݶ�����������������������ֵ
     * ��:setAttribute(String[]{"database","host"},"value")
     * @param allNames ����+������
     * @return
     */
    public void setAttributeValue(String[] allNames, String value)
    {
        String[] segNames = new String[allNames.length - 1];
        System.arraycopy(allNames, 0, segNames, 0, segNames.length);
        Element e = getElement(segNames);
        Attribute attribute = e.getAttribute(allNames[segNames.length]);
        attribute.setValue(value);
    }

    public void setAttributeValue(Attribute attribute, String value)
    {
        attribute.setValue(value);
    }

    public void appendAttributeValue(Attribute attribute, String append, String seperator)
    {
        String oldValue = attribute.getValue();

        String newValue = null;
        if(oldValue.equalsIgnoreCase(""))
        {
            newValue = append;
        }
        else
        {
            newValue = oldValue + "," + append;
        }

        attribute.setValue(newValue);

    }

    public boolean save()
    {
        XMLOutputter outputter = new XMLOutputter();
        outputter.setEncoding(System.getProperty("file.encoding"));
        outputter.setTextTrim(false);
        outputter.setIndent("    ");
        outputter.setNewlines(true);
        outputter.setTextNormalize(true);
        outputter.setExpandEmptyElements(false);
        
        FileOutputStream fs = null;

        try
        {
            fs = new FileOutputStream(this.xmlFileName);

            outputter.output(configRoot.getDocument(), fs);;
            
            return true;
        }
        catch(IOException ex)
        {
        }
        finally
        {
            closeOutputStream(fs);
        }

        return false;
    }
    
    private void closeOutputStream(OutputStream fs)
    {
        if(fs != null)
        {
            try
            {
                fs.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Element> getAllElements(Element startElement, String... elementsName)
    {
        Element e = startElement;
        
        for(int i = 0; i < elementsName.length - 1; i++)
        {
            if(e != null)
            {
                e = e.getChild(elementsName[i]);
            }
        }
        
        ArrayList<Element> rtnElements = new ArrayList<Element>();
        
        if(e != null)
        {
            Iterator it = e.getChildren(elementsName[elementsName.length - 1]).iterator();
            while(it.hasNext())
            {
                rtnElements.add((Element)it.next());
            }
        }
        
        return rtnElements;
    }
    
    public static ArrayList<String> getAllElementsValue(String attributeName, Element startElement, String... elementsName)
    {
        ArrayList<Element> elements = getAllElements(startElement, elementsName);
        
        ArrayList<String> attributeValueList = new ArrayList<String>();
        for(Element e : elements)
        {
            attributeValueList.add(e.getAttributeValue(attributeName));
        }
        
        return attributeValueList;
    }
}
