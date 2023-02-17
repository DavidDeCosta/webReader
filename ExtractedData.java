import java.util.Vector;

public class ExtractedData 
{
    String link;
    int distanceToSeed;
    String baseDomain;
    Vector<String> emailList;

    ExtractedData()
    {
        emailList = new Vector<>();
    }
    ExtractedData(String link, int distanceToSeed)
    {
        this.link = link;
        this.distanceToSeed = distanceToSeed;
        emailList = new Vector<>();
    }
    ExtractedData(String link, Vector<String> emailList)
    {
        this.link = link;
        this.emailList = emailList;
    }
    ExtractedData(String link, String baseDomain, int distanceToSeed)
    {
        this.baseDomain = baseDomain;
        this.link = link;
        this.distanceToSeed = distanceToSeed;
        emailList = new Vector<>();
    }

    @Override
    public String toString()
    {
        String formattedString;
        formattedString = "<html><br>************************************************ <br>" + link +"<br>" + emailList;

        return formattedString;
    }
}
