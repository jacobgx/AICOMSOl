/*
 * thin_layer_chronoamperometry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:53 by COMSOL 6.3.0.290. */
public class thin_layer_chronoamperometry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electroanalysis");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("c");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"c"});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c_bulk", "1[mol/m^3]", "\u53cd\u5e94\u7269\u672c\u4f53\u6d53\u5ea6");
    model.param().set("L", "60[um]", "\u8584\u5c42\u7535\u6c60\u539a\u5ea6");
    model.param().set("A_el", "1[mm^2]", "\u7535\u6781\u9762\u79ef");
    model.param().set("D", "1e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("t_rise", "1[us]", "\u6052\u7535\u4f4d\u4e0a\u5347\u65f6\u95f4");
    model.param().set("x_step", "sqrt(2*t_rise*D)", "\u89e3\u6790\u7684\u6269\u6563\u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.5);
    model.component("comp1").func("step1").set("smooth", 1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("i_el", "-F_const*A_el*tcd.tflux_cx");
    model.component("comp1").variable("var1").descr("i_el", "\u603b\u7535\u6d41");
    model.component("comp1").variable("var1").set("i_Cottrell", "F_const*A_el*c_bulk*sqrt(D/(pi*t+eps))");
    model.component("comp1").variable("var1").descr("i_Cottrell", "Cottrell \u65b9\u7a0b\u7535\u6d41");

    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 0);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk*(1-step1(t/t_rise))", 0);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "x_step/5");
    model.component("comp1").mesh("mesh1").run("edg1");

    model.study("std1").feature("time").set("tlist", "range(0,0.1,5)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "1e-7");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u6d53\u5ea6 (tcd)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"c"});
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(1);
    model.result("pg2").feature("ptgr1").set("expr", "i_el");
    model.result("pg2").run();
    model.result("pg2").label("\u8ba1\u65f6\u7535\u6d41\u56fe");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0.1);
    model.result("pg2").set("xmax", 5);
    model.result("pg2").set("ymin", 0);
    model.result("pg2").set("ymax", "5e-6");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u4e0e Cottrell \u65b9\u7a0b\u6bd4\u8f83");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "i_Cottrell", 0);
    model.result("pg3").run();
    model.result("pg3").set("axislimits", false);
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("ylog", true);
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", "1e-5");
    model.result("pg3").run();
    model.result().numerical().create("av1", "AvLine");
    model.result().numerical("av1").set("intsurface", true);
    model.result().numerical("av1").selection().set(1);
    model.result().numerical("av1").setIndex("expr", "(c_bulk-c)/c_bulk", 0);
    model.result().numerical("av1").setIndex("descr", "\u53cd\u5e94\u7a0b\u5ea6", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "table");
    model.result("pg4").feature("tblp1").set("table", "tbl1");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").label("\u53cd\u5e94\u7a0b\u5ea6");

    model.title("\u8584\u5c42\u8ba1\u65f6\u5b89\u57f9\u5206\u6790\u6cd5");

    model
         .description("\u672c\u4f8b\u4e2d\uff0c\u5bf9\u5fae\u5c3a\u5ea6\u8584\u5c42\u4e2d\u7684\u8be6\u5c3d\u5b89\u57f9\u68c0\u6d4b\u5efa\u6a21\u4e3a\u4e00\u7ef4\u5bf9\u79f0\u6269\u6563\u6a21\u578b\uff0c\u8fd9\u79cd\u68c0\u6d4b\u65b9\u6cd5\u662f\u4e00\u79cd\u5e38\u89c1\u7684\u7535\u5206\u6790\u65b9\u6cd5\u3002\u6a21\u62df\u5f97\u5230\u7684\u7ed3\u679c\u4e0e\u89e3\u6790 Cottrell \u65b9\u7a0b\u5728\u77ed\u65f6\u95f4\u5185\u7684\u6f14\u5316\u76f8\u540c\uff0c\u4f46\u65f6\u95f4\u8f83\u957f\u65f6\u5219\u51fa\u73b0\u504f\u5dee\uff0c\u8fd9\u662f\u56e0\u4e3a\u6269\u6563\u5c42\u6db5\u76d6\u4e86\u8584\u5c42\u3002");

    model.label("thin_layer_chronoamperometry.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
