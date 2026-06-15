/*
 * microdisk_voltammetry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:46 by COMSOL 6.3.0.290. */
public class microdisk_voltammetry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\General_Electrochemistry");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cRed");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cRed", "cOx"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("E_appl", "0[V]", "\u5916\u52a0\u7535\u52bf");
    model.param().set("Ef", "0[V]", "\u8868\u89c2\u7535\u4f4d");
    model.param().set("c_bulk", "1[mmol/L]", "\u53cd\u5e94\u7269\u6d53\u5ea6");
    model.param().set("re", "10[um]", "\u7535\u6781\u534a\u5f84");
    model.param().set("r_max", "25*re", "\u4eff\u771f\u7a7a\u95f4\u7684\u5927\u5c0f");
    model.param().set("D1", "1e-9[m^2/s]", "\u53cd\u5e94\u7269\u6269\u6563\u7cfb\u6570");
    model.param().set("D2", "1e-9[m^2/s]", "\u4ea7\u7269\u6269\u6563\u7cfb\u6570");
    model.param().set("i0ref", "1000[mol/(m^2*s)]*F_const", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("E_start", "-0.4[V]", "\u8d77\u59cb\u7535\u4f4d");
    model.param().set("E_vertex", "0.4[V]", "\u9876\u70b9\u7535\u4f4d");
    model.param().set("E_step", "10[mV]", "\u7535\u4f4d\u6b65\u957f\uff08\u8f93\u51fa\uff09");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_max");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "re", 0);
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("r", "r_max*1.2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().set(2);

    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cRed", new String[]{"D1", "0", "0", "0", "D1", "0", "0", "0", "D1"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cOx", new String[]{"D2", "0", "0", "0", "D2", "0", "0", "0", "D2"});
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").set("BoundaryCondition", "ElectrodePotential");
    model.component("comp1").physics("tcd").feature("es1").set("Evsref0", "E_appl");
    model.component("comp1").physics("tcd").feature("es1").selection().set(2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Ef");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0ref");
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(7);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 0);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "re/100");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "E_appl", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("pname", "E_appl", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(E_start,E_step,E_vertex)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 81, 0);
    model.result("pg1").label("\u6d53\u5ea6, Red (tcd)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"cRed"});
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tcd.tflux_cRedr", "tcd.tflux_cRedz"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").setIndex("looplevel", 81, 0);
    model.result("pg2").label("\u6d53\u5ea6, Red, 3D (tcd)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"cRed"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 81, 0);
    model.result("pg3").label("\u6d53\u5ea6, Ox (tcd)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cOx"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tcd.tflux_cOxr", "tcd.tflux_cOxz"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 81, 0);
    model.result("pg4").label("\u6d53\u5ea6, Ox, 3D (tcd)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cOx"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg1").run();

    model.component("comp1").view("view1").axis().set("xmin", "-1e-6");
    model.component("comp1").view("view1").axis().set("xmax", "10e-5");
    model.component("comp1").view("view1").axis().set("ymin", "-0.5e-6");
    model.component("comp1").view("view1").axis().set("ymax", "10e-5");

    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "r");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "z");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").active(false);
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "cRed");
    model.result("pg1").feature("con1").set("contourtype", "tubes");
    model.result("pg1").feature("con1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("con1").set("tuberadiusscale", 1.2E-7);
    model.result("pg1").feature("con1").set("colortable", "RainbowLight");
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("posmethod", "selection");
    model.result("pg1").feature("str1").set("selnumber", 5);
    model.result("pg1").feature("str1").selection().set(2);
    model.result("pg1").feature("str1").set("arrowlength", "normalized");
    model.result("pg1").feature("str1").set("arrowcountactive", true);
    model.result("pg1").feature("str1").set("arrowcount", 100);
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", "0.4E-6");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "gray");
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection().set(2);
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("text", "\u7535\u6781\u8868\u9762");
    model.result("pg1").feature("ann1").set("posyexpr", "-1.5e-6");
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u603b\u7535\u6d41");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"tcd.Itot_es1"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u603b\u7535\u6d41"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg5").run();

    model.title("\u5fae\u76d8\u7535\u6781\u7684\u4f0f\u5b89\u66f2\u7ebf");

    model
         .description("\u7535\u5206\u6790\u4e2d\u5e38\u7528\u5fae\u7535\u6781\uff0c\u56e0\u4e3a\u5b83\u53ef\u4ee5\u5728\u6781\u5c0f\u7684\u7535\u6781\u6750\u6599\u8303\u56f4\u5185\u63d0\u4f9b\u8f83\u9ad8\u7684\u7535\u6d41\u5bc6\u5ea6\u3002\u5fae\u7535\u6781\u7684\u6269\u6563\u65f6\u95f4\u5c3a\u5ea6\u5f88\u77ed\uff0c\u610f\u5473\u7740\u53ef\u4ee5\u7cbe\u786e\u5730\u5f97\u5230\u7a33\u6001\u6d4b\u8bd5\u7ed3\u679c\uff0c\u56e0\u6b64\u53ef\u4ee5\u4f7f\u7528\u201c\u7a33\u6001\u201d\u7814\u7a76\u3002\n\n\u672c\u4f8b\u6a21\u62df\u534a\u5f84\u4e3a 10\u00a0um \u7684\u5fae\u7535\u6781\u7684\u4f0f\u5b89\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("microdisk_voltammetry.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
