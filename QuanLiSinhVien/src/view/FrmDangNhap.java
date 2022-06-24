package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrmDangNhap extends JFrame {

	private JPanel contentPane;
	TextField txttk = new TextField();
	TextField txtmk = new TextField();
	public static String un = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDangNhap frame = new FrmDangNhap();
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
	public FrmDangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label = new Label("Tai khoan");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(154, 102, 125, 21);
		contentPane.add(label);

		txttk.setBounds(154, 143, 201, 21);
		contentPane.add(txttk);

		Label label_1 = new Label("Mat khau");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(154, 200, 104, 21);
		contentPane.add(label_1);

		txtmk.setBounds(154, 244, 201, 21);
		txtmk.setEchoChar('*');
		contentPane.add(txtmk);

		Button button = new Button("Dang nhap");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				un = txttk.getText();
				FrmMenu f = new FrmMenu();
				f.setVisible(true);
			}
		});
		button.setBounds(217, 313, 96, 21);
		contentPane.add(button);

		Label label_2 = new Label("DANG NHAP");
		label_2.setFont(new Font("Dialog", Font.BOLD, 22));
		label_2.setBounds(188, 37, 201, 21);
		contentPane.add(label_2);
	}
}
