package vistas;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladores.LoginController;
import usuarios.Cliente;

public class LoginVista extends JFrame{
	
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	
	private LoginController controller;
	
	public LoginVista(LoginController controller) {
		this.controller = controller;
		
		setTitle("Login");
		setSize(350, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel labUsuario = new JLabel("Usuario: ");
		JTextField txtUsuario = new JTextField(15);
		
		JLabel labPass = new JLabel("Contraseña: ");
		JPasswordField txtPass = new JPasswordField(15);
		
		JButton btnLogin = new JButton("Iniciar sesión");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2,5,5));
		
		panel.add(labUsuario);
		panel.add(txtUsuario);
		panel.add(labPass);
		panel.add(txtPass);
		panel.add(new JLabel(""));
		panel.add(btnLogin);
		
		add(panel);
		
		btnLogin.addActionListener(e -> {
			
			String user = txtUsuario.getText();
			String pass = new String(txtPass.getPassword());
			
			Cliente cliente = controller.login(user, pass);
			
			if (cliente == null) {
				JOptionPane.showMessageDialog(this,  "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(this,  "Bienvenido, " + cliente.getNombreUsuario(), "Login exitoso", JOptionPane.INFORMATION_MESSAGE);
			
			ventanaPorRol(cliente);
			
		});
		
		setVisible(true);
		
	}

	private void ventanaPorRol(Cliente cliente) {
		
		if (cliente instanceof usuarios.Administrador) {
			new AdminVista();
		}
		
		else if (cliente instanceof usuarios.Organizador) {
			new OrganizadorVista();
		}
		
		else if (cliente instanceof usuarios.ClienteNatural) {
			new ClienteVista();
		}
		this.dispose();
	}
	

	
	
	
}
