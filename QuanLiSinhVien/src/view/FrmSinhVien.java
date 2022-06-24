package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Button;

import bean.Lop;
import bean.Khoa;
import bean.SinhVien;
import bo.SinhVienbo;
import bo.Khoabo;
import bo.Lopbo;
import dao.Coso;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmSinhVien extends JFrame {

	private JPanel contentPane;
	private JTable table;
	TextField txtmsv = new TextField();
	TextField txtht = new TextField();
	TextField txtdc = new TextField();
	TextField txtns = new TextField();
	TextField txtqt = new TextField();
	TextField txtqq = new TextField();
	TextField txtdt = new TextField();
	TextField txttg = new TextField();
	TextField txtdd = new TextField();
	TextField txte = new TextField();
	JCheckBox checkBoxNam = new JCheckBox("Nam");
	JCheckBox checkBoxNu = new JCheckBox("Nu");
	Choice choicemalop = new Choice();
	Choice choicemakhoa = new Choice();

	Khoabo khbo = new Khoabo();
	Lopbo lhbo = new Lopbo();
	SinhVienbo svbo = new SinhVienbo();
	ArrayList<SinhVien> ds;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	void NapBang(ArrayList<SinhVien> ds) {
		DefaultTableModel mh = new DefaultTableModel();
		int stt = 0;
		String[] td = { "STT", "Ma Sinh Vien", "Ho ten", "Dia chi", "Gioi tinh", "Ngay sinh", "Quoc tich", "Que quan",
				"Dan toc", "Ton giao", "Di dong", "Email", "Ma Lop" };
		mh.setColumnIdentifiers(td);
		for (SinhVien sv : ds) {
			Object[] t = new Object[14];
			t[0] = ++stt;
			t[1] = sv.getMaSV();
			t[2] = sv.getHoTen();
			t[3] = sv.getDiaChi();
			if (sv.getGioiTinh()) {
				t[4] = "Nam";
			} else {
				t[4] = "Nu";
			}
			SimpleDateFormat dd = new SimpleDateFormat("dd/MM/yyyy");
			String ns = dd.format(sv.getNgaySinh());
			t[5] = ns;
			t[6] = sv.getQuocTich();
			t[7] = sv.getQueQuan();
			t[8] = sv.getDanToc();
			t[9] = sv.getTonGiao();
			t[10] = sv.getDiDong();
			t[11] = sv.getEmail();
			t[12] = sv.getMaLop();
			mh.addRow(t);
		}
		table.setModel(mh);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSinhVien frame = new FrmSinhVien();
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
	public FrmSinhVien() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Quan li Sinh vien");
					new Coso().KetNoi();
					ds = svbo.getSinhVien();
					NapBang(ds);

					for (Khoa kh : khbo.getKhoa()) {
						choicemakhoa.add(kh.getMaKhoa());
					}
					for (Lop lh : lhbo.getLop()) {
						choicemalop.add(lh.getMaLop());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1510, 985);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(55, 508, 1404, 414);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach Sinh vien", null, scrollPane, null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d = table.getSelectedRow(); // lay hang.
				txtmsv.setText(table.getValueAt(d, 1).toString());
				txtht.setText(table.getValueAt(d, 2).toString());
				txtdc.setText(table.getValueAt(d, 3).toString());
				if (table.getValueAt(d, 4).toString().equals("Nam")) {
					checkBoxNam.setSelected(true);
				} else {
					checkBoxNu.setSelected(true);
				}
				txtns.setText(table.getValueAt(d, 5).toString());
				txtqt.setText(table.getValueAt(d, 6).toString());
				txtqq.setText(table.getValueAt(d, 7).toString());
				txtdt.setText(table.getValueAt(d, 8).toString());
				txttg.setText(table.getValueAt(d, 9).toString());
				txtdd.setText(table.getValueAt(d, 10).toString());
				txte.setText(table.getValueAt(d, 11).toString());
				choicemalop.select(table.getValueAt(d, 12).toString());
				try {
					for (Lop lh : lhbo.getLop()) {
						if (lh.getMaLop().equals(table.getValueAt(d, 12).toString())) {
							choicemakhoa.select(lh.getMaKhoa());
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);

		Label label = new Label("THONG TIN SINH VIEN");
		label.setFont(new Font("Dialog", Font.BOLD, 22));
		label.setBounds(589, 22, 340, 21);
		contentPane.add(label);
		txtmsv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtht.requestFocus();
				}
			}
		});

		txtmsv.setBounds(234, 110, 228, 21);
		contentPane.add(txtmsv);
		txtht.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdc.requestFocus();
				}
			}
		});

		txtht.setBounds(234, 154, 228, 21);
		contentPane.add(txtht);

		txtdc.setBounds(234, 201, 228, 21);
		contentPane.add(txtdc);
		txtns.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtqt.requestFocus();
				}
			}
		});

		txtns.setBounds(234, 300, 228, 21);
		contentPane.add(txtns);
		txtqt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtqq.requestFocus();
				}
			}
		});

		txtqt.setBounds(234, 355, 228, 21);
		contentPane.add(txtqt);
		txtqq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdt.requestFocus();
				}
			}
		});

		txtqq.setBounds(728, 110, 228, 21);
		contentPane.add(txtqq);
		txtdt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txttg.requestFocus();
				}
			}
		});

		txtdt.setBounds(728, 154, 228, 21);
		contentPane.add(txtdt);
		txttg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdd.requestFocus();
				}
			}
		});

		txttg.setBounds(728, 201, 228, 21);
		contentPane.add(txttg);
		txtdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txte.requestFocus();
				}
			}
		});

		txtdd.setBounds(728, 251, 228, 21);
		contentPane.add(txtdd);

		txte.setBounds(728, 300, 228, 21);
		contentPane.add(txte);

		Label label_1 = new Label("Ma sinh vien");
		label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1.setBounds(85, 110, 130, 21);
		contentPane.add(label_1);

		Label label_1_1 = new Label("Ho ten");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_1.setBounds(85, 154, 91, 21);
		contentPane.add(label_1_1);

		Label label_1_2 = new Label("Dia Chi");
		label_1_2.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_2.setBounds(85, 201, 91, 21);
		contentPane.add(label_1_2);

		Label label_1_3 = new Label("Gioi tinh");
		label_1_3.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_3.setBounds(85, 251, 99, 21);
		contentPane.add(label_1_3);

		Label label_1_4 = new Label("Ngay sinh");
		label_1_4.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_4.setBounds(85, 300, 130, 21);
		contentPane.add(label_1_4);

		Label label_1_5 = new Label("Quoc tich");
		label_1_5.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_5.setBounds(85, 355, 130, 21);
		contentPane.add(label_1_5);

		Label label_1_6 = new Label("Que quan");
		label_1_6.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_6.setBounds(589, 110, 109, 21);
		contentPane.add(label_1_6);

		Label label_1_7 = new Label("Dan toc");
		label_1_7.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_7.setBounds(589, 154, 109, 21);
		contentPane.add(label_1_7);

		Label label_1_8 = new Label("Ton giao");
		label_1_8.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_8.setBounds(589, 201, 109, 21);
		contentPane.add(label_1_8);

		Label label_1_9 = new Label("Di dong");
		label_1_9.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_9.setBounds(589, 251, 109, 21);
		contentPane.add(label_1_9);

		Label label_1_10 = new Label("Email");
		label_1_10.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1_10.setBounds(589, 300, 109, 21);
		contentPane.add(label_1_10);

		Label label_1_11 = new Label("Ma Lop");
		label_1_11.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1_11.setBounds(1067, 236, 91, 21);
		contentPane.add(label_1_11);

		Button button = new Button("Them");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date ngaysinh = null;
					//if (txtns.getText().contains("/")) {
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
						String ns = sdf2.format(sdf1.parse(txtns.getText()));
						ngaysinh = sdf2.parse(ns);
//					} else {
//						SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
//						ngaysinh = dd.parse(txtns.getText());
//					}

					Boolean gt = checkBoxNam.isSelected();
					int kt = svbo.Them(txtmsv.getText(), txtht.getText(), txtdc.getText(), gt, ngaysinh,
							txtqt.getText(), txtqq.getText(), txtdt.getText(), txttg.getText(), txtdd.getText(),
							txte.getText(), choicemalop.getSelectedItem());

					if (kt == 0)
						JOptionPane.showMessageDialog(null, "Trung ma");
					else
						JOptionPane.showMessageDialog(null, "Da Them");
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button.setBounds(347, 445, 67, 21);
		contentPane.add(button);

		Button button_1 = new Button("Sua");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date ngaysinh = null;
					//if (txtns.getText().contains("/")) {
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
						String ns = sdf2.format(sdf1.parse(txtns.getText()));
						ngaysinh = sdf2.parse(ns);
//					} else {
//						SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
//						ngaysinh = dd.parse(txtns.getText());
//					}

					Boolean gt = checkBoxNam.isSelected();
					int kt = svbo.Sua(txtmsv.getText(), txtht.getText(), txtdc.getText(), gt, ngaysinh, txtqt.getText(),
							txtqq.getText(), txtdt.getText(), txttg.getText(), txtdd.getText(), txte.getText(),
							choicemalop.getSelectedItem());

					if (kt == 0)
						JOptionPane.showMessageDialog(null, "Khong sua duoc");
					else
						JOptionPane.showMessageDialog(null, "Da sua");
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_1.setBounds(506, 445, 67, 21);
		contentPane.add(button_1);

		Button button_2 = new Button("Xoa");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int choice = JOptionPane.showConfirmDialog(null, "Ban co muon xoa ?");
					if (choice == 0) {
						int kt = svbo.Xoa(txtmsv.getText());
						if (kt == 0)
							JOptionPane.showMessageDialog(null, "Khong xoa duoc");
						else
							JOptionPane.showMessageDialog(null, "Da xoa");
						NapBang(ds);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		button_2.setBounds(681, 445, 67, 21);
		contentPane.add(button_2);

		Button button_3 = new Button("Tim kiem");
		button_3.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String key = JOptionPane.showInputDialog("Nhap MaSV, Ho ten hoac Ma Lop");
					NapBang(svbo.TimKiem(key));
					if (svbo.TimKiem(key).size() == 0) {
						// Thong bao neu ko tim thay.
						JOptionPane.showMessageDialog(null, "Khong tim thay !!");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3.setBounds(854, 445, 67, 21);
		contentPane.add(button_3);
		choicemalop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String maLop = choicemalop.getSelectedItem().toString();
					NapBang(svbo.TimLop(maLop));

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		choicemalop.setBounds(1173, 236, 228, 18);
		contentPane.add(choicemalop);

		Label label_2 = new Label("Ma Khoa");
		label_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2.setBounds(1067, 110, 91, 21);
		contentPane.add(label_2);
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

		choicemakhoa.setBounds(1173, 113, 228, 18);
		contentPane.add(choicemakhoa);
		buttonGroup.add(checkBoxNam);

		checkBoxNam.setFont(new Font("Dialog", Font.BOLD, 15));
		checkBoxNam.setBounds(234, 249, 99, 23);
		contentPane.add(checkBoxNam);
		buttonGroup.add(checkBoxNu);

		checkBoxNu.setFont(new Font("Dialog", Font.BOLD, 15));
		checkBoxNu.setBounds(336, 249, 99, 23);
		contentPane.add(checkBoxNu);
	}
}
