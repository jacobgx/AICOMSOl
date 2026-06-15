/*
 * monai_runup.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class monai_runup {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Shallow_Water_Equations");

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
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{5.448, 3.402});

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("\u5e95\u90e8\u5f62\u8c8c\u7684\u9006\u6df1\u5ea6");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "monai_runup_bathymetry.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("defvars", true);
    model.component("comp1").func("int1").setEntry("funcnames", "col3", "zB");
    model.component("comp1").func("int1").setIndex("fununit", "m", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("\u5165\u5c04\u6ce2\u5256\u9762\u56fe");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "monai_runup_input_wave.txt");
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").set("funcname", "H_in");
    model.component("comp1").func("int2").setIndex("argunit", "s", 0);
    model.component("comp1").func("int2").setIndex("fununit", "m", 0);

    model.component("comp1").probe().create("pdom1", "DomainPoint");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").probe("pdom1").setIndex("coords2", "4.52[m]", 0);
    model.component("comp1").probe("pdom1").setIndex("coords2", "1.196[m]", 1);
    model.component("comp1").probe("pdom1").label("\u63a2\u9488 (x,y)=(4.52,1.196)");
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "swe.H");
    model.component("comp1").probe("pdom1").feature("ppb1").set("descr", "\u603b\u9ad8\u5ea6");
    model.component("comp1").probe().duplicate("pdom2", "pdom1");
    model.component("comp1").probe("pdom2").label("\u63a2\u9488 (x,y)=(4.52,1.696)");
    model.component("comp1").probe("pdom2").setIndex("coords2", "1.696[m]", 1);
    model.component("comp1").probe().duplicate("pdom3", "pdom2");
    model.component("comp1").probe("pdom3").label("\u63a2\u9488 (x,y)=(4.52,2.196)");
    model.component("comp1").probe("pdom3").setIndex("coords2", "2.196[m]", 1);

    model.component("comp1").physics("swe").feature("dp1").set("hb", "-zB");
    model.component("comp1").physics("swe").feature("init1").set("specifyDepth", "specifyTotalHeight");
    model.component("comp1").physics("swe").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("swe").feature("inl1").selection().set(1);
    model.component("comp1").physics("swe").feature("inl1").set("waterDepthSelect", "totalHeight");
    model.component("comp1").physics("swe").feature("inl1").set("H0", "H_in(t)");
    model.component("comp1").physics("swe").feature("inl1").set("velocitySelect", "fromDomain");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").feature().remove("ftri1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,1,10) range(10.25,0.25,25)");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("pdom2").genResult("none");
    model.component("comp1").probe("pdom3").genResult("none");

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
    model.result("pg2").feature("surf1").feature("hght1").set("scale", 10);
    model.result("pg2").feature("surf1").feature("hght1").set("isheightaxisshown", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("filt1", "Filter");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("filt1").set("expr", "h>1e-4[m]");
    model.result("pg2").feature("surf1").feature("filt1").set("nodespec", "all");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("mtrl1").active(false);
    model.result("pg2").feature("surf2").feature("mtrl1").active(true);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "upperleft");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("pdom2").genResult("none");
    model.component("comp1").probe("pdom3").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 19, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 31, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 39, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 47, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 59, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 71, 0);
    model.result("pg2").run();
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg2").run();

    model
         .title("\u6d77\u5578\u51b2\u4e0a\u590d\u6742\u4e09\u7ef4\u5f62\u72b6\u7684\u83ab\u5948\u5c71\u8c37\u6d77\u6ee9");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u65e2\u5b9a\u57fa\u51c6\u6848\u4f8b\uff0c\u9488\u5bf9\u65e5\u672c\u83ab\u5948\u5c71\u8c37\u6d77\u5578\u7206\u53d1\u7684\u573a\u666f\uff0c\u6a21\u62df\u4e86\u6309\u6bd4\u4f8b\u7f29\u5c0f\u7684\u5b9e\u9a8c\u5ba4\u5b9e\u9a8c\u3002\u8be5\u57fa\u51c6\u6d4b\u8bd5\u96c6\u4e2d\u5728\u9760\u8fd1\u6d77\u5cb8\u7ebf\u7684\u533a\u57df\uff0c\u5e76\u63d0\u4f9b\u4e86\u8be6\u7ec6\u7684\u5b9e\u9a8c\u6570\u636e\u3002\u6c34\u69fd\u6700\u521d\u5145\u6ee1\u9759\u6b62\u7684\u6c34\uff0c\u5176\u4e2d\u4e00\u4e2a\u8fb9\u754c\u4e0a\u65bd\u52a0\u4e86\u5df2\u77e5\u7684\u5165\u5c04\u6ce2\uff0c\u4ece\u800c\u4f7f\u6d77\u5cb8\u7ebf\u6765\u56de\u79fb\u52a8\uff0c\u6700\u7ec8\u8986\u76d6\u4e86\u57df\u4e2d\u95f4\u7684\u5c0f\u5c9b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("monai_runup.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
