package pgn.proyectoFinal.negocio;

import pgn.utiles.PasaHtml;
/**
 * Clase hija de personaje que regula el comportamiento y estadisticas específicas de los brujos. 
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class Brujo extends Personaje {

	/**
	 * Vida máxima que posee de forma básica.
	 */
	public static final double VIDAMAXIMA = 500;
	/**
	 * Velocidad que posee de forma básica
	 */
	public static final int VELOCIDAD = 40;
	/**
	 * Probabilidad de que el daño sea crítico
	 */
	public static final int GOLPCRITICO = 10;
	/**
	 * Defensa que posee de forma básica
	 */
	public static final int DEF = 3;

	/**
	 * Poder de ataque.
	 */
	public static final double ENERGIA_VIL = 30;

	/**
	 * Coste de vida que tiene el ataque básico
	 */
	public static final double COSTO_ATA_BASICO = 0;
	/**
	 * Coste de vida que tiene el ataque secundario
	 */
	public static final double COSTO_ATA_SECUNDARIO = 25;
	/**
	 * Coste de vida que tiene el ataque final
	 */
	public static final double COSTO_ATA_FINAL = 50;
	/**
	 * Coste de vida que tiene el ataque restauratorio
	 */
	public static final double COSTO_ATA_RESTAURATORIO = 0;

	/**
	 * Vida que restaura cuando realiza el ataque restauratorio
	 */
	public static final double VIDA_RESTAURADA = 50;

	/**
	 * Campo que guarda el poder destructivo del brujo.
	 */
	private double energia_vil;

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
	public Brujo(String nombre, Raza raza) throws RazaNoValidaException, PatronNoValidoException {
		super(nombre, raza, VIDAMAXIMA, VELOCIDAD, GOLPCRITICO, DEF);
		setEnergiaVil(ENERGIA_VIL);
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

	/**
	 * Cambia el campo energía vil del brujo, donde se guarda el poder de ataque del
	 * brujo
	 * 
	 * @param energiaVil
	 */
	private void setEnergiaVil(double energiaVil) {
		this.energia_vil = energiaVil;
	}

	/**
	 * Devuelve el campo Energia Vil del brujo, donde se guarda el poder de ataque
	 * del brujo
	 * 
	 * @return energiaVil
	 */
	public double getEnergiaVil() {
		return energia_vil;
	}

	public String toString() {
		return super.toString() + "\n -Energia Vil: " + getEnergiaVil() + toStringCostoAtaques();
	}

	public void compruebaRaza(Raza raza) throws RazaNoValidaException {
		if (raza == Raza.TROLL || raza == Raza.HUMANO || raza == null)
			throw new RazaNoValidaException("Esta raza no es valida para esta clase");
	}

	@Override
	public void inicioCombate() {
		setVida(getVidaMaxima());
	}

	@Override
	public double ataqueBasico() throws ConsumibleInsuficienteException {
		super.ataqueBasico();
		setVida(getVida() - getCosto_ata_basico());
		return getEnergiaVil();
	}

	@Override
	public String descripcionAtaqueBasico() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_BASICO + " vida",
				"DESCRIPCION: hace un poco de daño");
	}

	@Override
	public double ataqueSecundario() throws ConsumibleInsuficienteException {
		super.ataqueSecundario();
		setVida(getVida() - getCosto_ata_secundario());
		return getEnergiaVil() * 2.5;
	}

	@Override
	public String descripcionAtaquesecundario() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_SECUNDARIO + " vida",
				"DESCRIPCION: hace bastante de daño");
	}

	@Override
	public double ataqueFinal() throws ConsumibleInsuficienteException {
		super.ataqueFinal();
		setVida(getVida() - getCosto_ata_final());
		return getEnergiaVil() * 4;
	}

	@Override
	public String descripcionAtaqueFinal() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_FINAL + " vida", "DESCRIPCION: hace mucho daño");
	}

	@Override
	public void ataqueRestauratorio() throws ConsumibleInsuficienteException {
		super.ataqueRestauratorio();
		setVida(getVida() - getCosto_ata_restauratorio());
		setVida(getVida() + 50);
		comprobarEstadisticasMaximas();
	}

	@Override
	public String descripcionAtaqueRestauratorio() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_RESTAURATORIO + " vida",
				"DESCRIPCION: te cura bastante vida");
	}

	@Override
	public String descripcionRestauracion() {
		return " se ha curado " + VIDA_RESTAURADA + " vida.";
	}

	@Override
	public void cambioTurno() {
		setVida(getVida() + getVidaMaxima() * 0.01);
		comprobarEstadisticasMaximas();
	}

	public void comprobarConsumibleMinimo(double vida) throws ConsumibleInsuficienteException {
		if ((getVida() - vida) < 0)
			throw new ConsumibleInsuficienteException("No tienes suficiente vida");
	}

	protected void comprobarEstadisticasMaximas() {
		super.comprobarEstadisticasMaximas();
	}

	@Override
	public void subirLvl() {
		super.subirLvl();
		setEnergiaVil(getEnergiaVil() + getEnergiaVil() * 0.1);
		setVidaMaxima(getVidaMaxima() + getVidaMaxima() * 0.05);
	}

}
