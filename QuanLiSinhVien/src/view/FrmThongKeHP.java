package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Label;
import java.util.ArrayList;

import dao.Coso;
import bean.HocPhan;
import bo.HocPhanbo;
import bean.LopHocPhan;
import bo.LopHocPhanbo;
import bean.GiaoVien;
import bo.GiaoVienbo;
import bean.Diem;
import bo.Diembo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrmThongKeHP extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Choice choicemhp = new Choice();
	Choice choicemlhp = new Choice();

	HocPhanbo hpbo = new HocPhanbo();
	LopHocPhanbo lhpbo = new LopHocPhanbo();
	Diembo diembo = new Diembo();
	GiaoVienbo gvbo = new GiaoVienbo();
	ArrayList<HocPhan> dshp;
	ArrayList<LopHocPhan> dslhp;
	ArrayList<GiaoVien> dsgv;
	ArrayList<Diem> dsd;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThongKeHP frame = new FrmThongKeHP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmThongKeHP() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Thong ke Hoc phan");
					new Coso().KetNoi();
					dshp = hpbo.getHocPhan();
					dslhp = lhpbo.getLopHocPhan();
					dsgv = gvbo.getGiaoVien();
					dsd = diembo.getDiem();

					for (HocPhan hp : hpbo.getHocPhan()) {
						choicemhp.add(hp.getMaHocPhan());
					}

					for (LopHocPhan lhp : lhpbo.getLopHocPhan()) {
						choicemlhp.add(lhp.getMaLopHP());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1401, 872);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(87, 377, 1215, 422);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Thong ke", null, scrollPane, null);

		table = new JTable();
		scrollPane.setViewportView(table);
		choicemhp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String mahp = choicemhp.getSelectedItem().toString();
					choicemlhp.removeAll();

					for (LopHocPhan lhp : lhpbo.TimHocPhan(mahp)) {
						choicemlhp.add(lhp.getMaLopHP());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		choicemhp.setBounds(396, 149, 170, 18);
		contentPane.add(choicemhp);

		choicemlhp.setBounds(902, 149, 189, 18);
		contentPane.add(choicemlhp);

		Button button = new Button("Thong ke HP");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel mh = new DefaultTableModel();
				String[] td = { "Ma Hoc Phan", "Ten Hoc Phan", "So LopHP", "So Giao vien giang day", "So Sinh vien" };
				mh.setColumnIdentifiers(td);
				int soLHP = 0, soGv = 0, soSv = 0;

				for (HocPhan hp : dshp) {
					if (hp.getMaHocPhan().equals(choicemhp.getSelectedItem())) {
						Object[] t = new Object[5];
						t[0] = choicemhp.getSelectedItem();
						t[1] = hp.getTenHocPhan();
						for (LopHocPhan lhp : dslhp) {
							if (lhp.getMaHocPhan().equals(choicemhp.getSelectedItem())) {
								++soLHP;
								for (GiaoVien gv : dsgv) {
									if (gv.getMaGiaoVien().equalsIgnoreCase(lhp.getMaGiaoVien())) {
										++soGv;
									}
								}
								for (Diem d : dsd) {
									if (d.getMaLopHP().equals(lhp.getMaLopHP())) {
										++soSv;
									}
								}
							}
						}
						t[2] = soLHP;
						t[3] = soGv;
						t[4] = soSv;
						mh.addRow(t);
					}
				}
				table.setModel(mh);
			}
		});
		button.setBounds(522, 315, 97, 21);
		contentPane.add(button);

		Button button_1 = new Button("Thong ke LopHP");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel mh = new DefaultTableModel();
				String[] td = { "Ma Lop Hoc Phan", "Ten Lop Hoc Phan", "Giao vien", "Si so", "So SV diem A",
						"So SV diem B", "So SV diem C", "So SV diem D", "So SV diem F", "% SV qua mon",
						"% SV hoc lai" };
				mh.setColumnIdentifiers(td);
				int siSo = 0, dA = 0, dB = 0, dC = 0, dD = 0, dF = 0;

				for (LopHocPhan lhp : dslhp) {
					if (lhp.getMaLopHP().equals(choicemlhp.getSelectedItem())) {
						Object[] t = new Object[11];
						t[0] = choicemlhp.getSelectedItem();
						t[1] = lhp.getTenLopHP();
						for (GiaoVien gv : dsgv) {
							if (gv.getMaGiaoVien().equalsIgnoreCase(lhp.getMaGiaoVien())) {
								t[2] = gv.HoTen;
							}
						}
						for (Diem d : dsd) {
							if (d.getMaLopHP().equals(choicemlhp.getSelectedItem())) {
								++siSo;
								if (d.getDiemChu().equals("A"))
									++dA;
								else if (d.getDiemChu().equals("B"))
									++dB;
								else if (d.getDiemChu().equals("C"))
									++dC;
								else if (d.getDiemChu().equals("D"))
									++dD;
								else if (d.getDiemChu().equals("F"))
									++dF;
							}
						}
						t[3] = siSo;
						t[4] = dA;
						t[5] = dB;
						t[6] = dC;
						t[7] = dD;
						t[8] = dF;
						double qm = ((double) (dA + dB + dC + dD) * 100) / (double) (siSo);
						double ptqm = (double) Math.round(qm * 100d) / 100d;
						t[9] = ptqm;
						t[10] = 100.00 - ptqm;
						mh.addRow(t);
					}
				}

				table.setModel(mh);
			}
		});
		button_1.setBounds(799, 315, 115, 21);
		contentPane.add(button_1);

		Label label = new Label("Ma Hoc Phan");
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBounds(220, 146, 170, 21);
		contentPane.add(label);

		Label label_1 = new Label("Ma LopHP");
		label_1.setFont(new Font("Dialog", Font.BOLD, 16));
		label_1.setBounds(775, 146, 121, 21);
		contentPane.add(label_1);

		Button button_2 = new Button("Thong ke");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel mh = new DefaultTableModel();
				int stt = 0;

				String[] td = { "STT", "Ma Hoc Phan", "Ten Hoc Phan", "So LopHP", "So Giao vien giang day", "So Sinh vien" };
				mh.setColumnIdentifiers(td);
				for (HocPhan hp : dshp) {
					int soLHP = 0, soGv = 0, soSv = 0;

					Object[] t = new Object[6];
					t[0] = ++stt;
					t[1] = hp.getMaHocPhan();
					t[2] = hp.getTenHocPhan();
					for (LopHocPhan lhp : dslhp) {
						if (lhp.getMaHocPhan().equals(hp.getMaHocPhan())) {
							++soLHP;
							for (GiaoVien gv : dsgv) {
								if (gv.getMaGiaoVien().equalsIgnoreCase(lhp.getMaGiaoVien())) {
									++soGv;
								}
							}
							for (Diem d : dsd) {
								if (d.getMaLopHP().equals(lhp.getMaLopHP())) {
									++soSv;
								}
							}
						}
					}
					t[3] = soLHP;
					t[4] = soGv;
					t[5] = soSv;
					mh.addRow(t);

				}
				table.setModel(mh);
			}
		});
		button_2.setBounds(279, 315, 67, 21);
		contentPane.add(button_2);
		
		Label label_2 = new Label("THONG KE HOC PHAN");
		label_2.setFont(new Font("Dialog", Font.BOLD, 22));
		label_2.setBounds(576, 28, 350, 21);
		contentPane.add(label_2);
	}
}
