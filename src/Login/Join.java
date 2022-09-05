package Login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.border.EmptyBorder;

import MemberDAO.MemberDAO;
import MemberVO.MemberVO;

import javax.swing.JPasswordField;




public class Join extends JFrame {

	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	private JTextField tfUsername;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JPasswordField tfPassword;
	private JButton backBtn;
	private JLabel lblNick;
	private JTextField tfNick;
	private JButton checkBtn;
	
	private int count=0; //중복 체크용 변수

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join frame = new Join();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Join() {
		setTitle("\uD68C\uC6D0\uAC00\uC785");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(503, 570);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblJoin = new JLabel("회원가입");
		Font f1 = new Font("돋움", Font.BOLD, 20); 
		lblJoin.setFont(f1); 
		lblJoin.setBounds(159, 41, 101, 20);
		contentPane.add(lblJoin);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setBounds(69, 163, 69, 20);
		contentPane.add(lblPassword);
		
		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(69, 113, 69, 20);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(69, 206, 69, 20);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setBounds(69, 300, 69, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("핸드폰번호");
		lblPhone.setBounds(69, 345, 69, 20);
		contentPane.add(lblPhone);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(159, 106, 186, 35);
		contentPane.add(tfUsername);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(159, 199, 186, 35);
		contentPane.add(tfName);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(159, 293, 186, 35);
		contentPane.add(tfEmail);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(159, 338, 186, 35);
		contentPane.add(tfPhone);
		
		joinCompleteBtn = new JButton("가입신청");
		joinCompleteBtn.setBounds(69, 422, 118, 29);
		contentPane.add(joinCompleteBtn);
		
		tfPassword = new JPasswordField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(159, 154, 186, 35);
		contentPane.add(tfPassword);
		
		backBtn = new JButton("\uCDE8\uC18C");
		backBtn.setBounds(248, 422, 97, 29);
		contentPane.add(backBtn);
		
		lblNick = new JLabel("\uB2C9\uB124\uC784");
		lblNick.setBounds(69, 256, 57, 15);
		contentPane.add(lblNick);
		
		tfNick = new JTextField();
		tfNick.setBounds(159, 248, 186, 35);
		contentPane.add(tfNick);
		tfNick.setColumns(10);
		
		checkBtn = new JButton("\uC911\uBCF5\uD655\uC778");
		checkBtn.setBounds(357, 113, 97, 22);
		contentPane.add(checkBtn);
		
		setVisible(true);
		
		MemberDAO dao = new MemberDAO();
		
		//중복 체크 버튼 
		checkBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(dao.findExistID(tfUsername.getText())) {
						JOptionPane.showMessageDialog(null,"중복된 ID 입니다.");
						tfUsername.setText("");
						return;
					}else {
						JOptionPane.showMessageDialog(tfUsername, "사용 가능한 ID 입니다.");
						count=1;

					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		//회원가입완료 액션
		joinCompleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberVO memberVO = new MemberVO();

				int result;
				try {
					
					
					if(count == 1) {
						
						memberVO.setMem_id(tfUsername.getText());
						memberVO.setMem_pw(tfPassword.getText());
						memberVO.setMem_name(tfName.getText());
						memberVO.setMem_nick(tfNick.getText());
						memberVO.setMem_email(tfEmail.getText());
						memberVO.setMem_phone(tfPhone.getText());
						result = dao.save(memberVO);
						
						if(result == 1) {
							JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
							dispose();

						}else if(result == -2) {
							JOptionPane.showMessageDialog(null, "빈칸을 입력해 주세요.");
							dispose();
							new Join();
						}else {
							JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.");
							dispose();
						}	
					}else {
						JOptionPane.showMessageDialog(null, "중복 체크를 해주세요");
					}
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
			}
		});

	}
}







