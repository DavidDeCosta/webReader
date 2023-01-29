import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.html.parser.ParserDelegator;

public class MyListModel extends DefaultListModel<String>
{
    URL url;
    BufferedReader pageReader;
    String str;
    URLConnection urlConnection;
    InputStreamReader isr;
    TagHandler tagHandler;

    int testingnum = 0;
    Vector<String> listOfUrls = new Vector<String>();
    Vector<String> listOfEmailAdresses = new Vector<String>();

    String grabTheEmailString;

    MyListModel()
    {

    }

    MyListModel(String urlString)
    {
        try 
        {
            url = new URL(urlString);          // construct a url object using the string from the textfield
            readPage();
        
            urlConnection = url.openConnection();
            isr = new InputStreamReader(urlConnection.getInputStream());
            tagHandler = new TagHandler(urlString,listOfUrls,listOfEmailAdresses);
            new ParserDelegator().parse(isr, tagHandler, true);
            displayElements();
            
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

    void displayElements()
    {
        Enumeration<String> lala = listOfUrls.elements();
 //       System.out.println("this happened: " + testingnum + " times ");
 //       testingnum++;
        System.out.println("This is the list of HREF Links: \n");
        while(lala.hasMoreElements())
        {
            System.out.println(lala.nextElement());
        }
    }

    void readPage()
    {

        try 
        {
            pageReader = new BufferedReader(new InputStreamReader(url.openStream()));
            str = pageReader.readLine();            //str will hold the first line of the html file


            Matcher matcher = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(str);
            if(matcher.find())
            {
                grabTheEmailString = matcher.group();
                System.out.println(grabTheEmailString + "\n");
            }

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
