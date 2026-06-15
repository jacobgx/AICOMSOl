/*
 * heat_transient_axi.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class heat_transient_axi {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.3, 0.4});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().all();
    model.component("comp1").physics("ht").feature("temp1").set("T0", "1000[degC]");
    model.component("comp1").physics("ht").feature("solid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("k", new int[]{52, 0, 0, 0, 52, 0, 0, 0, 52});
    model.component("comp1").physics("ht").feature("solid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("rho", 7850);
    model.component("comp1").physics("ht").feature("solid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("Cp", 460);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "0[degC]");

    model.study("std1").feature("time").label("\u77ac\u6001 - \u6301\u7eed\u4eff\u771f\uff08\u52a0\u70ed\uff09");
    model.study("std1").feature("time").set("tlist", "range(0,10,380)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-5");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 20, 0);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg2|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg3|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg4|ht"});
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg2").run();
    model.result("pg2").label("\u4e09\u7ef4\u6e29\u5ea6");
    model.result("pg2").setIndex("looplevel", 20, 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").set("solutionparams", "parent");
    model.result("pg3").feature("con1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg3").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg3").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", 0.1);
    model.result().dataset("cpt1").set("pointy", 0.3);
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").set("data", "cpt1");
    model.result().numerical("pev1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("pev1").setIndex("looplevel", new int[]{20}, 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").label("\u77ac\u6001 - \u7b2c\u4e00\u90e8\u5206\uff08\u52a0\u70ed\uff09");
    model.study("std2").feature("time").set("tlist", "range(0,10,190)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1e-5");
    model.study("std2").create("time2", "Transient");
    model.study("std2").feature("time2").label("\u77ac\u6001 - \u7b2c\u4e8c\u90e8\u5206\uff08\u7edd\u70ed\uff09");
    model.study("std2").feature("time2").set("tlist", "range(190,10,380)");
    model.study("std2").feature("time2").set("usertol", true);
    model.study("std2").feature("time2").set("rtol", "1e-5");
    model.study("std2").feature("time2").set("useadvanceddisable", true);
    model.study("std2").feature("time2").set("disabledphysics", new String[]{"ht/temp1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 20, 0);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").run();

    model.study("std2").create("cmbsol", "CombineSolution");
    model.study("std2").feature("cmbsol").set("cssol1", "sol3");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result().dataset("cpt1").label("\u4e8c\u7ef4\u622a\u70b9 - \u6301\u7eed\u52a0\u70ed");
    model.result().dataset().create("cpt2", "CutPoint2D");
    model.result().dataset("cpt2").label("\u4e8c\u7ef4\u622a\u70b9 - \u7ec4\u5408\u89e3");
    model.result().dataset("cpt2").set("data", "dset2");
    model.result().dataset("cpt2").set("pointx", 0.1);
    model.result().dataset("cpt2").set("pointy", 0.3);
    model.result().dataset().create("join1", "Join");
    model.result().dataset("join1").label("\u5408\u5e76 - \u6e29\u5dee");
    model.result().dataset("join1").set("data", "cpt1");
    model.result().dataset("join1").set("data2", "cpt2");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6e29\u5ea6\uff0c\u4e00\u7ef4");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5")
         .set("title", "\u8fde\u7eed\u89e3\u548c\u7ec4\u5408\u89e3\u7684\u6e29\u5ea6\u4e0e\u65f6\u95f4\u5bf9\u6bd4");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("data", "cpt1");
    model.result("pg5").feature("ptgr1").set("linestyle", "dashed");
    model.result("pg5").feature("ptgr1").set("linecolor", "fromtheme");
    model.result("pg5").run();
    model.result("pg5").create("ptgr2", "PointGraph");
    model.result("pg5").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr2").set("linewidth", "preference");
    model.result("pg5").feature("ptgr2").set("data", "cpt2");
    model.result("pg5").feature("ptgr2").set("linecolor", "magenta");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u6301\u7eed\u52a0\u70ed", 0);
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("legend", true);
    model.result("pg5").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr2").setIndex("legends", "190 s \u4e4b\u540e\u7edd\u70ed", 0);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u4e00\u7ef4\u6e29\u5dee");
    model.result("pg6").set("data", "join1");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u6e29\u5dee");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").run();
    model.result("pg2").run();

    model.title("\u8f74\u5bf9\u79f0\u77ac\u6001\u4f20\u70ed");

    model
         .description("\u8fd9\u662f\u8f74\u5bf9\u79f0\u77ac\u6001\u70ed\u5206\u6790\u7684\u57fa\u51c6\u95ee\u9898\u3002\u4eff\u771f\u5f00\u59cb\u65f6\uff0c\u8fb9\u754c\u4e0a\u7684\u6e29\u5ea6\u4ece 0\u00b0C \u53d8\u4e3a 1000\u00b0C\u3002\u672c\u4f8b\u63d0\u4f9b\u901a\u8fc7\u5206\u6790\u5f97\u5230\u7684 190\u00a0s \u65f6\u7684\u6e29\u5ea6\u4e0e NAFEMS \u57fa\u51c6\u89e3\u7684\u6bd4\u8f83\u7ed3\u679c\u3002");

    model.label("heat_transient_axi.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
