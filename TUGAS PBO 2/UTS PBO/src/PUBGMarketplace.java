import java.util.*;

// Class dasar untuk semua akun
class Akun {
    // Enkapsulasi: atribut private
    private String id;
    private String username;
    private int level;
    private double harga;
    private boolean terjual;
    // Tambahkan skin untuk semua akun
    private ArrayList<String> skinSenjata;
    private ArrayList<String> skinBaju;

    // Constructor
    public Akun(String id, String username, int level, double harga) {
        this.id = id;
        this.username = username;
        this.level = level;
        this.harga = harga;
        this.terjual = false;
        this.skinSenjata = new ArrayList<>();
        this.skinBaju = new ArrayList<>();
    }
    
    // Constructor dengan parameter skin
    public Akun(String id, String username, int level, double harga, 
               ArrayList<String> skinSenjata, ArrayList<String> skinBaju) {
        this.id = id;
        this.username = username;
        this.level = level;
        this.harga = harga;
        this.terjual = false;
        this.skinSenjata = skinSenjata;
        this.skinBaju = skinBaju;
    }

    // Getter dan Setter (Enkapsulasi)
    public String getId() { return id; }
    public String getUsername() { return username; }
    public int getLevel() { return level; }
    public double getHarga() { return harga; }
    public boolean isTerjual() { return terjual; }
    public ArrayList<String> getSkinSenjata() { return skinSenjata; }
    public ArrayList<String> getSkinBaju() { return skinBaju; }

    public void setHarga(double harga) { this.harga = harga; }
    public void setTerjual(boolean terjual) { this.terjual = terjual; }
    
    public void addSkinSenjata(String skin) {
        skinSenjata.add(skin);
    }
    
    public void addSkinBaju(String skin) {
        skinBaju.add(skin);
    }

    // Method untuk menampilkan informasi akun
    public void displayInfo() {
        System.out.println("\nID: " + id);
        System.out.println("Username: " + username);
        System.out.println("Level: " + level);
        System.out.println("Harga: Rp " + String.format("%,.2f", harga));
        System.out.println("Status: " + (terjual ? "Terjual" : "Tersedia"));
        
        // Tambahkan display untuk skin pada semua akun
        if (!skinSenjata.isEmpty()) {
            System.out.println("Skin Senjata:");
            for (String skin : skinSenjata) {
                System.out.println("  - " + skin);
            }
        }
        
        if (!skinBaju.isEmpty()) {
            System.out.println("Skin Baju:");
            for (String skin : skinBaju) {
                System.out.println("  - " + skin);
            }
        }
    }
}

// Pewarisan: Class AkunPremium mewarisi Akun
class AkunPremium extends Akun {
    private int jumlahSkin;
    private boolean royalPass;

    public AkunPremium(String id, String username, int level, double harga, int jumlahSkin, boolean royalPass) {
        super(id, username, level, harga);  // Memanggil constructor parent class
        this.jumlahSkin = jumlahSkin;
        this.royalPass = royalPass;
    }
    
    // Constructor tambahan dengan parameter skin
    public AkunPremium(String id, String username, int level, double harga, int jumlahSkin, boolean royalPass, 
                      ArrayList<String> skinSenjata, ArrayList<String> skinBaju) {
        super(id, username, level, harga, skinSenjata, skinBaju);  // Memanggil constructor parent class dengan skin
        this.jumlahSkin = jumlahSkin;
        this.royalPass = royalPass;
    }

    public int getJumlahSkin() { return jumlahSkin; }
    public boolean hasRoyalPass() { return royalPass; }

    // Polimorfisme: Override method dari class parent
    @Override
    public void displayInfo() {
        super.displayInfo();  // Memanggil method parent yang sudah termasuk informasi skin
        System.out.println("Jumlah Skin: " + jumlahSkin);
        System.out.println("Royal Pass: " + (royalPass ? "Ya" : "Tidak"));
    }
}

// Class untuk transaksi
class Transaksi {
    private String idTransaksi;
    private String idAkun;
    private String pembeli;
    private Date tanggal;
    private double nominal;

    public Transaksi(String idTransaksi, String idAkun, String pembeli, double nominal) {
        this.idTransaksi = idTransaksi;
        this.idAkun = idAkun;
        this.pembeli = pembeli;
        this.tanggal = new Date();
        this.nominal = nominal;
    }

    public String getIdTransaksi() { return idTransaksi; }
    public String getIdAkun() { return idAkun; }
    public String getPembeli() { return pembeli; }
    public Date getTanggal() { return tanggal; }
    public double getNominal() { return nominal; }

    public void displayInfo() {
        System.out.println("\n===== DETAIL TRANSAKSI =====");
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("ID Akun: " + idAkun);
        System.out.println("Pembeli: " + pembeli);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Nominal: Rp " + String.format("%,.2f", nominal));
        System.out.println("==========================");
    }
}

// Class untuk marketplace akun PUBG
class MarketplacePUBG {
    private ArrayList<Akun> daftarAkun;
    private ArrayList<Transaksi> daftarTransaksi;
    private Scanner scanner;

    public MarketplacePUBG() {
        daftarAkun = new ArrayList<>();
        daftarTransaksi = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        // Data awal untuk testing dengan skin untuk semua akun
        
        // Akun biasa pertama dengan skin
        ArrayList<String> skinSenjata1 = new ArrayList<>();
        skinSenjata1.add("M416 - Glacier");
        skinSenjata1.add("AKM - Blood & Bones");
        
        ArrayList<String> skinBaju1 = new ArrayList<>();
        skinBaju1.add("X-Suit");
        skinBaju1.add("Mythic Fashion");
        
        daftarAkun.add(new Akun("A001", "JekiRajaGula", 75, 5000000, skinSenjata1, skinBaju1));
        
        // Akun premium
        ArrayList<String> skinSenjata2 = new ArrayList<>();
        skinSenjata2.add("AWM - Dragon Hunter");
        skinSenjata2.add("Kar98k - Polar Bear");
        
        ArrayList<String> skinBaju2 = new ArrayList<>();
        skinBaju2.add("Pharaoh X-Suit");
        skinBaju2.add("Godzilla Collaboration");
        
        daftarAkun.add(new AkunPremium("A002", "LofePVNK", 100, 2500000, 50, true, skinSenjata2, skinBaju2));
        
        // Akun biasa kedua dengan skin
        ArrayList<String> skinSenjata3 = new ArrayList<>();
        skinSenjata3.add("UZI - Neon Destroyer");
        skinSenjata3.add("M762 - Toxic Rip");
        
        ArrayList<String> skinBaju3 = new ArrayList<>();
        skinBaju3.add("Golden Pharaoh X-Suit");
        skinBaju3.add("Blood Raven X-Suit");
        
        daftarAkun.add(new Akun("A003", "OMKEGAMS", 60, 3000000, skinSenjata3, skinBaju3));
        
        // Akun premium kedua
        ArrayList<String> skinSenjata4 = new ArrayList<>();
        skinSenjata4.add("M24 - Pharaoh");
        skinSenjata4.add("Groza - Invader");
        
        ArrayList<String> skinBaju4 = new ArrayList<>();
        skinBaju4.add("Mecha Bruiser Set");
        skinBaju4.add("Poseidon X-Suit");
        
        daftarAkun.add(new AkunPremium("A004", "LofeYNS", 95, 1200000, 40, true, skinSenjata4, skinBaju4));
        
        // Akun BattleKing dengan skin
        ArrayList<String> skinSenjata5 = new ArrayList<>();
        skinSenjata5.add("M14 - Glacier");
        skinSenjata5.add("AKM - Hellfire");
        
        ArrayList<String> skinBaju5 = new ArrayList<>();
        skinBaju5.add("Mummy Pharaoh Set");
        skinBaju5.add("Avalanche X-Suit");
        
        daftarAkun.add(new AkunPremium("A005", "JekiBantaiPoke", 80, 1750000, 4, false, skinSenjata5, skinBaju5));
    }

    // Method untuk menampilkan menu utama
    public void showMenu() {
        int pilihan = 0;
        
        do {
            System.out.println("\n===== MARKETPLACE AKUN PUBG =====");
            System.out.println("1. Tampilkan Semua Akun");
            System.out.println("2. Cari Akun");
            System.out.println("3. Urutkan Akun");
            System.out.println("4. Tambah Akun Baru");
            System.out.println("5. Beli Akun");
            System.out.println("6. Lihat Riwayat Transaksi");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer
                
                switch (pilihan) {
                    case 1:
                        displayAllAkun();
                        break;
                    case 2:
                        searchAkun();
                        break;
                    case 3:
                        sortAkun();
                        break;
                    case 4:
                        addAkun();
                        break;
                    case 5:
                        beliAkun();
                        break;
                    case 6:
                        displayTransaksi();
                        break;
                    case 0:
                        System.out.println("Terima kasih telah menggunakan aplikasi kami!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid! Masukkan angka.");
                scanner.nextLine(); // Membersihkan buffer
            }
            
        } while (pilihan != 0);
    }
    
    // Menampilkan semua akun
    private void displayAllAkun() {
        System.out.println("\n===== DAFTAR AKUN PUBG =====");
        if (daftarAkun.isEmpty()) {
            System.out.println("Tidak ada akun yang tersedia.");
            return;
        }
        
        for (Akun akun : daftarAkun) {
            akun.displayInfo();
            System.out.println("----------------------------");
        }
    }
    
    // Searching: Mencari akun berdasarkan ID atau username
    private void searchAkun() {
        System.out.println("\n===== CARI AKUN =====");
        System.out.println("1. Cari berdasarkan ID");
        System.out.println("2. Cari berdasarkan Username");
        System.out.print("Pilihan: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer
        
        if (pilihan == 1) {
            System.out.print("Masukkan ID Akun: ");
            String id = scanner.nextLine();
            
            boolean found = false;
            for (Akun akun : daftarAkun) {
                if (akun.getId().equalsIgnoreCase(id)) {
                    System.out.println("\nAkun ditemukan:");
                    akun.displayInfo();
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("Akun dengan ID " + id + " tidak ditemukan.");
            }
        } else if (pilihan == 2) {
            System.out.print("Masukkan Username: ");
            String username = scanner.nextLine();
            
            boolean found = false;
            for (Akun akun : daftarAkun) {
                if (akun.getUsername().toLowerCase().contains(username.toLowerCase())) {
                    if (!found) {
                        System.out.println("\nAkun yang ditemukan:");
                        found = true;
                    }
                    akun.displayInfo();
                    System.out.println("----------------------------");
                }
            }
            
            if (!found) {
                System.out.println("Tidak ada akun dengan username yang mengandung '" + username + "'.");
            }
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }
    
    // Sorting: Mengurutkan akun berdasarkan harga atau level
    private void sortAkun() {
        System.out.println("\n===== URUTKAN AKUN =====");
        System.out.println("1. Urutkan berdasarkan Harga (Terendah - Tertinggi)");
        System.out.println("2. Urutkan berdasarkan Harga (Tertinggi - Terendah)");
        System.out.println("3. Urutkan berdasarkan Level (Terendah - Tertinggi)");
        System.out.println("4. Urutkan berdasarkan Level (Tertinggi - Terendah)");
        System.out.print("Pilihan: ");
        int pilihan = scanner.nextInt();
        
        switch (pilihan) {
            case 1:
                Collections.sort(daftarAkun, Comparator.comparing(Akun::getHarga));
                System.out.println("Akun diurutkan berdasarkan harga terendah.");
                break;
            case 2:
                Collections.sort(daftarAkun, Comparator.comparing(Akun::getHarga).reversed());
                System.out.println("Akun diurutkan berdasarkan harga tertinggi.");
                break;
            case 3:
                Collections.sort(daftarAkun, Comparator.comparing(Akun::getLevel));
                System.out.println("Akun diurutkan berdasarkan level terendah.");
                break;
            case 4:
                Collections.sort(daftarAkun, Comparator.comparing(Akun::getLevel).reversed());
                System.out.println("Akun diurutkan berdasarkan level tertinggi.");
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                return;
        }
        
        displayAllAkun();
    }
    
    // Method untuk menambah akun baru
    private void addAkun() {
        System.out.println("\n===== TAMBAH AKUN =====");
        System.out.println("1. Akun Biasa");
        System.out.println("2. Akun Premium");
        System.out.print("Pilihan: ");
        int jenis = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer
        
        // Generate ID baru
        String newId = "A" + String.format("%03d", daftarAkun.size() + 1);
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Level: ");
        int level = scanner.nextInt();
        
        System.out.print("Harga (Rp): ");
        double harga = scanner.nextDouble();
        scanner.nextLine(); // Membersihkan buffer
        
        // Untuk semua jenis akun, tambahkan input skin
        ArrayList<String> skinSenjata = new ArrayList<>();
        ArrayList<String> skinBaju = new ArrayList<>();
        
        System.out.println("Tambahkan Skin Senjata? (y/n): ");
        String tambahSkinSenjata = scanner.nextLine();
        
        if (tambahSkinSenjata.equalsIgnoreCase("y")) {
            System.out.println("Masukkan skin senjata (ketik 'selesai' untuk berhenti):");
            while (true) {
                String skin = scanner.nextLine();
                if (skin.equalsIgnoreCase("selesai")) {
                    break;
                }
                skinSenjata.add(skin);
            }
        }
        
        System.out.println("Tambahkan Skin Baju? (y/n): ");
        String tambahSkinBaju = scanner.nextLine();
        
        if (tambahSkinBaju.equalsIgnoreCase("y")) {
            System.out.println("Masukkan skin baju (ketik 'selesai' untuk berhenti):");
            while (true) {
                String skin = scanner.nextLine();
                if (skin.equalsIgnoreCase("selesai")) {
                    break;
                }
                skinBaju.add(skin);
            }
        }
        
        if (jenis == 1) {
            // Buat akun biasa dengan skin
            daftarAkun.add(new Akun(newId, username, level, harga, skinSenjata, skinBaju));
            System.out.println("Akun biasa berhasil ditambahkan dengan ID: " + newId);
        } else if (jenis == 2) {
            System.out.print("Jumlah Skin: ");
            int jumlahSkin = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer
            
            System.out.print("Royal Pass (1: Ya, 0: Tidak): ");
            int rp = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer
            boolean royalPass = (rp == 1);
            
            // Buat akun premium dengan skin
            daftarAkun.add(new AkunPremium(newId, username, level, harga, jumlahSkin, royalPass, skinSenjata, skinBaju));
            System.out.println("Akun premium berhasil ditambahkan dengan ID: " + newId);
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }
    
    // Method untuk membeli akun
    private void beliAkun() {
        System.out.println("\n===== BELI AKUN =====");
        System.out.print("Masukkan ID Akun yang ingin dibeli: ");
        String id = scanner.nextLine();
        
        Akun targetAkun = null;
        for (Akun akun : daftarAkun) {
            if (akun.getId().equalsIgnoreCase(id)) {
                targetAkun = akun;
                break;
            }
        }
        
        if (targetAkun == null) {
            System.out.println("Akun dengan ID " + id + " tidak ditemukan.");
            return;
        }
        
        if (targetAkun.isTerjual()) {
            System.out.println("Maaf, akun ini sudah terjual.");
            return;
        }
        
        targetAkun.displayInfo();
        System.out.print("\nApakah Anda yakin ingin membeli akun ini? (y/n): ");
        String konfirmasi = scanner.nextLine();
        
        if (konfirmasi.equalsIgnoreCase("y")) {
            System.out.print("Masukkan nama pembeli: ");
            String pembeli = scanner.nextLine();
            
            // Generate ID transaksi
            String idTransaksi = "T" + String.format("%03d", daftarTransaksi.size() + 1);
            
            // Buat transaksi baru
            Transaksi transaksi = new Transaksi(idTransaksi, targetAkun.getId(), pembeli, targetAkun.getHarga());
            daftarTransaksi.add(transaksi);
            
            // Update status akun
            targetAkun.setTerjual(true);
            
            System.out.println("\nSelamat! Akun berhasil dibeli.");
            transaksi.displayInfo();
        } else {
            System.out.println("Pembelian dibatalkan.");
        }
    }
    
    // Method untuk menampilkan riwayat transaksi
    private void displayTransaksi() {
        System.out.println("\n===== RIWAYAT TRANSAKSI =====");
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        
        for (Transaksi transaksi : daftarTransaksi) {
            transaksi.displayInfo();
        }
    }
}

// Main class
public class PUBGMarketplace {
    public static void main(String[] args) {
        System.out.println("Selamat datang di Aplikasi Marketplace Akun PUBG");
        
        // Membuat objek marketplace dan menampilkan menu
        MarketplacePUBG marketplace = new MarketplacePUBG();
        marketplace.showMenu();
    }
}