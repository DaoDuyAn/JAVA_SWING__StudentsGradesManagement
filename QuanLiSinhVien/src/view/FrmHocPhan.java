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
import java.util.ArrayList;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Label;

import bo.HocPhanbo;
import bo.Khoabo;
import bean.HocPhan;
import bean.Khoa;
import dao.Coso;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmHocPhan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	TextField txtmhp = new TextField();
	TextField txtthp = new TextField();
	Choice choicemakhoa = new Choice();
	Khoabo khbo = new Khoabo();
	HocPhanbo hpbo = new HocPhanbo();
	ArrayList<HocPhan> ds;

	void NapBang(ArrayList<HocPhan> ds) {
		DefaultTableModel mh = new DefaultTableModel();
		int stt = 0;
		String[] td = { "STT", "Ma Hoc Phan", "Ten Hoc Phan", "Ma Khoa" };
		mh.setColumnIdentifiers(td);
		for (HocPhan hp : ds) {
			Object[] t = new Object[4];
			t[0] = ++stt;
			t[1] = hp.getMaHocPhan();
			t[2] = hp.getTenHocPhan();
			t[3] = hp.getMaKhoa();
			mh.addRow(t);
		}
		table.setModel(mh);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHocPhan frame = new FrmHocPhan();
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
	public FrmHocPhan() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					setTitle("Quan li Hoc phan");
					new Coso().KetNoi();
					ds = hpbo.getHocPhan();
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
		setBounds(100, 100, 1073, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(89, 322, 860, 366);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach hoc phan", null, scrollPane, null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d = table.getSelectedRow(); // lay hang.
				txtmhp.setText(table.getValueAt(d, 1).toString());
				txtthp.setText(table.getValueAt(d, 2).toString());
				choicemakhoa.select(table.getValueAt(d, 3).toString());
			}
		});
		scrollPane.setViewportView(table);
		txtmhp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtthp.requestFocus();
				}
			}
		});

		txtmhp.setBounds(265, 120, 225, 21);
		contentPane.add(txtmhp);

		txtthp.setBounds(265, 170, 225, 21);
		contentPane.add(txtthp);
		choicemakhoa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					NapBang(hpbo.TimHocPhan(choicemakhoa.getSelectedItem()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		choicemakhoa.setBounds(678, 120, 225, 18);
		contentPane.add(choicemakhoa);

		Button button = new Button("Them");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int kt = hpbo.Them(txtmhp.getText(), txtthp.getText(), choicemakhoa.getSelectedItem());
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
		button.setBounds(128, 263, 67, 21);
		contentPane.add(button);

		Button button_1 = new Button("Xoa");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int choice = JOptionPane.showConfirmDialog(null, "Ban co muon xoa ?");
					if (choice == 0) {
						int kt = hpbo.Xoa(txtmhp.getText());
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
		button_1.setBounds(395, 263, 67, 21);
		contentPane.add(button_1);

		Button button_2 = new Button("Sua");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int kt = hpbo.Sua(txtmhp.getText(), txtthp.getText(), choicemakhoa.getSelectedItem());
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
		button_2.setBounds(269, 263, 67, 21);
		contentPane.add(button_2);

		Button button_3 = new Button("Tim kiem");
		button_3.setFont(new Font("Dialog", Font.BOLD, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String key = JOptionPane.showInputDialog("Nhap Ma Hoc Phan, Ten Hoc Phan hoac Ma Khoa");
					NapBang(hpbo.TimKiem(key));
					if (hpbo.TimKiem(key).size() == 0) {
						// Thong bao neu ko tim thay.
						JOptionPane.showMessageDialog(null, "Khong tim thay !!");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_3.setBounds(554, 263, 67, 21);
		contentPane.add(button_3);

		Button button_4 = new Button("Hien thi");
		button_4.setFont(new Font("Dialog", Font.BOLD, 14));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapBang(ds);
			}
		});
		button_4.setBounds(691, 263, 67, 21);
		contentPane.add(button_4);

		Label label = new Label("Ma Hoc Phan");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(109, 120, 121, 21);
		contentPane.add(label);

		Label label_1 = new Label("Ten Hoc Phan");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(109, 170, 121, 21);
		contentPane.add(label_1);

		Label label_2 = new Label("Ma Khoa ");
		label_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2.setBounds(574, 120, 84, 21);
		contentPane.add(label_2);

		Label label_3 = new Label("QUAN LI HOC PHAN");
		label_3.setFont(new Font("Dialog", Font.BOLD, 22));
		label_3.setBounds(376, 25, 364, 21);
		contentPane.add(label_3);
	}
}
