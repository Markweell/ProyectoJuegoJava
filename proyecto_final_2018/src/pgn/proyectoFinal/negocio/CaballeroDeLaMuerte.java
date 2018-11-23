package pgn.proyectoFinal.negocio;

import pgn.utiles.PasaHtml;
/**
 * Clase hija de personaje que regula el comportamiento y estadisticas específicas de los Caballeros de la Muerte. 
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class CaballeroDeLaMuerte extends Personaje {

	/**
	 * Vida máxima que posee de forma básica.
	 */
	public static final double VIDAMAXIMA = 1000;
	/**
	 * Velocidad que posee de forma básica
	 */
	public static final int VELOCIDAD = 50;
	/**
	 * Probabilidad de que el daño sea crítico
	 */
	public static final int GOLPCRITICO = 7;
	/**
	 * Defensa que posee de forma básica
	 */
	public static final int DEF = 5;

	/**
	 * Poder de ataque.
	 */
	static final double FUERZA = 30;
	/**
	 * Cantidad de consumible que puedes tener.
	 */
	public static final double RUNASMAXIMAS = 5;
	/**
	 * Probabilidad de que con con cada ataque se genere una runa.
	 */
	public static final double PROB_GENERACIONRUNAS = 33.3;
	
	/**
	 * Coste de runas que tiene el ataque básico
	 */
	public static final double COSTO_ATA_BASICO = 0;
	/**
	 * Coste de runas que tiene el ataque secundario
	 */
	public static final double COSTO_ATA_SECUNDARIO = 1;
	/**
	 * Coste de runas que tiene el ataque final
	 */
	public static final double COSTO_ATA_FINAL = 5;
	/**
	 * Coste de runas que tiene el ataque restauratorio
	 */
	public static final double COSTO_ATA_RESTAURATORIO = 1;

	/**
	 * Campo que guarda el poder destructivo del caballero de la muerte.
	 */
	private double fuerza;
	/**
	 * Campo que guarda la runas que posee en un determinado momento el caballero de la muerte
	 */
	private double runas;
	/**
	 * Campo que guarda la cantidad máxima de runas que posee el caballero de la muerte.
	 */
	private double runasMaximas;
	
	/**
	 * Campo que guarda la probabilida de que se genere una runa.
	 */
	private double prob_GeneracionRunas;

	/**
	 * Constructor.
	 * 
	 * @param nombre
	 * @param raza
	 * @throws RazaNoValidaException,
	 *             salta cuando la raza no es compatible con la clase
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre no cumple con el patrón.
	 */
	public CaballeroDeLaMuerte(String nombre, Raza raza) throws RazaNoValidaException, PatronNoValidoException {
		super(nombre, raza, VIDAMAXIMA, VELOCIDAD, GOLPCRITICO, DEF);
		setFuerza(FUERZA);
		setRunasMaximas(RUNASMAXIMAS);
		setProb_GeneracionRunas(PROB_GENERACIONRUNAS);
		asignarCostos();
	}
	
	/**
	 * Asigna los costos a las habilidades
	 */
	private void asignarCostos() {
		setCosto_ata_basico(COSTO_ATA_BASICO);
		setCosto_ata_secundario(COSTO_ATA_SECUNDARIO);
		setCosto_ata_final(COSTO_ATA_FINAL);
		setCosto_ata_restauratorio(COSTO_ATA_RESTAURATORIO);
	}

	public double getFuerza() {
		return fuerza;
	}

	public void setFuerza(double fuerza) {
		this.fuerza = fuerza;
	}

	public double getRunas() {
		return runas;
	}

	public void setRunas(double runas) {
		this.runas = runas;
	}

	public double getRunasMaximas() {
		return runasMaximas;
	}

	public void setRunasMaximas(double runasMaximas) {
		this.runasMaximas = runasMaximas;
	}

	public double getProb_GeneracionRunas() {
		return prob_GeneracionRunas;
	}

	public void setProb_GeneracionRunas(double prob_GeneracionRunas) {
		this.prob_GeneracionRunas = prob_GeneracionRunas;
	}

	public String toString() {
		return super.toString() + "\n -Fuerza: " + getFuerza() + "\n -Runas Máximas: " + getRunasMaximas()
				+ "\n -Runas: " + getRunas() + toStringCostoAtaques();
	}

	@Override
	public void compruebaRaza(Raza raza) throws RazaNoValidaException {
		if (raza == Raza.TROLL || raza == Raza.DRAENEI || raza == null)
			throw new RazaNoValidaException("Esta raza no es valida para esta clase");
	}

	@Override
	public void inicioCombate() {
		setRunas(1);
		setVida(getVidaMaxima());
	}

	@Override
	public double ataqueBasico() throws ConsumibleInsuficienteException {
		super.ataqueBasico();
		setRunas(getRunas() - getCosto_ata_basico());
		generarRunas();
		return getFuerza();
	}
	
	@Override
	public String descripcionAtaqueBasico() {
		return PasaHtml.tresSaltoLineaCentrado("COSTO: " + COSTO_ATA_BASICO + " runas",
				"DESCRIPCION: hace un poco de daño y tiene"," posibilidad de generar una runa");	}

	@Override
	public double ataqueSecundario() throws ConsumibleInsuficienteException {
		super.ataqueSecundario();
		setRunas(getRunas() - getCosto_ata_secundario());
		generarRunas();
		return getFuerza() * 1.5;
	}
	
	@Override
	public String descripcionAtaquesecundario() {
		return PasaHtml.tresSaltoLineaCentrado("COSTO: " + COSTO_ATA_SECUNDARIO + " runas",
				"DESCRIPCION: hace bastante de daño y tiene ","posibilidad de genera una runa");
	}

	@Override
	public double ataqueFinal() throws ConsumibleInsuficienteException {
		super.ataqueFinal();
		setRunas(getRunas() - getCosto_ata_final());
		generarRunas();
		return getFuerza() * 10;
	}
	@Override
	public String descripcionAtaqueFinal() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_FINAL + " runas",
				"DESCRIPCION: es dificil que tu adversario sobreviva a este ataque");
	}

	/**
	 * A cambio de un costo, tienes la prob de ganar 3 runas.
	 */
	@Override
	public void ataqueRestauratorio() throws ConsumibleInsuficienteException {
		super.ataqueRestauratorio();
		setRunas(getRunas() - getCosto_ata_restauratorio());
		generarRunas();
		generarRunas();
		generarRunas();
	}
	@Override
	public String descripcionAtaqueRestauratorio() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_RESTAURATORIO + " runas",
				"DESCRIPCION: Intenta generar un máximo de tres runas.");
	}

	@Override
	public String descripcionRestauracion() {
		return " ha intentado generar 3 runas.";
	}

	@Override
	public void cambioTurno() {
		setVida(getVida() + getVidaMaxima() * 0.01);
		comprobarEstadisticasMaximas();
	}

	private boolean generarRunas() {
		if (Math.random() * 100 <= getProb_GeneracionRunas()) {
			setRunas(getRunas() + 1);
			comprobarEstadisticasMaximas();
			return true;
		}
		return false;
	}

	public void comprobarConsumibleMinimo(double consumible) throws ConsumibleInsuficienteException {
		if ((getRunas() - consumible) < 0)
			throw new ConsumibleInsuficienteException("No hay suficientes runas");
	}

	@Override
	protected void comprobarEstadisticasMaximas() {
		super.comprobarEstadisticasMaximas();
		if (getRunas() > getRunasMaximas())
			setRunas(getRunasMaximas());
	}

	@Override
	public void subirLvl() {
		super.subirLvl();
		setFuerza(getFuerza() * 1.1);
		setProb_GeneracionRunas(getProb_GeneracionRunas() * 1.1);
	}

}
