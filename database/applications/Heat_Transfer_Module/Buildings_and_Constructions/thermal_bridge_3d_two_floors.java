/*
 * thermal_bridge_3d_two_floors.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:19 by COMSOL 6.3.0.290. */
public class thermal_bridge_3d_two_floors {

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

    model.param().set("wall_t1", "5[cm]");
    model.param().descr("wall_t1", "\u9694\u70ed\u5c42\u539a\u5ea6");
    model.param().set("wall_t2", "30[cm]");
    model.param().descr("wall_t2", "\u58c1\u539a");
    model.param().set("rect1", "1.2[m]");
    model.param().descr("rect1", "\u5899\u58c1\u7b2c\u4e00\u4e2a\u77e9\u5f62\u57fa\u672c\u5c3a\u5bf8");
    model.param().set("rect2", "1.3[m]");
    model.param().descr("rect2", "\u5899\u58c1\u7b2c\u4e8c\u4e2a\u77e9\u5f62\u57fa\u672c\u5c3a\u5bf8");
    model.param().set("rect_shift", "-10[cm]");
    model.param().descr("rect_shift", "\u77e9\u5f62\u7684\u4f4d\u79fb");
    model.param().set("wall_h", "2.15[m]");
    model.param().descr("wall_h", "\u58c1\u9ad8\u5ea6");
    model.param().set("blk_w", "1.15[m]");
    model.param().descr("blk_w", "\u77e9\u5f62\u6c34\u5e73\u5757\u5bbd\u5ea6");
    model.param().set("blk_d", "1.9[m]");
    model.param().descr("blk_d", "\u77e9\u5f62\u6c34\u5e73\u5757\u6df1\u5ea6");
    model.param().set("blk_h", "0.15[m]");
    model.param().descr("blk_h", "\u77e9\u5f62\u6c34\u5e73\u5757\u9ad8\u5ea6");
    model.param().set("blk_shiftx", "5[cm]");
    model.param().descr("blk_shiftx", "\u77e9\u5f62\u6c34\u5e73\u5757\u5728 x \u65b9\u5411\u7684\u4f4d\u79fb");
    model.param().set("blk_shifty", "-0.7[m]");
    model.param().descr("blk_shifty", "\u77e9\u5f62\u6c34\u5e73\u5757\u5728 y \u65b9\u5411\u7684\u4f4d\u79fb");
    model.param().set("blk_shiftz", "1[m]");
    model.param().descr("blk_shiftz", "\u77e9\u5f62\u6c34\u5e73\u5757\u5728 z \u65b9\u5411\u7684\u4f4d\u79fb");
    model.param().set("sq_l", "1[m]");
    model.param().descr("sq_l", "\u65b9\u5f62\u6c34\u5e73\u5757\u8fb9\u957f");
    model.param().set("sq_h", "5[cm]");
    model.param().descr("sq_h", "\u65b9\u5f62\u6c34\u5e73\u5757\u9ad8\u5ea6");
    model.param().set("sq_shift", "0.2[m]");
    model.param().descr("sq_shift", "\u65b9\u5f62\u6c34\u5e73\u5757\u4f4d\u79fb");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 - \u7269\u7406\u573a");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("T_gamma", "0[degC]", "Gamma \u6e29\u5ea6");
    model.param("par2").set("T_beta", "15[degC]", "Beta \u6e29\u5ea6");
    model.param("par2").set("T_alpha", "20[degC]", "Alpha \u6e29\u5ea6");
    model.param("par2").set("R_gamma", "0.05[m^2*K/W]", "Gamma R \u503c");
    model.param("par2").set("R_beta", "0.2[m^2*K/W]", "Beta R \u503c");
    model.param("par2").set("R_alpha", "0.2[m^2*K/W]", "Alpha R \u503c");
    model.param("par2").set("h_gamma", "1/R_gamma", "Gamma \u4f20\u70ed\u7cfb\u6570");
    model.param("par2").set("h_beta", "1/R_beta", "Beta \u4f20\u70ed\u7cfb\u6570");
    model.param("par2").set("h_alpha", "1/R_alpha", "Alpha \u4f20\u70ed\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"rect1", "wall_t1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"wall_t1", "rect1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"rect2", "wall_t2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"rect_shift", "rect_shift"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"wall_t2", "rect2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"rect_shift", "rect_shift"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input").set("r3", "r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "wall_h", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"blk_w", "blk_d", "blk_h"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"blk_shiftx", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "blk_shifty", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "blk_shiftz", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"sq_l", "sq_l", "sq_h"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"sq_shift", "sq_shift", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "blk_shiftz+blk_h", 2);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 6, 17, 33, 38, 60, 63);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ige1", 4, 5);
    model.component("comp1").geom("geom1").feature("sel1").label("\u5185\u5899");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("ige1", 2);
    model.component("comp1").geom("geom1").feature("sel2").label("\u9694\u70ed\u5c42");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5916\u5899");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("ige1", 1);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u6c34\u5e73\u7ed3\u6784");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("ige1", 3);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u5730\u677f");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("ige1", 6);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("Alpha");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("ige1", 33, 34, 35);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("Beta");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("ige1", 39, 40, 41);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("Gamma");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("ige1", 1, 2, 11, 12, 13, 14);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5185\u5899");
    model.component("comp1").material("mat1").selection().named("geom1_sel1");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.7[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1700[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"800[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u9694\u70ed\u5c42");
    model.component("comp1").material("mat2").selection().named("geom1_sel2");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.04[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"200[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u5916\u5899");
    model.component("comp1").material("mat3").selection().named("geom1_sel3");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"2000[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u6c34\u5e73\u7ed3\u6784");
    model.component("comp1").material("mat4").selection().named("geom1_sel4");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2.5[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"5000[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("heatcapacity", new String[]{"600[J/(kg*K)]"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u5730\u677f");
    model.component("comp1").material("mat5").selection().named("geom1_sel5");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"1000[kg/m^3]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("heatcapacity", new String[]{"800[J/(kg*K)]"});

    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").label("\u70ed\u901a\u91cf - Alpha");
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_sel6");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_alpha");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_alpha");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").label("\u70ed\u901a\u91cf - Beta");
    model.component("comp1").physics("ht").feature("hf2").selection().named("geom1_sel7");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "h_beta");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "T_beta");
    model.component("comp1").physics("ht").create("hf3", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf3").selection().set(1, 2, 11, 12, 13, 14);
    model.component("comp1").physics("ht").feature("hf3").label("\u70ed\u901a\u91cf - Gamma");
    model.component("comp1").physics("ht").feature("hf3").selection().named("geom1_sel8");
    model.component("comp1").physics("ht").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf3").set("h", "h_gamma");
    model.component("comp1").physics("ht").feature("hf3").set("Text", "T_gamma");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
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
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical().create("min1", "MinSurface");
    model.result().numerical("min1").selection().named("geom1_sel6");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u6700\u5c0f\u503c 1");
    model.result().numerical("min1").set("table", "tbl1");
    model.result().numerical("min1").setResult();
    model.result().numerical().create("min2", "MinSurface");
    model.result().numerical("min2").selection().named("geom1_sel7");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u6700\u5c0f\u503c 2");
    model.result().numerical("min2").set("table", "tbl2");
    model.result().numerical("min2").setResult();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().named("geom1_sel6");
    model.result().numerical("int1").set("expr", new String[]{"ht.q0"});
    model.result().numerical("int1").set("descr", new String[]{"\u5411\u5185\u70ed\u901a\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"W"});
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl3");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").selection().named("geom1_sel7");
    model.result().numerical("int2").set("expr", new String[]{"ht.q0"});
    model.result().numerical("int2").set("descr", new String[]{"\u5411\u5185\u70ed\u901a\u91cf"});
    model.result().numerical("int2").set("unit", new String[]{"W"});
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u8868\u9762\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl4");
    model.result().numerical("int2").setResult();
    model.result().numerical().create("int3", "IntSurface");
    model.result().numerical("int3").set("intvolume", true);
    model.result().numerical("int3").selection().named("geom1_sel8");
    model.result().numerical("int3").set("expr", new String[]{"ht.q0"});
    model.result().numerical("int3").set("descr", new String[]{"\u5411\u5185\u70ed\u901a\u91cf"});
    model.result().numerical("int3").set("unit", new String[]{"W"});
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u8868\u9762\u79ef\u5206 3");
    model.result().numerical("int3").set("table", "tbl5");
    model.result().numerical("int3").setResult();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("Alpha \u4e0a\u7684\u6700\u4f4e\u6e29\u5ea6");
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_sel6");
    model.result("pg2").run();
    model.result("pg2").create("mms1", "MaxMinSurface");
    model.result("pg2").feature("mms1").create("sel1", "Selection");
    model.result("pg2").feature("mms1").feature("sel1").selection().named("geom1_sel6");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("Beta \u4e0a\u7684\u6700\u4f4e\u6e29\u5ea6");
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_sel7");
    model.result("pg3").run();
    model.result("pg3").feature("mms1").feature("sel1").selection().named("geom1_sel7");
    model.result("pg3").run();
    model.result("pg3").run();

    model
         .title("\u5efa\u7b51\u7ed3\u6784\u70ed\u6865 - \u4e24\u5c42\u697c\u4e4b\u95f4\u7684\u4e09\u7ef4\u7ed3\u6784");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5c06\u4e24\u5c42\u697c\u4e0e\u5916\u90e8\u73af\u5883\u9694\u5f00\u7684\u5efa\u7b51\u7ed3\u6784\u4e2d\u7684\u70ed\u4f20\u5bfc\uff0c\u8be5\u7ed3\u6784\u7531\u56db\u79cd\u5177\u6709\u4e0d\u540c\u70ed\u5bfc\u7387\u7684\u6750\u6599\u5236\u6210\u3002\u5176\u4e2d\u5c06\u7ed3\u6784\u4e2d\u7684\u6e29\u5ea6\u5206\u5e03\u548c\u70ed\u901a\u91cf\u4e0e\u5df2\u53d1\u5e03\u7684\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\u3002\u672c\u4f8b\u5bf9\u5e94\u4e8e\u6b27\u6d32\u6807\u51c6 EN ISO 10211:2017 \u4e2d\u63cf\u8ff0\u5efa\u7b51\u7ed3\u6784\u70ed\u6865\u7684\u6848\u4f8b\u00a03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("thermal_bridge_3d_two_floors.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
