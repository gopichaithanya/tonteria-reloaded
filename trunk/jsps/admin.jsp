<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administer your tbookstore</title>
</head>
<body>
<h1>Administer your tbookstore</h1>

<div style="border:1px solid black;">
<h3>Manage Users</h3>

<form action="customers" method="get">

name: <input type="text" name="name"><br>
surname: <input type="text" name="surname"><br>
password: <input type="text" name="password"><br>
email: <input type="text" name="email"><br>
<fieldset>
  <legend>Scegli cosa fare</legend>
  Crea un nuovo utente<input type="radio" name="choice" value="1"/><br>
  Elimina l'utente <input type="radio" name="choice" value="2"/><br>
  Fai login <input type="radio" name="choice" value="3"/><br>
</fieldset>
<br>
<input type="submit">

</form>
</div>
<br>
<div style="border:1px solid black;">
<h3>Manage Books</h3>


<form action="books" method="get">

titolo: <input type="text" name="title"><br>
autore: <input type="text" name="author"><br>
isbn: <input type="text" name="isbn"><br>
prezzo: <input type="text" name="price"><br>
descrizione: <textarea name="description" rows="5" cols="30">descrizione</textarea>

<fieldset>
  <legend>Scegli cosa fare</legend>
  Inserire un nuovo libro<input type="radio" name="choice" value="1"/><br>
  rimuovere un libro (dato l'isbn)<input type="radio" name="choice" value="2"/><br>
  Visualizzare lista dei libri <input type="radio" name="choice" value="3"/><br>
</fieldset>
<br>
<input type="submit">
</form>
</div>


</body>
</html>