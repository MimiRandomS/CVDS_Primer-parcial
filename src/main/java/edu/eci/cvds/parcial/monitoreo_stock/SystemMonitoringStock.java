package edu.eci.cvds.parcial.monitoreo_stock;
import  edu.eci.cvds.parcial.monitoreo_stock.classes.*;

import java.util.HashMap;

public class SystemMonitoringStock {
    private static SystemMonitoringStock instace;
    private final Agent agentWarning = new AgentWarning();
    private final Agent agentLog = new AgentLog();
    private static final HashMap<String, Product> products = new HashMap<>();

    private SystemMonitoringStock(){
    }

    /**
     * Obtener instancia del sistema, se implemento de manera que fuera un singleton.
     */
    private SystemMonitoringStock getInstance(){
        return instace;
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
    public static boolean addProduct(Product product){
        return false;
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
    public static boolean updateStockProduct(String nameProduct, int newQuantity){
        return false;
    }

    public static Product getProduct(String nameProduct) {return products.get(nameProduct);}
    public static boolean isProductExist(String nameProduct) {return products.containsKey(nameProduct);}
    public static void emptyProducts(){products.clear();}
}
