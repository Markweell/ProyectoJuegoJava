package pgn.proyectoFinal.negocio;

/**
 * Comportamiento que deben tener los personajes que deseen participar en el
 * combate.
 * 
 * @author Gallardo Pérez Marcos.
 */
interface Atacante {
	/**
	 * Realiza la accion del ataque básico del personaje, devolviendo el daño que
	 * realiza y comprobando que tiene suficiente consumible para realizarlo.
	 * 
	 * @return daño que realiza
	 * @throws ConsumibleInsuficienteException,
	 *             salta cuando no tienes suficiente consumible para realizar el
	 *             ataque.
	 */
	double ataqueBasico() throws ConsumibleInsuficienteException;

	/**
	 * Describe el ataque básico
	 * 
	 * @return cadena con la descripción
	 */
	String descripcionAtaqueBasico();

	/**
	 * Realiza la acción del ataque secundario del personaje, devolviendo el daño
	 * que realiza y comprobando que tiene suficiente consumible para realizarlo.
	 * 
	 * @return daño que realiza
	 * @throws ConsumibleInsuficienteException,
	 *             salta cuando no tienes suficiente consumible para realizar el
	 *             ataque.
	 */
	double ataqueSecundario() throws ConsumibleInsuficienteException;

	/**
	 * Describe el ataque secundario
	 * 
	 * @return cadena con la descripción
	 */
	String descripcionAtaquesecundario();

	/**
	 * Realiza la acción del ataque final del personaje, devolviendo el daño que
	 * realiza y comprobando que tiene suficiente consumible para realizarlo.
	 * 
	 * @return daño que realiza
	 * @throws ConsumibleInsuficienteException,
	 *             salta cuando no tienes suficiente consumible para realizar el
	 *             ataque.
	 */
	double ataqueFinal() throws ConsumibleInsuficienteException;

	/**
	 * Describe el ataque final
	 * 
	 * @return cadena con la descripción
	 */
	String descripcionAtaqueFinal();

	/**
	 * Realiza la accion del ataque restauratorio del personaje, comprobando que
	 * tiene suficiente consumible para realizarlo.
	 * 
	 * @throws ConsumibleInsuficienteException,
	 *             salta cuando no tienes suficiente consumible para realizar el
	 *             ataque.
	 */
	void ataqueRestauratorio() throws ConsumibleInsuficienteException;

	/**
	 * Describe el ataque restauratorio
	 * 
	 * @return cadena con la descripción
	 */
	String descripcionAtaqueRestauratorio();

	/**
	 * Describe la acción de restaurar
	 * 
	 * @return cadena con la descripción
	 */
	String descripcionRestauracion();

	/**
	 * Regula los acontecimientos que ocurren al cambiar el turno
	 */
	void cambioTurno();

	/**
	 * Regula los campos del personaje al principio del combate.
	 */
	void inicioCombate();
}
