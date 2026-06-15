/*
 * thermal_bridge_3d_iron_bar.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:19 by COMSOL 6.3.0.290. */
public class thermal_bridge_3d_iron_bar {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Buildings_and_Constructions");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().set("w1", "1[m]");
    model.param().descr("w1", "\u9694\u70ed\u5c42\u5bbd\u5ea6");
    model.param().set("d1", "0.2[m]");
    model.param().descr("d1", "\u9694\u70ed\u5c42\u6df1\u5ea6");
    model.param().set("h1", "1[m]");
    model.param().descr("h1", "\u9694\u70ed\u5c42\u9ad8\u5ea6");
    model.param().set("w2", "0.1[m]");
    model.param().descr("w2", "\u94c1\u68d2\u5bbd\u5ea6");
    model.param().set("d2", "0.6[m]");
    model.param().descr("d2", "\u94c1\u68d2\u6df1\u5ea6");
    model.param().set("h2", "50[mm]");
    model.param().descr("h2", "\u94c1\u68d2\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w1", "d1", "h1"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"w2", "d2", "h2"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"w1/2-w2/2", "0", "h1/2-h2/2"});
    model.component("comp1").geom("geom1").run("blk2");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("igf1", "IgnoreFaces");
    model.component("comp1").geom("geom1").feature("igf1").selection("input").set("fin", 11);
    model.component("comp1").geom("geom1").run("igf1");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u5185\u90e8");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("ymin", 0.1);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u5916\u90e8");
    model.component("comp1").selection("box2").set("entitydim", 2);
    model.component("comp1").selection("box2").set("ymax", 0.1);
    model.component("comp1").selection("box2").set("condition", "inside");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u9694\u70ed\u6750\u6599");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.1[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"500[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"1700[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u94c1");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"50[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7800[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"460[J/(kg*K)]"});

    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("box1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "1[W/(m^2*K)]/0.10");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "1[degC]");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().named("box2");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "1[W/(m^2*K)]/0.10");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "0[degC]");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature().create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result().numerical().create("max1", "MaxSurface");
    model.result().numerical("max1").selection().named("box2");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u6700\u5927\u503c 1");
    model.result().numerical("max1").set("table", "tbl1");
    model.result().numerical("max1").setResult();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().named("box2");
    model.result().numerical("int1").set("expr", new String[]{"ht.q0"});
    model.result().numerical("int1").set("descr", new String[]{"\u5411\u5185\u70ed\u901a\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"W"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").setResult();
    model.result("pg1").run();

    model
         .title("\u5efa\u7b51\u7ed3\u6784\u70ed\u6865 - \u5d4c\u5165\u9694\u70ed\u5c42\u7684\u94c1\u68d2\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u7531\u94c1\u68d2\u548c\u9694\u70ed\u5c42\u7ec4\u6210\u7684\u70ed\u6865\u4e2d\u7684\u70ed\u4f20\u5bfc\uff0c\u5176\u4e2d\u9694\u70ed\u5c42\u7528\u4e8e\u5c06\u5185\u90e8\u70ed\u8868\u9762\u4e0e\u5916\u90e8\u51b7\u8868\u9762\u9694\u5f00\u3002\u672c\u4f8b\u5bf9\u5e94\u4e8e\u6b27\u6d32\u6807\u51c6 EN ISO 10211:2017 \u4e2d\u63cf\u8ff0\u5efa\u7b51\u7ed3\u6784\u70ed\u6865\u7684\u6848\u4f8b\u00a04\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("thermal_bridge_3d_iron_bar.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
