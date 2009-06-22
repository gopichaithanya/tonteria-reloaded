<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="it-IT">
<head profile="http://gmpg.org/xfn/11">

	<title>tBookshop &raquo; The online bookshop with java-core</title>

	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

	<link rel="stylesheet" type="text/css" href="includes/styles/default.css" />


</head>

<!--  credo che la classe sia inutile, o meglio utile solo per idetificare il contenuto -->
<body class="wordpress y2009 m05 d31 h09 home blog">

<jsp:useBean id="customer" scope="session" class="bean.stateless.Customer" />

<%
	boolean logged = false;

	if (customer.getName() != null)
		logged = true;
	
/*	try {
		if (customer.getName() != null) {
			out.println("Benvenuto -> " + customer.getName());
			logged = true;

		} else {
			
			if (request.getAttribute("failedMessage") != null) {
				out.println("Errore login! -> "
						+ session.getAttribute("failedMessage"));
			} else {

				//out.println("primo accesso!");
			}
		}
	} catch (Exception e) {
		out.println("[Exception] nessun bean caricato!!");

		
	} */
%>


<div id="universe-a">
	<div id="universe-b">
		<div id="universe-c">

<div id="header">

<a href="./"><img
	src="./images/theme/site-logo.png"
	alt="Tonteria Bookshop" /></a>

<div id="blog-description">The online bookstore with java-core</div>

</div>

<hr class="hide" />

<div id="nav">
<ul class="clearfix">
	<li class="current_page_item"><a
		href="./">Home</a></li>
	<li class="page_item page-item-1"><a
		href="search/"
		title="Ricerca avanzata">Advanced Search</a></li>
		<%
			if (!logged) {
		%>
	<li class="page_item page-item-2"><a
		href="registration/"
		title="Registrazione">Registration</a></li>
		<%
			} else {
		%>
	<!-- <li class="page_item page-item-3"><a
		href="http://localhost:8888/wordpress_it_IT_271/?page_id=2"
		title="Ricerca avanzata">Administration</a></li>-->
	<li class="page_item page-item-4"><a
		href="shoppingcart/"
		title="Il tuo carrello">Shopping cart</a></li>
		<%
			}
		%>
</ul>
</div>

<div id="container" class="clearfix">
	<div id="wrapper">
		<div id="content">
		
			<!-- Titolo della pagina -->
			<div class="page-title-container"><h1 class="page-title">Latest offers</h1></div>
			
			<!-- Lista dei libri (contenuto della pagina) -->
			<div id="post-1" class="hentry">
            <div class="photo">
				<img src="images/cover/altaMarea.jpg" alt="Alta Marea" />
				<span></span>
			</div>
			<div class="entry">
				<h2 class="entry-title"><a href="view?p=1" rel="bookmark" title="Alta Marea">Alta Marea</a></h2>
                <h3 class="entry-subtitle"><a href="author?id=1" title="Clive cussler"></a>di Clive Cussler</h3>
		    <div class="entry-date entry-meta">Editore: TEA &#8211; ISBN: 887818750X</div> 

				<div class="entry-content">
					<p>Descrizione / Anteprima scheda libro</p>
				</div>

				<div class="entry-meta">
				<span class="cat-list">Categorie: <a href="index?cat=1" title="Visualizza tutti i libri Best Seller" rel="category">Best Seller</a>, <a href="index?cat=1" title="Visualizza tutti i libri di Avventura" rel="category">Avventura</a></span>
				</div>
               </div>
			</div>

			<div id="post-1" class="hentry">
            <div class="photo">
				<img src="images/cover/altaMarea.jpg" alt="Alta Marea" />
				<span></span>
			</div>
			<div class="entry">
				<h2 class="entry-title"><a href="view?p=1" rel="bookmark" title="Alta Marea">Alta Marea</a></h2>
                <h3 class="entry-subtitle"><a href="author?id=1" title="Clive cussler"></a>di Clive Cussler</h3>
		    <div class="entry-date entry-meta">Editore: TEA &#8211; ISBN: 887818750X</div> 

				<div class="entry-content">
					<p>Descrizione / Anteprima scheda libro</p>
				</div>

				<div class="entry-meta">
				<span class="cat-list">Categorie: <a href="index?cat=1" title="Visualizza tutti i libri Best Seller" rel="category">Best Seller</a>, <a href="index?cat=1" title="Visualizza tutti i libri di Avventura" rel="category">Avventura</a></span>
				</div>
               </div>
			</div>
            
			
			<div class="navigation clearfix">
				<div class="nav-previous"></div>
				<div class="nav-next"></div>
			</div>

		</div><!-- #content -->
	</div><!-- #wrapper -->

<div id="sidebar"><div id="sidebar-inner">
	<ul>


	<li id="search">
	
	
	<form id="mainsearch" method="get" action="searchres/">
	<div>
	<input id="s" class="text-input" name="s" type="text"
		value="cerchi un libro?"
		onfocus="if (this.value == 'cerchi un libro?') {this.value = '';}"
		onblur="if (this.value == '') {this.value = 'cerchi un libro?';}"
		tabindex="1" />
		<input id="searchsubmit" name="searchsubmit"
		type="image"
		src="./images/theme/btn-search.png"
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
    <input id="loginsubmit" name="login" type="image" src="./images/theme/login-key.png" />
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
