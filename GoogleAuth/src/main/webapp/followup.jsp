<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<body>
<h1>
    Follow-up 
</h1>
<%
try
{
	List<String> result= (List<String>) request.getAttribute("questions");
	Iterator<String> it = result.iterator();
	out.println("<br>Am unsure how to help at this time.<br>");

	out.println("<br>Please answer the following:<br><br>");

	while(it.hasNext()){
		out.println(it.next()+"<br>");
	}
}
catch (Exception e)
{
    out.println("An exception occurred: " + e.getMessage());
}

%>
    <form method="post" action="QuestionServlet">
		<textarea rows="10" cols="30" name="comment">Enter your answer here</textarea>
        <br><br>
        <input type="submit">
    </form>
<a href="#" onclick="signOut();">Sign out</a>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script>
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
</script>    
    
</body>
</html>