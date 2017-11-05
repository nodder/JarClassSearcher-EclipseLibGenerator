package name.cdd.product.clzsearch.userlibmaker.business.selectpolicy;

import java.io.File;
import java.util.ArrayList;

import name.cdd.product.clzsearch.userlibmaker.business.filterpolicy.FilterPolicy;


public class FilterLastOnePolicy implements FilterPolicy
{
    @Override
    public ArrayList<File> filter(ArrayList<File> mathedJars)
    {
        ArrayList<File> f = new ArrayList<File>();
        f.add(mathedJars.get(mathedJars.size() - 1));
        return f;
    }

}
