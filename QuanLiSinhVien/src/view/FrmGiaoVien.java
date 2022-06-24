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
import java.awt.TextField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Button;
import java.awt.List;

import bean.GiaoVien;
import bean.Khoa;
import bo.GiaoVienbo;
import bo.Khoabo;
import dao.Coso;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmGiaoVien extends JFrame {

	private JPanel contentPane;
	private JTable table;
	TextField txtmgv = new TextField();
	TextField txtht = new TextField();
	TextField txtdc = new TextField();
	TextField txtns = new TextField();
	TextField txtqq = new TextField();
	TextField txtdd = new TextField();
	TextField txte = new TextField();
	JCheckBox chckbxNam = new JCheckBox("Nam");
	JCheckBox chckbxNu = new JCheckBox("Nu");
	Choice choicemakhoa = new Choice();
	List listmakhoa = new List();

	Khoabo khbo = new Khoabo();
	GiaoVienbo gvbo = new GiaoVienbo();
	ArrayList<GiaoVien> ds;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	void NapBang(ArrayList<GiaoVien> ds) {
		DefaultTableModel mh = new DefaultTableModel();
		int stt = 0;
		String[] td = { "STT", "Ma Giao Vien", "Ho ten", "Dia chi", "Gioi tinh", "Ngay sinh", "Que quan", "Di dong",
				"Email", "Ma Khoa" };
		mh.setColumnIdentifiers(td);
		for (GiaoVien gv : ds) {
			Object[] t = new Object[10];
			t[0] = ++stt;
			t[1] = gv.getMaGiaoVien();
			t[2] = gv.getHoTen();
			t[3] = gv.getDiaChi();
			if (gv.getGioiTinh()) {
				t[4] = "Nam";
			} else {
				t[4] = "Nu";
			}
			SimpleDateFormat dd = new SimpleDateFormat("dd/MM/yyyy");
			String ns = dd.format(gv.getNgaySinh());
			t[5] = ns;
			t[6] = gv.getQueQuan();
			t[7] = gv.getDiDong();
			t[8] = gv.getEmail();
			t[9] = gv.getMaKhoa();
			mh.addRow(t);
		}
		table.setModel(mh);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGiaoVien frame = new FrmGiaoVien();
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
	public FrmGiaoVien() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Quan li Giao vien");
					new Coso().KetNoi();
					ds = gvbo.getGiaoVien();
					NapBang(ds);
					
					for (Khoa kh : khbo.getKhoa()) {
						choicemakhoa.add(kh.getMaKhoa());
					}
					for (Khoa kh : khbo.getKhoa()) {
						listmakhoa.add(kh.getMaKhoa());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1418, 961);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(287, 407, 1107, 488);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach Giao vien", null, scrollPane, null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d = table.getSelectedRow(); // lay hang.
				txtmgv.setText(table.getValueAt(d, 1).toString());
				txtht.setText(table.getValueAt(d, 2).toString());
				txtdc.setText(table.getValueAt(d, 3).toString());

				if (table.getValueAt(d, 4).toString().equals("Nam")) {
					chckbxNam.setSelected(true);
				} else {
					chckbxNu.setSelected(true);
				}
				txtns.setText(table.getValueAt(d, 5).toString());
				txtqq.setText(table.getValueAt(d, 6).toString());
				txtdd.setText(table.getValueAt(d, 7).toString());
				txte.setText(table.getValueAt(d, 8).toString());
				choicemakhoa.select(table.getValueAt(d, 9).toString());
			}
		});
		scrollPane.setViewportView(table);
		txtmgv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtht.requestFocus();
				}
			}
		});

		txtmgv.setBounds(436, 88, 201, 21);
		contentPane.add(txtmgv);
		txtht.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdc.requestFocus();
				}
			}
		});

		txtht.setBounds(436, 131, 201, 21);
		contentPane.add(txtht);

		txtdc.setBounds(436, 181, 201, 21);
		contentPane.add(txtdc);
		txtns.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtqq.requestFocus();
				}
			}
		});

		txtns.setBounds(839, 88, 201, 21);
		contentPane.add(txtns);
		txtdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txte.requestFocus();
				}
			}
		});

		txtdd.setBounds(839, 185, 201, 21);
		contentPane.add(txtdd);

		txte.setBounds(839, 235, 201, 21);
		contentPane.add(txte);

		choicemakhoa.setBounds(1173, 91, 201, 18);
		contentPane.add(choicemakhoa);

		Label label = new Label("Ma Giao Vien");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(321, 88, 109, 21);
		contentPane.add(label);

		Label label_1 = new Label("Ho Ten");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(321, 131, 85, 21);
		contentPane.add(label_1);

		Label label_2 = new Label("Dia Chi");
		label_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2.setBounds(321, 181, 85, 21);
		contentPane.add(label_2);

		Label label_3 = new Label("Gioi tinh");
		label_3.setFont(new Font("Dialog", Font.BOLD, 14));
		label_3.setBounds(321, 235, 93, 21);
		contentPane.add(label_3);

		Label label_4 = new Label("Ngay sinh");
		label_4.setFont(new Font("Dialog", Font.BOLD, 14));
		label_4.setBounds(740, 88, 93, 21);
		contentPane.add(label_4);

		Label label_5 = new Label("Di Dong");
		label_5.setFont(new Font("Dialog", Font.BOLD, 14));
		label_5.setBounds(740, 181, 93, 21);
		contentPane.add(label_5);

		Label label_6 = new Label("Email");
		label_6.setFont(new Font("Dialog", Font.BOLD, 14));
		label_6.setBounds(740, 235, 60, 21);
		contentPane.add(label_6);

		Label label_7 = new Label("Ma Khoa");
		label_7.setFont(new Font("Dialog", Font.BOLD, 14));
		label_7.setBounds(1094, 88, 73, 21);
		contentPane.add(label_7);

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

					Boolean gt = chckbxNam.isSelected();
					int kt = gvbo.Them(txtmgv.getText(), txtht.getText(), txtdc.getText(), gt, ngaysinh,
							txtqq.getText(), txtdd.getText(), txte.getText(), choicemakhoa.getSelectedItem());

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
		button.setBounds(494, 358, 67, 21);
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

					Boolean gt = chckbxNam.isSelected();
					int kt = gvbo.Sua(txtmgv.getText(), txtht.getText(), txtdc.getText(), gt, ngaysinh, txtqq.getText(),
							txtdd.getText(), txte.getText(), choicemakhoa.getSelectedItem());

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
		button_1.setBounds(645, 358, 67, 21);
		contentPane.add(button_1);

		Button button_2 = new Button("Xoa");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int choice = JOptionPane.showConfirmDialog(null, "Ban co muon xoa ?");
					if (choice == 0) {
						int kt = gvbo.Xoa(txtmgv.getText());
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
		button_2.setBounds(817, 358, 67, 21);
		contentPane.add(button_2);

		Button button_3 = new Button("Tim kiem");
		button_3.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String key = JOptionPane.showInputDialog("Nhap Ma Giao vien hoac Ten Giao vien");
					NapBang(gvbo.TimKiem(key));
					if (gvbo.TimKiem(key).size() == 0) {
						// Thong bao neu ko tim thay.
						JOptionPane.showMessageDialog(null, "Khong tim thay !!");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3.setBounds(995, 358, 67, 21);
		contentPane.add(button_3);

		Button button_4 = new Button("Hien thi");
		button_4.setFont(new Font("Dialog", Font.BOLD, 14));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapBang(ds);
			}
		});
		button_4.setBounds(1173, 358, 67, 21);
		contentPane.add(button_4);
		listmakhoa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String mk = listmakhoa.getSelectedItem().toString();
					NapBang(gvbo.TimKhoa(mk));
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		listmakhoa.setBounds(26, 128, 243, 767);
		contentPane.add(listmakhoa);

		Label label_8 = new Label("Danh sach Ma Khoa");
		label_8.setFont(new Font("Dialog", Font.BOLD, 14));
		label_8.setBounds(27, 88, 206, 21);
		contentPane.add(label_8);

		Label label_5_1 = new Label("Que quan");
		label_5_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_5_1.setBounds(740, 131, 85, 21);
		contentPane.add(label_5_1);
		txtqq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtdd.requestFocus();
				}
			}
		});

		txtqq.setBounds(839, 131, 201, 21);
		contentPane.add(txtqq);

		Label label_9 = new Label("QUAN LI GIAO VIEN");
		label_9.setFont(new Font("Dialog", Font.BOLD, 22));
		label_9.setBounds(594, 20, 355, 21);
		contentPane.add(label_9);
		buttonGroup.add(chckbxNam);

		chckbxNam.setFont(new Font("Dialog", Font.BOLD, 15));
		chckbxNam.setBounds(436, 235, 99, 23);
		contentPane.add(chckbxNam);
		buttonGroup.add(chckbxNu);

		chckbxNu.setFont(new Font("Dialog", Font.BOLD, 15));
		chckbxNu.setBounds(538, 235, 99, 23);
		contentPane.add(chckbxNu);
	}
}
