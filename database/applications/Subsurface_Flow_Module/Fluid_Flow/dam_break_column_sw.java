/*
 * dam_break_column_sw.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:28 by COMSOL 6.3.0.290. */
public class dam_break_column_sw {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("swe", "ShallowWaterEquationsTimeExplicit", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("errorexpr", new String[]{"1"});
    model.study("std1").feature("stat").setSolveFor("/physics/swe", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").setSolveFor("/physics/swe", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.61, 1.6});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.4, 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 0.12);
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new double[]{0.24, 0.9});
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("sq1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("swe").feature("init1").set("h_init", 0.01);
    model.component("comp1").physics("swe").create("init2", "init", 2);
    model.component("comp1").physics("swe").feature("init2").selection().set(1);
    model.component("comp1").physics("swe").feature("init2").set("h_init", 0.3);

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").set("probename", "Fp");
    model.component("comp1").probe("bnd1").set("type", "integral");
    model.component("comp1").probe("bnd1").selection().set(7, 8);
    model.component("comp1").probe("bnd1").set("expr", "swe.Fpy");
    model.component("comp1").probe("bnd1").set("descractive", true);
    model.component("comp1").probe("bnd1").set("descr", "\u67f1\u4e0a\u7684\u538b\u529b");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.01,3)");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.component("comp1").probe("bnd1").genResult("none");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u603b\u9ad8\u5ea6 (swe)");
    model.result("pg2").set("edges", "off");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u603b\u9ad8\u5ea6");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "swe.H");
    model.result("pg2").feature("surf1").set("smooth", "everywhere");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("hght1", "Height");
    model.result("pg2").feature("surf1").feature().create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "water");
    model.result("pg2").feature().create("surf2", "Surface");
    model.result("pg2").feature("surf2").label("\u5e95\u90e8\u9ad8\u5ea6");
    model.result("pg2").feature("surf2").set("showsolutionparams", "on");
    model.result("pg2").feature("surf2").set("expr", "swe.hb");
    model.result("pg2").feature("surf2").set("coloring", "uniform");
    model.result("pg2").feature("surf2").set("color", "gray");
    model.result("pg2").feature("surf2").set("smooth", "everywhere");
    model.result("pg2").feature("surf2").set("inheritcolor", false);
    model.result("pg2").feature("surf2").set("inheritrange", false);
    model.result("pg2").feature("surf2").set("showsolutionparams", "on");
    model.result("pg2").feature("surf2").set("data", "parent");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").feature("surf2").feature().create("hght1", "Height");
    model.result("pg2").feature("surf2").feature().create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf2").feature("mtrl1").set("family", "rock");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6\u5927\u5c0f (swe)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u901f\u5ea6\u5927\u5c0f");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "swe.U");
    model.result("pg3").feature("surf1").set("smooth", "everywhere");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("hght1").set("scaleactive", true);

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 23, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 39, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 77, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 145, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 179, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 231, 0);
    model.result("pg2").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 39, 0);
    model.result("pg2").run();

    model.title("\u6e83\u575d\u5bf9\u67f1\u7684\u5f71\u54cd\uff0c\u6d45\u6c34\u65b9\u7a0b");

    model
         .description("\u8fd9\u4e2a\u77ac\u6001\u6a21\u578b\u901a\u8fc7\u6c42\u89e3\u6d45\u6c34\u65b9\u7a0b\u6765\u6a21\u62df\u6c34\u6ce2\u5bf9\u67f1\u7684\u51b2\u51fb\u3002\u95f8\u95e8\u540e\u9762\u6700\u521d\u6709\u4e00\u4e2a 0.3\u00a0\u7c73\u9ad8\u7684\u6c34\u4f53\u3002\u4eff\u771f\u5f00\u59cb\u65f6\uff0c\u95f8\u95e8\u7a81\u7136\u91ca\u653e\uff0c\u6c34\u4f53\u5f62\u6210\u5411\u5efa\u7b51\u7ed3\u6784\u79fb\u52a8\u7684\u6ce2\u3002\u6c34\u5728\u51b2\u51fb\u7ed3\u6784\u540e\u7ee7\u7eed\u6d41\u52a8\uff0c\u76f4\u5230\u78b0\u5230\u6c34\u5e93\u7684\u5899\u58c1\u53cd\u5c04\u56de\u6765\uff0c\u5e76\u518d\u6b21\u51b2\u51fb\u67f1\u4f53\u3002\u672c\u4f8b\u8ba1\u7b97\u67f1\u4e0a\u7684\u538b\u529b\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u5b9e\u9a8c\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("dam_break_column_sw.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
