import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Vector;

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
            url = new URL(urlString);            // construct a url object using the string from the textfield
 //           readPage();
            urlConnection = url.openConnection();       //makes a connection to webpage
            isr = new InputStreamReader(urlConnection.getInputStream());           //isr reads the page
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


    //This method displays the HREF's and email adresses found on the page
    void displayElements()
    {
        Enumeration<String> forURLS = listOfUrls.elements();
        System.out.println(" \n This is the list of HREF Links: ");
        while(forURLS.hasMoreElements())
        {
            System.out.println(forURLS.nextElement());
        }

        Enumeration<String> forEmails = listOfEmailAdresses.elements();
        System.out.println(" \n\n This is a list of the email adresses: ");
        while(forEmails.hasMoreElements())
        {
            System.out.println(forEmails.nextElement());
        }
    }
/*
    void readPage()
    {

        try 
        {
            pageReader = new BufferedReader(new InputStreamReader(url.openStream()));
            str = pageReader.readLine();            //str will hold the first line of the html file



            String regExString = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
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
                    System.out.println("Found " + str.substring(matcher.start(),matcher.end()) + " ");
                    matcher.region(matcher.end(), str.length());
                }
                else
                {
                    done = true;
                }
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
    */

}