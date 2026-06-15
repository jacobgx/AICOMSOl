/*
 * slurry_transport.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:00 by COMSOL 6.3.0.290. */
public class slurry_transport {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("pfl", "FlowInPipes", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/pfl", true);

    model.param().set("Qv0", "3.4[m^3/min]");
    model.param().descr("Qv0", "\u4f53\u79ef\u6d41\u7387");
    model.param().set("d1", "150[mm ]");
    model.param().descr("d1", "\u7ba1\u5f84\uff0c\u7ba1 1");
    model.param().set("d2", "100[mm]");
    model.param().descr("d2", "\u7ba1\u5f84\uff0c\u7ba1 2");
    model.param().set("d3", "75[mm]");
    model.param().descr("d3", "\u7ba1\u5f84\uff0c\u7ba1 3");
    model.param().set("d4", "175[mm]");
    model.param().descr("d4", "\u7ba1\u5f84\uff0c\u7ba1 4");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 80");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "0 0");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7ba1 1");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2").set("x", "80 80 90 90 120 120 130 130");
    model.component("comp1").geom("geom1").feature("pol2").set("y", "0 0 20 20 20 20 0 0");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u7ba1 2");
    model.component("comp1").geom("geom1").feature("pol2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol3").set("x", "80 80 80 130 130 130");
    model.component("comp1").geom("geom1").feature("pol3").set("y", "0 -20 -20 -20 -20 0");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u7ba1 3");
    model.component("comp1").geom("geom1").feature("pol3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol4").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol4").set("x", "130 180");
    model.component("comp1").geom("geom1").feature("pol4").set("y", "0  0");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u7ba1 4");
    model.component("comp1").geom("geom1").feature("pol4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().named("geom1_csel1_bnd");
    model.component("comp1").variable("var1").set("d", "d1");
    model.component("comp1").variable("var1").descr("d", "\u7ba1\u5f84");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").selection().named("geom1_csel2_bnd");
    model.component("comp1").variable("var2").set("d", "d2");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").selection().named("geom1_csel3_bnd");
    model.component("comp1").variable("var3").set("d", "d3");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").selection().named("geom1_csel4_bnd");
    model.component("comp1").variable("var4").set("d", "d4");

    model.component("comp1").physics("pfl").prop("FluidType").set("fluidmodel", "powerlaw");
    model.component("comp1").physics("pfl").feature("fp1").set("m", 1.4);
    model.component("comp1").physics("pfl").feature("fp1").set("n", 0.4);
    model.component("comp1").physics("pfl").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("pfl").feature("pipe1").set("innerd", "d");
    model.component("comp1").physics("pfl").create("inl1", "Inlet", 0);
    model.component("comp1").physics("pfl").feature("inl1").selection().set(1);
    model.component("comp1").physics("pfl").feature("inl1").set("spec", 1);
    model.component("comp1").physics("pfl").feature("inl1").set("qv0", "Qv0");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (pfl)");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "0.5*pfl.dh");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (pfl)");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").set("arrowcount", 15);
    model.result("pg2").feature("arwl1").set("arrowlength", "normalized");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("col1", "Color");
    model.result("pg2").feature("arwl1").feature("col1").set("showcolordata", "off");
    model.result("pg2").feature("arwl1").feature("col1").set("expr", "pfl.U");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg1").feature("line1").feature("hght1").set("expr", "pfl.ptot");
    model.result("pg1").feature("line1").feature("hght1").set("descr", "\u603b\u538b");
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("titletype", "none");
    model.result("pg1").feature("arwl1").set("arrowlength", "normalized");
    model.result("pg1").feature("arwl1").set("arrowbase", "center");
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", 3.5);
    model.result("pg1").feature("arwl1").set("arrowcount", 14);
    model.result("pg1").feature("arwl1").set("color", "blue");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "pfl.ReMR");
    model.result("pg2").feature("line1").set("descr", "\u5e42\u5f8b\u96f7\u8bfa\u6570");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").create("hght1", "Height");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").feature("col1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(2, 4);
    model.result().numerical("pev1").setIndex("expr", "pfl.Qv", 0);
    model.result().numerical("pev1").setIndex("unit", "m^3/min", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();

    model.title("\u7164\u6d46\u8f93\u9001");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u7164\u6d46\u5728\u7ba1\u9053\u7cfb\u7edf\u4e2d\u7684\u8f93\u9001\uff0c\u5176\u4e2d\u4e0d\u540c\u7ba1\u6bb5\u7684\u7ba1\u5f84\u5404\u4e0d\u76f8\u540c\u3002\u7164\u6d46\u8868\u73b0\u4e3a\u975e\u725b\u987f\u6d41\u4f53\uff0c\u901a\u8fc7\u5e42\u5f8b\u6a21\u578b\u6765\u63cf\u8ff0\u3002\u7ed3\u679c\u7ed9\u51fa\u4e86\u4e0d\u540c\u7ba1\u6bb5\u7684\u6d41\u7387\u4ee5\u53ca\u6574\u4e2a\u7cfb\u7edf\u7684\u603b\u538b\u964d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("slurry_transport.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
