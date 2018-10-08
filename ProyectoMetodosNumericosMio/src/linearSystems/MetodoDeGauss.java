package linearSystems;

import pdfCreator.PDFCreator;

public class MetodoDeGauss {

	public static String jacobi(String aprox, String vectorX, boolean acotar) {
		SistemsManager.setMetodo("J");
		return SistemsManager.decodificarAnswer(jacobi(
				SistemsManager.getEcuMat(), new Integer(aprox),
				SistemsManager.sacarDatosIniciales(vectorX.toCharArray()),
				acotar));
	}

	public static void jacobiForPDF(String aprox, String vectorX,
			boolean acotar, String title) {
		SistemsManager.setMetodo("J");
		String content = "Solucion para el sistema:\n"
				+ SistemsManager.ecsEnString()
				+ SistemsManager.decodificarAnswerForPDF(jacobi(SistemsManager
						.getEcuMat(), new Integer(aprox), SistemsManager
						.sacarDatosIniciales(vectorX.toCharArray()), acotar));
		PDFCreator.createPdfDoc(title, content);
	}

	public static String jacobiForPDF(String k, String vectorX, boolean acotar) {
		SistemsManager.setMetodo("J");
		return SistemsManager.decodificarAnswerForPDF(jacobi(
				SistemsManager.getEcuMat(), new Integer(k),
				SistemsManager.sacarDatosIniciales(vectorX.toCharArray()),
				acotar));
	}

	public static boolean convergenciaRetornoJ(double[][] mat) {
		mat = comprobacionMatriz(mat);
		double[] inverseDiag = new double[mat.length];
		double[][] lu = new double[mat.length][mat.length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (i == j) {
					inverseDiag[i] = 1.0 / mat[i][i];
				} else if (j == mat[0].length - 1) {
				} else {
					lu[i][j] = mat[i][j];
				}
			}
		}
		double[][] m = multiplyMatrDiagNorm(inverseDiag, lu);
		double[] conv = convergenciaJacobi(m);
		return conv[0] == 1;
	}

	public static boolean convergenciaRetornoGS(double[][] mat) {
		mat = comprobacionMatriz(mat);
		double[][] diagLow = new double[mat.length][mat.length];
		double[][] up = new double[mat.length][mat.length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (i == j) {
					diagLow[i][i] = mat[i][i];
				} else if (j == mat[0].length - 1) {
				} else if (j < i) {
					diagLow[i][j] = mat[i][j];
				} else {
					up[i][j] = mat[i][j];
				}
			}
		}
		double[][] inverseDL = inverseDiagLowMatrix(diagLow);
		double[][] m = multiplyMatr(inverseDL, up);
		double[] conv = convergenciaJacobi(m);
		return conv[0] == 1;
	}

	public static Answer[] jacobi(double[][] mat, int aprox, double[] vectorX,
			boolean acotar) {
		// Comprobar si es diagonalmente fuerte
		mat = comprobacionMatriz(mat);
		// Asignacion de valores
		double error = 0;
		double[] ans = vectorX;
		double[] inverseDiag = new double[mat.length];
		double[][] lu = new double[mat.length][mat.length];
		double[] b = new double[mat.length];
		Answer[] sol = new Answer[aprox + 1];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (i == j) {
					inverseDiag[i] = 1.0 / mat[i][i];
				} else if (j == mat[0].length - 1) {
					b[i] = mat[i][j];
				} else {
					lu[i][j] = mat[i][j];
				}
			}
		}
		// Escribir de la forma F + MX
		double[] f = multiplyArr(inverseDiag, b);
		double[][] m = multiplyMatrDiagNorm(inverseDiag, lu);
		// Comprobar la convergencia para m
		double[] conv = convergenciaJacobi(m);
		// Norma de m
		if (conv[0] == 1) {
			// Magic happens
			double[] ans2 = ans;
			sol[0] = new Answer(vectorX, 0);
			for (int i = 1; i <= aprox; i++) {
				ans2 = minus(f, multiplyMatr(m, ans));
				// Comparar los ans y an2, guardar en algun lado para ver si
				// diverge o no
				double[] delta = minus(ans, ans2);
				// respecto a norma 1;
				double normaDelta = normaArrayUno(delta, conv[1]);
				if (acotar) {
					double error2 = (normaDelta * conv[2]) / (1 - conv[2]);
					error = Math.abs(error2);
				} else {
					error = Math.abs(normaDelta);
				}
				ans = ans2;
				sol[i] = new Answer(ans, error);
			}
			return sol;
		} else {
			for (int k = 0; k < aprox; k++) {
				double[] ans2 = new double[ans.length];
				for (int i = 0; i < mat.length; i++) {
					for (int j = 0; j < i; j++) {
						ans2[i] -= ans2[j] * mat[i][j];
					}
					for (int j = i + 1; j < mat.length; j++) {
						ans2[i] -= ans[j] * mat[i][j];
					}
					ans2[i] += mat[i][mat.length];
					ans2[i] /= mat[i][i];
				}
				double[] delta = minusForError(ans, ans2);
				double error2 = Math.abs(normaArrayUno(delta, 1));
				ans = ans2;
				if (k == 0) {
					error = error2;
					sol[k] = new Answer(ans, error);
				} else if (error2 > error) {
					return null;
				} else {
					error = error2;
					sol[k] = new Answer(ans, error);
				}
			}
			return sol;
		}
	}

	public static String gaussSeidel(String aprox, String vectorX,
			boolean acotar) {
		SistemsManager.setMetodo("GS");
		return SistemsManager.decodificarAnswer(gaussSeidel(
				SistemsManager.getEcuMat(), new Integer(aprox),
				SistemsManager.sacarDatosIniciales(vectorX.toCharArray()),
				acotar));
	}

	public static String gaussSeidelForPDF(String k, String vectorX,
			boolean acotar) {
		SistemsManager.setMetodo("GS");
		return SistemsManager.decodificarAnswerForPDF(gaussSeidel(
				SistemsManager.getEcuMat(), new Integer(k),
				SistemsManager.sacarDatosIniciales(vectorX.toCharArray()),
				acotar));
	}

	public static void gaussSeidelForPDF(String k, String vectorX,
			boolean acotar, String title) {
		SistemsManager.setMetodo("GS");
		String content = "Solucion para el sistema:/n"
				+ SistemsManager.ecsEnString()
				+ SistemsManager.decodificarAnswerForPDF(gaussSeidel(
						SistemsManager.getEcuMat(), new Integer(k),
						SistemsManager.sacarDatosIniciales(vectorX
								.toCharArray()), acotar));
		PDFCreator.createPdfDoc(title, content);
	}

	public static Answer[] gaussSeidel(double[][] mat, int aprox,
			double[] vectorX, boolean acotar) {
		// Comprobar si es diagonalmente fuerte
		mat = comprobacionMatriz(mat);
		// Asignacion de valores
		double error = 0;
		double[] ans = vectorX;
		double[][] diagLow = new double[mat.length][mat.length];
		double[][] up = new double[mat.length][mat.length];
		double[] b = new double[mat.length];
		Answer[] sol = new Answer[aprox + 1];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (i == j) {
					diagLow[i][i] = mat[i][i];
				} else if (j == mat[0].length - 1) {
					b[i] = mat[i][j];
				} else if (j < i) {
					diagLow[i][j] = mat[i][j];
				} else {
					up[i][j] = mat[i][j];
				}
			}
		}
		double[][] inverseDL = inverseDiagLowMatrix(diagLow);
		// Escribir de la forma F + MX
		double[] f = multiplyMatr(inverseDL, b);
		double[][] m = multiplyMatr(inverseDL, up);
		// Convergencia
		double[] conver = convergenciaJacobi(m);
		if (conver[0] == 1) {
			// Magic happens
			double[] ans2 = ans;
			sol[0] = new Answer(vectorX, 0);
			for (int i = 1; i <= aprox; i++) {
				ans2 = minus(f, multiplyMatr(m, ans));
				double[] delta = minus(ans, ans2);
				double normaDelta = normaArrayUno(delta, conver[1]);
				if (acotar) {
					double error2 = (normaDelta * conver[2]) / (1 - conver[2]);
					error = Math.abs(error2);
				} else {
					error = Math.abs(normaDelta);
				}
				ans = ans2;
				sol[i] = new Answer(ans, error);
			}
			return sol;
		} else {
			for (int k = 0; k < aprox; k++) {
				double[] ans2 = new double[ans.length];
				for (int i = 0; i < mat.length; i++) {
					for (int j = 0; j < i; j++) {
						ans2[i] -= ans2[j] * mat[i][j];
					}
					for (int j = i + 1; j < mat.length; j++) {
						ans2[i] -= ans[j] * mat[i][j];
					}
					ans2[i] += mat[i][mat.length];
					ans2[i] /= mat[i][i];
				}
				double[] delta = minusForError(ans, ans2);
				double error2 = Math.abs(normaArrayUno(delta, 1));
				ans = ans2;
				if (k == 0) {
					error = error2;
					sol[k] = new Answer(ans, error);
				} else if (error2 > error) {
					return null;
				} else {
					error = error2;
					sol[k] = new Answer(ans, error);
				}
			}
			return sol;
		}
	}

	private static double normaArrayUno(double[] delta, double norma) {
		if (norma == 1) {
			double max = -Double.MAX_VALUE;
			for (int i = 0; i < delta.length; i++) {
				if (Math.abs(delta[i]) > max) {
					max = Math.abs(delta[i]);
				}
			}
			return max;
		} else if (norma == 2) {
			double sum = 0;
			for (int i = 0; i < delta.length; i++) {
				sum += Math.abs(delta[i]);
			}
			return sum;
		} else if (norma == 3) {
			double cuadr = 0;
			for (int i = 0; i < delta.length; i++) {
				cuadr += delta[i] * delta[i];
			}
			return Math.pow(cuadr, 0.5);
		}
		return 0;
	}

	private static double[] convergenciaJacobi(double[][] m) {
		double[] ans = new double[3];
		ans[0] = 0;
		double[] filas = new double[m.length];
		double[] cols = new double[m.length];
		double cuadr = 0;
		// Asignacion
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				filas[i] += Math.abs(m[i][j]);
				cols[j] += Math.abs(m[i][j]);
				cuadr += m[i][j] * m[i][j];
			}
		}
		boolean fil = true;
		boolean col = true;
		boolean cuad = true;
		double norma1 = -Double.MAX_VALUE;
		double norma2 = -Double.MAX_VALUE;
		// Comprobacion
		for (int i = 0; i < m.length; i++) {
			if (filas[i] > norma1) {
				norma1 = filas[i];
			}
			if (cols[i] > norma2) {
				norma2 = cols[i];
			}
			if (filas[i] >= 1) {
				fil = false;
			}
			if (cols[i] >= 1) {
				col = false;
			}
		}
		cuadr = Math.pow(cuadr, 0.5);
		if (cuadr >= 1) {
			cuad = false;
		}

		if (fil) {
			ans[0] = 1;
			ans[1] = 1;
			ans[2] = norma1;
		} else if (col) {
			ans[0] = 1;
			ans[1] = 2;
			ans[2] = norma2;
		} else if (cuad) {
			ans[0] = 1;
			ans[1] = 3;
			ans[2] = cuadr;
		}
		return ans;
	}

	private static double[][] comprobacionMatriz(double[][] mat) {
		double[][] ans = new double[mat.length][mat.length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				ans[i][j] += Math.abs(mat[i][j]);
				for (int k = 0; k < mat.length; k++) {
					if (k != j) {
						ans[i][k] -= Math.abs(mat[i][j]);
					}
				}
			}
		}
		for (int i = 0; i < mat.length - 1; i++) {
			int index = -1;
			double max = -Integer.MAX_VALUE;
			for (int j = i; j < mat.length; j++) {
				if (ans[j][i] > max) {
					max = ans[j][i];
					index = j;
				}
			}
			if (i != index) {
				double[] ad = mat[index];
				mat[index] = mat[i];
				mat[i] = ad;
				double[] ad2 = ans[index];
				ans[index] = ans[i];
				ans[i] = ad2;

			}
		}
		return mat;
	}

	private static double[][] inverseDiagLowMatrix(double[][] mat) {
		if (mat.length == mat[0].length) {
			double[][] ans = new double[mat.length][mat.length];
			for (int i = 0; i < ans.length; i++) {
				ans[i][i] = 1;
			}
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j <= i; j++) {
					if (j == i) {
						for (int k = 0; k <= j; k++) {
							ans[i][k] /= (double) mat[i][i];
						}
					} else {
						for (int k = 0; k <= j; k++) {
							ans[i][k] += -((double) mat[i][j]) * ans[j][k];
						}
					}
				}
			}
			return ans;
		}
		return null;
	}

	private static double[] multiplyArr(double[] matA, double[] matB) {
		if (matA.length == matB.length) {
			double[] ans = new double[matA.length];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = matA[i] * matB[i];
			}
			return ans;
		}
		return null;
	}

	private static double[] minus(double[] arrayA, double[] arrayB) {
		if (arrayA.length == arrayB.length) {
			double[] ans = new double[arrayA.length];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = arrayA[i] - arrayB[i];
			}
			return ans;
		}
		return null;
	}

	private static double[] minusForError(double[] arrayA, double[] arrayB) {
		if (arrayA.length == arrayB.length) {
			double[] ans = new double[arrayA.length];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = Math.abs(arrayA[i]) - Math.abs(arrayB[i]);
			}
			return ans;
		}
		return null;
	}

	private static double[][] multiplyMatr(double[][] matA, double[][] matB) {
		if (matA[0].length == matB.length) {
			double[][] ans = new double[matA.length][matB[0].length];
			for (int i = 0; i < matA.length; i++) {
				for (int j = 0; j < matB[0].length; j++) {
					for (int k = 0; k < matA[0].length; k++) {
						ans[i][j] += matA[i][k] * matB[k][j];
					}
				}
			}
			return ans;
		}
		return null;
	}

	private static double[] multiplyMatr(double[][] matA, double[] matB) {
		if (matA[0].length == matB.length) {
			double[] ans = new double[matA.length];
			for (int i = 0; i < matA.length; i++) {
				for (int j = 0; j < matA[0].length; j++) {
					ans[i] += matA[i][j] * matB[j];
				}
			}
			return ans;
		}
		return null;
	}

	private static double[][] multiplyMatrDiagNorm(double[] matA,
			double[][] matB) {
		if (matA.length == matB.length) {
			double[][] ans = new double[matA.length][matB[0].length];
			for (int i = 0; i < matA.length; i++) {
				for (int j = 0; j < matB[0].length; j++) {
					ans[i][j] = matA[i] * matB[i][j];
				}
			}
			return ans;
		}
		return null;
	}

	// private static void printArr(double[] array) {
	// for (int i = 0; i < array.length; i++) {
	// if (i == array.length - 1) {
	// System.out.println(array[i]);
	// } else {
	// System.out.print(array[i] + " ");
	// }
	// }
	// }
	//
	// private static void printMat(double[][] mat) {
	// for (double[] i : mat) {
	// printArr(i);
	// }
	// }
}
