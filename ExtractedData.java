import java.util.Vector;

public class ExtractedData 
{
    String link;
    int distanceToSeed;
    String baseDomain;
    Vector<String> emailList;

    ExtractedData()
    {

    }
    ExtractedData(String link)
    {
        this.link = link;
    }
    ExtractedData(String link, Vector<String> emailList)
    {
        this.link = link;
        this.emailList = emailList;
    }
}
