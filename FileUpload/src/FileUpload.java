import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
@MultipartConfig
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Pirocapp Test</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Pirocapp get test</h3>");
        out.println("Testing Pirocapp<br>");
        out.println("<P>");
        out.println("</body>");
        out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		Enumeration enumerator = request.getParameterNames();
		String param = null;
		while (enumerator.hasMoreElements()) {
			param = String.valueOf(enumerator.nextElement());
			System.out.println("Name: " + param);
			System.out.println("Value: " + request.getParameter(param));
		}
		*/
		
		
		
		
		// configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        //factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        //factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        //byBu
        //factory.setRepository(new File("c:\\temp\\"));
        factory.setRepository(new File("/var/www/appiroca/upload/"));
        //factory.setRepository(new File("upload/"));        
        ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(request));
			
	        String fileName1 = "";
	        String transformedImage = null;
	        
	        if (formItems != null && formItems.size() > 0) {
	            for (FileItem item : formItems) {
	                // processes only fields that are not form fields
	                if (!item.isFormField()) {
	                	String fileName = new File(item.getName()).getName();
                        fileName1 += fileName;                        
                        Random rand = new Random();
                        //String filePath = "c:\\temp\\chupa.jpg";
                        String filePath = "/var/www/appiroca/upload/" + String.valueOf(rand.nextInt(5000) + 1);
                        //String filePath = System.getProperty("java.io.tmpdir") + String.valueOf(rand.nextInt(5000) + 1);
                        System.out.println("murilo" + filePath);
                        //String filePath = "upload/" + rand.nextInt(5000) + 1;
                        File storeFile = new File(filePath);                        
                        
                        // saves the file on disk
                        try {
                        	item.write(storeFile);                        	
                        } catch (Exception e) {
                        	System.out.println("Error in save:" + e.getMessage());
                        }                        
                        Pirocator pirocando = new Pirocator(filePath);
                        transformedImage = pirocando.pirocate();
	                }
	                else {
	                	// If we need to read parameters, do it here
	                	//System.out.println(item.getFieldName());
	                	//System.out.println(item.getString());
	                	
	                }
	                /*
	                response.setContentType("text/html");
	                PrintWriter out = response.getWriter();
	                
	                out.println("<html>");
	                out.println("<head>");
	                out.println("<title>Sua imagem com Negao da Piroca</title>");
	                out.println("</head>");
	                out.println("<body>");
	                out.println("<h3>BEHOLD!</h3>");
	                */
	                
	                ServletOutputStream streamOut;
	                streamOut = response.getOutputStream();
	                FileInputStream fin = new FileInputStream(transformedImage);
	                BufferedInputStream bin = new BufferedInputStream(fin);
	                BufferedOutputStream bout = new BufferedOutputStream(streamOut);
	                int ch = 0;
	                while((ch=bin.read())!=-1)
	                {
	                	bout.write(ch);
	                }
	                bin.close();
	                fin.close();
	                bout.close();
	                streamOut.close();
	                /*
	                 * out = response.getWriter();
	                 */
	                //out.println("<img src=" + transformedImage + ">");
	                
	                /*
	                if (firstName != null || lastName != null) {
	                    out.println("First Name:");
	                    out.println(" = " + HTMLFilter.filter(firstName) + "<br>");
	                    out.println("Last Name:");
	                    out.println(" = " + HTMLFilter.filter(lastName));
	                } else {
	                    out.println("No Parameters, Please enter some");
	                }
	                */
	                /*
	                out.println("<br>");
	                out.println("</body>");
	                out.println("</html>");
	                out.close();
	                */
	            }
	        }
		} catch(FileUploadException error) {
			System.out.println("Error uploading file:" + error.getMessage());
		}
		
	    // By Bu: This part works
	    /*
		ServletFileUpload upload = new ServletFileUpload();
		
    	Part filePart = request.getPart("inputFile"); // Retrieves <input type="file" name="file">
	    //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    InputStream fileContent = filePart.getInputStream();
	    OutputStream myFile = new FileOutputStream("c:\\temp\\chupa.jpg");
	    IOUtils.copy(fileContent, myFile);
	    System.out.println(request.get);
	    myFile.close();
		*/
	}

}