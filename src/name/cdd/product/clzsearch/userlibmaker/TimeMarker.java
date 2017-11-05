package name.cdd.product.clzsearch.userlibmaker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeMarker
{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private long startTime;
    private long endTime;
    
    public String markStart()
    {
        Date currentTime = currentTime();
        startTime = currentTime.getTime();
        
        return getFormatedTime(currentTime);
    }
    
    public String markend()
    {
        Date currentTime = currentTime();
        endTime = currentTime.getTime();
        
        return getFormatedTime(currentTime);
    }
    
    public String getTimeCost()
    {
        long timecost = endTime - startTime;//ms
        
        if(timecost < 10)
        {
            return Double.toString(timecost / 1000.0);
        }
        
        return Long.toString(timecost / 1000);
    }

    private String getFormatedTime(Date currentTime)
    {
        return sdf.format(currentTime);
    }

    private Date currentTime()
    {
        return new Date();
    }
}
