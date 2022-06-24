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
import java.awt.Choice;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import dao.Coso;
import bean.Khoa;
import bean.Lop;
import bo.Lopbo;
import bo.Khoabo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmLop extends JFrame {

	private JPanel contentPane;
	private JTable table;
	TextField txtmalop = new TextField();
	TextField txttenlop = new TextField();
	Choice choicemakhoa = new Choice();
	Khoabo khbo = new Khoabo();
	Lopbo lhbo = new Lopbo();
	ArrayList<Lop> ds;

	void NapBang(ArrayList<Lop> ds) {
		DefaultTableModel mh = new DefaultTableModel();
		int stt = 0;
		String[] td = { "STT", "Ma Lop", "Ten Lop", "Ma Khoa" };
		mh.setColumnIdentifiers(td);
		for (Lop lh : ds) {
			Object[] t = new Object[4];
			t[0] = ++stt;
			t[1] = lh.getMaLop();
			t[2] = lh.getTenLop();
			t[3] = lh.getMaKhoa();
			mh.addRow(t);
		}
		table.setModel(mh);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLop frame = new FrmLop();
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
	public FrmLop() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Quan li Lop");
					new Coso().KetNoi();
					ds = lhbo.getLop();
					NapBang(ds);
					for (Khoa kh : khbo.getKhoa()) {
						choicemakhoa.add(kh.getMaKhoa());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 936, 738);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(103, 368, 712, 308);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach Lop", null, scrollPane, null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d = table.getSelectedRow(); // lay hang.
				txtmalop.setText(table.getValueAt(d, 1).toString());
				txttenlop.setText(table.getValueAt(d, 2).toString());
				choicemakhoa.select(table.getValueAt(d, 3).toString());
			}
		});
		scrollPane.setViewportView(table);
		txtmalop.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txttenlop.requestFocus();
				}
			}
		});

		txtmalop.setBounds(235, 116, 276, 21);
		contentPane.add(txtmalop);

		txttenlop.setBounds(235, 175, 276, 21);
		contentPane.add(txttenlop);

		choicemakhoa.setBounds(235, 237, 276, 18);
		contentPane.add(choicemakhoa);

		Label label = new Label("Ma Lop");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(138, 113, 73, 21);
		contentPane.add(label);

		Label label_1 = new Label("Ten Lop");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(138, 172, 73, 21);
		contentPane.add(label_1);

		Label label_2 = new Label("Ma Khoa");
		label_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2.setBounds(138, 234, 73, 21);
		contentPane.add(label_2);

		Button button = new Button("Them");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int kt = lhbo.Them(txtmalop.getText(), txttenlop.getText(), choicemakhoa.getSelectedItem());
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
		button.setBounds(153, 313, 67, 21);
		contentPane.add(button);

		Button button_1 = new Button("Sua");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int kt = lhbo.Sua(txtmalop.getText(), txttenlop.getText(), choicemakhoa.getSelectedItem());
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
		button_1.setBounds(295, 313, 67, 21);
		contentPane.add(button_1);

		Button button_2 = new Button("Xoa");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int choice = JOptionPane.showConfirmDialog(null, "Ban co muon xoa ?");
					if (choice == 0) {
						int kt = lhbo.Xoa(txtmalop.getText());
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
		button_2.setBounds(434, 313, 67, 21);
		contentPane.add(button_2);

		Button button_3 = new Button("Tim kiem");
		button_3.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String key = JOptionPane.showInputDialog("Nhap Ma Lop, Ten lop hoac Ma Khoa");
					NapBang(lhbo.TimKiem(key));
					if (lhbo.TimKiem(key).size() == 0) {
						// Thong bao neu ko tim thay.
						JOptionPane.showMessageDialog(null, "Khong tim thay !!");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3.setBounds(571, 313, 67, 21);
		contentPane.add(button_3);

		Button button_4 = new Button("Hien thi");
		button_4.setFont(new Font("Dialog", Font.BOLD, 14));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapBang(ds);
			}
		});
		button_4.setBounds(709, 313, 67, 21);
		contentPane.add(button_4);

		Label label_3 = new Label("QUAN LI LOP");
		label_3.setFont(new Font("Dialog", Font.BOLD, 22));
		label_3.setBounds(354, 25, 243, 21);
		contentPane.add(label_3);
	}
}
