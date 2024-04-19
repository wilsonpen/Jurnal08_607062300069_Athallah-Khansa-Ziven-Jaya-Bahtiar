import java.util.*;

class Mahasiswa {
    String nim;
    String kelas;
    String nama;
    List<Double> nilai;

    public Mahasiswa(String nim, String kelas, String nama) {
        this.nim = nim;
        this.kelas = kelas;
        this.nama = nama;
        this.nilai = new ArrayList<>();
    }

    public void tambahNilai(double nilai) {
        this.nilai.add(nilai);
    }

    public double hitungRataRataNilai() {
        if (nilai.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (double nilai : this.nilai) {
            total += nilai;
        }
        return total / nilai.size();
    }
}

class DatabaseMahasiswa {
    Map<String, Mahasiswa> daftarMahasiswa;

    public DatabaseMahasiswa() {
        daftarMahasiswa = new HashMap<>();
    }

    public void tambahMahasiswa(String nim, String kelas, String nama) {
        if (nim.isEmpty() || kelas.isEmpty() || nama.isEmpty()) {
            System.out.println("Error: NIM, kelas, atau nama tidak boleh kosong.");
            return;
        }
        if (!daftarMahasiswa.containsKey(nim)) {
            Mahasiswa mahasiswa = new Mahasiswa(nim, kelas, nama);
            daftarMahasiswa.put(nim, mahasiswa);
            System.out.println("Mahasiswa dengan NIM " + nim + " berhasil ditambahkan.");
        } else {
            System.out.println("Error: Mahasiswa dengan NIM " + nim + " sudah ada.");
        }
    }

    public void hapusMahasiswa(String nim) {
        if (nim.isEmpty()) {
            System.out.println("Error: NIM tidak boleh kosong.");
            return;
        }
        if (daftarMahasiswa.containsKey(nim)) {
            daftarMahasiswa.remove(nim);
            System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus.");
        } else {
            System.out.println("Error: Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    public void tambahNilai(String nim, double nilai) {
        if (nim.isEmpty()) {
            System.out.println("Error: NIM tidak boleh kosong.");
            return;
        }
        if (daftarMahasiswa.containsKey(nim)) {
            daftarMahasiswa.get(nim).tambahNilai(nilai);
            System.out.println("Nilai ditambahkan untuk mahasiswa dengan NIM " + nim);
        } else {
            System.out.println("Error: Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    public void tampilkanSemuaNilaiDanRataRata() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Tidak ada mahasiswa dalam database.");
            return;
        }
        for (Map.Entry<String, Mahasiswa> entry : daftarMahasiswa.entrySet()) {
            Mahasiswa mahasiswa = entry.getValue();
            System.out.println("NIM: " + mahasiswa.nim);
            System.out.println("Nama: " + mahasiswa.nama);
            System.out.println("Kelas: " + mahasiswa.kelas);
            System.out.println("Nilai: " + mahasiswa.nilai);
            System.out.println("Rata-rata Nilai: " + mahasiswa.hitungRataRataNilai());
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseMahasiswa database = new DatabaseMahasiswa();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Tambah Nilai");
            System.out.println("4. Liat Semua Nilai dan Rata-rata");
            System.out.println("5. Keluar");
            System.out.print("pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();  

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();
                    System.out.print("Masukkan Kelas: ");
                    String kelas = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    database.tambahMahasiswa(nim, kelas, nama);
                    break;
                case 2:
                    System.out.print("Masukkan NIM : ");
                    nim = scanner.nextLine();
                    database.hapusMahasiswa(nim);
                    break;
                case 3:
                    System.out.print("Masukkan NIM: ");
                    nim = scanner.nextLine();
                    System.out.print("Masukkan Nilai: ");
                    double nilai = scanner.nextDouble();
                    database.tambahNilai(nim, nilai);
                    break;
                case 4:
                    database.tampilkanSemuaNilaiDanRataRata();
                    break;
                case 5:
                    System.out.println("Keluar");
                    System.exit(0);
                default:
                    System.out.println("pilih yg benar");
            }
        }
    }
}
