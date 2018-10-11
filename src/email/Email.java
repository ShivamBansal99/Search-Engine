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
}
