package name.cdd.product.clzsearch.userlibmaker.business.manager;


public class ListManager
{
    private ClassAndJavaListManager classAndJavaListManager;
    private JarAndClassListManager jarAndClassListManager;

    public ListManager(final ClassAndJavaListManager classAndJavaListManager, final JarAndClassListManager jarAndClassListManager)
    {
        this.classAndJavaListManager = classAndJavaListManager;
        this.jarAndClassListManager = jarAndClassListManager;
    }
}
