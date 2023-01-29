import java.util.Enumeration;
import java.util.Vector;
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

        Enumeration<?> attEnum;
        attEnum = a.getAttributeNames();
        while(attEnum.hasMoreElements())
        {
            if(attEnum.nextElement().equals(HTML.Attribute.HREF))
            {
            //    System.out.println(a.getAttribute(HTML.Attribute.HREF) + "\n");
                listOfUrls.addElement((String) a.getAttribute(HTML.Attribute.HREF));
            }
        }
    }
     
}