/*
 * thermal_bridge_2d_composite_structure.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:19 by COMSOL 6.3.0.290. */
public class thermal_bridge_2d_composite_structure {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Buildings_and_Constructions");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().set("W", "0.5[m]");
    model.param().descr("W", "\u7ed3\u6784\u5bbd\u5ea6");
    model.param().set("w", "15[mm]");
    model.param().descr("w", "\u6728\u677f\u57df\u548c\u94dd\u5c42\u57df\u7684\u8f83\u77ed\u5bbd\u5ea6");
    model.param().set("h1", "6[mm]");
    model.param().descr("h1", "\u6df7\u51dd\u571f\u57df\u9ad8\u5ea6");
    model.param().set("h2", "5[mm]");
    model.param().descr("h2", "\u6728\u677f\u57df\u9ad8\u5ea6");
    model.param().set("h3", "41.5[mm]");
    model.param().descr("h3", "\u9694\u70ed\u6750\u6599\u57df\u9ad8\u5ea6");
    model.param().set("h4", "h3-h2");
    model.param().descr("h4", "\u94dd\u5c42\u57df\u9ad8\u5ea6");
    model.param().set("t4", "1.5[mm]");
    model.param().descr("t4", "\u94dd\u5c42\u57df\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "h3+h1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "h3", 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W", "t4"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"t4", "h4"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"w", "t4"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "h4-t4"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r2", "r3", "r4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"w", "h2"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"0", "h4"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6df7\u51dd\u571f");
    model.component("comp1").material("mat1").selection().set(3);
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.15[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2300[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"880[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6728\u677f");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.12[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"500[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"2500[J/(kg*K)]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u9694\u70ed\u6750\u6599");
    model.component("comp1").material("mat3").selection().set(4);
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.029[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"150[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u94dd");
    model.component("comp1").material("mat4").selection().set(1);
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"230[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"2700[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("heatcapacity", new String[]{"900[J/(kg*K)]"});

    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(2, 10);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "1[W/(m^2*K)]/0.11");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "20[degC]");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().set(9);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "1[W/(m^2*K)]/0.06");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "0[degC]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
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
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").selection().set(2, 10);
    model.result().numerical("int1").set("expr", new String[]{"ht.q0"});
    model.result().numerical("int1").set("descr", new String[]{"\u5411\u5185\u70ed\u901a\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"W/m"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().export().create("data1", "Data");
    model.result().export("data1").set("filename", "thermal_bridge_2d_composite_structure_result.txt");
    model.result().export("data1").set("location", "grid");
    model.result().export("data1").set("gridx2", "0,w,W");
    model.result().export("data1").set("gridy2", "0,h4,h4+h2,h4+h2+h1");
    model.result("pg1").run();

    model.title("\u5efa\u7b51\u7ed3\u6784\u70ed\u6865 - \u4e8c\u7ef4\u590d\u5408\u7ed3\u6784");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u7531\u56db\u79cd\u5177\u6709\u4e0d\u540c\u70ed\u5bfc\u7387\u7684\u6750\u6599\u5236\u6210\u7684\u4e8c\u7ef4\u590d\u5408\u7ed3\u6784\u4e2d\u7684\u4f20\u70ed\uff0c\u5e76\u5c06\u7ed3\u6784\u4e2d\u7684\u6e29\u5ea6\u5206\u5e03\u548c\u70ed\u901a\u91cf\u4e0e\u5df2\u53d1\u5e03\u7684\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\u3002\u672c\u4f8b\u5bf9\u5e94\u4e8e\u6b27\u6d32\u6807\u51c6 EN ISO 10211:2017 \u4e2d\u63cf\u8ff0\u5efa\u7b51\u7ed3\u6784\u70ed\u6865\u7684\u6848\u4f8b\u00a02\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("thermal_bridge_2d_composite_structure.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
