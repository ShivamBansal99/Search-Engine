package email;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Email
{
	public static void main ( String args [])
	{
		BufferedReader br = null;
		SearchEngine r = new SearchEngine();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions.txt"));

			while ((actionString = br.readLine()) != null) {
				try{r.performAction(actionString);}catch(FileNotFoundException e){
                                    e.printStackTrace();
                                }

			}
		} catch (IOException e) {
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
    /*public static void main(String[] args) throws FileNotFoundException{
        MyAVLTree a = new MyAVLTree();
        PageEntry temp = new PageEntry();
        a.addNode(new Position(temp,0,69));
        a.addNode(new Position(temp,0,115));
        a.addNode(new Position(temp,0,73));
        a.addNode(new Position(temp,0,98));
        a.addNode(new Position(temp,0,11));
        a.addNode(new Position(temp,0,2));
        a.addNode(new Position(temp,0,81));
        a.addNode(new Position(temp,0,78));
        a.addNode(new Position(temp,0,3));
        System.out.println(a.root.getValue().originalWordIndex);
    }*/
}
