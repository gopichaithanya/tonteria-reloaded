package servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.stateless.BookSB;
import bean.stateless.BookSBProxy;
import bean.stateless.CustomerSB;

/**
 * Servlet implementation class ManageBook
 */
public class ManageBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BookSB bsb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageBook() {
        super();
         bsb = new BookSBProxy();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().println("Niente da fare per chi viene in Get qui.");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*//directory temporanea in cui vengono salvati i file
		System.getProperty("java.io.tmpdir")*/
		
		
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				// inizializza i parametri che vengono passati input
				HashMap<String, String> parametersMap =	initialize(request);
				// si comporta di conseguenza
				behavior(response, parametersMap);
				if(Integer.parseInt(parametersMap.get("choice")) == 1) {
					response.setContentType("text/html");
					response.getWriter().write("<html><body><img src=\"" + parametersMap.get("imageServerPath") + "\"></body></html>");
				}
			} catch (Exception e) {
				System.err.println("ERRORE: scansione dei parametri in input / upload immagine");
				e.printStackTrace();
			}
		} else {

			response.getWriter().println("ERRORE - da dove diavolo stai arrivando?? da admin.jsp no di sicuro!");
		}
	}


	/**
	 * Gestisce il comportamento della servlet basandosi sul valore di choice (passato tramite post). 
	 * */
	private synchronized void behavior(HttpServletResponse response, HashMap<String, String> parametersMap) throws IOException{

		int choice = Integer.parseInt(parametersMap.get("choice"));

		switch(choice) {

		case 1:
			int price = 0;
			try { price = Integer.parseInt(parametersMap.get("price")); }catch(Exception e){}
			bsb.insertBook(parametersMap.get("title"), parametersMap.get("author"), parametersMap.get("isbn"), price, 
					parametersMap.get("editor"), parametersMap.get("imageServerPath"), parametersMap.get("description"));
			response.getWriter().write("Libro inserito con successo");
			break;

		case 2:
			bsb.removeBook(parametersMap.get("isbn"));
			response.getWriter().write("Libro eliminato con successo");
			break;
		case 3:
			// TODO: implementare una funzione di ricerca
			response.getWriter().write("Non ancora implementata");
		}

	}
	
	/**
	 * Upload dell'immagine
	 * */
	private synchronized void uploadImage(FileItem item, HashMap<String, String> parametersMap) throws Exception {
		
		// process uploaded fields.
		File fullFile = new File( item.getName() );
		// finding the physical directory the image is going to be stored at 
		String dirPath = getServletContext().getRealPath("/images/cover/");
		// just my own little modification to the uploaded image filename.
		String fileName = trimImgFileName(fullFile.getName(), parametersMap);
		// creo l directory se non esistono gia'
		File directories = new File(dirPath);
		if(!directories.exists()) {
			directories.mkdirs();
			System.err.println("WARNING: le directory delle immagini non esistono. Le creo.");
		}
		// writing the image file
		item.write( new File( dirPath, fileName ) );
		parametersMap.put("imageServerPath", "/tbookstoreWeb/images/cover/" + fileName);
	}
	
	/**
	 * Crea il filename corretto basandosi sull'isbn
	 * */
    private synchronized String trimImgFileName(String fileName, HashMap<String, String> parametersMap) {
        StringBuffer sb = new StringBuffer(fileName);
        sb.replace(0, sb.lastIndexOf("."), parametersMap.get("isbn"));
        return sb.toString();
    }

    /**
     * Assegna alla giusta variabile di istanza il valore passato come parametro
     * */
	private synchronized void initParameter(String fieldName, String fieldValue, HashMap<String, String> parametersMap) {
		
		
		fieldValue = fieldValue.trim();
		
		if(fieldName.equals("author"))
			parametersMap.put("author", fieldValue);
		else if(fieldName.equals("title"))
			parametersMap.put("title", fieldValue);
		else if(fieldName.equals("description"))
			parametersMap.put("description", fieldValue);
		else if(fieldName.equals("isbn"))
			parametersMap.put("isbn", fieldValue);
		else if(fieldName.equals("editor"))
			parametersMap.put("editor", fieldValue);
		else if(fieldName.equals("choice")) 
			parametersMap.put("choice", fieldValue);
		else if(fieldName.equals("price")) {
			parametersMap.put("price", fieldValue);
		}
	}

	/**
	 * Inizializza la tabella hash che conterra' tutti i parametri passati tramite post.
	 * */
	@SuppressWarnings("unchecked")
	private synchronized HashMap<String, String> initialize(HttpServletRequest request) throws Exception {

		HashMap<String, String> parametersMap = new HashMap<String, String>();
		// Parse the HTTP request...
		ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
		servletFileUpload.setSizeMax(300000); /* the unit is bytes */
		List<FileItem> fileItemsList = servletFileUpload.parseRequest(request);
		Iterator<FileItem> it = fileItemsList.iterator();
		FileItem imageItem = null;
		while (it.hasNext()){
			FileItem item = it.next();
			if (item.isFormField()){
				/* The file item contains a simple name-value pair of a form field */
				// process normal input form fields		
				System.out.println("Field: " + item.getFieldName() );
				System.out.println("Value: " + item.getString() );
				initParameter(item.getFieldName(), item.getString(), parametersMap);

			} else {
				/* The file item contains an uploaded file */
				//lo salvo in imageItem per utilizzarlo successivamente
				imageItem = item;	
			}
		}
		if(!imageItem.getName().equals(""))
			uploadImage(imageItem, parametersMap);
		else {
			
			// TODO: settare il path dell'immagine che segnala che il libro non ha immagine
			// parametersMap.put("imageServerPath", "/webbookstore/images/cover/nocover.png");
		}
		return parametersMap;
	}
	
}