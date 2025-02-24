package edu.eci.cvds.parcial.monitoreo_stock;
import static org.junit.jupiter.api.Assertions.*;

import edu.eci.cvds.parcial.monitoreo_stock.classes.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class SystemMonitoringStockTest {
    @BeforeEach
    public void setUp(){
        SystemMonitoringStock.emptyProducts();
    }
    @Test
    public void shouldAddProductNotExist(){
        Product productA = new Product("Xbox One", 200, 32, "Consolas");
        boolean b = SystemMonitoringStock.addProduct(productA);
        assertTrue(b);
    }

    @Test
    public void shouldNotAddProductExist(){
        Product productA = new Product("Xbox One", 200, 32, "Consolas");
        SystemMonitoringStock.addProduct(productA);
        Product productB = new Product("Xbox One", 250, 45, "Consolas");
        boolean b = SystemMonitoringStock.addProduct(productB);
        assertFalse(b);
    }

    @Test
    public void shouldUpdateStockProductExist(){
        Product productA = new Product("Xbox One", 200, 32, "Consolas");
        SystemMonitoringStock.addProduct(productA);
        boolean b = SystemMonitoringStock.updateStockProduct("Xbox One", 16);
        assertTrue(b);
    }

    @Test
    public void shouldNotUpdateStockProductNotExist(){
        boolean b = SystemMonitoringStock.updateStockProduct("Xbox One", 16);
        assertFalse(b);
    }

    @Test
    public void shouldAgentsNotify(){
        // i don't know
    }

}
