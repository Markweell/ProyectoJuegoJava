package pgn.utiles;

public class PasaHtml {

	public static String unSaltoLineaCentrado(String mj) {
		return "<html><body><center>"+mj+"</center></body></html>";
	}
	public static String dosSaltoLineaCentrado(String mj,String mj2) {
		return "<html><body><center>"+mj+"</center><center>"+mj2+"</center></body></html>";
	}
	public static String tresSaltoLineaCentrado(String mj, String mj2, String mj3) {
		return "<html><body><center>"+mj+"</center><center>"+mj2+"</center><center>"+mj3+"</center></body></html>";
	}
}
