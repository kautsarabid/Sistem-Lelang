package pattern.strategy;

import model.Barang;

public interface PembayaranStrategy {
    void bayar(Barang barang);
}
