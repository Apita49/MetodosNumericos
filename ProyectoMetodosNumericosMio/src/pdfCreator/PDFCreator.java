package pdfCreator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreator {
	public static void createPdfDoc(String title, String content) {
		Document doc = new Document();
		doc.setPageSize(PageSize.LEGAL.rotate());

		title = verifyAndFixFileName(title);

		try {
			// Windows doesn't allow
			// \ / : * ? " < > |
			// characters for the file name (don't know 'bout iOS 'tho...)
			PdfWriter.getInstance(doc, new FileOutputStream(title + ".pdf"));

			doc.open();

			doc.add(new Paragraph(content, new Font(FontFamily.COURIER, 13)));

			doc.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

	private static String verifyAndFixFileName(String fileName) {
		String fixedFileName = "";
		for (char c : fileName.toCharArray()) {
			if (c != '\\' && c != '/' && c != ':' && c != '*' && c != '?'
					&& c != '"' && c != '<' && c != '>' && c != '|') {
				fixedFileName += c;
			}
		}

		return fixedFileName;
	}

	public static void main(String[] args) {
		// PDFCreator.createPdfDoc("helloWorld", "Hola papus! =v");
		System.out.println("\\");
		System.out.println(verifyAndFixFileName("hola\\\\ mundo!"));
	}
}