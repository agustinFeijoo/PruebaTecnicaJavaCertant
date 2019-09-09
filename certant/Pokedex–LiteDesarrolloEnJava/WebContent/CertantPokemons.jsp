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
	<table border="1" id='tabla'>
		
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
		    var tabla=document.getElementById("tabla");
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
		    
		    xmlhttp.open("GET","PokemonPet?mostrar=1&borrar=1&idPokemonPet="+idPokemonPet);
		    xmlhttp.send();
		}
        
        
        
        
function modificarPokemonPet(idPokemonPet){
			
			if(document.getElementById("boton"+idPokemonPet).innerHTML!="Guardar cambios" ){
				
		        
				
		        var c1,c2,c3,c4,c5;
		        var input1 =document.createElement("input");
		        var select3 =document.createElement("select");
		        select3.setAttribute("id","opcionesPokemon");
		        var input4 =document.createElement("input");
		        var input5 =document.createElement("input");
		        var opcionSeleccionada;
		        var options;
		        
		        c1=document.getElementById("c1f"+idPokemonPet).innerHTML;
		        //c2=document.getElementById("c2f"+idPokemonPet).innerHTML; //La columna dos varía acorde a la clase
		        c3=document.getElementById("c3f"+idPokemonPet).innerHTML;
		        c4=document.getElementById("c4f"+idPokemonPet).innerHTML;
		        c5=document.getElementById("c5f"+idPokemonPet).innerHTML;
		        
		        //lo haria todo con la misma var pero por algun motivo no me deja
		        input1.type="text";
		        input1.setAttribute('value', c1);
		        document.getElementById("c1f"+idPokemonPet).innerHTML="";
		        document.getElementById("c1f"+idPokemonPet).appendChild(input1);
		        
		        
		        
		        //		ESTO ES PARA HACER UN SELECT EN LA SEGUNDA COLUMNA(TYPES) QUE LISTE LOS NOMBRE DE LAS CLASES DE POKEMONEVOLUTION

		        <%!
		        String mostrarOptions(){ //la Columna 3 debe mostrar un select de las clases pokemon
		        	String options="";						// El id sirve para hacer un selected del pokemonEvolution.name al que pertenece el pokemonPet
		        	PokemonEvolutionABM pokemonEvolutionABM=new PokemonEvolutionABM();
		        	String name;
		        	List<PokemonEvolution> pokemonEvolutions= pokemonEvolutionABM.traer();
		        	for(PokemonEvolution pks:pokemonEvolutions){
		        		name=pks.getName();
		        		options=options+"<option id=\""+pks.getIdPokemon()+"\" value=\""+name+"\">"+name+"</option>";//error de comillas,use las \" para no tener error de js
		        	}
		        	return options;

		        }
		        %>
		      
		        
		        
		        
		        options='<%=mostrarOptions()%>';
		        
		        
		        select3.innerHTML=options;
		       	//En estos momentos c3 tiene el nombre de la clase en el innerHtml y voy a buscarlo para poner un efecto selected
				opcionSeleccionada=c3;
				buscarSelect(opcionSeleccionada,select3);
		       	
		        document.getElementById("c3f"+idPokemonPet).innerHTML="";//vacio el td y lo lleno con el select
		
		       	c3=select3;
		       	
		       	
		       	document.getElementById("c3f"+idPokemonPet).appendChild(select3);
		          
		        
		        input4.setAttribute('value', c4);
		        document.getElementById("c4f"+idPokemonPet).innerHTML="";
		        document.getElementById("c4f"+idPokemonPet).appendChild(input4);
		        
		        input5.setAttribute('value', c5);
		        document.getElementById("c5f"+idPokemonPet).innerHTML="";
		        document.getElementById("c5f"+idPokemonPet).appendChild(input5);
		        
				
		        document.getElementById("boton"+idPokemonPet).innerHTML="Guardar cambios";
			}else{
				document.getElementById("boton"+idPokemonPet).innerHTML="Modificar";
				guardarCambios(idPokemonPet); 
			}
	    }
		function guardarCambios(idPokemonPet){
			 var xmlhttp;
			 var c1,c3,c4,c5;
			 
			 var tabla=document.getElementById("tabla");
			 if(idPokemonPet==0){//Esto cambia dependiendo si viene de modificar/agregar
				 //los primeros son input y en else son td
				  c1=document.getElementById("c1").value;
					
				  c3=document.getElementById("c3").value;
				  c4=document.getElementById("c4").value;
				  c5=document.getElementById("c5").value;
				  
				 
			 }else{
				  c1=document.getElementById("c1f"+idPokemonPet).childNodes[0].value;
				  
				  c3=document.getElementById("c3f"+idPokemonPet).childNodes[0].value;
				  c4=document.getElementById("c4f"+idPokemonPet).childNodes[0].value;
				  c5=document.getElementById("c5f"+idPokemonPet).childNodes[0].value;
				  
			 }
			 
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
		        xmlhttp.open("GET","PokemonPet?mostrar=1&guardar=1&name="+c1+"&className="+c3+"&levelToFound="+c4+"&habilities="+c5,true);
		        xmlhttp.send();
		}
		function agregarPokemonPet(){
			var renglon=document.createElement("tr");
			
			var c1,c3,c4,c5,c6;
			
			
			c1=document.createElement("td");
			c2=document.createElement("td");
			c3=document.createElement("td");
			c4=document.createElement("td");
			c5=document.createElement("td");
			c5=document.createElement("td");
			c6=document.createElement("td");
			
			var input1 =document.createElement("input");
	        var select3 =document.createElement("select");
	        var input4 =document.createElement("input");
	        var input5 =document.createElement("input");
	        
	        var boton=document.createElement("button");
	        
	        input1.setAttribute("id","c1");
	        select3.setAttribute("id","c3");
	        input4.setAttribute("id","c4");
	        input5.setAttribute("id","c5");
	        select3.innerHTML='<%= mostrarOptions() %>';
	        boton.setAttribute("onclick","guardarCambios(0)");
	        boton.innerHTML="Guardar cambios";
			
	        c1.appendChild(input1);
	        c3.appendChild(select3);
	        c4.appendChild(input4);
	        c5.appendChild(input5);
	        
	        c6.appendChild(boton);
	        
			renglon.appendChild(c1);
			renglon.appendChild(c2);
			renglon.appendChild(c3);
			
			renglon.appendChild(c4);
			renglon.appendChild(c5);
			renglon.appendChild(c6);
			
			var tbody=document.getElementById("tbody");
			tbody.appendChild(renglon);
			
		}
		function buscarSelect(buscar,select)// obtenemos el valor a buscar
		{
			// creamos un variable que hace referencia al select
			
			
			// recorremos todos los valores del select
			for(var i=1;i<select.length;i++)
			{
				if(select.options[i].text==buscar)
				{
					// seleccionamos el valor que coincide
					select.selectedIndex=i;
				}
			}
		}
</script>

</body>
</html>