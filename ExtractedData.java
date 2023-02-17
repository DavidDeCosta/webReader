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

    void sortEmailList()
    {
        String temp;
        for(int i = 0; i < emailList.size(); i++)
        {
            for(int j = 0; j < emailList.size(); j++)
            {
                if(emailList.get(i).compareTo(emailList.get(j)) < 0)
                {
                    temp = emailList.get(i);
                    emailList.set(i, emailList.get(j));
                    emailList.set(j, temp);
                }
            }
        }
    }
}
