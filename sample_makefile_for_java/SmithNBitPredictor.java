public class SmithNBitPredictor {
    private int historyRegister;
    private int[] predictionTable;
    private int nBits;

    public SmithNBitPredictor(int n) {
        this.nBits = n;
        historyRegister = 0;
        predictionTable = new int[1 << nBits]; // 2^nBits entries
        for (int i = 0; i < predictionTable.length; i++) {
            predictionTable[i] = (1 << nBits) - 1; // Initialize to maximum value
        }
    }

    public boolean predict(int branchAddress) {
        int predictionIndex = branchAddress & ((1 << nBits) - 1); // Masking for table index
        int prediction = predictionTable[predictionIndex];
        return prediction >= (1 << (nBits - 1)); // Predict taken if MSB of prediction is 1
    }

    public void update(int branchAddress, boolean outcome) {
        int predictionIndex = branchAddress & ((1 << nBits) - 1);
        int prediction = predictionTable[predictionIndex];

        // Update prediction table based on actual outcome
        if (outcome) {
            if (prediction < (1 << nBits) - 1) {
                predictionTable[predictionIndex]++; // Increment if prediction is not saturated
            }
        } else {
            if (prediction > 0) {
                predictionTable[predictionIndex]--; // Decrement if prediction is not saturated
            }
        }

        // Update history register
        historyRegister = (historyRegister << 1 | (outcome ? 1 : 0)) & ((1 << nBits) - 1);
    }

    // Other methods can be added for testing or additional functionality
}
