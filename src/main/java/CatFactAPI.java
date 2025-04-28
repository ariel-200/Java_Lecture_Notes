import kong.unirest.Unirest;

public class CatFactAPI {

    public static void main(String[] args) {

        String url = "https://catfact.ninja/fact";
        CatFact catFact = Unirest.get(url).asObject(CatFact.class).getBody();

        String fact = catFact.getFact();

        System.out.println("A cat fact is: \n" + fact);

        int length = catFact.getLength();
        System.out.println("A cat fact length is: " + length);
    }
}

class CatFact {
    private String fact;
    private int length;

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}