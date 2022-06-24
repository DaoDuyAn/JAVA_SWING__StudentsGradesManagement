package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenu frame = new FrmMenu();
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
	public FrmMenu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setTitle("He thong quan li Sinh vien");
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 666, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu_2 = new JMenu("He thong");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Thoat");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_9);

		JMenu mnNewMenu = new JMenu("Quan ly 1");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Quan ly Khoa");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmKhoa fk = new FrmKhoa();
				fk.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Quan ly Lop");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmLop fl = new FrmLop();
				fl.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Quan ly Sinh vien");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmSinhVien fsv = new FrmSinhVien();
				fsv.setVisible(true);
			}
		});
		
				JMenuItem mntmNewMenuItem_3 = new JMenuItem("Quan ly Giao vien");
				mntmNewMenuItem_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FrmGiaoVien fgv = new FrmGiaoVien();
						fgv.setVisible(true);
					}
				});
				mnNewMenu.add(mntmNewMenuItem_3);
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenu mnNewMenu_1 = new JMenu("Quan ly 2");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Quan ly Hoc Phan");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmHocPhan fhp = new FrmHocPhan();
				fhp.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Quan Ly Lop Hoc Phan");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmLopHocPhan flhp = new FrmLopHocPhan();
				flhp.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Quan Ly Diem");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDiem fd = new FrmDiem();
				fd.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);

		JMenu mnNewMenu_3 = new JMenu("Thong ke");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Thong Ke Si So");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmThongKeSiSo f1 = new FrmThongKeSiSo();
				f1.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Thong Ke Hoc Phan");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmThongKeHP f2 = new FrmThongKeHP();
				f2.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
	}
}
