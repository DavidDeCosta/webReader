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
                if(emailList.get(i).compareTo(emailList.get(j)) < 0)  //if the email at index i is less than the email at index j
                {
                    temp = emailList.get(i);                 //temp holds the email at index i
                    emailList.set(i, emailList.get(j));     //set the email at index i to the email at index j
                    emailList.set(j, temp);                 //set the email at index j to the email at index i (which is now in temp)
                }
            }
        }
    }
}
