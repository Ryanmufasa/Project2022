package javaoraDAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Login.Login;
import admin_page.Update_Page;
import admin_page.Update_Restorant;
import javaoraDBConn.JavaoraDBConn;
import javaoraMain.JavaoraMain;
import javaoraMain.JavaoraMain_2;
import javaoraVO.JavaoraComVO;
import javaoraVO.JavaoraMemVO;
import javaoraVO.JavaoraRestVO;

public class JavaoraDAO extends JFrame {

	private Connection con;
	private File confirmFile;
	private int z = 0;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int temp=0;
	
	private JPanel contentPane;
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
	}
	
	public JavaoraDAO() throws ClassNotFoundException, SQLException {
		con= new JavaoraDBConn().getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}


	public ArrayList<JavaoraRestVO> getAllInfo() throws SQLException { //전체 맛집리스트를 랜덤순서로 가져오는 객체
		ArrayList<JavaoraRestVO> rest_list = new ArrayList<JavaoraRestVO>();
		String sql = "SELECT * FROM restaurants ORDER BY rest_num ASC";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		restListUp(rest_list);
		
		ArrayList<JavaoraRestVO> temp = new ArrayList<JavaoraRestVO>();
		temp.add(0,rest_list.get(0));
		
		for(int i=0; i<=rest_list.size()*10 ; i++) {
			int idx = (int) (Math.random()*(rest_list.size()));
			temp.set(0, rest_list.get(0));
			rest_list.set(0, rest_list.get(idx));
			rest_list.set(idx, temp.get(0));
		}
		pstmt.close();
		rs.close();
		return rest_list;
	}
	
	public ArrayList<JavaoraMemVO> getAllMemInfo() throws SQLException { //전체 맛집리스트를 랜덤순서로 가져오는 객체
		ArrayList<JavaoraMemVO> mem_list = new ArrayList<JavaoraMemVO>();
		String sql = "SELECT * FROM members ORDER BY mem_num ASC";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		memListUp(mem_list);
		
		pstmt.close();
		rs.close();
		return mem_list;
	}
	
	public ArrayList<JavaoraMemVO> getMemSearch(String stridx, int choiceIdx) throws SQLException { //맛집이름으로 검색하는 객체 
		ArrayList<JavaoraMemVO> mem_list = new ArrayList<JavaoraMemVO>();
		String sql = null;
		System.out.println(choiceIdx);
		switch(choiceIdx) {
		case 1 :
			sql = "SELECT * FROM members WHERE LOWER(mem_name) LIKE LOWER(?) ORDER BY mem_num ASC";
			break;
		case 2 :
			 sql = "SELECT * FROM members WHERE LOWER(mem_nick) LIKE LOWER(?) ORDER BY mem_num ASC";
			break;
		}
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+stridx+"%");
		rs = pstmt.executeQuery();
		memListUp(mem_list);
		
		pstmt.close();
		rs.close();
		return mem_list;
	}

	public ArrayList<JavaoraRestVO> getAdminAllInfo() throws SQLException { //전체 맛집리스트 오름차순으로 가져오는 객체
		ArrayList<JavaoraRestVO> rest_list = new ArrayList<JavaoraRestVO>();
		String sql = "SELECT * FROM restaurants ORDER BY rest_num ASC";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		restListUp(rest_list);
		
		pstmt.close();
		rs.close();
		return rest_list;
	}

	public ArrayList<JavaoraRestVO> getPartInfo(String part) throws SQLException { //음식 종류별 리스트 검색객체
		ArrayList<JavaoraRestVO> rest_list = new ArrayList<JavaoraRestVO>();
		String sql = "SELECT * FROM restaurants WHERE rest_part = ? ORDER BY rest_num ASC";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, part);
		rs = pstmt.executeQuery();
		restListUp(rest_list);
		
		pstmt.close();
		rs.close();
		return rest_list;
	}


	public void disp(JPanel panel, ArrayList<JavaoraRestVO> rest_list, String mem_id) throws SQLException { //맛집 리스트 출력객체
		String rName="";
		Panel [] arrayPanel = new Panel [rest_list.size()];
		JLabel [] rest_photo = new JLabel [rest_list.size()];
		JLabel [] rest_title = new JLabel [rest_list.size()];
		System.out.println(rest_list.size());
		for(int i=0; i<rest_list.size();i++) {
			String idx = rest_list.get(i).getRest_num(); //rest_num 값을 저장한 객체
			String filePath = "..\\Project2022\\src\\image\\"+idx+".jpg";
			confirmFile = new File(filePath);
				arrayPanel[i] = new Panel();
				arrayPanel[i].setSize(200, 200);
				arrayPanel[i].setLayout(null);
				panel.add(arrayPanel[i]);
				
				arrayPanel[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new JavaoraMain_2(idx,mem_id,temp);  //맛집 상세페이지 생성자에 rest_num값을 저장한 객체를 포함하여 페이지를 불러옴
						dispose();
					}
				});
				
				rest_photo[i] = new JLabel();
				rest_photo[i].setHorizontalAlignment(SwingConstants.CENTER);
				if(confirmFile.exists()) {
				rest_photo[i].setIcon(new ImageIcon(JavaoraMain.class.getResource("/image/"+idx+".jpg")));
				}else {
				rest_photo[i].setIcon(new ImageIcon(JavaoraMain.class.getResource("/image/blank.jpg")));
				}
				rest_photo[i].setBounds(10, 10, 200, 150);
				arrayPanel[i].add(rest_photo[i]);
				rest_photo[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new JavaoraMain_2(idx,mem_id,temp);  //맛집 상세페이지 생성자에 rest_num값을 저장한 객체를 포함하여 페이지를 불러옴
						dispose();
						z++;
					}
				});
				
				
				rest_title[i] = new JLabel(rest_list.get(i).getRest_name());
				rest_title[i].setFont(new Font("굴림", Font.PLAIN, 15));
				rest_title[i].setHorizontalAlignment(SwingConstants.CENTER);
				rest_title[i].setBounds(10, 170, 200, 30);
				arrayPanel[i].add(rest_title[i]);
				rest_title[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new JavaoraMain_2(idx,mem_id,temp);  //맛집 상세페이지 생성자에 rest_num값을 저장한 객체를 포함하여 페이지를 불러옴
						dispose();
					}
				});
		}
	}
	
	public void adminDisp(JPanel panel, ArrayList<JavaoraRestVO> rest_list,String mem_id, String mem_pw) throws SQLException { //관리자 맛집 리스트 출력객체
		Panel [] arrayPanel = new Panel [rest_list.size()];
		JLabel [] rest_photo = new JLabel [rest_list.size()];
		JLabel [] rest_title = new JLabel [rest_list.size()];
		JButton [] delete = new JButton [rest_list.size()];
		JButton [] update = new JButton [rest_list.size()];
		
		System.out.println(rest_list.size());
		for(int i=0; i<rest_list.size();i++) {
			String idx = rest_list.get(i).getRest_num();
			String filePath = "..\\Project2022\\src\\image\\"+idx+".jpg";
			confirmFile = new File(filePath);
				arrayPanel[i] = new Panel();
				arrayPanel[i].setSize(200, 250);
				arrayPanel[i].setLayout(null);
				panel.add(arrayPanel[i]);
				
				rest_photo[i] = new JLabel();
				rest_photo[i].setHorizontalAlignment(SwingConstants.CENTER);
				if(confirmFile.exists()) {
				rest_photo[i].setIcon(new ImageIcon(JavaoraMain.class.getResource("/image/"+idx+".jpg")));
				}else {
					rest_photo[i].setIcon(new ImageIcon(JavaoraMain.class.getResource("/image/blank.jpg")));
				}
				rest_photo[i].setBounds(10, 10, 200, 150);
				arrayPanel[i].add(rest_photo[i]);
				
				rest_title[i] = new JLabel(rest_list.get(i).getRest_name());
				rest_title[i].setFont(new Font("굴림", Font.PLAIN, 15));
				rest_title[i].setHorizontalAlignment(SwingConstants.CENTER);
				rest_title[i].setBounds(10, 170, 200, 30);
				arrayPanel[i].add(rest_title[i]);
				
				update[i] = new JButton("수정");
				update[i].setFont(new Font("굴림", Font.PLAIN, 15));
				update[i].setBackground(Color.LIGHT_GRAY);
				update[i].setHorizontalAlignment(SwingConstants.CENTER);
				update[i].setBounds(10, 210, 100, 40);
				arrayPanel[i].add(update[i]);
				update[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new Update_Page(mem_id,mem_pw,temp,idx);
						dispose();
					}
				});
				
				
				delete[i] = new JButton("삭제");
				delete[i].setFont(new Font("굴림", Font.PLAIN, 15));
				delete[i].setBackground(Color.GRAY);
				delete[i].setHorizontalAlignment(SwingConstants.CENTER);
				delete[i].setBounds(100, 210, 100, 40);
				arrayPanel[i].add(delete[i]);
				delete[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int flag = JOptionPane.showConfirmDialog(null, idx+"번 맛집정보를 정말 삭제하시겠습니까?", "삭제확인", JOptionPane.YES_NO_OPTION);
						if(flag==0) {
						try {
							deleteStore(idx);
							new Update_Restorant(mem_id,mem_pw,temp,null);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}else {
							JOptionPane.showMessageDialog(null, "취소되었습니다.");
						}
					}
				});
		}
		
	}

	public ArrayList<JavaoraRestVO> uploadStore(String idx) throws SQLException { //맛집이름으로 검색하는 객체 
		ArrayList<JavaoraRestVO> rest_list = new ArrayList<JavaoraRestVO>();
		String sql = "SELECT * FROM restaurants WHERE rest_num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, idx);
		rs = pstmt.executeQuery();
		restListUp(rest_list);
		
		pstmt.close();
		rs.close();
		return rest_list;
	}

	public ArrayList<JavaoraRestVO> getSearch(String stridx) throws SQLException { //맛집이름으로 검색하는 객체 
		ArrayList<JavaoraRestVO> rest_list = new ArrayList<JavaoraRestVO>();
		String sql = "SELECT * FROM restaurants WHERE LOWER(rest_name) LIKE LOWER(?) ORDER BY rest_num ASC";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+stridx+"%");
		rs = pstmt.executeQuery();
		restListUp(rest_list);
		
		pstmt.close();
		rs.close();
		return rest_list;
	}



	public String getUserInfo(String mem_id, String mem_pw) throws SQLException { //사용자정보를 불러오는객체
		String sql = "SELECT mem_nick FROM members WHERE LOWER(mem_id) = ? AND LOWER(mem_pw) = ? ";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, mem_id);
		pstmt.setString(2, mem_pw);
		rs = pstmt.executeQuery();
		String userNick = null;
		if(rs.next()) {
			userNick = rs.getString("mem_nick");
		}else {
			userNick = "방문자";
		}
		pstmt.close();
		rs.close();
		return userNick;
	}
	
	public ArrayList<JavaoraRestVO> restListUp(ArrayList<JavaoraRestVO> rest_list) throws SQLException{
		while(rs.next()) {
			String rest_num = rs.getString("rest_num");
			String rest_name= rs.getString("rest_name");
			String rest_part= rs.getString("rest_part");
			String rest_phone= rs.getString("rest_phone");
			String rest_opent= rs.getString("rest_opent");
			String rest_closed= rs.getString("rest_closed");
			String rest_address= rs.getString("rest_address");
			
			JavaoraRestVO jaRvo = new JavaoraRestVO(rest_num, rest_name, rest_part, rest_phone, rest_opent,
					rest_closed, rest_address);
			rest_list.add(jaRvo);
		}
		return rest_list;
	}
	
	public ArrayList<JavaoraMemVO> memListUp(ArrayList<JavaoraMemVO> mem_list) throws SQLException{
		while(rs.next()) {
			long mem_num = rs.getLong("mem_num");
			String mem_name = rs.getString("mem_name");
			String mem_nick = rs.getString("mem_nick");
			String mem_id = rs.getString("mem_id");
			String mem_pw = rs.getString("mem_pw");
			String mem_phone = rs.getString("mem_phone");
			String mem_email = rs.getString("mem_email");
			Timestamp mem_regi = rs.getTimestamp("mem_regi");
			
			JavaoraMemVO jaMvo = new JavaoraMemVO(mem_num, mem_name, mem_nick, mem_id, mem_pw,
					mem_phone, mem_email, mem_regi);
			mem_list.add(jaMvo);
		}
		return mem_list;
	}
	
	public Object[][] getMemArray(ArrayList<JavaoraMemVO> mem_list) throws SQLException {
		JCheckBox [] delete = new JCheckBox [mem_list.size()];
		Object [][] memArray = new Object[mem_list.size()][9];
		for(int i=0;i < mem_list.size();i++) {
			for(int j=0;j<=7;j++) {
				switch(j) {
				case 0 : 
					memArray[i][j] = mem_list.get(i).getMem_num();
					break;
				case 1 : 
					memArray[i][j] = mem_list.get(i).getMem_name();
					break;
				case 2 : 
					memArray[i][j] = mem_list.get(i).getMem_nick();
					break;
				case 3 : 
					memArray[i][j] = mem_list.get(i).getMem_id();
					break;
				case 4 : 
					memArray[i][j] = mem_list.get(i).getMem_pw();
					break;
				case 5 : 
					memArray[i][j] = mem_list.get(i).getMem_phone();
					break;
				case 6 : 
					memArray[i][j] = mem_list.get(i).getMem_email();
					break;
				case 7 : 
					memArray[i][j] = mem_list.get(i).getMem_regi();
					break;
				case 8 :
					memArray[i][j] = 0;
				}
			}
		}
		return memArray;
	}


	public void deleteMem(long[] idx) throws SQLException {
		int deleteCnt=0;
		
		for(int i=0;i<idx.length;i++) {
			String sql = "DELETE FROM members WHERE mem_num = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, idx[i]);
			deleteCnt += pstmt.executeUpdate();
		}
		JOptionPane.showMessageDialog(null, deleteCnt+"개의 회원정보가 삭제되었습니다.");
	}
	
	public void deleteStore(String idx) throws SQLException {
		int deleteCnt=0;
		String filePath = "..\\Project2022\\src\\image\\"+idx+".jpg";
		String filePath2 = "..\\Project2022\\src\\image\\"+idx+"menu.jpg";
		File deleteFile = new File(filePath);
		File deleteFile2 = new File(filePath2);
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
		if(deleteFile2.exists()) {
			deleteFile2.delete();
		}
		
		String sql = "DELETE FROM restaurants WHERE rest_num = ? ";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, idx);
		deleteCnt += pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, deleteCnt+"개의 맛집정보가 삭제되었습니다.");
	}


	public JTable memDisp(ArrayList<JavaoraMemVO> mem_list, DefaultTableModel dTable, JTable table, JScrollPane scrollPane) throws SQLException {
		Object [] mem_Attribute = {"회원번호","이름","닉네임","ID","PW","전화번호","이메일","가입일시","삭제"};
		Object [][] mem_Array = getMemArray(mem_list);
		
		DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
		
		JCheckBox checkBox = new JCheckBox();
		dTable = new DefaultTableModel(mem_Array,mem_Attribute) {
			public boolean isCellEditable(int row, int column) {
				return column==8;
			}
		};
		table = new JTable();
		table.setModel(dTable);
		table.getColumn("삭제").setCellRenderer(dtcr1);
		table.getColumn("삭제").setCellEditor(new DefaultCellEditor(checkBox));
		table.getColumn("삭제").setPreferredWidth(40);
		table.setCellSelectionEnabled(false);
		scrollPane.setViewportView(table);
		checkBox.setHorizontalAlignment(JLabel.CENTER);
		checkBox.setVisible(true);
		
		return table;
	}


	public String register(String name, String part, String phone, String opent, String closed, String address) 
																						throws SQLException {
		int registCnt = 0;
		String sql = "INSERT INTO restaurants VALUES(seq_rest.NEXTVAL,?,?,?,?,?,?)"; 
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, part);
		pstmt.setString(3, phone);
		pstmt.setString(4, opent);
		pstmt.setString(5, closed);
		pstmt.setString(6, address);
		registCnt = pstmt.executeUpdate();
		
		JOptionPane.showMessageDialog(null, registCnt+"개의 맛집정보가 게시되었습니다.");
		
		String sql2 = "SELECT rest_num FROM restaurants WHERE rest_name = ? AND rest_phone = ? AND rest_address = ?";
		
		pstmt = con.prepareStatement(sql2);
		pstmt.setString(1, name);
		pstmt.setString(2, phone);
		pstmt.setString(3, address);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			String rest_num = rs.getString("rest_num");
			return rest_num;
		}
		return null;
	}
	
	public void registerUpdate(String num, String name, String part, String phone, String opent, String closed, String address)
																									throws SQLException {
		int registCnt = 0;
		
		String sql = "UPDATE restaurants SET rest_name = ?,rest_part = ?, rest_phone = ?, rest_opent = ?, rest_closed = ?, "
					+ "rest_address = ? WHERE rest_num = ?";
					
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, part);
		pstmt.setString(3, phone);
		pstmt.setString(4, opent);
		pstmt.setString(5, closed);
		pstmt.setString(6, address);
		pstmt.setString(7, num);
		registCnt = pstmt.executeUpdate();
		
		JOptionPane.showMessageDialog(null, registCnt+"개의 맛집정보가 수정되었습니다.");
	}

	public File fileOpenDlg() {
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File("C:\\img\\"));
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int returnVal = jfc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File f = jfc.getSelectedFile();
			return f;
		}
		return null;
	}

	public byte[] loadImage(File src) {
		try {
			FileInputStream fIn= new FileInputStream(src);
			
			BufferedInputStream bIn = new BufferedInputStream(fIn);
			
			byte[] buf = new byte[128];
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			int cnt = 0;
			
			while((cnt=bIn.read(buf,0,buf.length))!=-1) {
				bout.write(buf,0,cnt);
			}
			
			fIn.close();
			bout.close();
			return bout.toByteArray();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean saveStoreImage(String num, byte[] imgData) {
		String filePath = "..\\Project2022\\src\\image\\"+num+".jpg";
		File deleteFile = new File(filePath);
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
		try {
			FileOutputStream fOut = new FileOutputStream(filePath);
		BufferedOutputStream bOut = new BufferedOutputStream(fOut);
		bOut.write(imgData);
		bOut.close();
		return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveMenuImage(String num, byte[] imgData) {
		String filePath = "..\\Project2022\\src\\image\\"+num+"menu.jpg";
		File deleteFile = new File(filePath);
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
		try {
			FileOutputStream fOut = new FileOutputStream(filePath);
		BufferedOutputStream bOut = new BufferedOutputStream(fOut);
		bOut.write(imgData);
		bOut.close();
		return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public JavaoraRestVO getAllInfo_rest(String rest_numIdx) throws SQLException { // 식당 정보 가져오기

		String sql = "SELECT * FROM RESTAURANTS Where rest_num = ?";
//		String sql = "SELECT * FROM RESTAURANTS ";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, rest_numIdx);
		rs = pstmt.executeQuery();
		String rest_num = "";
		String rest_name = "";
		String rest_address = "";
		String rest_phone = "";
		String rest_opent = "";
		String rest_closed = "";
		String rest_part = "";

		if (rs.next()) {
			rest_num = rs.getString("rest_num");
			rest_name = rs.getString("REST_NAME");
			rest_phone = rs.getString("REST_PHONE");
			rest_opent = rs.getString("REST_OPENT");
			rest_closed = rs.getString("REST_CLOSED");
			rest_address = rs.getString("REST_ADDRESS");
			rest_part = rs.getString("rest_part");

		}
		JavaoraRestVO tv = new JavaoraRestVO(rest_num, rest_name, rest_part, rest_phone, rest_opent, 
				rest_closed, rest_address);

		// VO 객체에 넣기
		// tiarray.add(tv); // vo객체 >> collection에 넣기

		// while-end
		return tv;
	}
	
	public ArrayList<JavaoraComVO> getAllInfo_com(String rest_num) throws SQLException {
		ArrayList<JavaoraComVO> tiarray = new ArrayList<JavaoraComVO>();
		String sql = "select * from comments where rest_num = ? order by comm_star desc";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, rest_num);
		rs = pstmt.executeQuery();

		String rest_name; // 식당 이름
		String mem_nick; // 닉네임
		String comm_star; // 별점
		String comm_review; // 리뷰
		while (rs.next()) {
			rest_name = rs.getString("rest_name");
			mem_nick = rs.getString("mem_nick");
			comm_star = rs.getString("comm_star");
			comm_review = rs.getString("comm_review");
			JavaoraComVO tv = new JavaoraComVO(rest_name, mem_nick, comm_star, comm_review);
			// VO 객체에 넣기
			tiarray.add(tv); // vo객체 >> collection에 넣기

		}
		return tiarray;
	}
	
	public ArrayList<JavaoraMemVO> getAllInfo_mem(String mem_getid) throws SQLException {
		ArrayList<JavaoraMemVO> tiarray = new ArrayList<JavaoraMemVO>();
		String sql = "select * from members where mem_id= ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, mem_getid);
		rs = pstmt.executeQuery();
		long mem_num;
		String mem_name;
		String mem_nick;
		String mem_id;
		String mem_pw;
		String mem_phone;
		String mem_email;
		Timestamp mem_regi;
		while (rs.next()) {
			mem_num = rs.getLong("mem_num");
			mem_name = rs.getString("mem_name");
			mem_nick = rs.getString("mem_nick");
			mem_id = rs.getString("mem_id");
			mem_pw = rs.getString("mem_pw");
			mem_phone = rs.getString("mem_phone");
			mem_email = rs.getString("mem_email");
			mem_regi = rs.getTimestamp("mem_regi");

			JavaoraMemVO tv = new JavaoraMemVO(mem_num, mem_name, mem_nick, mem_id, mem_pw, mem_phone, mem_email,
					mem_regi);
			// VO 객체에 넣기
			tiarray.add(tv); // vo객체 >> collection에 넣기

		}

		return tiarray;

	}
	
	public boolean insert_nametel(String rest_name, String mem_nick, int abc, String comm_review) { // 삽입
		String sql = "insert into comments values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rest_name);
			pstmt.setString(2, mem_nick);
			pstmt.setInt(3, abc);
			pstmt.setString(4, comm_review);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert Exception");
			return false;
		}
		return true;
	}
	
	
	public boolean insert_nametel(String rest_num, String rest_name, String mem_nick, String mem_id, 
									int abc, String comm_review) throws SQLException { // 삽입
		String sql2 = "INSERT INTO comments VALUES(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, rest_num);
			pstmt.setString(2, rest_name);
			pstmt.setString(3, mem_nick);
			pstmt.setString(4, mem_id);
			pstmt.setInt(5, abc);
			pstmt.setString(6, comm_review);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert Exception");
			return false;
		}
		return true;
	}

}
