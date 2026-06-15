/*
 * capacitor_tunable.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class capacitor_tunable {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Capacitive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("esbe", "ElectrostaticsBoundaryElements", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/esbe", true);

    model.component("comp1").geom("geom1").insertFile("capacitor_tunable_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u63a5\u5730\u5e73\u9762");
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection("sel1").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7ec8\u7aef");
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").set(1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7535\u4ecb\u8d28");
    model.component("comp1").material("mat1").selection().set();
    model.component("comp1").material("mat1").selection().allVoids();
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"4.2"});

    model.component("comp1").physics("esbe").selection().set();
    model.component("comp1").physics("esbe").selection().allVoids();
    model.component("comp1").physics("esbe").create("gnd1", "Ground", 2);
    model.component("comp1").physics("esbe").feature("gnd1").selection().named("sel1");
    model.component("comp1").physics("esbe").create("term1", "Terminal", 2);
    model.component("comp1").physics("esbe").feature("term1").selection().named("sel2");
    model.component("comp1").physics("esbe").feature("term1").set("TerminalType", "Voltage");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("d1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset1");
    model.result().dataset("grid1").set("par1", "x");
    model.result().dataset("grid1").set("par2", "y");
    model.result().dataset("grid1").set("par3", "z");
    model.result().dataset("grid1").set("parmin1", -400);
    model.result().dataset("grid1").set("parmax1", 680);
    model.result().dataset("grid1").set("parmin2", -300);
    model.result().dataset("grid1").set("parmax2", 600);
    model.result().dataset("grid1").set("parmin3", -54.000000000000014);
    model.result().dataset("grid1").set("parmax3", 108.00000000000003);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "grid1");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"esbe.V"});
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("colortable", "Dipole");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").label("\u7535\u52bf (esbe)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("expr", new String[]{"esbe.Ex", "esbe.Ey", "esbe.Ez"});
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", "0.02");
    model.result("pg1").feature("strmsl1").set("smooth", "internal");
    model.result("pg1").feature("strmsl1").set("maxlen", "0.4");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("expr", new String[]{"esbe.V"});
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"esbe.V"});
    model.result("pg1").feature("surf1").set("data", "dset1");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("smooth", "material");
    model.result("pg1").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"1"});
    model.result("pg1").feature("line1").set("data", "dset1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("solutionparams", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "grid1");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"esbe.normE"});
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", "-0.8");
    model.result("pg2").feature("mslc1").set("smooth", "internal");
    model.result("pg2").label("\u7535\u573a\u6a21 (esbe)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"esbe.Ex", "esbe.Ey", "esbe.Ez"});
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", "0.02");
    model.result("pg2").feature("strmsl1").set("smooth", "internal");
    model.result("pg2").feature("strmsl1").set("maxlen", "0.4");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", new String[]{"esbe.normE"});
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", "-0.8");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"1"});
    model.result("pg2").feature("line1").set("data", "dset1");
    model.result("pg2").feature("line1").set("titletype", "none");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").feature("line1").set("solutionparams", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", 320);
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", 320);
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", -20);
    model.result("pg1").run();
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", 320);
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", 320);
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", -20);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", 350);
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", 350);
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", 350);
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", 350);
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"esbe.Q0_1"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7ec8\u7aef\u7535\u8377"});
    model.result().numerical("gev1").set("unit", new String[]{"C"});
    model.result().numerical("gev1").setIndex("expr", "esbe.Q0_1/esbe.V0_1", 0);
    model.result().numerical("gev1").setIndex("unit", "pF", 0);
    model.result().numerical("gev1").setIndex("descr", "\u9ea6\u514b\u65af\u97e6\u7535\u5bb9", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();

    model.title("\u53ef\u8c03 MEMS \u7535\u5bb9\u5668");

    model
         .description("\u672c\u4f8b\u4e2d\u7684\u9759\u7535\u53ef\u8c03\u5f0f\u5e73\u884c\u677f\u7535\u5bb9\u5668\u662f\u5c04\u9891\u8303\u56f4\u4ecb\u4e8e 300\u00a0MHz \u548c 300\u00a0GHz \u4e4b\u95f4\u7684\u5404\u79cd MEMS \u5668\u4ef6\u4e2d\u7684\u5178\u578b\u5143\u4ef6\u3002\u5728\u5176\u4e2d\u4e00\u5757\u677f\u4e0a\u8fde\u63a5\u5f39\u7c27\u540e\uff0c\u5c31\u53ef\u4ee5\u6839\u636e\u5916\u52a0\u7535\u538b\u7684\u53d8\u5316\u6765\u6539\u53d8\u677f\u95f4\u8ddd\u3002\u968f\u540e\u7684\u540e\u5904\u7406\u6b65\u9aa4\u8ba1\u7b97\u7535\u5bb9\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u57fa\u4e8e\u8fb9\u754c\u5143\u6cd5 (BEM) \u7684\u9759\u7535\uff0c\u8fb9\u754c\u5143 \u63a5\u53e3\u8fd0\u884c\u6b64\u4eff\u771f\u3002\u6b64\u63a5\u53e3\u65e0\u9700\u5b9a\u4e49\u6709\u9650\u5efa\u6a21\u57df\u548c\u8fb9\u754c\uff0c\u4e5f\u65e0\u9700\u5bf9\u7535\u5bb9\u5668\u7684\u8584\u4f53\u79ef\u8fdb\u884c\u7f51\u683c\u5212\u5206\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("capacitor_tunable.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
