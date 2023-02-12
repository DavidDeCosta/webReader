import java.awt.*;
import javax.swing.*;
import java.awt.event.*;    //for ActionListener
import java.util.Enumeration;
import java.util.Vector;


public class TheMainFrame  extends JFrame
                    implements ActionListener
{
    Toolkit toolkit;
    Dimension screenSize;
    JList<String> displayList;
    JScrollPane scrollPane;
    JTextField textField;
    JPanel panel;
    JButton button;
    ListModel listModel;

    Vector<String> listOfUrls = new Vector<>();
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
        listModel = new ListModel();
        displayList = new JList<>(listModel);
        scrollPane = new JScrollPane(displayList);
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

    void crawl()
    {
        initilize();

        Enumeration<String> forURLS = listOfUrls.elements();
        while(forURLS.hasMoreElements())
        {
            listModel.clear();                               //clear the old contents if any
            listModel = new ListModel(forURLS.nextElement(),listOfUrls, vectorOfExtractedData);     
            displayList.setModel(listModel);
        }
    }

    void initilize()
    {
        String urlString;                       //to get URL text from textfield
        listModel.clear();                          //clear the old contents if any
        urlString = textField.getText().trim();     //stores the text from the textfield
        listModel = new ListModel(urlString,listOfUrls,vectorOfExtractedData);     
        displayList.setModel(listModel);

        listOfUrls.addElement(urlString);   //puts the seed in the list of URLS

        ExtractedData extractedData;
        extractedData = new ExtractedData(urlString);
        vectorOfExtractedData.addElement(extractedData);    //puts our first HREF seed into our vector of Extracted data
    }
}
