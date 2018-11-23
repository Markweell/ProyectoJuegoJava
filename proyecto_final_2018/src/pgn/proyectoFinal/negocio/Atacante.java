package pgn.proyectoFinal.negocio;

/**
 * Comportamiento que deben tener los personajes que deseen participar en el
 * combate.
 * 
 * @author Gallardo P�rez Marcos.
 */
interface Atacante {
	/**
	 * Realiza la accion del ataque b�sico del personaje, devolviendo el da�o que
	 * realiza y comprobando que tiene suficiente consumible para realizarlo.
	 * 
	 * @return da�o que realiza
	 * @throws ConsumibleInsuficienteException,
	 *             salta cuando no tienes suficiente consumible para realizar el
	 *             ataque.
	 */
	double ataqueBasico() throws ConsumibleInsuficienteException;

	/**
	 * Describe el ataque b�sico
	 * 
	 * @return cadena con la descripci�n
	 */
	String descripcionAtaqueBasico();

	/**
	 * Realiza la acci�n del ataque secundario del personaje, devolviendo el da�o
	 * que realiza y comprobando que tiene suficiente consumible para realizarlo.
	 * 
	 * @return da�o que realiza
	 * @throws ConsumibleInsuficienteException,
	 *             salta cuando no tienes suficiente consumible para realizar el
	 *             ataque.
	 */
	double ataqueSecundario() throws ConsumibleInsuficienteException;

	/**
	 * Describe el ataque secundario
	 * 
	 * @return cadena con la descripci�n
	 */
	String descripcionAtaquesecundario();

	/**
	 * Realiza la acci�n del ataque final del personaje, devolviendo el da�o que
	 * realiza y comprobando que tiene suficiente consumible para realizarlo.
	 * 
	 * @return da�o que realiza
	 * @throws ConsumibleInsuficienteException,
	 *             salta cuando no tienes suficiente consumible para realizar el
	 *             ataque.
	 */
	double ataqueFinal() throws ConsumibleInsuficienteException;

	/**
	 * Describe el ataque final
	 * 
	 * @return cadena con la descripci�n
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
	 * @return cadena con la descripci�n
	 */
	String descripcionAtaqueRestauratorio();

	/**
	 * Describe la acci�n de restaurar
	 * 
	 * @return cadena con la descripci�n
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
