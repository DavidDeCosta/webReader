import java.awt.*;
import javax.swing.*;
import javax.swing.text.html.parser.ParserDelegator;

import java.awt.event.*;    //for ActionListener
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;


public class TheMainFrame  extends JFrame
                    implements ActionListener
{
    Toolkit toolkit;
    Dimension screenSize;
    JList<ExtractedData> displayJList;
    JScrollPane scrollPane;
    JTextField textField;
    JPanel panel;
    JButton button;
    ListModel defaultListModel;

    URL url;
    BufferedReader pageReader;
    URLConnection urlConnection;
    InputStreamReader isr;
    TagHandler tagHandler;

    Vector <ExtractedData> vectorOfExtractedData = new Vector<ExtractedData>();


    TheMainFrame()
    {
        addComponents();
        buildMainFrame();
    }

    void buildMainFrame()
    {
        toolkit = Toolkit.getDefaultToolkit();                      // used to help get the users screen size
        screenSize = toolkit.getScreenSize();                       //get the users screen size
        setSize(screenSize.width/2, screenSize.height/2);           // makes JFrame 1/2 the users screensize
        setLocationRelativeTo(null);                             // window is placed in the center of screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //when close frame the program stops
        setTitle("Project 1");
        setVisible(true);
    }

    void addComponents()
    {
        defaultListModel = new ListModel();
        displayJList = new JList(defaultListModel);
        scrollPane = new JScrollPane(displayJList);
        add(scrollPane,BorderLayout.CENTER);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(450,50));
        

        button = new JButton("Go!");
        button.addActionListener(this);

        panel = new JPanel();
        add(panel,BorderLayout.SOUTH);
        panel.add(textField);
        panel.add(button);


    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals(("Go!")))
        {
            crawl();
        }
        else
        {

        }
    }

    void initilize()
    {
        //Get the original Link and create an Extracted Data object out of it and add it to the list 
        String urlString;
        defaultListModel.clear();
        urlString = textField.getText().trim();
        ExtractedData extractedData;
        extractedData = new ExtractedData(urlString,0);
        vectorOfExtractedData.addElement(extractedData);   //add the first link to the vectorOfExtractedData
    }

    void crawl()
    {
        initilize();
        defaultListModel = new ListModel(vectorOfExtractedData);
        displayJList.setModel(defaultListModel);
        ExtractedData testExtractedData;

        //Get the ExtractedData object out of the list to parse
        int currPos = 0;
        testExtractedData = vectorOfExtractedData.get(currPos);
        getData(testExtractedData);
        defaultListModel.addElement(testExtractedData);
        currPos++;
        while( currPos <vectorOfExtractedData.size() && testExtractedData.distanceToSeed < 2)
        {
            testExtractedData = vectorOfExtractedData.get(currPos);
            getData(testExtractedData);
            defaultListModel.addElement(testExtractedData);
            currPos++;
        }     
    }

    void getData(ExtractedData testExtracteData)
    {
        try
        {
            url = new URL(testExtracteData.link);
            urlConnection = url.openConnection();
            isr = new InputStreamReader(urlConnection.getInputStream());
            tagHandler = new TagHandler(testExtracteData,vectorOfExtractedData);  
            new ParserDelegator().parse(isr, tagHandler, true);
        }
        catch (MalformedURLException e) 
        {
        //    JOptionPane.showMessageDialog(null, "URL is malformed! " + url);
            
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
