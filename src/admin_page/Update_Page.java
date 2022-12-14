package admin_page;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class Update_Page extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<JavaoraRestVO> rest_list;
	private ArrayList<JavaoraMemVO> mem_list;
	private JTextField resNumTF;
	private JTextField resNameTF;
	private JTextField resPartTF;
	private JTextField resPhoneTF;
	private JTextField resOpentTF;
	private JTextField resAddressTF;
	private JTextField fileLocationTF;
	private JTextField fileLocationTF2;
	private String num=null;
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
	
	public Update_Page() {}

	public Update_Page(String mem_id, String mem_pw, int temp,String idx) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Page frame = new Update_Page(mem_id,mem_pw,idx);
					frame.setVisible(true);
					frame.setSize(1024,768);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Update_Page(String mem_id, String mem_pw,String idx) throws SQLException, ClassNotFoundException {
		int temp=0;
		JavaoraDAO jaDao = new JavaoraDAO();
		rest_list = jaDao.uploadStore(idx);
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
		
		JButton logout = new JButton("????????????"); //???????????? ??????
		logout.setBounds(899, 10, 97, 23);
		contentPane.add(logout);
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login();
				dispose();
			}
		});
		
		JButton myPage = new JButton("???????????????"); //??????????????? ??????
		myPage.setBounds(790, 10, 97, 23);
		contentPane.add(myPage);
		myPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MyPage(mem_id, mem_pw,temp);
				dispose();
			}
		});
		
		JButton admin = new JButton("UserPage"); //?????? ????????? ??????????????? ?????? ??????
		admin.setBounds(899, 43, 97, 23);
		admin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new JavaoraMain(mem_id,mem_pw,temp);
				dispose();
			}
		});
		contentPane.add(admin);
		
		JLabel homebtn = new JLabel(""); // ????????? ????????? ??????????????? ?????? ?????? 
		homebtn.setHorizontalAlignment(SwingConstants.CENTER);
		homebtn.setBounds(15, 10, 120, 110);
		homebtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Update_Restorant(mem_id, mem_pw,temp,null);
					dispose();
				}
			});
		homebtn.setIcon(new ImageIcon(Update_Page.class.getResource("/image/home.png")));
		contentPane.add(homebtn);
		
		
		
		JLabel title = new JLabel("????????? ?????? ?????????????????????."); //????????? ???????????? ??????
		title.setBounds(372, 30, 280, 36);
		title.setFont(new Font("??????", Font.BOLD, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title);

		
		JLabel addRestTitle = new JLabel("???????????? ??????????????? ??????????????????");
		addRestTitle.setBounds(300, 0, 260, 40);
		panel.add(addRestTitle);
		
		Label resNumLabel = new Label("?????? ????????????");
		resNumLabel.setBounds(100, 50, 100, 30);
		panel.add(resNumLabel);
		
		Label resNameLabel = new Label("?????????");
		resNameLabel.setBounds(100, 100, 100, 30);
		panel.add(resNameLabel);
		
		Label resPartLabel = new Label("?????? ?????? ex)??????, ?????? ???");
		resPartLabel.setBounds(50, 150, 150, 30);
		panel.add(resPartLabel);
		
		Label resPhoneLabel = new Label("????????????");
		resPhoneLabel.setBounds(100, 200, 100, 30);
		panel.add(resPhoneLabel);
		
		Label resOpentLabel = new Label("????????????");
		resOpentLabel.setBounds(100, 250, 100, 30);
		panel.add(resOpentLabel);
		
		Label resClosedLabel = new Label("????????????");
		resClosedLabel.setBounds(100, 300, 100, 30);
		panel.add(resClosedLabel);
		
		Label resAddressLabel = new Label("??????");
		resAddressLabel.setBounds(100, 350, 100, 30);
		panel.add(resAddressLabel);
		
		Label uploadfile = new Label("?????? ????????????");
		uploadfile.setBounds(100, 400, 100, 30);
		panel.add(uploadfile);
		
		fileLocationTF = new JTextField();
		fileLocationTF.setColumns(50);
		fileLocationTF.setBounds(210, 400, 200, 30);
		fileLocationTF.setEditable(false);
		fileLocationTF.setEnabled(false);
		panel.add(fileLocationTF);
		
		JButton filechoose = new JButton("?????? ??????");
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
		
		Label uploadfile2 = new Label("?????? ????????????");
		uploadfile2.setBounds(100, 450, 100, 30);
		panel.add(uploadfile2);
		
		
		fileLocationTF2 = new JTextField();
		fileLocationTF2.setColumns(50);
		fileLocationTF2.setBounds(210, 450, 200, 30);
		fileLocationTF2.setEditable(false);
		fileLocationTF2.setEnabled(false);
		panel.add(fileLocationTF2);
		
		JButton filechoose2 = new JButton("?????? ??????");
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
		
		resNumTF = new JTextField();
		resNumTF.setEditable(false);
		resNumTF.setEnabled(false);
		resNumTF.setText(rest_list.get(0).getRest_num());
		resNumTF.setColumns(10);
		resNumTF.setBounds(210, 50, 200, 30);
		panel.add(resNumTF);
		
		resNameTF = new JTextField();
		resNameTF.setColumns(20);
		resNameTF.setText(rest_list.get(0).getRest_name());
		resNameTF.setBounds(210, 100, 200, 30);
		panel.add(resNameTF);
		
		resPartTF = new JTextField();
		resPartTF.setColumns(10);
		resPartTF.setText(rest_list.get(0).getRest_part());
		resPartTF.setBounds(210, 150, 200, 30);
		panel.add(resPartTF);
		
		resPhoneTF = new JTextField();
		resPhoneTF.setColumns(15);
		resPhoneTF.setText(rest_list.get(0).getRest_phone());
		resPhoneTF.setBounds(210, 200, 200, 30);
		panel.add(resPhoneTF);
		
		resOpentTF = new JTextField();
		resOpentTF.setColumns(20);
		resOpentTF.setText(rest_list.get(0).getRest_opent());
		resOpentTF.setBounds(210, 250, 200, 30);
		panel.add(resOpentTF);
		
		resAddressTF = new JTextField();
		resAddressTF.setColumns(20);
		resAddressTF.setText(rest_list.get(0).getRest_address());
		resAddressTF.setBounds(210, 350, 200, 30);
		panel.add(resAddressTF);
		
		String [] week = {"???","???","???","???","???","???","???"};
		JCheckBox [] dayOffCB = new JCheckBox[7];
		
		dayOffCB[0] = new JCheckBox("???");
		dayOffCB[0].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[0].setBounds(210, 285, 40, 25);
		panel.add(dayOffCB[0]);
		
		dayOffCB[1] = new JCheckBox("???");
		dayOffCB[1].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[1].setBounds(250, 285, 40, 25);
		panel.add(dayOffCB[1]);
		
		dayOffCB[2] = new JCheckBox("???");
		dayOffCB[2].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[2].setBounds(290, 285, 40, 25);
		panel.add(dayOffCB[2]);
		
		dayOffCB[3] = new JCheckBox("???");
		dayOffCB[3].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[3].setBounds(330, 285, 40, 25);
		panel.add(dayOffCB[3]);
		
		dayOffCB[4] = new JCheckBox("???");
		dayOffCB[4].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[4].setBounds(370, 285, 40, 25);
		panel.add(dayOffCB[4]);
		
		dayOffCB[5] = new JCheckBox("???");
		dayOffCB[5].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[5].setBounds(210, 320, 40, 25);
		panel.add(dayOffCB[5]);
		
		dayOffCB[6] = new JCheckBox("???");
		dayOffCB[6].setHorizontalAlignment(SwingConstants.RIGHT);
		dayOffCB[6].setBounds(250, 320, 40, 25);
		panel.add(dayOffCB[6]);
		
//		if(rest_list.get(0).getRest_closed().contains("??????")) {
//			
//		}
		for(int i=0; i<week.length;i++) {
			if(rest_list.get(0).getRest_closed().contains(week[i])) {
				dayOffCB[i].setSelected(true);
			}else {
				dayOffCB[i].setSelected(false);
			}
		}
		
		JButton addApply = new JButton("????????????");
		addApply.setBounds(570, 130, 100, 60);
		panel.add(addApply);
		addApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				num = resNumTF.getText();
				name = resNameTF.getText();
				part = resPartTF.getText();
				phone = resPhoneTF.getText();
				opent = resOpentTF.getText();
				for(int i=0;i<dayOffCB.length;i++) {
					if(dayOffCB[i].isSelected()) {
						closed = closed + dayOffCB[i].getText() + " ";
					}
				}
				if(closed.equals("")) {
					closed = "??????";
				}
				address =resAddressTF.getText();
				
				try {
					jaDao.registerUpdate(num,name,part,phone,opent,closed,address);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
				
				new Update_Restorant(mem_id, mem_pw,temp,null);
				dispose();
			}
		});
		
		JButton addCancer = new JButton("??????");
		addCancer.setBounds(570, 260, 97, 60);
		panel.add(addCancer);
		addCancer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		Choice choice = new Choice();
		choice.setBounds(176, 88, 100, 23);
		contentPane.add(choice);
		choice.add("?????????");
		choice.add("?????????");
		choice.add("???????????????");
		
		textField = new JTextField();
		textField.setBounds(277, 88, 422, 23);
		textField.setText("");
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton search = new JButton("??????"); //?????? ??????, ?????? ??????, ?????? ???????????? ???????????? ????????????
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
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(0, 130, 150, 600);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 15));
		
		
		Button addRest = new Button("?????? ??????"); //?????? ?????? ?????????
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
		
		Button updateRest = new Button("?????? ??????/??????"); //?????? ??????/?????? ?????????
		panel_1.add(updateRest);
		updateRest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					panel.removeAll();
					rest_list.clear();
					rest_list = jaDao.getAdminAllInfo();
					jaDao.adminDisp(panel, rest_list,mem_id,mem_pw);
				} catch (SQLException e1) {}
				
			}
		});
		
		Button memManagement = new Button("????????????"); //???????????? ?????????
		panel_1.add(memManagement);
		memManagement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Member_Management(mem_id, mem_pw,temp,null);
				dispose();
			}
		});
		
		JLabel salut; //????????? ???????????? ???????????? ???????????? ???????????? ??????
		if(mem_id.equals("admin")) {
			salut = new JLabel("???????????? ??????!!!");
		}else {
			String userNick = jaDao.getUserInfo(mem_id,mem_pw);
			salut = new JLabel(userNick + "??? ???????????????~!");
		}
		salut.setBounds(704, 39, 183, 23);
		contentPane.add(salut);
		

	}
}
