<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
	<title>APDS Technical Exercise</title>
</head>

<body>
	<h1><font color="blue">APDS Technical Exercise</font></h1>
	<p>
	<h2>English Dictionary</h2>
	<textarea  disabled="disabled" rows="2" cols="90" style="font-family:Times New Roman;color:#003399;white-space:pre-wrap; align-content:left; overflow:auto; font-weight: bold">
For this exercise, please first add the words you want to exist in the English dictionary. 
Enter an english word in the box below and click the 'Add' button
	</textarea>
	<p>
    <form method="post" action="dictionary">
      <font color="red">${dictErrorMessage}</font>
	      <div><label for="dictionary"><b><font color="aquaBlue">Add Word to English Dictionary:</font></b></label>
	      <input id="dictWord" name="dictWord" type="text" /><input type="submit" value="Add" /></div>
	      <p>
     </form>
      <p>
   	  <a href="dictionary/words">Click here</a> to see all words in the English dictionary.
     
    <br>
    <p>
	<h2>Palindrome</h2>
    <p>
	<textarea  disabled="disabled" rows="3" cols="90" style="font-family:Times New Roman;color:#003399;white-space:pre-wrap; align-content:left; overflow:auto; font-weight: bold">
Enter a word in the box below and click the Submit button. The word can only contain letters and no spaces. There will be two checks, one to see if the word is in the English dictionay and the other to check if the word is a palindrome
	</textarea>
	<p>
    <form method="post" action="palindrome" action="/palindrome">
     <font color="red">${errorMessage}</font>
        <P>
	      <div><label for="word"><b><font color="aquaBlue">Enter word:</font></b></label>
	      <input id="word" name="word" type="text" /><input type="submit" /></div>
        <P>
	      <div><label for="palindrome"><b><font color="aquaBlue">Palindrome:</font></b></label>
	       ${palindrome}</div>
	      <p>
	   	  <div><font color="blue">${inDictionaryMessage}</font></div>
	      <p>
	   	  <div><font color="blue">${isPalindromeMessage}</font></div>
	      <p>
	   	  <a href="/words">Click here</a> to see all words which were entered and the palindromes.
	   	  
    </form>
</body>

</html>
</html>
