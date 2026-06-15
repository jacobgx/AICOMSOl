/*
 * pcb_import.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:48 by COMSOL 6.3.0.290. */
public class pcb_import {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ECAD_Import_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "printed_circuit_board_si_geom.zip");
    model.component("comp1").geom("geom1").feature("imp1").setIndex("importlayer", false, 6);
    model.component("comp1").geom("geom1").feature("imp1").setIndex("importlayer", true, 9);
    model.component("comp1").geom("geom1").feature("imp1").set("importtype", "shell");
    model.component("comp1").geom("geom1").feature("imp1").set("ignoreoutsideboard", true);
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view().duplicate("view2", "view1");
    model.component("comp1").view("view2").camera().set("viewscaletype", "automatic");
    model.component("comp1").view("view2").set("transparency", true);

    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"imp1_TOP", "imp1_BOTTOM", "imp1_TOP_DIEL"});
    model.component("comp1").geom("geom1").feature("unisel1").set("selshow", "dom");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"imp1_TOP", "imp1_BOTTOM", "imp1_DRILL_TOP_DIEL"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("imp1_DRILL_TOP_DIEL");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat1").set("family", "pcbgreen");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "22e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material("mat1").set("family", "pcbgreen");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat1").selection().named("geom1_unisel1_dom");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("geom1_unisel2");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("rendermesh", false);

    model.component("comp1").geom("geom1").measureFinal().selection().geom("geom1", 1);
    model.component("comp1").geom("geom1").measureFinal().selection().set(317);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").feature("rmd1").set("smallfaces", false);
    model.component("comp1").geom("geom1").feature("rmd1").set("sliverfaces", false);
    model.component("comp1").geom("geom1").feature("rmd1").set("narrowfaceregions", false);
    model.component("comp1").geom("geom1").feature("rmd1").set("thindomains", false);
    model.component("comp1").geom("geom1").feature("rmd1").set("detailsizetype", "absolute");
    model.component("comp1").geom("geom1").feature("rmd1").set("maxabssize", "0.008[in]");
    model.component("comp1").geom("geom1").feature("rmd1").set("contangletol", "16[deg]");
    model.component("comp1").geom("geom1").run("rmd1");
    model.component("comp1").geom("geom1").feature("rmd1").set("automatic", "off");
    model.component("comp1").geom("geom1").runPre("rmd1/aigv4");
    model.component("comp1").geom("geom1").feature("rmd1").set("automatic", true);
    model.component("comp1").geom("geom1").run("rmd1");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "0.7[in]");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.07[in]");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .named("geom1_imp1_TOP_DIEL_bnd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "0.1[in]");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", "0.01[in]");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("rendermesh", true);

    model
         .title("\u4ece ODB++ \u5b58\u6863\u4e2d\u5bfc\u5165 PCB \u51e0\u4f55\u5e76\u8fdb\u884c\u7f51\u683c\u5212\u5206");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4ece ODB++ \u5b58\u6863\u4e2d\u5bfc\u5165\u6570\u636e\uff0c\u4ee5\u751f\u6210\u5370\u5237\u7535\u8def\u677f (PCB) \u51e0\u4f55\u7ed3\u6784\u3002\u8bf7\u6309\u7167\u64cd\u4f5c\u8bf4\u660e\u8fdb\u884c\u64cd\u4f5c\uff0c\u4e86\u89e3\u5982\u4f55\u79fb\u9664\u51e0\u4f55\u7ed3\u6784\u4e2d\u7684\u5c0f\u7ec6\u8282\uff0c\u5982\u4f55\u521b\u5efa\u7f51\u683c\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u81ea\u52a8\u751f\u6210\u7684\u9009\u62e9\u6765\u5b9a\u4e49\u7269\u7406\u573a\u548c\u7f51\u683c\u8bbe\u7f6e\u3002\n\nODB++ \u6587\u4ef6\u7531\u7f8e\u56fd\u65b0\u7f55\u5e03\u4ec0\u5c14\u5dde\u6c49\u8bfa\u5a01\u5e02\u7684 Hypertherm, Inc. \u53cb\u60c5\u63d0\u4f9b");

    model.label("pcb_import.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
