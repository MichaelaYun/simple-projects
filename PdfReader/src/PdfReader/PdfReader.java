package PdfReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PdfReader {
	
	public void readPdf(File pdfFile) throws Exception
	{
		String encoding = "UTF-8";
		int startPage = 1;    
		int endPage = Integer.MAX_VALUE;
		PDDocument document = PDDocument.load(pdfFile);    
		String pdfFileName = pdfFile.getName();
		String outfilename = null;
		String outputdir = "D:/workspace/PdfReader/outputdir";
		if(pdfFileName.length() > 4)
		{
			outfilename = outputdir +'/'+ pdfFileName.substring(0, pdfFileName.length() - 4)  + ".txt";   
			System.out.println(outfilename);
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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PdfReader pdfReader = new PdfReader();
		File pdfDir = new File("D:/workspace/PdfReader/pdfdir");
		if(pdfDir.isDirectory())
		{
			File[] pdfFile = pdfDir.listFiles();
			for(File subfile : pdfFile)
			{
				System.out.println(subfile.getAbsolutePath());
				pdfReader.readPdf(subfile);
			}
		}
	}
}
