import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;
import javax.swing.text.html.parser.ParserDelegator;

public class MyListModel extends DefaultListModel<String>
{
    URL url;
    BufferedReader pageReader;
    String str;
    URLConnection urlConnection;
    InputStreamReader isr;
    MyParserCallbackTagHandler tagHandler;

    MyListModel()
    {

    }

    MyListModel(String urlString)
    {
        try 
        {
            url = new URL(urlString);          // construct a url object using the string from the textfield
//            readPage();
        
            urlConnection = url.openConnection();
            isr = new InputStreamReader(urlConnection.getInputStream());
            tagHandler = new MyParserCallbackTagHandler(urlString);
            new ParserDelegator().parse(isr, tagHandler, true);
            
        } 
        catch (MalformedURLException e) 
        {
            JOptionPane.showMessageDialog(null, "URL is malformed! ");
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        
    }

    void readPage()
    {
        try 
        {
            pageReader = new BufferedReader(new InputStreamReader(url.openStream()));
            str = pageReader.readLine();            //str will hold the first line of the html file
            while(str != null)
            {
            addElement(str);                        //add that string of text to the JList
            str = pageReader.readLine();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

}
