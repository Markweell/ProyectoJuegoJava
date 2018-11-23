Guia para WowFighter.

Es necesario la creación de al menos dos jugadores con al menos un
personaje cada uno para poder combatir. 

Combate el personaje principal del jugador seleccionado 1 contra el 
jugador seleccionado 2. 

Empieza atacando el jugador más rápido y a partir del primer ataque, van
a ir alternandose a la hora de atacar los dos jugadores.

Cada personaje tiene una manera de atacar y de una estrategia distinta 
para hacer el máximo daño.

La contraseña de administración para borrar de forma masiva por defecto
es 0000.

Si se ha olvidado la contraseña de algún jugador en particular, debes
entrar en baja jugador, baja listando, y al acceder como administrador
con la contraseña 0000, podras picar en la contraseña para poder verla.
De esta manera solo el administrador podrá conocer tu contraseña.

Para el testeo y pruebas pueden ejecutar lo siguiente al principio
de la ventana inicial:

static{
	try {
		Partida.add("Marcos", "Markweell", "1pP!pppp");
		Partida.getJugador("Markweell").anadirPersonaje(new Mago("Maguillo",Raza.ELFO_NOCHE));
		Partida.getJugador("Markweell").anadirPersonaje(new Guerrero("Warro",Raza.ELFO_NOCHE));
		Partida.getJugador("Markweell").anadirPersonaje(new Picaro("Picaro",Raza.ELFO_NOCHE));
		Partida.add("Marcos", "marcos", "1pP!pppp");
		Partida.getJugador("marcos").anadirPersonaje(new Mago("Mago",Raza.NO_MUERTO));
		Partida.getJugador("marcos").anadirPersonaje(new Guerrero("Guarro",Raza.HUMANO));
		Partida.getJugador("marcos").anadirPersonaje(new CaballeroDeLaMuerte("Zeyth",Raza.ELFO_SANGRE));
		Partida.add("Marcos", "Marquitos", "1pP!pppp");
		Partida.getJugador("Marquitos").anadirPersonaje(new Paladin("Pala",Raza.GNOMO));
		Partida.getJugador("Marquitos").anadirPersonaje(new Brujo("Brujo",Raza.ORCO));
		Partida.getJugador("Marquitos").anadirPersonaje(new CaballeroDeLaMuerte("Zeyth",Raza.NO_MUERTO));
		Partida.add("Marcos", "Marco", "1pP!pppp");
		Partida.add("Marcos", "Markwell", "1pP!pppp");
		Partida.getJugador("Markwell").anadirPersonaje(new Brujo("Brujo",Raza.ELFO_SANGRE));
		Partida.getJugador("Markwell").anadirPersonaje(new Picaro("Picar",Raza.NO_MUERTO));
		Partida.getJugador("Markwell").anadirPersonaje(new Mago("Maguillo",Raza.ELFO_NOCHE));
		Partida.getJugador("Markwell").anadirPersonaje(new Guerrero("Warro",Raza.ELFO_NOCHE));
		Partida.getJugador("Markwell").anadirPersonaje(new Picaro("Picaro",Raza.ELFO_NOCHE));
			
	} catch (JugadorYaExistenteException | PatronNoValidoException | PersonajeYaExistenteException | RazaNoValidaException e) {
		e.printStackTrace();
	}
}
