package linearSystems;

public class Gauss {

	public static double[] gauss(double[][] mat) {
		double[][] clone = new double[mat.length][mat[0].length];
		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j < clone[i].length; j++) {
				clone[i][j] = mat[i][j];
			}
		}
		double[] ans = new double[clone.length];
		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j <= i; j++) {
				if (i == j) {
					double div = clone[i][j];
					for (int k = i; k < clone[0].length; k++) {
						clone[i][k] /= div;
					}
				} else {
					double consta = clone[i][j];
					for (int k = j + 1; k < clone[0].length; k++) {
						clone[i][k] += -consta * clone[j][k];
					}
				}
			}
		}
		for (int i = clone.length - 1; i >= 0; i--) {
			ans[i] = clone[i][clone[0].length - 1];
			for (int j = clone.length - 1; j > i; j--) {
				ans[i] -= clone[i][j] * ans[j];
			}
		}
		return ans;
	}

	// public static void main(String[] args) {
	// double[][] d = new double[3][4];
	// d[0][0] = 1;
	// d[0][1] = 2;
	// d[0][2] = 1;
	// d[0][3] = -1;
	// d[1][0] = 2;
	// d[1][1] = -1;
	// d[1][2] = -1;
	// d[1][3] = -1;
	// d[2][0] = 1;
	// d[2][1] = -1;
	// d[2][2] = 2;
	// d[2][3] = -6;
	//
	// double[] a = gauss (d);
	// for (int i = 0; i < 3; i++) {
	// System.out.println(a[i]);
	// }
	// }
}