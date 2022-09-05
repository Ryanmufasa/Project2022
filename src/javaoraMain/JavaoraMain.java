package javaoraMain;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Login.Login;
import Login.MyPage;
import admin_page.Update_Restorant;
import javaoraDAO.JavaoraDAO;
import javaoraVO.JavaoraRestVO;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Panel;

public class JavaoraMain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String part=null;
	private ArrayList<JavaoraRestVO> rest_list;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
	
	public JavaoraMain() {}

	public JavaoraMain(String mem_id, String mem_pw, int temp) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaoraMain frame = new JavaoraMain(mem_id,mem_pw);
					frame.setVisible(true);
					frame.setSize(1024,768);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JavaoraMain(String mem_id, String mem_pw) throws SQLException, ClassNotFoundException {
		setTitle("메인 페이지");
		int temp=0;
		JavaoraDAO jaDao = new JavaoraDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setLayout(new GridLayout(0, 4, 10, 10));
		scrollPane.setBounds(150, 130, 860, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(12);
		contentPane.add(scrollPane);
		
		JButton logout = new JButton("로그아웃"); //로그아웃 버튼
		logout.setBounds(899, 10, 97, 23);
		contentPane.add(logout);
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login();
				dispose();
			}
		});
		
		JButton myPage = new JButton("마이페이지"); //마이페이지 버튼
		myPage.setBounds(790, 10, 97, 23);
		contentPane.add(myPage);
		myPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MyPage(mem_id, mem_pw,temp);
				dispose();
			}
		});
		
		JButton admin = new JButton("adminpage"); //관리자 페이지 버튼
		admin.setBounds(899, 43, 97, 23);
		if(mem_id.equals("admin")) { //관리자 아이디 외 다른아이디가 접근 시 노출되지않음
			admin.setVisible(true);
		}else {
			admin.setVisible(false);
		}
		admin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Update_Restorant(mem_id, mem_pw,temp,null);
				dispose();
			}
		});
		contentPane.add(admin);
		
		JLabel homebtn = new JLabel(""); // 홈버튼, 창을 다시 띄워서 홈화면을 다시 불러오는것이 아니라 홈에서 확인하는 전체 랜덤리스트를 다시 불러오는 방식 
		homebtn.setHorizontalAlignment(SwingConstants.CENTER);
		homebtn.setBounds(15, 10, 120, 110);
		homebtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					try {
						panel.removeAll();
						rest_list.clear();
						rest_list = jaDao.getAllInfo();
						jaDao.disp(panel, rest_list,mem_id);
					} catch (SQLException e1) {}
				}
			});
		homebtn.setIcon(new ImageIcon(JavaoraMain.class.getResource("/image/home.png")));
		contentPane.add(homebtn);
		
		textField = new JTextField();
		textField.setBounds(277, 88, 422, 23);
		textField.setText("");
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("검색"); //맛집 이름을 토대로한 검색버튼
		btnNewButton_3.setBounds(704, 88, 97, 23);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String stridx = textField.getText();
				try {
					panel.removeAll();
					rest_list.clear();
					rest_list = jaDao.getSearch(stridx);
					jaDao.disp(panel, rest_list, mem_id);
				} catch (SQLException e1) {}
			}
		});
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("노량진 맛집 자바오라입니다."); //타이틀 표시하는 라벨
		lblNewLabel_1.setBounds(372, 30, 280, 36);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);

//=======================메인 호출부분======================================
		rest_list = jaDao.getAllInfo();
		jaDao.disp(panel, rest_list,mem_id);
//=======================메인 호출부분======================================
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(0, 130, 150, 600);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 15));
		
		
		Button hansik = new Button("한식"); //한식 카테고리만 표시하는 버튼
		panel_1.add(hansik);
		hansik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				part = "한식";
				try {
					panel.removeAll();
					rest_list.clear();
					rest_list = jaDao.getPartInfo(part);
					jaDao.disp(panel, rest_list,mem_id);
				} catch (SQLException e1) {}
				
			}
		});
		
		Button joongsik = new Button("중식"); //중식 카테고리만 표시하는 버튼
		panel_1.add(joongsik);
		joongsik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				part = "중식";
				try {
					panel.removeAll();
					rest_list.clear();
					rest_list = jaDao.getPartInfo(part);
					jaDao.disp(panel, rest_list,mem_id);
				} catch (SQLException e1) {}
				
			}
		});
		
		Button ilsik = new Button("일식"); //일식 카테고리만 표시하는 버튼
		panel_1.add(ilsik);
		ilsik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				part = "일식";
				try {
					panel.removeAll();
					rest_list.clear();
					rest_list = jaDao.getPartInfo(part);
					jaDao.disp(panel, rest_list,mem_id);
				} catch (SQLException e1) {}
				
			}
		});
		
		Button yangsik = new Button("양식"); //양식 카테고리만 표시하는 버튼
		panel_1.add(yangsik);
		yangsik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				part = "양식";
				try {
					panel.removeAll();
					rest_list.clear();
					rest_list = jaDao.getPartInfo(part);
					jaDao.disp(panel, rest_list,mem_id);
				} catch (SQLException e1) {}
				
			}
		});
		
		Button boonsik = new Button("분식"); //분식 카테고리만 표시하는 버튼
		panel_1.add(boonsik);
		boonsik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				part = "분식";
				try {
					panel.removeAll();
					rest_list.clear();
					rest_list = jaDao.getPartInfo(part);
					jaDao.disp(panel, rest_list,mem_id);
				} catch (SQLException e1) {}
				
			}
		});
		
		JLabel lblNewLabel; //사용자 아이디를 인식하여 인사말을 표시하는 라벨
		if(mem_id.equals("admin")) {
			lblNewLabel = new JLabel("관리자님 충성!!!");
		}else {
			String userNick = jaDao.getUserInfo(mem_id,mem_pw);
			lblNewLabel = new JLabel(userNick + "님 환영합니다~!");
		}
		lblNewLabel.setBounds(704, 39, 183, 23);
		contentPane.add(lblNewLabel);
		

	}
}
