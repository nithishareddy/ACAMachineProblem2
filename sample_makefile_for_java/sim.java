import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class sim {
	public static void main(String[] args) {
		int nBits = 3; // Change this to the desired N-bit predictor
		SmithNBitPredictor predictor = new SmithNBitPredictor(nBits);

		String filePath = "../traces/gcc_trace.txt"; // Change this to your file path

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int mispredictions = 0;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" ");
				int branchAddress = Integer.parseInt(parts[0], 16); // Parse hexadecimal address
				boolean outcome = parts[1].equalsIgnoreCase("t"); // 't' for taken, 'n' for not taken

				boolean prediction = predictor.predict(branchAddress);
				if (prediction != outcome) {
					//System.out.println("Misprediction at address 0x" + Integer.toHexString(branchAddress));
					mispredictions++;
				}

				predictor.update(branchAddress, outcome);
			}
			System.out.println("Total mispredictions: " + mispredictions);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Integer hexToBinary(String hex) {
		int i = Integer.parseInt(hex, 16);
		//String bin = Integer.toBinaryString(i);
		return i;
	}
}


