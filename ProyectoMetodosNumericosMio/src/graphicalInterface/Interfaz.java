package graphicalInterface;

import interpolation.GeneradorDeData;
import interpolation.InterpolationsManager;
import interpolation.LaGrange;
import interpolation.Lectura;
import interpolation.NewRapNP;
import interpolation.NewRapP;
import interpolation.NewtonAscendente;
import interpolation.NewtonDescendente;
import interpolation.Steffensen;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import linearSystems.Gauss;
import linearSystems.MetodoDeGauss;
import linearSystems.SistemsManager;
import numericIntegration.CuadraturaDeGauss;
import numericIntegration.IntegralsManager;
import numericIntegration.NewtonCotes;
import numericIntegration.Simpson;
import numericIntegration.Trapecios;
import pdfCreator.PDFCreator;
import differentialEcuations.DiffEcuManager;
import differentialEcuations.Euler;
import differentialEcuations.EulerMejorado;
import differentialEcuations.EulerModificado;
import differentialEcuations.RungeKutta;

public class Interfaz {

	private JFrame frame;
	private JButton btnBack;
	private JLabel lblTituloDelPdf;
	private JTextField textFieldTituloPDF;
	private JButton btnCrearPdf;
	private JTextField txtTrapeciosFunc;
	private JTextField txtTrapeciosLimInf;
	private JTextField txtTrapeciosLimSup;
	private JTextField txtTrapeciosN;
	private JTextField txtSimpsonFunc;
	private JTextField txtSimpsonLimInf;
	private JTextField txtSimpsonLimSup;
	private JTextField txtSimpsonN;
	private JPanel menuPrincipal;
	private JPanel menuIntegrales;
	private JPanel menuSistemas;
	private JPanel trapecios;
	private JPanel simpson;
	private JPanel newtonCotes;
	private JPanel cuadraturaDeGauss;
	private JRadioButton rdbtnTrapeciosIntegral;
	private JRadioButton rdbtnTrapeciosArea;
	private JLabel lblTrapeciosIOP;
	private JLabel lblTrapeciosEOP;
	private JRadioButton rdbtnSimpsonIntegral;
	private JRadioButton rdbtnSimpsonArea;
	private JLabel lblSimpsonIOP;
	private JLabel lblSimpsonEOP;
	private JTextField txtNewCotIF;
	private JTextField txtNewCotIN;
	private JTextField txtNewCotXS;
	private JTextField txtNewCotXI;
	private JTextField txtNewCotYI;
	private JTextField txtNewCotYS;
	private JTextField txtNewCotZS;
	private JTextField txtNewCotZI;
	private JRadioButton selNewCotIS;
	private JRadioButton selNewCotID;
	private JRadioButton selNewCotIDF;
	private JRadioButton selNewCotIT;
	private JLabel lblNewCotIOP;
	private JTextField txtCuadGaussIF;
	private JTextField txtCuadGaussIN;
	private JTextField txtCuadGaussXS;
	private JTextField txtCuadGaussXI;
	private JTextField txtCuadGaussYI;
	private JTextField txtCuadGaussYS;
	private JTextField txtCuadGaussZS;
	private JTextField txtCuadGaussZI;
	private JRadioButton selCuadGaussIS;
	private JRadioButton selCuadGaussID;
	private JRadioButton selCuadGaussIDF;
	private JRadioButton selCuadGaussIT;
	private JLabel lblCuadGaussIOP;
	private JPanel gauss;
	private JTextField txtGaussICE;
	private JLabel lblGaussIEI;
	private JButton btnGaussAE;
	private JLabel lblGaussEA;
	private JScrollPane sPGaussMP;
	private JTextPane txtGaussIM;
	private JLabel lblGaussOPL;
	private JScrollPane sPGaussOPP;
	private JLabel lblGaussOP;
	private JPanel jacobi;
	private JTextField txtJacobiICE;
	private JLabel lblJacobiIEI;
	private JButton btnJacobiAE;
	private JLabel lblJacobiEA;
	private JScrollPane sPJacobiMP;
	private JTextPane txtJacobiIM;
	private JLabel lblJacobiOPL;
	private JScrollPane sPJacobiOPP;
	private JLabel lblJacobiOP;
	private JPanel gaussSeidel;
	private JTextField txtGaussSeidelICE;
	private JLabel lblGaussSeidelIEI;
	private JButton btnGaussSeidelAE;
	private JLabel lblGaussSeidelEA;
	private JScrollPane sPGaussSeidelMP;
	private JTextPane txtGaussSeidelIM;
	private JLabel lblGaussSeidelOPL;
	private JScrollPane sPGaussSeidelOPP;
	private JLabel lblGaussSeidelOP;
	private JTextField txtJacobiDI;
	private JLabel lblJacobiDatosIniciales;
	private JTextField txtGaussSeidelDI;
	private JLabel lblGaussSeidelDatosIniciales;
	private JPanel menuInterpolacion;
	private JPanel newtonRaphson;
	private JLabel nrTitle;
	private JLabel nrFuncLabel;
	private JTextField nrFuncInput;
	private JLabel nrFuncGradeLabel;
	private JTextField nrFuncGradeInput;
	private JLabel nrLLabel;
	private JTextField nrLInput;
	private JLabel nrPrimeLLabel;
	private JTextField nrPrimeLInput;
	private JButton nrAcceptButton;
	private JLabel nrAnswersLabel;
	private JLabel nrX0Label;
	private JTextField nrX0Input;
	private JPanel newtonAscendente;
	private JLabel naTitle;
	private JRadioButton naNumValOption;
	private JLabel naDataAmountLabel;
	private JTextField naDataAmountInput;
	private JScrollPane naDataPanel;
	private JLabel naEvaluationLabel;
	private JTextField naEvaluationInput;
	private JButton naCalcButton;
	private JLabel naAnswerLabel;
	private JScrollPane naAnswerPanel;
	private JLabel naAnswerOutput;
	private JPanel newtonDescendente;
	private JLabel ndTitle;
	private JRadioButton ndNumValOption;
	private JLabel ndDataAmountLabel;
	private JTextField ndDataAmountInput;
	private JScrollPane ndDataPanel;
	private JLabel ndEvaluationLabel;
	private JTextField ndEvaluationInput;
	private JButton ndCalcButton;
	private JLabel ndAnswerLabel;
	private JScrollPane ndAnswerPanel;
	private JLabel ndAnswerOutput;
	private JPanel laGrange;
	private JLabel lgTitle;
	private JRadioButton lgNumValOption;
	private JLabel lgDataAmountLabel;
	private JTextField lgDataAmountInput;
	private JScrollPane lgDataPanel;
	private JLabel lgEvaluationLabel;
	private JTextField lgEvaluationInput;
	private JButton lgCalcButton;
	private JLabel lgAnswerLabel;
	private JScrollPane lgAnswerPanel;
	private JLabel lgAnswerOutput;
	private JTextField textFieldErrorNR;
	private JPanel aitken;
	private JPanel steffensen;
	private JTextField aFuncInput;
	private JTextField aX0Input;
	private JTextField aX2Input;
	private JLabel stfTitle;
	private JLabel stfFuncLabel;
	private JTextField stfFuncInput;
	private JTextField stfX0Input;
	private JTextField stfErrorInput;
	private JButton stfCalcButton;
	private JPanel comparacionIntegracion;
	private JTextField txtCompIntIF;
	private JTextField txtCompIntIN;
	private JTextField txtCompIntXS;
	private JTextField txtCompIntXI;
	private JTextField txtCompIntYI;
	private JTextField txtCompIntYS;
	private JTextField txtCompIntZS;
	private JTextField txtCompIntZI;
	private JRadioButton selCompIntIS;
	private JRadioButton selCompIntID;
	private JRadioButton selCompIntIDF;
	private JRadioButton selCompIntIT;
	private JLabel lblCompIntIOP;
	private JScrollPane scrlCompIntAnswerPanel;
	private JTextField txtCompIntNP;
	private JButton btnComparacionIntegrales;
	private JLabel lblIntroducirNpar;
	private JRadioButton rdbtnCompIntArea;
	private JPanel comparacionSistemas;
	private JTextField txtComparacionSistemasICE;
	private JLabel lblComparacionSistemasIEI;
	private JButton btnComparacionSistemasAE;
	private JLabel lblComparacionSistemasEA;
	private JScrollPane sPComparacionSistemasMP;
	private JTextPane txtComparacionSistemasIM;
	private JLabel lblComparacionSistemasOPL;
	private JScrollPane sPComparacionSistemasOPP;
	private JLabel lblComparacionSistemasOP;
	private JTextField txtComparacionSistemasDI;
	private JLabel lblComparacionSistemasDatosIniciales;
	private JButton btnComparacionEntreMetodos;
	private JLabel lblComparacionSistemasICE;
	private JButton btnComparacionSistemasACE;
	private JRadioButton rdbtnComparacionSistemasAcotar;
	private JLabel lblComparacionSistemasAproxL;
	private JTextField txtComparacionSistemasAprox;
	private JPanel comparacionPorTabla;
	private JLabel cptTitle;
	private JLabel cptDataAmountLabel;
	private JTextField cptDataAmountInput;
	private JScrollPane cptDataPanel;
	private JLabel cptEvaluationLabel;
	private JTextField cptEvaluationInput;
	private JButton cptCalcButton;
	private JLabel cptAnswerLabel;
	private JScrollPane cptAnswerPanel;
	private JLabel cptAnswerOutput;
	private JButton btnComparacionMetodosDe;
	private JPanel comparacionSinTabla;
	private JLabel cstFuncLabel;
	private JTextField cstFuncInput;
	private JTextField cstX0Input;
	private JTextField cstErrorInput;
	private JButton cstCalcButton;
	private JButton btnAitkenSteffensenNewtonraphson;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private JPanel menuEcuacionesDiferenciales;
	private JButton btnEuler;
	private JButton btnRungeKutta;
	private JButton btnComparacionDeMetodos_3;
	private JPanel euler;
	private JLabel lblEuler;
	private JRadioButton rdEulerPrimerOrden;
	private JRadioButton rdEulerSegundoOrden;
	private JRadioButton rdEulerIteraciones;
	private JTextField txtEulerF;
	private JTextField txtEulerG;
	private JTextField txtEulerX0;
	private JTextField txtEulerY0;
	private JTextField txtEulerH;
	private JTextField txtEulerEval;
	private JLabel lblT;
	private JTextField txtEulerT0;
	private JPanel rungeKutta;
	private JLabel lblRungekutta;
	private JRadioButton rdRungePrimer;
	private JRadioButton rdRungeSegundo;
	private JRadioButton rdRungeIteraciones;
	private JLabel lblRungeF;
	private JTextField txtRungeF;
	private JLabel label_2;
	private JTextField txtRungeG;
	private JLabel lblXt;
	private JTextField txtRungeX;
	private JLabel label_4;
	private JTextField txtRungeY;
	private JLabel label_5;
	private JTextField txtRungeH;
	private JLabel lblEvaluacionn;
	private JTextField txtRungeEval;
	private JButton button;
	private JLabel label_8;
	private JScrollPane scrollPane;
	private JLabel label_9;
	private JTextField txtRungeT;
	private JPanel eulerMejorado;
	private JLabel lblEulerMejorado;
	private JRadioButton rdEulerMejoradoPrimerOrden;
	private JRadioButton rdEulerMejoradoSegundoOrden;
	private JRadioButton rdEulerMejoradoIteraciones;
	private JLabel label_1;
	private JTextField txtEulerMejoradoF;
	private JLabel label_3;
	private JTextField txtEulerMejoradoG;
	private JLabel label_6;
	private JTextField txtEulerMejoradoX0;
	private JLabel label_10;
	private JTextField txtEulerMejoradoY0;
	private JLabel label_11;
	private JTextField txtEulerMejoradoH;
	private JLabel label_12;
	private JTextField txtEulerMejoradoEval;
	private JScrollPane scrollPane_1;
	private JButton button_2;
	private JLabel label_14;
	private JLabel label_15;
	private JTextField txtEulerMejoradoT0;
	private JLabel lblEulerMejoradoOutput;
	private JPanel eulerModificado;
	private JLabel lblEulerModificado;
	private JRadioButton rdEulerModificadoPrimerOrden;
	private JRadioButton rdEulerModificadoSegundoOrden;
	private JRadioButton rdEulerModificadoIteraciones;
	private JLabel label_16;
	private JTextField txtEulerModificadoF;
	private JLabel label_17;
	private JTextField txtEulerModificadoG;
	private JLabel label_18;
	private JTextField txtEulerModificadoX0;
	private JLabel label_19;
	private JTextField txtEulerModificadoY0;
	private JLabel label_20;
	private JTextField txtEulerModificadoH;
	private JLabel label_21;
	private JTextField txtEulerModificadoEval;
	private JScrollPane scrollPane_2;
	private JButton button_4;
	private JLabel label_23;
	private JLabel label_24;
	private JTextField txtEulerModificadoT0;
	private JLabel lblEulerModificadoOutput;
	private JPanel comparacionEcuDif;
	private JLabel lblComparacionDeMetodos;
	private JRadioButton rdCompEcuDifPrimerOrden;
	private JRadioButton rdCompEcuDifSegundoOrden;
	private JRadioButton rdCompEcuDifIteraciones;
	private JLabel label_34;
	private JTextField txtCompEcuDifF;
	private JLabel label_35;
	private JTextField txtCompEcuDifG;
	private JLabel label_36;
	private JTextField txtCompEcuDifX0;
	private JLabel label_37;
	private JTextField txtCompEcuDifY0;
	private JLabel label_38;
	private JTextField txtCompEcuDifH;
	private JLabel label_39;
	private JTextField txtCompEcuDifEval;
	private JScrollPane scrollPane_4;
	private JButton button_8;
	private JLabel label_41;
	private JLabel label_42;
	private JTextField txtCompEcuDifT0;
	private JLabel lblCompEcuDifOutput;
	private JButton btnEcuacionesDiferenciales;
	private JTextField respuestaConvergencia;
	private JButton btnConvergencia;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interfaz() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("M\u00E9todos Num\u00E9ricos");
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(0, 0, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		menuPrincipal = new JPanel();
		menuPrincipal.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(menuPrincipal, "name_1096909188540482");
		menuPrincipal.setLayout(null);
		menuPrincipal.setVisible(true);

		JLabel lblMtodosNumricos = new JLabel("Escoja una opci\u00F3n");
		lblMtodosNumricos.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblMtodosNumricos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMtodosNumricos.setBounds(214, 13, 218, 52);
		menuPrincipal.add(lblMtodosNumricos);

		JButton btnIntegralesNumericas = new JButton("Integrales Definidas");
		btnIntegralesNumericas.setHorizontalAlignment(SwingConstants.LEFT);
		btnIntegralesNumericas.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnIntegralesNumericas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuIntegrales.add(btnBack);
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(true);
				menuSistemas.setVisible(false);
				trapecios.setVisible(false);
				simpson.setVisible(false);
				newtonCotes.setVisible(false);
				cuadraturaDeGauss.setVisible(false);
			}
		});
		btnIntegralesNumericas.setBounds(12, 100, 308, 52);
		menuPrincipal.add(btnIntegralesNumericas);

		JButton btnS = new JButton("Sistema de Ecuaciones");
		btnS.setHorizontalAlignment(SwingConstants.LEFT);
		btnS.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSistemas.add(btnBack);
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(false);
				menuSistemas.setVisible(true);
				trapecios.setVisible(false);
				simpson.setVisible(false);
				newtonCotes.setVisible(false);
				cuadraturaDeGauss.setVisible(false);
			}
		});
		btnS.setBounds(326, 100, 308, 52);
		menuPrincipal.add(btnS);

		menuIntegrales = new JPanel();
		menuIntegrales.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(menuIntegrales, "name_1096931654182272");
		menuIntegrales.setLayout(null);
		menuIntegrales.setVisible(false);

		JLabel lblIntegralesNumericas = new JLabel("Integrales Definidas");
		lblIntegralesNumericas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblIntegralesNumericas.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntegralesNumericas.setBounds(53, 11, 329, 48);
		menuIntegrales.add(lblIntegralesNumericas);

		JButton btnTrapecios = new JButton("M\u00E9todo de trapecios");
		btnTrapecios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTrapecios.setHorizontalAlignment(SwingConstants.LEFT);
		btnTrapecios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(false);
				menuSistemas.setVisible(false);
				menuIntegrales.remove(btnBack);
				trapecios.add(btnBack);
				trapecios.add(lblTituloDelPdf);
				trapecios.add(btnCrearPdf);
				trapecios.add(textFieldTituloPDF);
				trapecios.setVisible(true);
				simpson.setVisible(false);
				newtonCotes.setVisible(false);
				cuadraturaDeGauss.setVisible(false);
			}
		});
		btnTrapecios.setBounds(89, 73, 264, 35);
		menuIntegrales.add(btnTrapecios);

		JButton btnSimpson = new JButton("M\u00E9todo de Simpson");
		btnSimpson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSimpson.setHorizontalAlignment(SwingConstants.LEFT);
		btnSimpson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(false);
				menuIntegrales.remove(btnBack);
				menuSistemas.setVisible(false);
				trapecios.setVisible(false);
				simpson.add(btnBack);
				simpson.add(lblTituloDelPdf);
				simpson.add(btnCrearPdf);
				simpson.add(textFieldTituloPDF);
				simpson.setVisible(true);
				newtonCotes.setVisible(false);
				cuadraturaDeGauss.setVisible(false);
			}
		});
		btnSimpson.setBounds(89, 121, 264, 35);
		menuIntegrales.add(btnSimpson);

		JButton btnNewtonCotes = new JButton("M\u00E9todo de Newton-Cotes");
		btnNewtonCotes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewtonCotes.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewtonCotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(false);
				menuSistemas.setVisible(false);
				menuIntegrales.remove(btnBack);
				trapecios.setVisible(false);
				simpson.setVisible(false);
				newtonCotes.setVisible(true);
				newtonCotes.add(btnBack);
				newtonCotes.add(lblTituloDelPdf);
				newtonCotes.add(btnCrearPdf);
				newtonCotes.add(textFieldTituloPDF);
				cuadraturaDeGauss.setVisible(false);
			}
		});
		btnNewtonCotes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewtonCotes.setBounds(89, 169, 264, 35);
		menuIntegrales.add(btnNewtonCotes);

		JButton btnCuadraturaDeGauss = new JButton(
				"M\u00E9todo de la cuadratura de Gauss");
		btnCuadraturaDeGauss.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCuadraturaDeGauss.setHorizontalAlignment(SwingConstants.LEFT);
		btnCuadraturaDeGauss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(false);
				menuSistemas.setVisible(false);
				menuIntegrales.remove(btnBack);
				trapecios.setVisible(false);
				simpson.setVisible(false);
				newtonCotes.setVisible(false);
				cuadraturaDeGauss.setVisible(true);
				cuadraturaDeGauss.add(btnBack);
				cuadraturaDeGauss.add(lblTituloDelPdf);
				cuadraturaDeGauss.add(btnCrearPdf);
				cuadraturaDeGauss.add(textFieldTituloPDF);
			}
		});
		btnCuadraturaDeGauss.setBounds(89, 217, 264, 35);
		menuIntegrales.add(btnCuadraturaDeGauss);

		btnComparacionIntegrales = new JButton("Comparacion entre metodos");
		btnComparacionIntegrales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuIntegrales.setVisible(false);
				menuIntegrales.remove(btnBack);
				comparacionIntegracion.setVisible(true);
				comparacionIntegracion.add(btnBack);
				comparacionIntegracion.add(textFieldTituloPDF);
				comparacionIntegracion.add(btnCrearPdf);
				comparacionIntegracion.add(lblTituloDelPdf);
			}
		});
		btnComparacionIntegrales.setHorizontalAlignment(SwingConstants.LEFT);
		btnComparacionIntegrales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnComparacionIntegrales.setBounds(89, 265, 264, 35);
		menuIntegrales.add(btnComparacionIntegrales);

		menuSistemas = new JPanel();
		menuSistemas.setBackground(Color.LIGHT_GRAY);
		menuSistemas.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(menuSistemas, "name_1096935960721276");
		menuSistemas.setLayout(null);
		menuSistemas.setVisible(false);

		JLabel lblSistemasLineales = new JLabel("Resoluci\u00F3n de Ecuaciones");
		lblSistemasLineales.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSistemasLineales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemasLineales.setBounds(29, 11, 370, 39);
		menuSistemas.add(lblSistemasLineales);

		JButton btnGaussSeidel_1 = new JButton("M\u00E9todo de Gauss-Seidel ");
		btnGaussSeidel_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnGaussSeidel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGaussSeidel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(false);
				menuSistemas.setVisible(false);
				menuSistemas.remove(btnBack);
				trapecios.setVisible(false);
				simpson.setVisible(false);
				newtonCotes.setVisible(false);
				cuadraturaDeGauss.setVisible(false);
				gauss.setVisible(false);
				jacobi.setVisible(false);
				gaussSeidel.setVisible(true);
				gaussSeidel.add(btnBack);
				gaussSeidel.add(lblTituloDelPdf);
				gaussSeidel.add(btnCrearPdf);
				btnCrearPdf.setVisible(false);
				gaussSeidel.add(textFieldTituloPDF);
				textFieldTituloPDF.setVisible(false);
				lblTituloDelPdf.setVisible(false);
			}
		});
		btnGaussSeidel_1.setBounds(39, 128, 273, 35);
		menuSistemas.add(btnGaussSeidel_1);

		JButton btnJacobi = new JButton("M\u00E9todo de Jacobi");
		btnJacobi.setHorizontalAlignment(SwingConstants.LEFT);
		btnJacobi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnJacobi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(false);
				menuSistemas.setVisible(false);
				trapecios.setVisible(false);
				simpson.setVisible(false);
				newtonCotes.setVisible(false);
				cuadraturaDeGauss.setVisible(false);
				gauss.setVisible(false);
				menuSistemas.remove(btnBack);
				jacobi.add(btnBack);
				jacobi.add(lblTituloDelPdf);
				jacobi.add(btnCrearPdf);
				btnCrearPdf.setVisible(false);
				jacobi.add(textFieldTituloPDF);
				textFieldTituloPDF.setVisible(false);
				lblTituloDelPdf.setVisible(false);
				jacobi.setVisible(true);
				gaussSeidel.setVisible(false);
			}
		});
		btnJacobi.setBounds(39, 63, 273, 35);
		menuSistemas.add(btnJacobi);

		JButton btnGauss = new JButton("M\u00E9todo de Gauss");
		btnGauss.setHorizontalAlignment(SwingConstants.LEFT);
		btnGauss.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGauss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuIntegrales.setVisible(false);
				menuSistemas.setVisible(false);
				menuSistemas.remove(btnBack);
				trapecios.setVisible(false);
				simpson.setVisible(false);
				newtonCotes.setVisible(false);
				cuadraturaDeGauss.setVisible(false);
				gauss.setVisible(true);
				gauss.add(btnBack);
				gauss.add(lblTituloDelPdf);
				gauss.add(btnCrearPdf);
				btnCrearPdf.setVisible(false);
				gauss.add(textFieldTituloPDF);
				textFieldTituloPDF.setVisible(false);
				lblTituloDelPdf.setVisible(false);
			}
		});
		btnGauss.setBounds(39, 193, 273, 35);
		menuSistemas.add(btnGauss);

		btnComparacionEntreMetodos = new JButton("Comparacion entre Metodos");
		btnComparacionEntreMetodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSistemas.setVisible(false);
				menuSistemas.remove(btnBack);
				comparacionSistemas.setVisible(true);
				comparacionSistemas.add(btnBack);
				comparacionSistemas.add(textFieldTituloPDF);
				comparacionSistemas.add(btnCrearPdf);
				comparacionSistemas.add(lblTituloDelPdf);
				textFieldTituloPDF.setVisible(false);
				btnCrearPdf.setVisible(false);
				lblTituloDelPdf.setVisible(false);
				lblComparacionSistemasICE.setVisible(true);
				txtComparacionSistemasICE.setVisible(true);
				btnComparacionSistemasACE.setVisible(true);
				lblComparacionSistemasIEI.setVisible(false);
				btnComparacionSistemasAE.setVisible(false);
				lblComparacionSistemasEA.setVisible(false);
				sPComparacionSistemasMP.setVisible(false);
				txtComparacionSistemasIM.setVisible(false);
				lblComparacionSistemasOPL.setVisible(false);
				sPComparacionSistemasOPP.setVisible(false);
				lblComparacionSistemasOP.setVisible(false);
				rdbtnComparacionSistemasAcotar.setVisible(false);
				lblComparacionSistemasAproxL.setVisible(false);
				txtComparacionSistemasAprox.setVisible(false);
				txtComparacionSistemasDI.setVisible(false);
				lblComparacionSistemasDatosIniciales.setVisible(false);
			}
		});
		btnComparacionEntreMetodos.setHorizontalAlignment(SwingConstants.LEFT);
		btnComparacionEntreMetodos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnComparacionEntreMetodos.setBounds(39, 259, 273, 35);
		menuSistemas.add(btnComparacionEntreMetodos);

		menuInterpolacion = new JPanel();
		menuInterpolacion.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(menuInterpolacion, "name_1922644963910022");
		menuInterpolacion.setLayout(null);

		JLabel iTitle = new JLabel("Interpolaci\u00F3n");
		iTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		iTitle.setHorizontalAlignment(SwingConstants.CENTER);
		iTitle.setBounds(10, 11, 424, 50);
		menuInterpolacion.add(iTitle);

		JButton iNewtonRaphsonButton = new JButton("Newton-Raphson");
		iNewtonRaphsonButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		iNewtonRaphsonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuInterpolacion.setVisible(false);
				menuInterpolacion.remove(btnBack);
				newtonRaphson.setVisible(true);
				newtonRaphson.add(btnBack);
				newtonRaphson.add(lblTituloDelPdf);
				newtonRaphson.add(btnCrearPdf);
				newtonRaphson.add(textFieldTituloPDF);
			}
		});
		iNewtonRaphsonButton.setBounds(402, 66, 220, 40);
		menuInterpolacion.add(iNewtonRaphsonButton);

		JButton iNewtonAButton = new JButton("Newton Ascendente");
		iNewtonAButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		iNewtonAButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuInterpolacion.setVisible(false);
				menuInterpolacion.remove(btnBack);
				newtonAscendente.setVisible(true);
				newtonAscendente.add(btnBack);
				newtonAscendente.add(lblTituloDelPdf);
				newtonAscendente.add(btnCrearPdf);
				newtonAscendente.add(textFieldTituloPDF);
			}
		});
		iNewtonAButton.setBounds(0, 66, 220, 40);
		menuInterpolacion.add(iNewtonAButton);

		JButton iNewtonDButton = new JButton("Newton Descendente");
		iNewtonDButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		iNewtonDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuInterpolacion.setVisible(false);
				menuInterpolacion.remove(btnBack);
				newtonDescendente.setVisible(true);
				newtonDescendente.add(btnBack);
				newtonDescendente.add(lblTituloDelPdf);
				newtonDescendente.add(btnCrearPdf);
				newtonDescendente.add(textFieldTituloPDF);
			}
		});
		iNewtonDButton.setBounds(0, 131, 220, 40);
		menuInterpolacion.add(iNewtonDButton);

		JButton iLaGrangeButton = new JButton("LaGrange");
		iLaGrangeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		iLaGrangeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuInterpolacion.setVisible(false);
				menuInterpolacion.remove(btnBack);
				laGrange.setVisible(true);
				laGrange.add(btnBack);
				laGrange.add(lblTituloDelPdf);
				laGrange.add(btnCrearPdf);
				laGrange.add(textFieldTituloPDF);
			}
		});
		iLaGrangeButton.setBounds(0, 197, 220, 40);
		menuInterpolacion.add(iLaGrangeButton);

		JButton iAitkenButton = new JButton("Aitken");
		iAitkenButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		iAitkenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuInterpolacion.setVisible(false);
				menuInterpolacion.remove(btnBack);
				aitken.setVisible(true);
				aitken.add(btnBack);
				aitken.add(lblTituloDelPdf);
				aitken.add(btnCrearPdf);
				aitken.add(textFieldTituloPDF);
			}
		});
		iAitkenButton.setBounds(402, 197, 220, 40);
		menuInterpolacion.add(iAitkenButton);

		JButton iSteffensenButton = new JButton("Steffensen");
		iSteffensenButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		iSteffensenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuInterpolacion.setVisible(false);
				menuInterpolacion.remove(btnBack);
				steffensen.setVisible(true);
				steffensen.add(btnBack);
				steffensen.add(lblTituloDelPdf);
				steffensen.add(btnCrearPdf);
				steffensen.add(textFieldTituloPDF);
			}
		});
		iSteffensenButton.setBounds(402, 131, 220, 40);
		menuInterpolacion.add(iSteffensenButton);

		btnComparacionMetodosDe = new JButton("Comparacion metodos de tabla");
		btnComparacionMetodosDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuInterpolacion.setVisible(false);
				menuInterpolacion.remove(btnBack);
				comparacionPorTabla.setVisible(true);
				comparacionPorTabla.add(btnBack);
				comparacionPorTabla.add(lblTituloDelPdf);
				comparacionPorTabla.add(btnCrearPdf);
				comparacionPorTabla.add(textFieldTituloPDF);
			}
		});
		btnComparacionMetodosDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnComparacionMetodosDe.setBounds(0, 266, 317, 40);
		menuInterpolacion.add(btnComparacionMetodosDe);

		btnAitkenSteffensenNewtonraphson = new JButton(
				"Aitken, Steffensen, NewtonRaphson");
		btnAitkenSteffensenNewtonraphson
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menuInterpolacion.setVisible(false);
						menuInterpolacion.remove(btnBack);
						comparacionSinTabla.setVisible(true);
						comparacionSinTabla.add(btnBack);
						comparacionSinTabla.add(lblTituloDelPdf);
						comparacionSinTabla.add(btnCrearPdf);
						comparacionSinTabla.add(textFieldTituloPDF);
					}
				});
		btnAitkenSteffensenNewtonraphson.setFont(new Font("Tahoma", Font.PLAIN,
				18));
		btnAitkenSteffensenNewtonraphson.setBounds(315, 266, 317, 40);
		menuInterpolacion.add(btnAitkenSteffensenNewtonraphson);

		menuEcuacionesDiferenciales = new JPanel();
		frame.getContentPane().add(menuEcuacionesDiferenciales,
				"name_505654714168");
		menuEcuacionesDiferenciales.setLayout(null);

		JLabel edmTitle = new JLabel("Ecuaciones Diferenciales");
		edmTitle.setHorizontalAlignment(SwingConstants.CENTER);
		edmTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		edmTitle.setBounds(10, 11, 614, 64);
		menuEcuacionesDiferenciales.add(edmTitle);

		btnEuler = new JButton("Euler");
		btnEuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEcuacionesDiferenciales.setVisible(false);
				menuEcuacionesDiferenciales.remove(btnBack);
				euler.setVisible(true);
				euler.add(btnBack);
				euler.add(textFieldTituloPDF);
				euler.add(btnCrearPdf);
				euler.add(lblTituloDelPdf);
			}
		});
		btnEuler.setBounds(212, 141, 229, 23);
		menuEcuacionesDiferenciales.add(btnEuler);

		btnRungeKutta = new JButton("Runge Kutta");
		btnRungeKutta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEcuacionesDiferenciales.setVisible(false);
				menuEcuacionesDiferenciales.remove(btnBack);
				rungeKutta.setVisible(true);
				rungeKutta.add(btnBack);
				rungeKutta.add(textFieldTituloPDF);
				rungeKutta.add(btnCrearPdf);
				rungeKutta.add(lblTituloDelPdf);
			}
		});
		btnRungeKutta.setBounds(212, 243, 229, 23);
		menuEcuacionesDiferenciales.add(btnRungeKutta);

		btnComparacionDeMetodos_3 = new JButton("Comparacion de metodos");
		btnComparacionDeMetodos_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEcuacionesDiferenciales.setVisible(false);
				menuEcuacionesDiferenciales.remove(btnBack);
				comparacionEcuDif.setVisible(true);
				comparacionEcuDif.add(btnBack);
				comparacionEcuDif.add(textFieldTituloPDF);
				comparacionEcuDif.add(btnCrearPdf);
				comparacionEcuDif.add(lblTituloDelPdf);
			}
		});
		btnComparacionDeMetodos_3.setBounds(212, 307, 229, 23);
		menuEcuacionesDiferenciales.add(btnComparacionDeMetodos_3);

		JButton btnEulerModificado = new JButton("Euler Modificado");
		btnEulerModificado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEcuacionesDiferenciales.setVisible(false);
				menuEcuacionesDiferenciales.remove(btnBack);
				eulerModificado.setVisible(true);
				eulerModificado.add(btnBack);
				eulerModificado.add(textFieldTituloPDF);
				eulerModificado.add(btnCrearPdf);
				eulerModificado.add(lblTituloDelPdf);
			}
		});
		btnEulerModificado.setBounds(212, 175, 229, 23);
		menuEcuacionesDiferenciales.add(btnEulerModificado);

		JButton btnEulerMejorado = new JButton("Euler Mejorado");
		btnEulerMejorado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEcuacionesDiferenciales.setVisible(false);
				menuEcuacionesDiferenciales.remove(btnBack);
				eulerMejorado.setVisible(true);
				eulerMejorado.add(btnBack);
				eulerMejorado.add(textFieldTituloPDF);
				eulerMejorado.add(btnCrearPdf);
				eulerMejorado.add(lblTituloDelPdf);
			}
		});
		btnEulerMejorado.setBounds(212, 209, 229, 23);
		menuEcuacionesDiferenciales.add(btnEulerMejorado);

		trapecios = new JPanel();
		trapecios.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(trapecios, "name_1097819612753303");
		trapecios.setLayout(null);
		trapecios.setVisible(false);

		JLabel lblTrapeciosTitulo = new JLabel("M\u00E9todo de trapecios");
		lblTrapeciosTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrapeciosTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTrapeciosTitulo.setBounds(53, 11, 311, 52);
		trapecios.add(lblTrapeciosTitulo);

		JLabel lblTrapeciosIF = new JLabel(
				"Introduzca la funci\u00F3n, \u00E9sta debe ser continua");
		lblTrapeciosIF.setBounds(10, 65, 376, 23);
		trapecios.add(lblTrapeciosIF);

		txtTrapeciosFunc = new JTextField();
		txtTrapeciosFunc.setBounds(10, 86, 256, 23);
		trapecios.add(txtTrapeciosFunc);
		txtTrapeciosFunc.setColumns(10);

		JLabel lblTrapeciosIL = new JLabel(
				"Introduzca los l\u00EDmites de integraci\u00F3n");
		lblTrapeciosIL.setBounds(10, 120, 215, 16);
		trapecios.add(lblTrapeciosIL);

		JLabel lblTrapeciosILI = new JLabel("de:");
		lblTrapeciosILI.setBounds(10, 145, 25, 14);
		trapecios.add(lblTrapeciosILI);

		txtTrapeciosLimInf = new JTextField();
		txtTrapeciosLimInf.setBounds(31, 145, 37, 20);
		trapecios.add(txtTrapeciosLimInf);
		txtTrapeciosLimInf.setColumns(10);

		JLabel lblTrapeciosILS = new JLabel("hasta:");
		lblTrapeciosILS.setBounds(91, 145, 61, 14);
		trapecios.add(lblTrapeciosILS);

		txtTrapeciosLimSup = new JTextField();
		txtTrapeciosLimSup.setBounds(147, 145, 43, 20);
		trapecios.add(txtTrapeciosLimSup);
		txtTrapeciosLimSup.setColumns(10);

		JLabel lblTrapeciosIN = new JLabel(
				"Introduzca n subdivisiones de la funci\u00F3n");
		lblTrapeciosIN.setBounds(10, 176, 237, 16);
		trapecios.add(lblTrapeciosIN);

		txtTrapeciosN = new JTextField();
		txtTrapeciosN.setBounds(246, 174, 37, 20);
		trapecios.add(txtTrapeciosN);
		txtTrapeciosN.setColumns(10);

		rdbtnTrapeciosArea = new JRadioButton("Area");
		rdbtnTrapeciosArea.setBackground(Color.LIGHT_GRAY);
		rdbtnTrapeciosArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTrapeciosArea.setSelected(true);
				rdbtnTrapeciosIntegral.setSelected(false);
			}
		});
		rdbtnTrapeciosArea.setBounds(295, 116, 109, 23);
		trapecios.add(rdbtnTrapeciosArea);

		rdbtnTrapeciosIntegral = new JRadioButton("Integral");
		rdbtnTrapeciosIntegral.setBackground(Color.LIGHT_GRAY);
		rdbtnTrapeciosIntegral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTrapeciosIntegral.setSelected(true);
				rdbtnTrapeciosArea.setSelected(false);
			}
		});
		rdbtnTrapeciosIntegral.setSelected(true);
		rdbtnTrapeciosIntegral.setBounds(295, 86, 109, 23);
		trapecios.add(rdbtnTrapeciosIntegral);

		JButton btnTrapeciosCalc = new JButton("Calcular");
		btnTrapeciosCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] answers = Trapecios.calculate(
						txtTrapeciosFunc.getText(),
						txtTrapeciosLimInf.getText(),
						txtTrapeciosLimSup.getText(), txtTrapeciosN.getText(),
						rdbtnTrapeciosIntegral.isSelected());
				lblTrapeciosIOP.setText("" + answers[0]);
				lblTrapeciosEOP.setText("" + answers[1]);
			}
		});
		btnTrapeciosCalc.setBounds(295, 141, 89, 23);
		trapecios.add(btnTrapeciosCalc);

		JLabel lblTrapeciosResp = new JLabel("Respuesta:");
		lblTrapeciosResp.setBounds(10, 201, 75, 17);
		trapecios.add(lblTrapeciosResp);

		lblTrapeciosIOP = new JLabel("");
		lblTrapeciosIOP.setBounds(86, 201, 300, 16);
		trapecios.add(lblTrapeciosIOP);

		JLabel lblTrapeciosError = new JLabel("Error:");
		lblTrapeciosError.setBounds(10, 222, 46, 14);
		trapecios.add(lblTrapeciosError);

		lblTrapeciosEOP = new JLabel("");
		lblTrapeciosEOP.setBounds(76, 222, 308, 14);
		trapecios.add(lblTrapeciosEOP);

		simpson = new JPanel();
		simpson.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(simpson, "name_1107285511395975");
		simpson.setLayout(null);
		simpson.setVisible(false);

		JLabel lblSimpsonTitulo = new JLabel("M\u00E9todo de Simpson");
		lblSimpsonTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimpsonTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSimpsonTitulo.setBounds(53, 11, 311, 52);
		simpson.add(lblSimpsonTitulo);

		JLabel lblSimpsonIF = new JLabel(
				"Introduzca la funci\u00F3n, \u00E9sta debe ser continua");
		lblSimpsonIF.setBounds(10, 65, 376, 23);
		simpson.add(lblSimpsonIF);

		txtSimpsonFunc = new JTextField();
		txtSimpsonFunc.setBounds(10, 86, 256, 23);
		simpson.add(txtSimpsonFunc);
		txtSimpsonFunc.setColumns(10);

		JLabel lblSimpsonIL = new JLabel(
				"Introduzca los l\u00EDmites de integraci\u00F3n");
		lblSimpsonIL.setBounds(10, 120, 215, 16);
		simpson.add(lblSimpsonIL);

		JLabel lblSimpsonILI = new JLabel("de:");
		lblSimpsonILI.setBounds(10, 145, 25, 14);
		simpson.add(lblSimpsonILI);

		txtSimpsonLimInf = new JTextField();
		txtSimpsonLimInf.setBounds(31, 145, 37, 20);
		simpson.add(txtSimpsonLimInf);
		txtSimpsonLimInf.setColumns(10);

		JLabel lblSimpsonILS = new JLabel("hasta:");
		lblSimpsonILS.setBounds(91, 145, 62, 14);
		simpson.add(lblSimpsonILS);

		txtSimpsonLimSup = new JTextField();
		txtSimpsonLimSup.setBounds(132, 145, 43, 20);
		simpson.add(txtSimpsonLimSup);
		txtSimpsonLimSup.setColumns(10);

		JLabel lblSimpsonIN = new JLabel(
				"Introduzca n subdivisiones, siendo n par:");
		lblSimpsonIN.setBounds(10, 176, 245, 14);
		simpson.add(lblSimpsonIN);

		txtSimpsonN = new JTextField();
		txtSimpsonN.setBounds(267, 173, 37, 20);
		simpson.add(txtSimpsonN);
		txtSimpsonN.setColumns(10);

		rdbtnSimpsonArea = new JRadioButton("Area");
		rdbtnSimpsonArea.setBackground(Color.LIGHT_GRAY);
		rdbtnSimpsonArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSimpsonArea.setSelected(true);
				rdbtnSimpsonIntegral.setSelected(false);
			}
		});
		rdbtnSimpsonArea.setBounds(316, 86, 109, 23);
		simpson.add(rdbtnSimpsonArea);

		rdbtnSimpsonIntegral = new JRadioButton("Integral");
		rdbtnSimpsonIntegral.setBackground(Color.LIGHT_GRAY);
		rdbtnSimpsonIntegral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSimpsonIntegral.setSelected(true);
				rdbtnSimpsonArea.setSelected(false);
			}
		});
		rdbtnSimpsonIntegral.setSelected(true);
		rdbtnSimpsonIntegral.setBounds(316, 65, 109, 23);
		simpson.add(rdbtnSimpsonIntegral);

		JButton btnSimpsonCalc = new JButton("Calcular");
		btnSimpsonCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] answers = Simpson.calculate(txtSimpsonFunc.getText(),
						txtSimpsonLimInf.getText(), txtSimpsonLimSup.getText(),
						txtSimpsonN.getText(),
						rdbtnSimpsonIntegral.isSelected());
				lblSimpsonIOP.setText("" + answers[0]);
				lblSimpsonEOP.setText("" + answers[1]);
			}
		});
		btnSimpsonCalc.setBounds(316, 116, 89, 23);
		simpson.add(btnSimpsonCalc);

		JLabel lblSimpsonResp = new JLabel("Respuesta:");
		lblSimpsonResp.setBounds(10, 201, 75, 17);
		simpson.add(lblSimpsonResp);

		lblSimpsonIOP = new JLabel("");
		lblSimpsonIOP.setBounds(86, 201, 308, 16);
		simpson.add(lblSimpsonIOP);

		JLabel lblSimpsonError = new JLabel("Error:");
		lblSimpsonError.setBounds(10, 222, 46, 14);
		simpson.add(lblSimpsonError);

		lblSimpsonEOP = new JLabel("");
		lblSimpsonEOP.setBounds(76, 222, 308, 14);
		simpson.add(lblSimpsonEOP);

		newtonCotes = new JPanel();
		newtonCotes.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(newtonCotes, "name_1136532580180977");
		newtonCotes.setLayout(null);
		newtonCotes.setVisible(false);

		JLabel lblNewCotTitulo = new JLabel("M\u00E9todo de Newton-Cotes");
		lblNewCotTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewCotTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewCotTitulo.setBounds(10, 11, 424, 48);
		newtonCotes.add(lblNewCotTitulo);

		JLabel lblNewCotIFL = new JLabel(
				"Introduzca la funci\u00F3n, \u00E9sta debe ser continua");
		lblNewCotIFL.setBounds(10, 57, 279, 24);
		newtonCotes.add(lblNewCotIFL);

		txtNewCotIF = new JTextField();
		txtNewCotIF.setBounds(10, 83, 279, 24);
		newtonCotes.add(txtNewCotIF);
		txtNewCotIF.setColumns(10);

		JLabel lblNewCotINL = new JLabel("Introduzca n, entre 2 y 6");
		lblNewCotINL.setBounds(10, 118, 152, 24);
		newtonCotes.add(lblNewCotINL);

		txtNewCotIN = new JTextField();
		txtNewCotIN.setBounds(172, 118, 75, 24);
		newtonCotes.add(txtNewCotIN);
		txtNewCotIN.setColumns(10);

		JLabel lblNewCotLimXL = new JLabel("L\u00EDmites en x:");
		lblNewCotLimXL.setBounds(80, 153, 119, 24);
		newtonCotes.add(lblNewCotLimXL);

		JLabel lblNewCotLimYL = new JLabel("L\u00EDmites en y:");
		lblNewCotLimYL.setBounds(230, 153, 100, 24);
		newtonCotes.add(lblNewCotLimYL);

		JLabel lblNewCotLimZL = new JLabel("L\u00EDmites en z:");
		lblNewCotLimZL.setBounds(376, 156, 133, 19);
		newtonCotes.add(lblNewCotLimZL);

		selNewCotIS = new JRadioButton("Integral Simple");
		selNewCotIS.setBackground(Color.LIGHT_GRAY);
		selNewCotIS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selNewCotIS.setSelected(true);
				selNewCotID.setSelected(false);
				selNewCotIDF.setSelected(false);
				selNewCotIT.setSelected(false);
			}
		});
		selNewCotIS.setSelected(true);
		selNewCotIS.setBounds(290, 58, 133, 23);
		newtonCotes.add(selNewCotIS);

		selNewCotID = new JRadioButton("Integral Doble");
		selNewCotID.setBackground(Color.LIGHT_GRAY);
		selNewCotID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selNewCotIS.setSelected(false);
				selNewCotID.setSelected(true);
				selNewCotIDF.setSelected(false);
				selNewCotIT.setSelected(false);
			}
		});
		selNewCotID.setBounds(290, 84, 133, 23);
		newtonCotes.add(selNewCotID);

		selNewCotIDF = new JRadioButton("Evaluar en una funcion");
		selNewCotIDF.setBackground(Color.LIGHT_GRAY);
		selNewCotIDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selNewCotIS.setSelected(false);
				selNewCotID.setSelected(true);
				selNewCotIDF.setSelected(true);
				selNewCotIT.setSelected(false);
			}
		});
		selNewCotIDF.setBounds(290, 105, 172, 23);
		newtonCotes.add(selNewCotIDF);

		selNewCotIT = new JRadioButton("Integral Triple");
		selNewCotIT.setBackground(Color.LIGHT_GRAY);
		selNewCotIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selNewCotIS.setSelected(false);
				selNewCotID.setSelected(false);
				selNewCotIDF.setSelected(false);
				selNewCotIT.setSelected(true);
			}
		});
		selNewCotIT.setBounds(290, 130, 133, 23);
		newtonCotes.add(selNewCotIT);

		txtNewCotXS = new JTextField();
		txtNewCotXS.setBounds(80, 177, 86, 20);
		newtonCotes.add(txtNewCotXS);
		txtNewCotXS.setColumns(10);

		txtNewCotXI = new JTextField();
		txtNewCotXI.setBounds(80, 203, 86, 20);
		newtonCotes.add(txtNewCotXI);
		txtNewCotXI.setColumns(10);

		txtNewCotYI = new JTextField();
		txtNewCotYI.setBounds(230, 203, 86, 20);
		newtonCotes.add(txtNewCotYI);
		txtNewCotYI.setColumns(10);

		txtNewCotYS = new JTextField();
		txtNewCotYS.setBounds(230, 177, 86, 20);
		newtonCotes.add(txtNewCotYS);
		txtNewCotYS.setColumns(10);

		txtNewCotZS = new JTextField();
		txtNewCotZS.setBounds(376, 177, 86, 20);
		newtonCotes.add(txtNewCotZS);
		txtNewCotZS.setColumns(10);

		txtNewCotZI = new JTextField();
		txtNewCotZI.setBounds(376, 203, 86, 20);
		newtonCotes.add(txtNewCotZI);
		txtNewCotZI.setColumns(10);

		JButton btnNewCotCalc = new JButton("Calcular");
		btnNewCotCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selNewCotIS.isSelected()) {
					lblNewCotIOP.setText(""
							+ NewtonCotes.newtonCotes(txtNewCotXI.getText(),
									txtNewCotXS.getText(),
									txtNewCotIN.getText(),
									txtNewCotIF.getText()));
				} else if (selNewCotID.isSelected()) {
					if (selNewCotIDF.isSelected()) {
						lblNewCotIOP.setText(""
								+ NewtonCotes.newtonCotesFunc(
										txtNewCotXI.getText(),
										txtNewCotXS.getText(),
										txtNewCotYI.getText(),
										txtNewCotYS.getText(),
										txtNewCotIN.getText(),
										txtNewCotIF.getText()));
					} else {
						lblNewCotIOP.setText(""
								+ NewtonCotes.newtonCotes2(
										txtNewCotXI.getText(),
										txtNewCotXS.getText(),
										txtNewCotYI.getText(),
										txtNewCotYS.getText(),
										txtNewCotIN.getText(),
										txtNewCotIF.getText()));
					}
				} else {
					lblNewCotIOP.setText(""
							+ NewtonCotes.newtonCotes3(txtNewCotXI.getText(),
									txtNewCotXS.getText(),
									txtNewCotYI.getText(),
									txtNewCotYS.getText(),
									txtNewCotZI.getText(),
									txtNewCotZS.getText(),
									txtNewCotIN.getText(),
									txtNewCotIF.getText()));
				}
			}
		});
		btnNewCotCalc.setBounds(10, 237, 89, 23);
		newtonCotes.add(btnNewCotCalc);

		JLabel lblNewCotOP = new JLabel("Respuesta:");
		lblNewCotOP.setBounds(109, 241, 67, 14);
		newtonCotes.add(lblNewCotOP);

		lblNewCotIOP = new JLabel("");
		lblNewCotIOP.setBounds(186, 241, 248, 19);
		newtonCotes.add(lblNewCotIOP);

		lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(12, 203, 56, 16);
		newtonCotes.add(lblDesde);

		lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(10, 177, 56, 16);
		newtonCotes.add(lblHasta);

		cuadraturaDeGauss = new JPanel();
		cuadraturaDeGauss.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(cuadraturaDeGauss, "name_1147859931909198");
		cuadraturaDeGauss.setLayout(null);
		cuadraturaDeGauss.setVisible(false);

		JLabel lblCuadGaussTitulo = new JLabel(
				"M\u00E9todo de cuadratura de Gauss");
		lblCuadGaussTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuadGaussTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCuadGaussTitulo.setBounds(10, 11, 424, 48);
		cuadraturaDeGauss.add(lblCuadGaussTitulo);

		JLabel lblCuadGaussIFL = new JLabel(
				"Introduzca la funci\u00F3n, \u00E9sta debe ser continua");
		lblCuadGaussIFL.setBounds(10, 57, 279, 24);
		cuadraturaDeGauss.add(lblCuadGaussIFL);

		txtCuadGaussIF = new JTextField();
		txtCuadGaussIF.setBounds(10, 83, 279, 24);
		cuadraturaDeGauss.add(txtCuadGaussIF);
		txtCuadGaussIF.setColumns(10);

		JLabel lblCuadGaussINL = new JLabel("Introduzca n, entre 2 y 6");
		lblCuadGaussINL.setBounds(10, 118, 152, 24);
		cuadraturaDeGauss.add(lblCuadGaussINL);

		txtCuadGaussIN = new JTextField();
		txtCuadGaussIN.setBounds(172, 118, 75, 24);
		cuadraturaDeGauss.add(txtCuadGaussIN);
		txtCuadGaussIN.setColumns(10);

		JLabel lblCuadGaussLimXL = new JLabel("L\u00EDmites en x:");
		lblCuadGaussLimXL.setBounds(10, 153, 119, 24);
		cuadraturaDeGauss.add(lblCuadGaussLimXL);

		JLabel lblCuadGaussLimYL = new JLabel("L\u00EDmites en y:");
		lblCuadGaussLimYL.setBounds(150, 153, 152, 24);
		cuadraturaDeGauss.add(lblCuadGaussLimYL);

		JLabel lblCuadGaussLimZL = new JLabel("L\u00EDmites en z:");
		lblCuadGaussLimZL.setBounds(301, 156, 133, 19);
		cuadraturaDeGauss.add(lblCuadGaussLimZL);

		selCuadGaussIS = new JRadioButton("Integral Simple");
		selCuadGaussIS.setBackground(Color.LIGHT_GRAY);
		selCuadGaussIS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selCuadGaussIS.setSelected(true);
				selCuadGaussID.setSelected(false);
				selCuadGaussIDF.setSelected(false);
				selCuadGaussIT.setSelected(false);
			}
		});
		selCuadGaussIS.setSelected(true);
		selCuadGaussIS.setBounds(290, 58, 133, 23);
		cuadraturaDeGauss.add(selCuadGaussIS);

		selCuadGaussID = new JRadioButton("Integral Doble");
		selCuadGaussID.setBackground(Color.LIGHT_GRAY);
		selCuadGaussID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selCuadGaussIS.setSelected(false);
				selCuadGaussID.setSelected(true);
				selCuadGaussIDF.setSelected(false);
				selCuadGaussIT.setSelected(false);
			}
		});
		selCuadGaussID.setBounds(290, 84, 133, 23);
		cuadraturaDeGauss.add(selCuadGaussID);

		selCuadGaussIDF = new JRadioButton("Evaluar en una funci\u00F3n");
		selCuadGaussIDF.setBackground(Color.LIGHT_GRAY);
		selCuadGaussIDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selCuadGaussIS.setSelected(false);
				selCuadGaussID.setSelected(true);
				selCuadGaussIDF.setSelected(true);
				selCuadGaussIT.setSelected(false);
			}
		});
		selCuadGaussIDF.setBounds(290, 105, 172, 23);
		cuadraturaDeGauss.add(selCuadGaussIDF);

		selCuadGaussIT = new JRadioButton("Integral Triple");
		selCuadGaussIT.setBackground(Color.LIGHT_GRAY);
		selCuadGaussIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selCuadGaussIS.setSelected(false);
				selCuadGaussID.setSelected(false);
				selCuadGaussIDF.setSelected(false);
				selCuadGaussIT.setSelected(true);
			}
		});
		selCuadGaussIT.setBounds(290, 130, 133, 23);
		cuadraturaDeGauss.add(selCuadGaussIT);

		txtCuadGaussXS = new JTextField();
		txtCuadGaussXS.setBounds(10, 177, 86, 20);
		cuadraturaDeGauss.add(txtCuadGaussXS);
		txtCuadGaussXS.setColumns(10);

		txtCuadGaussXI = new JTextField();
		txtCuadGaussXI.setBounds(10, 203, 86, 20);
		cuadraturaDeGauss.add(txtCuadGaussXI);
		txtCuadGaussXI.setColumns(10);

		txtCuadGaussYI = new JTextField();
		txtCuadGaussYI.setBounds(150, 203, 86, 20);
		cuadraturaDeGauss.add(txtCuadGaussYI);
		txtCuadGaussYI.setColumns(10);

		txtCuadGaussYS = new JTextField();
		txtCuadGaussYS.setBounds(150, 177, 86, 20);
		cuadraturaDeGauss.add(txtCuadGaussYS);
		txtCuadGaussYS.setColumns(10);

		txtCuadGaussZS = new JTextField();
		txtCuadGaussZS.setBounds(301, 177, 86, 20);
		cuadraturaDeGauss.add(txtCuadGaussZS);
		txtCuadGaussZS.setColumns(10);

		txtCuadGaussZI = new JTextField();
		txtCuadGaussZI.setBounds(301, 203, 86, 20);
		cuadraturaDeGauss.add(txtCuadGaussZI);
		txtCuadGaussZI.setColumns(10);

		JButton btnCuadGaussCalc = new JButton("Calcular");
		btnCuadGaussCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selCuadGaussIS.isSelected()) {
					lblCuadGaussIOP.setText(""
							+ CuadraturaDeGauss.gauss1(
									txtCuadGaussXI.getText(),
									txtCuadGaussXS.getText(),
									txtCuadGaussIN.getText(),
									txtCuadGaussIF.getText()));
				} else if (selCuadGaussID.isSelected()) {
					if (selCuadGaussIDF.isSelected()) {
						lblCuadGaussIOP.setText(""
								+ CuadraturaDeGauss.cuadraturaFunc(
										txtCuadGaussXI.getText(),
										txtCuadGaussXS.getText(),
										txtCuadGaussYI.getText(),
										txtCuadGaussYS.getText(),
										txtCuadGaussIN.getText(),
										txtCuadGaussIF.getText()));
					} else {
						lblCuadGaussIOP.setText(""
								+ CuadraturaDeGauss.gauss2(
										txtCuadGaussXI.getText(),
										txtCuadGaussXS.getText(),
										txtCuadGaussYI.getText(),
										txtCuadGaussYS.getText(),
										txtCuadGaussIN.getText(),
										txtCuadGaussIF.getText()));
					}
				} else {
					lblCuadGaussIOP.setText(""
							+ CuadraturaDeGauss.gauss3(
									txtCuadGaussXI.getText(),
									txtCuadGaussXS.getText(),
									txtCuadGaussYI.getText(),
									txtCuadGaussYS.getText(),
									txtCuadGaussZI.getText(),
									txtCuadGaussZS.getText(),
									txtCuadGaussIN.getText(),
									txtCuadGaussIF.getText()));
				}
			}
		});
		btnCuadGaussCalc.setBounds(10, 237, 89, 23);
		cuadraturaDeGauss.add(btnCuadGaussCalc);

		JLabel lblCuadGaussOP = new JLabel("Respuesta:");
		lblCuadGaussOP.setBounds(109, 241, 67, 14);
		cuadraturaDeGauss.add(lblCuadGaussOP);

		lblCuadGaussIOP = new JLabel("");
		lblCuadGaussIOP.setBounds(186, 241, 248, 19);
		cuadraturaDeGauss.add(lblCuadGaussIOP);

		gauss = new JPanel();
		gauss.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(gauss, "name_1187317586477758");
		gauss.setLayout(null);

		JLabel lblGaussTitulo = new JLabel("M\u00E9todo de Gauss");
		lblGaussTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGaussTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGaussTitulo.setBounds(10, 11, 424, 45);
		gauss.add(lblGaussTitulo);

		JLabel lblGaussICE = new JLabel(
				"Introduzca el n\u00FAmero de ecuaciones");
		lblGaussICE.setHorizontalAlignment(SwingConstants.CENTER);
		lblGaussICE.setBounds(93, 90, 262, 36);
		gauss.add(lblGaussICE);

		txtGaussICE = new JTextField();
		txtGaussICE.setBounds(184, 142, 86, 20);
		gauss.add(txtGaussICE);
		txtGaussICE.setColumns(10);

		JButton btnGaussACE = new JButton("Aceptar");
		btnGaussACE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches("[0-9]+", txtGaussICE.getText()) == true) {
					SistemsManager.setEcuaciones(new Integer(txtGaussICE
							.getText()));
					lblGaussICE.setVisible(false);
					txtGaussICE.setVisible(false);
					btnGaussACE.setVisible(false);
					lblGaussIEI.setVisible(true);
					btnGaussAE.setVisible(true);
					lblGaussEA.setVisible(true);
					sPGaussMP.setVisible(true);
					txtGaussIM.setVisible(true);
					lblGaussOPL.setVisible(true);
					sPGaussOPP.setVisible(true);
					lblGaussOP.setVisible(true);
					lblTituloDelPdf.setVisible(true);
					btnCrearPdf.setVisible(true);
					textFieldTituloPDF.setVisible(true);
					txtGaussIM.setText(SistemsManager.ecsEnString());
				}
			}
		});
		btnGaussACE.setBounds(181, 198, 89, 23);
		gauss.add(btnGaussACE);
		lblGaussIEI = new JLabel(
				"Introduzca las ecuaciones de la forma a1x1 + a2x2 + ... + anxn = a(n+1)x(n+1)");
		lblGaussIEI.setBounds(10, 61, 424, 20);
		gauss.add(lblGaussIEI);
		lblGaussIEI.setVisible(false);

		btnGaussAE = new JButton("Aceptar");
		btnGaussAE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SistemsManager.stringEnEcs(txtGaussIM.getText());
				lblGaussOP.setText("<html>"
						+ SistemsManager.respEnString(Gauss
								.gauss(SistemsManager.getEcuMat())) + "</html>");
			}
		});
		btnGaussAE.setBounds(181, 237, 89, 23);
		gauss.add(btnGaussAE);
		btnGaussAE.setVisible(false);

		lblGaussEA = new JLabel("");
		lblGaussEA.setBounds(10, 198, 144, 20);
		gauss.add(lblGaussEA);
		lblGaussEA.setVisible(false);

		sPGaussMP = new JScrollPane();
		sPGaussMP.setBounds(10, 90, 210, 147);
		gauss.add(sPGaussMP);
		sPGaussMP.setVisible(false);

		txtGaussIM = new JTextPane();
		sPGaussMP.setViewportView(txtGaussIM);
		txtGaussIM.setVisible(false);

		lblGaussOPL = new JLabel("Respuestas:");
		lblGaussOPL.setBounds(230, 90, 204, 20);
		gauss.add(lblGaussOPL);
		lblGaussOPL.setVisible(false);

		sPGaussOPP = new JScrollPane();
		sPGaussOPP.setBounds(230, 121, 204, 115);
		gauss.add(sPGaussOPP);
		sPGaussOPP.setVisible(false);

		lblGaussOP = new JLabel("");
		sPGaussOPP.setViewportView(lblGaussOP);
		lblGaussOP.setVisible(false);

		jacobi = new JPanel();
		jacobi.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(jacobi, "name_1187317586477758");
		jacobi.setLayout(null);

		JLabel lblJacobiTitulo = new JLabel("M\u00E9todo de Jacobi");
		lblJacobiTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblJacobiTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblJacobiTitulo.setBounds(10, 11, 424, 45);
		jacobi.add(lblJacobiTitulo);

		JLabel lblJacobiICE = new JLabel(
				"Introduzca el n\u00FAmero de ecuaciones");
		lblJacobiICE.setHorizontalAlignment(SwingConstants.CENTER);
		lblJacobiICE.setBounds(93, 90, 262, 36);
		jacobi.add(lblJacobiICE);

		txtJacobiICE = new JTextField();
		txtJacobiICE.setBounds(184, 142, 86, 20);
		jacobi.add(txtJacobiICE);
		txtJacobiICE.setColumns(10);

		lblJacobiIEI = new JLabel(
				"Introduzca las ecuaciones de la forma a1x1 + a2x2 + ... + anxn = a(n+1)x(n+1)");
		lblJacobiIEI.setBounds(10, 61, 424, 20);
		jacobi.add(lblJacobiIEI);
		lblJacobiIEI.setVisible(false);

		JRadioButton rdbtnJacobiAcotar = new JRadioButton("Acotar");
		rdbtnJacobiAcotar.setBackground(Color.LIGHT_GRAY);
		rdbtnJacobiAcotar.setBounds(325, 88, 109, 23);
		jacobi.add(rdbtnJacobiAcotar);
		rdbtnJacobiAcotar.setVisible(false);

		JLabel lblJacobiAproxL = new JLabel("Aproximacion:");
		lblJacobiAproxL.setBounds(10, 241, 89, 19);
		jacobi.add(lblJacobiAproxL);
		lblJacobiAproxL.setVisible(false);

		JTextField txtJacobiAprox = new JTextField();
		txtJacobiAprox.setBounds(103, 240, 45, 20);
		jacobi.add(txtJacobiAprox);
		txtJacobiAprox.setColumns(10);
		txtJacobiAprox.setVisible(false);

		btnJacobiAE = new JButton("Aceptar");
		btnJacobiAE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SistemsManager.stringEnEcs(txtJacobiIM.getText());
				SistemsManager.setMetodo("J");
				lblJacobiOP.setText("<html>"
						+ MetodoDeGauss.jacobi(txtJacobiAprox.getText(),
								txtJacobiDI.getText(),
								rdbtnJacobiAcotar.isSelected()) + "</html>");
			}
		});
		btnJacobiAE.setBounds(345, 239, 89, 23);
		jacobi.add(btnJacobiAE);
		btnJacobiAE.setVisible(false);

		lblJacobiEA = new JLabel("");
		lblJacobiEA.setBounds(10, 198, 144, 20);
		jacobi.add(lblJacobiEA);
		lblJacobiEA.setVisible(false);

		sPJacobiMP = new JScrollPane();
		sPJacobiMP.setBounds(10, 90, 210, 147);
		jacobi.add(sPJacobiMP);
		sPJacobiMP.setVisible(false);

		txtJacobiIM = new JTextPane();
		sPJacobiMP.setViewportView(txtJacobiIM);
		txtJacobiIM.setVisible(false);

		lblJacobiOPL = new JLabel("Respuestas:");
		lblJacobiOPL.setBounds(230, 90, 204, 20);
		jacobi.add(lblJacobiOPL);
		lblJacobiOPL.setVisible(false);

		sPJacobiOPP = new JScrollPane();
		sPJacobiOPP.setBounds(230, 121, 204, 115);
		jacobi.add(sPJacobiOPP);
		sPJacobiOPP.setVisible(false);

		lblJacobiOP = new JLabel("");
		lblJacobiOP.setFont(new Font("Courier New", Font.PLAIN, 13));
		sPJacobiOPP.setViewportView(lblJacobiOP);
		lblJacobiOP.setVisible(false);

		JButton btnJacobiACE = new JButton("Aceptar");
		btnJacobiACE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches("[0-9]+", txtJacobiICE.getText()) == true) {
					SistemsManager.setEcuaciones(new Integer(txtJacobiICE
							.getText()));
					lblJacobiICE.setVisible(false);
					txtJacobiICE.setVisible(false);
					btnJacobiACE.setVisible(false);
					lblJacobiIEI.setVisible(true);
					btnJacobiAE.setVisible(true);
					lblJacobiEA.setVisible(true);
					sPJacobiMP.setVisible(true);
					txtJacobiIM.setVisible(true);
					lblJacobiOPL.setVisible(true);
					sPJacobiOPP.setVisible(true);
					lblJacobiOP.setVisible(true);
					rdbtnJacobiAcotar.setVisible(true);
					lblJacobiAproxL.setVisible(true);
					txtJacobiAprox.setVisible(true);
					txtJacobiDI.setVisible(true);
					lblJacobiDatosIniciales.setVisible(true);
					lblTituloDelPdf.setVisible(true);
					btnCrearPdf.setVisible(true);
					textFieldTituloPDF.setVisible(true);
					btnConvergencia.setVisible(true);
					respuestaConvergencia.setVisible(true);
					txtJacobiIM.setText(SistemsManager.ecsEnString());
				}
			}
		});
		btnJacobiACE.setBounds(181, 198, 89, 23);
		jacobi.add(btnJacobiACE);

		txtJacobiDI = new JTextField();
		txtJacobiDI.setText("[a,b,...,n]");
		txtJacobiDI.setBounds(249, 240, 86, 20);
		jacobi.add(txtJacobiDI);
		txtJacobiDI.setColumns(10);
		txtJacobiDI.setVisible(false);

		lblJacobiDatosIniciales = new JLabel("X0=");
		lblJacobiDatosIniciales.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJacobiDatosIniciales.setBounds(158, 243, 86, 17);
		jacobi.add(lblJacobiDatosIniciales);

		btnConvergencia = new JButton("Convergencia");
		btnConvergencia.setVisible(false);
		btnConvergencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SistemsManager.stringEnEcs(txtJacobiIM.getText());
				respuestaConvergencia.setText(MetodoDeGauss
						.convergenciaRetornoJ(SistemsManager.getEcuMat()) ? "Converge"
						: "No converge");
			}
		});
		btnConvergencia.setBounds(20, 284, 128, 25);
		jacobi.add(btnConvergencia);

		respuestaConvergencia = new JTextField();
		respuestaConvergencia.setVisible(false);
		respuestaConvergencia.setBounds(168, 285, 116, 22);
		jacobi.add(respuestaConvergencia);
		respuestaConvergencia.setColumns(10);
		lblJacobiDatosIniciales.setVisible(false);

		gaussSeidel = new JPanel();
		gaussSeidel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(gaussSeidel, "name_1187317586477758");
		gaussSeidel.setLayout(null);

		JLabel lblGaussSeidelTitulo = new JLabel("M\u00E9todo de Gauss-Seidel");
		lblGaussSeidelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGaussSeidelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGaussSeidelTitulo.setBounds(10, 11, 424, 45);
		gaussSeidel.add(lblGaussSeidelTitulo);

		JLabel lblGaussSeidelICE = new JLabel(
				"Introduzca el n\u00FAmero de ecuaciones");
		lblGaussSeidelICE.setHorizontalAlignment(SwingConstants.CENTER);
		lblGaussSeidelICE.setBounds(93, 90, 262, 36);
		gaussSeidel.add(lblGaussSeidelICE);

		txtGaussSeidelICE = new JTextField();
		txtGaussSeidelICE.setBounds(184, 142, 86, 20);
		gaussSeidel.add(txtGaussSeidelICE);
		txtGaussSeidelICE.setColumns(10);

		lblGaussSeidelIEI = new JLabel(
				"Introduzca las ecuaciones de la forma a1x1 + a2x2 + ... + anxn = a(n+1)x(n+1)");
		lblGaussSeidelIEI.setBounds(10, 61, 424, 20);
		gaussSeidel.add(lblGaussSeidelIEI);
		lblGaussSeidelIEI.setVisible(false);

		JRadioButton rdbtnGaussSeidelAcotar = new JRadioButton("Acotar");
		rdbtnGaussSeidelAcotar.setBackground(Color.LIGHT_GRAY);
		rdbtnGaussSeidelAcotar.setBounds(325, 88, 109, 23);
		gaussSeidel.add(rdbtnGaussSeidelAcotar);
		rdbtnGaussSeidelAcotar.setVisible(false);

		JLabel lblGaussSeidelAproxL = new JLabel("Aproximacion:");
		lblGaussSeidelAproxL.setBounds(10, 241, 89, 19);
		gaussSeidel.add(lblGaussSeidelAproxL);
		lblGaussSeidelAproxL.setVisible(false);

		JTextField txtGaussSeidelAprox = new JTextField();
		txtGaussSeidelAprox.setBounds(92, 240, 39, 20);
		gaussSeidel.add(txtGaussSeidelAprox);
		txtGaussSeidelAprox.setColumns(10);
		txtGaussSeidelAprox.setVisible(false);

		btnGaussSeidelAE = new JButton("Aceptar");
		btnGaussSeidelAE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SistemsManager.stringEnEcs(txtGaussSeidelIM.getText());
				SistemsManager.setMetodo("GS");
				lblGaussSeidelOP.setText("<html>"
						+ MetodoDeGauss.gaussSeidel(
								txtGaussSeidelAprox.getText(),
								txtGaussSeidelDI.getText(),
								rdbtnGaussSeidelAcotar.isSelected())
						+ "</html>");
			}
		});

		btnGaussSeidelAE.setBounds(345, 239, 89, 23);
		gaussSeidel.add(btnGaussSeidelAE);
		btnGaussSeidelAE.setVisible(false);

		lblGaussSeidelEA = new JLabel("");
		lblGaussSeidelEA.setBounds(10, 198, 144, 20);
		gaussSeidel.add(lblGaussSeidelEA);
		lblGaussSeidelEA.setVisible(false);

		sPGaussSeidelMP = new JScrollPane();
		sPGaussSeidelMP.setBounds(10, 90, 210, 147);
		gaussSeidel.add(sPGaussSeidelMP);
		sPGaussSeidelMP.setVisible(false);

		txtGaussSeidelIM = new JTextPane();
		sPGaussSeidelMP.setViewportView(txtGaussSeidelIM);
		txtGaussSeidelIM.setVisible(false);

		lblGaussSeidelOPL = new JLabel("Respuestas:");
		lblGaussSeidelOPL.setBounds(230, 90, 204, 20);
		gaussSeidel.add(lblGaussSeidelOPL);
		lblGaussSeidelOPL.setVisible(false);

		sPGaussSeidelOPP = new JScrollPane();
		sPGaussSeidelOPP.setBounds(230, 121, 204, 115);
		gaussSeidel.add(sPGaussSeidelOPP);
		sPGaussSeidelOPP.setVisible(false);

		lblGaussSeidelOP = new JLabel("");
		lblGaussSeidelOP.setFont(new Font("Courier New", Font.PLAIN, 13));
		sPGaussSeidelOPP.setViewportView(lblGaussSeidelOP);
		lblGaussSeidelOP.setVisible(false);

		txtGaussSeidelDI = new JTextField();
		txtGaussSeidelDI.setText("[a,b,...,n]");
		txtGaussSeidelDI.setBounds(249, 240, 86, 20);
		gaussSeidel.add(txtGaussSeidelDI);
		txtGaussSeidelDI.setColumns(10);
		txtGaussSeidelDI.setVisible(false);

		lblGaussSeidelDatosIniciales = new JLabel("X0=");
		lblGaussSeidelDatosIniciales
				.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGaussSeidelDatosIniciales.setBounds(158, 243, 86, 17);
		gaussSeidel.add(lblGaussSeidelDatosIniciales);
		lblGaussSeidelDatosIniciales.setVisible(false);

		JButton btnGaussSeidelACE = new JButton("Aceptar");
		btnGaussSeidelACE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches("[0-9]+", txtGaussSeidelICE.getText()) == true) {
					SistemsManager.setEcuaciones(new Integer(txtGaussSeidelICE
							.getText()));
					lblGaussSeidelICE.setVisible(false);
					txtGaussSeidelICE.setVisible(false);
					btnGaussSeidelACE.setVisible(false);
					lblGaussSeidelIEI.setVisible(true);
					btnGaussSeidelAE.setVisible(true);
					lblGaussSeidelEA.setVisible(true);
					sPGaussSeidelMP.setVisible(true);
					txtGaussSeidelIM.setVisible(true);
					lblGaussSeidelOPL.setVisible(true);
					sPGaussSeidelOPP.setVisible(true);
					lblGaussSeidelOP.setVisible(true);
					rdbtnGaussSeidelAcotar.setVisible(true);
					lblGaussSeidelAproxL.setVisible(true);
					txtGaussSeidelAprox.setVisible(true);
					txtGaussSeidelDI.setVisible(true);
					lblGaussSeidelDatosIniciales.setVisible(true);
					lblTituloDelPdf.setVisible(true);
					btnCrearPdf.setVisible(true);
					textFieldTituloPDF.setVisible(true);
					txtGaussSeidelIM.setText(SistemsManager.ecsEnString());
				}
			}
		});
		btnGaussSeidelACE.setBounds(181, 198, 89, 23);
		gaussSeidel.add(btnGaussSeidelACE);
		JButton btnInterpolacion = new JButton("Interpolaci\u00F3n");
		btnInterpolacion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnInterpolacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuInterpolacion.setVisible(true);
				menuInterpolacion.add(btnBack);
			}
		});
		btnInterpolacion.setBounds(110, 179, 210, 52);
		menuPrincipal.add(btnInterpolacion);

		btnEcuacionesDiferenciales = new JButton("Ecuaciones Diferenciales");
		btnEcuacionesDiferenciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(false);
				menuEcuacionesDiferenciales.setVisible(true);
				menuEcuacionesDiferenciales.add(btnBack);
			}
		});
		btnEcuacionesDiferenciales.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEcuacionesDiferenciales.setBounds(332, 179, 302, 52);
		menuPrincipal.add(btnEcuacionesDiferenciales);

		newtonRaphson = new JPanel();
		newtonRaphson.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(newtonRaphson, "name_1924533807329776");
		newtonRaphson.setLayout(null);

		nrTitle = new JLabel("Newton-Raphson");
		nrTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		nrTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nrTitle.setBounds(10, 11, 424, 41);
		newtonRaphson.add(nrTitle);

		nrFuncLabel = new JLabel("Ingrese la funcion:");
		nrFuncLabel.setBounds(10, 81, 168, 19);
		newtonRaphson.add(nrFuncLabel);

		nrFuncInput = new JTextField();
		nrFuncInput.setBounds(122, 80, 222, 19);
		newtonRaphson.add(nrFuncInput);
		nrFuncInput.setColumns(10);

		JRadioButton nrPolynomialOption = new JRadioButton("Funcion Polinomial");
		nrPolynomialOption.setBackground(Color.LIGHT_GRAY);
		nrPolynomialOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		nrPolynomialOption.setSelected(true);
		nrPolynomialOption.setBounds(10, 47, 139, 23);
		newtonRaphson.add(nrPolynomialOption);

		nrFuncGradeLabel = new JLabel("Ingrese el grado de la funcion:");
		nrFuncGradeLabel.setBounds(10, 111, 192, 19);
		newtonRaphson.add(nrFuncGradeLabel);

		nrFuncGradeInput = new JTextField();
		nrFuncGradeInput.setBounds(188, 110, 46, 23);
		newtonRaphson.add(nrFuncGradeInput);
		nrFuncGradeInput.setColumns(10);

		nrLLabel = new JLabel("Ingrese L:");
		nrLLabel.setBounds(10, 137, 72, 14);
		newtonRaphson.add(nrLLabel);

		nrLInput = new JTextField();
		nrLInput.setBounds(92, 134, 86, 20);
		newtonRaphson.add(nrLInput);
		nrLInput.setColumns(10);

		nrPrimeLLabel = new JLabel("Ingrese L':");
		nrPrimeLLabel.setBounds(10, 162, 72, 14);
		newtonRaphson.add(nrPrimeLLabel);

		nrPrimeLInput = new JTextField();
		nrPrimeLInput.setBounds(92, 159, 86, 20);
		newtonRaphson.add(nrPrimeLInput);
		nrPrimeLInput.setColumns(10);

		JScrollPane nrAnswersPanel = new JScrollPane();
		nrAnswersPanel.setBounds(92, 225, 342, 166);
		newtonRaphson.add(nrAnswersPanel);

		JLabel nrAnswersOutput = new JLabel("");
		nrAnswersOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		nrAnswersPanel.setViewportView(nrAnswersOutput);

		nrAcceptButton = new JButton("Aceptar");
		nrAcceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String error = "";
				if (textFieldErrorNR.getText().equals("")) {
					error = "" + 0.00009;
				} else {
					error = textFieldErrorNR.getText();
				}
				if (nrPolynomialOption.isSelected()) {
					nrAnswersOutput.setText(NewRapP.newtonRaphson(
							nrFuncGradeInput.getText(), nrFuncInput.getText(),
							nrLInput.getText(), nrPrimeLInput.getText(), error));
				} else {
					nrAnswersOutput.setText(NewRapNP.calculate(
							nrFuncInput.getText(), nrX0Input.getText(), error));
				}
			}
		});
		nrAcceptButton.setBounds(161, 190, 89, 23);
		newtonRaphson.add(nrAcceptButton);

		nrAnswersLabel = new JLabel("Respuestas:");
		nrAnswersLabel.setBounds(10, 225, 102, 14);
		newtonRaphson.add(nrAnswersLabel);

		nrX0Label = new JLabel("Ingrese x0:");
		nrX0Label.setBounds(214, 137, 116, 17);
		newtonRaphson.add(nrX0Label);

		nrX0Input = new JTextField();
		nrX0Input.setBounds(283, 134, 86, 20);
		newtonRaphson.add(nrX0Input);
		nrX0Input.setColumns(10);

		JLabel lblIngreseElError = new JLabel("Ingrese el error:");
		lblIngreseElError.setBounds(184, 161, 130, 16);
		newtonRaphson.add(lblIngreseElError);

		textFieldErrorNR = new JTextField();
		textFieldErrorNR.setBounds(283, 158, 86, 20);
		newtonRaphson.add(textFieldErrorNR);
		textFieldErrorNR.setColumns(10);

		newtonAscendente = new JPanel();
		newtonAscendente.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(newtonAscendente, "name_1932770124470719");
		newtonAscendente.setLayout(null);

		naTitle = new JLabel("Newton Ascendente");
		naTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		naTitle.setHorizontalAlignment(SwingConstants.CENTER);
		naTitle.setBounds(10, 11, 424, 45);
		newtonAscendente.add(naTitle);

		naNumValOption = new JRadioButton("Valor numerico");
		naNumValOption.setBackground(Color.LIGHT_GRAY);
		naNumValOption.setSelected(true);
		naNumValOption.setBounds(10, 56, 109, 23);
		newtonAscendente.add(naNumValOption);

		naDataAmountLabel = new JLabel("Cantidad de datos:");
		naDataAmountLabel.setBounds(10, 79, 123, 14);
		newtonAscendente.add(naDataAmountLabel);

		naDataAmountInput = new JTextField();
		naDataAmountInput.setBounds(127, 76, 45, 20);
		newtonAscendente.add(naDataAmountInput);
		naDataAmountInput.setColumns(10);

		naDataPanel = new JScrollPane();
		naDataPanel.setBounds(10, 104, 168, 127);
		newtonAscendente.add(naDataPanel);

		JTextPane naDataInput = new JTextPane();
		naDataPanel.setViewportView(naDataInput);

		JButton naGenerateDataButton = new JButton("Generar Tabla");
		naGenerateDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneradorDeData.generarData(new Integer(naDataAmountInput
						.getText()));
				naDataInput.setText(GeneradorDeData.dataEnString());
			}
		});
		naGenerateDataButton.setBounds(182, 75, 116, 23);
		newtonAscendente.add(naGenerateDataButton);

		naEvaluationLabel = new JLabel("Evaluacion:");
		naEvaluationLabel.setBounds(10, 235, 76, 14);
		newtonAscendente.add(naEvaluationLabel);

		naEvaluationInput = new JTextField();
		naEvaluationInput.setBounds(86, 232, 56, 20);
		newtonAscendente.add(naEvaluationInput);
		naEvaluationInput.setColumns(10);

		naCalcButton = new JButton("Calcular");
		naCalcButton.setBounds(152, 242, 89, 23);
		newtonAscendente.add(naCalcButton);

		naAnswerLabel = new JLabel("Respuesta:");
		naAnswerLabel.setBounds(188, 109, 89, 14);
		newtonAscendente.add(naAnswerLabel);

		naAnswerPanel = new JScrollPane();
		naAnswerPanel.setBounds(188, 134, 246, 32);
		newtonAscendente.add(naAnswerPanel);

		naAnswerOutput = new JLabel("");
		naAnswerPanel.setViewportView(naAnswerOutput);

		naCalcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneradorDeData.stringEnData(naDataInput.getText()
						.toCharArray());
				if (naNumValOption.isSelected()) {
					naAnswerOutput.setText(""
							+ NewtonAscendente.newtonAscendente(GeneradorDeData
									.getData(),
									new Double(naEvaluationInput.getText())));
				} else {
					naAnswerOutput.setText(Lectura.leerTodo(NewtonAscendente
							.newtonAscendente(GeneradorDeData.getData())));
				}
			}
		});

		newtonDescendente = new JPanel();
		newtonDescendente.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(newtonDescendente, "ndme_1932770124470719");
		newtonDescendente.setLayout(null);

		ndTitle = new JLabel("Newton Descendente");
		ndTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ndTitle.setHorizontalAlignment(SwingConstants.CENTER);
		ndTitle.setBounds(10, 11, 424, 45);
		newtonDescendente.add(ndTitle);

		ndNumValOption = new JRadioButton("Valor numerico");
		ndNumValOption.setBackground(Color.LIGHT_GRAY);
		ndNumValOption.setSelected(true);
		ndNumValOption.setBounds(10, 56, 109, 23);
		newtonDescendente.add(ndNumValOption);

		ndDataAmountLabel = new JLabel("Cantidad de datos:");
		ndDataAmountLabel.setBounds(10, 79, 123, 14);
		newtonDescendente.add(ndDataAmountLabel);

		ndDataAmountInput = new JTextField();
		ndDataAmountInput.setBounds(127, 76, 45, 20);
		newtonDescendente.add(ndDataAmountInput);
		ndDataAmountInput.setColumns(10);

		ndDataPanel = new JScrollPane();
		ndDataPanel.setBounds(10, 104, 168, 127);
		newtonDescendente.add(ndDataPanel);

		JTextPane ndDataInput = new JTextPane();
		ndDataPanel.setViewportView(ndDataInput);

		JButton ndGenerateDataButton = new JButton("Generar Tabla");
		ndGenerateDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneradorDeData.generarData(new Integer(ndDataAmountInput
						.getText()));
				ndDataInput.setText(GeneradorDeData.dataEnString());
			}
		});
		ndGenerateDataButton.setBounds(182, 75, 116, 23);
		newtonDescendente.add(ndGenerateDataButton);

		ndEvaluationLabel = new JLabel("Evaluacion:");
		ndEvaluationLabel.setBounds(10, 235, 76, 14);
		newtonDescendente.add(ndEvaluationLabel);

		ndEvaluationInput = new JTextField();
		ndEvaluationInput.setBounds(86, 232, 56, 20);
		newtonDescendente.add(ndEvaluationInput);
		ndEvaluationInput.setColumns(10);

		ndCalcButton = new JButton("Calcular");
		ndCalcButton.setBounds(152, 242, 89, 23);
		newtonDescendente.add(ndCalcButton);

		ndAnswerLabel = new JLabel("Respuesta:");
		ndAnswerLabel.setBounds(188, 109, 89, 14);
		newtonDescendente.add(ndAnswerLabel);

		ndAnswerPanel = new JScrollPane();
		ndAnswerPanel.setBounds(188, 134, 246, 32);
		newtonDescendente.add(ndAnswerPanel);

		ndAnswerOutput = new JLabel("");
		ndAnswerPanel.setViewportView(ndAnswerOutput);

		ndCalcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneradorDeData.stringEnData(ndDataInput.getText()
						.toCharArray());
				if (ndNumValOption.isSelected()) {
					ndAnswerOutput.setText(""
							+ NewtonDescendente.newtonDescendente(
									GeneradorDeData.getData(), new Double(
											ndEvaluationInput.getText())));
				} else {
					ndAnswerOutput.setText(Lectura.leerTodo(NewtonDescendente
							.newtonDescendente(GeneradorDeData.getData())));
				}
			}
		});

		laGrange = new JPanel();
		laGrange.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(laGrange, "lgme_1932770124470719");
		laGrange.setLayout(null);

		lgTitle = new JLabel("LaGrange");
		lgTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lgTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lgTitle.setBounds(10, 11, 424, 45);
		laGrange.add(lgTitle);

		lgNumValOption = new JRadioButton("Valor numerico");
		lgNumValOption.setBackground(Color.LIGHT_GRAY);
		lgNumValOption.setSelected(true);
		lgNumValOption.setBounds(10, 56, 109, 23);
		laGrange.add(lgNumValOption);

		lgDataAmountLabel = new JLabel("Cantidad de datos:");
		lgDataAmountLabel.setBounds(10, 79, 123, 14);
		laGrange.add(lgDataAmountLabel);

		lgDataAmountInput = new JTextField();
		lgDataAmountInput.setBounds(127, 76, 45, 20);
		laGrange.add(lgDataAmountInput);
		lgDataAmountInput.setColumns(10);

		lgDataPanel = new JScrollPane();
		lgDataPanel.setBounds(10, 104, 168, 127);
		laGrange.add(lgDataPanel);

		JTextPane lgDataInput = new JTextPane();
		lgDataPanel.setViewportView(lgDataInput);

		JButton lgGenerateDataButton = new JButton("Generar Tabla");
		lgGenerateDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneradorDeData.generarData(new Integer(lgDataAmountInput
						.getText()));
				lgDataInput.setText(GeneradorDeData.dataEnString());
			}
		});
		lgGenerateDataButton.setBounds(182, 75, 116, 23);
		laGrange.add(lgGenerateDataButton);

		lgEvaluationLabel = new JLabel("Evaluacion:");
		lgEvaluationLabel.setBounds(10, 235, 76, 14);
		laGrange.add(lgEvaluationLabel);

		lgEvaluationInput = new JTextField();
		lgEvaluationInput.setBounds(86, 232, 56, 20);
		laGrange.add(lgEvaluationInput);
		lgEvaluationInput.setColumns(10);

		lgCalcButton = new JButton("Calcular");
		lgCalcButton.setBounds(152, 242, 89, 23);
		laGrange.add(lgCalcButton);

		lgAnswerLabel = new JLabel("Respuesta:");
		lgAnswerLabel.setBounds(188, 109, 89, 14);
		laGrange.add(lgAnswerLabel);

		lgAnswerPanel = new JScrollPane();
		lgAnswerPanel.setBounds(188, 134, 246, 32);
		laGrange.add(lgAnswerPanel);

		lgAnswerOutput = new JLabel("");
		lgAnswerPanel.setViewportView(lgAnswerOutput);

		lgCalcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneradorDeData.stringEnData(lgDataInput.getText()
						.toCharArray());
				if (lgNumValOption.isSelected()) {
					lgAnswerOutput.setText(""
							+ LaGrange.laGrange(GeneradorDeData.getDataD(),
									new Double(lgEvaluationInput.getText())));
				} else {
					lgAnswerOutput.setText(Lectura.leerTodo(LaGrange
							.laGrange(GeneradorDeData.getDataD())));
				}
			}
		});

		aitken = new JPanel();
		aitken.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(aitken, "name_338218713867434");
		aitken.setLayout(null);

		JLabel aTitle = new JLabel("Aitken");
		aTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		aTitle.setHorizontalAlignment(SwingConstants.CENTER);
		aTitle.setBounds(10, 11, 424, 51);
		aitken.add(aTitle);

		JLabel aFuncLabel = new JLabel("Introduzca la funcion:");
		aFuncLabel.setBounds(10, 58, 134, 14);
		aitken.add(aFuncLabel);

		JLabel aX0Label = new JLabel("Introduzca x0:");
		aX0Label.setBounds(10, 86, 113, 14);
		aitken.add(aX0Label);

		JLabel aErrorLabel = new JLabel("Introduzca el error:");
		aErrorLabel.setBounds(10, 111, 134, 14);
		aitken.add(aErrorLabel);

		aFuncInput = new JTextField();
		aFuncInput.setBounds(138, 55, 249, 20);
		aitken.add(aFuncInput);
		aFuncInput.setColumns(10);

		aX0Input = new JTextField();
		aX0Input.setBounds(135, 83, 86, 20);
		aitken.add(aX0Input);
		aX0Input.setColumns(10);

		aX2Input = new JTextField();
		aX2Input.setBounds(138, 108, 86, 20);
		aitken.add(aX2Input);
		aX2Input.setColumns(10);

		JScrollPane aAnswersPanel = new JScrollPane();
		aAnswersPanel.setBounds(78, 165, 356, 95);
		aitken.add(aAnswersPanel);

		JLabel aAnswersOutput = new JLabel("");
		aAnswersOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		aAnswersPanel.setViewportView(aAnswersOutput);

		JButton aCalcButton = new JButton("Calcular");
		aCalcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aAnswersOutput.setText(Steffensen.aitken(aFuncInput.getText(),
						aX0Input.getText(), aX2Input.getText()));
			}
		});
		aCalcButton.setBounds(173, 131, 89, 23);
		aitken.add(aCalcButton);

		JLabel lblRespuesta = new JLabel("Respuestas:");
		lblRespuesta.setBounds(10, 164, 86, 20);
		aitken.add(lblRespuesta);

		steffensen = new JPanel();
		steffensen.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(steffensen, "name_338232066417383");
		steffensen.setLayout(null);

		stfTitle = new JLabel("Steffensen");
		stfTitle.setHorizontalAlignment(SwingConstants.CENTER);
		stfTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		stfTitle.setBounds(10, 11, 424, 57);
		steffensen.add(stfTitle);

		stfFuncLabel = new JLabel("Ingrese la funcion:");
		stfFuncLabel.setBounds(10, 79, 130, 14);
		steffensen.add(stfFuncLabel);

		stfFuncInput = new JTextField();
		stfFuncInput.setBounds(127, 77, 305, 17);
		steffensen.add(stfFuncInput);
		stfFuncInput.setColumns(10);

		JLabel stfX0Label = new JLabel("Ingrese el x inicial:");
		stfX0Label.setBounds(10, 104, 130, 14);
		steffensen.add(stfX0Label);

		stfX0Input = new JTextField();
		stfX0Input.setBounds(127, 102, 51, 17);
		steffensen.add(stfX0Input);
		stfX0Input.setColumns(10);

		JLabel stfErrorLabel = new JLabel("Ingrese el error:");
		stfErrorLabel.setBounds(10, 129, 150, 14);
		steffensen.add(stfErrorLabel);

		stfErrorInput = new JTextField();
		stfErrorInput.setBounds(127, 127, 51, 17);
		steffensen.add(stfErrorInput);
		stfErrorInput.setColumns(10);

		JLabel stfAnswersLabel = new JLabel("Respuestas:");
		stfAnswersLabel.setBounds(10, 154, 88, 14);
		steffensen.add(stfAnswersLabel);

		JScrollPane stfAnswersPanel = new JScrollPane();
		stfAnswersPanel.setBounds(108, 154, 326, 106);
		steffensen.add(stfAnswersPanel);

		JLabel stfAnswersOutput = new JLabel("");
		stfAnswersOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		stfAnswersPanel.setViewportView(stfAnswersOutput);

		stfCalcButton = new JButton("Calcular");
		stfCalcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stfAnswersOutput.setText(Steffensen.steffensen(
						stfFuncInput.getText(), stfX0Input.getText(),
						stfErrorInput.getText()));
			}
		});
		stfCalcButton.setBounds(345, 100, 89, 23);
		steffensen.add(stfCalcButton);

		lblTituloDelPdf = new JLabel("T\u00EDtulo del PDF:");
		lblTituloDelPdf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTituloDelPdf.setBounds(10, 416, 110, 20);

		textFieldTituloPDF = new JTextField();
		textFieldTituloPDF.setBounds(131, 416, 220, 20);
		textFieldTituloPDF.setColumns(10);

		comparacionIntegracion = new JPanel();
		comparacionIntegracion.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(comparacionIntegracion,
				"name_1136532580180977");
		comparacionIntegracion.setLayout(null);
		comparacionIntegracion.setVisible(false);

		JLabel lblCompIntTitulo = new JLabel("Comparacion De Integracion");
		lblCompIntTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompIntTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCompIntTitulo.setBounds(132, 11, 424, 48);
		comparacionIntegracion.add(lblCompIntTitulo);

		JLabel lblCompIntIFL = new JLabel(
				"Introducir la funcion (debe ser continua):");
		lblCompIntIFL.setBounds(10, 57, 237, 24);
		comparacionIntegracion.add(lblCompIntIFL);

		txtCompIntIF = new JTextField();
		txtCompIntIF.setBounds(10, 83, 279, 24);
		comparacionIntegracion.add(txtCompIntIF);
		txtCompIntIF.setColumns(10);

		JLabel lblCompIntINL = new JLabel("Introducir n (de 2 a 6):");
		lblCompIntINL.setBounds(10, 156, 152, 24);
		comparacionIntegracion.add(lblCompIntINL);

		txtCompIntIN = new JTextField();
		txtCompIntIN.setBounds(148, 156, 75, 24);
		comparacionIntegracion.add(txtCompIntIN);
		txtCompIntIN.setColumns(10);

		JLabel lblCompIntLimXL = new JLabel("Limites en x:");
		lblCompIntLimXL.setBounds(42, 218, 119, 24);
		comparacionIntegracion.add(lblCompIntLimXL);

		JLabel lblCompIntLimYL = new JLabel("Limites en y:");
		lblCompIntLimYL.setBounds(268, 218, 152, 24);
		comparacionIntegracion.add(lblCompIntLimYL);

		JLabel lblCompIntLimZL = new JLabel("Limites en z:");
		lblCompIntLimZL.setBounds(472, 221, 133, 19);
		comparacionIntegracion.add(lblCompIntLimZL);

		selCompIntIS = new JRadioButton("Integral Simple");
		selCompIntIS.setBackground(Color.LIGHT_GRAY);
		selCompIntIS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selCompIntIS.setSelected(true);
				rdbtnCompIntArea.setSelected(false);
				selCompIntID.setSelected(false);
				selCompIntIDF.setSelected(false);
				selCompIntIT.setSelected(false);
			}
		});
		selCompIntIS.setSelected(true);
		selCompIntIS.setBounds(423, 58, 133, 23);
		comparacionIntegracion.add(selCompIntIS);

		selCompIntID = new JRadioButton("Integral Doble");
		selCompIntID.setBackground(Color.LIGHT_GRAY);
		selCompIntID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selCompIntIS.setSelected(false);
				selCompIntID.setSelected(true);
				rdbtnCompIntArea.setSelected(false);
				selCompIntIDF.setSelected(false);
				selCompIntIT.setSelected(false);
			}
		});
		selCompIntID.setBounds(423, 84, 133, 23);
		comparacionIntegracion.add(selCompIntID);

		selCompIntIDF = new JRadioButton("Evaluar en una funcion");
		selCompIntIDF.setBackground(Color.LIGHT_GRAY);
		selCompIntIDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selCompIntIS.setSelected(false);
				rdbtnCompIntArea.setSelected(false);
				selCompIntID.setSelected(true);
				selCompIntIDF.setSelected(true);
				selCompIntIT.setSelected(false);
			}
		});
		selCompIntIDF.setBounds(433, 104, 172, 23);
		comparacionIntegracion.add(selCompIntIDF);

		selCompIntIT = new JRadioButton("Integral Triple");
		selCompIntIT.setBackground(Color.LIGHT_GRAY);
		selCompIntIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selCompIntIS.setSelected(false);
				selCompIntID.setSelected(false);
				selCompIntIDF.setSelected(false);
				rdbtnCompIntArea.setSelected(false);
				selCompIntIT.setSelected(true);
			}
		});
		selCompIntIT.setBounds(423, 126, 133, 23);
		comparacionIntegracion.add(selCompIntIT);

		txtCompIntXS = new JTextField();
		txtCompIntXS.setBounds(42, 251, 86, 20);
		comparacionIntegracion.add(txtCompIntXS);
		txtCompIntXS.setColumns(10);

		txtCompIntXI = new JTextField();
		txtCompIntXI.setBounds(42, 282, 86, 20);
		comparacionIntegracion.add(txtCompIntXI);
		txtCompIntXI.setColumns(10);

		txtCompIntYI = new JTextField();
		txtCompIntYI.setBounds(268, 282, 86, 20);
		comparacionIntegracion.add(txtCompIntYI);
		txtCompIntYI.setColumns(10);

		txtCompIntYS = new JTextField();
		txtCompIntYS.setBounds(268, 251, 86, 20);
		comparacionIntegracion.add(txtCompIntYS);
		txtCompIntYS.setColumns(10);

		txtCompIntZS = new JTextField();
		txtCompIntZS.setBounds(472, 251, 86, 20);
		comparacionIntegracion.add(txtCompIntZS);
		txtCompIntZS.setColumns(10);

		txtCompIntZI = new JTextField();
		txtCompIntZI.setBounds(470, 282, 86, 20);
		comparacionIntegracion.add(txtCompIntZI);
		txtCompIntZI.setColumns(10);

		lblIntroducirNpar = new JLabel("Introducir n (par)");
		lblIntroducirNpar.setBounds(306, 156, 110, 24);
		comparacionIntegracion.add(lblIntroducirNpar);

		txtCompIntNP = new JTextField();
		txtCompIntNP.setBounds(414, 158, 127, 24);
		comparacionIntegracion.add(txtCompIntNP);
		txtCompIntNP.setColumns(10);

		JLabel lblCompIntOP = new JLabel("Respuesta:");
		lblCompIntOP.setBounds(132, 343, 67, 14);
		comparacionIntegracion.add(lblCompIntOP);

		lblCompIntIOP = new JLabel("");

		scrlCompIntAnswerPanel = new JScrollPane();
		scrlCompIntAnswerPanel.setBounds(197, 307, 368, 77);
		scrlCompIntAnswerPanel.setViewportView(lblCompIntIOP);
		comparacionIntegracion.add(scrlCompIntAnswerPanel);

		rdbtnCompIntArea = new JRadioButton("Area");
		rdbtnCompIntArea.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selCompIntID.setSelected(false);
				selCompIntIDF.setSelected(false);
				selCompIntIT.setSelected(false);
				selCompIntIS.setSelected(true);
			}
		});
		rdbtnCompIntArea.setBackground(Color.LIGHT_GRAY);
		rdbtnCompIntArea.setBounds(311, 58, 109, 23);
		comparacionIntegracion.add(rdbtnCompIntArea);

		JButton btnCompIntCalc = new JButton("Calcular");
		btnCompIntCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selCompIntIS.isSelected()) {
					if (rdbtnCompIntArea.isSelected()) {
						lblCompIntIOP.setText(IntegralsManager
								.calculateAllSimpleAreas(
										txtCompIntXI.getText(),
										txtCompIntXS.getText(),
										txtCompIntNP.getText(),
										txtCompIntIF.getText()));
					} else {
						lblCompIntIOP.setText(IntegralsManager
								.calculateAllSimpleIntegrals(
										txtCompIntXI.getText(),
										txtCompIntXS.getText(),
										txtCompIntIN.getText(),
										txtCompIntNP.getText(),
										txtCompIntIF.getText()));
					}
				} else if (selCompIntID.isSelected()) {
					if (selCompIntIDF.isSelected()) {
						lblCompIntIOP.setText(IntegralsManager
								.calculateAllDoubleWithFunctionIntegrals(
										txtCompIntXI.getText(),
										txtCompIntXS.getText(),
										txtCompIntYI.getText(),
										txtCompIntYS.getText(),
										txtCompIntIN.getText(),
										txtCompIntIF.getText()));
					} else {
						lblCompIntIOP.setText(IntegralsManager
								.calculateAllDoubleIntegrals(
										txtCompIntXI.getText(),
										txtCompIntXS.getText(),
										txtCompIntYI.getText(),
										txtCompIntYS.getText(),
										txtCompIntIN.getText(),
										txtCompIntIF.getText()));
					}
				} else {
					lblCompIntIOP.setText(IntegralsManager
							.calculateAllTripleIntegrals(
									txtCompIntXI.getText(),
									txtCompIntXS.getText(),
									txtCompIntYI.getText(),
									txtCompIntYS.getText(),
									txtCompIntZI.getText(),
									txtCompIntZS.getText(),
									txtCompIntIN.getText(),
									txtCompIntIF.getText()));
				}
			}
		});
		btnCompIntCalc.setBounds(10, 339, 89, 23);
		comparacionIntegracion.add(btnCompIntCalc);

		comparacionSistemas = new JPanel();
		comparacionSistemas.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane()
				.add(comparacionSistemas, "name_1187317586477758");
		comparacionSistemas.setLayout(null);

		JLabel lblComparacionSistemasTitulo = new JLabel("ComparacionSistemas");
		lblComparacionSistemasTitulo
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblComparacionSistemasTitulo
				.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblComparacionSistemasTitulo.setBounds(118, 11, 424, 45);
		comparacionSistemas.add(lblComparacionSistemasTitulo);

		lblComparacionSistemasICE = new JLabel(
				"Introducir la cantidad de ecuaciones:");
		lblComparacionSistemasICE.setHorizontalAlignment(SwingConstants.CENTER);
		lblComparacionSistemasICE.setBounds(184, 203, 262, 36);
		comparacionSistemas.add(lblComparacionSistemasICE);

		txtComparacionSistemasICE = new JTextField();
		txtComparacionSistemasICE.setBounds(269, 250, 86, 20);
		comparacionSistemas.add(txtComparacionSistemasICE);
		txtComparacionSistemasICE.setColumns(10);

		lblComparacionSistemasIEI = new JLabel(
				"Ingresar las ecuaciones de la forma a1x1 + a2x2 + ... + anxn = a(n+1)x(n+1)");
		lblComparacionSistemasIEI.setBounds(9, 121, 424, 20);
		comparacionSistemas.add(lblComparacionSistemasIEI);
		lblComparacionSistemasIEI.setVisible(false);

		rdbtnComparacionSistemasAcotar = new JRadioButton("Acotar");
		rdbtnComparacionSistemasAcotar.setBackground(Color.LIGHT_GRAY);
		rdbtnComparacionSistemasAcotar.setBounds(458, 303, 109, 23);
		comparacionSistemas.add(rdbtnComparacionSistemasAcotar);
		rdbtnComparacionSistemasAcotar.setVisible(false);

		lblComparacionSistemasAproxL = new JLabel("Aproximacion:");
		lblComparacionSistemasAproxL.setBounds(29, 361, 89, 19);
		comparacionSistemas.add(lblComparacionSistemasAproxL);
		lblComparacionSistemasAproxL.setVisible(false);

		txtComparacionSistemasAprox = new JTextField();
		txtComparacionSistemasAprox.setBounds(128, 360, 45, 20);
		comparacionSistemas.add(txtComparacionSistemasAprox);
		txtComparacionSistemasAprox.setColumns(10);
		txtComparacionSistemasAprox.setVisible(false);

		btnComparacionSistemasAE = new JButton("Aceptar");
		btnComparacionSistemasAE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SistemsManager.stringEnEcs(txtComparacionSistemasIM.getText());
				lblComparacionSistemasOP.setText(SistemsManager
						.allSystemMethods(
								txtComparacionSistemasAprox.getText(),
								txtComparacionSistemasDI.getText(),
								rdbtnComparacionSistemasAcotar.isSelected()));
			}
		});
		btnComparacionSistemasAE.setBounds(520, 359, 89, 23);
		comparacionSistemas.add(btnComparacionSistemasAE);
		btnComparacionSistemasAE.setVisible(false);

		lblComparacionSistemasEA = new JLabel("");
		lblComparacionSistemasEA.setBounds(10, 198, 144, 20);
		comparacionSistemas.add(lblComparacionSistemasEA);
		lblComparacionSistemasEA.setVisible(false);

		sPComparacionSistemasMP = new JScrollPane();
		sPComparacionSistemasMP.setBounds(10, 160, 210, 147);
		comparacionSistemas.add(sPComparacionSistemasMP);
		sPComparacionSistemasMP.setVisible(false);

		txtComparacionSistemasIM = new JTextPane();
		sPComparacionSistemasMP.setViewportView(txtComparacionSistemasIM);
		txtComparacionSistemasIM.setVisible(false);

		lblComparacionSistemasOPL = new JLabel("Respuestas:");
		lblComparacionSistemasOPL.setBounds(405, 160, 204, 20);
		comparacionSistemas.add(lblComparacionSistemasOPL);
		lblComparacionSistemasOPL.setVisible(false);

		sPComparacionSistemasOPP = new JScrollPane();
		sPComparacionSistemasOPP.setBounds(405, 181, 204, 115);
		comparacionSistemas.add(sPComparacionSistemasOPP);
		sPComparacionSistemasOPP.setVisible(false);

		lblComparacionSistemasOP = new JLabel("");
		lblComparacionSistemasOP
				.setFont(new Font("Courier New", Font.PLAIN, 13));
		sPComparacionSistemasOPP.setViewportView(lblComparacionSistemasOP);
		lblComparacionSistemasOP.setVisible(false);

		btnComparacionSistemasACE = new JButton("Aceptar");
		btnComparacionSistemasACE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches("[0-9]+",
						txtComparacionSistemasICE.getText()) == true) {
					SistemsManager.setEcuaciones(new Integer(
							txtComparacionSistemasICE.getText()));
					lblComparacionSistemasICE.setVisible(false);
					txtComparacionSistemasICE.setVisible(false);
					btnComparacionSistemasACE.setVisible(false);
					lblComparacionSistemasIEI.setVisible(true);
					btnComparacionSistemasAE.setVisible(true);
					lblComparacionSistemasEA.setVisible(true);
					sPComparacionSistemasMP.setVisible(true);
					txtComparacionSistemasIM.setVisible(true);
					lblComparacionSistemasOPL.setVisible(true);
					sPComparacionSistemasOPP.setVisible(true);
					lblComparacionSistemasOP.setVisible(true);
					rdbtnComparacionSistemasAcotar.setVisible(true);
					lblComparacionSistemasAproxL.setVisible(true);
					txtComparacionSistemasAprox.setVisible(true);
					txtComparacionSistemasDI.setVisible(true);
					lblComparacionSistemasDatosIniciales.setVisible(true);
					lblTituloDelPdf.setVisible(true);
					textFieldTituloPDF.setVisible(true);
					btnCrearPdf.setVisible(true);
					SistemsManager.setMetodo("J");
					txtComparacionSistemasIM.setText(SistemsManager
							.ecsEnString());
				}
			}
		});
		btnComparacionSistemasACE.setBounds(266, 306, 89, 23);
		comparacionSistemas.add(btnComparacionSistemasACE);

		txtComparacionSistemasDI = new JTextField();
		txtComparacionSistemasDI.setText("[a,b,...,n]");
		txtComparacionSistemasDI.setBounds(347, 360, 86, 20);
		comparacionSistemas.add(txtComparacionSistemasDI);
		txtComparacionSistemasDI.setColumns(10);
		txtComparacionSistemasDI.setVisible(false);

		lblComparacionSistemasDatosIniciales = new JLabel("Datos Iniciales");
		lblComparacionSistemasDatosIniciales.setBounds(251, 362, 86, 17);
		comparacionSistemas.add(lblComparacionSistemasDatosIniciales);

		lblComparacionSistemasDatosIniciales.setVisible(false);

		comparacionPorTabla = new JPanel();
		comparacionPorTabla.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(comparacionPorTabla,
				"cptme_1932770124470719");
		comparacionPorTabla.setLayout(null);

		cptTitle = new JLabel("Comparacion por Tabla");
		cptTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cptTitle.setHorizontalAlignment(SwingConstants.CENTER);
		cptTitle.setBounds(140, 11, 424, 45);
		comparacionPorTabla.add(cptTitle);

		cptDataAmountLabel = new JLabel("Cantidad de datos:");
		cptDataAmountLabel.setBounds(10, 79, 123, 14);
		comparacionPorTabla.add(cptDataAmountLabel);

		cptDataAmountInput = new JTextField();
		cptDataAmountInput.setBounds(127, 76, 45, 20);
		comparacionPorTabla.add(cptDataAmountInput);
		cptDataAmountInput.setColumns(10);

		cptDataPanel = new JScrollPane();
		cptDataPanel.setBounds(52, 191, 168, 127);
		comparacionPorTabla.add(cptDataPanel);

		JTextPane cptDataInput = new JTextPane();
		cptDataPanel.setViewportView(cptDataInput);

		JButton cptGenerateDataButton = new JButton("Generar Tabla");
		cptGenerateDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneradorDeData.generarData(new Integer(cptDataAmountInput
						.getText()));
				cptDataInput.setText(GeneradorDeData.dataEnString());
			}
		});

		cptGenerateDataButton.setBounds(182, 75, 116, 23);
		comparacionPorTabla.add(cptGenerateDataButton);

		cptEvaluationLabel = new JLabel("Evaluacion:");
		cptEvaluationLabel.setBounds(42, 108, 76, 17);
		comparacionPorTabla.add(cptEvaluationLabel);

		cptEvaluationInput = new JTextField();
		cptEvaluationInput.setBounds(106, 106, 56, 20);
		comparacionPorTabla.add(cptEvaluationInput);
		cptEvaluationInput.setColumns(10);

		cptCalcButton = new JButton("Calcular");
		cptCalcButton.setBounds(535, 409, 89, 23);
		comparacionPorTabla.add(cptCalcButton);

		cptAnswerLabel = new JLabel("Respuesta:");
		cptAnswerLabel.setBounds(378, 235, 89, 14);
		comparacionPorTabla.add(cptAnswerLabel);

		cptAnswerPanel = new JScrollPane();
		cptAnswerPanel.setBounds(378, 263, 246, 103);
		comparacionPorTabla.add(cptAnswerPanel);

		cptAnswerOutput = new JLabel("");
		cptAnswerPanel.setViewportView(cptAnswerOutput);

		cptCalcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneradorDeData.stringEnData(cptDataInput.getText()
						.toCharArray());
				cptAnswerOutput.setText(""
						+ InterpolationsManager
								.compararMetodosDeTabla(new Double(
										cptEvaluationInput.getText())));
			}
		});

		comparacionSinTabla = new JPanel();
		comparacionSinTabla.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(comparacionSinTabla, "name_338232066417383");
		comparacionSinTabla.setLayout(null);

		JLabel cstTitle = new JLabel("Steffensen, Aitken, NewtonRaphson");
		cstTitle.setHorizontalAlignment(SwingConstants.CENTER);
		cstTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cstTitle.setBounds(35, 13, 564, 57);
		comparacionSinTabla.add(cstTitle);

		cstFuncLabel = new JLabel("Ingrese la funcion:");
		cstFuncLabel.setBounds(10, 135, 130, 14);
		comparacionSinTabla.add(cstFuncLabel);

		cstFuncInput = new JTextField();
		cstFuncInput.setBounds(149, 133, 248, 17);
		comparacionSinTabla.add(cstFuncInput);
		cstFuncInput.setColumns(10);

		JLabel cstX0Label = new JLabel("Ingrese el x inicial:");
		cstX0Label.setBounds(10, 188, 130, 14);
		comparacionSinTabla.add(cstX0Label);

		cstX0Input = new JTextField();
		cstX0Input.setBounds(149, 186, 51, 17);
		comparacionSinTabla.add(cstX0Input);
		cstX0Input.setColumns(10);

		JLabel cstErrorLabel = new JLabel("Ingrese el error deseado:");
		cstErrorLabel.setBounds(10, 234, 150, 14);
		comparacionSinTabla.add(cstErrorLabel);

		cstErrorInput = new JTextField();
		cstErrorInput.setBounds(170, 232, 51, 17);
		comparacionSinTabla.add(cstErrorInput);
		cstErrorInput.setColumns(10);

		JLabel cstAnswersLabel = new JLabel("Respuestas:");
		cstAnswersLabel.setBounds(136, 311, 88, 14);
		comparacionSinTabla.add(cstAnswersLabel);

		JScrollPane cstAnswersPanel = new JScrollPane();
		cstAnswersPanel.setBounds(273, 277, 326, 106);
		comparacionSinTabla.add(cstAnswersPanel);

		JLabel cstAnswersOutput = new JLabel("");
		cstAnswersOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		cstAnswersPanel.setViewportView(cstAnswersOutput);

		cstCalcButton = new JButton("Calcular");
		cstCalcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cstAnswersOutput.setText(InterpolationsManager
						.compararMetodosDeNoTabla(cstFuncInput.getText(),
								cstX0Input.getText(), cstErrorInput.getText()));
			}
		});
		cstCalcButton.setBounds(533, 409, 89, 23);
		comparacionSinTabla.add(cstCalcButton);

		rungeKutta = new JPanel();
		rungeKutta.setLayout(null);
		frame.getContentPane().add(rungeKutta, "name_1272995421062381");

		lblRungekutta = new JLabel("Runge Kutta");
		lblRungekutta.setHorizontalAlignment(SwingConstants.CENTER);
		lblRungekutta.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblRungekutta.setBounds(10, 11, 614, 56);
		rungeKutta.add(lblRungekutta);

		rdRungePrimer = new JRadioButton("Primer Orden");
		rdRungePrimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdRungePrimer.setSelected(true);
				rdRungeSegundo.setSelected(false);
			}
		});
		rdRungePrimer.setSelected(true);
		rdRungePrimer.setBounds(42, 98, 109, 23);
		rungeKutta.add(rdRungePrimer);

		rdRungeSegundo = new JRadioButton("Segundo Orden");
		rdRungeSegundo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdRungePrimer.setSelected(false);
				rdRungeSegundo.setSelected(true);
			}
		});
		rdRungeSegundo.setBounds(256, 98, 109, 23);
		rungeKutta.add(rdRungeSegundo);

		rdRungeIteraciones = new JRadioButton("Mostrar Iteraciones");
		rdRungeIteraciones.setBounds(448, 98, 128, 23);
		rungeKutta.add(rdRungeIteraciones);

		lblRungeF = new JLabel("Ingrese f(x,y)");
		lblRungeF.setBounds(73, 130, 128, 23);
		rungeKutta.add(lblRungeF);

		txtRungeF = new JTextField();
		txtRungeF.setColumns(10);
		txtRungeF.setBounds(165, 131, 145, 23);
		rungeKutta.add(txtRungeF);

		label_2 = new JLabel("Ingrese g(x,y)");
		label_2.setBounds(73, 181, 109, 23);
		rungeKutta.add(label_2);

		txtRungeG = new JTextField();
		txtRungeG.setColumns(10);
		txtRungeG.setBounds(165, 182, 145, 22);
		rungeKutta.add(txtRungeG);

		lblXt = new JLabel("x0:");
		lblXt.setBounds(366, 130, 52, 23);
		rungeKutta.add(lblXt);

		txtRungeX = new JTextField();
		txtRungeX.setColumns(10);
		txtRungeX.setBounds(408, 131, 115, 22);
		rungeKutta.add(txtRungeX);

		label_4 = new JLabel("y0:");
		label_4.setBounds(366, 168, 46, 14);
		rungeKutta.add(label_4);

		txtRungeY = new JTextField();
		txtRungeY.setColumns(10);
		txtRungeY.setBounds(408, 164, 115, 22);
		rungeKutta.add(txtRungeY);

		label_5 = new JLabel("Ingrese h:");
		label_5.setBounds(76, 234, 75, 23);
		rungeKutta.add(label_5);

		txtRungeH = new JTextField();
		txtRungeH.setColumns(10);
		txtRungeH.setBounds(168, 237, 142, 20);
		rungeKutta.add(txtRungeH);

		lblEvaluacionn = new JLabel("Evaluacion/n:");
		lblEvaluacionn.setBounds(320, 243, 109, 14);
		rungeKutta.add(lblEvaluacionn);

		txtRungeEval = new JTextField();
		txtRungeEval.setColumns(10);
		txtRungeEval.setBounds(408, 235, 115, 23);
		rungeKutta.add(txtRungeEval);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(165, 290, 295, 104);
		rungeKutta.add(scrollPane);

		JLabel lblRungeOutput = new JLabel("");
		lblRungeOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		scrollPane.setViewportView(lblRungeOutput);

		button = new JButton("Calcular");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdRungePrimer.isSelected()) {
					lblRungeOutput.setText(RungeKutta.rungeKutta(
							txtRungeF.getText(), txtRungeH.getText(),
							txtRungeX.getText(), txtRungeY.getText(),
							txtRungeEval.getText(),
							rdRungeIteraciones.isSelected()));
				} else {
					lblRungeOutput.setText(RungeKutta.rungeKutta2ndoOrden(
							txtRungeF.getText(), txtRungeG.getText(),
							txtRungeH.getText(), txtRungeEval.getText(),
							txtRungeX.getText(), txtRungeY.getText(),
							txtRungeT.getText(),
							rdRungeIteraciones.isSelected()));
				}
			}
		});
		button.setBounds(51, 338, 89, 23);
		rungeKutta.add(button);

		label_8 = new JLabel("Respuestas:");
		label_8.setBounds(51, 290, 100, 23);
		rungeKutta.add(label_8);

		label_9 = new JLabel("t0:");
		label_9.setBounds(366, 197, 46, 14);
		rungeKutta.add(label_9);

		txtRungeT = new JTextField();
		txtRungeT.setColumns(10);
		txtRungeT.setBounds(408, 197, 115, 20);
		rungeKutta.add(txtRungeT);

		euler = new JPanel();
		frame.getContentPane().add(euler, "name_1229850624739719");
		euler.setLayout(null);

		lblEuler = new JLabel("Euler");
		lblEuler.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuler.setBounds(10, 11, 614, 56);
		euler.add(lblEuler);

		rdEulerPrimerOrden = new JRadioButton("Primer Orden");
		rdEulerPrimerOrden.setSelected(true);
		rdEulerPrimerOrden.setBounds(42, 98, 109, 23);
		euler.add(rdEulerPrimerOrden);

		rdEulerSegundoOrden = new JRadioButton("Segundo Orden");
		rdEulerSegundoOrden.setBounds(256, 98, 109, 23);
		euler.add(rdEulerSegundoOrden);

		rdEulerIteraciones = new JRadioButton("Mostrar Iteraciones");
		rdEulerIteraciones.setBounds(448, 98, 128, 23);
		euler.add(rdEulerIteraciones);

		JLabel lblIngreseFxy = new JLabel("Ingrese f(x,y)");
		lblIngreseFxy.setBounds(71, 130, 128, 23);
		euler.add(lblIngreseFxy);

		txtEulerF = new JTextField();
		txtEulerF.setBounds(163, 131, 145, 23);
		euler.add(txtEulerF);
		txtEulerF.setColumns(10);

		JLabel lblIngreseGxy = new JLabel("Ingrese g(x,y)");
		lblIngreseGxy.setBounds(71, 181, 109, 23);
		euler.add(lblIngreseGxy);

		txtEulerG = new JTextField();
		txtEulerG.setBounds(163, 182, 145, 22);
		euler.add(txtEulerG);
		txtEulerG.setColumns(10);

		JLabel lblX = new JLabel("x0:");
		lblX.setBounds(364, 130, 52, 23);
		euler.add(lblX);

		txtEulerX0 = new JTextField();
		txtEulerX0.setBounds(406, 131, 115, 22);
		euler.add(txtEulerX0);
		txtEulerX0.setColumns(10);

		JLabel lblY = new JLabel("y0:");
		lblY.setBounds(364, 168, 46, 14);
		euler.add(lblY);

		txtEulerY0 = new JTextField();
		txtEulerY0.setBounds(406, 164, 115, 22);
		euler.add(txtEulerY0);
		txtEulerY0.setColumns(10);

		JLabel lblIngreseH = new JLabel("Ingrese h:");
		lblIngreseH.setBounds(74, 234, 75, 23);
		euler.add(lblIngreseH);

		txtEulerH = new JTextField();
		txtEulerH.setBounds(166, 237, 142, 20);
		euler.add(txtEulerH);
		txtEulerH.setColumns(10);

		JLabel lblEvaluacion = new JLabel("Evaluacion/n:");
		lblEvaluacion.setBounds(318, 238, 109, 14);
		euler.add(lblEvaluacion);

		txtEulerEval = new JTextField();
		txtEulerEval.setBounds(406, 235, 115, 23);
		euler.add(txtEulerEval);
		txtEulerEval.setColumns(10);

		JScrollPane spEulerAnswer = new JScrollPane();
		spEulerAnswer.setBounds(163, 286, 295, 104);
		euler.add(spEulerAnswer);

		JLabel lblEulerOutput = new JLabel("");
		lblEulerOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		spEulerAnswer.setViewportView(lblEulerOutput);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdEulerPrimerOrden.isSelected()) {
					lblEulerOutput.setText(Euler.euler(txtEulerF.getText(),
							txtEulerH.getText(), txtEulerX0.getText(),
							txtEulerY0.getText(), txtEulerEval.getText(),
							rdEulerIteraciones.isSelected()));
				} else {
					lblEulerOutput.setText(Euler.eulerSeg(txtEulerF.getText(),
							txtEulerG.getText(), txtEulerH.getText(),
							txtEulerEval.getText(), txtEulerX0.getText(),
							txtEulerY0.getText(), txtEulerT0.getText(),
							rdEulerIteraciones.isSelected()));
				}
			}
		});
		btnCalcular.setBounds(42, 332, 89, 23);
		euler.add(btnCalcular);

		JLabel lblRespuestas = new JLabel("Respuestas:");
		lblRespuestas.setBounds(42, 284, 100, 23);
		euler.add(lblRespuestas);

		lblT = new JLabel("t0:");
		lblT.setBounds(364, 197, 46, 14);
		euler.add(lblT);

		txtEulerT0 = new JTextField();
		txtEulerT0.setBounds(406, 197, 115, 20);
		euler.add(txtEulerT0);
		txtEulerT0.setColumns(10);

		eulerMejorado = new JPanel();
		eulerMejorado.setLayout(null);
		frame.getContentPane().add(eulerMejorado, "name_1306003743633492");

		lblEulerMejorado = new JLabel("Euler Mejorado");
		lblEulerMejorado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEulerMejorado.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEulerMejorado.setBounds(10, 11, 614, 56);
		eulerMejorado.add(lblEulerMejorado);

		rdEulerMejoradoPrimerOrden = new JRadioButton("Primer Orden");
		rdEulerMejoradoPrimerOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdEulerMejoradoPrimerOrden.setSelected(true);
				rdEulerMejoradoSegundoOrden.setSelected(false);
			}
		});
		rdEulerMejoradoPrimerOrden.setSelected(true);
		rdEulerMejoradoPrimerOrden.setBounds(42, 98, 109, 23);
		eulerMejorado.add(rdEulerMejoradoPrimerOrden);

		rdEulerMejoradoSegundoOrden = new JRadioButton("Segundo Orden");
		rdEulerMejoradoSegundoOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdEulerMejoradoPrimerOrden.setSelected(false);
				rdEulerMejoradoSegundoOrden.setSelected(true);
			}
		});
		rdEulerMejoradoSegundoOrden.setBounds(256, 98, 109, 23);
		eulerMejorado.add(rdEulerMejoradoSegundoOrden);

		rdEulerMejoradoIteraciones = new JRadioButton("Mostrar Iteraciones");
		rdEulerMejoradoIteraciones.setBounds(448, 98, 128, 23);
		eulerMejorado.add(rdEulerMejoradoIteraciones);

		label_1 = new JLabel("Ingrese f(x,y)");
		label_1.setBounds(52, 130, 128, 23);
		eulerMejorado.add(label_1);

		txtEulerMejoradoF = new JTextField();
		txtEulerMejoradoF.setColumns(10);
		txtEulerMejoradoF.setBounds(144, 131, 145, 23);
		eulerMejorado.add(txtEulerMejoradoF);

		label_3 = new JLabel("Ingrese g(x,y)");
		label_3.setBounds(52, 181, 109, 23);
		eulerMejorado.add(label_3);

		txtEulerMejoradoG = new JTextField();
		txtEulerMejoradoG.setColumns(10);
		txtEulerMejoradoG.setBounds(144, 182, 145, 22);
		eulerMejorado.add(txtEulerMejoradoG);

		label_6 = new JLabel("x0:");
		label_6.setBounds(345, 130, 52, 23);
		eulerMejorado.add(label_6);

		txtEulerMejoradoX0 = new JTextField();
		txtEulerMejoradoX0.setColumns(10);
		txtEulerMejoradoX0.setBounds(387, 131, 115, 22);
		eulerMejorado.add(txtEulerMejoradoX0);

		label_10 = new JLabel("y0:");
		label_10.setBounds(345, 168, 46, 14);
		eulerMejorado.add(label_10);

		txtEulerMejoradoY0 = new JTextField();
		txtEulerMejoradoY0.setColumns(10);
		txtEulerMejoradoY0.setBounds(387, 164, 115, 22);
		eulerMejorado.add(txtEulerMejoradoY0);

		label_11 = new JLabel("Ingrese h:");
		label_11.setBounds(55, 234, 75, 23);
		eulerMejorado.add(label_11);

		txtEulerMejoradoH = new JTextField();
		txtEulerMejoradoH.setColumns(10);
		txtEulerMejoradoH.setBounds(147, 237, 142, 20);
		eulerMejorado.add(txtEulerMejoradoH);

		label_12 = new JLabel("Evaluacion/n:");
		label_12.setBounds(299, 238, 109, 14);
		eulerMejorado.add(label_12);

		txtEulerMejoradoEval = new JTextField();
		txtEulerMejoradoEval.setColumns(10);
		txtEulerMejoradoEval.setBounds(387, 235, 115, 23);
		eulerMejorado.add(txtEulerMejoradoEval);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(159, 286, 295, 104);
		eulerMejorado.add(scrollPane_1);

		lblEulerMejoradoOutput = new JLabel("");
		lblEulerMejoradoOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		scrollPane_1.setViewportView(lblEulerMejoradoOutput);

		button_2 = new JButton("Calcular");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdEulerMejoradoPrimerOrden.isSelected()) {
					lblEulerMejoradoOutput.setText(EulerMejorado.eulerMejorado(
							txtEulerMejoradoF.getText(),
							txtEulerMejoradoH.getText(),
							txtEulerMejoradoX0.getText(),
							txtEulerMejoradoY0.getText(),
							txtEulerMejoradoEval.getText(),
							rdEulerMejoradoIteraciones.isSelected()));
				} else {
					lblEulerMejoradoOutput.setText(EulerMejorado
							.eulerMejoradoSeg(txtEulerMejoradoF.getText(),
									txtEulerMejoradoG.getText(),
									txtEulerMejoradoH.getText(),
									txtEulerMejoradoEval.getText(),
									txtEulerMejoradoX0.getText(),
									txtEulerMejoradoY0.getText(),
									txtEulerMejoradoT0.getText(),
									rdEulerMejoradoIteraciones.isSelected()));
				}
			}
		});
		button_2.setBounds(30, 334, 89, 23);
		eulerMejorado.add(button_2);

		label_14 = new JLabel("Respuestas:");
		label_14.setBounds(30, 286, 100, 23);
		eulerMejorado.add(label_14);

		label_15 = new JLabel("t0:");
		label_15.setBounds(345, 197, 46, 14);
		eulerMejorado.add(label_15);

		txtEulerMejoradoT0 = new JTextField();
		txtEulerMejoradoT0.setColumns(10);
		txtEulerMejoradoT0.setBounds(387, 197, 115, 20);
		eulerMejorado.add(txtEulerMejoradoT0);

		eulerModificado = new JPanel();
		eulerModificado.setLayout(null);
		frame.getContentPane().add(eulerModificado, "name_1307223093685570");

		lblEulerModificado = new JLabel("Euler Modificado");
		lblEulerModificado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEulerModificado.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEulerModificado.setBounds(10, 11, 614, 56);
		eulerModificado.add(lblEulerModificado);

		rdEulerModificadoPrimerOrden = new JRadioButton("Primer Orden");
		rdEulerModificadoPrimerOrden.setSelected(true);
		rdEulerModificadoPrimerOrden.setBounds(42, 98, 109, 23);
		eulerModificado.add(rdEulerModificadoPrimerOrden);

		rdEulerModificadoSegundoOrden = new JRadioButton("Segundo Orden");
		rdEulerModificadoSegundoOrden.setBounds(256, 98, 109, 23);
		eulerModificado.add(rdEulerModificadoSegundoOrden);

		rdEulerModificadoIteraciones = new JRadioButton("Mostrar Iteraciones");
		rdEulerModificadoIteraciones.setBounds(448, 98, 128, 23);
		eulerModificado.add(rdEulerModificadoIteraciones);

		label_16 = new JLabel("Ingrese f(x,y)");
		label_16.setBounds(71, 130, 128, 23);
		eulerModificado.add(label_16);

		txtEulerModificadoF = new JTextField();
		txtEulerModificadoF.setColumns(10);
		txtEulerModificadoF.setBounds(163, 131, 145, 23);
		eulerModificado.add(txtEulerModificadoF);

		label_17 = new JLabel("Ingrese g(x,y)");
		label_17.setBounds(71, 181, 109, 23);
		eulerModificado.add(label_17);

		txtEulerModificadoG = new JTextField();
		txtEulerModificadoG.setColumns(10);
		txtEulerModificadoG.setBounds(163, 182, 145, 22);
		eulerModificado.add(txtEulerModificadoG);

		label_18 = new JLabel("x0:");
		label_18.setBounds(364, 130, 52, 23);
		eulerModificado.add(label_18);

		txtEulerModificadoX0 = new JTextField();
		txtEulerModificadoX0.setColumns(10);
		txtEulerModificadoX0.setBounds(406, 131, 115, 22);
		eulerModificado.add(txtEulerModificadoX0);

		label_19 = new JLabel("y0:");
		label_19.setBounds(364, 168, 46, 14);
		eulerModificado.add(label_19);

		txtEulerModificadoY0 = new JTextField();
		txtEulerModificadoY0.setColumns(10);
		txtEulerModificadoY0.setBounds(406, 164, 115, 22);
		eulerModificado.add(txtEulerModificadoY0);

		label_20 = new JLabel("Ingrese h:");
		label_20.setBounds(74, 234, 75, 23);
		eulerModificado.add(label_20);

		txtEulerModificadoH = new JTextField();
		txtEulerModificadoH.setColumns(10);
		txtEulerModificadoH.setBounds(166, 237, 142, 20);
		eulerModificado.add(txtEulerModificadoH);

		label_21 = new JLabel("Evaluacion/n:");
		label_21.setBounds(318, 238, 109, 14);
		eulerModificado.add(label_21);

		txtEulerModificadoEval = new JTextField();
		txtEulerModificadoEval.setColumns(10);
		txtEulerModificadoEval.setBounds(406, 235, 115, 23);
		eulerModificado.add(txtEulerModificadoEval);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(155, 285, 295, 104);
		eulerModificado.add(scrollPane_2);

		lblEulerModificadoOutput = new JLabel("");
		lblEulerModificadoOutput
				.setFont(new Font("Courier New", Font.PLAIN, 13));
		scrollPane_2.setViewportView(lblEulerModificadoOutput);

		button_4 = new JButton("Calcular");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdEulerModificadoPrimerOrden.isSelected()) {
					lblEulerModificadoOutput.setText(EulerModificado
							.eulerModificado(txtEulerModificadoF.getText(),
									txtEulerModificadoH.getText(),
									txtEulerModificadoX0.getText(),
									txtEulerModificadoY0.getText(),
									txtEulerModificadoEval.getText(),
									rdEulerModificadoIteraciones.isSelected()));
				} else {
					lblEulerModificadoOutput.setText(EulerModificado
							.eulerModificadoSeg(txtEulerModificadoF.getText(),
									txtEulerModificadoG.getText(),
									txtEulerModificadoH.getText(),
									txtEulerModificadoEval.getText(),
									txtEulerModificadoX0.getText(),
									txtEulerModificadoY0.getText(),
									txtEulerModificadoT0.getText(),
									rdEulerModificadoIteraciones.isSelected()));
				}
			}
		});
		button_4.setBounds(30, 333, 89, 23);
		eulerModificado.add(button_4);

		label_23 = new JLabel("Respuestas:");
		label_23.setBounds(30, 285, 100, 23);
		eulerModificado.add(label_23);

		label_24 = new JLabel("t0:");
		label_24.setBounds(364, 197, 46, 14);
		eulerModificado.add(label_24);

		txtEulerModificadoT0 = new JTextField();
		txtEulerModificadoT0.setColumns(10);
		txtEulerModificadoT0.setBounds(406, 197, 115, 20);
		eulerModificado.add(txtEulerModificadoT0);

		comparacionEcuDif = new JPanel();
		comparacionEcuDif.setLayout(null);
		frame.getContentPane().add(comparacionEcuDif, "name_1310050415104494");

		lblComparacionDeMetodos = new JLabel("Comparacion de Metodos");
		lblComparacionDeMetodos.setHorizontalAlignment(SwingConstants.CENTER);
		lblComparacionDeMetodos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblComparacionDeMetodos.setBounds(10, 11, 614, 56);
		comparacionEcuDif.add(lblComparacionDeMetodos);

		rdCompEcuDifPrimerOrden = new JRadioButton("Primer Orden");
		rdCompEcuDifPrimerOrden.setSelected(true);
		rdCompEcuDifPrimerOrden.setBounds(42, 98, 109, 23);
		comparacionEcuDif.add(rdCompEcuDifPrimerOrden);

		rdCompEcuDifSegundoOrden = new JRadioButton("Segundo Orden");
		rdCompEcuDifSegundoOrden.setBounds(256, 98, 109, 23);
		comparacionEcuDif.add(rdCompEcuDifSegundoOrden);

		rdCompEcuDifIteraciones = new JRadioButton("Mostrar Iteraciones");
		rdCompEcuDifIteraciones.setBounds(448, 98, 128, 23);
		comparacionEcuDif.add(rdCompEcuDifIteraciones);

		label_34 = new JLabel("Ingrese f(x,y)");
		label_34.setBounds(52, 130, 128, 23);
		comparacionEcuDif.add(label_34);

		txtCompEcuDifF = new JTextField();
		txtCompEcuDifF.setColumns(10);
		txtCompEcuDifF.setBounds(144, 131, 145, 23);
		comparacionEcuDif.add(txtCompEcuDifF);

		label_35 = new JLabel("Ingrese g(x,y)");
		label_35.setBounds(52, 181, 109, 23);
		comparacionEcuDif.add(label_35);

		txtCompEcuDifG = new JTextField();
		txtCompEcuDifG.setColumns(10);
		txtCompEcuDifG.setBounds(144, 182, 145, 22);
		comparacionEcuDif.add(txtCompEcuDifG);

		label_36 = new JLabel("x0:");
		label_36.setBounds(345, 130, 52, 23);
		comparacionEcuDif.add(label_36);

		txtCompEcuDifX0 = new JTextField();
		txtCompEcuDifX0.setColumns(10);
		txtCompEcuDifX0.setBounds(387, 131, 115, 22);
		comparacionEcuDif.add(txtCompEcuDifX0);

		label_37 = new JLabel("y0:");
		label_37.setBounds(345, 168, 46, 14);
		comparacionEcuDif.add(label_37);

		txtCompEcuDifY0 = new JTextField();
		txtCompEcuDifY0.setColumns(10);
		txtCompEcuDifY0.setBounds(387, 164, 115, 22);
		comparacionEcuDif.add(txtCompEcuDifY0);

		label_38 = new JLabel("Ingrese h:");
		label_38.setBounds(55, 234, 75, 23);
		comparacionEcuDif.add(label_38);

		txtCompEcuDifH = new JTextField();
		txtCompEcuDifH.setColumns(10);
		txtCompEcuDifH.setBounds(147, 237, 142, 20);
		comparacionEcuDif.add(txtCompEcuDifH);

		label_39 = new JLabel("Evaluacion/n:");
		label_39.setBounds(299, 238, 109, 14);
		comparacionEcuDif.add(label_39);

		txtCompEcuDifEval = new JTextField();
		txtCompEcuDifEval.setColumns(10);
		txtCompEcuDifEval.setBounds(387, 235, 115, 23);
		comparacionEcuDif.add(txtCompEcuDifEval);

		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(153, 283, 295, 104);
		comparacionEcuDif.add(scrollPane_4);

		lblCompEcuDifOutput = new JLabel("");
		lblCompEcuDifOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		scrollPane_4.setViewportView(lblCompEcuDifOutput);

		button_8 = new JButton("Calcular");
		button_8.setBounds(42, 331, 89, 23);
		comparacionEcuDif.add(button_8);

		label_41 = new JLabel("Respuestas:");
		label_41.setBounds(42, 283, 100, 23);
		comparacionEcuDif.add(label_41);

		label_42 = new JLabel("t0:");
		label_42.setBounds(345, 197, 46, 14);
		comparacionEcuDif.add(label_42);

		txtCompEcuDifT0 = new JTextField();
		txtCompEcuDifT0.setColumns(10);
		txtCompEcuDifT0.setBounds(387, 197, 115, 20);
		comparacionEcuDif.add(txtCompEcuDifT0);

		rdEulerPrimerOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdEulerPrimerOrden.setSelected(true);
				rdEulerSegundoOrden.setSelected(false);
			}
		});

		rdEulerSegundoOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdEulerPrimerOrden.setSelected(false);
				rdEulerSegundoOrden.setSelected(true);
			}
		});

		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdCompEcuDifPrimerOrden.isSelected()) {
					lblCompEcuDifOutput.setText(DiffEcuManager.compararMetodos(
							txtCompEcuDifF.getText(), txtCompEcuDifH.getText(),
							txtCompEcuDifX0.getText(),
							txtCompEcuDifY0.getText(),
							txtCompEcuDifEval.getText(),
							rdCompEcuDifIteraciones.isSelected()));
				} else {
					lblCompEcuDifOutput.setText(DiffEcuManager
							.compararMetodos2ndoOrden(txtCompEcuDifF.getText(),
									txtCompEcuDifG.getText(),
									txtCompEcuDifH.getText(),
									txtCompEcuDifEval.getText(),
									txtCompEcuDifX0.getText(),
									txtCompEcuDifY0.getText(),
									txtCompEcuDifT0.getText(),
									rdCompEcuDifIteraciones.isSelected()));
				}
			}
		});

		rdCompEcuDifPrimerOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdCompEcuDifPrimerOrden.setSelected(true);
				rdCompEcuDifSegundoOrden.setSelected(false);
			}
		});

		rdCompEcuDifSegundoOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdCompEcuDifPrimerOrden.setSelected(false);
				rdCompEcuDifSegundoOrden.setSelected(true);
			}
		});

		btnBack = new JButton(new ImageIcon(
				"C:/Paulo 2/UPB/Semestre IV/Metodos numericos/btnBack.png"));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (menuIntegrales.isShowing()) {
					menuIntegrales.setVisible(false);
					menuIntegrales.remove(btnBack);
					menuPrincipal.setVisible(true);
				} else if (menuInterpolacion.isShowing()) {
					menuInterpolacion.setVisible(false);
					menuInterpolacion.remove(btnBack);
					menuPrincipal.setVisible(true);
				} else if (menuSistemas.isShowing()) {
					menuSistemas.setVisible(false);
					menuSistemas.remove(btnBack);
					menuPrincipal.setVisible(true);
				} else if (laGrange.isShowing()) {
					laGrange.setVisible(false);
					laGrange.remove(btnBack);
					menuInterpolacion.setVisible(true);
					menuInterpolacion.add(btnBack);
					laGrange.remove(lblTituloDelPdf);
					laGrange.remove(btnCrearPdf);
					laGrange.remove(textFieldTituloPDF);
				} else if (newtonRaphson.isShowing()) {
					newtonRaphson.setVisible(false);
					newtonRaphson.remove(btnBack);
					menuInterpolacion.setVisible(true);
					menuInterpolacion.add(btnBack);
					newtonRaphson.remove(lblTituloDelPdf);
					newtonRaphson.remove(btnCrearPdf);
					newtonRaphson.remove(textFieldTituloPDF);
				} else if (newtonAscendente.isShowing()) {
					newtonAscendente.setVisible(false);
					newtonAscendente.remove(btnBack);
					menuInterpolacion.setVisible(true);
					menuInterpolacion.add(btnBack);
					newtonAscendente.remove(lblTituloDelPdf);
					newtonAscendente.remove(btnCrearPdf);
					newtonAscendente.remove(textFieldTituloPDF);
				} else if (newtonDescendente.isShowing()) {
					newtonDescendente.setVisible(false);
					newtonDescendente.remove(btnBack);
					menuInterpolacion.setVisible(true);
					menuInterpolacion.add(btnBack);
					newtonDescendente.remove(lblTituloDelPdf);
					newtonDescendente.remove(btnCrearPdf);
					newtonDescendente.remove(textFieldTituloPDF);
				} else if (gauss.isShowing()) {
					if (lblGaussIEI.isShowing()) {
						lblGaussICE.setVisible(true);
						txtGaussICE.setVisible(true);
						btnGaussACE.setVisible(true);
						lblGaussIEI.setVisible(false);
						btnGaussAE.setVisible(false);
						lblGaussEA.setVisible(false);
						sPGaussMP.setVisible(false);
						txtGaussIM.setVisible(false);
						lblGaussOPL.setVisible(false);
						sPGaussOPP.setVisible(false);
						lblGaussOP.setVisible(false);
						lblTituloDelPdf.setVisible(false);
						btnCrearPdf.setVisible(false);
						textFieldTituloPDF.setVisible(false);
					} else {
						gauss.setVisible(false);
						lblTituloDelPdf.setVisible(true);
						btnCrearPdf.setVisible(true);
						gauss.remove(btnCrearPdf);
						textFieldTituloPDF.setVisible(true);
						gauss.remove(textFieldTituloPDF);
						gauss.remove(lblTituloDelPdf);
						gauss.remove(btnBack);
						menuSistemas.setVisible(true);
						menuSistemas.add(btnBack);
					}
				} else if (gaussSeidel.isShowing()) {
					if (lblGaussSeidelIEI.isShowing()) {
						lblGaussSeidelICE.setVisible(true);
						txtGaussSeidelICE.setVisible(true);
						btnGaussSeidelACE.setVisible(true);
						lblGaussSeidelIEI.setVisible(false);
						btnGaussSeidelAE.setVisible(false);
						lblGaussSeidelEA.setVisible(false);
						sPGaussSeidelMP.setVisible(false);
						txtGaussSeidelIM.setVisible(false);
						lblGaussSeidelOPL.setVisible(false);
						sPGaussSeidelOPP.setVisible(false);
						lblGaussSeidelOP.setVisible(false);
						rdbtnGaussSeidelAcotar.setVisible(false);
						lblGaussSeidelAproxL.setVisible(false);
						txtGaussSeidelAprox.setVisible(false);
						txtGaussSeidelDI.setVisible(false);
						lblGaussSeidelDatosIniciales.setVisible(false);
						lblTituloDelPdf.setVisible(false);
						btnCrearPdf.setVisible(false);
						textFieldTituloPDF.setVisible(false);
					} else {
						gaussSeidel.setVisible(false);
						gaussSeidel.remove(btnBack);
						lblTituloDelPdf.setVisible(true);
						btnCrearPdf.setVisible(true);
						gaussSeidel.remove(btnCrearPdf);
						gaussSeidel.remove(lblTituloDelPdf);
						textFieldTituloPDF.setVisible(true);
						gaussSeidel.remove(textFieldTituloPDF);
						menuSistemas.setVisible(true);
						menuSistemas.add(btnBack);
					}
				} else if (jacobi.isShowing()) {
					if (lblJacobiIEI.isShowing()) {
						lblJacobiICE.setVisible(true);
						txtJacobiICE.setVisible(true);
						btnJacobiACE.setVisible(true);
						lblJacobiIEI.setVisible(false);
						btnJacobiAE.setVisible(false);
						lblJacobiEA.setVisible(false);
						sPJacobiMP.setVisible(false);
						txtJacobiIM.setVisible(false);
						lblJacobiOPL.setVisible(false);
						sPJacobiOPP.setVisible(false);
						lblJacobiOP.setVisible(false);
						rdbtnJacobiAcotar.setVisible(false);
						lblJacobiAproxL.setVisible(false);
						txtJacobiAprox.setVisible(false);
						txtJacobiDI.setVisible(false);
						lblJacobiDatosIniciales.setVisible(false);
						lblTituloDelPdf.setVisible(false);
						btnCrearPdf.setVisible(false);
						textFieldTituloPDF.setVisible(false);
						btnConvergencia.setVisible(false);
						respuestaConvergencia.setVisible(false);
					} else {
						jacobi.setVisible(false);
						lblTituloDelPdf.setVisible(true);
						btnCrearPdf.setVisible(true);
						jacobi.remove(btnCrearPdf);
						jacobi.remove(lblTituloDelPdf);
						textFieldTituloPDF.setVisible(true);
						jacobi.remove(textFieldTituloPDF);
						jacobi.remove(btnBack);
						menuSistemas.setVisible(true);
						menuSistemas.add(btnBack);
					}
				} else if (cuadraturaDeGauss.isShowing()) {
					cuadraturaDeGauss.setVisible(false);
					cuadraturaDeGauss.remove(btnBack);
					menuIntegrales.setVisible(true);
					menuIntegrales.add(btnBack);
					cuadraturaDeGauss.remove(lblTituloDelPdf);
					cuadraturaDeGauss.remove(btnCrearPdf);
					cuadraturaDeGauss.remove(textFieldTituloPDF);
				} else if (newtonCotes.isShowing()) {
					newtonCotes.setVisible(false);
					newtonCotes.remove(btnBack);
					menuIntegrales.setVisible(true);
					menuIntegrales.add(btnBack);
					newtonCotes.remove(lblTituloDelPdf);
					newtonCotes.remove(btnCrearPdf);
					newtonCotes.remove(textFieldTituloPDF);
				} else if (simpson.isShowing()) {
					simpson.setVisible(false);
					simpson.remove(btnBack);
					menuIntegrales.setVisible(true);
					menuIntegrales.add(btnBack);
					simpson.remove(lblTituloDelPdf);
					simpson.remove(btnCrearPdf);
					simpson.remove(textFieldTituloPDF);
				} else if (trapecios.isShowing()) {
					trapecios.setVisible(false);
					trapecios.remove(btnBack);
					menuIntegrales.setVisible(true);
					menuIntegrales.add(btnBack);
					trapecios.remove(lblTituloDelPdf);
					trapecios.remove(btnCrearPdf);
					trapecios.remove(textFieldTituloPDF);
				} else if (steffensen.isShowing()) {
					steffensen.setVisible(false);
					steffensen.remove(btnBack);
					menuInterpolacion.setVisible(true);
					menuInterpolacion.add(btnBack);
					steffensen.remove(lblTituloDelPdf);
					steffensen.remove(btnCrearPdf);
					steffensen.remove(textFieldTituloPDF);
				} else if (aitken.isShowing()) {
					aitken.setVisible(false);
					aitken.remove(btnBack);
					menuInterpolacion.setVisible(true);
					menuInterpolacion.add(btnBack);
					aitken.remove(lblTituloDelPdf);
					aitken.remove(btnCrearPdf);
					aitken.remove(textFieldTituloPDF);
				} else if (comparacionIntegracion.isShowing()) {
					comparacionIntegracion.setVisible(false);
					comparacionIntegracion.remove(btnBack);
					menuIntegrales.setVisible(true);
					menuIntegrales.add(btnBack);
					comparacionIntegracion.remove(lblTituloDelPdf);
					comparacionIntegracion.remove(btnCrearPdf);
					comparacionIntegracion.remove(textFieldTituloPDF);
				} else if (comparacionSistemas.isShowing()) {
					if (lblComparacionSistemasICE.isShowing()) {
						comparacionSistemas.setVisible(false);
						comparacionSistemas.remove(btnBack);
						lblTituloDelPdf.setVisible(true);
						btnCrearPdf.setVisible(true);
						textFieldTituloPDF.setVisible(true);
						comparacionSistemas.remove(lblTituloDelPdf);
						comparacionSistemas.remove(btnCrearPdf);
						comparacionSistemas.remove(textFieldTituloPDF);
						menuSistemas.setVisible(true);
						menuSistemas.add(btnBack);
					} else {
						textFieldTituloPDF.setVisible(false);
						btnCrearPdf.setVisible(false);
						lblTituloDelPdf.setVisible(false);
						lblComparacionSistemasICE.setVisible(true);
						txtComparacionSistemasICE.setVisible(true);
						btnComparacionSistemasACE.setVisible(true);
						lblComparacionSistemasIEI.setVisible(false);
						btnComparacionSistemasAE.setVisible(false);
						lblComparacionSistemasEA.setVisible(false);
						sPComparacionSistemasMP.setVisible(false);
						txtComparacionSistemasIM.setVisible(false);
						lblComparacionSistemasOPL.setVisible(false);
						sPComparacionSistemasOPP.setVisible(false);
						lblComparacionSistemasOP.setVisible(false);
						rdbtnComparacionSistemasAcotar.setVisible(false);
						lblComparacionSistemasAproxL.setVisible(false);
						txtComparacionSistemasAprox.setVisible(false);
						txtComparacionSistemasDI.setVisible(false);
						lblComparacionSistemasDatosIniciales.setVisible(false);
					}
				} else if (comparacionPorTabla.isShowing()) {
					comparacionPorTabla.setVisible(false);
					comparacionPorTabla.remove(btnBack);
					menuInterpolacion.setVisible(true);
					menuInterpolacion.add(btnBack);
					comparacionPorTabla.remove(lblTituloDelPdf);
					comparacionPorTabla.remove(btnCrearPdf);
					comparacionPorTabla.remove(textFieldTituloPDF);
				} else if (comparacionSinTabla.isShowing()) {
					comparacionSinTabla.setVisible(false);
					comparacionSinTabla.remove(btnBack);
					menuInterpolacion.setVisible(true);
					menuInterpolacion.add(btnBack);
					comparacionSinTabla.remove(lblTituloDelPdf);
					comparacionSinTabla.remove(btnCrearPdf);
					comparacionSinTabla.remove(textFieldTituloPDF);
				} else if (menuEcuacionesDiferenciales.isShowing()) {
					menuEcuacionesDiferenciales.remove(btnBack);
					menuEcuacionesDiferenciales.setVisible(false);
					menuPrincipal.setVisible(true);
				} else if (euler.isShowing()) {
					euler.setVisible(false);
					euler.remove(btnBack);
					menuEcuacionesDiferenciales.setVisible(true);
					menuEcuacionesDiferenciales.add(btnBack);
					euler.remove(lblTituloDelPdf);
					euler.remove(btnCrearPdf);
					euler.remove(textFieldTituloPDF);
				} else if (eulerModificado.isShowing()) {
					eulerModificado.setVisible(false);
					eulerModificado.remove(btnBack);
					menuEcuacionesDiferenciales.setVisible(true);
					menuEcuacionesDiferenciales.add(btnBack);
					eulerModificado.remove(lblTituloDelPdf);
					eulerModificado.remove(btnCrearPdf);
					eulerModificado.remove(textFieldTituloPDF);
				} else if (eulerMejorado.isShowing()) {
					eulerMejorado.setVisible(false);
					eulerMejorado.remove(btnBack);
					menuEcuacionesDiferenciales.setVisible(true);
					menuEcuacionesDiferenciales.add(btnBack);
					eulerMejorado.remove(lblTituloDelPdf);
					eulerMejorado.remove(btnCrearPdf);
					eulerMejorado.remove(textFieldTituloPDF);
				} else if (rungeKutta.isShowing()) {
					rungeKutta.setVisible(false);
					rungeKutta.remove(btnBack);
					menuEcuacionesDiferenciales.setVisible(true);
					menuEcuacionesDiferenciales.add(btnBack);
					rungeKutta.remove(lblTituloDelPdf);
					rungeKutta.remove(btnCrearPdf);
					rungeKutta.remove(textFieldTituloPDF);
				} else if (comparacionEcuDif.isShowing()) {
					comparacionEcuDif.setVisible(false);
					comparacionEcuDif.remove(btnBack);
					menuEcuacionesDiferenciales.setVisible(true);
					menuEcuacionesDiferenciales.add(btnBack);
					comparacionEcuDif.remove(lblTituloDelPdf);
					comparacionEcuDif.remove(btnCrearPdf);
					comparacionEcuDif.remove(textFieldTituloPDF);
				}
			}
		});
		btnBack.setBounds(610, 5, 20, 20);

		btnCrearPdf = new JButton("Crear PDF");
		btnCrearPdf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCrearPdf.setBounds(378, 416, 120, 25);
		btnCrearPdf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (laGrange.isShowing()) {
					GeneradorDeData.stringEnData(lgDataInput.getText()
							.toCharArray());
					if (lgNumValOption.isSelected()) {
						LaGrange.laGrangeForPDF(GeneradorDeData.getDataD(),
								new Double(lgEvaluationInput.getText()),
								textFieldTituloPDF.getText());
					} else {
						LaGrange.laGrangeForPDF(Lectura.leerTodo(LaGrange
								.laGrange(GeneradorDeData.getDataD())),
								textFieldTituloPDF.getText());
					}
				} else if (newtonRaphson.isShowing()) {
					String error = "";
					if (textFieldErrorNR.getText().equals("")) {
						error = "" + 0.00009;
					} else {
						error = textFieldErrorNR.getText();
					}
					if (nrPolynomialOption.isSelected()) {
						NewRapP.newtonRaphsonForPDF(nrFuncGradeInput.getText(),
								nrFuncInput.getText(), nrLInput.getText(),
								nrPrimeLInput.getText(), error,
								textFieldTituloPDF.getText());
					} else {
						NewRapNP.calculateForPDF(nrFuncInput.getText(),
								nrX0Input.getText(), error,
								textFieldTituloPDF.getText());
					}
				} else if (newtonAscendente.isShowing()) {
					GeneradorDeData.stringEnData(naDataInput.getText()
							.toCharArray());
					if (naNumValOption.isSelected()) {
						NewtonAscendente.newtonAscendenteForPDF(GeneradorDeData
								.getData(),
								new Double(naEvaluationInput.getText()),
								textFieldTituloPDF.getText());
					} else {
						NewtonAscendente.newtonAscendenteForPDF(Lectura
								.leerTodo(NewtonAscendente
										.newtonAscendente(GeneradorDeData
												.getData())),
								textFieldTituloPDF.getText());
					}
				} else if (newtonDescendente.isShowing()) {
					GeneradorDeData.stringEnData(ndDataInput.getText()
							.toCharArray());
					if (ndNumValOption.isSelected()) {
						NewtonDescendente.newtonDescendenteForPDF(
								GeneradorDeData.getData(), new Double(
										ndEvaluationInput.getText()),
								textFieldTituloPDF.getText());
					} else {
						NewtonDescendente.newtonDescendenteForPDF(Lectura
								.leerTodo(NewtonDescendente
										.newtonDescendente(GeneradorDeData
												.getData())),
								textFieldTituloPDF.getText());
					}
				} else if (gauss.isShowing()) {
					SistemsManager.stringEnEcs(txtGaussIM.getText());
					PDFCreator.createPdfDoc(textFieldTituloPDF.getText(),
							SistemsManager.respEnStringForPDF(Gauss
									.gauss(SistemsManager.getEcuMat())));
				} else if (gaussSeidel.isShowing()) {
					SistemsManager.stringEnEcs(txtGaussSeidelIM.getText());
					SistemsManager.setMetodo("GS");
					MetodoDeGauss.gaussSeidelForPDF(
							txtGaussSeidelAprox.getText(),
							txtGaussSeidelDI.getText(),
							rdbtnGaussSeidelAcotar.isSelected(),
							textFieldTituloPDF.getText());
				} else if (jacobi.isShowing()) {
					SistemsManager.stringEnEcs(txtJacobiIM.getText());
					SistemsManager.setMetodo("J");
					MetodoDeGauss.jacobiForPDF(txtJacobiAprox.getText(),
							txtJacobiDI.getText(),
							rdbtnJacobiAcotar.isSelected(),
							textFieldTituloPDF.getText());
				} else if (cuadraturaDeGauss.isShowing()) {
					if (selCuadGaussIS.isSelected()) {
						CuadraturaDeGauss.gauss1ForPDF(
								txtCuadGaussXI.getText(),
								txtCuadGaussXS.getText(),
								txtCuadGaussIN.getText(),
								txtCuadGaussIF.getText(),
								textFieldTituloPDF.getText());
					} else if (selCuadGaussID.isSelected()) {
						if (selCuadGaussIDF.isSelected()) {
							CuadraturaDeGauss.cuadraturaFuncForPDF(
									txtCuadGaussXI.getText(),
									txtCuadGaussXS.getText(),
									txtCuadGaussYI.getText(),
									txtCuadGaussYS.getText(),
									txtCuadGaussIN.getText(),
									txtCuadGaussIF.getText(),
									textFieldTituloPDF.getText());
						} else {
							CuadraturaDeGauss.gauss2ForPDF(
									txtCuadGaussXI.getText(),
									txtCuadGaussXS.getText(),
									txtCuadGaussYI.getText(),
									txtCuadGaussYS.getText(),
									txtCuadGaussIN.getText(),
									txtCuadGaussIF.getText(),
									textFieldTituloPDF.getText());
						}
					} else {
						CuadraturaDeGauss.gauss3ForPDF(
								txtCuadGaussXI.getText(),
								txtCuadGaussXS.getText(),
								txtCuadGaussYI.getText(),
								txtCuadGaussYS.getText(),
								txtCuadGaussZI.getText(),
								txtCuadGaussZS.getText(),
								txtCuadGaussIN.getText(),
								txtCuadGaussIF.getText(),
								textFieldTituloPDF.getText());
					}
				} else if (newtonCotes.isShowing()) {
					if (selNewCotIS.isSelected()) {
						NewtonCotes.newtonCotesForPDF(txtNewCotXI.getText(),
								txtNewCotXS.getText(), txtNewCotIN.getText(),
								txtNewCotIF.getText(),
								textFieldTituloPDF.getText());
					} else if (selNewCotID.isSelected()) {
						if (selNewCotIDF.isSelected()) {
							NewtonCotes.newtonCotesFuncForPDF(
									txtNewCotXI.getText(),
									txtNewCotXS.getText(),
									txtNewCotYI.getText(),
									txtNewCotYS.getText(),
									txtNewCotIN.getText(),
									txtNewCotIF.getText(),
									textFieldTituloPDF.getText());
						} else {
							NewtonCotes.newtonCotes2ForPDF(
									txtNewCotXI.getText(),
									txtNewCotXS.getText(),
									txtNewCotYI.getText(),
									txtNewCotYS.getText(),
									txtNewCotIN.getText(),
									txtNewCotIF.getText(),
									textFieldTituloPDF.getText());
						}
					} else {
						NewtonCotes.newtonCotes3ForPDF(txtNewCotXI.getText(),
								txtNewCotXS.getText(), txtNewCotYI.getText(),
								txtNewCotYS.getText(), txtNewCotZI.getText(),
								txtNewCotZS.getText(), txtNewCotIN.getText(),
								txtNewCotIF.getText(),
								textFieldTituloPDF.getText());
					}
				} else if (simpson.isShowing()) {
					Simpson.calculateForPDF(txtSimpsonFunc.getText(),
							txtSimpsonLimInf.getText(),
							txtSimpsonLimSup.getText(), txtSimpsonN.getText(),
							rdbtnSimpsonIntegral.isSelected(),
							textFieldTituloPDF.getText());
				} else if (trapecios.isShowing()) {
					Trapecios.calculateForPDF(txtTrapeciosFunc.getText(),
							txtTrapeciosLimInf.getText(),
							txtTrapeciosLimSup.getText(),
							txtTrapeciosN.getText(),
							rdbtnTrapeciosIntegral.isSelected(),
							textFieldTituloPDF.getText());
				} else if (steffensen.isShowing()) {
					Steffensen.steffensenForPDF(stfFuncInput.getText(),
							stfX0Input.getText(), stfErrorInput.getText(),
							textFieldTituloPDF.getText());
				} else if (aitken.isShowing()) {
					Steffensen.aitkenForPDF(aFuncInput.getText(),
							aX0Input.getText(), aX2Input.getText(),
							textFieldTituloPDF.getText());
				} else if (comparacionIntegracion.isShowing()) {
					if (selCompIntIS.isSelected()) {
						if (rdbtnCompIntArea.isSelected()) {
							IntegralsManager.calculateAllSimpleAreasForPDF(
									txtCompIntXI.getText(),
									txtCompIntXS.getText(),
									txtCompIntNP.getText(),
									txtCompIntIF.getText(),
									textFieldTituloPDF.getText());
						} else {
							IntegralsManager.calculateAllSimpleIntegralsForPDF(
									txtCompIntXI.getText(),
									txtCompIntXS.getText(),
									txtCompIntIN.getText(),
									txtCompIntNP.getText(),
									txtCompIntIF.getText(),
									textFieldTituloPDF.getText());
						}
					} else if (selCompIntID.isSelected()) {
						if (selCompIntIDF.isSelected()) {
							IntegralsManager
									.calculateAllDoubleWithFunctionIntegralsForPDF(
											txtCompIntXI.getText(),
											txtCompIntXS.getText(),
											txtCompIntYI.getText(),
											txtCompIntYS.getText(),
											txtCompIntIN.getText(),
											txtCompIntIF.getText(),
											textFieldTituloPDF.getText());
						} else {
							IntegralsManager.calculateAllDoubleIntegralsForPDF(
									txtCompIntXI.getText(),
									txtCompIntXS.getText(),
									txtCompIntYI.getText(),
									txtCompIntYS.getText(),
									txtCompIntIN.getText(),
									txtCompIntIF.getText(),
									textFieldTituloPDF.getText());
						}
					} else {
						IntegralsManager.calculateAllTripleIntegralsForPDF(
								txtCompIntXI.getText(), txtCompIntXS.getText(),
								txtCompIntYI.getText(), txtCompIntYS.getText(),
								txtCompIntZI.getText(), txtCompIntZS.getText(),
								txtCompIntIN.getText(), txtCompIntIF.getText(),
								textFieldTituloPDF.getText());
					}
				} else if (comparacionSistemas.isShowing()) {
					SistemsManager.stringEnEcs(txtComparacionSistemasIM
							.getText());
					SistemsManager.allSystemMethodsForPDF(
							txtComparacionSistemasAprox.getText(),
							txtComparacionSistemasDI.getText(),
							rdbtnComparacionSistemasAcotar.isSelected(),
							textFieldTituloPDF.getText());
				} else if (comparacionPorTabla.isShowing()) {
					GeneradorDeData.stringEnData(cptDataInput.getText()
							.toCharArray());
					InterpolationsManager.compararMetodosDeTablaForPDF(
							new Double(cptEvaluationInput.getText()),
							textFieldTituloPDF.getText());
				} else if (comparacionSinTabla.isShowing()) {
					InterpolationsManager.compararMetodosDeNoTablaForPDF(
							cstFuncInput.getText(), cstX0Input.getText(),
							cstErrorInput.getText(),
							textFieldTituloPDF.getText());
				} else if (rungeKutta.isShowing()) {
					if (rdRungePrimer.isSelected()) {
						RungeKutta.rungeKuttaForPDF(txtRungeF.getText(),
								txtRungeH.getText(), txtRungeX.getText(),
								txtRungeY.getText(), txtRungeEval.getText(),
								rdRungeIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					} else {
						RungeKutta.rungeKutta2ndoOrdenForPDF(
								txtRungeF.getText(), txtRungeG.getText(),
								txtRungeH.getText(), txtRungeEval.getText(),
								txtRungeX.getText(), txtRungeY.getText(),
								txtRungeT.getText(),
								rdRungeIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					}
				} else if (euler.isShowing()) {
					if (rdEulerPrimerOrden.isSelected()) {
						Euler.eulerForPDF(txtEulerF.getText(),
								txtEulerH.getText(), txtEulerX0.getText(),
								txtEulerY0.getText(), txtEulerEval.getText(),
								rdEulerIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					} else {
						Euler.eulerSegForPDF(txtEulerF.getText(),
								txtEulerG.getText(), txtEulerH.getText(),
								txtEulerEval.getText(), txtEulerX0.getText(),
								txtEulerY0.getText(), txtEulerT0.getText(),
								rdEulerIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					}
				} else if (eulerMejorado.isShowing()) {
					if (rdEulerMejoradoPrimerOrden.isSelected()) {
						EulerMejorado.eulerMejoradoForPDF(
								txtEulerMejoradoF.getText(),
								txtEulerMejoradoH.getText(),
								txtEulerMejoradoX0.getText(),
								txtEulerMejoradoY0.getText(),
								txtEulerMejoradoEval.getText(),
								rdEulerMejoradoIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					} else {
						EulerMejorado.eulerMejoradoSegForPDF(
								txtEulerMejoradoF.getText(),
								txtEulerMejoradoG.getText(),
								txtEulerMejoradoH.getText(),
								txtEulerMejoradoEval.getText(),
								txtEulerMejoradoX0.getText(),
								txtEulerMejoradoY0.getText(),
								txtEulerMejoradoT0.getText(),
								rdEulerMejoradoIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					}
				} else if (eulerModificado.isShowing()) {
					if (rdEulerModificadoPrimerOrden.isSelected()) {
						EulerModificado.eulerModificadoForPDF(
								txtEulerModificadoF.getText(),
								txtEulerModificadoH.getText(),
								txtEulerModificadoX0.getText(),
								txtEulerModificadoY0.getText(),
								txtEulerModificadoEval.getText(),
								rdEulerModificadoIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					} else {
						EulerModificado.eulerModificadoSegForPDF(
								txtEulerModificadoF.getText(),
								txtEulerModificadoG.getText(),
								txtEulerModificadoH.getText(),
								txtEulerModificadoEval.getText(),
								txtEulerModificadoX0.getText(),
								txtEulerModificadoY0.getText(),
								txtEulerModificadoT0.getText(),
								rdEulerModificadoIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					}
				} else if (comparacionEcuDif.isShowing()) {
					if (rdCompEcuDifPrimerOrden.isSelected()) {
						DiffEcuManager.compararMetodosForPDF(
								txtCompEcuDifF.getText(),
								txtCompEcuDifH.getText(),
								txtCompEcuDifX0.getText(),
								txtCompEcuDifY0.getText(),
								txtCompEcuDifEval.getText(),
								rdCompEcuDifIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					} else {
						DiffEcuManager.compararMetodos2ndoOrdenForPDF(
								txtCompEcuDifF.getText(),
								txtCompEcuDifG.getText(),
								txtCompEcuDifH.getText(),
								txtCompEcuDifEval.getText(),
								txtCompEcuDifX0.getText(),
								txtCompEcuDifY0.getText(),
								txtCompEcuDifT0.getText(),
								rdCompEcuDifIteraciones.isSelected(),
								textFieldTituloPDF.getText());
					}
				}
			}
		});
	}
}
