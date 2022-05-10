package nesneyonelim;

//Gozlemci olan kullanici sinifinin olusturulmasi
public class Kullanici implements IGozlemci {

    private String kullaniciAdi;
    private String sifre;

    public Kullanici (String kullaniciAdi, String sifre){
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
    }

    @Override
    public void guncelle(String mesaj) {
        System.out.println("Sayin kullanici " + this.kullaniciAdi + ", " + mesaj );
    }
}
