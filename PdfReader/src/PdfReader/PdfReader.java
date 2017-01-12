package PdfReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PdfReader {
	
	public void readPdf(String file) throws Exception
	{
		File pdfFile = new File(file);
		String encoding = "UTF-8";
		int startPage = 1;    
		int endPage = Integer.MAX_VALUE;
		PDDocument document = PDDocument.load(pdfFile);    
		String pdfFileName = pdfFile.getName();
		String outfilename = null;
		if(pdfFileName.length() > 4)
		{
			outfilename = pdfFileName.substring(0, pdfFileName.length() - 4)  + ".txt";   
		}
		Writer outputWriter = new OutputStreamWriter(new FileOutputStream(outfilename), encoding);    
		PDFTextStripper stripper = new PDFTextStripper();  	
		stripper.setSortByPosition(false);
		stripper.setStartPage(startPage);
		stripper.setEndPage(endPage);
		stripper.writeText(document, outputWriter);  
		if (outputWriter != null) 
		{
			outputWriter.close();     
		}    
		if (document != null) 
		{      
			document.close();    
		}    
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PdfReader pdfReader = new PdfReader();
		String fileName = "D:/workspace/PdfReader/Airtel Ghana.pdf";
		try {
			pdfReader.readPdf(fileName);
			System.out.print("ÒÑÍê³É£¡");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
