package pgn.proyectoFinal.negocio;

/**
 * Clase que gestiona el combate entre dos jugadores.
 * 
 * @author Gallardo Pérez Marcos
 *
 */
public class Combate {
	/**
	 * Personaje más veloz, y por tanto primero en atacar en el combate
	 */
	private Personaje pj1;
	/**
	 * Personaje menos veloz que participa en el combate
	 */
	private Personaje pj2;
	/**
	 * Personaje que esta atacando en un determinado momento
	 */
	private Personaje atacante;
	/**
	 * Personaje defensor en un determinado momento
	 */
	private Personaje defensor;
	/**
	 * Cadena que describe lo que ocurre tras realizar el ataque
	 */
	String descripcionAtaque = "";
	/**
	 * Turno en el que se encuentra el combate
	 */
	int turno = 1;
	/**
	 * Daño que hace el ataque.
	 */
	private double damage;

	/**
	 * Constructor de la clase Combate.
	 * 
	 * @param pj1
	 * @param pj2
	 */
	public Combate(Personaje pj1, Personaje pj2) {
		pj1.inicioCombate();
		pj2.inicioCombate();
		setPersonajes(pj1, pj2);
	}

	/**
	 * Crea los personajes, asignando pj1 al más rápido y pj2 al más lento.
	 * 
	 * @param pj1
	 * @param pj2
	 */
	private void setPersonajes(Personaje pj1, Personaje pj2) {
		setPj1(pj1);
		setPj2(pj2);
		if (getPj1().getVelocidad() < getPj2().getVelocidad()) {
			setPj2(pj1);
			setPj1(pj2);
		}
		setAtacante(getPj1());
		setDefensor(getPj2());
	}

	/**
	 * Devuelve el campo Pj1, donde se guarda al primer personaje(El más rapido de
	 * los dos)
	 * 
	 * @return pj1
	 */
	public Personaje getPj1() {
		return pj1;
	}

	/**
	 * Cambia el campo Pj1, donde se guarda al primer personaje(El más rapido)
	 * 
	 * @return pj1
	 */
	private void setPj1(Personaje pj1) {
		this.pj1 = pj1;
	}

	/**
	 * Devuelve el campo Pj2, donde se guarda al segundo personaje(El más lento de
	 * los dos)
	 * 
	 * @return pj2
	 */
	public Personaje getPj2() {
		return pj2;
	}

	/**
	 * Cambia el campo Pj2, donde se guarda al segundo personaje(El más lento de los
	 * dos)
	 * 
	 * @return pj2
	 */
	private void setPj2(Personaje pj2) {
		this.pj2 = pj2;
	}

	/**
	 * Devuleve el turno actual del combate.
	 * 
	 * @return turnos
	 */
	private int getTurnos() {
		return turno;
	}

	/**
	 * Sube un turno.
	 */
	private void turnosUp() {
		this.turno++;
	}

	/**
	 * Devuelve el personaje que tiene el rol de atacante en el combate actualmente
	 * 
	 * @return atacante
	 */
	public Personaje getAtacante() {
		return atacante;
	}

	/**
	 * Cambia el personaje que tiene el rol de atacante en el combate actualmente
	 * 
	 * @return atacante
	 */
	private void setAtacante(Personaje pj) {
		this.atacante = pj;
	}

	/**
	 * Devuelve el personaje que tiene el rol de defensor en el combate actualmente
	 * 
	 * @return defensor
	 */
	public Personaje getDefensor() {
		return defensor;
	}

	/**
	 * Cambia el personaje que tiene el rol de defensor en el combate actualmente
	 * 
	 * @return defensor
	 */
	private void setDefensor(Personaje pj) {
		this.defensor = pj;
	}

	/**
	 * Interaccion principal de Personajes, en ella un personaje(atacante) ataca con
	 * un ataque, se calcula el daño de ese ataque y se resuelve el daño a un
	 * segundo personaje (defensor). Devuelve true en caso de que este ataque lo
	 * halla matado.
	 * 
	 * @param atacante
	 * @param ataque
	 * @param defensor
	 * @return True en caso de que este ataque mate al defensor, false en caso
	 *         contrario
	 * @throws ConsumibleInsuficienteException,
	 *             se lanzará cuando no halla recursos suficientes tales como mana,
	 *             ira... para lanzar el ataque.
	 */
	public boolean Ataque(Ataque ataque) throws ConsumibleInsuficienteException {
		double danioRealizado = calculateDamage(ataque) - getDefensor().getDefensa();
		if (danioRealizado > 0)
			descripcionAtaque += "<center>" + getAtacante().getNombre() + " ha hecho " + danioRealizado
					+ " puntos de daño.</center>\r\n";
		if (getDefensor().doDamage(danioRealizado)) {
			descripcionAtaque += "<center> " + getDefensor().getNombre()
					+ " HA MUERTO !!</center>\r\n</body>\r\n</html>";
			return true;
		}
		cambioTurno();
		return false;
	}

	/**
	 * Calcula el daño que hace el ataque el personaje atacante.
	 * 
	 * @param ataque
	 * @return Daño del ataque.
	 * @throws ConsumibleInsuficienteException,
	 *             se lanzará cuando no halla recursos suficientes tales como mana,
	 *             ira... para a lanzar el ataque.
	 */
	private double calculateDamage(Ataque ataque) throws ConsumibleInsuficienteException {
		damage = 0;
		descripcionAtaque = "<html>\r\n<body>\r\n";
		switch (ataque) {
		case ATAQUE_BASICO:
			damage = getAtacante().ataqueBasico();
			break;
		case ATAQUE_SECUNDARIO:
			damage = getAtacante().ataqueSecundario();
			break;
		case ATAQUE_FINAL:
			damage = getAtacante().ataqueFinal();
			break;
		case ATAQUE_RESTAURATORIO:
			getAtacante().ataqueRestauratorio();
			descripcionAtaque += "<center>" + getAtacante().getNombre() + getAtacante().descripcionRestauracion()
					+ "</center>\r\n";
			return 0;
		}
		if (Math.random() * 100 < atacante.getProbGolpeCritico()) {
			damage *= 2;
			descripcionAtaque += "<center> ¡CRITICO! </center>\r\n";
		}
		return damage;
	}

	/**
	 * Procesos que ocurren cuando cambia el turno.
	 */
	private void cambioTurno() {
		defensor.cambioTurno();
		descripcionAtaque += "<center>" + getDefensor().getNombre()
				+ " se restaura un poco al pasar el turno.</center>\r\n <center>Es el turno de que ataque "
				+ getDefensor().getNombre() + ".</center>\r\n</body>\r\n</html>";
		turnosUp();
		cambioRol();
	}

	/**
	 * Cambia el rol de atacante y defensor. El personaje1(más rapido) ataca los
	 * turnos impares y el Personaje2(más lento) ataca los turnos pares.
	 */
	private void cambioRol() {
		if (getTurnos() % 2 == 0) {// Par
			setAtacante(pj2);
			setDefensor(pj1);
		} else {
			setAtacante(pj1);
			setDefensor(pj2);
		}
	}

	/**
	 * Devuelve el campo descripcionAtaque, donde se almacena información de que ha
	 * pasado en el ataque
	 * 
	 * @return descripcionAtaque
	 */
	public String getDescripcionAtaque() {
		return descripcionAtaque;
	}

}
