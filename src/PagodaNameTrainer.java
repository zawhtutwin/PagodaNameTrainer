import java.io.File;
import java.io.IOException;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;


public class PagodaNameTrainer {
	public static void main(String[] arg){
		//opennlp TokenNameFinderTrainer -model mm-ner-pagodas.bin -lang en -data pagodas.txt
		try {
			String[] sentences = {
			"Shwedagon is in Yangon. Sule is in downtown."};

			TokenNameFinderModel model = new TokenNameFinderModel(new File("D:\\apache-opennlp-1.6.0-bin\\apache-opennlp-1.6.0\\bin","mm-ner-pagodas.bin"));
			Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
			NameFinderME nameFinder = new NameFinderME(model);
			for (String sentence : sentences) {
				String tokens[] = tokenizer.tokenize(sentence);
				Span nameSpans[] = nameFinder.find(tokens);
				double[] spanProbs = nameFinder.probs(nameSpans);
				for (int i = 0; i < nameSpans.length; i++) {
				System.out.println("Span: " + nameSpans[i].toString());
				System.out.println("Entity: "
				+ tokens[nameSpans[i].getStart()]);
				System.out.println("Probability: " + spanProbs[i]);
				}
				System.out.println();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
