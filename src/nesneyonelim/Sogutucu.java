package nesneyonelim;

//Random sayi ve sleep islemi icin import
import java.util.Random;
import java.lang.Thread;


public class Sogutucu {

    boolean acikKapali;
    int sicaklik = 0;
    IAraBirim yayinci;//Notify bildirimi yapmak icin IArabirim turunde yayinci degiskeni olusturma
    Random random = new Random();//Random sayi ureteci

    //Yapici fonksiyon
    public Sogutucu(IAraBirim yayinci){
        this.yayinci = yayinci;
        this.acikKapali = false; // Baslangicta sogutucu kapali
        this.sicaklik = sicaklikBul(); //Baslangicta rastgele sicaklik tanimlama
        System.out.println("Oda sicaklik durumu: " + this.sicaklik + "\n");
    }

    //Sogutucunun acik/kapali durumunu yazdirma
    public void sogutucuDurumu(){
        System.out.println("Sogutucu durumu: " + (this.acikKapali ? "ACIK" : "KAPALI" ));
    }

    //Ortam sicakligini goruntuleme
    public void sicaklikGoster(){
        System.out.println("Sicaklik: " + this.sicaklik);
    }

    //Baslangicta rastgele sicaklik uretme fonksiyonu
    public int sicaklikBul(){
        int sicaklik = this.sicaklik + random.nextInt(18,35);
        return sicaklik;
    }

    //Sogutucu acma fonksiyonu
    public void sogutucuAc(){
        if(this.acikKapali){
            System.out.println("Sogutucu zaten acik!\n");
            return;
        }
        this.acikKapali = true;
        try {
            System.out.println("Sogutucu aciliyor...");
            Thread.sleep(2500); //Acilmasi icin bekleme suresi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sicaklikDegisikligi = random.nextInt(1,10); //Acildiktan sonra sicakligin azaltilmasi
        this.sicaklik -= sicaklikDegisikligi;
        //Yayinci araciligi ile kullaniciya bildirim gonderme
        yayinci.bildir("Sogutucu acik, yeni sicaklik: " + this.sicaklik + ".\n");
    }

    //Sogutucu kapama fonksiyonu
    public void sogutucuKapa(){
        if(!this.acikKapali){
            System.out.println("Sogutucu zaten kapali!\n");
            return;
        }
        this.acikKapali = false;
        try {
            System.out.println("Sogutucu kapaniyor...");
            Thread.sleep(2500);//Kapanmasi icin bekleme suresi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sicaklikDegisikligi = random.nextInt(1,10);//Kapandiktan sonra sicakligin artmasi
        this.sicaklik += sicaklikDegisikligi;
        //Yayinci araciligi ile kullaniciya notify gonderme
        yayinci.bildir("sogutucu kapali ve yeni sicaklik: " + this.sicaklik + ".\n");
    }
}
