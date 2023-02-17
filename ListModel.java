import java.util.*;
import javax.swing.*;

public class ListModel extends DefaultListModel<ExtractedData>
{

    Vector <ExtractedData> vectorOfExtractedData;

    ListModel()
    {

    }

    ListModel(Vector <ExtractedData> vectorOfExtractedData  )
    {
        this.vectorOfExtractedData = vectorOfExtractedData;
 
    }
}