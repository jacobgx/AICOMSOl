/*
 * heat_radiation_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class heat_radiation_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 0.1, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", 1000);
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 0);
    model.component("comp1").physics("ht").feature("sar1").selection().set(2);
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", 0.98);
    model.component("comp1").physics("ht").feature("sar1").set("Tamb", 300);
    model.component("comp1").physics("ht").feature("solid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1")
         .set("k", new double[]{55.563, 0, 0, 0, 55.563, 0, 0, 0, 55.563});
    model.component("comp1").physics("ht").feature("init1").set("Tinit", 1000);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result("pg1").run();

    model.title("\u7a33\u6001\u8f90\u5c04\u4f20\u70ed - \u4e00\u7ef4");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u7ef4\u7a33\u6001\u70ed\u5206\u6790\u7684\u57fa\u51c6\u95ee\u9898\uff0c\u6a21\u62df\u56fa\u5b9a\u6e29\u5ea6\u4e3a 1000\u00a0K \u7684\u5de6\u7aef\u8f90\u5c04\u81f3 300\u00a0K \u7684\u53f3\u7aef\u7684\u60c5\u51b5\u3002\u5206\u6790\u5f97\u5230\u7684\u6e29\u5ea6\u573a\u4e0e NAFEMS \u57fa\u51c6\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("heat_radiation_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
