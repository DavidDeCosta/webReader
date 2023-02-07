import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;

public class TagHandler extends HTMLEditorKit.ParserCallback
{
    String baseDomain;
    int testingnum = 0;

    Vector<String> listOfUrls;
    Vector<String> listOfEmailAdresses;


    TagHandler(String baseDomain, Vector<String> listOfUrls,Vector<String> listOfEmailAdresses)
    {
        this.baseDomain = baseDomain;
        this.listOfUrls = listOfUrls;
        this.listOfEmailAdresses = listOfEmailAdresses;
    }

    TagHandler()
    {

    }
    
    @Override
    public void handleStartTag(Tag t, MutableAttributeSet a, int pos) 
    {

 //       System.out.println("Found a tag: " + t);

        String mailTo = (String) a.getAttribute(HTML.Attribute.HREF);          //to check if the href starts with mailto:
        Object attribute;
        attribute = a.getAttribute(HTML.Attribute.HREF);
        if(attribute != null)
        {
            if(!mailTo.startsWith("mailto:") )
            {
                listOfUrls.addElement(attribute.toString());
            }
            else
            {
                
            }
        }

        
    }

    @Override
    public void handleText(char[] data,int po)
    {
        String str;
        str = new String(data);

//        System.out.println(str);

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
              //  System.out.println("Found " + str.substring(matcher.start(),matcher.end()) + " ");
                listOfEmailAdresses.add(str.substring(matcher.start(),matcher.end()));
                matcher.region(matcher.end(), str.length());
            }
            else
            {
                done = true;
            }
        }

    }
}