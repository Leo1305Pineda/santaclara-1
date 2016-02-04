package santaclara.controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioConcesionario;
import santaclara.Servicio.ServicioJefeVenta;
import santaclara.Servicio.ServicioVisita;
import santaclara.modelo.Concesionario;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Usuario;
import santaclara.modelo.Visita;
import santaclara.vista.CalendarioUI;

public class ContCalendarios extends ContGeneral implements IContGeneral {
	CalendarioUI vista;
	@SuppressWarnings("deprecation")
	Integer mesActual = new Integer(new Date().getMonth());
	Integer yearActual = new Integer(new SimpleDateFormat("yyyy").format(new Date()));
	Integer v = new Integer(0);
	public ContCalendarios(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new CalendarioUI(this);
		CargarComboUsuario();
		for(int i = 0;i<=34;i++)(vista.getBtn(i)).setBackground(Color.lightGray);
		dibujar(vista,this);
		vista.quitarNuevo();
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
	        Calendar cal = new GregorianCalendar();
	        cal.setTimeInMillis(fch.getTime());
	        cal.add(Calendar.DATE, dias);
	        return new java.sql.Date(cal.getTimeInMillis());
	    }
	 
	 public static synchronized java.sql.Date restarFechasDias(java.sql.Date fch, int dias) {
	        Calendar cal = new GregorianCalendar();
	        cal.setTimeInMillis(fch.getTime());
	        cal.add(Calendar.DATE, -dias);
	        return new java.sql.Date(cal.getTimeInMillis());
	    }
	 public void setImagen(JButton boton,String img){
			boton.setIcon(new ImageIcon("img/calendario/"+img+".png"));
	 }

	@SuppressWarnings("deprecation")
	public void activarFechas(Date date) throws Exception {
		// TODO Auto-generated method stub
		mesActual = date.getMonth()+1;
		yearActual = new Integer(new SimpleDateFormat("yyyy").format(date));
		
		java.sql.Date fecha =  new java.sql.Date(date.getTime());// Fecha Actual
			
		java.sql.Date fechainicio = restarFechasDias(fecha,fecha.getDate());// Fecha Al primer Dia Del Mes
		fechainicio = sumarFechasDias(fechainicio, 1);
		if (fechainicio.getDay()!=0)fechainicio = restarFechasDias(fechainicio, fechainicio.getDay());//Fecha AL Primer Domingo
		
		
		crearCalendario(fechainicio);
		cargarImagen();
		
		vista.getVerFecha().setDateFormatString(new SimpleDateFormat("dd/MM/yyyy").format(date));
		
	}
	
	@SuppressWarnings("deprecation")
	void crearCalendario(java.sql.Date fechainicio){
		
		for(int i = 0;i <=41 ;i++)
		{
			Date dateValue = sumarFechasDias(fechainicio, i);
			
			if(i==15) vista.getLblFecha().setText(getMes(dateValue.getMonth())+". "+new SimpleDateFormat("yyyy").format(dateValue));
			
			String value = new String((new Integer(dateValue.getDate())).toString());
			
			(vista.getBtn(i)).setFont(new Font("URW Chancery L", Font.BOLD | Font.ITALIC, 40));
			(vista.getBtn(i)).setHorizontalTextPosition(SwingConstants.CENTER);
			(vista.getBtn(i)).setForeground(new Color(139, 0, 0));
			(vista.getBtn(i)).setToolTipText(new SimpleDateFormat("dd/MM/yyyy").format(dateValue));
			(vista.getBtn(i)).setText(value);
			vista.getBtn(i).setLayout(null);
			vista.getBtn(i).setIcon(new ImageIcon("img/calendario/calendar.png"));
				
			if(vista.getBtn(i).countComponents()>0)
			{
				vista.getBtn(i).remove((vista.getBtn(i)).getComponent(0));
			}
			
			if((dateValue.getMonth()+1)!=mesActual)(vista.getBtn(i)).setEnabled(false);
			else (vista.getBtn(i)).setEnabled(true);
			vista.getBtn(i).setBackground(Color.LIGHT_GRAY);
		}
	}
	
	@SuppressWarnings("deprecation")
	void cargarImagen2() throws NumberFormatException, IOException, ParseException{
		for(Visita visita: consultaVisitas(mesActual,yearActual))
		{
			System.out.println(new SimpleDateFormat("dd,MM,yyyy").format(visita.getFecha())+" = "+mesActual+"/"+yearActual);
			Integer pos = new Integer(0);
			for (int i = 0; i <= 41;i++)
			{
				if((vista.getBtn(i)).getText().equals("1"))
				{
					pos = i;
					break;
				}
			}
			Integer diaPos =(
					new Integer(
							new SimpleDateFormat("dd").format(visita.getFecha())))+pos-1;
			if(vista.getBtn(diaPos).countComponents()<1)
			{  
				//(getBtn(diaPos)).add(getPanelOption(0,1,visita.getFecha()),0);
			}
		}
	}
	
	void cargarImagen() throws Exception{
		
		List<List<Visita>> listaVisitas = new ServicioVisita().listaVisitas(consultaVisitas(mesActual,yearActual));
		List<List<Visita>> listaVisitasAux =new ServicioVisita().listaVisitas(consultaVisitas(mesActual,yearActual));
		while(!listaVisitas.isEmpty())
		{
			List<Visita> value = listaVisitas.remove(0);
			if (value!=null)
			{
				Integer pos = new Integer(0);
				for (int i = 0; i <= 41;i++)
				{
					if((vista.getBtn(i)).getText().equals("1"))
					{
						pos = i;
						break;
					}
				}
				Integer diaPos =(new Integer(
						new SimpleDateFormat("dd").format((value.get(0)).getFecha())))+pos-1;  
					(vista.getBtn(diaPos)).add(getPanelOption(
							new Integer(vista.getBtn(diaPos).getText()),listaVisitasAux),0);
			}
		}
	}
	
	public List<Visita> consultaVisitas(Integer mes,Integer anno) throws NumberFormatException, IOException
	{
		switch (vista.getComboTipoUser().getSelectedItem().toString()) 
		{
			case "JefeVenta":
				return new ServicioVisita().ConsultaJefeVenta((JefeVenta)vista.getComboUsuario().getSelectedItem(),mes,anno);
			case "Concesionario":
				return new ServicioVisita().ConsultaConcesionario((Concesionario)vista.getComboUsuario().getSelectedItem(),mes,anno);
			default:return new ServicioVisita().getVisitas();
		}
	}
	
	public String getMes(Integer mes)
	{
		
		switch (mes) {
		case 0: return "enero";				
		case 1: return "febrero";			
		case 2: return "marzo";				
		case 3: return "abril";				
		case 4: return "mayo";				
		case 5: return "junio";				
		case 6: return "julio";
		case 7: return "agosto";				
		case 8: return "septiembre";			
		case 9: return "octubre";				
		case 10: return "noviembre";				
		case 11: return "diciembre";				

		default:return "";
		}

	}
	
	
	
	@SuppressWarnings("rawtypes")
	public ListCellRenderer setRendererComboUsuario(){
		return new ListCellRenderer() {
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Usuario usuario = (Usuario)value;
			
				return new JLabel(usuario.getId().toString()+"  "+usuario.getNombre());	
			}
		};
	}
	
	public ActionListener ActivarComboTipo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CargarComboUsuario();
			}
		};
	}

	@SuppressWarnings("rawtypes")
	public void CargarComboUsuario(){
		if (vista.getComboTipoUser().getSelectedItem().equals("Concesionario")){
			List<Usuario> usuarios = new ArrayList<Usuario>();
			List<Concesionario> concesionarios= new ArrayList<Concesionario>();
		
			try {
				concesionarios = new ServicioConcesionario().getConcecionarios();
				for(Concesionario concesionario : concesionarios){
					usuarios.add((Usuario)concesionario);
				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			JComboBoxBinding jcomboUsuario = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,usuarios,vista.getComboUsuario());

			jcomboUsuario.bind();	
		}
		else{
			List<Usuario> usuarios = new ArrayList<Usuario>();
			List<JefeVenta> jefeVentas= new ArrayList<JefeVenta>();
	
		try {
			jefeVentas = new ServicioJefeVenta().getJefeVentas();
				for(JefeVenta jefeVenta : jefeVentas){
					usuarios.add((Usuario)jefeVenta);
				}
			} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		JComboBoxBinding jcomboUsuario = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,usuarios,vista.getComboUsuario());

		
			jcomboUsuario.bind();
		}

	}
	
	public ActionListener ActivarComboUsuario() throws IOException{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
						try {
							if (vista.getComboUsuario().getSelectedItem()!=null)
							{
								activarFechas(new Date());
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
		};
	}

	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActivarAtras(null);
			}
		};
	}
	
	@SuppressWarnings("rawtypes")
	public void setSelectedValue(JComboBox comboBox,Integer id)
    {	
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
        	comboBox.setSelectedIndex(i);
        	Boolean enc=false;
        	switch (comboBox.getSelectedItem().getClass().getName().toString()) 
        	{
			case "santaclara.modelo.Usuario":
				enc = (((Usuario)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

	public ActionListener mesNuevo() throws IOException{
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)  {
				// TODO Auto-generated method stub
				try {
					activarFechas(new java.sql.Date(
									(new SimpleDateFormat("dd/MM/yyyy").parse(15+"/"+(mesActual+1)+"/"+yearActual)).getTime()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		};
	
    }

	public ActionListener mesAnterior() {
		// TODO Auto-generated method stub
		return new 	 ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					activarFechas(new java.sql.Date((
							new SimpleDateFormat("dd/MM/yyyy").parse(15+"/"+(mesActual-1)+"/"+yearActual)).getTime()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		};
	}

	public ActionListener actionBotones(final int index) {
		// TODO Auto-generated method stubdate.getTime()
		return new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i = 0;i<=34;i++)(vista.getBtn(i)).setBackground(Color.lightGray);				
				(vista.getBtn(index)).setBackground(Color.GRAY); 
				
			}
		};
	}
	@SuppressWarnings("resource")
	public JPanel getPanelOption(Integer valueButton,final List<List<Visita>> listaVisitas) throws FileNotFoundException{
		
		JPanel panelConsultar = new JPanel();
		panelConsultar.setBounds(0, 4, 24, 48);
		panelConsultar.setLayout(null);
		panelConsultar.setBackground(Color.LIGHT_GRAY);
		
		final JButton button = new JButton();
		button.setFont(new Font("Dialog", Font.BOLD, 5));
		button.setToolTipText("Visitas Hechas el Dia:"+valueButton.toString());
		button.setBackground(panelConsultar.getBackground());
		button.setBounds(0, 0,24, 24);
		button.addActionListener(new ActionListener() {
				
		List<Visita> getVisitasEstadoTrue(){
			List<Visita> visitasAux = new ArrayList<Visita>();
			for(int i=0; i < listaVisitas.size() ;i++)
			{ 
				Scanner scaner = new Scanner(button.getToolTipText().toString());
				Scanner sc = new Scanner(scaner.skip("Visitas Hechas el Dia:").next());
				if( new Integer(sc.nextInt()).equals(i))
				{
					if(listaVisitas.get(i)!=null)
					{
						List<Visita> visitas = listaVisitas.get(i);
						
						for(int j = 0; j < visitas.size() ;j++)
						{
							Visita value = visitas.get(j) ;
							
							if (value.getEstado().booleanValue()==true)
							{
								visitasAux.add(value);
							}
						}
					}
	
				}
			}
			return visitasAux;
		}
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					try {
						List<Visita> visitasTrue = getVisitasEstadoTrue();
						if (visitasTrue.size()!=0)
						{
							ContVisitas contVisitas;
							contVisitas = new ContVisitas(getContPrincipal());
							contVisitas.getComboTipoUser().setSelectedIndex(vista.getComboTipoUser().getSelectedIndex());
							contVisitas.getComboUsuario().setSelectedIndex(vista.getComboUsuario().getSelectedIndex());
							//setSelectedValue(contVisitas.getComboUsuario(), visitasTrue.get(0).getUsuario().getId());
							contVisitas.activarBinding(visitasTrue);
						}
						else JOptionPane.showMessageDialog(new JPanel(), "No existen Visitas hechas");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		
		final JButton button2 = new JButton();
		button2.setFont(new Font("Dialog", Font.BOLD, 5));
		button2.setBounds(0, 24,24, 24);
		button2.setBackground(panelConsultar.getBackground());
		button2.setToolTipText("Visitas por Hacer para el Dia:"+valueButton.toString());
		button2.addActionListener(new ActionListener() {
			
		List<Visita> getVisitasEstadoFalse(){
			List<Visita> visitasAux = new ArrayList<Visita>(); 
			for(int i=0; i < listaVisitas.size() ;i++)
			{ 
				Scanner scaner = new Scanner(button2.getToolTipText().toString());
				Scanner sc = new Scanner(scaner.skip("Visitas por Hacer para el Dia:").next());
				if( new Integer(sc.nextInt()).equals(i))
				{				
					if(listaVisitas.get(i)!=null)
					{
						List<Visita> visitas = listaVisitas.get(i);
						for(int j = 0; j < visitas.size() ;j++)
						{
							Visita value = visitas.get(j) ;
							if (value.getEstado().booleanValue()==false)
							{
								visitasAux.add(value);
							}
						}
					}
				}
			}
			return visitasAux;
		}	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
					
				try {
					List<Visita> visitasFalse = getVisitasEstadoFalse();
					if (visitasFalse.size()!=0)
					{
						ContVisitas contVisitas = new ContVisitas(getContPrincipal());
						contVisitas.getComboTipoUser().setSelectedIndex(vista.getComboTipoUser().getSelectedIndex());
						contVisitas.getComboUsuario().setSelectedIndex(vista.getComboUsuario().getSelectedIndex());
						contVisitas.activarBinding(visitasFalse);
					}
					else JOptionPane.showMessageDialog(new JPanel(), "no existen visitas Por hacer");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		setImagen(button, "18");
		setImagen(button2, "15");
			
		panelConsultar.add(button,0);
		panelConsultar.add(button2,1);
		
		return panelConsultar;
	}

}
