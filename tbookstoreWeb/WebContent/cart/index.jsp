<%@ page import="bean.stateless.Customer, bean.stateless.Book, bean.stateless.LineItem, java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="it-IT">
<head profile="http://gmpg.org/xfn/11">

	<title>tBookshop &raquo; The online bookstore with java-core</title>

	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

	
	<link rel="stylesheet" href="../includes/styles/form.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="../includes/styles/default.css" />
	<script language="javascript">
	function display_block() {
		document.getElementById('paymentType').style.display = "block";
		document.getElementById('checkOut').style.display = "none";
	}
	function hide_block() {
		document.getElementById('paymentType').style.display = "none";
	}
</script>

</head>

<% boolean logged = false; %>

<!--  credo che la classe sia inutile, o meglio utile per identificare il contenuto -->
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

</div>



<div id="nav">
<ul class="clearfix">
	<li class="page_item"><a
		href="./">Home</a></li>
	<li class="page_item page-item-1"><a
		href="search/"
		title="Ricerca avanzata">Advanced Search</a></li>

	<!-- <li class="current_page_item page-item-3"><a
		href="http://localhost:8888/wordpress_it_IT_271/?page_id=2"
		title="Ricerca avanzata">Administration</a></li>-->
	<li class="current_page_item page-item-4"><a
		href="shoppingcart/"
		title="Il tuo carrello">Shopping cart</a></li>

</ul>
</div>

<div id="container" class="clearfix">
	<div id="wrapper">
		<div id="content">
		
			<!-- Titolo della pagina -->
			<div class="page-title-container"><h1 class="page-title">Your shopping cart</h1></div>
			
			<!-- Form registrazione (contenuto della pagina) -->
<%
	
	Customer loggedCustomer = ((Customer)session.getAttribute("customer"));
	//DEBUG
	System.out.println("[JSP - cart] Utente loggato: "+loggedCustomer.getName());

 	try {
 		if (loggedCustomer.getEmail() != null) {
        	logged = true;
        	
 			if(request.getAttribute("cartList") != null){
 				Collection<LineItem> cartList = (Collection<LineItem>)request.getAttribute("cartList");
 				
 				Iterator<LineItem> it = cartList.iterator();
 				LineItem currentLineItem;
 				
 				while(it.hasNext()){
 					currentLineItem = it.next();
 					
 					Book book = currentLineItem.getBook();
 					
 					String isbn = book.getIsbn();
					String title = book.getTitle();
					String author = book.getAuthor();
					String imgPath = book.getImage();
					String editor = book.getEditor();
					int price = book.getPrice();
					long quantity = currentLineItem.getQuantity();
 				%>
 				
	 			<div class="hentry-search">
			    <div class="thumb">
				<img src="..<% out.write(imgPath); %>" alt="<% out.write(title); %>" title="<% out.write(title); %>" />
				</div>
				<div class="entry-search">
				<h3 class="search-title"><a href="../view?isbn=<% out.write(isbn); %>" title="<% out.write(title); %>"><% out.write(title + "-" + author); %></a></h3>
				<div class="search-editor">Editor: <% out.write(editor); %> &#8211; ISBN: <% out.write(isbn); %></div>
			    <div class="cart-price"><% out.write(String.valueOf(price)); %> &euro;</div>
	            <div class="cart-quantity">Quantit&agrave;: <% out.write(String.valueOf(quantity)); %></div>
			    <div class="cart-remove">
			    <form action="../shoppingcart/" method="post">
			     	<input name="removeBook" type="image" class="remove-submit" 
	                value="<% out.write(isbn); %>" title="Remove <% out.write(title); %> from your cart"
			        src="../images/theme/remove-cart.png" />
			    </form>
			    </div>
			    </div>
				</div>	
 					
 				<%	
 				}
 				
 				%>
 				<div id="checkOut">
 					<a href="javascript:display_block()">Checkout</a>
 				</div>
 				<div id="paymentType">
 				<form action="/tbookstoreWeb/shoppingcart/" method="post">
					<select  name="paymentChoice">
					    <option value="Credit card">Credit Card</option>
					    <option value="Bancomat">Bancomat</option>
					    <option value="Paypal">Paypal</option>
					</select>
					<input type="submit" name="checkout" value="Ship order"/>
				</form>
				</div> 				
 				<%
 			} else {
 				out.println("Shopping cart empty!");
 			}

            %>
            

			
			<%
 		} else {

 			out.println("Devi effettuare il login prima di utilizzare il carrello");
 			
 		}
 	} catch (Exception e) {
 		out.println("[Exception] nessun bean caricato!!");

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
    <input id="loginsubmit" name="login" type="image" src="./images/theme/login-key.png" />
    </div>
    </form>
    </li>
	<%
			} else {
	%>
	<li id="shopping-chart">
	<h3 class="sidebar-title"> <%
		out.write(loggedCustomer.getName());
	%>'s panel</h3>
	<form id="logout-form" method="post" action="../validateuser">
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
	
</div></div><!-- #universe -->

<hr class="hide" />

<div id="footer-a"><div id="footer-b">
   	<p>&copy; 2009 <span>tBookshop</span>. All rights reserved. </p>
</div></div><!-- #footer -->



</body>
</html>