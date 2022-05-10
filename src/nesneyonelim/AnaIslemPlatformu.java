package nesneyonelim;

import java.sql.*;
import java.util.Scanner;

public class AnaIslemPlatformu {

    public static void main(String[] args) throws SQLException, InterruptedException {
        //Veritabani Baglantisi
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Yetkililer",
                    "postgres", "0421");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        int devamMi = 0;
        do {
            //SQL sorgusu
            String sql = "SELECT * FROM yetkililer";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.print("Kullanici adini giriniz: ");
            String kullaniciAdi = scanner.next();
            //Kullanici adi dogrulama
            if (rs.getString("adi").equals(kullaniciAdi)) {
                do{
                    System.out.print("Sifre giriniz: ");
                    String sifre = scanner.next();
                    //Sifre dogrulama
                    if (rs.getString("sifre").equals(sifre)) {
                        System.out.println("Yetkili girisi yapiliyor... ");
                        Thread.sleep(2000);
                        System.out.println("Yetkili girisi yapildi: " + kullaniciAdi);
                        System.out.println("\n");
                        Thread.sleep(1000);
                        //Kullanici, yayinci ve sogutucuyu olusturma
                        Kullanici kullanici = new Kullanici(kullaniciAdi, sifre);
                        Yayinci yayinci = new Yayinci();
                        Sogutucu sogutucu = new Sogutucu(yayinci);
                        //Kullaniciyi yayinciya abone etmek
                        yayinci.ekle(kullanici);
                        Thread.sleep(1000);
                        int menu;
                        //Menu
                        do{
                            System.out.println();
                            System.out.println("-------Ana Menu-------");
                            System.out.println("1-Sicakligi Goster");
                            System.out.println("2-Sogutucu Durumu");
                            System.out.println("3-Sogutucu Ac");
                            System.out.println("4-Sogutucu Kapa");
                            System.out.println("5-Cikis");
                            System.out.println("Lutfen yapmak istediginiz islemi seciniz.");
                            menu = scanner.nextInt();
                            System.out.println("Islem yapiliyor...");
                            Thread.sleep(1500);
                            if(menu==1){
                                sogutucu.sicaklikGoster();
                            }
                            else if(menu==2){
                                sogutucu.sogutucuDurumu();
                            }
                            else if(menu==3){
                                sogutucu.sogutucuAc();
                            }
                            else if(menu==4){
                                sogutucu.sogutucuKapa();
                            }
                            else{
                                //Kullanici cikisi
                                yayinci.cikart(kullanici);
                                //Programdan cikis
                                System.out.println("Cikis yapildi.");
                                return;
                            }
                            Thread.sleep(500);
                        }while((menu>0&&menu<5));

                    } else {
                        System.out.println("Sifre yanlis.");
                        System.out.println("Tekrar sifre girmek istiyorsaniz 1'i tuslayiniz.");
                        devamMi = scanner.nextInt();
                    }
                }while(devamMi == 1);
            } else System.out.println("Kullanici adi yanlis.");
            System.out.println("Kullanici adini tekrar girmek istiyorsaniz 1'i tuslayiniz.");
            devamMi = scanner.nextInt();
        }while(devamMi == 1);
    }
}