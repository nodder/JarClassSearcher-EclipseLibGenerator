package name.cdd.product.clzsearch.business;

import java.io.File;

public interface SearchCaller
{
    void onFindOneResult(File file);
    void onFinish();
    void onFinishWithFindNothing();
    void onTips(String tips);
}
