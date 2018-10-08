package interpolation;

import java.util.LinkedList;
import java.util.List;

import pdfCreator.PDFCreator;

public class NewRapP {
	// public static void main(String[] args) {
	// List<List<Double>> answer = newtonRaphson(5,
	// "x^5 - 100x^4 + 3995x^3 - 79700x^2 + 794004x - 3160075", 20, -1,
	// 0.0001);
	// String content = decodificarAnswerForPDF(answer,
	// "x^5 - 100x^4 + 3995x^3 - 79700x^2 + 794004x - 3160075");
	// System.out.print(content);
	// }

	private static String fillSpace(int spacesNum) {
		String spaces = "";
		for (int i = 0; i < spacesNum + 3; i++) {
			spaces += "&nbsp;";
		}

		return spaces;
	}

	private static String fillSpaceForPDF(int spacesNum) {
		String spaces = "";
		for (int i = 0; i < spacesNum + 3; i++) {
			spaces += " ";
		}

		return spaces;
	}

	private static int[] maxLength(List<List<Double>> ans) {
		int[] maxs = new int[3];
		int maxIntervalsLength = 0;
		int maxILength = 0;
		int maxNumLength = 0;
		for (int i = 0; i < ans.size(); i++) {
			if (maxIntervalsLength < ans.get(i).get(ans.get(i).size() - 1)
					.toString().length()) {
				maxIntervalsLength = ans.get(i).get(ans.get(i).size() - 1)
						.toString().length();
			}
			if (maxIntervalsLength < ans.get(i).get(ans.get(i).size() - 2)
					.toString().length()) {
				maxIntervalsLength = ans.get(i).get(ans.get(i).size() - 2)
						.toString().length();
			}
			if (maxILength < (ans.get(i).size() + "").length()) {
				maxILength = (ans.get(i).size() + "").length();
			}
			for (int j = 0; j < ans.get(i).size() - 2; j++) {
				if (maxNumLength < ans.get(i).get(j).toString().length()) {
					maxNumLength = ans.get(i).get(j).toString().length();
				}
			}
		}
		maxs[0] = maxIntervalsLength;
		maxs[1] = maxILength;
		maxs[2] = maxNumLength;
		return maxs;
	}

	private static String decodificarAnswer(List<List<Double>> ans) {
		int[] maxLength = maxLength(ans);
		String resps = "<html>[a,b]" + fillSpace(2 * maxLength[0] - 2) + "|i"
				+ fillSpace(maxLength[1] - 1) + "|xi"
				+ fillSpace(maxLength[2] - 2) + "|error<br>";

		for (int i = 0; i < ans.size(); i++) {
			double y = ans.get(i).get(ans.get(i).size() - 1);
			double x = ans.get(i).get(ans.get(i).size() - 2);
			for (int j = 0; j < ans.get(i).size() - 2; j++) {
				resps += "["
						+ x
						+ ","
						+ y
						+ "]"
						+ fillSpace(2 * maxLength[0] - ("" + y).length()
								- ("" + x).length())
						+ "|"
						+ j
						+ fillSpace(maxLength[1] - ("" + j).length())
						+ "|"
						+ ans.get(i).get(j)
						+ fillSpace(maxLength[2]
								- ("" + ans.get(i).get(j)).length()) + "|";
				if (j == 0) {
					resps += "NN<br>";
				} else {
					resps += Math
							.abs(ans.get(i).get(j) - ans.get(i).get(j - 1));
					if (i == ans.size() - 1 && j == ans.get(i).size() - 3) {
						resps += "</html>";
					} else {
						resps += "<br>";
					}
				}
			}
		}
		return resps;
	}

	private static String decodificarAnswerForPDF(List<List<Double>> ans,
			String function, String l, String lPrima) {
		int[] maxLengths = maxLength(ans);
		String resps = "Tabla de soluciones de la funcion polinomica "
				+ function + " en el intervalo [" + l + "," + lPrima
				+ "], se encontraron " + ans.size() + " raices:\n";
		resps += "[a,b]" + fillSpaceForPDF(2 * maxLengths[0] - 2) + "|i"
				+ fillSpaceForPDF(maxLengths[1] - 1) + "|xi"
				+ fillSpaceForPDF(maxLengths[2] - 2) + "|error\n";

		for (int i = 0; i < ans.size(); i++) {
			double y = ans.get(i).get(ans.get(i).size() - 1);
			double x = ans.get(i).get(ans.get(i).size() - 2);
			for (int j = 0; j < ans.get(i).size() - 2; j++) {
				resps += "["
						+ x
						+ ","
						+ y
						+ "]"
						+ fillSpaceForPDF(2 * maxLengths[0] - ("" + y).length()
								- ("" + x).length())
						+ "|"
						+ j
						+ fillSpaceForPDF(maxLengths[1] - ("" + j).length())
						+ "|"
						+ ans.get(i).get(j)
						+ fillSpaceForPDF(maxLengths[2]
								- ("" + ans.get(i).get(j)).length()) + "|";
				if (j == 0) {
					resps += "NN\n";
				} else {
					resps += Math
							.abs(ans.get(i).get(j) - ans.get(i).get(j - 1));
					if (!(i == ans.size() - 1 && j == ans.get(i).size() - 3)) {
						resps += "\n";
					}
				}
			}
		}
		return resps;
	}

	public static String newtonRaphson(String grado, String function, String l,
			String lPrima, String errorDado) {
		return decodificarAnswer(newtonRaphson(new Integer(grado), function,
				new Double(l), new Double(lPrima), new Double(errorDado)));
	}

	public static void newtonRaphsonForPDF(String grado, String function,
			String l, String lPrima, String errorDado, String title) {
		List<List<Double>> answers = newtonRaphson(new Integer(grado),
				function, new Double(l), new Double(lPrima), new Double(
						errorDado));
		String content = decodificarAnswerForPDF(answers, function, l, lPrima);
		PDFCreator.createPdfDoc(title, content);
	}

	public static List<List<Double>> newtonRaphson(int grado, String function,
			double l, double lPrima, double errorDado) {
		if (errorDado == 0) {
			errorDado = 0.00009;
		}
		l = round(l, 2);
		lPrima = round(lPrima, 2);
		List<List<Double>> values = new LinkedList<>();
		List<Double> segundaDerivada = new LinkedList<>();
		values.add(transformToList(function, grado));
		for (int i = 1; i <= grado; i++) {
			if (i == 1) {
				values.add(derivar(values.get(0)));
			} else {
				values.add(dividir(values.get(i - 2), values.get(i - 1)));
			}
		}
		if (grado >= 2) {
			segundaDerivada = derivar(values.get(1));
		}

		double[][] intervals = tableGetIntervals(values, l, lPrima);

		List<List<Double>> answer = new LinkedList<>();
		for (int i = 0; i < intervals.length; i++) {
			double x1 = evaluateForTable(values.get(0), intervals[i][0]);
			double x2 = evaluateForTable(segundaDerivada, intervals[i][0]);
			double y1 = evaluateForTable(values.get(0), intervals[i][1]);
			double y2 = evaluateForTable(segundaDerivada, intervals[i][1]);
			int use = 0;
			if (x1 >= 0 && x2 >= 0 || x1 < 0 && x2 < 0) {
				use = 1;
			} else if (y1 >= 0 && y2 >= 0 || y1 < 0 && y2 < 0) {
				use = 2;
			}
			List<Double> answers = new LinkedList<>();
			if (use == 1) {
				answers.add(intervals[i][0]);
			} else if (use == 2) {
				answers.add(intervals[i][1]);
			}
			double error = Double.MAX_VALUE;
			int cont = 0;
			do {
				double newX = answers.get(cont)
						- evaluateForTable(values.get(0), answers.get(cont))
						/ evaluateForTable(values.get(1), answers.get(cont));
				answers.add(newX);
				error = Math.abs(answers.get(cont + 1) - answers.get(cont));
				cont++;
			} while (error > errorDado);
			answers.add(intervals[i][0]);
			answers.add(intervals[i][1]);
			answer.add(answers);
		}
		return answer;
	}

	private static double round(double l, int decs) {
		boolean negative = false;
		if (l < 0) {
			negative = true;
		}
		l = Math.abs(l);
		int ad1 = (int) (l / 1);
		int ad2 = (int) (l / 0.1) - ad1 * 10;
		int ad3 = (int) (l / 0.01) - ad1 * 100 - ad2 * 10;
		int ad4 = (int) (l / 0.001) - ad1 * 1000 - ad2 * 100 - ad3 * 10;
		if (decs == 0) {
			if (ad2 >= 5) {
				ad1++;
			}
			if (negative) {
				return new Double("-" + ad1);
			} else {
				return new Double(ad1);
			}
		} else if (decs == 1) {
			if (ad3 >= 5) {
				ad2++;
				if (ad2 == 10) {
					ad2 = 0;
					ad1++;
				}
			}
			if (negative) {
				return new Double("-" + ad1 + "." + ad2);
			} else {
				return new Double(ad1 + "." + ad2);
			}
		} else if (decs == 2) {
			if (ad4 >= 5) {
				ad3++;
				if (ad3 == 10) {
					ad3 = 0;
					ad2++;
				}
				if (ad2 == 10) {
					ad2 = 0;
					ad1++;
				}
			}
			if (negative) {
				return new Double("-" + ad1 + "." + ad2 + "" + ad3);
			} else {
				return new Double(ad1 + "." + ad2 + "" + ad3);
			}

		}
		return 0;
	}

	private static double[][] tableGetIntervals(List<List<Double>> values,
			double l, double lPrima) {
		double ad = l - lPrima;
		double inter = 0;
		if (ad < 1 && ad > 0) {
			inter = 0.01;
			ad = round(ad, 2);
		} else if (ad < 5 && ad >= 1) {
			inter = 0.05;
			ad = round(ad, 2);
		} else if (ad < 10 && ad >= 5) {
			inter = 0.1;
			ad = round(ad, 1);
		} else if (ad >= 10 && ad < 50) {
			inter = 0.5;
			ad = round(ad, 1);
		} else if (ad >= 50 && ad < 100) {
			inter = 1;
			ad = round(ad, 0);
		} else if (ad >= 100 && ad < 500) {
			inter = 5;
			ad = round(ad, 0);
		} else if (ad >= 500) {
			inter = 10;
			ad = round(ad, 0);
		}

		double[][] table = new double[values.size()][(int) (ad / inter) + 1];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				table[i][j] = evaluateForTable(values.get(i), lPrima + inter
						* (double) j);
			}
		}
		int[] change = new int[table[0].length];
		for (int i = 0; i < table[0].length; i++) {
			boolean negative = false;
			int changes = 0;
			for (int j = 0; j < table.length; j++) {
				if (j == 0) {
					if (table[j][i] < 0) {
						negative = true;
					}
				} else {
					if (table[j][i] < 0 && !negative || table[j][i] > 0
							&& negative) {
						negative = !negative;
						changes++;
					} else if (table[j][i] == 0) {
						changes = 0;
						break;
					}
				}
			}
			change[i] = changes;
		}
		int numIntervals = 0;
		if (change[0] == 0) {
			numIntervals = change[1] - change[change.length - 1];
		} else {
			numIntervals = change[0] - change[change.length - 1];
		}
		double[][] intervals = new double[numIntervals][2];
		int actual = 0;
		int saved = 0;
		for (int i = 0; i < change.length && saved < numIntervals; i++) {
			if (i == 0) {
				actual = change[i];
			} else {
				if (change[i] == 0 && i < change.length - 1
						&& change[i + 1] != 0) {
					continue;
				}
				if (change[i] != actual) {
					actual = change[i];
					intervals[saved][0] = lPrima + inter * ((double) (i - 1));
					intervals[saved][1] = lPrima + inter * ((double) i);
					saved++;
				}
			}
		}
		if (inter < 0.1) {
			roundInterval(intervals, 2);
		} else if (inter < 1) {
			roundInterval(intervals, 1);
		} else {
			roundInterval(intervals, 0);
		}
		return intervals;
	}

	private static void roundInterval(double[][] intervals, int x) {
		for (int i = 0; i < intervals.length; i++) {
			for (int j = 0; j < intervals[i].length; j++) {
				intervals[i][j] = round(intervals[i][j], x);
			}
		}
	}

	private static double evaluateForTable(List<Double> list, double d) {
		double res = 0;
		for (int i = 0; i < list.size(); i++) {
			res += list.get(i) * Math.pow(d, list.size() - i - 1);
		}
		return res;
	}

	private static List<Double> dividir(List<Double> list, List<Double> list2) {
		List<Double> resto = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			resto.add(list.get(i));
		}
		for (int i = 0; i < 2; i++) {
			double k = 0;
			for (int j = 0; j < list2.size(); j++) {
				if (j == 0) {
					k = (resto.get(i + j)) / (list2.get(j));
				} else {
					resto.set(i + j, resto.get(i + j) - k * list2.get(j));
				}
			}
		}
		resto.remove(0);
		resto.remove(0);
		for (int i = 0; i < resto.size(); i++) {
			resto.set(i, -resto.get(i));
		}
		return resto;
	}

	private static List<Double> derivar(List<Double> values) {
		List<Double> ans = new LinkedList<>();
		for (int i = 0; i < values.size() - 1; i++) {
			ans.add(values.get(i) * (values.size() - 1 - i));
		}
		return ans;
	}

	private static List<Double> transformToList(String function, int grado) {
		char[] values = function.toCharArray();
		double[] realValues = new double[grado + 1];
		String actual = "";
		String gradoS = "";
		List<Double> list = new LinkedList<>();
		for (int i = 0; i < values.length; i++) {
			if (values[i] == 'x') {
				if (i < values.length - 3 && values[1 + i] == '^') {
					if (actual.equals("") || actual.equals("-")) {
						actual += "1";
					}
					// No estas aceptando de grado 10. Cuidado
					for (int j = i + 2; j < values.length; j++) {
						if (values[j] == '0' || values[j] == '1'
								|| values[j] == '2' || values[j] == '3'
								|| values[j] == '4' || values[j] == '5'
								|| values[j] == '6' || values[j] == '7'
								|| values[j] == '8' || values[j] == '9') {
							gradoS += values[j];
						} else {
							i = j - 1;
							break;
						}
					}
					realValues[grado - new Integer(gradoS)] = new Double(actual);
					gradoS = "";
				} else {
					if (actual.equals("") || actual.equals("-")) {
						actual += "1";
					}
					realValues[realValues.length - 2] = new Double(actual);
				}
				actual = "";
			} else if (i == values.length - 1) {
				actual = actual + values[i];
				realValues[realValues.length - 1] = new Double(actual);
			} else if (values[i] == '-' || values[i] == '.' || values[i] == '0'
					|| values[i] == '1' || values[i] == '2' || values[i] == '3'
					|| values[i] == '4' || values[i] == '5' || values[i] == '6'
					|| values[i] == '7' || values[i] == '8' || values[i] == '9') {
				actual = actual + values[i];
			}
		}
		for (int i = 0; i < realValues.length; i++) {
			list.add(realValues[i]);
		}
		return list;
	}

	// private static void printAns(List<List<Double>> ans) {
	// for (int i = 0; i < ans.size(); i++) {
	// System.out.print("x" + (i + 1) + ": ");
	// for (int j = 0; j < ans.get(i).size(); j++) {
	// if (j == ans.get(i).size() - 1) {
	// System.out.println(ans.get(i).get(j) + " ");
	// } else {
	// System.out.print(ans.get(i).get(j) + " ");
	// }
	// }
	// }
	// }
}