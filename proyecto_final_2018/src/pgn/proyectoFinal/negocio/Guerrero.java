package pgn.proyectoFinal.negocio;

import pgn.utiles.PasaHtml;

/**
 * Clase hija de personaje que regula el comportamiento y estadisticas
 * específicas de los guerreros.
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class Guerrero extends Personaje {

	/**
	 * Vida máxima que posee de forma básica.
	 */
	public static final double VIDAMAXIMA = 300;
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
	public static final double FUERZA = 30;
	/**
	 * Cantidad de consumible que puedes tener
	 */
	public static final double IRAMAXIMA = 100;

	/**
	 * Coste de ira que tiene el ataque básico
	 */
	public static final double COSTO_ATA_BASICO = 0;
	/**
	 * Coste de ira que tiene el ataque secundario
	 */
	public static final double COSTO_ATA_SECUNDARIO = 20;
	/**
	 * Coste de ira que tiene el ataque final
	 */
	public static final double COSTO_ATA_FINAL = 50;
	/**
	 * Coste de ira que tiene el ataque restauratorio
	 */
	public static final double COSTO_ATA_RESTAURATORIO = 50;

	/**
	 * Poder destructivo del guerrero
	 */
	private double fuerza;
	/**
	 * Cantidad de consumible que posees en un determinado momento del combate.
	 */
	private double ira;
	/**
	 * Cantidad máxima de consumible que puedes tener.
	 */
	private double iraMaxima;

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
	public Guerrero(String nombre, Raza raza) throws RazaNoValidaException, PatronNoValidoException {
		super(nombre, raza, VIDAMAXIMA, VELOCIDAD, GOLPCRITICO, DEF);
		setFuerza(FUERZA);
		setIraMaxima(IRAMAXIMA);
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

	public double getIra() {
		return ira;
	}

	public void setIra(double ira) {
		if (ira < 0)
			ira = 0;
		this.ira = ira;
	}

	public double getIraMaxima() {
		return iraMaxima;
	}

	public void setIraMaxima(double iraMaxima) {
		this.iraMaxima = iraMaxima;
	}

	public String toString() {
		return super.toString() + "\n -Fuerza: " + getFuerza() + "\n -Ira Máxima: " + getIraMaxima() + "\n -Ira: "
				+ getIra() + toStringCostoAtaques();
	}

	@Override
	public void compruebaRaza(Raza raza) throws RazaNoValidaException {
		if (raza == Raza.NO_MUERTO || raza == Raza.GNOMO || raza == null)
			throw new RazaNoValidaException("Esta raza no es valida para esta clase");
	}

	@Override
	public void inicioCombate() {
		setIra(0.0);
		setVida(getVidaMaxima());
	}

	@Override
	public double ataqueBasico() throws ConsumibleInsuficienteException {
		super.ataqueBasico();
		setIra(getIra() - getCosto_ata_basico());
		setIra(getIra() + 20);
		comprobarEstadisticasMaximas();
		return getFuerza();
	}

	@Override
	public String descripcionAtaqueBasico() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_BASICO + " ira",
				"DESCRIPCION: hace un poco de daño y te enfurece");
	}

	@Override
	public double ataqueSecundario() throws ConsumibleInsuficienteException {
		super.ataqueSecundario();
		setIra(getIra() - getCosto_ata_secundario());
		return getFuerza() * 1.5;
	}

	@Override
	public String descripcionAtaquesecundario() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_SECUNDARIO + " ira",
				"DESCRIPCION: hace bastante de daño");
	}

	@Override
	public double ataqueFinal() throws ConsumibleInsuficienteException {
		super.ataqueFinal();
		setIra(getIra() - getCosto_ata_final());
		return getFuerza() * 4;
	}

	@Override
	public String descripcionAtaqueFinal() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_FINAL + " ira", "DESCRIPCION: hace mucho daño");
	}

	@Override
	public void ataqueRestauratorio() throws ConsumibleInsuficienteException {
		super.ataqueRestauratorio();
		setIra(getIra() - getCosto_ata_restauratorio());
		setVida(getVidaMaxima());
	}

	@Override
	public String descripcionAtaqueRestauratorio() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_RESTAURATORIO + " ira",
				"DESCRIPCION: Te cura toda la vida.");
	}

	@Override
	public String descripcionRestauracion() {
		return " se ha curado entero.";
	}

	@Override
	public void cambioTurno() {
		setIra(getIra() - getIraMaxima() * 0.05);

		setVida(getVida() + getVidaMaxima() * 0.01);
		comprobarEstadisticasMaximas();
	}

	public void comprobarConsumibleMinimo(double consumible) throws ConsumibleInsuficienteException {
		if ((getIra() - consumible) < 0)
			throw new ConsumibleInsuficienteException("No hay suficiente ira");
	}

	@Override
	protected void comprobarEstadisticasMaximas() {
		super.comprobarEstadisticasMaximas();
		if (getIra() > getIraMaxima())
			setIra(getIraMaxima());

	}

	@Override
	public void subirLvl() {
		super.subirLvl();
		setFuerza(getFuerza() + getFuerza() * 0.1);
		setIraMaxima(getIraMaxima() + getIraMaxima() * 0.1);
	}

}
