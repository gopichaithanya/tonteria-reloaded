<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="it-IT">
<head profile="http://gmpg.org/xfn/11">

	<title>tBookshop &raquo; The online bookstore with java-core</title>

	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

	
	<link rel="stylesheet" href="../includes/styles/form.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="../includes/styles/default.css" />

</head>

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

<div id="nav">
<ul class="clearfix">
	<li class="page_item"><a
		href="../">Home</a></li>
	<li class="page_item page-item-2"><a
		href="../search/"
		title="Ricerca avanzata">Advanced search</a></li>
	<li class="current_page_item page-item-2"><a
		href="./"
		title="Ricerca avanzata">Registration</a></li>
	<!-- <li class="page_item page-item-2"><a
		href="http://localhost:8888/wordpress_it_IT_271/?page_id=2"
		title="Ricerca avanzata">Amministrazione</a></li>-->

</ul>
</div>

<div id="container" class="clearfix">
	<div id="wrapper">
		<div id="content">
		
			<!-- Titolo della pagina -->
			<div class="page-title-container"><h1 class="page-title">Registration</h1></div>
			
			<!-- Form registrazione (contenuto della pagina) -->
			
<span class="text">
Compile all fields to register and join us!</span>

<form action="../registration" method="post">
<ul>
		
	
<li id="foli1" class="section">
	<label class="desc" id="title1" for="Field1">
		Name
				<span id="req_1" class="req">*</span>
			</label>
	<span>
		<input id="Field1" name="name" type="text" class="field text" value="" size="8" tabindex="1" />
		<label for="Field1">First</label>
	</span>
	<span>
		<input id="Field2" name="surname" type="text" class="field text" value="" size="14" tabindex="2" />
		<label for="Field2">Last</label>
	</span>
	</li>


<li id="foli3" 		class="section">
	<label class="desc" id="title3" for="Field3">
		Email
				<span id="req_3" class="req">*</span>
			</label>
	<div>
		<input id="Field3" 			name="email" 			type="text" 			class="field text medium" 			value="" 			maxlength="255" 			tabindex="3" 			/> 
	</div>
		<p class="instruct" id="instruct3" ><small>This will be used to log in into your account.</small></p>
	</li>


<li id="foli119" 		class="section">
	<label class="desc" id="title119" for="Field119">
		Password
				<span id="req_119" class="req">*</span>
			</label>
	<span>
		<input id="s" 			name="password" 			type="password" 			class="field text small" 			value="" 			size="8" 			tabindex="4" 			/>
		<label for="Field119">Choose Password</label>
	</span>
	<span>
		<input id="Field120" name="confirm-password" type="password" class="field text" value="" size="14" tabindex="5" />
		<label for="Field120">Confirm Password</label>
	</span>
		<p class="instruct" id="instruct119"><small>Password length must be between 6 and 14 characters. Retype password twice.</small></p>
	</li>


	
	<li class="buttons">
				<input id="saveForm" class="btTxt submit" type="submit" value="Register new account" />
			</li>
</ul>
</form>
			
			<div class="navigation clearfix">
				<div class="nav-previous"></div>
				<div class="nav-next"></div>
			</div>

		</div><!-- #content -->
	</div><!-- #wrapper -->

<div id="sidebar"><div id="sidebar-inner">
	<ul>


	<li id="search">
	
	
	<form id="mainsearch" method="get" action="../search">
	<div>
	<input id="s" class="text-input" name="s" type="text"
		value="cerchi un libro?"
		onfocus="if (this.value == 'cerchi un libro?') {this.value = '';}"
		onblur="if (this.value == '') {this.value = 'cerchi un libro?';}"
		tabindex="1" /> <input id="searchsubmit" name="searchsubmit"
		type="image"
		src="../images/theme/btn-search.png"
		alt="search" tabindex="2" /></div>
	</form>
	</li>
	
	<li id="login">
	<h3 class="sidebar-title">Login</h3>
    <form id="login-form" method="post" action="../validateuser">
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
