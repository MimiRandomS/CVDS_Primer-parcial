package edu.eci.cvds.parcial.monitoreo_stock.classes;

import edu.eci.cvds.parcial.monitoreo_stock.SystemMonitoringStock;

public class AgentWarning implements Agent{
    private static AgentWarning instance;
    private AgentWarning(){}
    public static AgentWarning getInstance(){
        if (instance == null) {
            instance = new AgentWarning();
        }
        return instance;
    }

    /**
     *
     * Notifica cuando el stock del Producto es menor que cinco
     *
     */
    @Override
    public String createNotifyStock(String nameProduct) {
        SystemMonitoringStock system = SystemMonitoringStock.getInstance();
        Product product = system.getProduct(nameProduct);
        int quantityProduct = product.getQuantity();
        if (5 < quantityProduct) return "";
        return "ALERTA!!! El stock del Producto: " + product.getName() + " es muy bajo, solo quedan " + quantityProduct +  " unidades.";
    }
}
