//Ders kayıt bilgileri sınıfı.
public class Ders {
    private String dersAdi;
    private int dersKodu;
    private double dersKredi;
    private String dersSinifi;
    private String bolum;

    //Çekip sıralıyor.
    public Ders(String dersAdi, int dersKodu, double dersKredi, String dersSinifi, String bolum) {
        this.dersAdi = dersAdi;
        this.dersKodu = dersKodu;
        this.dersKredi = dersKredi;
        this.dersSinifi = dersSinifi;
        this.bolum = bolum;
    }

    public String getDersSinifi() {
        return dersSinifi;
    }

    public void setDersSinifi(String dersSinifi) {
        this.dersSinifi = dersSinifi;
    }

    //Sıralamayı csv ye kayıt yapar.
    public String toCSV() {
        // Kayıt dosyasını (DersKayıt.csv) ye "kod, ad, kredi,  sınıf, bolum" şeklinde kayıt yapar.
        return dersKodu + "," + dersAdi + "," + dersKredi + "," + dersSinifi + "," + bolum ;
    }
}







