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
import java.awt.Button;
import java.awt.Label;

import bean.LopHocPhan;
import bo.LopHocPhanbo;
import bean.HocPhan;
import bo.HocPhanbo;
import dao.Coso;

import java.awt.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmLopHocPhan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	TextField txtmlhp = new TextField();
	TextField txttlhp = new TextField();
	TextField txtstc = new TextField();
	TextField txtnbd = new TextField();
	TextField txtmgv = new TextField();
	TextField txtmhp = new TextField();
	List listmhp = new List();
	HocPhanbo hpbo = new HocPhanbo();
	LopHocPhanbo lhpbo = new LopHocPhanbo();
	ArrayList<LopHocPhan> ds;

	void NapBang(ArrayList<LopHocPhan> ds) {
		DefaultTableModel mh = new DefaultTableModel();
		int stt = 0;
		String[] td = { "STT", "Ma Lop Hoc Phan", "Ten Lop Hoc Phan", "So tin chi", "Ngay bat dau", "Ma Hoc Phan",
				"Ma Giao Vien" };
		mh.setColumnIdentifiers(td);
		for (LopHocPhan lhp : ds) {
			Object[] t = new Object[7];
			t[0] = ++stt;
			t[1] = lhp.getMaLopHP();
			t[2] = lhp.getTenLopHP();
			t[3] = lhp.getSoTinChi();
			SimpleDateFormat dd = new SimpleDateFormat("dd/MM/yyyy");
			String nbd = dd.format(lhp.getNgayBatDau());
			t[4] = nbd;
			t[5] = lhp.getMaHocPhan();
			t[6] = lhp.getMaGiaoVien();
			mh.addRow(t);
		}
		table.setModel(mh);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLopHocPhan frame = new FrmLopHocPhan();
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
	public FrmLopHocPhan() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Quan li Lop hoc phan");
					new Coso().KetNoi();
					ds = lhpbo.getLopHocPhan();
					NapBang(ds);

					for (HocPhan kh : hpbo.getHocPhan()) {
						listmhp.add(kh.getMaHocPhan());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1416, 1009);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(338, 373, 1006, 576);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach Lop hoc phan", null, scrollPane, null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d = table.getSelectedRow(); // lay hang.
				txtmlhp.setText(table.getValueAt(d, 1).toString());
				txttlhp.setText(table.getValueAt(d, 2).toString());
				txtstc.setText(table.getValueAt(d, 3).toString());
				txtnbd.setText(table.getValueAt(d, 4).toString());
				txtmhp.setText(table.getValueAt(d, 5).toString());
				txtmgv.setText(table.getValueAt(d, 6).toString());

			}
		});
		scrollPane.setViewportView(table);
		txtmlhp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txttlhp.requestFocus();
				}
			}
		});

		txtmlhp.setBounds(581, 119, 182, 21);
		contentPane.add(txtmlhp);
		txttlhp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtstc.requestFocus();
				}
			}
		});

		txttlhp.setBounds(581, 168, 182, 21);
		contentPane.add(txttlhp);
		txtstc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtnbd.requestFocus();
				}
			}
		});

		txtstc.setBounds(581, 215, 182, 21);
		contentPane.add(txtstc);
		txtnbd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtmhp.requestFocus();
				}
			}
		});

		txtnbd.setBounds(581, 268, 182, 21);
		contentPane.add(txtnbd);

		txtmgv.setBounds(1034, 168, 188, 21);
		contentPane.add(txtmgv);

		Button button = new Button("Them");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date ngaybd = null;
					if (txtnbd.getText().contains("/")) {
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
						String ns = sdf2.format(sdf1.parse(txtnbd.getText()));
						ngaybd = sdf2.parse(ns);
					} else {
						SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
						ngaybd = dd.parse(txtnbd.getText());
					}

					int kt = lhpbo.Them(txtmlhp.getText(), txttlhp.getText(), Integer.parseInt(txtstc.getText()),
							ngaybd, txtmhp.getText(), txtmgv.getText());
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
		button.setBounds(527, 333, 67, 21);
		contentPane.add(button);

		Button button_1 = new Button("Sua");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date ngaybd = null;
					if (txtnbd.getText().contains("/")) {
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
						String ns = sdf2.format(sdf1.parse(txtnbd.getText()));
						ngaybd = sdf2.parse(ns);
					} else {
						SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
						ngaybd = dd.parse(txtnbd.getText());
					}

					int kt = lhpbo.Sua(txtmlhp.getText(), txttlhp.getText(), Integer.parseInt(txtstc.getText()), ngaybd,
							txtmhp.getText(), txtmgv.getText());
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
		button_1.setBounds(642, 333, 67, 21);
		contentPane.add(button_1);

		Button button_2 = new Button("Xoa");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int choice = JOptionPane.showConfirmDialog(null, "Ban co muon xoa ?");
					if (choice == 0) {
						int kt = lhpbo.Xoa(txtmlhp.getText());
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
		button_2.setBounds(780, 333, 67, 21);
		contentPane.add(button_2);

		Button button_3 = new Button("Tim kiem");
		button_3.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String key = JOptionPane.showInputDialog("Nhap MaLopHP, TenLopHP, MaHP hoac MaGV");
					NapBang(lhpbo.TimKiem(key));
					if (lhpbo.TimKiem(key).size() == 0) {
						// Thong bao neu ko tim thay.
						JOptionPane.showMessageDialog(null, "Khong tim thay !!");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3.setBounds(936, 333, 67, 21);
		contentPane.add(button_3);

		Button button_4 = new Button("Hien thi");
		button_4.setFont(new Font("Dialog", Font.BOLD, 14));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapBang(ds);
			}
		});
		button_4.setBounds(1091, 333, 67, 21);
		contentPane.add(button_4);

		Label label = new Label("Ma LopHP");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(436, 119, 112, 21);
		contentPane.add(label);

		Label label_1 = new Label("Ten LopHP");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(436, 168, 112, 21);
		contentPane.add(label_1);

		Label label_2 = new Label("So tin chi");
		label_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2.setBounds(436, 215, 112, 21);
		contentPane.add(label_2);

		Label label_3 = new Label("Ngay bat dau");
		label_3.setFont(new Font("Dialog", Font.BOLD, 14));
		label_3.setBounds(436, 268, 127, 21);
		contentPane.add(label_3);

		Label label_4 = new Label("Ma Hoc Phan");
		label_4.setFont(new Font("Dialog", Font.BOLD, 14));
		label_4.setBounds(897, 119, 127, 21);
		contentPane.add(label_4);

		Label label_5 = new Label("Ma Giao Vien");
		label_5.setFont(new Font("Dialog", Font.BOLD, 14));
		label_5.setBounds(897, 168, 127, 21);
		contentPane.add(label_5);
		txtmhp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtmgv.requestFocus();
				}
			}
		});

		txtmhp.setBounds(1034, 119, 188, 21);
		contentPane.add(txtmhp);
		listmhp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String mhp = listmhp.getSelectedItem().toString();
					NapBang(lhpbo.TimHocPhan(mhp));
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		listmhp.setBounds(27, 168, 271, 781);
		contentPane.add(listmhp);

		Label label_6 = new Label("Ma Hoc Phan");
		label_6.setFont(new Font("Dialog", Font.BOLD, 14));
		label_6.setBounds(30, 119, 159, 21);
		contentPane.add(label_6);

		Label label_7 = new Label("QUAN LI LOP HOC PHAN");
		label_7.setFont(new Font("Dialog", Font.BOLD, 22));
		label_7.setBounds(661, 33, 412, 21);
		contentPane.add(label_7);
	}
}
