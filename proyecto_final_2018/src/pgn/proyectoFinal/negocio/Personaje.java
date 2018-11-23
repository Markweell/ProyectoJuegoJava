package pgn.proyectoFinal.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Clase padre de todos los personajes. En ella se regula el comportamiento común de los personajes 
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public abstract class Personaje implements Leveable, Atacante, Cansable, Serializable {
	/**
	 * Identificador único
	 */
	private String id;
	/**
	 * Nombre del personaje.
	 */
	private String nombre;
	/**
	 * Patrón del nombre.
	 */
	private static final String PATRON_NOMBRE = "[(A-Z)]{1}[(a-z)]{2,9}";
	/**
	 * Bando por el que luchas.
	 */
	private Faccion faccion;
	/**
	 * Raza a la que pertenece el personaje.
	 */
	private Raza raza;
	/**
	 * Vida variable durante el combate que posee el personaje.
	 */
	private double vida;
	/**
	 * Vida maxima que puede tener el personaje.
	 */
	private double vidaMaxima;
	/**
	 * Cantidad que refleja la rapidez a la que se lanza el ataque.
	 */
	private int velocidad;
	/**
	 * Cantidad que refleja la capacidad de resistir un golpe del personaje.
	 */
	private int defensa;
	/**
	 * Probabilidad que tiene el personaje de que su daño sea crítico.
	 */
	private int probGolpeCritico;
	/**
	 * Nivel del personaje.
	 */
	private int lvl = 1;
	/**
	 * Experiencia que tiene el personaje actualmente.
	 */
	private int exp = 0;
	/**
	 * Experiencia máxima que debe de obtener para subir al siguiente nivel.
	 */
	private int expMax = 2;
	/**
	 * Cantidad que sube la experiencia máxima cada vez que se sube de nivel.
	 */
	private static final int SUBIDA_EXP_PORLVL = 2;
	/**
	 * Guarda informacion sobre si el personaje esta muerto o no.
	 */
	private boolean isDead;
	/**
	 * Patrones validos para la matricula
	 */
	private static Pattern patronNombrePersonaje = Pattern.compile(PATRON_NOMBRE);

	/**
	 * Cantidad que refleja el coste para lanzar el ataque básico.
	 */
	private double costo_ata_basico;
	/**
	 * Cantidad que refleja el coste para lanzar el ataque secundario.
	 */
	private double costo_ata_secundario;
	/**
	 * Cantidad que refleja el coste para lanzar el ataque final.
	 */
	private double costo_ata_final;
	/**
	 * Cantidad que refleja el coste para lanzar el ataque restauratorio.
	 */
	private double costo_ata_restauratorio;

	/**
	 * Constructor de la clase Personaje.
	 * 
	 * @param nombre
	 * @param raza
	 * @param vidaMaxima
	 * @param velocidad
	 * @param probGolpeCritico
	 * @param defensa
	 * @throws RazaNoValidaException,
	 *             que se lanzará cuando la raza no sea compatible con la clase.
	 * @throws NombreNoValidoException,
	 *             que se lanzará cuando el nombre no cumpla con el patrón
	 *             especificado.
	 */
	Personaje(String nombre, Raza raza, double vidaMaxima, int velocidad, int probGolpeCritico, int defensa)
			throws RazaNoValidaException, PatronNoValidoException {
		setNombre(nombre);
		setRaza(raza);
		setFaccion();
		setVida(vidaMaxima);
		setVidaMaxima(vidaMaxima);
		setVelocidad(velocidad);
		setProbGolpeCritico(probGolpeCritico);
		setDefensa(defensa);
		setId();

		estadisticasRaza(raza);
	}

	/**
	 * Comprueba que razas son válidas para dicha clase.
	 * 
	 * @param raza
	 * @throws RazaNoValidaException,
	 *             que se lanzará cuando la raza no sea compatible con la clase.
	 */
	public abstract void compruebaRaza(Raza raza) throws RazaNoValidaException;

	/**
	 * Cambia el campo id, que se usa para identificar de forma única a cada
	 * personaje.
	 */
	private void setId() {
		this.id = generaID();
	}

	/**
	 * Genera un id de forma única.
	 * 
	 * @return
	 */
	public static String generaID() {
		return java.util.UUID.randomUUID().toString();
	}

	/**
	 * Devuelve el campo id, que se usa para identificar de forma única a cada
	 * personaje.
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Cambia el campo nombre, estandarizandolo y comporbando que el nombre cumple
	 * con un patron.
	 * 
	 * @param nombre
	 * @throws PatronNoValidoException,salta
	 *             cuando el nombre no cumpla con el patron.
	 */
	protected void setNombre(String nombre) throws PatronNoValidoException {
		nombre = estandarizarNombre(nombre);
		comprobarNombre(nombre);
		this.nombre = estandarizarNombre(nombre);
	}

	/**
	 * Recibe un nombre para estandarizarlo, quitando los espacios al principio y
	 * final y poniendo la primera letra en mayuscula y las demas en minusculas
	 * 
	 * @param nombre
	 * @return nombre estandarizado.
	 */
	private String estandarizarNombre(String nombre) {
		nombre = nombre.trim();
		try {
			return nombre.toUpperCase().charAt(0) + "" + nombre.subSequence(1, nombre.length());
		} catch (StringIndexOutOfBoundsException e) {
			return "";
		}
	}

	/**
	 * Comprueba que el nombre cumpla con un patron asignado, sino saltará una
	 * excepcion.
	 * 
	 * @param nombre
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre no cumpla con el patron.
	 */
	private void comprobarNombre(String nombre) throws PatronNoValidoException {
		if (!patronNombrePersonaje.matcher(nombre).matches())
			throw new PatronNoValidoException(
					"El nombre no cumple el patron especificado." + " Debe contener al menos 3 letras y máximo 10");
	}

	/**
	 * Devuelve el nombre del personaje.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el campo raza donde se almacena la especie a la que el personaje
	 * pertenece
	 * 
	 * @param raza
	 * @throws RazaNoValidaException,
	 *             que se lanzará cuando la raza no sea compatible con la clase.
	 */
	public void setRaza(Raza raza) throws RazaNoValidaException {
		compruebaRaza(raza);
		this.raza = raza;
	}

	/**
	 * Cambia el campo raza donde se almacena la especie a la que el personaje
	 * pertenece
	 * 
	 * @return raza
	 */
	public Raza getRaza() {
		return raza;
	}

	/**
	 * Determina el campo faccion, donde se almacena el bando por el que lucha el
	 * personaje, dependiendo de la raza a la que este pertenece.
	 * 
	 * @return faccion
	 */
	private void setFaccion() throws RazaNoValidaException {
		if (raza == null) {
			throw new RazaNoValidaException("Esta raza no es válida");
		}
		this.faccion = raza.getFaccion();
	}

	/**
	 * Devuelve el campo faccion donde se almacena el bando por el que lucha el
	 * personaje
	 * 
	 * @return faccion
	 */
	public Faccion getFaccion() {
		return faccion;
	}

	/**
	 * Cambia el campo vida, donde se almacena los puntos de vida que varian a lo
	 * largo de un combate, comprobando que la vida sea mayor que cero y menor que
	 * la vida máxima
	 * 
	 * @param vida
	 */
	void setVida(double vida) {
		if (vida < 0) {
			setDead(true);
			vida = 0;
		}
		if (vida > getVidaMaxima())
			vida = getVidaMaxima();
		this.vida = vida;
	}

	/**
	 * Devuelve el campo vida, donde se almacena los puntos de vida que varian a lo
	 * largo de un combate.
	 * 
	 * @return vida
	 */
	public double getVida() {
		return vida;
	}

	/**
	 * Cambia el campo vidaMaxima, donde se almacenan los puntos de vida máximos que
	 * tiene el personaje
	 * 
	 * @param vidaMaxima
	 */
	void setVidaMaxima(double vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	/**
	 * Devuelve el campo vidaMaxima, donde se almacenan los puntos de vida máximos
	 * que tiene el personaje
	 * 
	 * @return vidaMaxima
	 */
	public double getVidaMaxima() {
		return vidaMaxima;
	}

	/**
	 * Cambia el campo velocidad, donde se almacena una cantidad que refleja lo
	 * rapido que es un personaje.
	 * 
	 * @param velocidad
	 */
	void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	/**
	 * Devuelve el campo velocidad, donde se almacena una cantidad que refleja lo
	 * rapido que es un personaje.
	 * 
	 * @return velocidad.
	 */
	public int getVelocidad() {
		return velocidad;
	}

	/**
	 * Cambia el campo defensa,donde se almacena una cantidad que refleja lo
	 * resistente que es un personaje.
	 * 
	 * @param defensa
	 */
	void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	/**
	 * Devuelve el campo defensa, donde se almacena una cantidad que refleja lo
	 * resistente que es un personaje.
	 * 
	 * @return defensa
	 */
	public int getDefensa() {
		return defensa;
	}

	/**
	 * Cambia el campo probGolpeCritico donde se almacena la probabilidad de que el
	 * ataque haga daño crítico.
	 * 
	 * @param probGolpeCritico
	 */
	protected void setProbGolpeCritico(int probGolpeCritico) {
		if (probGolpeCritico > 100)
			probGolpeCritico = 100;
		this.probGolpeCritico = probGolpeCritico;
	}

	/**
	 * Devuelve el campo probGolpeCritico donde se almacena la probabilidad de que
	 * el ataque haga daño crítico.
	 * 
	 * @return
	 */
	public int getProbGolpeCritico() {
		return probGolpeCritico;
	}

	/**
	 * Sube un nivel el personaje, aumentando las estadisticas oportunas.
	 */
	public void lvlUp() {
		this.lvl++;
		setExpMax(getExpMax() + SUBIDA_EXP_PORLVL);
		subirLvl();
	}

	/**
	 * Devuelve el campo lvl en el que se almacena el nivel actual del personaje
	 * 
	 * @return lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * Cambia el campo exp en que se almacena la experiencia actual del personaje.
	 * Al modificar la experiencia modificamos la partida.
	 * 
	 * @param exp
	 */
	void setExp(int exp) {
		Partida.setModificado(true);
		this.exp = exp;
	}

	/**
	 * Devuelve el campo exp en que se almacena la experiencia actual del personaje
	 * 
	 * @return exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Cambia el campo expMax en el que se almacena la experiencia necesaria para
	 * subir al siguiente nivel.
	 * 
	 * @param expMax
	 */
	private void setExpMax(int expMax) {
		this.expMax = expMax;
	}

	/**
	 * Devuelve el campo expMax en el que se almacena la experiencia necesaria para
	 * subir al siguiente nivel.
	 * 
	 * @return expMax.
	 */
	public int getExpMax() {
		return expMax;
	}

	/**
	 * Devuelve el estado isDead que almacena información sobre si el personaje está
	 * vivo o muerto
	 * 
	 * @return true en caso de que este muerto, false en caso contrario.
	 */
	public Boolean isDead() {
		return isDead;
	}

	/**
	 * Cambia el estado isDead que almacena información sobre si el personaje esta
	 * vivo o muerto
	 * 
	 * @param isDead
	 */
	void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	/**
	 * Devuelve el costo del ataque básico
	 * 
	 * @return Costo ataque básico
	 */
	public double getCosto_ata_basico() {
		return costo_ata_basico;
	}

	/**
	 * Cambia el costo del ataque básico
	 * 
	 * @param costo_ata_basico
	 */
	void setCosto_ata_basico(double costo_ata_basico) {
		this.costo_ata_basico = costo_ata_basico;
	}

	/**
	 * Devuelve el costo del ataque secundario
	 * 
	 * @return costo ataque secundario
	 */
	public double getCosto_ata_secundario() {
		return costo_ata_secundario;
	}

	/**
	 * Cambia el costo del ataque secundario
	 * 
	 * @param costo_ata_secundario
	 */
	void setCosto_ata_secundario(double costo_ata_secundario) {
		this.costo_ata_secundario = costo_ata_secundario;
	}

	/**
	 * Devuelve el costo del ataque final
	 * 
	 * @return costo_ata_final;
	 */
	public double getCosto_ata_final() {
		return costo_ata_final;
	}

	/**
	 * Cambia el costo del ataque final
	 * 
	 * @param costo_ata_final;
	 */
	void setCosto_ata_final(double costo_ata_final) {
		this.costo_ata_final = costo_ata_final;
	}

	/**
	 * Devuelve el costo del ataque restauratorio
	 * 
	 * @return costo_ata_restauratorio;
	 */
	public double getCosto_ata_restauratorio() {
		return costo_ata_restauratorio;
	}

	/**
	 * Cambia el costo del ataque restauratorio
	 * 
	 * @param costo_ata_restauratorio
	 */
	void setCosto_ata_restauratorio(double costo_ata_restauratorio) {
		this.costo_ata_restauratorio = costo_ata_restauratorio;
	}

	/**
	 * Muestra los campos del personaje.
	 * 
	 * @return cadena con los campos del personaje.
	 */
	public String toString() {
		return "El " + getClass().getSimpleName() + ": " + getNombre() + ", posee las estadíticas: \n -Id: " + getId()
				+ "\n -Raza: " + getRaza() + "\n -Faccion: " + getFaccion() + "\n -Vida Máxima: " + getVidaMaxima()
				+ "\n -Vida: " + getVida() + "\n -Velocidad: " + getVelocidad() + "\n -Defensa: " + getDefensa()
				+ "\n -Prob Golpe Crítico: " + getProbGolpeCritico() + "\n -Lvl: " + getLvl()
				+ "\n -Experiencia máxima: " + getExpMax() + "\n -Experiencia: " + getExp();
	}

	/**
	 * Muestra los costos de los ataques del personaje.
	 * 
	 * @return cadena con los valores de cada uno de los ataques.
	 */
	public String toStringCostoAtaques() {
		return "\n    Y SUS ATAQUES CUESTAN:" + "\n -Ataque básico: " + getCosto_ata_basico()
				+ "\n -Ataque Secundario: " + getCosto_ata_secundario() + "\n -Ataque Final: " + getCosto_ata_final()
				+ "\n -Ataque Restauratorio: " + getCosto_ata_restauratorio();
	}

	/**
	 * Añade experiencia al personaje, subiendo de nivel en caso de que reuna la
	 * experiencia suficiente.
	 * 
	 * @param PuntosExperiencia
	 */
	public void ganarExperiencia(int PuntosExperiencia) {
		setExp(getExp() + PuntosExperiencia);
		do {
			if (getExpMax() > getExp())
				break;
			lvlUp();
			setExp(0);
		} while (true);
	}

	/**
	 * Modifica la vida del personaje causandole una cantidad (double) de daño y
	 * comprobando que la vida del personaje no sea menor a cero.
	 * 
	 * @param damage
	 * @return true en caso de que el personaje muera.
	 */
	public boolean doDamage(double damage) {
		setVida(getVida() - damage);
		if (getVida() <= 0) {
			setDead(true);
			setVida(0);
			return true;
		}
		return false;
	}


	@Override
	public double ataqueBasico() throws ConsumibleInsuficienteException {
		comprobarConsumibleMinimo(getCosto_ata_basico());
		return 0;
	}


	@Override
	public double ataqueSecundario() throws ConsumibleInsuficienteException {
		comprobarConsumibleMinimo(getCosto_ata_secundario());
		return 0;
	}


	@Override
	public double ataqueFinal() throws ConsumibleInsuficienteException {
		comprobarConsumibleMinimo(getCosto_ata_final());
		return 0;
	}


	@Override
	public void ataqueRestauratorio() throws ConsumibleInsuficienteException {
		comprobarConsumibleMinimo(getCosto_ata_restauratorio());
	}

	/**
	 * Comprueba que ciertas estadisticas no exceden el límite
	 */
	protected void comprobarEstadisticasMaximas() {
		if (getVida() > getVidaMaxima())
			setVida(getVidaMaxima());
	}

	/**
	 * Estadisticas generales que se modifican cuando el personaje sube de nivel.
	 */
	@Override
	public void subirLvl() {
		setVidaMaxima(getVidaMaxima() + getVidaMaxima() * 0.01);
		estadisticasRaza(getRaza());
	}

	
	@SuppressWarnings("finally")
	public ArrayList<Ataque> ataquesPosibles() {
		ArrayList<Ataque> ataquesDisponibles = new ArrayList<Ataque>();
		ArrayList<Double> costo_ataques = new ArrayList<Double>();

		costo_ataques.add(getCosto_ata_restauratorio());
		costo_ataques.add(getCosto_ata_basico());
		costo_ataques.add(getCosto_ata_secundario());
		costo_ataques.add(getCosto_ata_final());

		Collections.sort(costo_ataques);
		try {
			for (int i = 0; i < costo_ataques.size(); i++) {
				if (costo_ataques.get(i) == getCosto_ata_basico()) {
					if (!ataquesDisponibles.contains(Ataque.ATAQUE_BASICO)) {
						comprobarConsumibleMinimo(getCosto_ata_basico());
						ataquesDisponibles.add(Ataque.ATAQUE_BASICO);
					}
				}
				if (costo_ataques.get(i) == getCosto_ata_secundario()) {
					if (!ataquesDisponibles.contains(Ataque.ATAQUE_SECUNDARIO)) {
						comprobarConsumibleMinimo(getCosto_ata_secundario());
						ataquesDisponibles.add(Ataque.ATAQUE_SECUNDARIO);
					}
				}
				if (costo_ataques.get(i) == getCosto_ata_final()) {
					if (!ataquesDisponibles.contains(Ataque.ATAQUE_FINAL)) {
						comprobarConsumibleMinimo(getCosto_ata_final());
						ataquesDisponibles.add(Ataque.ATAQUE_FINAL);
					}
				}
				if (costo_ataques.get(i) == getCosto_ata_restauratorio()) {
					if (!ataquesDisponibles.contains(Ataque.ATAQUE_RESTAURATORIO)) {
						comprobarConsumibleMinimo(getCosto_ata_restauratorio());
						ataquesDisponibles.add(Ataque.ATAQUE_RESTAURATORIO);
					}
				}
			}
		} finally {
			return ataquesDisponibles;
		}
	}

	/**
	 * Aumenta estadisticas en funcion de la raza
	 * 
	 * @param raza
	 */
	void estadisticasRaza(Raza raza) {
		switch (raza) {
		case ORCO:
		case DRAENEI:
			setDefensa(getDefensa() + 2);
			break;
		case ELFO_SANGRE:
		case ELFO_NOCHE:
			setProbGolpeCritico(getProbGolpeCritico() + 1);
			break;
		case TROLL:
		case GNOMO:
			setVelocidad(getVelocidad() + 5);
			break;
		case NO_MUERTO:
		case HUMANO:
			setVidaMaxima(getVidaMaxima() + 10);
			break;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		Personaje other = (Personaje) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
