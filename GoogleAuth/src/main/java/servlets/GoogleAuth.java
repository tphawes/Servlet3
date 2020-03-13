package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.riversun.oauth2.google.OAuthSession;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

import auth.IdTokenVerifierAndParser;

@WebServlet(
        name = "googleauth",
        urlPatterns = "/GoogleAuth"
)
public class GoogleAuth extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("GoogleLoginServlet start");
        //response.setContentType("text/html");

        
        
        try {
            String idToken = request.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            System.out.println("User name: " + name);
            System.out.println("User email: " + email);
    		String returnVal="";

            //Verify the token
            
    		if(idToken != null && !idToken.equals(""))
    		{
    			System.out.println("Verify");
    			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
    				    .setAudience(Collections.singletonList("37858416227-7ogd24in3t5of5o5ugqkpefhvehjbbgi.apps.googleusercontent.com"))
    				    .build();
    				try{
    					GoogleIdToken verifiedToken = verifier.verify(idToken);
    					if (verifiedToken != null) {
    						Payload payload = verifiedToken.getPayload();
    						returnVal = "User ID: " + payload.getSubject();
    						System.out.println("Verified");
    						// You can also access the following properties of the payload in order
    						// for other attributes of the user. Note that these fields are only
    						// available if the user has granted the 'profile' and 'email' OAuth
    						// scopes when requested.
    						// String email = payload.getEmail();
    						// boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
    						// String name = (String) payload.get("name");
    						// String pictureUrl = (String) payload.get("picture");
    						// String locale = (String) payload.get("locale");
    						// String familyName = (String) payload.get("family_name");
    						// String givenName = (String) payload.get("given_name");
    					} else {
    						System.out.println("Not Verified");
    						returnVal = "Invalid ID token.";
    					}
    				} catch (Exception ex){
    					returnVal = ex.getMessage();
    				}
    		}
    		try {
    			HttpSession session = request.getSession(true);
    			System.out.println("Session creation:" + session.getCreationTime());
    			
    			System.out.println("Session creation:" + session.getId());
                session.setAttribute("userName", returnVal);
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		
    		
    		//request.getServletContext()
            //   .getRequestDispatcher("/followup.jsp").forward(request, response);
    		//Just return a string
    		response.setHeader("Set-Cookie", "HttpOnly;Secure;SameSite=Strict");
    		response.setContentType("text/plain;charset=UTF-8");
            ServletOutputStream sout = response.getOutputStream();
            sout.print(returnVal);
            //End verify token


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("GoogleLoginServlet complete");
/*        
        GoogleCredential credential = OAuthSession.getInstance().createCredential(request);
        System.out.println("GoogleLoginServlet 1");
        String userId = OAuthSession.getInstance().getUserId(request);
        System.out.println("GoogleLoginServlet 2");
        // Get userInfo
        Oauth2 oauth2 = new Oauth2.Builder(
                new com.google.api.client.http.javanet.NetHttpTransport(),
                new com.google.api.client.json.jackson2.JacksonFactory(),
                credential).build();
        System.out.println("GoogleLoginServlet 3");
        // Get userInfo
        Userinfoplus userInfo = oauth2.userinfo().get().execute();
        System.out.println("GoogleLoginServlet 4");
        System.out.println("userId:" + userId );
        System.out.println("userInfo:" + userInfo );
        
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		String idToken = request.getParameter("id_token");
		if( idToken != null )
		{
	        System.out.println("Got token");
	        GoogleIdToken.Payload payLoad;
		}
        System.out.println("Hello:" + user );
        System.out.println("Hello:" + pwd );
    	Cookie loginCookie = new Cookie("user","user");
		//setting cookie to expiry in 30 mins
		loginCookie.setMaxAge(30*60);
		response.addCookie(loginCookie);
        String question = "How are you?";
        List<String> questions = new ArrayList<String>();
        questions.add(question);
        request.setAttribute("questions", questions);
        RequestDispatcher view = request.getRequestDispatcher("followup.jsp");
        System.out.println("GoogleLoginServlet complete");
        view.forward(request, response);
        */
    }
}