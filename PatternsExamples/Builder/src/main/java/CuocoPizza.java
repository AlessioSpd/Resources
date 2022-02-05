// abstract builder
public abstract class CuocoPizza {
    protected Pizza pizza;

    public Pizza getPizza(){
        return pizza;
    }

    public void creaPizza(){
        pizza = new Pizza();
    }

    public abstract void setIngredienti();
}
