package pgn.proyectoFinal.negocio;

import pgn.utiles.PasaHtml;
/**
 * Clase hija de personaje que regula el comportamiento y estadisticas espec�ficas de los brujos. 
 * 
 * @author Gallardo P�rez Marcos
 *
 */
@SuppressWarnings("serial")
public class Picaro extends Personaje {

	/**
	 * Vida m�xima que posee de forma b�sica.
	 */
	public static final double VIDAMAXIMA = 450;
	/**
	 * Velocidad que posee de forma b�sica
	 */
	public static final int VELOCIDAD = 40;
	/**
	 * Probabilidad de que el da�o sea cr�tico
	 */
	public static final int GOLPCRITICO = 10;
	/**
	 * Defensa que posee de forma b�sica
	 */
	public static final int DEF = 3;

	/**
	 * Poder de ataque del picaro
	 */
	public static final double AGILIDAD = 30;
	/**
	 * Energ�a m�xima que posee el picaro.
	 */
	public static final double ENERGIA_MAXIMA = 100;

	/**
	 * Coste de energ�a que tiene el ataque b�sico
	 */
	public static final double COSTO_ATA_BASICO = 15;
	/**
	 * Coste de energ�a que tiene el ataque secundario
	 */
	public static final double COSTO_ATA_SECUNDARIO = 25;
	/**
	 * Coste de energ�a que tiene el ataque final
	 */
	public static final double COSTO_ATA_FINAL = 50;
	/**
	 * Coste de energ�a que tiene el ataque restauratorio
	 */
	public static final double COSTO_ATA_RESTAURATORIO = 0;
	
	/**
	 * Energ�a que restaura el ataque restauratorio
	 */
	public static final double ENERGIA_RESTAURADA = 50;

	/**
	 * Poder destructivo del picaro
	 */
	private double agilidad;
	/**
	 * Puntos que va a ir obteniendo el picaro con cada ataque seguido
	 */
	private double ptosCombo = 0;
	/**
	 * Consumible que posee el picaro en un determinado momento del combate
	 */
	private double energia;
	/**
	 * Cantidad m�xima de consumible que posee el picaro
	 */
	private double energiaMaxima;
	/**
	 * Constructor.
	 * 
	 * @param nombre
	 * @param raza
	 * @throws RazaNoValidaException,
	 *             salta cuando la raza no es compatible con la clase
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre no cumple con el patr�n.
	 */
	public Picaro(String nombre, Raza raza) throws RazaNoValidaException, PatronNoValidoException {
		super(nombre, raza, VIDAMAXIMA, VELOCIDAD, GOLPCRITICO, DEF);
		setAgilidad(AGILIDAD);
		setEnergiaMaxima(ENERGIA_MAXIMA);
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

	private void setEnergia(double energia) {
		this.energia = energia;
	}

	public double getEnergia() {
		return energia;
	}

	public double getPtosCombo() {
		return ptosCombo;
	}

	public void setPtosCombo(double ptosCombo) {
		this.ptosCombo = ptosCombo;
	}

	private void setEnergiaMaxima(double manaMaximo) {
		this.energiaMaxima = manaMaximo;
	}

	public double getEnergiaMaxima() {
		return energiaMaxima;
	}

	private void setAgilidad(double agilidad) {
		this.agilidad = agilidad;
	}

	public double getAgilidad() {
		return agilidad;
	}

	public String toString() {
		return super.toString() + "\n -Agilidad: " + getAgilidad() + "\n -Agilidad: " + getEnergiaMaxima()
				+ "\n -Agilidad: " + getEnergia() + toStringCostoAtaques();
	}

	/**
	 * Comprueba las razas que puede ser el mago.
	 */
	public void compruebaRaza(Raza raza) throws RazaNoValidaException {
		if (raza == Raza.TROLL || raza == Raza.DRAENEI || raza == null)
			throw new RazaNoValidaException("Esta raza no es valida para esta clase");
	}

	@Override
	public void inicioCombate() {
		setEnergia(getEnergiaMaxima());
		setVida(getVidaMaxima());
		setPtosCombo(0);
	}

	@Override
	public double ataqueBasico() throws ConsumibleInsuficienteException {
		super.ataqueBasico();
		setEnergia(getEnergia() - getCosto_ata_basico());
		setPtosCombo(getPtosCombo() + 1);
		return getAgilidad();
	}

	@Override
	public String descripcionAtaqueBasico() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_BASICO + " energia",
				"DESCRIPCION: hace un poco de da�o y genera un punto de combo");	}

	@Override
	public double ataqueSecundario() throws ConsumibleInsuficienteException {
		super.ataqueSecundario();
		setEnergia(getEnergia() - getCosto_ata_secundario());
		setPtosCombo(getPtosCombo() + 1);
		return getAgilidad() * 1.5;
	}

	@Override
	public String descripcionAtaquesecundario() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_SECUNDARIO + " energia",
				"DESCRIPCION: hace bastante de da�o y genera un punto de combo");	
	}

	@Override
	public double ataqueFinal() throws ConsumibleInsuficienteException {
		super.ataqueFinal();
		setEnergia(getEnergia() - getCosto_ata_final());
		setPtosCombo(getPtosCombo() + 1);
		return getAgilidad() * getPtosCombo();
	}

	@Override
	public String descripcionAtaqueFinal() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_FINAL + " energia",
				"DESCRIPCION: hace da�o en funcion de los puntos de combo que tengas");
	}

	@Override
	public void ataqueRestauratorio() throws ConsumibleInsuficienteException {
		super.ataqueRestauratorio();
		setEnergia(getEnergia() - getCosto_ata_restauratorio());
		setEnergia(getEnergia() + 50);
		setPtosCombo(0);
		comprobarEstadisticasMaximas();
	}

	@Override
	public String descripcionAtaqueRestauratorio() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_RESTAURATORIO + " energia",
				"DESCRIPCION: Restaura bastante energ�a, pierdes los puntos de combo");
	}

	@Override
	public String descripcionRestauracion() {
		return " ha restaurado " + ENERGIA_RESTAURADA + " puntos de puntos de energia.";
	}

	@Override
	public void cambioTurno() {
		setEnergia(getEnergia() + getEnergiaMaxima() * 0.05);
		setVida(getVida() + getVidaMaxima() * 0.01);
		comprobarEstadisticasMaximas();
	}

	public void comprobarConsumibleMinimo(double mana) throws ConsumibleInsuficienteException {
		if ((getEnergia() - mana) < 0)
			throw new ConsumibleInsuficienteException("No hay suficiente energia");
	}

	protected void comprobarEstadisticasMaximas() {
		super.comprobarEstadisticasMaximas();
		if (getEnergia() > getEnergiaMaxima())
			setEnergia(getEnergiaMaxima());
	}

	@Override
	public void subirLvl() {
		super.subirLvl();
		setAgilidad(getAgilidad() + getAgilidad() * 0.1);
		setEnergiaMaxima(getEnergiaMaxima() + getEnergiaMaxima() * 0.1);
	}

}
