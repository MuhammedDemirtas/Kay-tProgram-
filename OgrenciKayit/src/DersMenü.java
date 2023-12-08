import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DersMenü extends JFrame {

    private javax.swing.JPanel JPanel;
    private JLabel baslik;
    private JLabel lbl_DersKodu;
    private JLabel lbl_DersAdi;
    private JTextField txtdrad;
    private JTextField txtdrkd;
    private JLabel lbl_DersKredisi;
    private JTextField txtkrd;
    private JLabel lbl_DersSinifi;
    private JTextField txtsnf;
    private JLabel lbl_Bolum;
    private JTextField txtBlm;
    private JButton btn_Geri;
    private JButton btnKyt;
    //FORM DÜZENİ
    public DersMenü(){
        setTitle("DERS SEÇİM MENÜ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,  650);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);
        //Ana menüye yönlendirme.
        btn_Geri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //anasayfaya geri dön
                AnaMenü anasayfa = new AnaMenü();
                anasayfa.setVisible(true);

                setVisible(false);

            }
        });
        //Ekle butonuna tıklayınca oluşacak işlemler.
        btnKyt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boşlukları kontrol eder.
                if (txtdrad.getText().isEmpty() ||
                        txtdrkd.getText().isEmpty() ||
                        txtkrd.getText().isEmpty() ||
                        txtBlm.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(DersMenü.this, "Lütfen Boşluk Bırakmayınız !");
                    return;
                }
                //sınıfı kontrol eder.
                if (lbl_DersSinifi.isEnabled() && txtsnf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(DersMenü.this, "Ders sınıfı boş kalamaz !");
                    return;
                }
                 //adı kontrol eder.
                String dersAdi = txtdrad.getText();
                if (dersAdi.isEmpty() || !dersAdi.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+")) {
                    JOptionPane.showMessageDialog(DersMenü.this, "Harf dışında tuşlama yapmayınız !");
                    return;
                }
                //kodu kontrol eder.
                String dersKoduStr = txtdrkd.getText();
                if (dersKoduStr.isEmpty() || !dersKoduStr.matches("\\d+")) {
                    JOptionPane.showMessageDialog(DersMenü.this, "Sadece sayılsal veri giriniz(KOD İÇİN) !");
                    return;
                }
                int dersKodu = Integer.parseInt(dersKoduStr);
                //krediyikontrol eder.
                String dersKrediStr = txtkrd.getText();
                if (dersKrediStr.isEmpty() || !dersKrediStr.matches("\\d+(\\.\\d*)?")) {
                    JOptionPane.showMessageDialog(DersMenü.this, "Sadece sayılsal veri giriniz (KREDİ İÇİN) !");
                    return;
                }

                double dersKredi = Double.parseDouble(dersKrediStr);
                //bölümü kontrol eder.
                String bolum = txtBlm.getText();
                if (bolum.isEmpty() || !bolum.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ ]+")) {
                    JOptionPane.showMessageDialog(DersMenü.this, "Bölüm adı sadece harflerden ve boşluklardan oluşmalıdır.");
                    return;
                }

                String dersSinifi = txtsnf.getText();

                Ders ders = new Ders(dersAdi, dersKodu, dersKredi, dersSinifi, bolum);

                try {
                    kaydetDersBilgileri(ders);
                    JOptionPane.showMessageDialog(DersMenü.this, "Ders bilgileri başarıyla kaydedildi!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(DersMenü.this, "Hata: Ders bilgileri kaydedilemedi.");
                    ex.printStackTrace();
                }

            }

        });

    }

    //Dersleri csv ye kayıt eder
    private void kaydetDersBilgileri(Ders ders) throws IOException {
        //csv dosyasına yazmak için BufferedWriter kullanma.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DersKayıt.csv", true))) {
            //Ders bilgilerini Constant.CSV dosyasına yazma
            writer.write(ders.toCSV());
            writer.newLine();
        }
    }


    public static void main(String[] args) {

        new DersMenü();
    }
}
