<%@ page import="java.util.*" %>
<%@ page import="bean.stateless.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="it-IT">
<head>
	<title>tBookshop &raquo; The online bookstore with java-core</title>

	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

	<link rel="stylesheet" type="text/css" href="./includes/styles/default.css" />
	<link rel="stylesheet" href="./includes/styles/form.css" type="text/css" />


</head>

<jsp:useBean id="customer" scope="session" class="bean.stateless.Customer" />

<%
	
	boolean logged = false;
		
	try {
		if (customer.getName() != null) {
			logged = true;
		}
	} catch (Exception e) {
		out.println("[Exception] nessun bean caricato!!");	
	}
%>
<!--  credo che la classe sia inutile, o meglio utile solo per idetificare il contenuto -->
<body class="wordpress y2009 m05 d31 h09 home blog">

<div id="universe-a">
	<div id="universe-b">
		<div id="universe-c">

<div id="header">

<a href="../"><img
	src="../images/theme/site-logo.png"
	alt="Tonteria Bookshop" /></a>

<div id="blog-description">The online bookstore with java-core</div>

</div>

<hr class="hide" />

<div id="nav">
<ul class="clearfix">
	<li class="page_item"><a
		href="../">Home</a></li>
		
	<li class="current_page_item"><a
		href="./"
		title="Ricerca avanzata">Advanced search</a>
		</li>
		<%
			if (!logged) {
		%>
	<li class="page_item"><a
		href="../registration/"
		title="Registrazione">Registration</a></li>
		<%
			} else {
		%>
	<!-- <li class="page_item page-item-3"><a
		href="http://localhost:8888/wordpress_it_IT_271/?page_id=2"
		title="Ricerca avanzata">Amministrazione</a></li>-->
	<li class="current_page_item"><a
		href="../cart"
		title="Your shopping cart">Shopping cart</a></li>
		<%
			}
		%>
</ul>
</div>

<div id="container" class="clearfix">
	<div id="wrapper">
		<div id="content">
		
			<div class="page-title-container"><h1 class="page-title">
			<%
			if(request.getAttribute("book") == null){ %>
			
			<% } else%>
			<!-- Titolo della visualizzazione libro -->
			Advanced search</h1></div>
			
			
			<%
			// prendo la lista di libri eventualmente ricercati, altrimenti non mostro nulla
			
			if(request.getAttribute("searchResults") == null){
				
				if(request.getParameter("searchsubmit") != null || request.getParameter("advancedsearchsubmit") != null)
					// � stata fatta una ricerca, ma senza ottenere risultati
					out.write("Sorry, no results for &quot;"+request.getParameter("s")+"&quot;.");
				else
					out.write("You can do an advanced search using the above form.");
				
			}else{
				ArrayList<Book> resultList = ((ArrayList<Book>)request.getAttribute("searchResults"));
				
				Iterator<Book> resultsIterator = resultList.iterator();
				Book currentResultBook;
				
				// scorro la lista dei risultati e li aggiungo alla pagina
				while (resultsIterator.hasNext()) {
					
					currentResultBook = resultsIterator.next();
					
					String isbn = currentResultBook.getIsbn();
					String title = currentResultBook.getTitle();
					String author = currentResultBook.getAuthor();
					String imgPath = currentResultBook.getImage();
					String editor = currentResultBook.getEditor();
					String price = String.valueOf(currentResultBook.getPrice());
					
				%>
					<!-- BEGIN risultato/i -->
					<div class="hentry-search">
		            <div class="thumb">
						<img src="..<% out.write(imgPath); %>" alt="<% out.write(title); %>" title="<% out.write(title); %>" />
					</div>
					<div class="entry-search">
						<h3 class="search-title"><a href="../view?isbn=<% out.write(isbn); %>" title="<% out.write(title); %>"><% out.write(title + "-" + author); %></a></h3>
				    <div class="search-editor">Editor: <% out.write(editor); %> &#8211; ISBN: <% out.write(isbn); %></div>
		            <div class="search-price"><% out.write(price); %> &euro;</div>
		            <%
		            if(logged){
		            %>
		            <div class="search-add">
		            <form action="../cart" method="post">
		            	<input name="addBook" type="image" class="add-submit"
		            	value="<% out.write(isbn); %>" title="Add <% out.write(title); %> to chart"
		            	src="../images/theme/add-cart.png" />
		            </form>
		            </div>
		            <%
		            }
		            %>
		            </div>
					</div>
					<!-- END risultato/i -->
				<%
				
				}
			}
			%>
			
			<div class="navigation clearfix">
				<div class="nav-previous"></div>
				<div class="nav-next"></div>
			</div>

		</div><!-- #content -->
	</div><!-- #wrapper -->

<div id="sidebar"><div id="sidebar-inner">
	<ul>


	<li id="search">
	
	
	<form id="mainsearch" method="get" action="../searchres/">
	<div>
	<input id="s" class="text-input" name="s" type="text"
		value="cerchi un libro?"
		onfocus="if (this.value == 'cerchi un libro?') {this.value = '';}"
		onblur="if (this.value == '') {this.value = 'cerchi un libro?';}"
		tabindex="1" />
		<input id="searchsubmit" name="searchsubmit"
		type="image"
		src="../images/theme/btn-search.png"
		alt="search" tabindex="2" value="search" /></div>
	</form>
	</li>
	
	<%	
			if (!logged) {
			%>
	<li id="login">
	<h3 class="sidebar-title">Login</h3>
    <% if(request.getAttribute("failedMessage") != null){ %>
    <span class="error"><% out.print(request.getAttribute("failedMessage")); %></span>
    <% } %>
    <form id="login-form" method="post" action="validateuser">
    <div>
    <p>Username</p>
    <input id="s" class="text-input" name="username" type="text" />
    <p>Password</p>
    <input id="s" class="pass-input" name="password" type="password" />
    </div>
    <div id="loginsubmit-div">
    <input id="loginsubmit" name="login" type="image" src="../images/theme/login-key.png" />
    </div>
    </form>
    </li>
	<%
			} else {
	%>
	<li id="shopping-chart">
	<h3 class="sidebar-title"> <%
		out.write(customer.getName());
	%>'s panel</h3>
	<form id="logout-form" method="post" action="validateuser">
	<div class="logout"></div>
	</form>
	</li>
	<%
			}
		
	%>	

	<li id="categories">
	<h3 class="sidebar-title">Categorie</h3>
	<ul>
		<li class="cat-item cat-item-1"><a href="view?cat=1"
			title="Visualizza tutti i libri thriller">Thriller</a></li>
		<li class="cat-item cat-item-2"><a href="view?cat=2"
			title="Visualizza tutti i libri thriller">Best Seller</a></li>
		<li class="cat-item cat-item-1"><a href="view?cat=3"
			title="Visualizza tutti i libri thriller">Avventura</a></li>
		<li class="cat-item cat-item-1"><a href="view?cat=4"
			title="Visualizza tutti i libri thriller">Azione</a></li>

	</ul>
	</li>



</ul>
</div></div><!-- #sidebar -->
	</div><!-- #container -->
</div></div></div><!-- #universe -->

<hr class="hide" />

<div id="footer-a"><div id="footer-b">
   	<p>&copy; 2009 <span>tBookshop</span>. All rights reserved. </p>
</div></div><!-- #footer -->



</body>
</html>
