package com.gazelle.sumrize.controller;

import java.util.Hashtable;
import java.util.List;

import com.gazelle.sumrize.docsimilarity.DocumentVectorCalculator;
import com.gazelle.sumrize.docsimilarity.SimilarityMatrixGenerator;
import com.gazelle.sumrize.preprocessor.TextPreprocessor;
import com.gazelle.sumrize.ranker.Lexrank;
import com.gazelle.sumrize.sentenceextractor.ExtractSentence;
import com.gazelle.sumrize.webtextextractor.WebPageTextExtractor;

public class Controller {
	
	WebPageTextExtractor wpTextExtractor; // will extract text
	TextPreprocessor textPreprocessor;
	ExtractSentence extractSentence;
	Lexrank lexrank;
	List<Hashtable<String,Double>> score;
	int [] degree;
	
	public String getSummary(String url) throws Exception{
		
		String summary = null;
		String preprocessedText=null;
		List<String> sentences=null;
		
		if(url.equals("")){
			throw new IllegalArgumentException(Constants.EMPTY_URL_FIELD);
		}
		
		wpTextExtractor = new WebPageTextExtractor();
		String summarizableText = wpTextExtractor.extractText(url);
		
		if(!summarizableText.equals("")){
			
			textPreprocessor = new TextPreprocessor();
			preprocessedText = textPreprocessor.getPreprocessedText(summarizableText);
			
		}
		else{
			throw new Exception(Constants.TEXT_EXTRACTION_FAILED);
		}
		
		extractSentence = new ExtractSentence();
		sentences = extractSentence.getSentences(preprocessedText);
		
	
		double[][] similarityMatrix = getSimilarityMatrixFromSentenceList(sentences);
		
		printArray(degree);
		
		lexrank = new Lexrank(sentences, similarityMatrix	, 0.85, degree);
		double[] resultEigenValues = lexrank.powerMethod(.001);
		
		printArray(resultEigenValues);
		
		return summary;
		
	}
	
	
	private void printArray(double[] A){
		
		for(double i: A){
			System.out.print(" "+i);
		}
		
	}
	private void printArray(int[] A){
		
		for(int i: A){
			System.out.print(" "+i);
		}
		
	}
	
	private double[][] getSimilarityMatrixFromSentenceList(List<String> sentenceList){
		
		
		DocumentVectorCalculator dvc=new DocumentVectorCalculator(sentenceList);
		//System.out.println(dvc.generateTF_IDFScores().toString());
		score = dvc.generateTF_IDFScores();
		
		SimilarityMatrixGenerator simg=new SimilarityMatrixGenerator(score);
		
		double[][] result=simg.similarityMatrixGenerator();
		degree = new int[result.length]; 
		
		
		for(int i=0;i<degree.length;i++){
			degree[i] = 0;
		}
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("The Similarity Matrix");
		System.out.println(" ");
		
		//Similarity Mat
		
			
			for(int i=0;i<result.length;i++)
			{
				for(int j=0;j<result[i].length;j++)
				{
					
					if(i!=j)
					{
						if(result[i][j] >= 0.1)
						{result[i][j]=1.0;degree[i]++;}
						else
						result[i][j]=0.0;
					}
				}
			}
		

		
		//End
			for(int i=0;i<result.length;i++)
			{
				for(int j=0;j<result[i].length;j++)
				{
					System.out.print("     "+result[i][j]);
				}

				System.out.println(" ");
			}
			
		
		
		return result;
		
	}
	public static void main(String...a) throws Exception{
		
		Controller c = new Controller();
		c.getSummary("http://www.paulgraham.com/growth.html");
	}


}
