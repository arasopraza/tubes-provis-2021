/*
 * NAMA  : Fiona Avila Putri
 * NIM   : 10119013
 * KELAS : IF1 2019/2020
 */
package kemahasiswaan_10119001_10119013;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Fiona Avila
 */
public class frm_nilai_akhir extends javax.swing.JFrame {

    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    String kd_mk;
    String nama_mk;
    ArrayList<MataKuliah> arrMataKuliah = new ArrayList<>();
    
    /**
     * Creates new form frm_nilai_akhir
     */
    public frm_nilai_akhir() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tbl_simulasi_nilai.setModel(tableModel);
        settableload();
        tampil_combo_mata_kulah();
        tampil_kd_mk();
    }
    
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        //  Membuat judul header
        return new javax.swing.table.DefaultTableModel
        (
            new Object[][] {},
            new String [] {
                "Nama MK",
                "Persen Absen",
                "Persen Tugas",
                "Persen UTS",
                "Persen UAS",
                "Absensi",
                "Tgs 1",
                "Tgs 2",
                "Tgs 3",
                "UTS",
                "UAS",
                "Nilai Absen",
                "Nilai Tugas",
                "Nilai UTS",
                "Nilai UAS",
                "Nilai Akhir",
                "Indeks",
                "Keterangan",
            }
        )
                
        // disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    
    String data[]=new String[19];
 
    private void settableload() {
        String stat = "";
        try {
            Class.forName(driver);
            java.sql.Connection kon = DriverManager.getConnection(
                    database,
                    user,
                    pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT t_mata_kuliah.nama_mk, t_nilai_akhir.persen_absen, "
                    + "t_nilai_akhir.persen_tugas, t_nilai_akhir.persen_uts, "
                    + "t_nilai_akhir.persen_uas, t_nilai_akhir.kehadiran, "
                    + "t_nilai_akhir.tugas_satu, t_nilai_akhir.tugas_dua, "
                    + "t_nilai_akhir.tugas_tiga, t_nilai_akhir.uts, "
                    + "t_nilai_akhir.uas, t_nilai_akhir.nilai_absen, "
                    + "t_nilai_akhir.nilai_tugas, t_nilai_akhir.nilai_uts, "
                    + "t_nilai_akhir.nilai_uas, t_nilai_akhir.nilai_akhir, "
                    + "t_nilai_akhir.indeks, t_nilai_akhir.ket "
                    + "FROM t_nilai_akhir "
                    + "JOIN t_mata_kuliah "
                    + "ON t_nilai_akhir.kd_mk = t_mata_kuliah.kd_mk;";
            ResultSet res = stt.executeQuery(SQL);
            
            while (res.next()) {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                data[8] = res.getString(9);
                data[9] = res.getString(10);
                data[10] = res.getString(11);
                data[11] = res.getString(12);
                data[12] = res.getString(13);
                data[13] = res.getString(14);
                data[14] = res.getString(15);
                data[15] = res.getString(16);
                data[16] = res.getString(17);
                data[17] = res.getString(18);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    public void membersihkan_teks(){
        txt_nilai_akhir_kd_mk.setText("");
        txt_nilai_akhir_kehadiran.setText("");
        txt_nilai_akhir_tugas1.setText("");
        txt_nilai_akhir_tugas2.setText("");
        txt_nilai_akhir_tugas3.setText("");
        txt_nilai_akhir_uts.setText("");
        txt_nilai_akhir_uas.setText("");
        txt_nilai_akhir_persen_absen.setText("");
        txt_nilai_akhir_persen_tugas.setText("");
        txt_nilai_akhir_persen_uts.setText("");
        txt_nilai_akhir_persen_uas.setText("");
    }
    
    public void nonaktif_teks(){
        txt_nilai_akhir_kd_mk.setEnabled(false);
        txt_nilai_akhir_kehadiran.setEnabled(false);
        txt_nilai_akhir_tugas1.setEnabled(false);
        txt_nilai_akhir_tugas2.setEnabled(false);
        txt_nilai_akhir_tugas3.setEnabled(false);
        txt_nilai_akhir_uts.setEnabled(false);
        txt_nilai_akhir_uas.setEnabled(false);
        txt_nilai_akhir_persen_absen.setEnabled(false);
        txt_nilai_akhir_persen_tugas.setEnabled(false);
        txt_nilai_akhir_persen_uts.setEnabled(false);
        txt_nilai_akhir_persen_uas.setEnabled(false);
    }
    
    public void aktif_teks(){
       txt_nilai_akhir_kd_mk.setEnabled(true);
        txt_nilai_akhir_kehadiran.setEnabled(true);
        txt_nilai_akhir_tugas1.setEnabled(true);
        txt_nilai_akhir_tugas2.setEnabled(true);
        txt_nilai_akhir_tugas3.setEnabled(true);
        txt_nilai_akhir_uts.setEnabled(true);
        txt_nilai_akhir_uas.setEnabled(true);
        txt_nilai_akhir_persen_absen.setEnabled(true);
        txt_nilai_akhir_persen_tugas.setEnabled(true);
        txt_nilai_akhir_persen_uts.setEnabled(true);
        txt_nilai_akhir_persen_uas.setEnabled(true);
    }
    
    int row = 0;
    public void tampil_field(){
        row = tbl_simulasi_nilai.getSelectedRow();
        combo_nilai_akhir_nama_mk.setSelectedItem(tableModel.getValueAt(row, 0));
        txt_nilai_akhir_persen_absen.setText(tableModel.getValueAt(row, 1).toString());
        txt_nilai_akhir_persen_tugas.setText(tableModel.getValueAt(row, 2).toString());
        txt_nilai_akhir_persen_uts.setText(tableModel.getValueAt(row, 3).toString());
        txt_nilai_akhir_persen_uas.setText(tableModel.getValueAt(row, 4).toString());
        txt_nilai_akhir_kehadiran.setText(tableModel.getValueAt(row, 5).toString());
        txt_nilai_akhir_tugas1.setText(tableModel.getValueAt(row, 6).toString());
        txt_nilai_akhir_tugas2.setText(tableModel.getValueAt(row, 7).toString());
        txt_nilai_akhir_tugas3.setText(tableModel.getValueAt(row, 8).toString());
        txt_nilai_akhir_uts.setText(tableModel.getValueAt(row, 9).toString());
        txt_nilai_akhir_uas.setText(tableModel.getValueAt(row, 10).toString());
        
        btn_nilai_akhir_simpan.setEnabled(false);
        btn_nilai_akhir_ubah.setEnabled(true);
        btn_nilai_akhir_hapus.setEnabled(true);
        btn_nilai_akhir_batal.setEnabled(true);
        aktif_teks();
    }
    
     public void tampil_combo_mata_kulah(){
        try {
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String sql = "SELECT kd_mk, nama_mk FROM t_mata_kuliah ORDER BY nama_mk ASC;";
            ResultSet res = stt.executeQuery(sql);
            while(res.next()){
                arrMataKuliah.add(new MataKuliah(res.getString("kd_mk"), res.getString("nama_mk")));
                // fungsi ini bertugas menampung isi dari database
            }
            for(int i=0;i<arrMataKuliah.size();i++){
                combo_nilai_akhir_nama_mk.addItem(arrMataKuliah.get(i).getNamaMK());
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void tampil_kd_mk(){
        int idx = combo_nilai_akhir_nama_mk.getSelectedIndex();
        nama_mk = (String)combo_nilai_akhir_nama_mk.getSelectedItem();
        String data[] = new String[1];
        if(idx>=0){
            try {
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String sql = "SELECT kd_mk FROM t_mata_kuliah WHERE nama_mk='"+nama_mk+"'";      
                ResultSet res = stt.executeQuery(sql);
                while(res.next()){
                    data[0] = res.getString(1);
                    txt_nilai_akhir_kd_mk.setText(data[0]);
                }
                res.close();
                stt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_nilai_akhir_ubah1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbl_jdl_nilai_akhir = new javax.swing.JLabel();
        lbl_nilai_akhir_nama_mk = new javax.swing.JLabel();
        combo_nilai_akhir_nama_mk = new javax.swing.JComboBox<>();
        lbl_nilai_akhir_kd_mk = new javax.swing.JLabel();
        txt_nilai_akhir_kd_mk = new javax.swing.JTextField();
        lbl_nilai_akhir_persen_absen = new javax.swing.JLabel();
        txt_nilai_akhir_persen_absen = new javax.swing.JTextField();
        lbl_persen_absen = new javax.swing.JLabel();
        lbl_nilai_akhir_persen_tugas = new javax.swing.JLabel();
        txt_nilai_akhir_persen_tugas = new javax.swing.JTextField();
        lbl_persen_tugas = new javax.swing.JLabel();
        lbl_nilai_akhir_persen_uts = new javax.swing.JLabel();
        txt_nilai_akhir_persen_uts = new javax.swing.JTextField();
        lbl_persen_uts = new javax.swing.JLabel();
        lbl_nilai_akhir_persen_uas = new javax.swing.JLabel();
        txt_nilai_akhir_persen_uas = new javax.swing.JTextField();
        lbl_persen_uas = new javax.swing.JLabel();
        lbl_nilai_akhir_kehadiran = new javax.swing.JLabel();
        txt_nilai_akhir_kehadiran = new javax.swing.JTextField();
        lbl_nilai_akhir_pertemuan = new javax.swing.JLabel();
        lbl_nilai_akhir_tugas1 = new javax.swing.JLabel();
        lbl_nilai_akhir_tugas2 = new javax.swing.JLabel();
        lbl_nilai_akhir_tugas3 = new javax.swing.JLabel();
        txt_nilai_akhir_tugas1 = new javax.swing.JTextField();
        txt_nilai_akhir_tugas2 = new javax.swing.JTextField();
        txt_nilai_akhir_tugas3 = new javax.swing.JTextField();
        lbl_nilai_akhir_uts = new javax.swing.JLabel();
        txt_nilai_akhir_uts = new javax.swing.JTextField();
        lbl_nilai_akhir_uas = new javax.swing.JLabel();
        txt_nilai_akhir_uas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_simulasi_nilai = new javax.swing.JTable();
        btn_nilai_akhir_tambah = new javax.swing.JButton();
        btn_nilai_akhir_ubah = new javax.swing.JButton();
        btn_nilai_akhir_hapus = new javax.swing.JButton();
        btn_nilai_akhir_simpan = new javax.swing.JButton();
        btn_nilai_akhir_batal = new javax.swing.JButton();
        btn_nilai_akhir_keluar = new javax.swing.JButton();

        btn_nilai_akhir_ubah1.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btn_nilai_akhir_ubah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119001_10119013/ubah.png"))); // NOI18N
        btn_nilai_akhir_ubah1.setText("UBAH");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIMULASI NILAI AKHIR");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(209, 209, 229));
        jPanel1.setPreferredSize(new java.awt.Dimension(1355, 490));

        jPanel2.setBackground(new java.awt.Color(162, 169, 226));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        lbl_jdl_nilai_akhir.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        lbl_jdl_nilai_akhir.setText("FORM SIMULASI NILAI AKHIR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(lbl_jdl_nilai_akhir)
                .addContainerGap(579, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_jdl_nilai_akhir)
                .addContainerGap())
        );

        lbl_nilai_akhir_nama_mk.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_nama_mk.setText("Nama MK");

        combo_nilai_akhir_nama_mk.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        combo_nilai_akhir_nama_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_nilai_akhir_nama_mkActionPerformed(evt);
            }
        });

        lbl_nilai_akhir_kd_mk.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_kd_mk.setText("Kode MK");

        txt_nilai_akhir_kd_mk.setEditable(false);
        txt_nilai_akhir_kd_mk.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        lbl_nilai_akhir_persen_absen.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_persen_absen.setText("Persentase Absen");

        txt_nilai_akhir_persen_absen.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        lbl_persen_absen.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_persen_absen.setText("%");

        lbl_nilai_akhir_persen_tugas.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_persen_tugas.setText("Persentase Tugas");

        txt_nilai_akhir_persen_tugas.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        lbl_persen_tugas.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_persen_tugas.setText("%");

        lbl_nilai_akhir_persen_uts.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_persen_uts.setText("Persentase UTS");

        txt_nilai_akhir_persen_uts.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        lbl_persen_uts.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_persen_uts.setText("%");

        lbl_nilai_akhir_persen_uas.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_persen_uas.setText("Persentase UAS");

        txt_nilai_akhir_persen_uas.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        lbl_persen_uas.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_persen_uas.setText("%");

        lbl_nilai_akhir_kehadiran.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_kehadiran.setText("Kehadiran");

        txt_nilai_akhir_kehadiran.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        lbl_nilai_akhir_pertemuan.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_pertemuan.setText("pertemuan");

        lbl_nilai_akhir_tugas1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_tugas1.setText("Tugas 1");

        lbl_nilai_akhir_tugas2.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_tugas2.setText("Tugas 2");

        lbl_nilai_akhir_tugas3.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_tugas3.setText("Tugas 3");

        txt_nilai_akhir_tugas1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        txt_nilai_akhir_tugas2.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        txt_nilai_akhir_tugas3.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        lbl_nilai_akhir_uts.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_uts.setText("UTS");

        txt_nilai_akhir_uts.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        lbl_nilai_akhir_uas.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_nilai_akhir_uas.setText("UAS");

        txt_nilai_akhir_uas.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        tbl_simulasi_nilai.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbl_simulasi_nilai.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        tbl_simulasi_nilai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama MK", "Persen Absen", "Persen Tugas", "Persen UTS", "Persen UAS", "Absensi", "Tgs 1", "Tgs 2", "Tgs 3", "UTS", "UAS", "Nilai Absen", "Nilai Tugas", "Nilai UTS", "Nilai UAS", "Nilai Akhir", "Indeks", "Keterangan"
            }
        ));
        tbl_simulasi_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_simulasi_nilaiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_simulasi_nilaiMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_simulasi_nilai);

        btn_nilai_akhir_tambah.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btn_nilai_akhir_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119001_10119013/tambah.png"))); // NOI18N
        btn_nilai_akhir_tambah.setText("TAMBAH");
        btn_nilai_akhir_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_tambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_tambahMouseExited(evt);
            }
        });
        btn_nilai_akhir_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nilai_akhir_tambahActionPerformed(evt);
            }
        });

        btn_nilai_akhir_ubah.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btn_nilai_akhir_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119001_10119013/ubah.png"))); // NOI18N
        btn_nilai_akhir_ubah.setText("UBAH");
        btn_nilai_akhir_ubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_ubahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_ubahMouseExited(evt);
            }
        });
        btn_nilai_akhir_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nilai_akhir_ubahActionPerformed(evt);
            }
        });

        btn_nilai_akhir_hapus.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btn_nilai_akhir_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119001_10119013/hapus.png"))); // NOI18N
        btn_nilai_akhir_hapus.setText("HAPUS");
        btn_nilai_akhir_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_hapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_hapusMouseExited(evt);
            }
        });

        btn_nilai_akhir_simpan.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btn_nilai_akhir_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119001_10119013/simpan.png"))); // NOI18N
        btn_nilai_akhir_simpan.setText("SIMPAN");
        btn_nilai_akhir_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_simpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_simpanMouseExited(evt);
            }
        });
        btn_nilai_akhir_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nilai_akhir_simpanActionPerformed(evt);
            }
        });

        btn_nilai_akhir_batal.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btn_nilai_akhir_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119001_10119013/batal.png"))); // NOI18N
        btn_nilai_akhir_batal.setText("BATAL");
        btn_nilai_akhir_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_batalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_batalMouseExited(evt);
            }
        });

        btn_nilai_akhir_keluar.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btn_nilai_akhir_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119001_10119013/keluar.png"))); // NOI18N
        btn_nilai_akhir_keluar.setText("KELUAR");
        btn_nilai_akhir_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_keluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_nilai_akhir_keluarMouseExited(evt);
            }
        });
        btn_nilai_akhir_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nilai_akhir_keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_nilai_akhir_nama_mk)
                                    .addComponent(lbl_nilai_akhir_kd_mk))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo_nilai_akhir_nama_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_nilai_akhir_kd_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_nilai_akhir_persen_absen)
                                .addGap(18, 18, 18)
                                .addComponent(txt_nilai_akhir_persen_absen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_persen_absen))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_nilai_akhir_persen_tugas)
                                    .addComponent(lbl_nilai_akhir_persen_uts)
                                    .addComponent(lbl_nilai_akhir_persen_uas))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_nilai_akhir_persen_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_persen_uas))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_nilai_akhir_persen_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_persen_uts))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_nilai_akhir_persen_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_persen_tugas)))))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbl_nilai_akhir_kehadiran)
                                    .addGap(18, 18, 18))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbl_nilai_akhir_tugas1)
                                    .addGap(31, 31, 31)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_nilai_akhir_tugas2)
                                    .addComponent(lbl_nilai_akhir_tugas3)
                                    .addComponent(lbl_nilai_akhir_uts)
                                    .addComponent(lbl_nilai_akhir_uas))
                                .addGap(31, 31, 31)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nilai_akhir_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nilai_akhir_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nilai_akhir_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nilai_akhir_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_nilai_akhir_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_nilai_akhir_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_nilai_akhir_pertemuan)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_nilai_akhir_tambah)
                        .addGap(18, 18, 18)
                        .addComponent(btn_nilai_akhir_ubah)
                        .addGap(18, 18, 18)
                        .addComponent(btn_nilai_akhir_hapus)
                        .addGap(18, 18, 18)
                        .addComponent(btn_nilai_akhir_simpan)
                        .addGap(18, 18, 18)
                        .addComponent(btn_nilai_akhir_batal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_nilai_akhir_keluar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nilai_akhir_nama_mk)
                    .addComponent(combo_nilai_akhir_nama_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nilai_akhir_kehadiran)
                    .addComponent(txt_nilai_akhir_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nilai_akhir_pertemuan))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nilai_akhir_kd_mk)
                    .addComponent(txt_nilai_akhir_kd_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nilai_akhir_tugas1)
                    .addComponent(txt_nilai_akhir_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nilai_akhir_persen_absen)
                    .addComponent(txt_nilai_akhir_persen_absen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_persen_absen)
                    .addComponent(lbl_nilai_akhir_tugas2)
                    .addComponent(txt_nilai_akhir_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nilai_akhir_persen_tugas)
                    .addComponent(txt_nilai_akhir_persen_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_persen_tugas)
                    .addComponent(lbl_nilai_akhir_tugas3)
                    .addComponent(txt_nilai_akhir_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nilai_akhir_persen_uts)
                    .addComponent(txt_nilai_akhir_persen_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_persen_uts)
                    .addComponent(lbl_nilai_akhir_uts)
                    .addComponent(txt_nilai_akhir_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nilai_akhir_persen_uas)
                    .addComponent(txt_nilai_akhir_persen_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_persen_uas)
                    .addComponent(lbl_nilai_akhir_uas)
                    .addComponent(txt_nilai_akhir_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_nilai_akhir_tambah)
                    .addComponent(btn_nilai_akhir_ubah)
                    .addComponent(btn_nilai_akhir_hapus)
                    .addComponent(btn_nilai_akhir_simpan)
                    .addComponent(btn_nilai_akhir_batal)
                    .addComponent(btn_nilai_akhir_keluar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1371, 730));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        frm_utama u = new frm_utama();
        u.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void btn_nilai_akhir_tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_tambahMouseEntered
        btn_nilai_akhir_tambah.setBackground(new java.awt.Color(128,136,203));
        btn_nilai_akhir_tambah.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_nilai_akhir_tambahMouseEntered

    private void btn_nilai_akhir_tambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_tambahMouseExited
        btn_nilai_akhir_tambah.setBackground(new java.awt.Color(255, 255, 255));
        btn_nilai_akhir_tambah.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_nilai_akhir_tambahMouseExited

    private void btn_nilai_akhir_ubahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_ubahMouseEntered
        btn_nilai_akhir_ubah.setBackground(new java.awt.Color(128,136,203));
        btn_nilai_akhir_ubah.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_nilai_akhir_ubahMouseEntered

    private void btn_nilai_akhir_ubahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_ubahMouseExited
        btn_nilai_akhir_ubah.setBackground(new java.awt.Color(255, 255, 255));
        btn_nilai_akhir_ubah.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_nilai_akhir_ubahMouseExited

    private void btn_nilai_akhir_hapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_hapusMouseEntered
        btn_nilai_akhir_hapus.setBackground(new java.awt.Color(128,136,203));
        btn_nilai_akhir_hapus.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_nilai_akhir_hapusMouseEntered

    private void btn_nilai_akhir_hapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_hapusMouseExited
        btn_nilai_akhir_hapus.setBackground(new java.awt.Color(255, 255, 255));
        btn_nilai_akhir_hapus.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_nilai_akhir_hapusMouseExited

    private void btn_nilai_akhir_simpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_simpanMouseEntered
        btn_nilai_akhir_simpan.setBackground(new java.awt.Color(128,136,203));
        btn_nilai_akhir_simpan.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_nilai_akhir_simpanMouseEntered

    private void btn_nilai_akhir_simpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_simpanMouseExited
        btn_nilai_akhir_simpan.setBackground(new java.awt.Color(255, 255, 255));
        btn_nilai_akhir_simpan.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_nilai_akhir_simpanMouseExited

    private void btn_nilai_akhir_batalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_batalMouseEntered
        btn_nilai_akhir_batal.setBackground(new java.awt.Color(128,136,203));
        btn_nilai_akhir_batal.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_nilai_akhir_batalMouseEntered

    private void btn_nilai_akhir_batalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_batalMouseExited
        btn_nilai_akhir_batal.setBackground(new java.awt.Color(255, 255, 255));
        btn_nilai_akhir_batal.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_nilai_akhir_batalMouseExited

    private void btn_nilai_akhir_keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_keluarMouseEntered
        btn_nilai_akhir_keluar.setBackground(new java.awt.Color(255,102,102));
        btn_nilai_akhir_keluar.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_nilai_akhir_keluarMouseEntered

    private void btn_nilai_akhir_keluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_keluarMouseExited
        btn_nilai_akhir_keluar.setBackground(new java.awt.Color(255, 255, 255));
        btn_nilai_akhir_keluar.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_nilai_akhir_keluarMouseExited

    private void btn_nilai_akhir_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_keluarActionPerformed
        frm_utama u = new frm_utama();
        u.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_nilai_akhir_keluarActionPerformed

    private void btn_nilai_akhir_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_tambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        btn_nilai_akhir_tambah.setEnabled(false);
        btn_nilai_akhir_ubah.setEnabled(false);
        btn_nilai_akhir_hapus.setEnabled(false);
        btn_nilai_akhir_simpan.setEnabled(true);
        btn_nilai_akhir_batal.setEnabled(true);
        txt_nilai_akhir_persen_absen.setEnabled(true);
        txt_nilai_akhir_persen_tugas.setEnabled(true);
        txt_nilai_akhir_persen_uts.setEnabled(true);
        txt_nilai_akhir_persen_uas.setEnabled(true);
        txt_nilai_akhir_kehadiran.setEnabled(true);
        txt_nilai_akhir_tugas1.setEnabled(true);
        txt_nilai_akhir_tugas2.setEnabled(true);
        txt_nilai_akhir_tugas3.setEnabled(true);
        txt_nilai_akhir_uts.setEnabled(true);
        txt_nilai_akhir_uas.setEnabled(true);
        tampil_kd_mk();
    }//GEN-LAST:event_btn_nilai_akhir_tambahActionPerformed

    private void btn_nilai_akhir_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_simpanActionPerformed
        if(("".equals(txt_nilai_akhir_kehadiran.getText())) || ("".equals(txt_nilai_akhir_tugas1.getText())) || ("".equals(txt_nilai_akhir_tugas2.getText())) || ("".equals(txt_nilai_akhir_tugas3.getText())) || ("".equals(txt_nilai_akhir_uts.getText())) || ("".equals(txt_nilai_akhir_uas.getText()))){
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong");
        } else {
            double jmlhPertemuan = Double.valueOf(txt_nilai_akhir_kehadiran.getText());
            int angkatan = 2021;
            double tugas_satu = Double.valueOf(txt_nilai_akhir_tugas1.getText());
            double tugas_dua = Double.valueOf(txt_nilai_akhir_tugas2.getText());
            double tugas_tiga = Double.valueOf(txt_nilai_akhir_tugas3.getText());
            double uts = Double.valueOf(txt_nilai_akhir_uts.getText());
            double uas = Double.valueOf(txt_nilai_akhir_uas.getText());
            double persen_absen = Double.valueOf(txt_nilai_akhir_persen_absen.getText())/100;
            double persen_tugas = Double.valueOf(txt_nilai_akhir_persen_tugas.getText())/100;
            double persen_uts = Double.valueOf(txt_nilai_akhir_persen_uts.getText())/100;
            double persen_uas = Double.valueOf(txt_nilai_akhir_persen_uas.getText())/100;
            
            //hitung nilai_absen
            double nilai_absen = (jmlhPertemuan/14)*5;

            //hitung nilai_tugas
            double rataTugas = (tugas_satu + tugas_dua + tugas_tiga)/3;
            double nilai_tugas = rataTugas *0.25;

            //hitung nilai_uts
            double nilai_uts = uts * 0.30;

            //hitung nilai_uas
            double nilai_uas = uas * 0.40;

            //hitung nilai_akhir
            double nilai_akhir = nilai_absen + nilai_tugas + nilai_uts + nilai_uas;

            //cari nilai indeks dan keterangan
            String indeks = "";
            String ket = "";
            if((nilai_akhir >= 80) && (nilai_akhir <=100)){
                indeks = "A";
                if(jmlhPertemuan<11){
                    ket = "Tidak Lulus";
                }else{
                    ket = "Lulus";
                }
            }if((nilai_akhir >= 68) && (nilai_akhir <80)){
                indeks = "B";
                if(jmlhPertemuan<11){
                    ket = "Tidak Lulus";
                }else{
                    ket = "Lulus";
                }
            }if((nilai_akhir >= 56) && (nilai_akhir <68)){
                indeks = "C";
                if(jmlhPertemuan<11){
                    ket = "Tidak Lulus";
                }else{
                    ket = "Lulus";
                }
            }if((nilai_akhir >= 45) && (nilai_akhir <56)){
                indeks = "D";
                ket = "Tidak Lulus";
            }if((nilai_akhir >= 0) && (nilai_akhir <45)){
                indeks = "E";
                ket = "Tidak Lulus";
            }
            
            try {
                Class.forName(driver);
                java.sql.Connection kon = DriverManager.getConnection(
                                    database,
                                    user,
                                    pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO t_nilai_akhir"
                                + "(kd_mk, kehadiran, tugas_satu, tugas_dua, tugas_tiga, uts, uas, nilai_absen, nilai_tugas, nilai_uts, nilai_uas, nilai_akhir, indeks, ket, persen_absen, persen_tugas, persen_uts, persen_uas)"
                                + "VALUES "
                                + "( '"+txt_nilai_akhir_kd_mk.getText()+"' ,"
                                + " '"+jmlhPertemuan+"' ,"
                                + " '"+tugas_satu+"' ,"
                                + " '"+tugas_dua+"' ,"
                                + " '"+tugas_tiga+"' ,"
                                + " '"+uts+"' ,"
                                + " '"+uas+"' ,"
                                + " '"+nilai_absen+"' ,"
                                + " '"+nilai_tugas+"' ,"
                                + " '"+nilai_uts+"' ,"
                                + " '"+nilai_uas+"' ,"
                                + " '"+nilai_akhir+"' ,"
                                + " '"+indeks+"' ,"
                                + " '"+ket+"' ,"
                                + " '"+txt_nilai_akhir_persen_absen.getText()+"' ,"
                                + " '"+txt_nilai_akhir_persen_tugas.getText()+"' ,"
                                + " '"+txt_nilai_akhir_persen_uts.getText()+"' ,"
                                + " '"+txt_nilai_akhir_persen_uas.getText()+"' )";
                stt.executeUpdate(SQL);
                data[0] = combo_nilai_akhir_nama_mk.getSelectedItem().toString();
                data[1] = String.valueOf(txt_nilai_akhir_persen_absen.getText());
                data[2] = String.valueOf(txt_nilai_akhir_persen_tugas.getText());
                data[3] = String.valueOf(txt_nilai_akhir_persen_uts.getText());
                data[4] = String.valueOf(txt_nilai_akhir_persen_uas.getText());
                data[5] = String.valueOf(jmlhPertemuan);
                data[6] = String.valueOf(tugas_satu);
                data[7] = String.valueOf(tugas_dua);
                data[8] = String.valueOf(tugas_tiga);
                data[9] = String.valueOf(uts);
                data[10] = String.valueOf(uas);
                data[11] = String.valueOf(nilai_absen);
                data[12] = String.valueOf(nilai_tugas);
                data[13] = String.valueOf(nilai_uts);
                data[14] = String.valueOf(nilai_uas);
                data[15] = String.valueOf(nilai_akhir);
                data[16] = indeks;
                data[17] = ket;
                tableModel.addRow(data);
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_nilai_akhir_simpan.setEnabled(false);
                btn_nilai_akhir_tambah.setEnabled(true);
                nonaktif_teks();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Eror",
                JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        
    }//GEN-LAST:event_btn_nilai_akhir_simpanActionPerformed

    private void combo_nilai_akhir_nama_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_nilai_akhir_nama_mkActionPerformed
        int idx = combo_nilai_akhir_nama_mk.getSelectedIndex();
        
        if(arrMataKuliah.size() > 0){
            kd_mk = arrMataKuliah.get(idx).getNamaMK();
            tampil_kd_mk();
        }
    }//GEN-LAST:event_combo_nilai_akhir_nama_mkActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txt_nilai_akhir_persen_absen.setEnabled(false);
        txt_nilai_akhir_persen_tugas.setEnabled(false);
        txt_nilai_akhir_persen_uts.setEnabled(false);
        txt_nilai_akhir_persen_uas.setEnabled(false);
        txt_nilai_akhir_kehadiran.setEnabled(false);
        txt_nilai_akhir_tugas1.setEnabled(false);
        txt_nilai_akhir_tugas2.setEnabled(false);
        txt_nilai_akhir_tugas3.setEnabled(false);
        txt_nilai_akhir_uts.setEnabled(false);
        txt_nilai_akhir_uas.setEnabled(false);
        btn_nilai_akhir_ubah.setEnabled(false);
        btn_nilai_akhir_hapus.setEnabled(false);
        btn_nilai_akhir_simpan.setEnabled(false);
        btn_nilai_akhir_batal.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void tbl_simulasi_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_simulasi_nilaiMouseClicked
        if(evt.getClickCount()==1){
            tampil_field();
            btn_nilai_akhir_tambah.setEnabled(true);
            btn_nilai_akhir_ubah.setEnabled(true);
            btn_nilai_akhir_hapus.setEnabled(true);
            btn_nilai_akhir_simpan.setEnabled(false);
        }
    }//GEN-LAST:event_tbl_simulasi_nilaiMouseClicked

    private void tbl_simulasi_nilaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_simulasi_nilaiMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_simulasi_nilaiMouseEntered

    private void btn_nilai_akhir_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nilai_akhir_ubahActionPerformed
        if (("".equals(txt_nilai_akhir_kehadiran.getText())) || ("".equals(txt_nilai_akhir_tugas1.getText())) || ("".equals(txt_nilai_akhir_tugas2.getText())) || ("".equals(txt_nilai_akhir_tugas3.getText())) || ("".equals(txt_nilai_akhir_uts.getText())) || ("".equals(txt_nilai_akhir_uas.getText()))){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan dilengkapi", "Error!", JOptionPane.CANCEL_OPTION);
        } else {
            String data[] = new String[19];
            double jmlhPertemuan = Double.valueOf(txt_nilai_akhir_kehadiran.getText());
            int angkatan = 2021;
            double tugas_satu = Double.valueOf(txt_nilai_akhir_tugas1.getText());
            double tugas_dua = Double.valueOf(txt_nilai_akhir_tugas2.getText());
            double tugas_tiga = Double.valueOf(txt_nilai_akhir_tugas3.getText());
            double uts = Double.valueOf(txt_nilai_akhir_uts.getText());
            double uas = Double.valueOf(txt_nilai_akhir_uas.getText());
            double persen_absen = Double.valueOf(txt_nilai_akhir_persen_absen.getText())/100;
            double persen_tugas = Double.valueOf(txt_nilai_akhir_persen_tugas.getText())/100;
            double persen_uts = Double.valueOf(txt_nilai_akhir_persen_uts.getText())/100;
            double persen_uas = Double.valueOf(txt_nilai_akhir_persen_uas.getText())/100;
            
            //hitung nilai_absen
            double nilai_absen = (jmlhPertemuan/14)*5;

            //hitung nilai_tugas
            double rataTugas = (tugas_satu + tugas_dua + tugas_tiga)/3;
            double nilai_tugas = rataTugas *0.25;

            //hitung nilai_uts
            double nilai_uts = uts * 0.30;

            //hitung nilai_uas
            double nilai_uas = uas * 0.40;

            //hitung nilai_akhir
            double nilai_akhir = nilai_absen + nilai_tugas + nilai_uts + nilai_uas;

            //cari nilai indeks dan keterangan
            String indeks = "";
            String ket = "";
            if((nilai_akhir >= 80) && (nilai_akhir <=100)){
                indeks = "A";
                if(jmlhPertemuan<11){
                    ket = "Tidak Lulus";
                }else{
                    ket = "Lulus";
                }
            }if((nilai_akhir >= 68) && (nilai_akhir <80)){
                indeks = "B";
                if(jmlhPertemuan<11){
                    ket = "Tidak Lulus";
                }else{
                    ket = "Lulus";
                }
            }if((nilai_akhir >= 56) && (nilai_akhir <68)){
                indeks = "C";
                if(jmlhPertemuan<11){
                    ket = "Tidak Lulus";
                }else{
                    ket = "Lulus";
                }
            }if((nilai_akhir >= 45) && (nilai_akhir <56)){
                indeks = "D";
                ket = "Tidak Lulus";
            }if((nilai_akhir >= 0) && (nilai_akhir <45)){
                indeks = "E";
                ket = "Tidak Lulus";
            }
            
            try {
                Class.forName(driver);
                java.sql.Connection kon = DriverManager.getConnection(
                                    database,
                                    user,
                                    pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE t_nilai_akhir "
                        + "SET kehadiran = '"+txt_nilai_akhir_kehadiran.getText()+"', "
                        + "tugas_satu = '"+txt_nilai_akhir_tugas1.getText()+"', "
                        + "tugas_dua = '"+txt_nilai_akhir_tugas2.getText()+"', "
                        + "tugas_tiga = '"+txt_nilai_akhir_tugas3.getText()+"', "
                        + "uts = '"+txt_nilai_akhir_uts.getText()+"', "
                        + "uas = '"+txt_nilai_akhir_uas.getText()+"', "
                        + "nilai_absen = '"+nilai_absen+"', "
                        + "nilai_tugas = '"+nilai_tugas+"', "
                        + "nilai_uts = '"+nilai_uts+"', "
                        + "nilai_uas = '"+nilai_uas+"', "
                        + "nilai_akhir = '"+nilai_akhir+"', "
                        + "indeks = '"+indeks+"', "
                        + "ket = '"+ket+"', "
                        + "persen_absen = '"+txt_nilai_akhir_persen_absen.getText()+"', "
                        + "persen_tugas = '"+txt_nilai_akhir_persen_tugas.getText()+"', "
                        + "persen_uts = '"+txt_nilai_akhir_persen_uts.getText()+"', "
                        + "persen_uas = '"+txt_nilai_akhir_persen_uas.getText()+"'"
                        + "WHERE kd_mk = '"+txt_nilai_akhir_kd_mk.getText()+"';";
                stt.executeUpdate(SQL);
                data[0] = combo_nilai_akhir_nama_mk.getSelectedItem().toString();
                data[1] = String.valueOf(txt_nilai_akhir_persen_absen.getText());
                data[2] = String.valueOf(txt_nilai_akhir_persen_tugas.getText());
                data[3] = String.valueOf(txt_nilai_akhir_persen_uts.getText());
                data[4] = String.valueOf(txt_nilai_akhir_persen_uas.getText());
                data[5] = String.valueOf(jmlhPertemuan);
                data[6] = String.valueOf(tugas_satu);
                data[7] = String.valueOf(tugas_dua);
                data[8] = String.valueOf(tugas_tiga);
                data[9] = String.valueOf(uts);
                data[10] = String.valueOf(uas);
                data[11] = String.valueOf(nilai_absen);
                data[12] = String.valueOf(nilai_tugas);
                data[13] = String.valueOf(nilai_uts);
                data[14] = String.valueOf(nilai_uas);
                data[15] = String.valueOf(nilai_akhir);
                data[16] = indeks;
                data[17] = ket;
                tableModel.removeRow(row);
                tableModel.addRow(data);
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_nilai_akhir_ubah.setEnabled(false);
                btn_nilai_akhir_tambah.setEnabled(true);
                btn_nilai_akhir_hapus.setEnabled(false);
                nonaktif_teks();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Eror",
                JOptionPane.INFORMATION_MESSAGE);
            }    
        }
    }//GEN-LAST:event_btn_nilai_akhir_ubahActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_nilai_akhir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_nilai_akhir_batal;
    private javax.swing.JButton btn_nilai_akhir_hapus;
    private javax.swing.JButton btn_nilai_akhir_keluar;
    private javax.swing.JButton btn_nilai_akhir_simpan;
    private javax.swing.JButton btn_nilai_akhir_tambah;
    private javax.swing.JButton btn_nilai_akhir_ubah;
    private javax.swing.JButton btn_nilai_akhir_ubah1;
    private javax.swing.JComboBox<String> combo_nilai_akhir_nama_mk;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_jdl_nilai_akhir;
    private javax.swing.JLabel lbl_nilai_akhir_kd_mk;
    private javax.swing.JLabel lbl_nilai_akhir_kehadiran;
    private javax.swing.JLabel lbl_nilai_akhir_nama_mk;
    private javax.swing.JLabel lbl_nilai_akhir_persen_absen;
    private javax.swing.JLabel lbl_nilai_akhir_persen_tugas;
    private javax.swing.JLabel lbl_nilai_akhir_persen_uas;
    private javax.swing.JLabel lbl_nilai_akhir_persen_uts;
    private javax.swing.JLabel lbl_nilai_akhir_pertemuan;
    private javax.swing.JLabel lbl_nilai_akhir_tugas1;
    private javax.swing.JLabel lbl_nilai_akhir_tugas2;
    private javax.swing.JLabel lbl_nilai_akhir_tugas3;
    private javax.swing.JLabel lbl_nilai_akhir_uas;
    private javax.swing.JLabel lbl_nilai_akhir_uts;
    private javax.swing.JLabel lbl_persen_absen;
    private javax.swing.JLabel lbl_persen_tugas;
    private javax.swing.JLabel lbl_persen_uas;
    private javax.swing.JLabel lbl_persen_uts;
    private javax.swing.JTable tbl_simulasi_nilai;
    private javax.swing.JTextField txt_nilai_akhir_kd_mk;
    private javax.swing.JTextField txt_nilai_akhir_kehadiran;
    private javax.swing.JTextField txt_nilai_akhir_persen_absen;
    private javax.swing.JTextField txt_nilai_akhir_persen_tugas;
    private javax.swing.JTextField txt_nilai_akhir_persen_uas;
    private javax.swing.JTextField txt_nilai_akhir_persen_uts;
    private javax.swing.JTextField txt_nilai_akhir_tugas1;
    private javax.swing.JTextField txt_nilai_akhir_tugas2;
    private javax.swing.JTextField txt_nilai_akhir_tugas3;
    private javax.swing.JTextField txt_nilai_akhir_uas;
    private javax.swing.JTextField txt_nilai_akhir_uts;
    // End of variables declaration//GEN-END:variables
}
