# marvelbook
API Marver developer

![icon](https://github.com/vladymix/marvelbook/blob/main/app_desing.png)

# Importacion de librerias
   // To load images
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'

    // To caller services
    implementation 'com.squareup.retrofit2:retrofit:2.6.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.0'

    // To animations
    implementation "com.airbnb.android:lottie:3.5.0"

     //Dagger2
    implementation "com.google.dagger:dagger:${daggerVersion}"
    kapt "com.google.dagger:dagger-compiler:${daggerVersion}"


# Utilización de dagger

La principal ventaja de dagger es que puedes escribir codigo obligadote a seguir los principios SOLID. el primero y fundamental es de la "Single Responsability".
Las aplicaciones se vuelven de esta forma mas escalables, e independientes de implementaciones ya que trabajas mas con abstracciones. la utilizacion es sencilla
y de esta forma consiguies modulos mas testeables.

La ventaja de separar la aplicacion en diferentes modulos es que se consigue un codigo mas legible. y una desventaja es que esto nos lleva a tener una gran cantidad de clases en las que delegamos resposabilidades concretas. pero por otro lado nos ayuda a cumplir con el principio de "Denendency inversion" que permite que nuestro codigo dependa de abstracciones. 


# Control de excepciones
Como el servicio simpre devuelve un 200 para las peticiónes, y dentro del cuerpo envia la información de error, he generado excepciones para que los controle la capa de respositorioAPI (Principios solid -> Responsabilidad unica).
Como la vista solo tiene que mostrar el mensaje el metodo que controla esto es el caso de uso, enviando solo el texto

## MarvelRepositoryAPI
 if (response.code() != 200) {
           return when (code) {
            401 -> MarvelApiException(401,"Invalid Referer | Invalid Hash","Occurs when a referrer which is not valid for the passed apikey parameter is sent. or Occurs when a ts, hash and apikey parameter are sent but the hash is not valid per the above hash generation rule.")
            403 -> MarvelApiException(403,"Forbidden","Occurs when a user with an otherwise authenticated request attempts to access an endpoint to which they do not have access.")
            405 -> MarvelApiException(405,"Method Not Allowed","Occurs when an API endpoint is accessed using an HTTP verb which is not allowed for that endpoint.")
            409 -> MarvelApiException(409,"Missing API Key | Missing Hash | Missing Timestamp","Occurs when the apikey parameter is not included with a request.")
            404 -> MarvelApiException(404,"Character not found.", "Character not found.")
            else -> Exception("Error when caller api")
        }
 }

 ## MarvelApplication

 Aqui se genera el primer lanzamiento de la aplicacion, ya que para que pueda existir una actividad tiene que generar primero la clase Application. 
 En la cual generamos el arbol de dagger inyectando las dependencias para que puedan ser utilizadas posteriormente. 


 # Lottie animacion
 Yo personalmente he aprendido a utilizar esta libreria ya que considero que tiene un gran grupo de desarrollo por detraz, a demás como controlo programas de edicion como adobe illustrator tengo la posibilidad de crear de una forma rapida mis propias animaciones. (esto me ha sido de gran ayuda tanto para maquetaciones como para generacion de iconos.)

 
 # Testing

 Como testing he creado 4 que considero los mas relevantes.

```groovy
 testingHashId() // Verificar si el hashid es el correcto ya que sin esto el servicio nos daria un fallo de autenticación y el proposito de la aplicación no se cumpliria

 testCreateUrl() // Como tenemos que darle formato a las url concatenando este metodo me verifca que se concatene correctamente

 testPreloadFile() // Sirve para cargar un la respuesta para interceptar el servicio rest

 testLoadCharacters() // Verifica que recoge la respuesta y sea el codigo correcto

 testLoadCharactersBody() // Verifica que el cuerpo de la respuesta sea la correcta.
  ``` 



