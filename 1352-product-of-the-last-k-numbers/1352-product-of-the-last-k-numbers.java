import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefixProducts;

    public ProductOfNumbers() {
        prefixProducts = new ArrayList<>();
        // Base case of 1 simplifies multiplication and boundary checks
        prefixProducts.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            // Any product spanning across this zero will be 0, so reset the stream
            prefixProducts = new ArrayList<>();
            prefixProducts.add(1);
        } else {
            int lastProduct = prefixProducts.get(prefixProducts.size() - 1);
            prefixProducts.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {
        int n = prefixProducts.size();
        // If k >= n, a zero was encountered within the last k elements
        if (k >= n) {
            return 0;
        }
        return prefixProducts.get(n - 1) / prefixProducts.get(n - 1 - k);
    }
}
