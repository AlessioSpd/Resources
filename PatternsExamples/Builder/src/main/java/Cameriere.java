// director
public class Cameriere {
	
	// abstract builder
    private CuocoPizza cuocoPizza;
    
    public Cameriere(CuocoPizza cuocoPizza) {
    	this.cuocoPizza = cuocoPizza;
    }

    public Pizza creaPizza(){
    	this.cuocoPizza.creaPizza();
    	this.cuocoPizza.setIngredienti();
    	return cuocoPizza.getPizza();
    }
   
}
