package pgn.proyectoFinal.negocio;

import pgn.utiles.PasaHtml;
/**
 * Clase hija de personaje que regula el comportamiento y estadisticas específicas de los paladines. 
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class Paladin extends Personaje {
	
	/**
	 * Vida máxima que posee de forma básica.
	 */
	public static final double VIDAMAXIMA = 300;
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
	 * Poder de ataque del paladin
	 */
	public static final double FUERZA = 30;
	
	/**
	 * Cantidad máxima que tiene el paladin cuando lo creamos.
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
	 * Mana que restaura el ataque restauratorio del paladin
	 */
	public static final double MANA_RESTAURADO = 20;

	/**
	 * Poder de ataque del paladin
	 */
	private double fuerza;
	
	/**
	 * Cantidad de consumible que tiene el paladin en un determinado momento del combate
	 */
	private double mana;
	
	/**
	 * Cantidad máxima de consumible que puede tener el paladin
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
	public Paladin(String nombre, Raza raza) throws RazaNoValidaException, PatronNoValidoException {
		super(nombre, raza, VIDAMAXIMA, VELOCIDAD, GOLPCRITICO, DEF);
		setFuerza(FUERZA);
		setManaMaximo(MANAMAXIMO);
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

	private void setFuerza(double fuerza) {
		this.fuerza = fuerza;
	}

	public double getFuerza() {
		return fuerza;
	}
	public String toString() {
		return super.toString() + "\n -Fuerza: " + getFuerza() + "\n -Mana Máximo: " + getManaMaximo() + "\n -Mana: "
				+ getMana() + toStringCostoAtaques();
	}
	
	/**
	 * Comprueba las razas que puede ser el paladin.
	 */
	public void compruebaRaza(Raza raza) throws RazaNoValidaException {
		if (raza == Raza.NO_MUERTO || raza == Raza.ELFO_NOCHE|| raza ==null)
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
		return getFuerza();
	}
	
	@Override
	public String descripcionAtaqueBasico() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_BASICO + " mana",
				"DESCRIPCION: hace un poco de daño");	}

	@Override
	public double ataqueSecundario() throws ConsumibleInsuficienteException {
		super.ataqueSecundario();
		setMana(getMana() - getCosto_ata_secundario());
		setVida(getVida()+10);
		comprobarEstadisticasMaximas();
		return getFuerza();
	}
	
	@Override
	public String descripcionAtaquesecundario() {
		return PasaHtml.tresSaltoLineaCentrado("COSTO: " + COSTO_ATA_SECUNDARIO + " vida",
				"DESCRIPCION: hace bastante de daño y te ","cura un poco de vida");
	}

	@Override
	public double ataqueFinal() throws ConsumibleInsuficienteException {
		super.ataqueFinal();
		setMana(getMana() - getCosto_ata_final());
		setVida(getVidaMaxima());
		return getFuerza() * 2;
	}
	
	@Override
	public String descripcionAtaqueFinal() {
		return PasaHtml.dosSaltoLineaCentrado("COSTO: " + COSTO_ATA_FINAL + " mana",
				"DESCRIPCION: hace mucho daño y te cura toda la vida");	}

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
				"DESCRIPCION: Restaura un poco de mana");
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
		setFuerza(getFuerza() + getFuerza() * 0.1);
		setManaMaximo(getManaMaximo() + getManaMaximo() * 0.1);

	}

}

