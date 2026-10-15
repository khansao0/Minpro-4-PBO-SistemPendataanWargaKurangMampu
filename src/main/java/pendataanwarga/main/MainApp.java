/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendataanwarga.main;

import pendataanwarga.controller.WargaController;
import pendataanwarga.view.WargaView;

/**
 *
 * @author qonitah
 */
public class MainApp {
    public static void main(String[] args) {
        WargaController controller = new WargaController("DATA-PUSAT-01", "14 Oktober 2026", true);
        WargaView view = new WargaView(controller);
        view.jalankanMenu();
    }
}