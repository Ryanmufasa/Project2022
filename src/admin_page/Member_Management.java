package admin_page;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.DefaultCellEditor;

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
import java.awt.event.MouseWheelEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Panel;

public class Member_Management extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String part=null;
	private ArrayList<JavaoraRestVO> rest_list;
	private ArrayList<JavaoraMemVO> mem_list;
	private JTable table;
	private JTable table2;
	private DefaultTableModel dTable;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
	
	public Member_Management() {}

	public Member_Management(String mem_id, String mem_pw, int temp, ArrayList<JavaoraMemVO> searchIdx) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member_Management frame = new Member_Management(mem_id,mem_pw,searchIdx);
					frame.setVisible(true);
					frame.setSize(1024,768);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Member_Management(String mem_id, String mem_pw, ArrayList<JavaoraMemVO> searchIdx) throws SQLException, ClassNotFoundException {
		int temp=0;
		System.out.println(searchIdx==null);
		JavaoraDAO jaDao = new JavaoraDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setLayout(new GridLayout(0, 4, 10, 10));
		scrollPane.setBounds(150, 130, 860, 500);
		scrollPane.getVerticalScrollBar().setUnitIncrement(12);
		contentPane.add(scrollPane);

//=======================?????? ????????????===================================================
		if(searchIdx==null) {
			mem_list = jaDao.getAllMemInfo();
			table2 = jaDao.memDisp(mem_list,dTable,table,scrollPane);
		}else {
			table2 = jaDao.memDisp(searchIdx,dTable,table,scrollPane);
		}
		searchIdx = null;
////=======================?????? ????????????=================================================
		
		
		JButton delConfirm = new JButton("?????? ??????");
		delConfirm.setBounds(530,663,100,30);
		contentPane.add(delConfirm);
		delConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				long [] idx = new long[table2.getRowCount()];
				int j=0;
				for(int i=0;i<table2.getRowCount(); i++) {
					if(table2.getValueAt(i, 8)!=null) {
						if((boolean)table2.getValueAt(i, 8)) {
							idx[j] = (long)table2.getValueAt(i, 0);
							j++;
						}
					}
				}
				try {
					jaDao.deleteMem(idx);
				} catch (SQLException e1) {
					System.out.println("?????????????????? ???????????? ?????? ??????");
				}
				new Member_Management(mem_id, mem_pw,temp,null);
				dispose();
			}
		});
//======================????????? ????????? ????????????===============================================================
		
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
					dispose();
					new Member_Management(mem_id, mem_pw,temp,null);
				}
			});
		homebtn.setIcon(new ImageIcon(Member_Management.class.getResource("/image/home.png")));
		contentPane.add(homebtn);
		
		Choice choice = new Choice();
		choice.setBounds(176, 88, 100, 23);
		contentPane.add(choice);
		choice.add("?????????");
		choice.add("?????????");
		choice.add("???????????????");
		
		textField = new JTextField();
		textField.setBounds(277, 88, 422, 23);
		textField.setText(null);
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
		
		JLabel title = new JLabel("????????? ?????? ?????????????????????."); //????????? ???????????? ??????
		title.setBounds(372, 30, 280, 36);
		title.setFont(new Font("??????", Font.BOLD, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title);


		Panel panel_1 = new Panel();
		panel_1.setBounds(0, 130, 150, 600);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 15));
		
		
		Button addRest = new Button("?????? ??????"); //?????? ?????? ?????????
		panel_1.add(addRest);
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
				new Update_Restorant(mem_id, mem_pw,temp,null);
				dispose();
			}
		});
		
		Button memManagement = new Button("????????????"); //???????????? ?????????
		panel_1.add(memManagement);
		memManagement.setBackground(new Color(168,194,249));
		memManagement.setEnabled(false);
		memManagement.setForeground(Color.BLACK);
		memManagement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Member_Management(mem_id, mem_pw,temp,null);
				dispose();
			}
		});
		
		JLabel lblNewLabel; //????????? ???????????? ???????????? ???????????? ???????????? ??????
		if(mem_id.equals("admin")) {
			lblNewLabel = new JLabel("???????????? ??????!!!");
		}else {
			String userNick = jaDao.getUserInfo(mem_id,mem_pw);
			lblNewLabel = new JLabel(userNick + "??? ???????????????~!");
		}
		lblNewLabel.setBounds(704, 39, 183, 23);
		contentPane.add(lblNewLabel);
		

	}
}
