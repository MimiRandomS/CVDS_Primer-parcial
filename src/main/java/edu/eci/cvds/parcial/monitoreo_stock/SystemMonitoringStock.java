package edu.eci.cvds.parcial.monitoreo_stock;
import  edu.eci.cvds.parcial.monitoreo_stock.classes.*;
import java.util.*;

public class SystemMonitoringStock {
    private static SystemMonitoringStock instance;
    private static final Queue<String> historyNotify = new LinkedList<>();
    private static final HashMap<String, Product> products = new HashMap<>();
    private static AgentWarning agentWarning;
    private static AgentLog agentLog;

    private SystemMonitoringStock(){}

    /**
     * Obtener instancia del sistema, se implemento de manera que fuera un singleton.
     */
    public static SystemMonitoringStock getInstance(){
        if (instance == null) {
            instance = new SystemMonitoringStock();
        }
        return instance;
    }

    /**
     *
     * Agregar un producto nuevo en el sistema de stock.
     * Se espera ->
     * 1. no se podra agregar un producto ya existente en el sistema
     * (no añadira dos productos con el mismo nombre)
     *
     * @param product producto
     *
     * @return Si la operacion se hizo correctamente
     */
    public boolean addProduct(Product product){
        String nameProduct = product.getName();
        if (isProductExist(nameProduct)) return false;
        products.put(nameProduct, product);
        return true;
    }

    /**
     *
     * Actualiza la cantidad en stock del producto inidicado
     * se espera ->
     * 1. no se actualiza el stock de un producto si no existe.
     * 2. se informe correctamente a los agentes.
     *
     * @param nameProduct nombre producto
     * @param newQuantity cantidad del producto
     *
     * @return Si la operacion se hizo correctamente
     */
    public boolean updateStockProduct(String nameProduct, int newQuantity){
        if (!isProductExist(nameProduct)) return false;
        Product product = getProduct(nameProduct);
        product.setQuantity(newQuantity);
        products.put(nameProduct, product);
        createNotifications(nameProduct);
        return true;
    }

    private void createNotifications(String nameProduct){
        String message1 = agentLog.createNotifyStock(nameProduct);
        System.out.println(message1);
        String message2 = agentWarning.createNotifyStock(nameProduct);
        if (!Objects.equals(message2, "")) System.out.println(message2);
        addNotificationsHistory(message1);
        if (!Objects.equals(message2, "")) addNotificationsHistory(message2);
    }

    private void addNotificationsHistory(String notify){historyNotify.add(notify);}
    public Product getProduct(String nameProduct) {return products.get(nameProduct);}
    public boolean isProductExist(String nameProduct) {return products.containsKey(nameProduct);}
    public void emptyProducts(){products.clear();}
    public Queue<String> getHistoryNotify(){return historyNotify;}

    public void setAgentWarning(AgentWarning agentWarning) {
        SystemMonitoringStock.agentWarning = agentWarning;
    }

    public void setAgentLog(AgentLog agentLog) {
        SystemMonitoringStock.agentLog = agentLog;
    }

    public void clearHistory() { historyNotify.clear();}
}
