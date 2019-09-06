<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="negocio.PokemonPetABM"%>
<%@ page import="datos.PokemonEvolution"%>
<%@ page import="negocio.PokemonEvolutionABM"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.ServletException"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pokedex-Lite</title>
</head>
<body>
	<form name="formulario1" method="get" action="">
		<strong>ABM de : </strong> <select class="seleccion" name="seleccion"
			id="seleccion" onchange="select(this.value)">
			<option value="0" disabled selected hidden>Seleccione
			<option value="1">Mascotas pokemon
			<option value="2">Evoluciones pokemon
		</select>
	</form>
	<table border="1" id="tabla">
		
			<!-- Acá van las columnas dependiendo del ABM elegido -->
					
	</table>
	
	
	
	
	
	<script type="text/javascript">
	var xmlhttp;
	var tabla;
        function select(tipoABM){
        	tabla=document.getElementById("tabla");
        	 if(window.XMLHttpRequest){
     	        xmlhttp=new XMLHttpRequest();
     	    }else{
     	        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
     	    }
     	    xmlhttp.onreadystatechange=function(){
     	        if(xmlhttp.readyState==4 && xmlhttp.status==200){
     	        	tabla.innerHTML=xmlhttp.responseText;
     	        }
     	    }
        	//peticionHTML(tabla);//el listener funcionara dentro de la funcion?
        			
            if(tipoABM=="1")
		    	xmlhttp.open("GET","PokemonPet?mostrar=1");
            else	//2
            	xmlhttp.open("GET","PokemonEvolution?mostrar=1");
        	//lo enviamos al controller para que muestre la tabla
            
		    xmlhttp.send();//se dirige al xmlhttp.onreadystatechange declarado en la funcion peticionHTML
		
            
        }
        
        
     
        
        function eliminarPokemonPet(idPokemonPet){
		    var xmlhttp;
		    if(window.XMLHttpRequest){
		        xmlhttp=new XMLHttpRequest();
		    }else{
		        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		    }
		    xmlhttp.onreadystatechange=function(){
		        if(xmlhttp.readyState==4 && xmlhttp.status==200){
		        	filas.innerHTML=xmlhttp.responseText;
		        }
		    }
		    xmlhttp.open("GET","PokemonPet?mostrar=1&borrar=1&idPokemonPet="+idPokemonPet);
		    xmlhttp.send();
		}
        
        
        
        
function modificarPokemonPet(idPokemonPet){
			
			if(document.getElementById("boton"+idPokemonPet).innerHTML!="Guardar cambios" ){
				
		        var filas=document.getElementById("filas");
		        var c1,c2,c3,c4;
		        c1=document.getElementById("c1f"+idPokemonPet).innerHTML;
		        c2=document.getElementById("c2f"+idPokemonPet).innerHTML;
		        c3=document.getElementById("c3f"+idPokemonPet).innerHTML;
		        c4=document.getElementById("c4f"+idPokemonPet).innerHTML;
		        var input1 =document.createElement("input");
		        var select2 =document.createElement("select");
		        var input3 =document.createElement("input");
		        var input4 =document.createElement("input");
		        var option=document.createElement("option");
		        
		        //lo haria todo con la misma var pero por algun motivo no me deja
		        input1.type="text";
		        input1.setAttribute('value', c1);
		        document.getElementById("c1f"+idPokemonPet).innerHTML="";
		        document.getElementById("c1f"+idPokemonPet).appendChild(input1);
		        
		        //		ESTO ES PARA HACER UN SELECT EN LA SEGUNDA COLUMNA(TYPES) QUE LISTE LOS NOMBRE DE LAS CLASES DE POKEMONEVOLUTION
		        
		        /*
		        	
		        	PokemonEvolutionABM pokemonEvolutionABM;
		        	HttpServletResponse response;
		        	List<PokemonEvolution> pokemonEvolutions= pokemonEvolutionABM.traer();
		        	pks=(Pokemon)pks;
		        	for(PokemonEvolution pks:pokemonsEvolutions){
		        		
		        
		        	
					
		        option.value=<@ // response.getWriter().append( pks.getIdPokemon()); %>
		        option.text="<@ // response.getWriter().append(pks.getName());	%>	
		        
		        select.appendChild(option);
		        	
		        	
		        
		        
		        
		        <@
		        // }
		        %>
		        */	
		        				//Lamentablemente debo comentar esto debido a que no llegue con los tiempos para terminarlo
		     //   select2.setAttribute('value', c2);
		        document.getElementById("c2f"+idPokemonPet).innerHTML="";
		        document.getElementById("c2f"+idPokemonPet).appendChild(select2);
		        
		        input3.setAttribute('value', c3);
		        document.getElementById("c3f"+idPokemonPet).innerHTML="";
		        document.getElementById("c3f"+idPokemonPet).appendChild(input3);
		        
		        input4.setAttribute('value', c4);
		        document.getElementById("c4f"+idPokemonPet).innerHTML="";
		        document.getElementById("c4f"+idPokemonPet).appendChild(input4);
				
		        document.getElementById("boton"+idPokemonPet).innerHTML="Guardar cambios";
			}else{
				document.getElementById("boton"+idPokemonPet).innerHTML="Modificar";
				guardarCambios(idPokemonPet); 
			}
	    }
		function guardarCambios(idPokemonPet){
			 var xmlhttp;
			 var c1,c2,c3,c4;
			 
			 var filas=document.getElementById("filas");
			 if(idPokemonPet==0){//Esto cambia dependiendo si viene de modificar/agregar
				 //los primeros son input y en else son td
				  c1=document.getElementById("c1").value;
				  c2=document.getElementById("c2").value;
				  c3=document.getElementById("c3").value;
				  c4=document.getElementById("c4").value;
				 
			 }else{
				  c1=document.getElementById("c1f"+idPokemonPet).childNodes[0].value;
				  c2=document.getElementById("c2f"+idPokemonPet).childNodes[0].value;
				  c3=document.getElementById("c3f"+idPokemonPet).childNodes[0].value;
				  c4=document.getElementById("c4f"+idPokemonPet).childNodes[0].value;
				  
			 }
			 
		        if(window.XMLHttpRequest){
		            xmlhttp=new XMLHttpRequest();
		        }else{
		            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		        }
		        xmlhttp.onreadystatechange=function(){
		            if(xmlhttp.readyState==4 && xmlhttp.status==200){
		            	filas.innerHTML=xmlhttp.responseText;
		            }
		        }
		        xmlhttp.open("GET","PokemonPet?mostrar=1&guardar=1&name="+c1+"&type="+c2+"&levelToFound="+c3+"&habilities="+c4,true);
		        xmlhttp.send();
		}
		function agregarPokemonPet(){
			var renglon=document.createElement("tr");
			
			var c1,c2,c3,c4;
			
			
			c1=document.createElement("td");
			c2=document.createElement("td");
			c3=document.createElement("td");
			c4=document.createElement("td");
			c5=document.createElement("td");
			
			var input1 =document.createElement("input");
	        var select2 =document.createElement("select");
	        var input3 =document.createElement("input");
	        var input4 =document.createElement("input");
	        var boton=document.createElement("button");
	        
	        input1.setAttribute("id","c1");
	        select2.setAttribute("id","c2");
	        input3.setAttribute("id","c3");
	        input4.setAttribute("id","c4");
	        
	        boton.setAttribute("onclick","guardarCambios(0)");
	        boton.innerHTML="Guardar cambios";
			
	        c1.appendChild(input1);
	        c2.appendChild(select2);
	        c3.appendChild(input3);
	        c4.appendChild(input4);
	        c5.appendChild(boton);
	        
			renglon.appendChild(c1);
			renglon.appendChild(c2);
			renglon.appendChild(c3);
			renglon.appendChild(c4);
			renglon.appendChild(c5);
			
			var tbody=document.getElementById("tbody");
			tbody.appendChild(renglon);
			
		}
</script>

</body>
</html>