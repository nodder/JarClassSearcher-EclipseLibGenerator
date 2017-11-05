package name.cdd.product.clzsearch.jarclzsearch.gui;

import java.io.Serializable;
import java.util.ArrayList;

public class ConditionInfo implements Serializable
{
    private static final long serialVersionUID = -5213948924769071884L;
    
    public String className = "";
    public String path = "";
    public ArrayList<String> pathList = new ArrayList<String>();
}