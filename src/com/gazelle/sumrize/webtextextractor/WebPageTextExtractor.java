package com.gazelle.sumrize.webtextextractor;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gazelle.sumrize.controller.Constants;

public class WebPageTextExtractor {
	
	String textFromWebpage;



public String extractText(String url) throws Exception
{
	
	try{
	Document doc = Jsoup.parse(new URL(url), 6000);

	Elements body = doc.select("body");

	textFromWebpage=body.text();

	}catch(Exception e){
		
		throw new Exception(Constants.TEXT_EXTRACTION_FAILED);
	}
	//System.out.println(textFromWebpage);

	return textFromWebpage;
}




public static void main(String...argv)throws Exception
{
	//Document doc = Jsoup.connect("http://jsoup.org").get();

	//Element link = doc.select("a").first();
	//String relHref = link.attr("href"); // == "/"
	//String absHref = link.attr("abs:href"); // "http://jsoup.org/"
	
	//System.out.println(relHref);
	//System.out.println(absHref);
	
	//HtmlToPlainText ht=new HtmlToPlainText();
	//String text=ht.getPlainText(link);
	//System.out.println(text);
	
//	String url = ("http://www.repubblica.it/economia/finanza/2011/10/27/news/la_fine_dell_incertezza_solleva_le_azioni_bancarie_in_borsa_alle_italiane_mancano_15_miliardi_di_capitale_met_di_unicredit-23967707/");

String url=("http://www.paulgraham.com/growth.html");
	//String url=("http://en.wikipedia.org/wiki/Benedict_Cumberbatch");
	//String url=("http://www.mcjournal.com/dsPIC_PIC/Getting%20Started%20with%20Programming%20and%20Interfacing%20PIC24%20in%20'C'/Project%201%20Hello%20World.pdf");
	
	//Document doc = Jsoup.parse(new URL(url), 2000);

	//Elements body = doc.select("body");

	//String s=body.text();

	//System.out.println(s);
	
	WebPageTextExtractor web=new WebPageTextExtractor();
	System.out.println(web.extractText(url));
	
	
	
	
}





}
