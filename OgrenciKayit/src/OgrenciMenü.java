import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class OgrenciMenü extends JFrame {

    private JButton btn_Geri;
    private JLabel lbm;
    private JLabel lblAd;
    private JTextField txAd;
    private JLabel lblSoyad;
    private JTextField txS;
    private JButton btKyt;
    private JLabel lblSınıf;
    private JComboBox cmbSnf;
    private JLabel lbl_DTarih;
    private JLabel lbDers;
    private JLabel lblBolum;
    private JTextField txbolum;
    private JComboBox cmDers;
    private javax.swing.JPanel JPanel;
    private JLabel lblogrNo;
    private JTextField txOn;
    //FORM DÜZENİ
    public OgrenciMenü() {

        setTitle("ÖĞRENCİ KAYIT MENÜ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 631);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);

        //Ana menüye yönlendirme.
        btn_Geri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnaMenü anasayfa = new AnaMenü();
                anasayfa.setVisible(true);


                setVisible(false);
            }
        });

        //Ekle butonuna tıklayınca oluşacak işlemler.
        btKyt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String ad = txAd.getText();
                    String soyad = txS.getText();
                    String ogrNoStr = txOn.getText();
                    String sinif = cmbSnf.getSelectedItem().toString();

                    //Boşlukları kontrol eder.
                    if (ad.isEmpty() || soyad.isEmpty() || ogrNoStr.isEmpty() ) {
                        JOptionPane.showMessageDialog(OgrenciMenü.this, "LÜTFEN BOŞLUK BIRAKMAYINIZ !");
                        return;
                    }

                    //Adın string kontrolü.
                    if (ad.isEmpty() || !ad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+")) {
                        JOptionPane.showMessageDialog(OgrenciMenü.this, "SADECE HARF GİRİNİZ (AD) !");
                        return;
                    }

                    //Soyadın string kontrolü.
                    if (soyad.isEmpty() || !soyad.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+")) {
                        JOptionPane.showMessageDialog(OgrenciMenü.this, "SADECE HARF GİRİNİZ (SOYAD)!");
                        return;
                    }

                    //Numaranın sayısal kontrolü.
                    if (ogrNoStr.isEmpty() || !ogrNoStr.matches("\\d+")) {
                        JOptionPane.showMessageDialog(OgrenciMenü.this, "SADECE SAYI GİRİNİZ GİRİNİZ (ÖĞRENCİ NO) !");
                        return;
                    }

                    String ders = cmDers.getSelectedItem().toString();

                    //Sorunsuz giriş olmuşsa kayıt yapar.
                    Ogrenci ogrenci = new Ogrenci(ad, soyad, sinif, ders, ogrNoStr);

                    try {
                        kaydetOgrenciBilgileri(ogrenci);
                        JOptionPane.showMessageDialog(OgrenciMenü.this, "BİLGİLERİNİZ BAŞARIYLA KAYIT OLMUŞTUR");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(OgrenciMenü.this, "HATA KAYIT YAPILAMADI !!");
                        ex.printStackTrace();
                    }
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }


            }
        });

        //Bölüm kısmındaki değişiklikleri algılar ve kontrol eder.
        txbolum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ogrenciBolumu = txbolum.getText();
                //
                try {
                    String[] dersler = getBolumeAitDersler(ogrenciBolumu);
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(dersler);
                    cmDers.setModel(model);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(OgrenciMenü.this, "Hata: Dersler yüklenirken bir hata oluştu.");
                }
            }
        });
    }

//Girilen bölüme ait detsleri alır.
    private String[] getBolumeAitDersler(String bolum) throws IOException {
        String dosyaYolu = "DersKayıt.csv";
        String[] dersler = new String[0];
        //bölümü string içinde bulur ve ders adını da.
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String dersBolumu = parts[4];
                if (dersBolumu.equals(bolum)) {
                    String dersAdi = parts[1];
                    dersler = append(dersler, dersAdi);
                }
            }
        }

        return dersler;
    }

    //Dizileri genişletir.
    private String[] append(String[] arr, String element) {
        String[] newArr = new String[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = element;
        return newArr;
    }

    //öğrenci bilgilerini OgrenciKayıt.csv ye kayıt eder.
    private void kaydetOgrenciBilgileri(Ogrenci ogrenci) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("OgrenciKayıt.csv", true))) {

            writer.write(ogrenci.toCSV());
            writer.newLine();
        }
    }

    public static void main(String[] args) {
        new OgrenciMenü();
    }
}