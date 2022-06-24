package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.SinhVien;
import bo.SinhVienbo;
import bean.LopHocPhan;
import bo.LopHocPhanbo;
import bo.Khoabo;
import bean.Khoa;
import bean.HocPhan;
import bo.HocPhanbo;
import bean.Diem;
import bean.GiaoVien;
import bo.Diembo;
import bo.GiaoVienbo;
import dao.Coso;
import java.awt.TextField;
import java.util.ArrayList;
import java.awt.Label;
import java.awt.Button;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Choice;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmDiem extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Choice choicemakhoa = new Choice();
	Choice choicemhp = new Choice();
	Choice choicemlhp = new Choice();
	TextField txtmsv = new TextField();
	TextField txtht = new TextField();
	TextField txtdcc = new TextField();
	TextField txtdkt = new TextField();
	TextField txttimkiem = new TextField();
	TextField txtdc = new TextField();
	TextField txtdkthp = new TextField();
	TextField txtdt = new TextField();
	TextField txttgv = new TextField();
	Choice choicetinh = new Choice();

	Khoabo khbo = new Khoabo();
	HocPhanbo hpbo = new HocPhanbo();
	LopHocPhanbo lhpbo = new LopHocPhanbo();
	SinhVienbo svbo = new SinhVienbo();
	Diembo diembo = new Diembo();
	GiaoVienbo gvbo = new GiaoVienbo();
	ArrayList<GiaoVien> dsgv;
	ArrayList<Diem> ds;
	ArrayList<SinhVien> dssv;
	ArrayList<LopHocPhan> dslhp;

	void NapBang(ArrayList<Diem> ds, ArrayList<SinhVien> dssv) {
		DefaultTableModel mh = new DefaultTableModel();
		int stt = 0;
		String[] td = { "STT", "MaSV", "Ho ten", "DiemCC", "DiemKT", "DiemThi", "DiemKTHP", "DiemChu" };
		mh.setColumnIdentifiers(td);
		for (Diem d : ds) {
			if (d.getMaLopHP().equalsIgnoreCase(choicemlhp.getSelectedItem().toString())) {
				Object[] t = new Object[8];
				t[0] = ++stt;
				t[1] = d.getMaSV();
				for (SinhVien sv : dssv) {
					if (sv.getMaSV().equals(d.getMaSV())) {
						t[2] = sv.getHoTen();
					}
				}
				t[3] = d.getDiemCC();
				t[4] = d.getDiemKT();
				t[5] = d.getDiemThi();
				t[6] = d.getDiemKTHP();
				t[7] = d.getDiemChu();
				mh.addRow(t);
			}

		}
		table.setModel(mh);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDiem frame = new FrmDiem();
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
	public FrmDiem() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Quan li Diem");
					new Coso().KetNoi();
					ds = diembo.getDiem();
					dssv = svbo.getSinhVien();
					dslhp = lhpbo.getLopHocPhan();
					dsgv = gvbo.getGiaoVien();
					for (Khoa kh : khbo.getKhoa()) {
						choicemakhoa.add(kh.getMaKhoa());

					}

					for (HocPhan hp : hpbo.getHocPhan()) {
						choicemhp.add(hp.getMaHocPhan());

					}

					for (LopHocPhan lhp : lhpbo.getLopHocPhan()) {
						choicemlhp.add(lhp.getMaLopHP());

					}

					choicemakhoa.select(0);
					for (HocPhan hp : hpbo.getHocPhan()) {
						if (hp.getMaKhoa().equals(choicemakhoa.getSelectedItem())) {
							choicemhp.select(hp.getMaHocPhan());
							for (LopHocPhan lhp : lhpbo.getLopHocPhan()) {
								if (lhp.getMaHocPhan().equals(choicemhp.getSelectedItem())) {
									choicemlhp.select(lhp.getMaLopHP());
									break;
								}
							}
							break;
						}

					}

					NapBang(ds, dssv);

					for (LopHocPhan lhp : dslhp) {
						if (lhp.getMaLopHP().equalsIgnoreCase(choicemlhp.getSelectedItem().toString())) {
							for (GiaoVien gv : dsgv) {
								if (gv.getMaGiaoVien().equalsIgnoreCase(lhp.getMaGiaoVien())) {
									txttgv.setText(gv.getHoTen());
								}
							}
						}
					}

					choicetinh.add("Diem QTHT 30% + Diem Thi 70%");
					choicetinh.add("Diem QTHT 40% + Diem Thi 60%");
					choicetinh.add("Diem QTHT 50% + Diem Thi 50%");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1640, 964);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txttimkiem.setBounds(22, 175, 161, 21);
		contentPane.add(txttimkiem);

		Label label = new Label("Nhap MaSV");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(22, 135, 117, 21);
		contentPane.add(label);

		Button button = new Button("Tim kiem SV");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msv = txttimkiem.getText();
				Boolean ok = true;
				for (SinhVien sv : dssv) {
					if (sv.getMaSV().equalsIgnoreCase(msv)) {
						ok = false;
						txtmsv.setText(sv.getMaSV());
						txtht.setText(sv.getHoTen());
					}
				}
				if (ok) {
					JOptionPane.showMessageDialog(null, "Khong tim thay MaSV");
				}
			}
		});
		button.setBounds(22, 215, 97, 21);
		contentPane.add(button);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(22, 502, 1556, 414);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach sinh vien lop hoc phan", null, scrollPane, null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d = table.getSelectedRow(); // lay hang.
				txtmsv.setText(table.getValueAt(d, 1).toString());
				txtht.setText(table.getValueAt(d, 2).toString());
				txtdcc.setText(table.getValueAt(d, 3).toString());
				txtdkt.setText(table.getValueAt(d, 4).toString());
				txtdt.setText(table.getValueAt(d, 5).toString());
				txtdkthp.setText(table.getValueAt(d, 6).toString());

				if (table.getValueAt(d, 7) == null) {
					txtdc.setText("Chua xep loai");
				} else {
					txtdc.setText(table.getValueAt(d, 7).toString());
				}

			}
		});
		scrollPane.setViewportView(table);
		choicemakhoa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String makhoa = choicemakhoa.getSelectedItem().toString();
					choicemhp.removeAll();
					for (HocPhan hp : hpbo.TimHocPhan(makhoa)) {
						choicemhp.add(hp.getMaHocPhan());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		choicemakhoa.setBounds(393, 89, 175, 18);
		contentPane.add(choicemakhoa);
		choicemhp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String mahp = choicemhp.getSelectedItem().toString();
					choicemlhp.removeAll();
					for (LopHocPhan lhp : lhpbo.TimLopHocPhan(mahp)) {
						choicemlhp.add(lhp.getMaLopHP());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		choicemhp.setBounds(393, 178, 175, 18);
		contentPane.add(choicemhp);
		choicemlhp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				NapBang(ds, dssv);
				for (LopHocPhan lhp : dslhp) {
					if (lhp.getMaLopHP().equalsIgnoreCase(choicemlhp.getSelectedItem().toString())) {
						for (GiaoVien gv : dsgv) {
							if (gv.getMaGiaoVien().equalsIgnoreCase(lhp.getMaGiaoVien())) {
								txttgv.setText(gv.getHoTen());
							}
						}
					}
				}
			}
		});

		choicemlhp.setBounds(393, 269, 175, 18);
		contentPane.add(choicemlhp);

		Label label_1 = new Label("Ma Khoa");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(270, 89, 105, 21);
		contentPane.add(label_1);

		Label label_1_1 = new Label("Ma Hoc Phan");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1_1.setBounds(270, 175, 117, 21);
		contentPane.add(label_1_1);

		Label label_1_2 = new Label("Ma LopHP");
		label_1_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1_2.setBounds(270, 269, 105, 21);
		contentPane.add(label_1_2);

		txtmsv.setBounds(846, 86, 168, 21);
		contentPane.add(txtmsv);

		Label label_2 = new Label("Ma SV");
		label_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2.setBounds(723, 86, 86, 21);
		contentPane.add(label_2);

		Label label_2_1 = new Label("Ho ten");
		label_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2_1.setBounds(723, 135, 86, 21);
		contentPane.add(label_2_1);

		txtht.setBounds(846, 135, 168, 21);
		contentPane.add(txtht);

		Label label_2_2 = new Label("Diem CC");
		label_2_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2_2.setBounds(723, 190, 97, 21);
		contentPane.add(label_2_2);
		TextField txttimkiem = new TextField();
		txtdcc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdkt.requestFocus();
				}
			}
		});

		txtdcc.setBounds(846, 190, 168, 21);
		contentPane.add(txtdcc);

		Label label_2_3 = new Label("Diem KT");
		label_2_3.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2_3.setBounds(723, 246, 97, 21);
		contentPane.add(label_2_3);
		txtdkt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdt.requestFocus();
				}
			}
		});

		txtdkt.setBounds(846, 246, 168, 21);
		contentPane.add(txtdkt);

		choicetinh.setBounds(1314, 190, 212, 18);
		contentPane.add(choicetinh);

		Label label_3 = new Label("Tinh theo");
		label_3.setFont(new Font("Dialog", Font.BOLD, 14));
		label_3.setBounds(1161, 190, 105, 21);
		contentPane.add(label_3);

		Button button_1 = new Button("Tinh DiemKTHP");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (choicetinh.getSelectedItem().toString().contains("40%")) {
						diembo.chamDiem(choicemlhp.getSelectedItem().toString(), 40);
						NapBang(ds, dssv);
					} else if (choicetinh.getSelectedItem().toString().contains("50%")) {
						diembo.chamDiem(choicemlhp.getSelectedItem().toString(), 50);
						NapBang(ds, dssv);
					} else if (choicetinh.getSelectedItem().toString().contains("30%")) {
						diembo.chamDiem(choicemlhp.getSelectedItem().toString(), 30);
						NapBang(ds, dssv);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_1.setBounds(1161, 246, 119, 21);
		contentPane.add(button_1);
		txtdkthp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdc.requestFocus();
				}
			}
		});

		txtdkthp.setBounds(1314, 86, 212, 21);
		contentPane.add(txtdkthp);

		Label label_2_4 = new Label("Diem KTHP");
		label_2_4.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2_4.setBounds(1161, 86, 119, 21);
		contentPane.add(label_2_4);

		txtdc.setBounds(1314, 135, 212, 21);
		contentPane.add(txtdc);

		Label label_2_5 = new Label("Diem Chu");
		label_2_5.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2_5.setBounds(1161, 135, 119, 21);
		contentPane.add(label_2_5);

		Button button_3 = new Button("Them");
		button_3.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int kt = diembo.Them(choicemlhp.getSelectedItem(), txtmsv.getText());
					if (kt == 0)
						JOptionPane.showMessageDialog(null, "Sinh vien da ton tai !");
					else
						JOptionPane.showMessageDialog(null, "Da Them");
					NapBang(ds, dssv);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3.setBounds(343, 457, 67, 21);
		contentPane.add(button_3);

		Button button_3_1 = new Button("Sua");
		button_3_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int kt = diembo.Sua(choicemlhp.getSelectedItem(), txtmsv.getText(),
							Integer.parseInt(txtdcc.getText()), Double.parseDouble(txtdkt.getText()),
							Double.parseDouble(txtdt.getText()));
					if (kt == 0)
						JOptionPane.showMessageDialog(null, "Khong sua duoc");
					else
						JOptionPane.showMessageDialog(null, "Da sua");
					NapBang(ds, dssv);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3_1.setBounds(548, 457, 67, 21);
		contentPane.add(button_3_1);

		Button button_3_2 = new Button("Xoa");
		button_3_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int choice = JOptionPane.showConfirmDialog(null, "Ban co muon xoa ?");
					if (choice == 0) {
						int kt = diembo.Xoa(choicemlhp.getSelectedItem(), txtmsv.getText());
						if (kt == 0)
							JOptionPane.showMessageDialog(null, "Ko xoa duoc");
						else
							JOptionPane.showMessageDialog(null, "Da xoa");
						NapBang(ds, dssv);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3_2.setBounds(742, 457, 67, 21);
		contentPane.add(button_3_2);

		Button button_3_3 = new Button("Tim kiem");
		button_3_3.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String key = JOptionPane.showInputDialog("Nhap MaSV hoac Ma LopHP");
					NapBang(diembo.TimKiem(key), dssv);
					if (diembo.TimKiem(key).size() == 0) {
						// Thong bao neu ko tim thay.
						JOptionPane.showMessageDialog(null, "Khong tim thay !!");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3_3.setBounds(950, 457, 67, 21);
		contentPane.add(button_3_3);
		txtdt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdkthp.requestFocus();
				}
			}
		});

		txtdt.setBounds(846, 309, 168, 21);
		contentPane.add(txtdt);

		Label label_2_3_1 = new Label("Diem Thi");
		label_2_3_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2_3_1.setBounds(723, 309, 97, 21);
		contentPane.add(label_2_3_1);

		txttgv.setBounds(393, 343, 175, 21);
		contentPane.add(txttgv);

		Label label_2_3_1_1 = new Label("Giao vien");
		label_2_3_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2_3_1_1.setBounds(270, 343, 105, 21);
		contentPane.add(label_2_3_1_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(492, 32, 49, 14);
		contentPane.add(lblNewLabel);

		Label label_4 = new Label("QUAN LI DIEM LOP HOC PHAN");
		label_4.setFont(new Font("Dialog", Font.BOLD, 22));
		label_4.setBounds(562, 20, 501, 21);
		contentPane.add(label_4);
	}
}
