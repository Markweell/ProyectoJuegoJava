package pgn.proyectoFinal.negocio;

import pgn.utiles.PasaHtml;
/**
 * Clase hija de personaje que regula el comportamiento y estadisticas específicas de los magos. 
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class Mago extends Personaje {

	/**
	 * Vida máxima que posee de forma básica.
	 */
	public static final double VIDAMAXIMA = 400;
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
	 * Poder de ataque
	 */
	public static final double INTELECTO = 30;
	/**
	 * Cantidad máxima de consumible que puede tener el mago.
	 */
	public static final double MANAMAXIMO = 100;

	/**
	 * Coste de mana que tiene el ataque básico
	 */
	public static final double COSTO_ATA_BASICO = 10;
	/**
	 * Coste de mana que tiene el ataque secundario
	 */
	public static final double COSTO_ATA_SECUNDARIO = 20;
	/**
	 * Coste de mana que tiene el ataque final
	 */
	public static final double COSTO_ATA_FINAL = 50;
	/**
	 * Coste de mana que tiene el ataque restauratorio
	 */
	public static final double COSTO_ATA_RESTAURATORIO = 0;

	/**
	 * Cantidad de mana que va a restaurar el ataque restauratorio del mago.
	 */
	public static final double MANA_RESTAURADO = 50;

	/**
	 * Poder destructivo del mago
	 */
	private double intelecto;
	/**
	 * Cantidad de consumible que posee el mago en un determinado momento del combate
	 */
	private double mana;
	/**
	 * Cantidad máxima de consumible que puede tener.
	 */
	private double manaMaximo;

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
	public Mago(String nombre, Raza raza) throws RazaNoValidaException, PatronNoValidoException {
		super(nombre, raza, VIDAMAXIMA, VELOCIDAD, GOLPCRITICO, DEF);
		setIntelecto(INTELECTO);
		setManaMaximo(MANAMAXIMO);
		setMana(MANAMAXIMO);
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

	private void setMana(double mana) {
		this.mana = mana;
	}

	public double getMana() {
		return mana;
	}

	private void setManaMaximo(double manaMaximo) {
		this.manaMaximo = manaMaximo;
	}

	public double getManaMaximo() {
		return manaMaximo;
	}

	private void setIntelecto(double intelecto) {
		this.intelecto = intelecto;
	}

	public double getIntelecto() {
		return intelecto;
	}

	public String toString() {
		return super.toString() + "\n -Intelecto: " + getIntelecto() + "\n -Mana Máximo: " + getManaMaximo()
				+ "\n -Mana: " + getMana() + toStringCostoAtaques();
	}

	/**
	 * Comprueba las razas que puede ser el mago.
	 */
	public void compruebaRaza(Raza raza) throws RazaNoValidaException {
		if (raza == Raza.ORCO || raza == Raza.DRAENEI || raza == null)
			throw new RazaNoValidaException("Esta raza no es valida para esta clase");
	}

	@Override
	public void inicioCombate() {
		setMana(getManaMaximo());
		setVida(getVidaMaxima());
	}

	@Override
	public double ataqueBasico() throws ConsumibleInsuficienteException {
		super.ataqueBasico();
		setMana(getMana() - getCosto_ata_basico());
		return getIntelecto();
	}

	@Override
	public String descripcionAtaqueBasico() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_BASICO + " mana",
				"DESCRIPCION: hace un poco de daño");
	}

	@Override
	public double ataqueSecundario() throws ConsumibleInsuficienteException {
		super.ataqueSecundario();
		setMana(getMana() - getCosto_ata_secundario());
		return getIntelecto() * 1.5;
	}

	@Override
	public String descripcionAtaquesecundario() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_SECUNDARIO + " mana",
				"DESCRIPCION: hace bastante de daño");
	}

	@Override
	public double ataqueFinal() throws ConsumibleInsuficienteException {
		super.ataqueFinal();
		setMana(getMana() - getCosto_ata_final());
		return getIntelecto() * 3;
	}

	@Override
	public String descripcionAtaqueFinal() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_FINAL + " mana",
				"DESCRIPCION: hace mucho daño");
	}

	@Override
	public void ataqueRestauratorio() throws ConsumibleInsuficienteException {
		super.ataqueRestauratorio();
		setMana(getMana() - getCosto_ata_restauratorio());
		setMana(getMana() + MANA_RESTAURADO);
		comprobarEstadisticasMaximas();
	}

	@Override
	public String descripcionAtaqueRestauratorio() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_RESTAURATORIO + " mana",
				"DESCRIPCION: Restaura mucho mana.");
	}

	@Override
	public String descripcionRestauracion() {
		return " ha restaurado " + MANA_RESTAURADO + " puntos de mana.";
	}

	@Override
	public void cambioTurno() {
		setMana(getMana() + getManaMaximo() * 0.05);
		setVida(getVida() + getVidaMaxima() * 0.01);
		comprobarEstadisticasMaximas();
	}

	public void comprobarConsumibleMinimo(double mana) throws ConsumibleInsuficienteException {
		if ((getMana() - mana) < 0)
			throw new ConsumibleInsuficienteException("No hay suficiente mana");
	}

	protected void comprobarEstadisticasMaximas() {
		super.comprobarEstadisticasMaximas();
		if (getMana() > getManaMaximo())
			setMana(getManaMaximo());
	}

	@Override
	public void subirLvl() {
		super.subirLvl();
		setIntelecto(getIntelecto() + getIntelecto() * 0.1);
		setManaMaximo(getManaMaximo() + getManaMaximo() * 0.1);

	}

}
