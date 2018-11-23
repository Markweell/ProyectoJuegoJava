package pgn.proyectoFinal.negocio;

/**
 * Enumeración para las distintas razas que pueden ser los personajes.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
public enum Raza {
	ORCO(Faccion.HORDA), NO_MUERTO(Faccion.HORDA), TROLL(Faccion.HORDA), ELFO_SANGRE(Faccion.HORDA), HUMANO(
			Faccion.ALIANZA), GNOMO(Faccion.ALIANZA), ELFO_NOCHE(Faccion.ALIANZA), DRAENEI(Faccion.ALIANZA);

	private Faccion faccion;

	private Raza(Faccion faccion) {
		setFaccion(faccion);
	}

	private void setFaccion(Faccion faccion) {
		this.faccion = faccion;
	}

	public Faccion getFaccion() {
		return faccion;
	}

}
