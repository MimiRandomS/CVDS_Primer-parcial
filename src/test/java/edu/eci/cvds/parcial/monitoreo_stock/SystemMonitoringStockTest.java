package edu.eci.cvds.parcial.monitoreo_stock;
import static org.junit.jupiter.api.Assertions.*;

import edu.eci.cvds.parcial.monitoreo_stock.classes.AgentLog;
import edu.eci.cvds.parcial.monitoreo_stock.classes.AgentWarning;
import edu.eci.cvds.parcial.monitoreo_stock.classes.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

public class SystemMonitoringStockTest {
    private final SystemMonitoringStock system = SystemMonitoringStock.getInstance();
    @BeforeEach
    public void setUp(){
        system.emptyProducts();
        system.clearHistory();
        system.setAgentLog(AgentLog.getInstance());
        system.setAgentWarning(AgentWarning.getInstance());
    }
    @Test
    public void shouldAddProductNotExist(){
        Product productA = new Product("Xbox One", 200, 32, "Consolas");
        boolean b = system.addProduct(productA);
        assertTrue(b);
    }

    @Test
    public void shouldNotAddProductExist(){
        Product productA = new Product("Xbox One", 200, 32, "Consolas");
        system.addProduct(productA);
        Product productB = new Product("Xbox One", 250, 45, "Consolas");
        boolean b = system.addProduct(productB);
        assertFalse(b);
    }

    @Test
    public void shouldUpdateStockProductExist(){
        Product productA = new Product("Xbox One", 200, 32, "Consolas");
        system.addProduct(productA);
        boolean b = system.updateStockProduct("Xbox One", 16);
        assertTrue(b);
    }

    @Test
    public void shouldNotUpdateStockProductNotExist(){
        boolean b = system.updateStockProduct("Xbox One", 16);
        assertFalse(b);
    }

    @Test
    public void shouldAgentsNotifyIftheProductIsUpdateStock(){
        Product productA = new Product("Xbox One", 200, 32, "Consolas");
        system.addProduct(productA);
        system.updateStockProduct("Xbox One", 16);
        Queue<String> historyNotify = system.getHistoryNotify();
        String message = historyNotify.remove();
        assertEquals("Producto: Xbox One -> 16 unidades disponibles", message);
    }

    @Test
    public void shouldAgentsWarningIftheProductHas5InStock(){
        Product productA = new Product("Xbox One", 200, 32, "Consolas");
        system.addProduct(productA);
        system.updateStockProduct("Xbox One", 4);
        Queue<String> historyNotify = system.getHistoryNotify();
        String message1 = historyNotify.remove();
        assertEquals("Producto: Xbox One -> 4 unidades disponibles", message1);
        String message2 = historyNotify.remove();
        assertEquals("ALERTA!!! El stock del Producto: Xbox One es muy bajo, solo quedan 4 unidades.", message2);
    }

}
