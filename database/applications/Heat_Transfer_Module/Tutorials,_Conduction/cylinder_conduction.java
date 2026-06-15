/*
 * cylinder_conduction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:33 by COMSOL 6.3.0.290. */
public class cylinder_conduction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Conduction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"0.08[m]", "0.14[m]"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0.02[m]", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "0.02[m] 0.02[m]", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "0.04[m] 0.1[m]", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("ht").feature("solid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1")
         .set("k", new String[]{"52[W/(m*K)]", "0", "0", "0", "52[W/(m*K)]", "0", "0", "0", "52[W/(m*K)]"});
    model.component("comp1").physics("ht").feature("solid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "273.15[K]");
    model.component("comp1").physics("ht").feature("temp1").selection().set(2, 5, 6);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "5e5[W/m^2]");
    model.component("comp1").physics("ht").feature("hf1").selection().set(3);

    model.component("comp1").mesh("mesh1").run();

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
    model.result("pg2").label("\u4e09\u7ef4\u6e29\u5ea6 (ht)");
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
    model.result().dataset("cpt1").set("pointx", 0.04);
    model.result().dataset("cpt1").set("pointy", 0.04);
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").set("data", "cpt1");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result("pg2").run();

    model.title("\u7a33\u6001\u4f20\u5bfc\u4f20\u70ed - \u4e8c\u7ef4\u8f74\u5bf9\u79f0");

    model
         .description("\u8fd9\u662f\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u7a33\u6001\u70ed\u5206\u6790\u7684\u57fa\u51c6\u95ee\u9898\uff0c\u5176\u4e2d\u5305\u542b\u70ed\u901a\u91cf\u3001\u6e29\u5ea6\u548c\u7edd\u70ed\u8fb9\u754c\u6761\u4ef6\u3002\u5206\u6790\u5f97\u5230\u7684\u6e29\u5ea6\u573a\u4e0e NAFEMS \u57fa\u51c6\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("cylinder_conduction.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
