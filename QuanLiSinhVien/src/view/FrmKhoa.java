package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.TextField;
import java.util.ArrayList;
import java.awt.Label;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Button;

import bean.Khoa;
import bo.Khoabo;
import dao.Coso;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmKhoa extends JFrame {

	private JPanel contentPane;
	private JTable table;
	TextField txtmakhoa = new TextField();
	TextField txttenkhoa = new TextField();

	Khoabo khbo = new Khoabo();
	ArrayList<Khoa> ds;

	void NapBang(ArrayList<Khoa> ds) {
		DefaultTableModel mh = new DefaultTableModel();
		int stt = 0;
		String[] td = { "STT", "Ma Khoa", "Ten Khoa" };
		mh.setColumnIdentifiers(td);
		for (Khoa kh : ds) {
			Object[] t = new Object[3];
			t[0] = ++stt;
			t[1] = kh.getMaKhoa();
			t[2] = kh.getTenKhoa();
			mh.addRow(t);
		}
		table.setModel(mh);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmKhoa frame = new FrmKhoa();
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
	public FrmKhoa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Quan li Khoa");
					new Coso().KetNoi();
					ds = khbo.getKhoa();
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 959, 711);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		txtmakhoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txttenkhoa.requestFocus();
				}
			}
		});

		txtmakhoa.setBounds(307, 142, 265, 21);
		contentPane.add(txtmakhoa);

		txttenkhoa.setBounds(307, 197, 265, 21);
		contentPane.add(txttenkhoa);

		Label label = new Label("Ma Khoa ");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(183, 142, 110, 21);
		contentPane.add(label);

		Label label_1 = new Label("Ten Khoa");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(183, 197, 110, 21);
		contentPane.add(label_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(122, 344, 702, 297);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach Khoa", null, scrollPane, null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d = table.getSelectedRow(); // lay hang.
				txtmakhoa.setText(table.getValueAt(d, 1).toString());
				txttenkhoa.setText(table.getValueAt(d, 2).toString());
			}
		});
		scrollPane.setViewportView(table);

		Button button = new Button("Them");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int kt = khbo.Them(txtmakhoa.getText(), txttenkhoa.getText());
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
		button.setBounds(166, 287, 67, 21);
		contentPane.add(button);

		Button button_1 = new Button("Xoa");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int choice = JOptionPane.showConfirmDialog(null, "Ban co muon xoa ?");
					if (choice == 0) {
						int kt = khbo.Xoa(txtmakhoa.getText());
						if (kt == 0)
							JOptionPane.showMessageDialog(null, "Ko xoa duoc");
						else
							JOptionPane.showMessageDialog(null, "Da xoa");
						NapBang(ds);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		button_1.setBounds(293, 287, 67, 21);
		contentPane.add(button_1);

		Button button_2 = new Button("Sua");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int kt = khbo.Sua(txtmakhoa.getText(), txttenkhoa.getText());
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
		button_2.setBounds(433, 287, 67, 21);
		contentPane.add(button_2);

		Button button_3 = new Button("Tim kiem");
		button_3.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String key = JOptionPane.showInputDialog("Nhap Ma Khoa hoac Ten Khoa");
					NapBang(khbo.TimKiem(key));
					if (khbo.TimKiem(key).size() == 0) {
						// Thong bao neu ko tim thay.
						JOptionPane.showMessageDialog(null, "Khong tim thay !!");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3.setBounds(574, 287, 67, 21);
		contentPane.add(button_3);

		Button button_4 = new Button("Hien thi");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapBang(ds);
			}
		});
		button_4.setFont(new Font("Dialog", Font.BOLD, 14));
		button_4.setBounds(720, 287, 67, 21);
		contentPane.add(button_4);

		Label label_2 = new Label("QUAN LI KHOA");
		label_2.setFont(new Font("Dialog", Font.BOLD, 22));
		label_2.setBounds(380, 32, 233, 21);
		contentPane.add(label_2);
	}
}
