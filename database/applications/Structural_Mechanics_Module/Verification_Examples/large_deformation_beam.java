/*
 * large_deformation_beam.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:24 by COMSOL 6.3.0.290. */
public class large_deformation_beam {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/beam", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("F_Lx", "-3.844[MN]", "\u6700\u5927\u538b\u7f29\u8f7d\u8377");
    model.param().set("F_Ly", "1e-3*F_Lx", "\u6a2a\u5411\u8f7d\u8377");
    model.param().set("NCL", "0", "\u5f52\u4e00\u5316\u538b\u7f29\u8f7d\u8377");
    model.param().set("d", "0.1[m]", "\u6881\u7684\u6a2a\u622a\u9762\u5c3a\u5bf8");
    model.param().set("l", "3.2[m]", "\u6881\u957f");
    model.param().set("vMax_Ref", "-2.58[m]", "\u7aef\u90e8\u6700\u5927\u5782\u76f4\u4f4d\u79fb\u53c2\u8003\u503c");
    model.param()
         .set("vFinal_Ref", "-1.36[m]", "\u7aef\u90e8\u6700\u7ec8\u5782\u76f4\u4f4d\u79fb\u53c2\u8003\u503c");
    model.param()
         .set("uFinal_Ref", "-5.04[m]", "\u7aef\u90e8\u6700\u7ec8\u6c34\u5e73\u4f4d\u79fb\u53c2\u8003\u503c");
    model.param().set("I", "d^4/12", "\u6881\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param().set("Fcr", "pi^2*2.1e5[MPa]*I/(4*l^2)", "\u7b2c\u4e00\u4e2a\u4e34\u754c\u5c48\u66f2\u8f7d\u8377");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"l", "d"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "5*d", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "l", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "5*d", 1, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").set("density", "");
    model.material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"7850"});
    model.material("mat1").propertyGroup("def").set("poissonsratio", new String[]{"0"});
    model.material("mat1").propertyGroup("def").set("youngsmodulus", new String[]{"2.1e5[MPa]"});
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk2").selection().set(4);

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "d");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(5);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"NCL*F_Lx", "F_Ly", "0"});
    model.component("comp1").physics("beam").selection().set(4);
    model.component("comp1").physics("beam").feature("csd1").set("SectionType", "RectangularSection");
    model.component("comp1").physics("beam").feature("csd1").set("hy_rect", "d");
    model.component("comp1").physics("beam").feature("csd1").set("hz_rect", "d");
    model.component("comp1").physics("beam").create("fix1", "Fixed", 0);
    model.component("comp1").physics("beam").feature("fix1").selection().set(3);
    model.component("comp1").physics("beam").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl1").set("forcePoint", new String[]{"NCL*F_Lx", "F_Ly", "0"});
    model.component("comp1").physics("beam").feature("pl1").selection().set(6);
    model.component("comp1").physics("beam").feature().duplicate("pl2", "pl1");
    model.component("comp1").physics("beam").feature("pl2").set("forcePoint", new int[]{-1, 0, 0});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 40);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"beam/pl2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "F_Lx", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "N", 0);
    model.study("std1").feature("stat").setIndex("pname", "F_Lx", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "N", 0);
    model.study("std1").feature("stat").setIndex("pname", "NCL", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,1)", 0);
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-4");
    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"beam.mises"});
    model.result("pg2").feature("line1").set("threshold", "manual");
    model.result("pg2").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("line1").set("colortable", "Rainbow");
    model.result("pg2").feature("line1").set("colortabletrans", "none");
    model.result("pg2").feature("line1").set("colorscalemode", "linear");
    model.result("pg2").label("\u5e94\u529b (beam)");
    model.result("pg2").feature("line1").set("colortable", "Rainbow");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 1);
    model.result("pg2").feature("line1").set("tubeendcaps", false);
    model.result("pg2").feature("line1").create("def", "Deform");
    model.result("pg2").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("line1").feature("def").set("scale", "1");
    model.result("pg2").feature("line1").feature("def").set("expr", new String[]{"u2", "v2"});
    model.result("pg2").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();

    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_solid_wZ", "comp1_u", "comp1_u2"});
    model.sol("sol1").feature("s1").feature("se1").feature("ssDef").set("subtermconst", "tol");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_beam_thLin", "comp1_beam_uLin"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subdtech", "auto");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("maxsubiter", 200);
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subntolfact", 1);

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("plotgroup", "pg2");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature().copy("line1", "pg2/line1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("inheritplot", "surf1");
    model.result("pg1").feature("line1").set("inherittubescale", false);
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff08\u5b9e\u4f53\u548c\u6881\uff09");
    model.result("pg1").set("frametype", "material");
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "l");
    model.result().dataset("cpt1").set("pointy", "d/2");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u672b\u7aef\u4f4d\u79fb");
    model.result("pg3").set("data", "cpt1");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("expr", "u");
    model.result("pg3").feature("ptgr1").set("descr", "\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf");
    model.result("pg3").feature("ptgr1").set("linewidth", 3);
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "u (solid)", 0);
    model.result("pg3").run();
    model.result("pg3").create("ptgr2", "PointGraph");
    model.result("pg3").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr2").set("linewidth", "preference");
    model.result("pg3").feature("ptgr2").set("expr", "v");
    model.result("pg3").feature("ptgr2").set("descr", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf");
    model.result("pg3").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg3").feature("ptgr2").set("linewidth", 3);
    model.result("pg3").feature("ptgr2").set("legend", true);
    model.result("pg3").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr2").setIndex("legends", "v (solid)", 0);
    model.result("pg3").run();
    model.result("pg3").create("ptgr3", "PointGraph");
    model.result("pg3").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr3").set("linewidth", "preference");
    model.result("pg3").feature("ptgr3").set("data", "dset1");
    model.result("pg3").feature("ptgr3").selection().set(6);
    model.result("pg3").feature("ptgr3").set("expr", "u2");
    model.result("pg3").feature("ptgr3").set("descr", "\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf");
    model.result("pg3").feature("ptgr3").set("linestyle", "dotted");
    model.result("pg3").feature("ptgr3").set("linemarker", "asterisk");
    model.result("pg3").feature("ptgr3").set("markerpos", "interp");
    model.result("pg3").feature("ptgr3").set("linewidth", 3);
    model.result("pg3").feature("ptgr3").set("legend", true);
    model.result("pg3").feature("ptgr3").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr3").setIndex("legends", "u (beam)", 0);
    model.result("pg3").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr4").set("expr", "v2");
    model.result("pg3").feature("ptgr4").set("descr", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf");
    model.result("pg3").feature("ptgr4").set("linemarker", "circle");
    model.result("pg3").feature("ptgr4").setIndex("legends", "v (beam)", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "\u672b\u7aef\u4f4d\u79fb\u5206\u91cf (m) vs. \u5f52\u4e00\u5316\u7684\u538b\u7f29\u8f7d\u8377");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u672b\u7aef\u4f4d\u79fb");
    model.result("pg3").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").set("data", "cpt1");
    model.result().numerical("pev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("pev1").set("expr", new String[]{"u"});
    model.result().numerical("pev1").set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf"});
    model.result().numerical("pev1").set("unit", new String[]{"m"});
    model.result().numerical("pev1").setIndex("descr", "\u5b9e\u4f53\uff1ax \u4f4d\u79fb", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().duplicate("pev2", "pev1");
    model.result().numerical("pev2").set("data", "dset1");
    model.result().numerical("pev2").selection().set(6);
    model.result().numerical("pev2").setIndex("expr", "u2", 0);
    model.result().numerical("pev2").setIndex("descr", "\u6881\uff1ax \u4f4d\u79fb", 0);
    model.result().numerical("pev2").setIndex("expr", "uFinal_Ref", 1);
    model.result().numerical("pev2")
         .setIndex("descr", "\u672b\u7aef\u5904\u6700\u7ec8\u6c34\u5e73\u4f4d\u79fb\u53c2\u8003\u503c", 1);
    model.result().numerical("pev2").set("table", "tbl1");
    model.result().numerical("pev2").appendResult();
    model.result().numerical().duplicate("pev3", "pev1");
    model.result().numerical("pev3").setIndex("expr", "v", 0);
    model.result().numerical("pev3").setIndex("descr", "\u5b9e\u4f53\uff1ay \u4f4d\u79fb", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u70b9\u8ba1\u7b97 3");
    model.result().numerical("pev3").set("table", "tbl2");
    model.result().numerical("pev3").setResult();
    model.result().numerical().duplicate("pev4", "pev2");
    model.result().numerical("pev4").setIndex("expr", "v2", 0);
    model.result().numerical("pev4").setIndex("descr", "\u6881\uff1ay \u4f4d\u79fb", 0);
    model.result().numerical("pev4").setIndex("expr", "vFinal_Ref", 1);
    model.result().numerical("pev4")
         .setIndex("descr", "\u672b\u7aef\u5904\u6700\u7ec8\u5782\u76f4\u4f4d\u79fb\u53c2\u8003\u503c", 1);
    model.result().numerical("pev4").set("table", "tbl2");
    model.result().numerical("pev4").appendResult();
    model.result().numerical().duplicate("pev5", "pev3");
    model.result().numerical("pev5").setIndex("looplevelinput", "all", 0);
    model.result().numerical("pev5").setIndex("expr", "abs(v)", 0);
    model.result().numerical("pev5").set("dataseries", "maximum");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u70b9\u8ba1\u7b97 5");
    model.result().numerical("pev5").set("table", "tbl3");
    model.result().numerical("pev5").setResult();
    model.result().numerical().duplicate("pev6", "pev4");
    model.result().numerical("pev6").setIndex("looplevelinput", "all", 0);
    model.result().numerical("pev6").setIndex("expr", "abs(v2)", 0);
    model.result().numerical("pev6").setIndex("expr", "abs(vMax_Ref)", 1);
    model.result().numerical("pev6").setIndex("descr", "", 1);
    model.result().numerical("pev6").set("dataseries", "maximum");
    model.result().numerical("pev6").set("table", "tbl3");
    model.result().numerical("pev6").appendResult();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/beam", true);
    model.study("std2").create("buckling", "LinearBuckling");
    model.study("std2").feature("buckling").set("plotgroup", "Default");
    model.study("std2").feature("buckling").set("neigsactive", true);
    model.study("std2").feature("buckling").set("solnum", "auto");
    model.study("std2").feature("buckling").set("notsolnum", "auto");
    model.study("std2").feature("buckling").set("outputmap", new String[]{});
    model.study("std2").feature("buckling").set("ngenAUX", "1");
    model.study("std2").feature("buckling").set("goalngenAUX", "1");
    model.study("std2").feature("buckling").set("ngenAUX", "1");
    model.study("std2").feature("buckling").set("goalngenAUX", "1");
    model.study("std2").feature("buckling").setSolveFor("/physics/solid", true);
    model.study("std2").feature("buckling").setSolveFor("/physics/beam", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid", "beam/pl1"});
    model.study("std2").feature("buckling").set("useadvanceddisable", true);
    model.study("std2").feature("buckling").setSolveFor("/physics/solid", false);
    model.study("std2").feature("buckling").set("disabledphysics", new String[]{"solid", "beam/pl1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("frametype", "spatial");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"beam.disp"});
    model.result("pg4").feature("line1").set("threshold", "manual");
    model.result("pg4").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("line1").set("colortable", "Rainbow");
    model.result("pg4").feature("line1").set("colortabletrans", "none");
    model.result("pg4").feature("line1").set("colorscalemode", "linear");
    model.result("pg4").label("\u632f\u578b (beam)");
    model.result("pg4").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("tuberadiusscale", 1);
    model.result("pg4").feature("line1").set("tubeendcaps", false);
    model.result("pg4").feature("line1").create("def", "Deform");
    model.result("pg4").feature("line1").feature("def").set("expr", new String[]{"u2", "v2"});
    model.result("pg4").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").run();
    model.result().numerical().create("pev7", "EvalPoint");
    model.result().numerical("pev7").set("data", "dset2");
    model.result().numerical("pev7").selection().set(6);
    model.result().numerical("pev7").setIndex("expr", "Fcr", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u70b9\u8ba1\u7b97 7");
    model.result().numerical("pev7").set("table", "tbl4");
    model.result().numerical("pev7").setResult();
    model.result("pg3").run();

    model.title("\u6881\u7684\u5927\u53d8\u5f62\u5206\u6790");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u56fa\u4f53\u529b\u5b66\u201d\u63a5\u53e3\u548c\u201c\u6881\u201d\u63a5\u53e3\u5bf9\u6881\u5efa\u6a21\uff0c\u7136\u540e\u5c06\u4e24\u4e2a\u63a5\u53e3\u5f97\u5230\u7684\u4eff\u771f\u7ed3\u679c\u76f8\u4e92\u6bd4\u8f83\uff0c\u5e76\u4e0e\u6765\u81ea NAFEMS \u7684\u57fa\u51c6\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002\u6b64\u5916\uff0c\u6a21\u578b\u8fd8\u6267\u884c\u4e86\u7ebf\u6027\u5c48\u66f2\u5206\u6790\uff0c\u5e76\u5c06\u6b64\u7814\u7a76\u5f97\u5230\u7684\u4e34\u754c\u8f7d\u8377\u4e0e\u6b27\u62c9\u4e34\u754c\u8f7d\u8377\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("large_deformation_beam.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
