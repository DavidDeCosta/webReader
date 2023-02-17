import java.util.Vector;
import java.util.regex.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;

public class TagHandler extends HTMLEditorKit.ParserCallback
{
    ExtractedData passedInExtractedDataObject;
    Vector <ExtractedData> vectorOfExtractedData;
    int distance;

    TagHandler()
    {

    }
    TagHandler(ExtractedData passedInExtractedDataObject, Vector <ExtractedData> vectorOfExtractedData)
    {
        this.passedInExtractedDataObject = passedInExtractedDataObject;
        this.vectorOfExtractedData = vectorOfExtractedData;
        this.distance = passedInExtractedDataObject.distanceToSeed +1;
    }


    @Override
    public void handleStartTag(Tag t, MutableAttributeSet a, int pos) 
    {
        //if the tag is not a mailTO then try to create an Extracted Data object and add it to the list
        boolean found= false;
        String mailTo = (String) a.getAttribute(HTML.Attribute.HREF);          //to check if the href starts with mailto:
        Object attribute;
        attribute = a.getAttribute(HTML.Attribute.HREF);
        if(attribute != null)
        {
            if(!mailTo.startsWith("mailto:") )                //makes sure the mailto's arent added to our url list
            {
                int n = 0;
                while(!found && n < vectorOfExtractedData.size())
                {
                    if(attribute.toString().equals(vectorOfExtractedData.get(n).link))
                    {
                        found = true;
                    }
                    else
                    {
                        n++;
                    }
                }
                if(!found)
                { 
                    ExtractedData extractedData;
                    extractedData = new ExtractedData(attribute.toString(), passedInExtractedDataObject.link,distance);
                    vectorOfExtractedData.addElement(extractedData);
                  //  sortVectorOfExtractedData();
                }
            }
        }
        else
        {
        //  System.out.println("im in the else of the mailto forloop \n");         
        }
    }

    @Override
    public void handleText(char[] data,int po)
    {
        //if you find an email in the text add the email to the passedInExtractedDataObject
        String str;
        str = new String(data);
        String regExString = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
        Pattern pattern;
        Matcher matcher;
        boolean done;
        pattern = Pattern.compile(regExString);
        matcher = pattern.matcher(str);
        done = false;
        while(!done)
        {
            if(matcher.find())
            {
                passedInExtractedDataObject.emailList.add(str.substring(matcher.start(),matcher.end()));
                passedInExtractedDataObject.sortEmailList();
                System.out.println("Found Email: " + str.substring(matcher.start(),matcher.end()) +
                " at the URL: " + passedInExtractedDataObject.link);
                matcher.region(matcher.end(), str.length());
                
            }
            else
            {
                done = true;
            }
        }
    }

    void sortVectorOfExtractedData()
    {
        ExtractedData temp;
        for(int i = 0; i < vectorOfExtractedData.size(); i++)
        {
            for(int j = 0; j < vectorOfExtractedData.size(); j++)
            {
                if(vectorOfExtractedData.get(i).link.compareTo(vectorOfExtractedData.get(j).link) < 0)
                {
                    temp = vectorOfExtractedData.get(i);
                    vectorOfExtractedData.set(i, vectorOfExtractedData.get(j));
                    vectorOfExtractedData.set(j, temp);
                }
            }
        }
    }

}