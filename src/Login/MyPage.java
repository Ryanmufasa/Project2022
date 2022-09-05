package Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javaoraMain.JavaoraMain;
import MemberDAO.MemberDAO;
import MemberVO.MemberVO;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DropMode;
import javax.swing.Icon;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MyPage extends JFrame {
	MemberVO memberVO = new MemberVO();
	String id = null;
	String pw, name;
	
	private Connection conn; 
	private PreparedStatement pstmt; 
	private ResultSet rs; 
	
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfNick;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JPasswordField tfPassword;
	private JTextField tfName;
	private JTable table;
	private DefaultTableModel tModel;

	public static void main(String[] args) {
		
	}

	public MyPage(String mem_id, String mem_pw, int temp) {
		setTitle("마이페이지");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPage frame = new MyPage(mem_id,mem_pw);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MyPage(String mem_id, String mem_pw) throws ClassNotFoundException, SQLException {
		setTitle("마이 페이지");
		
		MemberDAO dao = new MemberDAO();
		
		memberVO = dao.getMemberVO1(mem_id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPhone = new JLabel("\uD578\uB4DC\uD3F0\uBC88\uD638");
		lblPhone.setBounds(582, 293, 102, 22);
		contentPane.add(lblPhone);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(731, 101, 137, 28);
		contentPane.add(tfUsername);
		tfUsername.setEditable(false);
		tfUsername.setColumns(10);
		tfUsername.setText(memberVO.getMem_id());
		
		tfNick = new JTextField();
		tfNick.setColumns(10);
		tfNick.setBounds(731, 177, 137, 28);
		tfNick.setText(memberVO.getMem_nick());
		contentPane.add(tfNick);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(731, 253, 137, 28);
		tfEmail.setText(memberVO.getMem_email());
		contentPane.add(tfEmail);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(731, 291, 137, 28);
		tfPhone.setText(memberVO.getMem_phone());
		contentPane.add(tfPhone);
		
		JLabel lblId = new JLabel("\uC544\uC774\uB514");
		lblId.setBounds(582, 103, 102, 22);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblPassword.setBounds(582, 147, 102, 22);
		contentPane.add(lblPassword);
		
		JLabel lblNickname = new JLabel("\uB2C9\uB124\uC784");
		lblNickname.setBounds(582, 179, 102, 22);
		contentPane.add(lblNickname);
		
		JLabel lblEmail = new JLabel("\uC774\uBA54\uC77C");
		lblEmail.setBounds(582, 255, 102, 22);
		contentPane.add(lblEmail);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(731, 139, 137, 28);
		tfPassword.setText(memberVO.getMem_pw());
		contentPane.add(tfPassword);
		
		JButton updateBtn = new JButton("\uC218\uC815");
		updateBtn.setBounds(582, 375, 97, 44);
		contentPane.add(updateBtn);
		
		JButton backBtn = new JButton("\uCDE8\uC18C");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		backBtn.setBounds(771, 375, 97, 44);
		contentPane.add(backBtn);
		
		JLabel lblNewLabel = new JLabel("\uD3C9\uC810 \uB0B4\uC5ED");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(160, 0, 230, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblBig = new JLabel("\uB9C8\uC774\uD398\uC774\uC9C0");
		lblBig.setFont(new Font("굴림", Font.PLAIN, 25));
		lblBig.setHorizontalAlignment(SwingConstants.CENTER);
		lblBig.setBounds(582, 47, 286, 44);
		contentPane.add(lblBig);
		
		tfName = new JTextField();
		tfName.setText((String) null);
		tfName.setColumns(10);
		tfName.setBounds(731, 215, 137, 28);
		tfName.setText(memberVO.getMem_name());
		contentPane.add(tfName);
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(582, 211, 102, 28);
		contentPane.add(lblName);
		
		JPanel commentPanel = new JPanel();
		commentPanel.setBackground(Color.WHITE);
		commentPanel.setBounds(12, 42, 519, 456);
		commentPanel.setToolTipText("");
		contentPane.add(commentPanel);
	    commentPanel.setLayout(new BorderLayout(0, 0));
	    commentPanel.setBorder(new EmptyBorder(5,5,5,5));
	    
	    //////////////////////기능 구현 시작
	    
	    Vector commentVO = new Vector();
	    commentVO = dao.getComment(memberVO.getMem_nick());
	    
	    DefaultTableModel model= new DefaultTableModel(commentVO, dao.getColumn());
   
	    table = new JTable(model);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setEnabled(false);
	  		
	    //테이블 더블 클릭시 상세 내용 출력
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Vector commentVO = new Vector();
				try {
					commentVO = dao.getComment_review(memberVO.getMem_nick());
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				if(e.getClickCount() == 2) {
					int row = table.rowAtPoint( e.getPoint() );
		            int column = table.columnAtPoint( e.getPoint() );
		            String s=table.getModel().getValueAt(row, column)+"";

		            JOptionPane.showMessageDialog(null, s);
				}	
			}
			
		});
	    table.setColumnSelectionAllowed(true);
	    table.setCellSelectionEnabled(true);
	    table.setFont(new Font("돋움", Font.PLAIN, 12));
	    table.setRowHeight(30);

	    JScrollPane scrollPane = new JScrollPane(table);
	    commentPanel.add(scrollPane);
		
		setVisible(true);
	
		backBtn.addActionListener(new ActionListener() {
			int temp =0;
			@Override
			public void actionPerformed(ActionEvent e) {
				new JavaoraMain(mem_id, mem_pw,temp);
				dispose();
			}
		});
		
		memberVO = dao.getMemberVO1(mem_id);
		updateBtn.addActionListener(new ActionListener() {
			
			int temp =0;
			int result=1;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				memberVO.setMem_id(tfUsername.getText());
				memberVO.setMem_pw(tfPassword.getText());
				memberVO.setMem_name(tfName.getText());
				memberVO.setMem_nick(tfNick.getText());
				memberVO.setMem_email(tfEmail.getText());
				memberVO.setMem_phone(tfPhone.getText());
				
				
				try {
					result = dao.update(memberVO);
					if(result == 1) {
						JOptionPane.showMessageDialog(null, "수정 완료");
						new JavaoraMain(memberVO.getMem_id(), memberVO.getMem_pw(),temp);
						dispose();
					}else if(result == -2) {
						JOptionPane.showMessageDialog(null, "빈칸을 입력해 주세요.");
						dispose();
						new MyPage(memberVO.getMem_id(),memberVO.getMem_pw(),temp);
					}else {
						JOptionPane.showMessageDialog(null, "수정 실패!\n관리자에게 문의하세요.");
						new JavaoraMain(memberVO.getMem_id(), memberVO.getMem_pw(),temp);
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
