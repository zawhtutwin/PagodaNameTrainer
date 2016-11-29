import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Sequence;


public class MyanmarPosTagger {
	public static void main(String[] arg){
		InputStream modelIn = null;

		try {
		  modelIn = new FileInputStream("D:\\apache-opennlp-1.6.0-bin\\apache-opennlp-1.6.0\\bin\\mm-pos-maxent.bin");
		  POSModel model = new POSModel(modelIn);
		  String sent[] = new String[]{"၁", "large", "cities", "in", "the", "US", "had",
           "morning", "and", "afternoon", "newspapers", "."};
		  POSTaggerME tagger = new POSTaggerME(model);
		  String tags = tagger.tag("ေစ်းသို ့ကြ်န္ေတာ္ သြားသည္။");
		  double probs[] = tagger.probs(); // confidence scores for each tag
		  Sequence topSequences[] = tagger.topKSequences(sent);
		  System.out.println(tags);
		  System.out.println(Arrays.toString(probs));
		}
		catch (IOException e) {
		  // Model loading failed, handle the error
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
	}
}
