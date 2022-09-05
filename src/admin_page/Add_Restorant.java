package admin_page;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;

import Login.Login;
import Login.MyPage;
import javaoraDAO.JavaoraDAO;
import javaoraMain.JavaoraMain;
import javaoraVO.JavaoraMemVO;
import javaoraVO.JavaoraRestVO;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Button;
import java.awt.Choice;

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
import java.io.File;
import java.awt.event.MouseWheelEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Panel;

public class Add_Restorant extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<JavaoraRestVO> rest_list;
	private ArrayList<JavaoraMemVO> mem_list;
	private JTextField resNameTF;
	private JTextField resPartTF;
	private JTextField resPhoneTF;
	private JTextField resOpentTF;
	private JTextField resAddressTF;
	private JTextField fileLocationTF;
	private JTextField fileLocationTF2;
	private String name=null;
	private String part=null;
	private String phone=null;
	private String opent=null;
	private String closed="";
	private String address=null;
	private File src;
	private File src2;
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
	
	public Add_Restorant() {}

	public Add_Restorant(String mem_id, String mem_pw, int temp) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Restorant frame = new Add_Restorant(mem_id,mem_pw);
					frame.setVisible(true);
					frame.setSize(1024,768);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Add_Restorant(String mem_id, String mem_pw) throws SQLException, ClassNotFoundException {
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
		
		panel.setLayout(null);
		panel.setBounds(150, 130, 860, 600);
		contentPane.add(panel);
		
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
		
		JButton admin = new JButton("UserPage"); //일반 사용자 홈화면으로 가는 버튼
		admin.setBounds(899, 43, 97, 23);
		admin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new JavaoraMain(mem_id,mem_pw,temp);
				dispose();
			}
		});
		contentPane.add(admin);
		
		JLabel homebtn = new JLabel(""); // 관리자 페이지 홈화면으로 가는 버튼 
		homebtn.setHorizontalAlignment(SwingConstants.CENTER);
		homebtn.setBounds(15, 10, 120, 110);
		homebtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Update_Restorant(mem_id, mem_pw,temp,null);
					dispose();
				}
			});
		homebtn.setIcon(new ImageIcon(Add_Restorant.class.getResource("/image/home.png")));
		contentPane.add(homebtn);
		
		Choice choice = new Choice();
		choice.setBounds(176, 88, 100, 23);
		contentPane.add(choice);
		choice.add("맛집명");
		choice.add("회원명");
		choice.add("회원닉네임");
		
		textField = new JTextField();
		textField.setBounds(277, 88, 422, 23);
		textField.setText("");
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton search = new JButton("검색"); //맛집 이름, 회원 이름, 회원 닉네임을 토대로한 검색버튼
		search.setBounds(704, 88, 97, 23);
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				String stridx = textField.getText();
				int choiceIdx = choice.getSelectedIndex();
				if(stridx!=null) {
					if(choiceIdx==0) {			
						try {
							rest_list = jaDao.getSearch(stridx);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						new Update_Restorant(mem_id,mem_pw,temp,rest_list);
					}else {
						try {
							mem_list = jaDao.getMemSearch(stridx, choiceIdx);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						new Member_Management(mem_id, mem_pw,temp,mem_list);
					}
					dispose();
				}else {}
			}
		});
		contentPane.add(search);
		
		JLabel title = new JLabel("노량진 맛집 자바오라입니다."); //타이틀 표시하는 라벨
		title.setBounds(372, 30, 280, 36);
		title.setFont(new Font("굴림", Font.BOLD, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title);

		
		JLabel addRestTitle = new JLabel("추가하실 맛집정보를 입력해주세요");
		addRestTitle.setBounds(300, 10, 260, 40);
		panel.add(addRestTitle);
		
		Label resNameLabel = new Label("상호명");
		resNameLabel.setBounds(100, 100, 100, 30);
		panel.add(resNameLabel);
		
		Label resPartLabel = new Label("음식 유형 ex)한식, 양식 등");
		resPartLabel.setBounds(50, 150, 150, 30);
		panel.add(resPartLabel);
		
		Label resPhoneLabel = new Label("전화번호");
		resPhoneLabel.setBounds(100, 200, 100, 30);
		panel.add(resPhoneLabel);
		
		Label resOpentLabel = new Label("영업시간");
		resOpentLabel.setBounds(100, 250, 100, 30);
		panel.add(resOpentLabel);
		
		Label resClosedLabel = new Label("휴무요일");
		resClosedLabel.setBounds(100, 300, 100, 30);
		panel.add(resClosedLabel);
		
		Label resAddressLabel = new Label("주소");
		resAddressLabel.setBounds(100, 350, 100, 30);
		panel.add(resAddressLabel);
		
		Label uploadfile = new Label("가게 사진등록");
		uploadfile.setBounds(100, 400, 100, 30);
		panel.add(uploadfile);
		
		fileLocationTF = new JTextField();
		fileLocationTF.setColumns(50);
		fileLocationTF.setBounds(210, 400, 200, 30);
		fileLocationTF.setEditable(false);
		fileLocationTF.setEnabled(false);
		panel.add(fileLocationTF);
		
		JButton filechoose = new JButton("파일 선택");
		filechoose.setBounds(450, 400, 90, 30);
		panel.add(filechoose);
		filechoose.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			src = jaDao.fileOpenDlg();
			fileLocationTF.setText(src.getPath());
//			byte[] imgData = jaDao.loadImage(src);
//			
//			File copy = jaDao.fileSaveDlg();
//			jaDao.saveImage(copy, imgData);
//			
//			jaDao.showImage(copy);
		}
		});
		
		Label uploadfile2 = new Label("메뉴 사진등록");
		uploadfile2.setBounds(100, 450, 100, 30);
		panel.add(uploadfile2);
		
		
		fileLocationTF2 = new JTextField();
		fileLocationTF2.setColumns(50);
		fileLocationTF2.setBounds(210, 450, 200, 30);
		fileLocationTF2.setEditable(false);
		fileLocationTF2.setEnabled(false);
		panel.add(fileLocationTF2);
		
		JButton filechoose2 = new JButton("파일 선택");
		filechoose2.setBounds(450, 450, 90, 30);
		panel.add(filechoose2);
		filechoose2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				src2 = jaDao.fileOpenDlg();
				fileLocationTF2.setText(src2.getPath());
//			byte[] imgData = jaDao.loadImage(src);
//			
//			File copy = jaDao.storeSaveDlg("100");
//			jaDao.saveImage(copy, imgData);
//			
//			jaDao.showImage(copy);
			}
		});
		

		



		
		resNameTF = new JTextField();
		resNameTF.setColumns(10);
		resNameTF.setBounds(210, 100, 200, 30);
		panel.add(resNameTF);
		
		resPartTF = new JTextField();
		resPartTF.setColumns(10);
		resPartTF.setBounds(210, 150, 200, 30);
		panel.add(resPartTF);
		
		resPhoneTF = new JTextField();
		resPhoneTF.setColumns(10);
		resPhoneTF.setBounds(210, 200, 200, 30);
		panel.add(resPhoneTF);
		
		resOpentTF = new JTextField();
		resOpentTF.setColumns(10);
		resOpentTF.setBounds(210, 250, 200, 30);
		panel.add(resOpentTF);
		
		resAddressTF = new JTextField();
		resAddressTF.setColumns(10);
		resAddressTF.setBounds(210, 350, 200, 30);
		panel.add(resAddressTF);
		
		JCheckBox [] dayOffCB = new JCheckBox[7];
		
		dayOffCB[0] = new JCheckBox("월");
		dayOffCB[0].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[0].setBounds(210, 285, 40, 25);
		panel.add(dayOffCB[0]);
		
		dayOffCB[1] = new JCheckBox("화");
		dayOffCB[1].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[1].setBounds(250, 285, 40, 25);
		panel.add(dayOffCB[1]);
		
		dayOffCB[2] = new JCheckBox("수");
		dayOffCB[2].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[2].setBounds(290, 285, 40, 25);
		panel.add(dayOffCB[2]);
		
		dayOffCB[3] = new JCheckBox("목");
		dayOffCB[3].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[3].setBounds(330, 285, 40, 25);
		panel.add(dayOffCB[3]);
		
		dayOffCB[4] = new JCheckBox("금");
		dayOffCB[4].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[4].setBounds(370, 285, 40, 25);
		panel.add(dayOffCB[4]);
		
		dayOffCB[5] = new JCheckBox("토");
		dayOffCB[5].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[5].setBounds(210, 320, 40, 25);
		panel.add(dayOffCB[5]);
		
		dayOffCB[6] = new JCheckBox("일");
		dayOffCB[6].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[6].setBounds(250, 320, 40, 25);
		panel.add(dayOffCB[6]);
		
		JButton addApply = new JButton("등록");
		addApply.setBounds(570, 130, 100, 60);
		panel.add(addApply);
		addApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String num=null;
				int closeCnt = 0;
				name = resNameTF.getText();
				part = resPartTF.getText();
				phone = resPhoneTF.getText();
				opent = resOpentTF.getText();
				for(int i=0;i<dayOffCB.length;i++) {
					if(dayOffCB[i].isSelected()) {
						closed = closed + dayOffCB[i].getText() + " ";
						closeCnt++;
					}
				}
				if(closeCnt == 0) {
					closed = "없음";
				}
				address = resAddressTF.getText();
				
				try {
					num = jaDao.register(name,part,phone,opent,closed,address);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if(!(fileLocationTF.getText().equals(""))) {
					byte [] storeImg = jaDao.loadImage(src);
					jaDao.saveStoreImage(num,storeImg);
				}
				if(!(fileLocationTF2.getText().equals(""))) {
				byte [] menuImg = jaDao.loadImage(src2);
				jaDao.saveMenuImage(num,menuImg);
				}
				
				new Add_Restorant(mem_id, mem_pw,temp);
				dispose();
			}
		});
		
		JButton addCancer = new JButton("취소");
		addCancer.setBounds(570, 260, 97, 60);
		panel.add(addCancer);
		addCancer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Add_Restorant(mem_id, mem_pw,temp);
				dispose();
			}
		});
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(0, 130, 150, 600);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 15));
		
		
		Button addRest = new Button("맛집 추가"); //맛집 추가 페이지
		panel_1.add(addRest);
		addRest.setBackground(new Color(168,194,249));
		addRest.setEnabled(false);
		addRest.setForeground(Color.BLACK);
		addRest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Add_Restorant(mem_id, mem_pw,temp);
				dispose();
			}
		});
		
		Button updateRest = new Button("맛집 수정/삭제"); //맛집 수정/삭제 페이지
		panel_1.add(updateRest);
		updateRest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Update_Restorant(mem_id, mem_pw,temp,null);
				dispose();
			}
		});
		
		Button memManagement = new Button("회원관리"); //회원관리 페이지
		panel_1.add(memManagement);
		memManagement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Member_Management(mem_id, mem_pw,temp,null);
				dispose();
			}
		});
		
		JLabel salut; //사용자 아이디를 인식하여 인사말을 표시하는 라벨
		if(mem_id.equals("admin")) {
			salut = new JLabel("관리자님 충성!!!");
		}else {
			String userNick = jaDao.getUserInfo(mem_id,mem_pw);
			salut = new JLabel(userNick + "님 환영합니다~!");
		}
		salut.setBounds(704, 39, 183, 23);
		contentPane.add(salut);
		

	}

}
