package co.edureka.testreport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReportImpl {
	
	public GeneratePdfReportImpl() {	
	}

	Document document = new Document();

	public void createPdfTestReport() {
		try {
			String File = System.getProperty("user.dir") + "/test_report.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(File));
			document.open();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (DocumentException e) {
			e.getMessage();
		}
	}

	public void documentPdf(String message) {
		try {
			document.add(new Paragraph(message));
		} catch (DocumentException e) {
			e.getMessage();
		}
	}

	public void closePdf() {
		document.close();
	}
}