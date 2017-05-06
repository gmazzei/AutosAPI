# AutosAPI
API REST de autos.
<br/>

<p>Tecnologías utilizadas:</p>
<ul>
<li>Spring MVC</li>
<li>Spring Data</li>
<li>Hibernate</li>
<li>MySQL 5.6</li>
</ul>
<br/>

## Instrucciónes de instalación

<p>Requisitos previos:</p>
<ul>
<li>JDK 1.8</li>
<li>MySQL 5.6</li>
</ul>
</br>

<p>1) Clonar repositorio:</p>
<pre>
git clone git@github.com:gmazzei/AutosAPI.git
</pre>

<p>2) Crear la Base de datos con el archivo <a href="https://github.com/gmazzei/AutosAPI/blob/development/Database.sql">Database.sql</a>.</p>

<p>3) Modificar la configuración de la base de datos en el archivo <a href="https://github.com/gmazzei/AutosAPI/blob/development/src/main/resources/application.properties">application.properties</a></p>

<p>4) Ejecutar desde Eclipse o deployar en un Application Server.</p>
<br/>

## Guía de uso

### Consultas
<pre>GET /autos   <i>#Obtiene la colección completa</i></pre>

Ejemplo:
<br/>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://localhost:8080/autos" 
</pre>

<br/>
<pre>GET /autos/$id   <i>#Obtiene el auto cuyo ID = $id</i></pre>

Ejemplo:
<br/>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://localhost:8080/autos/1"
</pre>

<br/>

### Crear
<pre>/autos?nombre=$nombre&opcionales=$opcionales   <i>#Crea un nuevo auto</i></pre>

<p>Parámetros:</p>
<ul>
<li>nombre (obligatorio)</li>
<li>opcionales: Los valores deben ir separados por comas.</li>
</ul>
Ejemplo:
<br/>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST "http://localhost:8080/autos?nombre=coupe&opcionales=AA,A,SFABS,TC,LLA"
</pre>
<br/>

### Modificar
<pre>PUT /autos/$id?nombre=$nombre&opcionales=$opcionales   <i>#Modifica el auto con ID = $id</i></pre>

<p>Parámetros:</p>
<ul>
<li>nombre (obligatorio)</li>
<li>opcionales: Los valores deben ir separados por comas.</li>
</ul>
Ejemplo:
<br/>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X PUT "http://localhost:8080/autos/1?nombre=coupe&opcionales=AA,A,SFABS,TC,LLA"
</pre>
<br/>


### Eliminar
<pre>DELETE /autos/$id   <i>#Elimina el auto con ID = $id</i></pre>
Ejemplo:
<br/>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X DELETE "http://localhost:8080/autos/1"
</pre>
<br/>

#### Siglas de los opcionales
<ul>
<li>A: Airbag</li>
<li>AA: Aire Acondicionado</li>
<li>TC: Techo Corredizo</li>
<li>LLA: Llantas de Aleación</li>
<li>SFABS: Sistema de Frenos ABS</li>
</ul>
