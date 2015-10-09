package santaclara.vista;

import javax.swing.JButton;
import javax.swing.JPanel;

import santaclara.controlador.ContCalendarios;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class CalendarioUI extends JPanel{
	private JButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,
					btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,
					btn21,btn22,btn23,btn24,btn25,btn26,btn27,btn28,btn29,btn30,
					btn31,btn32,btn33,btn34,btn35,btn36,btn37,btn38,btn39,btn40,btn41;
	private JFrame frame;
	
	private JPanel pnlCalendario;
	private JPanel pnlDia;
	private JPanel pnlDiaSemana;
	private JPanel pnDia;
	private JPanel pnlOperecion;
	private JButton btnNuevo;
	private JButton btnNuevo_1;
	private JButton btnAnterior;
	private JPanel pnlConsulta;
	
	private JLabel  lblFecha;
	private JDateChooser verFecha;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoUser;
	@SuppressWarnings("rawtypes")
	private JComboBox comboUsuario;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CalendarioUI(ContCalendarios contCalendarios) throws IOException{
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(1216,759);
		
		pnDia = new JPanel();
		pnDia.setBorder(new LineBorder(Color.white));
		pnDia.setSize(30, 30);
		pnDia.setBackground(Color.gray);
		setLayout(null);
		
		pnlCalendario = new JPanel();
		pnlCalendario.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2, true), "Calendario de Visitas", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
		pnlCalendario.setBounds(101, 12, 975, 660);
		pnlCalendario.setBackground(Color.DARK_GRAY);
		add(pnlCalendario);
		pnlCalendario.setLayout(null);
		
		pnlDiaSemana = new JPanel();
		pnlDiaSemana.setBounds(46, 76, 917, 34);
		pnlCalendario.add(pnlDiaSemana);
		pnlDiaSemana.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlDiaSemana.setBackground(Color.DARK_GRAY);
		pnlDiaSemana.setLayout(null);
		
		JLabel lblDomingo = new JLabel("          Domingo");
		lblDomingo.setBounds(3, 1, 130, 32);
		lblDomingo.setForeground(Color.WHITE);
		pnlDiaSemana.add(lblDomingo);
		
		JLabel lblLunes = new JLabel("          Lune");
		lblLunes.setBounds(133, 1, 130, 32);
		lblLunes.setForeground(Color.WHITE);
		pnlDiaSemana.add(lblLunes);
		
		JLabel lblMarte = new JLabel("          Marte");
		lblMarte.setBounds(263, 1, 130, 32);
		lblMarte.setForeground(Color.WHITE);
		pnlDiaSemana.add(lblMarte);
		
		JLabel lblMiercole = new JLabel("          Miercole");
		lblMiercole.setBounds(393, 1, 130, 32);
		lblMiercole.setForeground(Color.WHITE);
		pnlDiaSemana.add(lblMiercole);
		
		JLabel lblJueve = new JLabel("          Jueve");
		lblJueve.setBounds(523, 1, 130, 32);
		lblJueve.setForeground(Color.WHITE);
		pnlDiaSemana.add(lblJueve);
		
		JLabel lblVierne = new JLabel("          Vierne");
		lblVierne.setBounds(653, 1, 130, 32);
		lblVierne.setForeground(Color.WHITE);
		pnlDiaSemana.add(lblVierne);
		
		JLabel lblLune = new JLabel("          Sabado");
		lblLune.setBounds(783, 1, 130, 32);
		lblLune.setForeground(Color.WHITE);
		pnlDiaSemana.add(lblLune);
		
		pnlDia = new JPanel();
		pnlDia.setBounds(46, 108, 917, 536);
		pnlCalendario.add(pnlDia);
		pnlDia.setLayout(null);
		
		btn0 = new JButton();
		btn0.setBounds(2, 1, 130, 89);
		btn0.addActionListener(contCalendarios.actionBotones(0));
		pnlDia.add(btn0,0);
		
		btn1 = new JButton();
		btn1.setBounds(132, 1, 130, 89);
		btn1.addActionListener(contCalendarios.actionBotones(1));
		pnlDia.add(btn1,1);
		
		btn2 = new JButton();
		btn2.setBounds(262, 1, 130, 89);
		btn2.addActionListener(contCalendarios.actionBotones(2));
		pnlDia.add(btn2,2);
		
		btn3 = new JButton();
		btn3.setBounds(392, 1, 130, 89);
		btn3.addActionListener(contCalendarios.actionBotones(3));
		pnlDia.add(btn3,3);
		
		btn4 = new JButton();
		btn4.setBounds(522, 1, 130, 89);
		btn4.addActionListener(contCalendarios.actionBotones(4));
		pnlDia.add(btn4,4);
		
		btn5 = new JButton();
		btn5.setBounds(652, 1, 130, 89);
		btn5.addActionListener(contCalendarios.actionBotones(5));
		pnlDia.add(btn5,5);
		
		btn6 = new JButton();
		btn6.setBounds(782, 1, 130, 89);
		btn6.addActionListener(contCalendarios.actionBotones(6));
		pnlDia.add(btn6,6);		
		
		btn7 = new JButton();
		btn7.setBounds(2, 90, 130, 89);
		btn7.addActionListener(contCalendarios.actionBotones(7));
		pnlDia.add(btn7,7);
		
		btn8 = new JButton();
		btn8.setBounds(132, 90, 130, 89);
		btn8.addActionListener(contCalendarios.actionBotones(8));
		pnlDia.add(btn8,8);
		
		btn9 = new JButton();
		btn9.setBounds(262, 90, 130, 89);
		btn9.addActionListener(contCalendarios.actionBotones(9));
		pnlDia.add(btn9,9);
		
		btn10 = new JButton();
		btn10.setBounds(392, 90, 130, 89);
		btn10.addActionListener(contCalendarios.actionBotones(10));
		pnlDia.add(btn10,10);
		
		btn11 = new JButton();
		btn11.setBounds(522, 90, 130, 89);
		btn11.addActionListener(contCalendarios.actionBotones(11));
		pnlDia.add(btn11,11);
		
		btn12 = new JButton();
		btn12.setBounds(652, 90, 130, 89);
		btn12.addActionListener(contCalendarios.actionBotones(12));
		pnlDia.add(btn12,12);
		
		btn13 = new JButton();
		btn13.setBounds(782, 90, 130, 89);
		btn13.addActionListener(contCalendarios.actionBotones(13));
		pnlDia.add(btn13,13);
		
		btn14 = new JButton();
		btn14.setBounds(2, 179, 130, 89);
		btn14.addActionListener(contCalendarios.actionBotones(14));
		pnlDia.add(btn14,14);	
		
		btn15 = new JButton();
		btn15.setBounds(132, 179, 130, 89);
		btn15.addActionListener(contCalendarios.actionBotones(15));
		pnlDia.add(btn15,15);
		
		btn16 = new JButton();
		btn16.setBounds(262, 179, 130, 89);
		btn16.addActionListener(contCalendarios.actionBotones(16));
		pnlDia.add(btn16,16);	
		
		btn17 = new JButton();
		btn17.setBounds(392, 179, 130, 89);
		btn17.addActionListener(contCalendarios.actionBotones(17));
		pnlDia.add(btn17,17);	
		
		btn18 = new JButton();
		btn18.setBounds(522, 179, 130, 89);
		btn18.addActionListener(contCalendarios.actionBotones(18));
		pnlDia.add(btn18,18);	
		
		btn19 = new JButton();
		btn19.setBounds(652, 179, 130, 89);
		btn19.addActionListener(contCalendarios.actionBotones(19));
		pnlDia.add(btn19,19);
		
		btn20 = new JButton();
		btn20.setBounds(782, 179, 130, 89);
		btn20.addActionListener(contCalendarios.actionBotones(20));
		pnlDia.add(btn20,20);	
		
		btn21 = new JButton();
		btn21.setBounds(2, 268, 130, 89);
		btn21.addActionListener(contCalendarios.actionBotones(21));
		pnlDia.add(btn21,21);
		
		btn22 = new JButton();
		btn22.setBounds(132, 268, 130, 89);
		btn22.addActionListener(contCalendarios.actionBotones(22));
		pnlDia.add(btn22,22);
		
		btn23 = new JButton();
		btn23.setBounds(262, 268, 130, 89);
		btn23.addActionListener(contCalendarios.actionBotones(23));
		pnlDia.add(btn23,23);
		
		btn24 = new JButton();
		btn24.setBounds(392, 268, 130, 89);
		btn24.addActionListener(contCalendarios.actionBotones(24));
		pnlDia.add(btn24,24);
		
		btn25 = new JButton();
		btn25.setBounds(522, 268, 130, 89);
		btn25.addActionListener(contCalendarios.actionBotones(25));
		pnlDia.add(btn25,25);
		
		btn26 = new JButton();
		btn26.setBounds(652, 268, 130, 89);
		btn26.addActionListener(contCalendarios.actionBotones(26));
		pnlDia.add(btn26,26);
		
		btn27 = new JButton();
		btn27.setBounds(782, 268, 130, 89);
		btn27.addActionListener(contCalendarios.actionBotones(27));
		pnlDia.add(btn27,27);
		
		btn28 = new JButton();
		btn28.setBounds(2, 357, 130, 89);
		btn28.addActionListener(contCalendarios.actionBotones(28));
		pnlDia.add(btn28,28);
		
		btn29 = new JButton();
		btn29.setBounds(132, 357, 130, 89);
		btn29.addActionListener(contCalendarios.actionBotones(29));
		pnlDia.add(btn29,29);
		
		btn30 = new JButton();
		btn30.setBounds(262, 357, 130, 89);
		btn30.addActionListener(contCalendarios.actionBotones(30));
		pnlDia.add(btn30,30);
		
		btn31 = new JButton();
		btn31.setBounds(392, 357, 130, 89);
		btn31.addActionListener(contCalendarios.actionBotones(31));
		pnlDia.add(btn31,31);
		
		btn32 = new JButton();
		btn32.setBounds(522, 357, 130, 89);
		btn32.addActionListener(contCalendarios.actionBotones(32));
		pnlDia.add(btn32,32);
		
		btn33 = new JButton();
		btn33.setBounds(652, 357, 130, 89);
		btn33.addActionListener(contCalendarios.actionBotones(33));
		pnlDia.add(btn33,33);
		
		btn34 = new JButton();
		btn34.setBounds(782, 357, 130, 89);
		btn34.addActionListener(contCalendarios.actionBotones(34));
		pnlDia.add(btn34,34);
		
		btn35 = new JButton();
		btn35.setBounds(2, 446, 130, 89);
		btn35.addActionListener(contCalendarios.actionBotones(35));
		pnlDia.add(btn35,35);
		
		btn36 = new JButton();
		btn36.setBounds(132, 446, 130, 89);
		btn36.addActionListener(contCalendarios.actionBotones(36));
		pnlDia.add(btn36,36);
		
		btn37 = new JButton();
		btn37.setBounds(262, 446, 130, 89);
		btn37.addActionListener(contCalendarios.actionBotones(37));
		pnlDia.add(btn37,37);
		
		btn38 = new JButton();
		btn38.setBounds(392, 446, 130, 89);
		btn38.addActionListener(contCalendarios.actionBotones(38));
		pnlDia.add(btn38,38);
		
		btn39 = new JButton();
		btn39.setBounds(522, 446, 130, 89);
		btn39.addActionListener(contCalendarios.actionBotones(39));
		pnlDia.add(btn39,39);
		
		btn40 = new JButton();
		btn40.setBounds(652, 446, 130, 89);
		btn40.addActionListener(contCalendarios.actionBotones(40));
		pnlDia.add(btn40,40);
		
		btn41 = new JButton();
		btn41.setBounds(782, 446, 130, 89);
		btn41.addActionListener(contCalendarios.actionBotones(41));
		pnlDia.add(btn41,41);
		
		pnlOperecion = new JPanel();
		pnlOperecion.setForeground(Color.GRAY);
		pnlOperecion.setBorder(new LineBorder(new Color(196, 196, 196), 2, true));
		pnlOperecion.setBounds(46, 51, 917, 34);
		pnlOperecion.setBackground(Color.GRAY);
		pnlCalendario.add(pnlOperecion);
		pnlOperecion.setLayout(null);
		
		btnAnterior = new JButton("<");
		btnAnterior.setBounds(19, 2, 44, 22);
		btnAnterior.addActionListener(contCalendarios.mesAnterior());
		pnlOperecion.add(btnAnterior);
		
		lblFecha = new JLabel("fecha");
		lblFecha.setBounds(420, 1, 174, 22);
		lblFecha.setFont(new Font("URW Chancery L", Font.BOLD, 20));
		lblFecha.setForeground(Color.WHITE);
		pnlOperecion.add(lblFecha);
		
		btnNuevo = new JButton(">");
		btnNuevo.addActionListener(contCalendarios.mesNuevo());
		btnNuevo.setBounds(860, 2, 44, 22);
		pnlOperecion.add(btnNuevo);
		
		pnlConsulta = new JPanel();
		pnlConsulta.setBackground(Color.DARK_GRAY);
		pnlConsulta.setBounds(46, 15, 917, 35);
		pnlCalendario.add(pnlConsulta);
		pnlConsulta.setLayout(null);
		
		JLabel lblUsfg = new JLabel("Tipo Usuario:");
		lblUsfg.setBounds(12, 12, 108, 15);
		lblUsfg.setForeground(Color.WHITE);
		pnlConsulta.add(lblUsfg);
	
		comboTipoUser = new JComboBox();  
		comboTipoUser.setBounds(115, 7, 129, 24);
		comboTipoUser.setModel(new DefaultComboBoxModel(new String[] {"JefeVenta", "Concesionario"}));
		comboTipoUser.addActionListener(contCalendarios.ActivarComboTipo());
		pnlConsulta.add(comboTipoUser);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(256, 12, 65, 15);
		lblUsuario.setForeground(Color.WHITE);
		pnlConsulta.add(lblUsuario);	

		comboUsuario = new JComboBox();
		comboUsuario.setBounds(321, 7, 198, 24);
		comboUsuario.addActionListener(contCalendarios.ActivarComboUsuario());
		comboUsuario.setRenderer(contCalendarios.setRendererComboUsuario());
		pnlConsulta.add(comboUsuario);
		
		JLabel lblBuscarfecha = new JLabel("BuscarFecha:");
		lblBuscarfecha.setBounds(669, 12, 100, 15);
		lblBuscarfecha.setForeground(Color.WHITE);
		pnlConsulta.add(lblBuscarfecha);
		
		verFecha = new JDateChooser();
		verFecha.setBounds(766, 8, 139, 19);
		
		btnNuevo_1 = new JButton("Activar.");
		btnNuevo_1.setBounds(531, 9, 129, 21);
		btnNuevo_1.setIcon(new ImageIcon("/home/leo/git/santaclara/img/calendario/9.png"));
		btnNuevo_1.setForeground(Color.WHITE);
		btnNuevo_1.setBackground(Color.DARK_GRAY);
		pnlConsulta.add(btnNuevo_1);
		
	}
	
	public JButton getBtn0() {
		return btn0;
	}

	public void setBtn0(JButton btn0) {
		this.btn0 = btn0;
	}

	public JButton getBtn1() {
		return btn1;
	}

	public void setBtn1(JButton btn1) {
		this.btn1 = btn1;
	}

	public JButton getBtn2() {
		return btn2;
	}

	public void setBtn2(JButton btn2) {
		this.btn2 = btn2;
	}

	public JButton getBtn3() {
		return btn3;
	}

	public void setBtn3(JButton btn3) {
		this.btn3 = btn3;
	}

	public JButton getBtn4() {
		return btn4;
	}

	public void setBtn4(JButton btn4) {
		this.btn4 = btn4;
	}

	public JButton getBtn5() {
		return btn5;
	}

	public void setBtn5(JButton btn5) {
		this.btn5 = btn5;
	}

	public JButton getBtn6() {
		return btn6;
	}

	public void setBtn6(JButton btn6) {
		this.btn6 = btn6;
	}

	public JButton getBtn7() {
		return btn7;
	}

	public void setBtn7(JButton btn7) {
		this.btn7 = btn7;
	}

	public JButton getBtn8() {
		return btn8;
	}

	public void setBtn8(JButton btn8) {
		this.btn8 = btn8;
	}

	public JButton getBtn9() {
		return btn9;
	}

	public void setBtn9(JButton btn9) {
		this.btn9 = btn9;
	}

	public JButton getBtn10() {
		return btn10;
	}

	public void setBtn10(JButton btn10) {
		this.btn10 = btn10;
	}

	public JButton getBtn11() {
		return btn11;
	}

	public void setBtn11(JButton btn11) {
		this.btn11 = btn11;
	}

	public JButton getBtn12() {
		return btn12;
	}

	public void setBtn12(JButton btn12) {
		this.btn12 = btn12;
	}

	public JButton getBtn13() {
		return btn13;
	}

	public void setBtn13(JButton btn13) {
		this.btn13 = btn13;
	}

	public JButton getBtn14() {
		return btn14;
	}

	public void setBtn14(JButton btn14) {
		this.btn14 = btn14;
	}

	public JButton getBtn15() {
		return btn15;
	}

	public void setBtn15(JButton btn15) {
		this.btn15 = btn15;
	}
	
	public JButton getBtn16() {
		return btn16;
	}

	public void setBtn16(JButton btn16) {
		this.btn16 = btn16;
	}

	public JButton getBtn17() {
		return btn17;
	}

	public void setBtn17(JButton btn17) {
		this.btn17 = btn17;
	}

	public JButton getBtn18() {
		return btn18;
	}

	public void setBtn18(JButton btn18) {
		this.btn18 = btn18;
	}

	public JButton getBtn19() {
		return btn19;
	}

	public void setBtn19(JButton btn19) {
		this.btn19 = btn19;
	}

	public JButton getBtn20() {
		return btn20;
	}

	public void setBtn20(JButton btn20) {
		this.btn20 = btn20;
	}

	public JButton getBtn21() {
		return btn21;
	}

	public void setBtn21(JButton btn21) {
		this.btn21 = btn21;
	}

	public JButton getBtn22() {
		return btn22;
	}

	public void setBtn22(JButton btn22) {
		this.btn22 = btn22;
	}

	public JButton getBtn23() {
		return btn23;
	}

	public void setBtn23(JButton btn23) {
		this.btn23 = btn23;
	}

	public JButton getBtn24() {
		return btn24;
	}

	public void setBtn24(JButton btn24) {
		this.btn24 = btn24;
	}

	public JButton getBtn25() {
		return btn25;
	}

	public void setBtn25(JButton btn25) {
		this.btn25 = btn25;
	}

	public JButton getBtn26() {
		return btn26;
	}

	public void setBtn26(JButton btn26) {
		this.btn26 = btn26;
	}

	public JButton getBtn27() {
		return btn27;
	}

	public void setBtn27(JButton btn27) {
		this.btn27 = btn27;
	}

	public JButton getBtn28() {
		return btn28;
	}

	public void setBtn28(JButton btn28) {
		this.btn28 = btn28;
	}

	public JButton getBtn29() {
		return btn29;
	}

	public void setBtn29(JButton btn29) {
		this.btn29 = btn29;
	}

	public JButton getBtn30() {
		return btn30;
	}

	public void setBtn30(JButton btn30) {
		this.btn30 = btn30;
	}

	public JButton getBtn31() {
		return btn31;
	}

	public void setBtn31(JButton btn31) {
		this.btn31 = btn31;
	}

	public JButton getBtn32() {
		return btn32;
	}

	public void setBtn32(JButton btn32) {
		this.btn32 = btn32;
	}

	public JButton getBtn33() {
		return btn33;
	}

	public void setBtn33(JButton btn33) {
		this.btn33 = btn33;
	}

	public JButton getBtn34() {
		return btn34;
	}

	public void setBtn34(JButton btn34) {
		this.btn34 = btn34;
	}

	public JButton getBtn35() {
		return btn35;
	}

	public void setBtn35(JButton btn35) {
		this.btn35 = btn35;
	}

	public JButton getBtn36() {
		return btn36;
	}

	public void setBtn36(JButton btn36) {
		this.btn36 = btn36;
	}

	public JButton getBtn37() {
		return btn37;
	}

	public void setBtn37(JButton btn37) {
		this.btn37 = btn37;
	}

	public JButton getBtn38() {
		return btn38;
	}

	public void setBtn38(JButton btn38) {
		this.btn38 = btn38;
	}

	public JButton getBtn39() {
		return btn39;
	}

	public void setBtn39(JButton btn39) {
		this.btn39 = btn39;
	}

	public JButton getBtn40() {
		return btn40;
	}

	public void setBtn40(JButton btn40) {
		this.btn40 = btn40;
	}

	public JButton getBtn41() {
		return btn41;
	}

	public void setBtn41(JButton btn41) {
		this.btn41 = btn41;
	}

	public JPanel getPnlCalendario() {
		return pnlCalendario;
	}

	public void setPnlCalendario(JPanel pnlCalendario) {
		this.pnlCalendario = pnlCalendario;
	}

	public JPanel getPnlDia() {
		return pnlDia;
	}

	public void setPnlDia(JPanel pnlDia) {
		this.pnlDia = pnlDia;
	}

	public JPanel getPnlDiaSemana() {
		return pnlDiaSemana;
	}

	public void setPnlDiaSemana(JPanel pnlDiaSemana) {
		this.pnlDiaSemana = pnlDiaSemana;
	}

	public JPanel getPnDia() {
		return pnDia;
	}

	public void setPnDia(JPanel pnDia) {
		this.pnDia = pnDia;
	}

	public JPanel getPnlOperecion() {
		return pnlOperecion;
	}

	public void setPnlOperecion(JPanel pnlOperecion) {
		this.pnlOperecion = pnlOperecion;
	}

	public JButton getBtnNuevo() {
		return btnNuevo_1;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo_1 = btnNuevo;
	}

	public JButton getBtnAnterior() {
		return btnAnterior;
	}

	public void setBtnAnterior(JButton btnAnterior) {
		this.btnAnterior = btnAnterior;
	}

	public JPanel getPnlConsulta() {
		return pnlConsulta;
	}

	public void setPnlConsulta(JPanel pnlConsulta) {
		this.pnlConsulta = pnlConsulta;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboTipoUser() {
		return comboTipoUser;
	}

	@SuppressWarnings("rawtypes")
	public void setComboTipoUser(JComboBox comboTipoUser) {
		this.comboTipoUser = comboTipoUser;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboUsuario() {
		return comboUsuario;
	}

	@SuppressWarnings("rawtypes")
	public void setComboUsuario(JComboBox comboUsuario) {
		this.comboUsuario = comboUsuario;
	}
	
	public JLabel getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(JLabel lblFecha) {
		this.lblFecha = lblFecha;
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		//pnCalendario.setVisible(false);
		
	}
	
	public JButton getBtn(Integer index)
	{
		switch (index) {
		case 0:return this.getBtn0();
		case 1:return this.getBtn1();
		case 2:return this.getBtn2();
		case 3:return this.getBtn3();
		case 4:return this.getBtn4();
		case 5:return this.getBtn5();
		case 6:return this.getBtn6();
		case 7:return this.getBtn7();
		case 8:return this.getBtn8();
		case 9:return this.getBtn9();
		case 10:return this.getBtn10();
		case 11:return this.getBtn11();
		case 12:return this.getBtn12();
		case 13:return this.getBtn13();
		case 14:return this.getBtn14();
		case 15:return this.getBtn15();
		case 16:return this.getBtn16();
		case 17:return this.getBtn17();
		case 18:return this.getBtn18();
		case 19:return this.getBtn19();
		case 20:return this.getBtn20();
		case 21:return this.getBtn21();
		case 22:return this.getBtn22();
		case 23:return this.getBtn23();
		case 24:return this.getBtn24();
		case 25:return this.getBtn25();
		case 26:return this.getBtn26();
		case 27:return this.getBtn27();
		case 28:return this.getBtn28();
		case 29:return this.getBtn29();
		case 30:return this.getBtn30();
		case 31:return this.getBtn31();
		case 32:return this.getBtn32();	
		case 33:return this.getBtn33();
		case 34:return this.getBtn34();
		case 35:return this.getBtn35();
		case 36:return this.getBtn36();
		case 37:return this.getBtn37();	
		case 38:return this.getBtn38();
		case 39:return this.getBtn39();
		case 40:return this.getBtn40();
		case 41:return this.getBtn41();
		
		default:return null; 
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JDateChooser getVerFecha() {
		return verFecha;
	}

	public void setVerFecha(JDateChooser verFecha) {
		this.verFecha = verFecha;
	}
	
}