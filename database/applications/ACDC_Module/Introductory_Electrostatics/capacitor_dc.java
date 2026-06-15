/*
 * capacitor_dc.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class capacitor_dc {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electrostatics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 20);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 20);
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 10);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 4);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new int[]{0, 0, 8});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("layer", "5[mm]", 0);
    model.component("comp1").geom("geom1").feature("cyl2").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl2").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl2").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 0.75);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 8);
    model.component("comp1").geom("geom1").feature().duplicate("cyl4", "cyl3");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new int[]{0, 0, 12});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u91d1\u5c5e");
    model.component("comp1").selection("sel1").set(2, 4, 5, 6);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u7edd\u7f18\u4f53");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u63a5\u5730");
    model.component("comp1").selection("sel2").set(2, 5);
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").set(2, 5);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7ec8\u7aef");
    model.component("comp1").selection("sel3").set(4, 6);
    model.component("comp1").selection("sel3").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel3").set(4, 6);

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 4, 23);

    model.component("comp1").physics("es").selection().named("com1");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 3);
    model.component("comp1").physics("es").feature("ccns1").selection().set(3);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("sel2");
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("sel3");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Glass (quartz)");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2210[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.5", "0", "0", "0", "1.5", "0", "0", "0", "1.5"});
    model.component("comp1").material("mat1").selection().set(3);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().named("sel1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "dset2");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "es.normE");
    model.result("pg1").feature("slc1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg1").feature("slc1").set("quickxnumber", 1);
    model.result("pg1").feature("slc1").set("colortable", "RainbowLight");
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("xnumber", 1);
    model.result("pg1").feature("arwv1").set("ynumber", 24);
    model.result("pg1").feature("arwv1").set("znumber", 11);
    model.result("pg1").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("arwv1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("arwv1").feature("col1").set("colorlegend", false);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("levels", "range(0.1,0.1,0.9)");
    model.result("pg2").feature("con1").set("contourtype", "filled");
    model.result("pg2").feature("con1").set("colortable", "RainbowLight");
    model.result("pg2").run();
    model.result("pg2").create("con2", "Contour");
    model.result("pg2").feature("con2").set("titletype", "none");
    model.result("pg2").feature("con2").set("levelmethod", "levels");
    model.result("pg2").feature("con2").set("levels", "range(0,0.1,1)");
    model.result("pg2").feature("con2").set("contourlabels", true);
    model.result("pg2").feature("con2").set("coloring", "uniform");
    model.result("pg2").feature("con2").set("color", "black");
    model.result("pg2").feature("con2").set("colorlegend", false);
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"es.C11"});
    model.result().numerical("gev1").set("descr", new String[]{"\u9ea6\u514b\u65af\u97e6\u7535\u5bb9"});
    model.result().numerical("gev1").set("unit", new String[]{"F"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.title("\u8ba1\u7b97\u7535\u5bb9");

    model
         .description("\u7535\u5bb9\u5668\u7684\u6700\u7b80\u5355\u5f62\u5f0f\u662f\u53cc\u7aef\u7535\u6c14\u8bbe\u5907\uff0c\u5f53\u4e24\u7aef\u88ab\u65bd\u52a0\u7535\u538b\u5dee\u65f6\uff0c\u8fd9\u79cd\u7535\u5bb9\u5668\u53ef\u4ee5\u50a8\u5b58\u7535\u80fd\u3002\u50a8\u5b58\u7684\u7535\u80fd\u4e0e\u5916\u52a0\u7535\u538b\u7684\u5e73\u65b9\u6210\u6b63\u6bd4\uff0c\u5e76\u901a\u8fc7\u5668\u4ef6\u7684\u7535\u5bb9\u8fdb\u884c\u91cf\u5316\u3002\u672c\u4f8b\u4ecb\u7ecd\u4e86\u4e00\u4e2a\u7b80\u5355\u7684\u7535\u5bb9\u5668\u6a21\u578b\uff0c\u6c42\u89e3\u4e86\u9759\u7535\u6761\u4ef6\u4e0b\u7684\u7535\u573a\u548c\u5668\u4ef6\u7535\u5bb9\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("capacitor_dc.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
