La aplicación consume datos de la datos de la pagina www.themoviedb.org
Se consumen datos de las categorias Peliculas y Series, de estas categorías se usan obtienen datos como:
	- Título o nombre de la película/serie
	- Fecha de lanzamiento de la película/serie
	- Puntuación de la película/serie
	- Descripción de la película/serie
	- Imagen de la película/serie

Para obtener estos datos se realizó una clase llamada ApiClient, que consume el API de la pagina www.themoviedb.org . en esta clase se tiene el api_key ya definida para después usar la clase ApiClient en la clase InterfaceApi.

Se creó la clase InterfaceApi, de tipo interfaz, en la cual se tienen definidos los métodos con los cuales se obtienen los datos ya sean de las películas o series y los tipos de contenido (películas/series populares, mejor valoradas, y próximas) por medio del método GET. Cuando se llaman los métodos, de está clase, retornan un dato de tipo “Movie” (película) o “TV” (serie).

Para las dos categorías se tienen los dos activities, una llamada MainActivity (activity correspondiente a la categoria Peliculas) y otra activity llamada TvActivity (activity correspondiente a la categoría Series). En estas activities se usan las clases ApiClient y Interface api para obtener los datos del Api de la pagina  www.themoviedb.org . Los datos que se obtienen son de tipo “Movie” o “TV”.

Se tienen otras dos activities llamadas “Information” e “InformationTv” las cuales se encargan de mostrar la información de una pelicula o serie específica.

Se tienen dos clases llamadas “MovieAdapter” y “TvAdapter” encargadas de mostrar el contenido obtenido por los métodos GET de los diferentes categorias (Peliculas/Series) y de los los diferentes tipos de contenido como películas/series populares, mejor valoradas, y próximas.


El tipo “Movie” o “TV” estan definidos en las clases Movie y TV, los atributos de estas clases son “results” el cual es una lista de datos de tipo Result (usado clase Movie) y ResultTV (usado clase TV). 
Los tipos “Result” y “ResultTV” estan definidos en las clases Movie y TV, los atributos de estas clases son distintos datos de tipo Integer, Boolean, String, Double, o listas de enteros.

--------------- CAPAS ---------------

Vista
En la capa de la de vista se tienen las activities MainActivity y TvActivity, las cuales se encargan de mostrar el contenido en una grilla de dos columnas.
Cuando se ocurre un “click” en una de las celdas de la grilla se cambia de activity a Information y InformationTv para mostrar el contenido específico de la serie o película seleccionada. El contenido visualizado en estas activities es mandado por la activity anterior en correspondiente por medio del método putExtra de la clase Intent, esto ocurre en las clases “MovieAdapter” y “TvAdapter” .

Negocio
En la capa de Negocio se tienen las clases “MovieAdapter” y “TvAdapter” las cuales se encargan de organizar los datos. Para luego presentarlos en la paca de vista

Datos
En la capa de datos se tienen las clases ApiClient y Interface api, encargadas de obtener los datos necesarios.
