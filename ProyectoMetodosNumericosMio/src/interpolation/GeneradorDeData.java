package interpolation;

public class GeneradorDeData {
	private static int _cantidadDeData;
	private static double[][] _data;
	private static Double[][] _Data;

	public static double[][] getData() {
		return _data;
	}

	public static Double[][] getDataD() {
		return _Data;
	}

	private static void transformarData() {
		for (int i = 0; i < _data.length; i++) {
			for (int j = 0; j < _data[0].length; j++) {
				_Data[i][j] = _data[i][j];
			}
		}
	}

	public static void generarData(int cantidadDeData) {
		_cantidadDeData = cantidadDeData;
		_data = new double[cantidadDeData][2];
		_Data = new Double[cantidadDeData][2];
	}

	public static String dataEnString() {
		String resp = "";
		for (int i = 0; i < _cantidadDeData; i++) {
			resp += "x" + i + ": " + _data[i][0];
			resp += ", y" + i + ": " + _data[i][1];
			resp += '\n';
		}
		return resp;
	}

	public static String dataEnStringForScreen() {
		String resp = "";
		for (int i = 0; i < _cantidadDeData; i++) {
			resp += "x" + i + ": " + _data[i][0];
			resp += ", y" + i + ": " + _data[i][1];
			resp += "<br>";
		}
		return resp;
	}

	public static void stringEnData(char[] data) {
		String numero = "";
		String numeroFinal = "";
		for (int k = 0, i = 0, j = 0; k < data.length; k++) {
			if (data[k] == 'x' || data[k] == 'y') {
				k++;
			} else if ((data[k] >= '0' && data[k] <= '9') || data[k] == '.'
					|| data[k] == '+' || data[k] == '-') {
				numero += data[k];
			} else if (data[k] == ',') {
				_data[i][j] = new Double(numero);
				numero = "";
				j++;
			} else if (data[k] == '\n') {
				_data[i][j] = new Double(numero);
				numeroFinal = numero;
				numero = "";
				j = 0;
				i++;
			}
		}
		_data[_cantidadDeData - 1][1] = new Double(numeroFinal);
		transformarData();
	}

	public static void main(String[] args) {
		generarData(5);
		stringEnData("x0: 0, y0: -1\nx1: 1, y1: -1\nx2: 2, y2: 3\nx3: 3, y3: 7\nx4: 4, y4: -11\n"
				.toCharArray());
		System.out.println(dataEnString());
	}
}