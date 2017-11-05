package name.cdd.product.clzsearch.userlibmaker.business.selectpolicy;

import java.io.File;
import java.util.ArrayList;

public class AlwaysSelectFirstOne implements SelectionPolicy
{
    @Override
    public File selectOneFromJars(ArrayList<File> jars)
    {
        return jars.get(0);
    }

}
