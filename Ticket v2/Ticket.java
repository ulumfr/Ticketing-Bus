import java.util.*;
import java.io.*;

public class Ticket {
	String record, tempUser, tempPass, fileLogin = "login.txt", filePesan = "pesan.txt";
	int cek, batas = 5, to = 0, y = 1, z = 0, end = 0, x;
	boolean login = false, ticket = true;

	int available[] = new int[6];
	int ticketI[][] = new int[100][3];
	String ticketS[][] = new String[100][3];
	double ticketD[][] = new double[100][3];
	double pay[] = new double[20];
	double change[] = new double[20];

	Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		Ticket t = new Ticket();

		try {
			t.bus();
		} catch (IOException e) {
			System.err.println("Error");
		}

	}

	void bus() throws IOException {
		System.out.println("\nLogin Admin!\n");
		login();
	}

	void menu() throws IOException {
		System.out.println("\n============================================");
		System.out.println("=== BUS RESERVATION AND TICKETING SYSTEM ===");
		System.out.println("============================================");
		System.out.println("=== [1] TUJUAN \t\t\t\t ===");
		System.out.println("=== [2] TICKET \t\t\t\t ===");
		System.out.println("=== [3] TAGIHAN \t\t\t ===");
		System.out.println("=== [4] DISPLAY \t\t\t ===");
		System.out.println("=== [5] EXIT \t\t\t\t ===");
		System.out.println("============================================");
		System.out.println("============================================");

		System.out.print("Input Pilihan : ");

		switch (input.nextInt()) {
			case 1:
				daftarHarga();
				break;
			case 2:
				penumpang();
				break;
			case 3:
				System.out.println("3");
				menu();
				break;
			case 4:
				System.out.println("4");
				menu();
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("\n\nMohon Maaf Pilihan Anda Salah!\n");
				menu();
		}
	}

	void available() {
		for (int kosong = 1; kosong <= batas; kosong++) {
			available[kosong] = 20;
		}
	}

	void login() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileLogin));

		try {
			System.out.print("Enter Username : ");
			tempUser = input.nextLine();
			System.out.print("Enter Password : ");
			tempPass = input.nextLine();

			available();

			while ((record = br.readLine()) != null) {
				String data[] = record.split(",");

				if (tempUser.equals(data[0]) && tempPass.equals(data[1])) {
					menu();
					login = true;
				}
			}

			if (!login) {
				System.out.println("\nInvalid User or Password!\n");
			}
		} catch (IOException e) {
			System.err.println("Error");
		}

		br.close();
	}

	void daftarHarga() throws IOException {
		System.out.println("\n==========================================");
		System.out.println("==\tTUJUAN \t  |  HARGA  |  KURSI  \t==");
		System.out.println("==========================================");
		System.out.println("== 1. Jakarta\t  | Rp80000 |   " + available[1] + "\t==");
		System.out.println("== 2. Malang\t  | Rp30000 |   " + available[2] + "\t==");
		System.out.println("== 3. Lamongan\t  | Rp15000 |   " + available[3] + "\t==");
		System.out.println("== 4. Surabaya\t  | Rp10000 |   " + available[4] + "\t==");
		System.out.println("== 5. Gresik\t  | Rp5000  |   " + available[5] + "\t==");
		System.out.println("==========================================");
		System.out.println("MAHASISWA MENDAPAT DISKON 20%!!!");

		lanjutTransaksi();
	}

	void lanjutTransaksi() throws IOException {
		System.out.print("\nLanjutkan Transaksi [y/n] ? ");
		char backMenu = input.next().charAt(0);

		if (backMenu == 'y' || backMenu == 'Y') {
			menu();
		} else if (backMenu == 'n' || backMenu == 'N') {
			System.out.println("\nTerima Kasih!!!");
			System.exit(0);
		} else {
			System.out.println("\nInputan Salah");
			lanjutTransaksi();
		}
	}

	void penumpang() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader(filePesan));
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePesan));

		available();
		do {
			ticket = false;

			try {
				for (x = 1; x == 1;) {
					if ((available[batas] == 0)) {
						System.out.println("Maaf, Kursi sudah penuh untuk semua tujuan!");
						x = 0;
					} else {
						for (x = 1; x == 1;) {
							System.out.println("\n=== DATA DIRI PENUMPANG ===");
							System.out.printf("\nMasukan Nama : ");
							ticketS[z][0] = in.readLine();
							x = 0;

							while ((record = br.readLine()) != null) {
								String data[] = record.split(",");

								for (int l = 0; l < z; l++) {
									if (ticketS[l][0].equals(data[0]) && ticketS[z][0].equals(data[1])) {
										System.out.println("Maaf, Nama pelanggan sudah ada di database!");
										x = 1;
									}
								}

							}
							// for (int l = 0; l < z; l++) {
							// if (ticketS[l][0].equalsIgnoreCase(ticketS[z][0])) {
							// System.out.println("Maaf, Nama pelanggan sudah ada di database!");
							// x = 1;
							// }
							// }
						}

						for (x = 1; x == 1;) {
							System.out.print("Masukan Tujuan [Nomer] : ");
							to = Integer.parseInt(in.readLine());

							if (to < 1 || to > 5) {
								System.out.println("Invalid Input!");
								x = 1;
							}

							for (int d = 1; d <= 5; d++) {
								if (to == d) {
									if (available[to] == 0) {
										System.out.println("Maaf, Kursi kami sudah full/tidak tersedia!");
										x = 1;
									}
									x = 0;
								}
							}
						}

						String dest[] = { " ", "Jakarta", "Malang", "Lamongan", "Surabaya", "Gresik" };
						double harga[] = { 0, 80000, 30000, 15000, 10000, 5000 };

						ticketS[z][1] = dest[to];
						ticketD[z][0] = harga[to];

						for (x = 1; x == 1;) {
							System.out.print("Ada berapa penumpang ? ");
							ticketI[z][0] = Integer.parseInt(in.readLine());

							for (int p = 1; p <= 5; p++) {
								if (to == p) {
									// print = 1;
									available[to] = available[to] - ticketI[z][0];

									if (available[to] < 0) {
										System.out.print("Maaf, Kami tidak punya kursi yang kosong untuk"
												+ ticketI[z][0] + " orang\n");
										available[to] = available[to] + ticketI[z][0];
										System.out.print("Kami hanya punya " + available[to] + " kursi yang kosong\n");
										x = 1;
										// print = 0;
									} else {
										x = 0;
									}
								}
							}
						}

						for (x = 1; x == 1;) {
							System.out.print("Ada berapa penumpang yang dapat diskon ? ");
							ticketI[z][1] = Integer.parseInt(in.readLine());

							if (ticketI[z][1] > ticketI[z][0]) {
								System.out.println("Invalid Input!");
								System.out.println("No. of Passengers are only " + ticketI[z][0] + "!");
								x = 1;
							} else {
								break;
							}
						}

						String tulis = String.format("%s, %s, %s, %s, %s\n", ticketS[z][0], ticketS[z][1],
								ticketD[z][0], ticketI[z][0], ticketI[z][1]);

						bw.write(tulis);
						bw.close();

						
					}
					lanjutTransaksi();
				}

			} catch (IOException e) {
				System.err.println("Error");
			}
		} while (ticket);

	}
}
