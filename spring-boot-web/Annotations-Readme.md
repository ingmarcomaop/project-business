Si no se indica el metodo se entiende que es GET
Tambien puede llamarse como @GetMapping y ya se sabe que es un
request method del tipo GET
    
@GetMapping("/index") si no hay mas parametros por defecto toma
el parametro value, en este caso, value=/index
    
Para mapear el metodo get a mas rutas se hace {"/index", "/", "/home"}
A una sola ruta seria value="/index"


Al crear un html file la siguiente line se crea utomáticamente:
<meta charset="UTF-8">
Pero, al usar Thymeleaf sale error. Se debe cerrar la llave así:
<meta charset="UTF-8" />

Si le doy click derecho al archivo index.html (Properties) creado y luego 
en "Resource" coincidir UTF-8 para que no genere conflictos. Si se va a 
cambiar, se debe cambiar en el archivo <meta charset="UTF-8" /> y en la
confuguración tambien.

Para importar un titulo creado con Thymeleaf en el html seria:

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<title th:text="${titulo}"></title>
</head>
<body>

<h1 th:text="${titulo}"></h1>

</body>
</html>

Donde, ${titulo} sale de el controlador en la parte:
model.addAttribute("titulo", "Hola Spring Framework");
Lo anterior fue usando Model. Pero tambien, se puede hacer utilizando
ModelMap. Solo que ModelMap es una clase que hereda el metodo
LinkedHashMap<String, Object>
Pero, ambas dan lo mismo.

Lo anterior tambien se puede hacer con Map, y en controlador quedaría:

public String index(Map<String, Object> map) {
        
        map.put("titulo", "Hola Spring Framework");
        return "index";
    }

Otra forma:

public ModelAndView index(ModelAndView mv) {
        
        mv.addObject("titulo", "Hola Spring Framework");
        mv.setViewName("index");
        return mv;
    }
    
Model es una implementación que permite pasar datos a la vista.
    
Un pojo es una clase simple de java que contiene atributos para sus datos, en el caso de Usuario sería nombre, apellido, email, etc; y sus respectivos métodos getters y setters.

<span th:text="${user.name}"></span>
De la anterior manera llamamos alobjeto usuario con el atributo nombre. El por debajo llama a getName.


<span th:if="${user.email == null}" th:text="'The user does not have email.'"></span>
También, se puede hacer así:
<span th:if="${user.email == null}">The user does not have email.</span>

Los imports de: 
List<User> users = new ArrayList<>();
son de java.util

Verificar si hay elementos en una lista creada en el controller:
<div th:if="${users.size() == 0}">There are not users on the list!</div>

Nombre de las columnas de una tabla:
<thead>
<th>Name</th>
<th>Last Name</th>
<th>Email</th>
</thead> 

Para hace un cilco foreach de la lista de usuarios:
<tr th:each="user: ${users}"></tr>
En lo anterior, users hace referencia a el nombre de la lista, es decir, como se definió en el controlador en su método tolist.

Acá preguntamos por <div th:if="${users.size() == 0}"> si es cero. Pero, podemos preguntar por si la lista está vacía:
<div th:if="${users.isEmpty() == 0}">
    
Para negar la anetrior condición sería:
<div th:if="${not users.isEmpty()}">
<div th:if="${!users.isEmpty() == 0}">


Simplificación de:
List<User> users = new ArrayList<>();
users.add(new User("Marco", "Ortega", "ing.marcomaop@gmail.com"));
users.add(new User("Andrés", "Guzmán", "correo@gmail.com"));
users.add(new User("Luciana", "Ortega", "luciana@gmail.com"));

Lo anterior, en vez de usar un ArrayList se puede usar:
List<User> users = new Array.asList de la siguiente manera:

List<User> users = Arrays.asList(
                new User("Marco", "Ortega", "ing.marcomaop@gmail.com"),
                new User("Andrés", "Guzmán", "correo@gmail.com"),
                new User("Luciana", "Ortega", "luciana@gmail.com")
                );
             

Para pasar datos a la vista por métodos en el controlador:
Asi "users" es un atributo común a todos lo métodos del controlador.

@ModelAttribute("users")
    public List<User> fillUser(){
        List<User> users = Arrays.asList(
                new User("Marco", "Ortega", "ing.marcomaop@gmail.com"),
                new User("Andrés", "Guzmán", "correo@gmail.com"),
                new User("Luciana", "Ortega", "luciana@gmail.com"),
                new User("Miguel Angel", "Ortega", "miguelito@gmail.com")
                );
        
        return users;
        
    }
 
 Envio de parámetros por URL:
 http://localhost:8090/parameters/string?text=hey


En thymeleaf las rutas se indican con @{/path} y los argumentos van con comilla simple.
<li><a th:href="@{/parameters/string(text='Que tal Marco')}">/parameters/string?text=Que
                tal Marco</a></li>
             
Lo anterior es para enviar el texto 'Que tal Marco' a la url /parameters/string

Al ingresar un path variable se debe hacer de la siguiente manera:
@GetMapping("/string/{text}")
    public String variables(@PathVariable String text) {
        
    }
Lo que va en llaves debe ser igual al argumento del @PathVariable. (text - text)

La forma de pasar valores al controlador por medio del application properties es:
text.indexcontroller.index.tolist=Hello Spring Framework
Lo anterior tiene una nomenclatura: texto, clase, archivo (template) y método.
Y en el controlador lo llamamos:
@Value("${text.indexcontroller.index.title}")
private String textIndex;
Y luego, se utiliza en el método correspondiente así:
model.addAttribute("titulo", textIndex);
Lo anterior sería inyección de dependencias aplicado a texto.

Lo anterior se puede hacer creando un archivo por ejemplo, texts.properties y se le ponen las variables a leer:
text.indexcontroller.index.tolist=Hello Spring Framework
.
.
Y también en el paquete donde está la app se crea una clase por ejemplo:
@Configuration
@PropertySources({
    @PropertySource("classpath:texts.properties")
    
})
public class TextsPropertiesConfiguration {

}
Y así tiene el mismo efecto de inyectar variables.

Para redirigir a rutas de la aplicación. Se crea otro controlador:
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        
        return "redirect:/app/index";
    }

}

Otra forma es con forwad:
return "redirect:/app/index";

La diferencia entre redirect y forwad es que la primera cambia la ruta en el navegador y la segunda la mantiene.
forwad es para rutas de controladores y se recomienda para implementar una pagina de inicio.








