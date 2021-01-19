# marvelbook
API Marver developer

![icon](https://github.com/vladymix/marvelbook/blob/master/app_desing.png)



# Importacion de librerias
   // To load images
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'

    // To caller services
    implementation 'com.squareup.retrofit2:retrofit:2.6.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.0'

    // To animations
    implementation "com.airbnb.android:lottie:3.5.0"


# Asignación de memoria

En clase MarvelService el atributos service lo declaro como by lazy para reducir el tamaño de asignación de memoria.
  companion object {
        val instance:IMarvelService by lazy {
            MarvelService()
        }
    }

Otra alternativa seria implementar el habitual singelton

companion object {

private var mInstance:IMarverService?=null;
    fun instance():IMarverService{
        if(mInstance==null){
            mInstance =  MarvelService()
        }
        return mInstance!!
    }
}


 Solo cuando voy a utilizar la variable service se inicializa caso contrario Android no reserva memoria para esa variable.

Esto es muy util cuando tenemos por ejemplo varios servicios, ejemplo tenemos 10 servicios pero el usuario actual solo usa funcionalidades de 2 servicios
los otros 8 servicios no se inicializarian y por tanto tampoco consume memoria.

# Control de excepciones
Como el servicio siempre devuelve un 200 para las peticiónes, y dentro del cuerpo envia la información de error, he generado excepciones para que los controle la capa de servicio (Principios solid -> Responsabilidad unica).
Como la vista solo tiene que mostrar el mensaje el metodo de la vista es view.errorOperation(idMessage)

## MarvelService
 if (response.code() != 200) {
            val error = when (response.code()) {
                401 -> Exception401()
                403 -> Exception403()
                405 -> Exception405()
                409 -> Exception409()
                else -> Exception("Error when caller api")
            }
            presenter.invalidOperation(error)
 }

 ## CharactersActivityView

 override fun errorOperation(stringRes: Int) {
       Toast.makeText(this, stringRes,Toast.LENGTH_LONG).show()
 }

 # Lottie animacion
 Yo personalmente he aprendido a utilizar esta libreria, ya que considero que tiene un gran grupo de desarrollo por detraz, a demás como controlo programas de diseño como adobe illustrator tengo la posibilidad de crear de una forma rapida mis propias animaciones. La carga inicial la hice para esta app
 https://lottiefiles.com/share/nsye1rcp

 
 # Testing

 Como testing he creado 4 que considero los mas relevantes.

 testingHashId() // Verificar si el hashid es el correcto ya que sin esto el servicio nos daria un fallo de autenticación y el proposito de la aplicación no se cumpliria

 testCreateUrl() // Como tenemos que darle formato a las url concatenando este metodo me verifca que se concatene correctamente

 testPreloadFile() // Sirve para cargar un la respuesta para interceptar el servicio rest

 testLoadCharacters() // Verifica que recoge la respuesta y sea el codigo correcto

 testLoadCharactersBody() // Verifica que el cuerpo de la respuesta sea la correcta.



