   package marketPlace;

   
import java.util.ArrayList;
import java.util.List;


public class Registro {
	
	private List<Oferta> ofertas;
	private List<ContraOferta> contraOfertas;

	
	public Registro() {
		
		this.ofertas = new ArrayList<>();
		this.contraOfertas = new ArrayList<>();
	}
	
	public List<Oferta> getOfertas() {
		return ofertas;
	}
	
	public List<ContraOferta> getContraOfertas(){
		return contraOfertas;
	}

}
