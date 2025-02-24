package edu.eci.cvds.parcial.monitoreo_stock.classes;

import edu.eci.cvds.parcial.monitoreo_stock.SystemMonitoringStock;

public class AgentLog implements Agent{
    private static AgentLog instance;
    private AgentLog(){}
    public static AgentLog getInstance(){
        if (instance == null) {
            instance = new AgentLog();
        }
        return instance;
    }

    /**
     *
     * Notifica el estado actual del Producto
     *
     */
    @Override
    public String createNotifyStock(String nameProduct) {
        SystemMonitoringStock system = SystemMonitoringStock.getInstance();
        Product product = system.getProduct(nameProduct);
        return "Producto: " + product.getName() + " -> " + product.getQuantity() + " unidades disponibles";
    }
}
