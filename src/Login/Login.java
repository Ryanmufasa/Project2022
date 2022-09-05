package Login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import MemberDAO.MemberDAO;
import javaoraDBConn.JavaoraDBConn;
import javaoraMain.JavaoraMain;
import javaoraVO.JavaoraMemVO;

import javax.swing.JPasswordField;

public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField tfUsername;
	private JButton loginBtn, joinBtn;
	private JPasswordField tfPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("아이디");
		lblLogin.setBounds(41, 52, 69, 35);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setBounds(41, 103, 69, 35);
		contentPane.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(157, 52, 176, 35);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		joinBtn = new JButton("회원가입");
		joinBtn.setBounds(229, 182, 104, 29);
		contentPane.add(joinBtn);
		
		loginBtn = new JButton("로그인");
		loginBtn.setBounds(91, 182, 106, 29);
		contentPane.add(loginBtn);
		
		tfPassword = new JPasswordField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(157, 103, 176, 35);
		contentPane.add(tfPassword);
		
		setVisible(true);
		//회원가입 액션
		joinBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Join frame = new Join();
			}
		});
		
		//로그인 액션
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String mem_id = tfUsername.getText();
				String mem_pw = tfPassword.getText();
				MemberDAO dao = new MemberDAO();
			
				int result; //로그인 성공,실패 메세지 출력을 위함
				
				int temp=0; 
				
				try {
					result = dao.findByUsernameAndPassword(mem_id, mem_pw);
					
					if(result == 1) {
						//로그인 성공 메시지
						JOptionPane.showMessageDialog(null, "로그인 성공");
						dispose();
						dispose();
						new JavaoraMain(mem_id, mem_pw,temp);
					}else {
						JOptionPane.showMessageDialog(null, "로그인 실패");
						new Login();
						dispose();
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
	}
}




