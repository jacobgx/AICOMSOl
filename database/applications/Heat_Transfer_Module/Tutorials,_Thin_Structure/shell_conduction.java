/*
 * shell_conduction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:35 by COMSOL 6.3.0.290. */
public class shell_conduction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Thin_Structure");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("htlsh", "HeatTransferInShellsLM", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/htlsh", true);

    model.param().set("T_edge", "373.15[K]");
    model.param().descr("T_edge", "\u8fb9\u6e29\u5ea6");
    model.param().set("T_ext", "273.15[K]");
    model.param().descr("T_ext", "\u5916\u90e8\u6e29\u5ea6");
    model.param().set("ht", "750[W/(m^2*K)]");
    model.param().descr("ht", "\u4f20\u70ed\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "2/pi");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "0.6[m]");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("cyl1", 1, 2, 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "(2/pi)*cos(pi*18/180)", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "(2/pi)*sin(pi*18/180)", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"52[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8800[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"420[J/(kg*K)]"});
    model.component("comp1").material("mat1").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat1").propertyGroup("shell").set("lth", new String[]{"0.01[m]"});

    model.component("comp1").physics("htlsh").create("ltemp1", "LineTemperature", 1);
    model.component("comp1").physics("htlsh").feature("ltemp1").selection().set(5);
    model.component("comp1").physics("htlsh").feature("ltemp1").set("T0", "T_edge");
    model.component("comp1").physics("htlsh").create("lhf1", "HeatFlux", 1);
    model.component("comp1").physics("htlsh").feature("lhf1").selection().set(1, 2, 4);
    model.component("comp1").physics("htlsh").feature("lhf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("htlsh").feature("lhf1").set("h", "ht");
    model.component("comp1").physics("htlsh").feature("lhf1").set("Text", "T_ext");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection().set(1);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6\uff0c\u58f3 (htlsh)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("data", "lshl1");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "lshl1");
    model.result("pg1").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();

    model.title("\u58f3\u4f20\u5bfc");

    model
         .description("\u672c\u4f8b\u5bf9\u5bfc\u7535\u8584\u58f3\u4e2d\u7684\u70ed\u4f20\u5bfc\u8fdb\u884c\u9759\u6001\u5206\u6790\uff0c\u5e76\u5c06\u8be5\u57fa\u51c6\u95ee\u9898\u7684\u7ed3\u679c\u4e0e NAFEMS \u57fa\u51c6\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("shell_conduction.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
