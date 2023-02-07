import java.awt.*;
import javax.swing.*;
import java.awt.event.*;    //for ActionListener


public class MyFrame  extends JFrame
                    implements ActionListener
{
    Toolkit toolkit;
    Dimension screenSize;
    JList<String> displayList;
    JScrollPane scrollPane;
    JTextField textField;
    JPanel panel;
    JButton button;
    MyListModel listModel;


    MyFrame()
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
        listModel = new MyListModel();
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
        String urlString;                       //to get URL text from textfield

        if(e.getActionCommand().equals(("Go!")))
        {
            listModel.clear();                          //clear the old contents if any
            urlString = textField.getText().trim();     //stores the text from the textfield
            listModel = new MyListModel(urlString);     
            displayList.setModel(listModel);
        }
        else
        {

        }
        
    }


    void crawl()
    {
        
    }
}
