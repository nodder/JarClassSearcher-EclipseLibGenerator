package name.cdd.product.clzsearch.userlibmaker.gui;

import java.io.File;

import name.cdd.product.clzsearch.userlibmaker.gui.view.UserLibMakerFrm;

import org.apache.log4j.PropertyConfigurator;


public class StartGui
{
    static
    {
        String propFile = new File("conf/log4j.properties").getAbsolutePath();
        PropertyConfigurator.configure(propFile);
    }
    
    public static void main(String[] args)
    {
        UserLibMakerFrm dlg = new UserLibMakerFrm();
        dlg.init();
    }
}
