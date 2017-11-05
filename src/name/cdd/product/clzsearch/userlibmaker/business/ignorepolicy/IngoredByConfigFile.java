package name.cdd.product.clzsearch.userlibmaker.business.ignorepolicy;

import name.cdd.product.clzsearch.userlibmaker.business.xml.IgnoreListXml;

import org.springframework.util.AntPathMatcher;

public class IngoredByConfigFile implements IgnorePolicy
{
    private String[] ingoreListPattern;
    private AntPathMatcher matcher = new AntPathMatcher();

    public IngoredByConfigFile()
    {
        this.ingoreListPattern = initIgnoreListPattern();
    }
    
    public String[] initIgnoreListPattern()
    {
        return IgnoreListXml.getInstance().getClassPatternList();
    }

    @Override
    public boolean isIgnored(String className)
    {
        for(String pattern : ingoreListPattern)
        {
            if(matcher.match(pattern, className))
            {
                return true;
            }
        }
        
        return false;
    }

}
