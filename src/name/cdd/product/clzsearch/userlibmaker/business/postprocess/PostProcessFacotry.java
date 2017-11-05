package name.cdd.product.clzsearch.userlibmaker.business.postprocess;


public class PostProcessFacotry
{

    public static PostProcess getPostProcess(String action, String jarRootDirectory)
    {
        if("+".equals(action))
        {
            return new IncreaseProcess(jarRootDirectory);
        }
        else if("-".equals(action))
        {
            return new DecreaseProcess();
        }
        return null;
    }
}
