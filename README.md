# AutosAPI
API REST de autos.
<br/>

<p>Tecnologías utilizadas:</p>
<ul>
<li>Spring MVC</li>
<li>Spring Data</li>
<li>Hibernate</li>
<li>MySQL 5.6</li>
<li>Maven 3</li>
</ul>
<br/>

## Instrucciones de instalación

<p>Requisitos previos:</p>
<ul>
<li>JDK 1.8</li>
<li>Maven 3</li>
<li>MySQL 5.6</li>
</ul>
</br>

<p>1) Clonar repositorio:</p>
<pre>
git clone git@github.com:gmazzei/AutosAPI.git
</pre>

<p>2) Crear la Base de datos con el archivo <a href="https://github.com/gmazzei/AutosAPI/blob/development/Database.sql">Database.sql</a>.</p>

<p>3) Modificar la configuración de la base de datos en el archivo <a href="https://github.com/gmazzei/AutosAPI/blob/development/src/main/resources/application.properties#L3-L5">application.properties</a>.</p>

<p>4) Ejecutar desde Eclipse o desplegar en un Tomcat 7.</p>
<br/>

### Creación de WAR para Tomcat 7
<p>1) Para crear un war, entrar en el directorio principal de la app y ejecutar:</p>
<pre>
mvn clean package
</pre>
<p>2) Entrar en <i>target/</i> y renombrar el archivo AutosAPI-0.1.0.war a ROOT.war</p>
<p>3) Ir al directorio del Tomcat y copiar el war a <i>/webapps</i></p>
<p>4) Ejecutar:</p>
<pre>
sh bin/startup.sh
</pre>
<p>5) Ingresar a <a href="http://localhost:8080/autos">http://localhost:8080/autos</a></p>

<br/>

## Guía de uso

### Consultas
<pre>GET /autos   <i>#Obtiene la colección completa</i></pre>

Ejemplo:
<br/>
<p>Local</p>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://localhost:8080/autos" 
</pre>
<p>Producción</p>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://autos-gmazzei.rhcloud.com/autos" 
</pre>

<br/>
<pre>GET /autos/$id   <i>#Obtiene el auto cuyo ID = $id</i></pre>

Ejemplo:
<br/>
<p>Local</p>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://localhost:8080/autos/1"
</pre>
<p>Producción</p>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "http://autos-gmazzei.rhcloud.com/autos/1"
</pre>

<br/>

### Crear
<pre>POST /autos   <i>#Crea un nuevo auto</i></pre>
<p>Estructura del body:</p>
<pre>
{
  "nombre": "$nombre",   <i>#Obligatorio</i>
  "opcionales": [...]   <i>#Obligatorio</i>
}
</pre>

Ejemplo:
<br/>
<p>Local</p>
<pre>
curl -H "Content-Type: application/json" -X POST -d '{"nombre":"sedan", "opcionales": ["AA","A","TC","SFABS","LLA"]}' "http://localhost:8080/autos"
</pre>
<p>Producción</p>
<pre>
curl -H "Content-Type: application/json" -X POST -d '{"nombre":"sedan", "opcionales": ["AA","A","TC","SFABS","LLA"]}' "http://autos-gmazzei.rhcloud.com/autos"
</pre>

<br/>

### Modificar
<pre>PUT /autos/$id   <i>#Modifica el auto con ID = $id</i></pre>
<p>Estructura del body:</p>
<pre> 
{
  "nombre": "$nombre",   <i>#Obligatorio</i>
  "opcionales": [...]   <i>#Obligatorio</i>
}
</pre>

Ejemplo:
<br/>
<p>Local</p>
<pre>
curl -H "Content-Type: application/json" -X PUT -d '{"nombre":"sedan", "opcionales": ["AA","A","TC","SFABS","LLA"]}' "http://localhost:8080/autos/1"
</pre>
<p>Producción</p>
<pre>
curl -H "Content-Type: application/json" -X PUT -d '{"nombre":"sedan", "opcionales": ["AA","A","TC","SFABS","LLA"]}' "http://autos-gmazzei.rhcloud.com/autos/1"
</pre>

<br/>


### Eliminar
<pre>DELETE /autos/$id   <i>#Elimina el auto con ID = $id</i></pre>
Ejemplo:
<br/>
<p>Local</p>
<pre>
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X DELETE "http://localhost:8080/autos/1"
</pre>
<p>Producción</p>
<pre>
curl -H "Content-Type: application/json" -X PUT -d '{"nombre":"sedan", "opcionales": ["AA","A","TC","SFABS","LLA"]}' "http://autos-gmazzei.rhcloud.com/autos/1"
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
