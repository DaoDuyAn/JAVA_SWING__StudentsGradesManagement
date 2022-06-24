package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Choice;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import java.util.ArrayList;
import java.awt.Button;

import dao.Coso;
import bean.GiaoVien;
import bean.Khoa;
import bo.GiaoVienbo;
import bo.Khoabo;
import bean.Lop;
import bo.Lopbo;
import bean.SinhVien;
import bo.SinhVienbo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrmThongKeSiSo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Choice choicemakhoa = new Choice();
	Choice choicemalop = new Choice();

	Khoabo khbo = new Khoabo();
	SinhVienbo svbo = new SinhVienbo();
	Lopbo lhbo = new Lopbo();
	GiaoVienbo gvbo = new GiaoVienbo();
	ArrayList<GiaoVien> dsgv;
	ArrayList<SinhVien> dssv;
	ArrayList<Khoa> dsk;
	ArrayList<Lop> dsl;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThongKeSiSo frame = new FrmThongKeSiSo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmThongKeSiSo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Thong ke sinh vien");
					new Coso().KetNoi();
					dssv = svbo.getSinhVien();
					dsk = khbo.getKhoa();
					dsl = lhbo.getLop();
					dsgv = gvbo.getGiaoVien();

					for (Khoa kh : khbo.getKhoa()) {
						choicemakhoa.add(kh.getMaKhoa());
					}
					for (Lop lh : lhbo.getLop()) {
						choicemalop.add(lh.getMaLop());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1316, 907);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		choicemakhoa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String makhoa = choicemakhoa.getSelectedItem().toString();
					choicemalop.removeAll();
					for (Lop lh : lhbo.TimLop(makhoa)) {
						choicemalop.add(lh.getMaLop());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		choicemakhoa.setBounds(250, 157, 176, 18);
		contentPane.add(choicemakhoa);

		choicemalop.setBounds(624, 160, 176, 18);
		contentPane.add(choicemalop);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(66, 450, 1169, 388);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Thong ke", null, scrollPane, null);

		table = new JTable();
		scrollPane.setViewportView(table);

		Label label = new Label("Ma Khoa");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(147, 154, 97, 21);
		contentPane.add(label);

		Label label_1 = new Label("Ma Lop");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(535, 154, 83, 21);
		contentPane.add(label_1);

		Button button = new Button("Thong ke Lop");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel mh = new DefaultTableModel();
				int nu = 0, nam = 0;
				String[] td = { "Ma Lop", "Ten Lop", "So SV", "So SV Nam", "SV Nam (%)", "So SV Nu", "SV Nu (%)" };
				mh.setColumnIdentifiers(td);
				for (Lop lh : dsl) {
					if (lh.getMaLop().equals(choicemalop.getSelectedItem())) {
						Object[] t = new Object[7];
						t[0] = choicemalop.getSelectedItem();
						t[1] = lh.getTenLop();
						for (SinhVien sv : dssv) {
							if (sv.getMaLop().equals(choicemalop.getSelectedItem())) {
								if (sv.getGioiTinh())
									nam++;
								else
									nu++;
							}
						}
						t[2] = nam + nu;
						t[3] = nam;
						double man = ((double) nam * 100) / (double) (nam + nu);
						double ptnam = (double) Math.round(man * 100d) / 100d;

						t[4] = ptnam;
						t[5] = nu;
						t[6] = 100.00 - ptnam;
						mh.addRow(t);
					}

				}
				table.setModel(mh);
			}
		});
		button.setBounds(1012, 222, 115, 21);
		contentPane.add(button);

		Button button_1 = new Button("Thong ke Khoa");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel mh = new DefaultTableModel();
				int stt = 0;

				String[] td = { "STT", "Ma Khoa", "Ten Khoa", "So lop", "So Giao vien", "So Sinh vien", "So SV Nam",
						"SV Nam (%)", "So SV Nu", "SV Nu (%)" };
				mh.setColumnIdentifiers(td);

				for (Khoa kh : dsk) {
					int soLop = 0, soGv = 0, nam = 0, nu = 0;

					Object[] t = new Object[10];
					t[0] = ++stt;
					t[1] = kh.getMaKhoa();
					t[2] = kh.getTenKhoa();
					for (Lop lh : dsl) {
						if (lh.getMaKhoa().equals(kh.getMaKhoa())) {
							soLop++;
							for (SinhVien sv : dssv) {
								if (sv.getMaLop().equals(lh.MaLop)) {
									if (sv.getGioiTinh())
										nam++;
									else
										nu++;
								}
							}
						}

					}

					for (GiaoVien gv : dsgv) {
						if (gv.getMaKhoa().equals(kh.getMaKhoa())) {
							soGv++;
						}
					}
					t[3] = soLop;
					t[4] = soGv;
					t[5] = nam + nu;
					t[6] = nam;
					double man = ((double) nam * 100) / (double) (nam + nu);
					double ptnam = (double) Math.round(man * 100d) / 100d;
					t[7] = ptnam;
					t[8] = nu;
					t[9] = 100.00 - ptnam;

					mh.addRow(t);
				}
				table.setModel(mh);
			}
		});
		button_1.setBounds(1012, 157, 115, 21);
		contentPane.add(button_1);

		Button button_2 = new Button("Thong ke SV");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel mh = new DefaultTableModel();
				int nam = 0, nu = 0;

				String[] td = { "Tong so Sinh vien", "So SV Nam", "SV Nam (%)", "So SV Nu", "SV Nu (%)" };
				mh.setColumnIdentifiers(td);
				Object[] t = new Object[5];
				for (SinhVien sv : dssv) {
					if (sv.getGioiTinh())
						nam++;
					else
						nu++;
				}
				t[0] = nam + nu;
				t[1] = nam;
				double man = ((double) nam * 100) / (double) (nam + nu);
				double ptnam = (double) Math.round(man * 100d) / 100d;
				t[2] = ptnam;
				t[3] = nu;
				t[4] = 100.00 - ptnam;
				mh.addRow(t);
				table.setModel(mh);
			}
		});
		button_2.setBounds(1012, 289, 115, 21);
		contentPane.add(button_2);

		Label label_2 = new Label("THONG KE SINH VIEN");
		label_2.setFont(new Font("Dialog", Font.BOLD, 22));
		label_2.setBounds(521, 44, 354, 21);
		contentPane.add(label_2);
	}
}
