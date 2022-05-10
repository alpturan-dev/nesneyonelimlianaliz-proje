package nesneyonelim;

import java.util.ArrayList;
import java.util.List;

public class Yayinci implements IAraBirim {

    private List<IGozlemci> kullanicilar = new ArrayList<>();

    @Override
    public void ekle(IGozlemci gozlemci) {
        kullanicilar.add(gozlemci);
    }

    @Override
    public void cikart(IGozlemci gozlemci) {
        kullanicilar.remove(gozlemci);
        System.out.println("Kullanici cikis yapti.");
    }

    @Override
    public void bildir(String mesaj) {
        for (IGozlemci kullanici: kullanicilar){
            kullanici.guncelle(mesaj);
        }
    }
}
