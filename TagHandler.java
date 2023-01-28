import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;

public class TagHandler extends HTMLEditorKit.ParserCallback
{
    String baseDomain;

    TagHandler(String baseDomain)
    {
        this.baseDomain = baseDomain;
    }

    TagHandler()
    {

    }


    
    @Override
    public void handleSimpleTag(Tag t, MutableAttributeSet a, int pos) 
    {
        
        handleSimpleTag(t, a, pos);

        
    }
}
